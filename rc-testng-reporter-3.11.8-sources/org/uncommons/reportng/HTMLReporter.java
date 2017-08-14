package org.uncommons.reportng;

import com.ringcentral.qa.common.annotations.TestName;
import com.ringcentral.qa.configuration.ConfigurationHolder;
import com.ringcentral.qa.reporter.ClassResults;
import com.ringcentral.qa.reporter.customgroups.CustomGroups;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.internal.Configuration;
import org.testng.internal.ResultMap;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.Logger;
import org.testng.xml.XmlTest;
import org.uncommons.reportng.mock.ReportSuiteResult;

/**
 * Enhanced HTML reporter for TestNG that uses Velocity templates to generate its
 * output.
 *
 * @author Daniel Dyer
 */
public class HTMLReporter extends StatusReporter {
    private static final String FRAMES_PROPERTY = "org.uncommons.reportng.frames";
    private static final String SWITCH_OFF_PROPERTY = "org.uncommons.reportng.switchOff";
    private static final String GROUPS_REPORTING = "groups.reporting";
    private static final String GROUPS_BY_CLASS = "groups.byclass";
    private static final String GROUP_BY_METHOD = "group.by.method";
    private static final String SORT_BY_PDV = "sort.by.pdv";

    private static final String TEMPLATES_PATH = "org/uncommons/reportng/templates/html/";
    private static final String INDEX_FILE = "index.html";
    private static final String SUITES_FILE = "suites.html";
    private static final String OVERVIEW_FILE = "overview.html";
    private static final String JIRA_STATUS_FILE = "jirastatus.html";
    private static final String GROUPS_FILE = "groups.html";
    private static final String RESULTS_FILE = "results.html";
    private static final String RESULTEST_FILE = "test_result.html";
    private static final String CUSTOM_STYLE_FILE = "custom.css";
    private static final String LOG_FILE = "test-log.log";
    private static final String LOG_EXTENSION = "log";

    private static final String SUITE_KEY = "suite";
    private static final String SUITES_KEY = "suites";
    private static final String FILES = "files";
    private static final String FILES_KEY = FILES;
    private static final String FILES_FILE = FILES+".html";
    private static final String SHOW_CONFIG_KEY = "showConfig";
    private static final String ENV_KEY = "env";
    private static final String EMPTY_SUITES_KEY = "emptySuites";
    private static final String GROUP_SUITES_KEY = "groupSuites";
    private static final String NORMAL_KEY = "normal";
    private static final String ANOTHER_VIEW_KEY = "anotherView";
    private static final String PREFIX_KEY = "prefix";
    private static final String GROUPS_KEY = "groups";
    private static final String RESULT_KEY = "result";
    private static final String STATUS_KEY = "testResultStatus";
    private static final String DEPEND_KEY = "dependency";
    private static final String DURATION_KEY = "duration";
    private static final String START_KEY = "start";
    private static final String PARENT_KEY = "parent";
    private static final String FAILED_CONFIG_KEY = "failedConfigurations";
    private static final String FAILED_TESTS_KEY = "failedTests";
    private static final String SKIPPED_TESTS_KEY = "skippedTests";
    private static final String ALL_TESTS_KEY = "allTests";
    private static final String GROUP_BY_METHOD_KEY = "groupByMethod";

    private static final String JIRA_CONNECTOR_KEY = "jiraConnector";
    public static final JiraConnector JIRA_CONNECTOR = new JiraConnector();

    private static final String REPORT_DIRECTORY = "html";

    private static final Comparator<ITestNGMethod> METHOD_COMPARATOR = new TestMethodComparator();
    private static final Comparator<IClass> CLASS_COMPARATOR = new TestClassComparator();
    private static final String FIRST_SPLIT_KEY = "firstPortion";
    private static final String LAST_SPLIT_KEY = "lastPortion";
    private static Logger log = LoggerFactory.getLogger(HTMLReporter.class);
    private static final String ATTRIBUTE_FILE_NAME = "fileName";
    private static final String ATTRIBUTE_LOG_NAME = "logFile";
    private static final String ATTRIBUTE_OVERVIEW_FILE_NAME = "overviewFileName";
    private static final String ATTRIBUTE_GROUP_OVERVIEW_FILE_NAME = "groupOverviewFileName";
    private static final String ATTRIBUTE_PARENTS = "parents";
    private static final String SUITE_IS_PROCESSED = "suite_is_processed_by_HTML_Reporter";
    private static CustomGroups customGroupInstance = null;
    private Map<String, IClass> classes = new HashMap<>();

    public HTMLReporter() {
        super(TEMPLATES_PATH);
    }


