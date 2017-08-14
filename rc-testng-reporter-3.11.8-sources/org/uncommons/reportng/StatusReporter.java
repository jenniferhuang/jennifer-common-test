package org.uncommons.reportng;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.internal.ResultMap;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

import static com.ringcentral.qa.common.MethodUtils.getMethodSig;

/**
 * @author Alexey Ilyin
 */
public class StatusReporter extends AbstractReporter {
    protected static final String SUITE_ID_ATTRIBUTE = "suite_id";
    private static Logger log = LoggerFactory.getLogger(StatusReporter.class);
    private static final String EXECUTION_ID_ATTRIBUTE = "execution_id";
    private static final String UNIQ_ID_ATTRIBUTE = "test_unique_id";
    private static final String REPLAY_INDEX_ATTRIBUTE = "replayIndex";
    private static final String CURRENT_INDEX_ATTRIBUTE = "current_param_index";
    private static final String EXECUTION_ID_KEY = "execution";
    private static final String REPLAY_DATA_KEY = "replayData";
    private static final String REPLAY_FILE = "replay_data.txt";
    private static final String REPORT_DIRECTORY = "status";
    private static final String STATUS_FILE = "rctf_status.txt";
    private static final String TEMPLATES_PATH = "org/uncommons/reportng/templates/status/";
    protected static final String TOTAL_ROW_KEY = "totalRow";

    public StatusReporter() {
        super(TEMPLATES_PATH);
    }
    /**
     * @param classpathPrefix Where in the classpath to load templates from.
     */
    protected StatusReporter(String classpathPrefix) {
        super(classpathPrefix);
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectoryName) {
        log.info("Status reporter started");
        File outputDirectory = new File(outputDirectoryName, REPORT_DIRECTORY);
        outputDirectory.mkdirs();
        recheckResults(suites);
        List<Object> results = processSuites(suites);
        createFailedAndSkippedTestngXml(suites, outputDirectory);
        log.info("Status reporter replay files are created");
        createStatusFile(results, outputDirectory);
        log.info("Status reporter short status is created");
    }

    protected List<Object> processSuites(List<ISuite> suitesToProcess) {
        List<Object> results = new ArrayList<>();
        List<ISuite> suites = new ArrayList<>();
        List<ISuite> emptySuites = new ArrayList<>();
        Long passedTests = 0l;
        Long skippedTests = 0l;
        Long missedAccTests = 0l;
        Long failedTests = 0l;
        Long knownTests = 0l;
        Long scripts = 0l;
        ReportNGUtils utils = new ReportNGUtils();
        Set<String> allExecutionsId = new HashSet<>();
        Object execution = null;
        Boolean enabled = false;
        for (ISuite suite:suitesToProcess) {
            if (execution == null) {
                execution = suite.getAttribute("execution");
                if (execution != null) {
                    enabled = (boolean)suite.getAttribute("dashboardEnabled");
                }
            }
            boolean hasTests = false;
            for(ISuiteResult result: suite.getResults().values()) {
                if (result.getTestContext().getPassedTests().size() > 0 ||
                        result.getTestContext().getSkippedTests().size() > 0 ||
                        result.getTestContext().getFailedTests().size() > 0) {
                    hasTests = true;
                    passedTests += result.getTestContext().getPassedTests().size();
                    int missedAcc = utils.getFewAccountsCount(result.getTestContext());
                    missedAccTests += missedAcc;
                    skippedTests += (result.getTestContext().getSkippedTests().size() - missedAcc);
                    int knownFail = utils.getExpectedFailuresCount(result.getTestContext());
                    knownTests += knownFail;
                    failedTests += (result.getTestContext().getFailedTests().size() - knownFail);
                    scripts += utils.getTotalTestMethods(result.getTestContext());
                    allExecutionsId.addAll(getExecutionsId(result.getTestContext().getFailedTests().getAllResults()));
                    allExecutionsId.addAll(getExecutionsId(result.getTestContext().getPassedTests().getAllResults()));
                    allExecutionsId.addAll(getExecutionsId(result.getTestContext().getSkippedTests().getAllResults()));
                }
            }
            if (hasTests) {
                suites.add(suite);
            } else {
                emptySuites.add(suite);
            }
        }
        results.add(suites);
        results.add(emptySuites);
        List<Long> totalRow = new ArrayList<>(Arrays.asList(passedTests,skippedTests,missedAccTests,failedTests,knownTests,scripts));
        results.add(totalRow);
        results.add(allExecutionsId);
        results.add(execution);
        results.add(enabled);
        return results;
    }

