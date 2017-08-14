package org.uncommons.reportng;

import com.ringcentral.qa.reporter.logger.ReportLogger;
import com.ringcentral.qa.reporter.logger.ReportLoggerContainer;
import org.testng.*;
import org.testng.internal.Utils;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;
import org.testng.reporters.XMLSuiteResultWriter;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

/**
 * <code>XMLReporter</code> is simply default XML reporter with custom logger usage. Used logger helps to save memory while preparing reports.
 *
 * @author Anton Gnutov
 */
public class XMLReporter implements IReporter {
    public static final String FILE_NAME = "testng-results.xml";

    private static final ReportLogger REPORT_LOGGER = ReportLoggerContainer.getInstance().getReportLogger();

    private final XMLReporterConfig config = new XMLReporterConfig();
    private XMLStringBuffer rootBuffer;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        if (Utils.isStringEmpty(config.getOutputDirectory())) {
            config.setOutputDirectory(outputDirectory);
        }

        // Calculate passed/failed/skipped
        int passed = 0;
        int failed = 0;
        int skipped = 0;

        for (ISuite s : suites) {
            for (ISuiteResult sr : s.getResults().values()) {
                ITestContext testContext = sr.getTestContext();
                passed += testContext.getPassedTests().size();
                failed += testContext.getFailedTests().size();
                skipped += testContext.getSkippedTests().size();
            }
        }

        rootBuffer = new XMLStringBuffer();
        Properties p = new Properties();
        p.put("passed", passed);
        p.put("failed", failed);
        p.put("skipped", skipped);
        p.put("total", passed + failed + skipped);
        rootBuffer.push(XMLReporterConfig.TAG_TESTNG_RESULTS, p);

        writeReporterOutput(rootBuffer);

        for (ISuite suite : suites) {
            writeSuite(suite);
        }
        rootBuffer.pop();
        Utils.writeUtf8File(config.getOutputDirectory(), FILE_NAME, rootBuffer.toXML());
    }

    private void writeReporterOutput(XMLStringBuffer xmlBuffer) {
        xmlBuffer.push(XMLReporterConfig.TAG_REPORTER_OUTPUT);
        for (String line : REPORT_LOGGER.getAllOutput()) {
            if (line != null) {
                xmlBuffer.push(XMLReporterConfig.TAG_LINE);
                xmlBuffer.addCDATA(line);
                xmlBuffer.pop();
            }
        }
        xmlBuffer.pop();
    }

    private void writeSuite(ISuite suite) {
        switch (config.getFileFragmentationLevel()) {
            case XMLReporterConfig.FF_LEVEL_NONE:
                writeSuiteToBuffer(rootBuffer, suite);
                break;
            case XMLReporterConfig.FF_LEVEL_SUITE:
            case XMLReporterConfig.FF_LEVEL_SUITE_RESULT:
                File suiteFile = referenceSuite(rootBuffer, suite);
                writeSuiteToFile(suiteFile, suite);
        }
    }

    private void writeSuiteToFile(File suiteFile, ISuite suite) {
        XMLStringBuffer xmlBuffer = new XMLStringBuffer();
        writeSuiteToBuffer(xmlBuffer, suite);
        File parentDir = suiteFile.getParentFile();
        if (parentDir.exists() || suiteFile.getParentFile().mkdirs()) {
            Utils.writeFile(parentDir.getAbsolutePath(), FILE_NAME, xmlBuffer.toXML());
        }
    }

    private File referenceSuite(XMLStringBuffer xmlBuffer, ISuite suite) {
        String relativePath = suite.getName() + File.separatorChar + FILE_NAME;
        File suiteFile = new File(config.getOutputDirectory(), relativePath);
        Properties attrs = new Properties();
        attrs.setProperty(XMLReporterConfig.ATTR_URL, relativePath);
        xmlBuffer.addEmptyElement(XMLReporterConfig.TAG_SUITE, attrs);
        return suiteFile;
    }

    private void writeSuiteToBuffer(XMLStringBuffer xmlBuffer, ISuite suite) {
        xmlBuffer.push(XMLReporterConfig.TAG_SUITE, getSuiteAttributes(suite));
        writeSuiteGroups(xmlBuffer, suite);

        Map<String, ISuiteResult> results = suite.getResults();
        XMLSuiteResultWriter suiteResultWriter = new XMLSuiteResultWriter(config);
        for (Map.Entry<String, ISuiteResult> result : results.entrySet()) {
            suiteResultWriter.writeSuiteResult(xmlBuffer, result.getValue());
        }

        xmlBuffer.pop();
    }

    private void writeSuiteGroups(XMLStringBuffer xmlBuffer, ISuite suite) {
        xmlBuffer.push(XMLReporterConfig.TAG_GROUPS);
        Map<String, Collection<ITestNGMethod>> methodsByGroups = suite.getMethodsByGroups();
        for (Map.Entry<String, Collection<ITestNGMethod>> entry : methodsByGroups.entrySet()) {
            Properties groupAttrs = new Properties();
            groupAttrs.setProperty(XMLReporterConfig.ATTR_NAME, entry.getKey());
            xmlBuffer.push(XMLReporterConfig.TAG_GROUP, groupAttrs);
            Set<ITestNGMethod> groupMethods = getUniqueMethodSet(entry.getValue());
            for (ITestNGMethod groupMethod : groupMethods) {
                Properties methodAttrs = new Properties();
                methodAttrs.setProperty(XMLReporterConfig.ATTR_NAME, groupMethod.getMethodName());
                methodAttrs.setProperty(XMLReporterConfig.ATTR_METHOD_SIG, groupMethod.toString());
                methodAttrs.setProperty(XMLReporterConfig.ATTR_CLASS, groupMethod.getRealClass().getName());
                xmlBuffer.addEmptyElement(XMLReporterConfig.TAG_METHOD, methodAttrs);
            }
            xmlBuffer.pop();
        }
        xmlBuffer.pop();
    }

    private Properties getSuiteAttributes(ISuite suite) {
        Properties props = new Properties();
        props.setProperty(XMLReporterConfig.ATTR_NAME, suite.getName());

        // Calculate the duration
        Map<String, ISuiteResult> results = suite.getResults();
        Date minStartDate = new Date();
        Date maxEndDate = null;

        for (Map.Entry<String, ISuiteResult> result : results.entrySet()) {
            ITestContext testContext = result.getValue().getTestContext();
            Date startDate = testContext.getStartDate();
            Date endDate = testContext.getEndDate();
            if (minStartDate.after(startDate)) {
                minStartDate = startDate;
            }
            if (maxEndDate == null || maxEndDate.before(endDate)) {
                maxEndDate = endDate != null ? endDate : startDate;
            }
        }

        // The suite could be completely empty
        if (maxEndDate == null) {
            maxEndDate = minStartDate;
        }
        org.testng.reporters.XMLReporter.addDurationAttributes(config, props, minStartDate, maxEndDate);
        return props;
    }

    private Set<ITestNGMethod> getUniqueMethodSet(Collection<ITestNGMethod> methods) {
        Set<ITestNGMethod> result = new LinkedHashSet<ITestNGMethod>();
        for (ITestNGMethod method : methods) {
            result.add(method);
        }
        return result;
    }
}