    /**
     * Generates a set of HTML files that contain data about the outcome of
     * the specified test suites.
     *
     * @param suitesInitial              Data about the test runs.
     * @param outputDirectoryName The directory in which to create the report.
     */
    @SuppressWarnings("unchecked")
    public void generateReport(List<XmlSuite> xmlSuites,
                               List<ISuite> suitesInitial,
                               String outputDirectoryName) {
        super.generateReport(xmlSuites, suitesInitial, outputDirectoryName);
        if ("true".equalsIgnoreCase(System.getProperty(SWITCH_OFF_PROPERTY))) {
            log.info ("HTML Reporter switched off");
            return;
        }
        List<ISuite> suites = removeWithoutReport(suitesInitial);
        if (suites.size() == 0) {
            return;
        }
        if (suites.get(0).getAttribute(SUITE_IS_PROCESSED) != null) {
            log.info("HTML Reporter called second time. skipping....");
            return;
        } else {
            suites.get(0).setAttribute(SUITE_IS_PROCESSED, true);
        }
	    log.info("HTML reporter is started for " + (suites.size() > 1 ? "" + suites.size() + " suites " : "" + suites.get(0).getName()));
        removeEmptyDirectories(new File(outputDirectoryName));
        recheckResults(suites);
        log.info("HTML reporter results are rechecked");

        boolean useFrames = System.getProperty(FRAMES_PROPERTY, "true").equals("true");
        boolean groupsReporting = System.getProperty(GROUPS_REPORTING, "false").equals("true");
        boolean groupsByClass = System.getProperty(GROUPS_BY_CLASS, "false").equals("true");
        File outputDirectory = new File(outputDirectoryName, REPORT_DIRECTORY);
        //noinspection ResultOfMethodCallIgnored
        outputDirectory.mkdirs();
        List<ISuite> suitesToProcess = new ArrayList<>(suites);
        List<ISuite> newGroupSuites;
        List<ISuite> emptySuites;
        List<Long> totalRow;
        String overviewFile = OVERVIEW_FILE;
        String groupOverviewFile = "g_"+OVERVIEW_FILE;
        try {
            copyResources(outputDirectory);
            log.info("HTML reporter resources are copied");
            if (useFrames) {
                createFrameset(outputDirectory, groupsReporting);
                log.info("HTML reporter frameset is created");
            } else {
               if (groupsReporting) {
                   groupOverviewFile = INDEX_FILE;
               } else {
                   overviewFile = INDEX_FILE;
               }
            }
            createJiraStatusReport(outputDirectory);
            log.info("HTML reporter jira report is created");
            updateFileNames(suitesToProcess, overviewFile, groupOverviewFile);
            newGroupSuites = prepareSuitesAsGroups(suitesToProcess, groupsByClass);
            log.info("HTML reporter group suites are created");
            List<Object> results = processSuites(suitesToProcess);
            suitesToProcess = (List<ISuite>)results.get(0);
            emptySuites = (List<ISuite>)results.get(1);
            totalRow = (List<Long>) results.get(2);
            log.info("HTML reporter suites are processed ");
            createOverview(suitesToProcess, outputDirectory, !useFrames && !groupsReporting, useFrames, emptySuites, totalRow);
            createOverview(newGroupSuites, outputDirectory, !useFrames && groupsReporting, useFrames, Collections.<ISuite>emptyList(), totalRow, "g_");
            log.info("HTML reporter overview is created");
            createSuiteList(suitesToProcess, outputDirectory, emptySuites, newGroupSuites);
            createFilesList(outputDirectory);
            log.info("HTML reporter suites list is created");
            createGroups(suitesToProcess, outputDirectory);
            log.info("HTML reporter groups are created");
            createResults(suitesToProcess, outputDirectory, overviewFile);
            createResults(newGroupSuites, outputDirectory, groupOverviewFile, "g_");
            log.info("HTML reporter results are created");
            createTestResults(suitesToProcess, outputDirectory);
            log.info("HTML reporter test results are created");
            createFailedAndSkippedTestngXml(suitesToProcess, outputDirectory);
            log.info("HTML reporter replay files are created");
            createStatusFile(results, outputDirectory);
            log.info("HTML reporter short status is created");
        } catch (Exception ex) {
            log.debug(ex.getMessage(), ex);
            throw new ReportNGException("Failed generating HTML report.", ex);
        }
        log.info("HTML reporter is finished for " + (suites.size()>1?""+suites.size()+ " suites ":""+suites.get(0).getName()));
    }

    private List<ISuite> removeWithoutReport(List<ISuite> suitesInitial) {
        List<ISuite> result = new ArrayList<>();
        for (ISuite suite: suitesInitial) {
            if (suite.getAttribute("noReport") == null || suite.getAttribute("noReport") != Boolean.TRUE) {
                result.add(suite);
            }
        }
        return result;
    }

    private void updateFileNames(List<ISuite> suitesToProcess, String linkToOverview, String linkToGroupOverview) {
        int overallNumber = 0;
        for (ISuite suite:suitesToProcess) {
            for (ISuiteResult suiteResult : suite.getResults().values()) {
                overallNumber = prepareFileNames(suiteResult, overallNumber, linkToOverview, linkToGroupOverview);
            }
        }
    }