    protected Set<String> getExecutionsId(Set<ITestResult> allResults) {
        Set<String> result = new HashSet<>();
        for (ITestResult itr: allResults) {
            result.add((String)itr.getAttribute(SUITE_ID_ATTRIBUTE));
        }
        return result;
    }

    protected void recheckResults(List<ISuite> suites) {
        for (ISuite suite : suites) {
            for (ISuiteResult result : suite.getResults().values()) {
                IResultMap iResultMapFailed = result.getTestContext().getFailedTests();
                Set<ITestResult> toRemove = new HashSet<>();
                for (ITestResult itr : iResultMapFailed.getAllResults()) {
                    if("true".equalsIgnoreCase(""+itr.getAttribute("oldResultBeforeRetry"))) {
                        toRemove.add(itr);
                    }
                }
                for (ITestResult itr : result.getTestContext().getSkippedTests().getAllResults()) {
                    if("true".equalsIgnoreCase(""+itr.getAttribute("oldResultBeforeRetry"))) {
                        toRemove.add(itr);
                    }
                }
                for (ITestResult itr : result.getTestContext().getFailedButWithinSuccessPercentageTests().getAllResults()) {
                    if("true".equalsIgnoreCase(""+itr.getAttribute("oldResultBeforeRetry"))) {
                        toRemove.add(itr);
                    }
                }
                for (ITestResult del : toRemove) {
                    iResultMapFailed.removeResult(del);
                    result.getTestContext().getSkippedTests().removeResult(del);
                    result.getTestContext().getFailedButWithinSuccessPercentageTests().removeResult(del);
                }
                for (ITestNGMethod iTestNGMethod : iResultMapFailed.getAllMethods()) {
                    for (ITestResult iTestResult : iResultMapFailed.getResults(iTestNGMethod))
                        if (ReportNGUtils.isSkippedAccount(iTestResult.getThrowable())) {
                            iResultMapFailed.removeResult(iTestResult);
                            iTestResult.setStatus(ITestResult.SKIP);
                        }
                }
                Map<String, List<ITestResult>> combined = new HashMap<>();
                IResultMap iFailed = result.getTestContext().getFailedTests();
                for(ITestResult itr: iFailed.getAllResults()) {
                    if (itr.getAttribute("pool_size") != null && (Integer)itr.getAttribute("pool_size") > 1 && itr.getAttribute("pool_processed") == null) {
                        putResult(combined, itr);
                    }
                }
                IResultMap iSkipped = result.getTestContext().getSkippedTests();
                for(ITestResult itr: iSkipped.getAllResults()) {
                    if (itr.getAttribute("pool_size") != null && (Integer)itr.getAttribute("pool_size") > 1 && itr.getAttribute("pool_processed") == null) {
                        putResult(combined, itr);
                    }
                }
                IResultMap iPassed = result.getTestContext().getPassedTests();
                for(ITestResult itr: iPassed.getAllResults()) {
                    if (itr.getAttribute("pool_size") != null && (Integer)itr.getAttribute("pool_size") > 1 && itr.getAttribute("pool_processed") == null) {
                        putResult(combined, itr);
                    }
                }
                for(Map.Entry<String, List<ITestResult>> entry : combined.entrySet()) {
                    entry.getValue().sort(new Comparator<ITestResult>() {
                        @Override
                        public int compare(ITestResult o1, ITestResult o2) {
                            return Long.compare(o1.getStartMillis(),o2.getStartMillis());
                        }
                    });
                    ITestResult res = entry.getValue().get(0);
                    res.setAttribute("otherResults", new ArrayList<ITestResult>());
                    for(ITestResult itr: entry.getValue()) {
                        if(itr.getEndMillis() > res.getEndMillis()) {
                            res.setEndMillis(itr.getEndMillis());
                        }
                        if (itr.getThrowable()!=null && itr.getThrowable().getMessage()!=null && (itr.getThrowable().getMessage().startsWith("Part of invocations") || itr.getThrowable().getMessage().startsWith("All invocations"))) {
                            res.setThrowable(itr.getThrowable());
                            res.setStatus(itr.getStatus());
                        }
                        if ("true".equalsIgnoreCase((String)itr.getAttribute("combined_missed"))) {
                            res.setThrowable(itr.getThrowable());
                            res.setStatus(itr.getStatus());
                        }
                        if (!itr.equals(res)) {
                            ((List<ITestResult>)res.getAttribute("otherResults")).add(itr);
                        }
                        iFailed.removeResult(itr);
                        iSkipped.removeResult(itr);
                        iPassed.removeResult(itr);
                    }
                    if(res.isSuccess()) {
                        iPassed.addResult(res, res.getMethod());
                    } else if(res.getStatus() == ITestResult.SKIP) {
                        iSkipped.addResult(res, res.getMethod());
                    } else {
                        iFailed.addResult(res, res.getMethod());
                    }
                }
            }
        }
    }