    private List<ISuite> prepareSuitesAsGroups(List<ISuite> suitesToProcess ,boolean perClassBasis) {
        List<ISuite> results = new ArrayList<>();
        Map<String, ISuite> mapSuites = new HashMap<>();
        Set<String> groups = new TreeSet<>();
        for (ISuite suite:suitesToProcess) {
            for (ISuiteResult suiteResult : suite.getResults().values()) {
                Set<ITestResult> allResults = new HashSet<>(suiteResult.getTestContext().getPassedTests().getAllResults());
                allResults.addAll(suiteResult.getTestContext().getFailedTests().getAllResults());
                allResults.addAll(suiteResult.getTestContext().getSkippedTests().getAllResults());
                for (ITestResult itr:allResults) {
                    Set<String> methodGroups = new HashSet<>(Arrays.asList(itr.getMethod().getGroups()));
                    addAdditionalGroups(methodGroups, itr);
                    if (methodGroups.isEmpty()) {
                        methodGroups.add("_____Without groups______");
                    }
                    for(String group: methodGroups) {
                        if (!mapSuites.containsKey(group)) {
                            XmlSuite xml = new XmlSuite();
                            xml.setName(group);
                            ISuite newSuite = new SuiteRunner(new Configuration(),xml,null);
                            mapSuites.put(group, newSuite);
                            groups.add(group);
                        }
                        ISuite newSuite = mapSuites.get(group);
                        String resultName = perClassBasis? defineName(itr.getMethod()):suite.getName();
                        if (!newSuite.getResults().containsKey(resultName)) {
                             newSuite.getResults().put(resultName, new ReportSuiteResult(newSuite, resultName));
                        }
                        ITestContext newContext = newSuite.getResults().get(resultName).getTestContext();
                        switch (itr.getStatus()) {
                            case ITestResult.SUCCESS: newContext.getPassedTests().addResult(itr,itr.getMethod());
                                break;
                            case ITestResult.SKIP: newContext.getSkippedTests().addResult(itr,itr.getMethod());
                                break;
                            case ITestResult.FAILURE: newContext.getFailedTests().addResult(itr,itr.getMethod());
                                break;
                        }
                    }
                }
                for (ITestResult itr:suiteResult.getTestContext().getFailedConfigurations().getAllResults()){
                    for (String group:groups) {
                        String resultName = perClassBasis? defineName(itr.getMethod()):suite.getName();
                        if (mapSuites.get(group).getResults().containsKey(resultName)) {
                            mapSuites.get(group).getResults().get(resultName).getTestContext().getFailedConfigurations().addResult(itr, itr.getMethod());
                        }
                    }
                }
            }
        }
        for (String group:groups) {
            results.add(mapSuites.get(group));
        }
        return results;
    }

    @SuppressWarnings("squid:S2444")
    private void addAdditionalGroups(Set<String> methodGroups, ITestResult itr) {
        String customGroupClass = System.getProperty("customGroupsClass");
        if(customGroupClass != null && customGroupInstance == null) {
            try {
                customGroupInstance = (CustomGroups)Class.forName(customGroupClass).newInstance();
            } catch (Exception e) {
                log.error("Cannot initialize customGroupInstance, due to: " + e.getMessage());
            }
        }
        if (customGroupInstance != null) {
            //applyGroups
            customGroupInstance.updateGroupsBasedOnTestResult(methodGroups, itr);
        }
    }

    /**
     * Create the index file that sets up the frameset.
     *
     * @param outputDirectory The target directory for the generated file(s).
     */
    private void createFrameset(File outputDirectory, boolean groupsReporting) throws Exception {
        log.debug("Start creation of frameset file");
        VelocityContext context = createContext();
        context.put(PREFIX_KEY , groupsReporting? "g_": "");
        generateFile(new File(outputDirectory, INDEX_FILE),
                INDEX_FILE + TEMPLATE_EXTENSION,
                context);
        log.debug("Frameset file is created");
    }

    private void createOverview(List<ISuite> suites,
                                File outputDirectory,
                                boolean isIndex,
                                boolean useFrames,
                                List<ISuite> emptySuites,
                                List<Long> totalRow
                                ) throws Exception {
        this.createOverview(suites, outputDirectory, isIndex, useFrames, emptySuites, totalRow, "");
    }

    private void createOverview(List<ISuite> suites,
                                File outputDirectory,
                                boolean isIndex,
                                boolean useFrames,
                                List<ISuite> emptySuites,
                                List<Long> totalRow,
                                String prefix) throws Exception {
        log.debug("Start creation of overview file");
        VelocityContext context = createContext();
        context.put(SUITES_KEY, suites);
        context.put(EMPTY_SUITES_KEY, emptySuites);
        context.put(TOTAL_ROW_KEY, totalRow);
        context.put(PREFIX_KEY, prefix);
        String fileName = OVERVIEW_FILE;
        if (!useFrames && !isIndex) {
            fileName = INDEX_FILE;
        } else if ("".equals(prefix)) {
            fileName = "g_" + OVERVIEW_FILE;
        }
        context.put(ANOTHER_VIEW_KEY, fileName);
        generateFile(new File(outputDirectory, isIndex ? INDEX_FILE : prefix + OVERVIEW_FILE),
                OVERVIEW_FILE + TEMPLATE_EXTENSION,
                context);
        log.debug("Overview file is created");
    }

    private void createJiraStatusReport(File outputDirectory) throws Exception {
        log.debug("Start creation of jira status file");
        VelocityContext context = createContext();
        context.put(JIRA_CONNECTOR_KEY, JIRA_CONNECTOR);
        generateFile(new File(outputDirectory, JIRA_STATUS_FILE), JIRA_STATUS_FILE + TEMPLATE_EXTENSION, context);
        log.debug("JIRA status file is created");
    }