    private void putResult(Map<String, List<ITestResult>> combined, ITestResult itr) {
        String key = itr.getInstance().getClass().getCanonicalName()+"."+itr.getMethod().getConstructorOrMethod().getMethod().getName()+"#"+itr.getAttribute("current_param_index");
        if (!combined.containsKey(key)) {
            combined.put(key, new ArrayList<ITestResult>());
        }
        itr.setAttribute("pool_processed","1");
        combined.get(key).add(itr);
    }

    protected void createFailedAndSkippedTestngXml(List<ISuite> suites, File outputDirectory) {
        try {
            int index = 1;
            for (ISuite suite : suites) {
                VelocityContext context = createContext();
                context.put(EXECUTION_ID_KEY, suite.getAttribute(EXECUTION_ID_ATTRIBUTE));
                Map<String, String> failedData = new HashMap<>();
                Map<String, String> skippedData = new HashMap<>();
                Map<String, String> missAccountsData = new HashMap<>();
                Map<String, String> otherSkipsData = new HashMap<>();
                Map<String, String> knownFailuresData = new HashMap<>();
                Map<String, String> otherFailsData = new HashMap<>();
                for (ISuiteResult result : ReportNGUtils.sortByComparator(suite.getResults()).values()) {
                    IResultMap failedTests = result.getTestContext().getFailedTests();
                    if (failedTests.size() >0) {
                        failedData.putAll(formData(failedTests));
                        knownFailuresData.putAll(formData(extractKnown(failedTests, true)));
                        otherFailsData.putAll(formData(extractKnown(failedTests, false)));
                    }
                    IResultMap skippedTests = result.getTestContext().getSkippedTests();
                    if (skippedTests.size() >0) {
                        skippedData.putAll(formData(skippedTests));
                        missAccountsData.putAll(formData(extractSpecial(skippedTests, true)));
                        otherSkipsData.putAll(formData(extractSpecial(skippedTests, false)));
                    }
                }
                if (failedData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, failedData);
                    generateFile(new File(outputDirectory, String.format("suite%d_failed_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (skippedData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, skippedData);
                    generateFile(new File(outputDirectory, String.format("suite%d_skipped_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (missAccountsData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, missAccountsData);
                    generateFile(new File(outputDirectory, String.format("suite%d_skipped_missaccounts_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (otherSkipsData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, otherSkipsData);
                    generateFile(new File(outputDirectory, String.format("suite%d_skipped_other_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (knownFailuresData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, knownFailuresData);
                    generateFile(new File(outputDirectory, String.format("suite%d_failed_known_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (otherFailsData.size() > 0) {
                    context.put(REPLAY_DATA_KEY, otherFailsData);
                    generateFile(new File(outputDirectory, String.format("suite%d_failed_other_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (failedData.size() + skippedData.size() > 0) {
                    Map<String,String> combined = combine(failedData,skippedData);
                    context.put(REPLAY_DATA_KEY, combined);
                    generateFile(new File(outputDirectory, String.format("suite%d_notpassed_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (otherFailsData.size() + skippedData.size() > 0) {
                    Map<String,String> combined = combine(otherFailsData,skippedData);
                    context.put(REPLAY_DATA_KEY, combined);
                    generateFile(new File(outputDirectory, String.format("suite%d_except_known_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (failedData.size() + otherSkipsData.size() > 0) {
                    Map<String,String> combined = combine(failedData,otherSkipsData);
                    context.put(REPLAY_DATA_KEY, combined);
                    generateFile(new File(outputDirectory, String.format("suite%d_except_miss_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                if (otherFailsData.size() + otherSkipsData.size() > 0) {
                    Map<String,String> combined = combine(otherFailsData,otherSkipsData);
                    context.put(REPLAY_DATA_KEY, combined);
                    generateFile(new File(outputDirectory, String.format("suite%d_other_%s", index, REPLAY_FILE)),
                            REPLAY_FILE + TEMPLATE_EXTENSION,
                            context);
                }
                ++index;
            }
        } catch(Exception ex) {
            log.warn("Some exception during preparation replay files", ex);
        }
    }

    /**
     * extracts either only miss accounts skips or any other
     * @param skippedTests - initial
     * @param missAccounts - extract missing accounts
     * @return result map with special type of skips
     */
    private IResultMap extractSpecial(IResultMap skippedTests, boolean missAccounts) {
        IResultMap irmResult = new ResultMap();
        for (ITestResult result: skippedTests.getAllResults()) {
            if (ReportNGUtils.isSkippedAccount(result.getThrowable()) && missAccounts) {
                irmResult.addResult(result, result.getMethod());
            } else if (!ReportNGUtils.isSkippedAccount(result.getThrowable()) && !missAccounts) {
                irmResult.addResult(result, result.getMethod());
            }
        }
        return irmResult;
    }

    /**
     * extracts either only known failures or any other
     * @param failedTests - initial
     * @param known - extract known failures
     * @return result map with special type of fails
     */
    private IResultMap extractKnown(IResultMap failedTests, boolean known) {
        IResultMap irmResult = new ResultMap();
        for (ITestResult result: failedTests.getAllResults()) {
            if (ReportNGUtils.isKnownFailure(result) && known) {
                irmResult.addResult(result, result.getMethod());
            } else if (!ReportNGUtils.isKnownFailure(result) && !known) {
                irmResult.addResult(result, result.getMethod());
            }
        }
        return irmResult;
    }

    /**
     * Combines two replay data into one
     */
    private Map<String, String> combine(Map<String, String> failedData, Map<String, String> skippedData) {
        Map<String, String> result = new HashMap<>();
        result.putAll(failedData);
        for (Map.Entry<String, String> entry: skippedData.entrySet()) {
            if (!result.containsKey(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            } else {
                result.put(entry.getKey(), result.get(entry.getKey()) + "," + entry.getValue());
            }
        }
        return result;
    }

    /**
     * Form data for replay file
     * in view: className.methodName:index = uniqueId
     * className.methodName = indexes
     */
    private Map<String, String> formData(IResultMap... tests) {
        Map<String, String> results = new HashMap<>();
        for (IResultMap irm:tests) {
            for (ITestResult result: irm.getAllResults()) {
                String instanceClassName = result.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
                String declaredClassName = result.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
                if (result.getInstance() != null) {
                    instanceClassName = result.getInstance().getClass().getCanonicalName();
                }
                putData(results, result, declaredClassName + "." + getMethodSig(result.getMethod().getConstructorOrMethod().getMethod()));
                // collect failed results based on instance class
                if (!declaredClassName.equals(instanceClassName)) {
                    putData(results, result, instanceClassName + "." + getMethodSig(result.getMethod().getConstructorOrMethod().getMethod()));
                }
            }
        }
        return results;
    }

    private void putData(Map<String, String> results, ITestResult result, String methodName) {
        if (result.getAttribute(CURRENT_INDEX_ATTRIBUTE) != null) {
            results.put(methodName +"#"+result.getAttribute(CURRENT_INDEX_ATTRIBUTE), result.getAttribute(UNIQ_ID_ATTRIBUTE) + "#" + result.getAttribute(REPLAY_INDEX_ATTRIBUTE) + "#" + result.getAttribute(SUITE_ID_ATTRIBUTE));
            String indexes = results.get(methodName);
            if (indexes!=null) {
                indexes +=",";
            } else {
                indexes = "";
            }
            results.put(methodName, indexes + result.getAttribute(CURRENT_INDEX_ATTRIBUTE));
        } else {
            if (result.getAttribute("prev_replay_data") !=null) {
                results.putAll((Map<String, String>)result.getAttribute("prev_replay_data"));
            } else {
                String suiteId = (String)result.getAttribute(SUITE_ID_ATTRIBUTE);
                results.put(methodName +"#-1", methodName +"#*#" + suiteId);
                results.put(methodName, "-1");
            }
        }
    }

    protected void createStatusFile(List<Object> results, File outputDirectory) {
        try {
            VelocityContext context = createContext();
            context.put("executions", results.get(3));
            context.put("run", results.get(4));
            context.put("enabledInfo",  results.get(5));
            context.put(TOTAL_ROW_KEY, results.get(2));
            generateFile(new File(outputDirectory, STATUS_FILE),
                    STATUS_FILE + TEMPLATE_EXTENSION,
                    context);
        } catch(Exception ex) {
            log.warn("Some exception during preparation short status", ex);
        }
    }

}