    /**
     * Create the navigation frame.
     *
     * @param outputDirectory The target directory for the generated file(s).
     */
    private void createSuiteList(List<ISuite> suites,
                                 File outputDirectory, List<ISuite> emptySuites,
                                 List<ISuite> newGroupSuites) throws Exception {
        log.debug("Start creation of suite list file");
        VelocityContext context = createContext();
        boolean showDashLink = false;
        for(ISuite suite: suites) {
            if (suite.getAttribute("run")!=null) {
                showDashLink = true;
                context.put("dashRun",suite.getAttribute("run"));
                break;
            }
        }
        context.put("showDashLink",showDashLink);
        context.put(SUITES_KEY, suites);
        context.put(EMPTY_SUITES_KEY, emptySuites);
        context.put(GROUP_SUITES_KEY, newGroupSuites);
        context.put(NORMAL_KEY, !System.getProperty(GROUPS_REPORTING, "false").equals("true"));
        File f = new File (outputDirectory,FILES);
        context.put(SHOW_CONFIG_KEY, false);
        if (f.exists()) {
            List<String> files = Arrays.asList(f.list());
            if (files.size() > 0) {
                context.put(SHOW_CONFIG_KEY, true);
            }
        }
        generateFile(new File(outputDirectory, SUITES_FILE),
        SUITES_FILE + TEMPLATE_EXTENSION,
        context);
        log.debug("Suite list file is created");
    }

    /**
     * Create the navigation frame.
     *
     * @param outputDirectory The target directory for the generated file(s).
     */
    private void createFilesList(File outputDirectory) throws Exception {
        File f = new File (outputDirectory,FILES);
        if (f.exists()) {
            List<String> files = Arrays.asList(f.list());
            if (files.size() > 0) {
                log.debug("Start creation of files list file");
                VelocityContext context = createContext();
                context.put(FILES_KEY, files);
                context.put(ENV_KEY, ConfigurationHolder.getInstance().getConfiguration().getEnvironment());
                generateFile(new File(outputDirectory, FILES_FILE),
                        FILES_FILE + TEMPLATE_EXTENSION,
                        context);
                log.debug("Files list file is created");
            }
        }
    }

    /**
     * Generate a results file for each test in each suite.
     *
     * @param outputDirectory The target directory for the generated file(s).
     */
    private void createResults(List<ISuite> suites,
                               File outputDirectory, String linkToParent) throws Exception {
        this.createResults(suites, outputDirectory, linkToParent,  "");
    }
    private void createResults(List<ISuite> suites,
                               File outputDirectory, String linkToParent, String prefix) throws Exception {
        log.debug("Start creation of results files");
        boolean groupByMethod = System.getProperty(GROUP_BY_METHOD, "false").equals("true");
        boolean sortByPDV = System.getProperty(SORT_BY_PDV, "false").equals("true");
        String type = "class";
        if (sortByPDV) {
            type = "pdv";
        }
        int index = 1;
        for (ISuite suite : suites) {
            int index2 = 1;
            log.debug("Start creation results files for suite " + suite.getName() + ". " + index + " of "+ suites.size());
            int totalResults =  suite.getResults().size();
            for (ISuiteResult result : ReportNGUtils.sortByComparator(suite.getResults()).values()) {
                log.debug("Start creation of result file for suite "+ suite.getName()+" and result "+result.getTestContext().getName()+ ". " + index2 +" of "+totalResults);
                VelocityContext context = createContext();
                Map<String, Object> internalContext = new HashMap<>();
                internalContext.put(META_KEY, context.get(META_KEY));
                internalContext.put(UTILS_KEY, context.get(UTILS_KEY));
                internalContext.put(MESSAGES_KEY, context.get(MESSAGES_KEY));
                internalContext.put(RESULT_KEY, result);
                internalContext.put(FAILED_CONFIG_KEY, sortBy(result.getTestContext().getFailedConfigurations(), type));
                internalContext.put(FAILED_TESTS_KEY, sortBy(result.getTestContext().getFailedTests(), type));
                internalContext.put(SKIPPED_TESTS_KEY, sortBy(result.getTestContext().getSkippedTests(), type));
                IResultMap iResultMap = getResults(result.getTestContext().getPassedTests());
                IResultMap iResultMapFailed = getResults(result.getTestContext().getFailedTests());
                IResultMap iResultMapSkipped = getResults(result.getTestContext().getSkippedTests());
                for (ITestNGMethod iTestNGMethod : iResultMapFailed.getAllMethods()) {
                    for (ITestResult iTestResult : iResultMapFailed.getResults(iTestNGMethod))
                        iResultMap.addResult(iTestResult, iTestNGMethod);
                    iResultMapFailed.removeResult(iTestNGMethod);
                }
                for (ITestNGMethod iTestNGMethod : iResultMapSkipped.getAllMethods()) {
                    for (ITestResult iTestResult : iResultMapSkipped.getResults(iTestNGMethod))
                        iResultMap.addResult(iTestResult, iTestNGMethod);
                    iResultMapSkipped.removeResult(iTestNGMethod);
                }
                internalContext.put(ALL_TESTS_KEY, sortBy(iResultMap, type));
                internalContext.put(PREFIX_KEY, prefix);
                internalContext.put(SUITE_KEY, suite);
                internalContext.put(PARENT_KEY, linkToParent);
                internalContext.put(GROUP_BY_METHOD_KEY, groupByMethod);
                String fileName = String.format(prefix + "suite%d_test%d_%s", index, index2, RESULTS_FILE);
                String linkName = "Go to " + result.getTestContext().getName() + "(in " + ("g_".equals(prefix)? "group" : "suite") + " "+ suite.getName()+")";
                addParent(result.getTestContext().getFailedConfigurations(), fileName, linkName);
                addParent(iResultMap, fileName, linkName);
                generateResultsFile(new File(outputDirectory, fileName),
                        internalContext);
                log.debug("Result file for suite "+ suite.getName()+" and result "+result.getTestContext().getName()+ " is created. " + index2 +" of "+totalResults);
                ++index2;
            }
            log.debug("Results files for suite " + suite.getName() + " are created. " + index + " of "+ suites.size());
            ++index;
        }
        log.debug("Results files are created");
    }

    private IResultMap getResults(IResultMap resultMap) {
        IResultMap irm = new ResultMap();
        for (ITestResult itr : resultMap.getAllResults()) {
            irm.addResult(itr, itr.getMethod());
        }
        return irm;
    }

    private void generateResultsFile(File file, Map<String, Object> internalContext) throws Exception {
        int index = 0;
        File headerFile = File.createTempFile("hdr",""+index);
        index++;
        UTILS.clearSupplementaryData();
        generateFile(headerFile, "results_header.html.vm", new VelocityContext(internalContext));
        List<File> files = new ArrayList<>();
        files.add(headerFile);
        @SuppressWarnings("unchecked")
        SortedMap<IClass,ClassResults> failedConfigurations = (SortedMap<IClass,ClassResults>)internalContext.get(FAILED_CONFIG_KEY);
        @SuppressWarnings("unchecked")
        SortedMap<IClass,ClassResults> failed = (SortedMap<IClass,ClassResults>)internalContext.get(FAILED_TESTS_KEY);
        @SuppressWarnings("unchecked")
        SortedMap<IClass,ClassResults> skipped = (SortedMap<IClass,ClassResults>)internalContext.get(SKIPPED_TESTS_KEY);
        @SuppressWarnings("unchecked")
        SortedMap<IClass,ClassResults> all = (SortedMap<IClass,ClassResults>)internalContext.get(ALL_TESTS_KEY);
        internalContext.remove(FAILED_CONFIG_KEY);
        internalContext.remove(FAILED_TESTS_KEY);
        internalContext.remove(SKIPPED_TESTS_KEY);
        internalContext.remove(ALL_TESTS_KEY);
        if (failedConfigurations.size()>0) {
            List<SortedMap<IClass,ClassResults>> splitted = splitMap(failedConfigurations, 1);
            for (int i=0;i<splitted.size();i++) {
                File cfgFile = File.createTempFile("cfg",""+index);
                index++;
                VelocityContext context = new VelocityContext(internalContext);
                context.put(FIRST_SPLIT_KEY, i == 0);
                context.put(LAST_SPLIT_KEY, i == splitted.size() - 1);
                context.put(FAILED_CONFIG_KEY, splitted.get(i));
                generateFile(cfgFile, "results_configs.html.vm", context);
                files.add(cfgFile);
            }
        }
        if (failed.size()>0) {
            List<SortedMap<IClass,ClassResults>> splitted = splitMap(failed, 1);
            for (int i=0;i<splitted.size();i++) {
                File fldFile = File.createTempFile("fld",""+index);
                index++;
                VelocityContext context = new VelocityContext(internalContext);
                context.put(FIRST_SPLIT_KEY, i == 0);
                context.put(LAST_SPLIT_KEY, i==splitted.size()-1);
                context.put(FAILED_TESTS_KEY, splitted.get(i));
                generateFile(fldFile, "results_failed.html.vm", context);
                files.add(fldFile);
            }
        }
        if (skipped.size()>0) {
            List<SortedMap<IClass,ClassResults>> splitted = splitMap(skipped, 1);
            for (int i=0;i<splitted.size();i++) {
                File skpFile = File.createTempFile("skp",""+index);
                index++;
                VelocityContext context = new VelocityContext(internalContext);
                context.put(FIRST_SPLIT_KEY, i == 0);
                context.put(LAST_SPLIT_KEY, i == splitted.size() - 1);
                context.put(SKIPPED_TESTS_KEY, splitted.get(i));
                generateFile(skpFile, "results_skipped.html.vm", context);
                files.add(skpFile);
            }
        }
        if (all.size()>0) {
            List<SortedMap<IClass,ClassResults>> splitted = splitMap(all, 1);
            Velocity.getTemplate(TEMPLATES_PATH + "results_all.html.vm", ENCODING);
            for (int i=0;i<splitted.size();i++) {
                File allFile = File.createTempFile("all",""+index);
                index++;
                VelocityContext context = new VelocityContext(internalContext);
                context.put(FIRST_SPLIT_KEY, i == 0);
                context.put(LAST_SPLIT_KEY, i==splitted.size()-1);
                context.put(ALL_TESTS_KEY, splitted.get(i));
                context.put(ALL_TESTS_KEY+"1", new TreeMap<IClass, ClassResults>(CLASS_COMPARATOR));
                generateFile(allFile, "results_all.html.vm", context);
                files.add(allFile);
            }
        }
        File footerFile = File.createTempFile("ftr",""+index);
        generateFile(footerFile, "results_footer.html.vm", new VelocityContext(internalContext));
        files.add(footerFile);
        concatFiles(file, files);
    }

    private List<SortedMap<IClass, ClassResults>> splitMap(SortedMap<IClass, ClassResults> initial, int portionSize) {
        List<SortedMap<IClass,ClassResults>> splitted = new ArrayList<>();
        SortedMap<IClass, ClassResults> current = new TreeMap<>(CLASS_COMPARATOR);
        splitted.add(current);
        for (IClass iClass : initial.keySet()) {
            if (current.size() == portionSize) {
                current = new TreeMap<>(CLASS_COMPARATOR);
                splitted.add(current);
            }
            current.put(iClass, initial.get(iClass));
        }
        return splitted;
    }

    private void concatFiles(File file, List<File> files) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (File file1 : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                    }
                    bw.flush();
                }
                file1.delete();
            }
            bw.flush();
        }
    }

    private void addParent(IResultMap resultMap, String fileName, String linkName) {
        for(ITestResult itr: resultMap.getAllResults()) {
            @SuppressWarnings("unchecked")
            List<List<String>> parents = (List<List<String>>) itr.getAttribute(ATTRIBUTE_PARENTS);
            if (parents == null) {
                parents = new ArrayList<>();
                itr.setAttribute(ATTRIBUTE_PARENTS, parents);
            }
            parents.add(Arrays.asList(fileName, linkName));
        }
    }

    /**
     * Define name of result based on annotation TestName on method/class or based on class name
     * @param method test method to process
     * @return name of test for particular method
     */
    private String defineName(ITestNGMethod method) {
        Method javaMethod = method.getConstructorOrMethod().getMethod();
        if (javaMethod.isAnnotationPresent(TestName.class)) {
            return javaMethod.getAnnotation(TestName.class).value();
        }
        if (javaMethod.getDeclaringClass().isAnnotationPresent(TestName.class)) {
            return javaMethod.getDeclaringClass().getAnnotation(TestName.class).value();
        }
        return javaMethod.getDeclaringClass().getSimpleName();
    }

    private int prepareFileNames(ISuiteResult result, int initialValue, String linkToOverview, String linkToGroupOverview) {
        int testNumber = initialValue;

        testNumber = prepareFileNames(result.getTestContext().getFailedConfigurations().getAllResults(), testNumber, linkToOverview, linkToGroupOverview);
        testNumber = prepareFileNames(result.getTestContext().getFailedTests().getAllResults(), testNumber, linkToOverview, linkToGroupOverview);
        testNumber = prepareFileNames(result.getTestContext().getSkippedTests().getAllResults(), testNumber, linkToOverview, linkToGroupOverview);
        testNumber = prepareFileNames(result.getTestContext().getPassedTests().getAllResults(), testNumber, linkToOverview, linkToGroupOverview);
        return testNumber;
    }

    private int prepareFileNames(Set<ITestResult> allResults, int initialValue, String linkToOverview, String linkToGroupOverview) {
        int testNumber = initialValue;
        for (ITestResult testResult : allResults) {
            final String fileName = ReportNGUtils.getFileName(testResult, ++testNumber);
            final String logName = getLogName(fileName);
            testResult.setAttribute(ATTRIBUTE_FILE_NAME, fileName);
            testResult.setAttribute(ATTRIBUTE_LOG_NAME, logName);
            testResult.setAttribute(ATTRIBUTE_OVERVIEW_FILE_NAME, linkToOverview);
            testResult.setAttribute(ATTRIBUTE_GROUP_OVERVIEW_FILE_NAME, linkToGroupOverview);
        }
        return testNumber;
    }

    private void createTestResults(List<ISuite> suites, File outputDirectory) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        log.debug("Start creation of tests files");
        final File logsDir = new File(outputDirectory, "logs");
        //noinspection ResultOfMethodCallIgnored
        logsDir.mkdirs();
        for (ISuite suite : suites) {
            log.debug("Starting creation of tests files for suite " + suite.getName());
            for (ISuiteResult result : suite.getResults().values()) {
                log.debug("Starting creation of tests files for result " + result.getTestContext().getName() + " ...");
                HashMap<String, String> testsResult = new HashMap<>();
                IResultMap results = result.getTestContext().getFailedConfigurations();
                for (ITestResult testResult : results.getAllResults()) {
                    VelocityContext context = createContext();
                    long duration = testResult.getEndMillis() - testResult.getStartMillis();
                    Date date = new Date(testResult.getStartMillis());
                    context.put(RESULT_KEY, testResult);
                    context.put(STATUS_KEY, 1);
                    context.put(DURATION_KEY, duration);
                    context.put(START_KEY, sdf.format(date));
                    String fileName = testResult.getAttribute(ATTRIBUTE_FILE_NAME).toString();
                    String logName = testResult.getAttribute(ATTRIBUTE_LOG_NAME).toString();
                    testsResult.put(testResult.getName(), fileName);
                    generateFile(new File(outputDirectory, fileName), RESULTEST_FILE + TEMPLATE_EXTENSION, context);
                    generateFile(new File(logsDir, logName), LOG_FILE + TEMPLATE_EXTENSION, context);
                }

                results = result.getTestContext().getFailedTests();
                for (ITestResult testResult : results.getAllResults()) {
                    VelocityContext context = createContext();
                    long duration = testResult.getEndMillis() - testResult.getStartMillis();
                    String dependencyFile = testsResult.get(testResult.getName());
                    Date date = new Date(testResult.getStartMillis());
                    context.put(RESULT_KEY, testResult);
                    context.put(DEPEND_KEY, dependencyFile);
                    context.put(STATUS_KEY, 2);
                    context.put(DURATION_KEY, duration);
                    context.put(START_KEY, sdf.format(date));
                    String fileName = testResult.getAttribute(ATTRIBUTE_FILE_NAME).toString();
                    String logName = testResult.getAttribute(ATTRIBUTE_LOG_NAME).toString();
                    generateFile(new File(outputDirectory, fileName), RESULTEST_FILE + TEMPLATE_EXTENSION, context);
                    generateFile(new File(logsDir, logName), LOG_FILE + TEMPLATE_EXTENSION, context);
                }
                results = result.getTestContext().getSkippedTests();
                for (ITestResult testResult : results.getAllResults()) {
                    VelocityContext context = createContext();
                    long duration = testResult.getEndMillis() - testResult.getStartMillis();
                    Date date = new Date(testResult.getStartMillis());
                    context.put(RESULT_KEY, testResult);
                    context.put(STATUS_KEY, 3);
                    context.put(DURATION_KEY, duration);
                    context.put(START_KEY, sdf.format(date));
                    String fileName = testResult.getAttribute(ATTRIBUTE_FILE_NAME).toString();
                    String logName = testResult.getAttribute(ATTRIBUTE_LOG_NAME).toString();
                    generateFile(new File(outputDirectory, fileName), RESULTEST_FILE + TEMPLATE_EXTENSION, context);
                    generateFile(new File(logsDir, logName), LOG_FILE + TEMPLATE_EXTENSION, context);
                }
                results = result.getTestContext().getPassedTests();
                for (ITestResult testResult : results.getAllResults()) {
                    VelocityContext context = createContext();
                    long duration = testResult.getEndMillis() - testResult.getStartMillis();
                    Date date = new Date(testResult.getStartMillis());
                    context.put(RESULT_KEY, testResult);
                    context.put(STATUS_KEY, 4);
                    context.put(DURATION_KEY, duration);
                    context.put(START_KEY, sdf.format(date));
                    String fileName = testResult.getAttribute(ATTRIBUTE_FILE_NAME).toString();
                    String logName = testResult.getAttribute(ATTRIBUTE_LOG_NAME).toString();
                    generateFile(new File(outputDirectory, fileName), RESULTEST_FILE + TEMPLATE_EXTENSION, context);
                    generateFile(new File(logsDir, logName), LOG_FILE + TEMPLATE_EXTENSION, context);
                }
                log.debug("Tests files for result " + result.getTestContext().getName() + " are created");
            }
            log.debug("Tests files for suite " + suite.getName() + " are created");
        }
        log.debug("Tests files are created");
    }

    private String getLogName(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return (dot > 0 ? fileName.substring(0, dot + 1) : fileName) + LOG_EXTENSION;
    }

    /**
     * Group test methods by class and sort alphabetically.
     */
    private SortedMap<IClass, ClassResults> sortByTestClass(IResultMap results) {
        SortedMap<IClass, ClassResults> sortedResults = new TreeMap<>(CLASS_COMPARATOR);
        for (ITestResult result : results.getAllResults()) {
            ClassResults classResults = sortedResults.get(result.getTestClass());
            if (classResults == null) {
                classResults = new ClassResults();
                sortedResults.put(result.getTestClass(), classResults);
            }
            classResults.addResult(result);
        }
        return sortedResults;
    }


    /**
     * Group test methods by
     * @param type type of sort (class or pdv)
     */
    private SortedMap<IClass, ClassResults> sortBy(IResultMap results, String type) {
        if ("class".equalsIgnoreCase(type)) {
            return sortByTestClass(results);
        }
        SortedMap<IClass, ClassResults> sortedResults = new TreeMap<>(CLASS_COMPARATOR);
        for (ITestResult result : results.getAllResults()) {
            IClass cl = getClassFromPDV(result);
            ClassResults classResults = sortedResults.get(cl);
            if (classResults == null) {
                classResults = new ClassResults();
                sortedResults.put(cl, classResults);
            }
            classResults.addResult(result);
        }
        return sortedResults;
    }

    private IClass getClassFromPDV(ITestResult result) {
        final String name = UTILS.getPDV(result);
        if (!classes.containsKey(name)) {
            classes.put(name, new IClass() {

                @Override
                public String getName() {
                    return name;
                }

                @Override
                public XmlTest getXmlTest() {
                    return null;
                }

                @Override
                public XmlClass getXmlClass() {
                    return null;
                }

                @Override
                public String getTestName() {
                    return null;
                }

                @Override
                public Class getRealClass() {
                    return null;
                }

                @Override
                public Object[] getInstances(boolean create) {
                    return new Object[0];
                }

                @Override
                public int getInstanceCount() {
                    return 0;
                }

                @Override
                public long[] getInstanceHashCodes() {
                    return new long[0];
                }

                @Override
                public void addInstance(Object instance) {

                }
            });
        }
        return classes.get(name);
    }

    /**
     * Generate a groups list for each suite.
     *
     * @param outputDirectory The target directory for the generated file(s).
     */
    private void createGroups(List<ISuite> suites,
                              File outputDirectory) throws Exception {
        int index = 1;
        for (ISuite suite : suites) {
            SortedMap<String, SortedSet<ITestNGMethod>> groups = sortGroups(suite.getMethodsByGroups());
            if (!groups.isEmpty()) {
                VelocityContext context = createContext();
                context.put(SUITE_KEY, suite);
                context.put(GROUPS_KEY, groups);
                String fileName = String.format("suite%d_%s", index, GROUPS_FILE);
                generateFile(new File(outputDirectory, fileName),
                        GROUPS_FILE + TEMPLATE_EXTENSION,
                        context);
            }
            ++index;
        }
    }

    /**
     * Sorts groups alphabetically and also sorts methods within groups alphabetically
     * (class name first, then method name).  Also eliminates duplicate entries.
     */
    private SortedMap<String, SortedSet<ITestNGMethod>> sortGroups(Map<String, Collection<ITestNGMethod>> groups) {
        SortedMap<String, SortedSet<ITestNGMethod>> sortedGroups = new TreeMap<>();
        for (Map.Entry<String, Collection<ITestNGMethod>> entry : groups.entrySet()) {
            SortedSet<ITestNGMethod> methods = new TreeSet<>(METHOD_COMPARATOR);
            methods.addAll(entry.getValue());
            sortedGroups.put(entry.getKey(), methods);
        }
        return sortedGroups;
    }


    /**
     * Reads the CSS and JavaScript files from the JAR file and writes them to
     * the output directory.
     *
     * @param outputDirectory Where to put the resources.
     * @throws IOException If the resources can't be read or written.
     */
    private void copyResources(File outputDirectory) throws IOException {
        log.debug("Start copying resources");
        copyClasspathResource(outputDirectory, "reportng.css", "reportng.css");
        copyClasspathResource(outputDirectory, "reportng.js", "reportng.js");
        copyClasspathResource(outputDirectory, "sorttable.js", "sorttable.js");
        copyClasspathResource(outputDirectory, "jquery-1.4.3.min.js", "jquery-1.4.3.min.js");
        copyClasspathResource(outputDirectory, "jquery.fancybox-1.3.4.css", "jquery.fancybox-1.3.4.css");
        copyClasspathResource(outputDirectory, "jquery.fancybox-1.3.4.pack.js", "jquery.fancybox-1.3.4.pack.js");
        copyImage(outputDirectory, "blank.gif", "blank.gif");
        copyImage(outputDirectory, "fancybox-x.png", "fancybox-x.png");
        copyImage(outputDirectory, "fancybox-y.png", "fancybox-y.png");
        copyImage(outputDirectory, "fancy_close.png", "fancy_close.png");
        copyImage(outputDirectory, "fancy_nav_left.png", "fancy_nav_left.png");
        copyImage(outputDirectory, "fancy_nav_right.png", "fancy_nav_right.png");
        copyImage(outputDirectory, "fancybox.png", "fancybox.png");
        copyImage(outputDirectory, "timeStampOn.png", "timeStampOn.png");
        copyImage(outputDirectory, "timeStampOff.png", "timeStampOff.png");
        copyImage(outputDirectory, "errorMessageOff.png", "errorMessageOff.png");
        copyImage(outputDirectory, "errorMessageOn.png", "errorMessageOn.png");
        copyImage(outputDirectory, "info.png", "info.png");
        copyImage(outputDirectory, "log_icon.png", "log_icon.png");
        copyImage(outputDirectory, "login.png", "login.png");
        copyImage(outputDirectory, "ci-dashboard.png", "ci-dashboard.png");
        // If there is a custom stylesheet, copy that.
        File customStylesheet = META.getStylesheetPath();

        if (customStylesheet != null) {
            if (customStylesheet.exists()) {
                copyFile(outputDirectory, customStylesheet, CUSTOM_STYLE_FILE);
            } else {
                // If not found, try to read the file as a resource on the classpath
                // useful when reportng is called by a jarred up library
                InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(customStylesheet.getPath());
                if (stream != null) {
                    copyStream(outputDirectory, stream, CUSTOM_STYLE_FILE);
                }
            }
        }
        log.debug("Copying resources is finished");
    }
}
