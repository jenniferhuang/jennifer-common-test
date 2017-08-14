//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package org.uncommons.reportng;

import com.ringcentral.qa.common.exception.RCFailure;
import com.ringcentral.qa.common.utils.CollectionUtils;
import com.ringcentral.qa.common.utils.StringUtils;
import com.ringcentral.qa.configuration.Configuration;
import com.ringcentral.qa.configuration.ConfigurationHolder;
import com.ringcentral.qa.configuration.knownfailures.FailureDescription;
import com.ringcentral.qa.configuration.knownfailures.KnownFailures;
import com.ringcentral.qa.environment.model.POD;
import com.ringcentral.qa.environment.model.POP;
import com.ringcentral.qa.environment.model.Server;
import com.ringcentral.qa.jiraclient.JiraIssue;
import com.ringcentral.qa.reporter.MissedAccountException;
import com.ringcentral.qa.reporter.logger.ReportLogger;
import com.ringcentral.qa.reporter.logger.ReportLoggerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Utility class that provides various helper methods that can be invoked
 * from a Velocity template.
 *
 * @author Daniel Dyer
 * @author QATools
 */
public class ReportNGUtils {
    private static final int LEVEL_BUSINESS = 2;

    private static final char STATUS_PASSED = '0';
    private static final char STATUS_WARNING = '1';
    private static final char STATUS_FAILED = '2';

    private static final NumberFormat DURATION_FORMAT = new DecimalFormat("#0.000");
    private static final Comparator<Map.Entry<String, ISuiteResult>> ENTRY_COMPARATOR = new SuiteResultMapEntryComparator();
    private static final String TESTLINK_URL = "http://testlink.dins.ru/linkto.php?tprojectPrefix=%s&item=testcase&id=%s";
    private static final String PLATFORM_TCM_URL = "http://platform-dev.dins.ru:9090/%s/%s?filter=%s";
    private static final String CI_DASH_LINK = "http://ci-dashboard.int.nordigy.ru/direct.xhtml?";

    private static final ReportLogger REPORT_LOGGER = ReportLoggerContainer.getInstance().getReportLogger();
    private static final ReportLogger SLF4J_LOGGER = ReportLoggerContainer.getInstance().getSlf4jReportLogger();

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportNGUtils.class);

    private String serviceSite;

    public ReportNGUtils() {
        final Configuration configuration = ConfigurationHolder.getInstance().getConfiguration();
        final String swr = configuration.getProperty("env:envSWR");
        serviceSite = StringUtils.isNotEmpty(swr) ? swr : configuration.getProperty("env:server(SWS)@address");
    }

    /**
     * Returns the aggregate of the elapsed times for each test result.
     *
     * @param context The test results.
     * @return The sum of the test durations.
     */
    public long getDuration(ITestContext context) {
        long duration = getDuration(context.getPassedConfigurations().getAllResults());
        duration += getDuration(context.getPassedTests().getAllResults());
        // You would expect skipped tests to have durations of zero, but apparently not.
        duration += getDuration(context.getSkippedConfigurations().getAllResults());
        duration += getDuration(context.getSkippedTests().getAllResults());
        duration += getDuration(context.getFailedConfigurations().getAllResults());
        duration += getDuration(context.getFailedTests().getAllResults());
        return duration;
    }


    /**
     * Returns the aggregate of the elapsed times for each test result.
     *
     * @param results A set of test results.
     * @return The sum of the test durations.
     */
    private long getDuration(Set<ITestResult> results) {
        long duration = 0;
        for (ITestResult result : results) {
            duration += (result.getEndMillis() - result.getStartMillis());
        }
        return duration;
    }

    public String formatDuration(long startMillis, long endMillis) {
        long elapsed = endMillis - startMillis;
        return formatDuration(elapsed);
    }

    public String formatDuration(long elapsed) {
        double seconds = (double) elapsed / 1000;
        return DURATION_FORMAT.format(seconds);
    }

    /**
     * Convert a Throwable into a list containing all of its causes.
     *
     * @param t The throwable for which the causes are to be returned.
     * @return A (possibly empty) list of {@link Throwable}s.
     */
    public List<Throwable> getCauses(Throwable t) {
        List<Throwable> causes = new LinkedList<>();
        Throwable next = t;
        while (next.getCause() != null) {
            next = next.getCause();
            causes.add(next);
        }
        return causes;
    }

    /**
     * Retrieves all log messages associated with a particular test result.
     *
     * @param result Which test result to look-up.
     * @return A list of log messages.
     */
    public List<String> getTestOutput(ITestResult result) {
        return REPORT_LOGGER.getTestOutput(result);
    }

    /**
     * Retrieves all slf4j log messages associated with a particular test result.
     *
     * @param result Which test result to look-up.
     * @return A list of log messages.
     */
    public List<String> getSlf4jTestOutput(ITestResult result) {
        final String uuid = getAttribute(result, "test_unique_id");
        if (StringUtils.isNotEmpty(uuid)) {
            return SLF4J_LOGGER.getTestOutput(uuid);
        } else {
            return SLF4J_LOGGER.getTestOutput(result);
        }
    }

    /**
     * Retrieves the output from all calls to {@link ReportLogger#log(String)}
     * across all tests.
     *
     * @return A (possibly empty) iterator of log messages.
     */
    public Iterable<String> getAllOutput() {
        return REPORT_LOGGER.getAllOutput();
    }

    public boolean hasArguments(ITestResult result) {
        return result.getParameters().length > 0;
    }

    public String getArguments(ITestResult result) {
        Object[] arguments = result.getParameters();
        List<String> argumentStrings = new ArrayList<>();

        for (Object argument : arguments) {
            argumentStrings.add(StringUtils.safeToString(argument));
        }
        return commaSeparate(argumentStrings);
    }

    public String getTestParameters(ITestResult result) {
        final Object parameters = result.getAttribute("testParameters");
        return parameters != null ? parameters.toString() : "";
    }


    public List<String> getTestParametersAsArray(ITestResult result) {
        final Object parameters = result.getAttribute("testParameters");
        String str = parameters != null ? parameters.toString() : "";
        return Arrays.asList(str.split("####"));
    }

    /**
     * Decorate the string representation of an argument to give some
     * hint as to its type (e.g. render Strings in double quotes).
     *
     * @param argument The argument to render.
     * @return The string representation of the argument.
     */
    private String renderArgument(Object argument) {
        if (argument == null) {
            return "null";
        } else if (argument instanceof String) {
            return "\"" + argument + "\"";
        } else if (argument instanceof Character) {
            return "\'" + argument + "\'";
        } else if (argument instanceof String[]) {
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            int length = ((String[]) argument).length;
            for (int i = 0; i < length; i++) {
                builder.append("\"").append(((String[]) argument)[i]).append("\"");
                if (i < length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("}");
            return builder.toString();
        } else {
            return argument.toString();
        }
    }


    /**
     * @param result The test result to be checked for dependent groups.
     * @return True if this test was dependent on any groups, false otherwise.
     */
    public boolean hasDependentGroups(ITestResult result) {
        return result.getMethod().getGroupsDependedUpon().length > 0;
    }

    public int getExpectedFailuresCount(ITestContext context) {
        return getExpectedFailuresCount(context.getFailedTests());
    }

    private int getExpectedFailuresCount(IResultMap results) {
        int result = 0;
        for (ITestResult testResult : results.getAllResults()) {
            if (isKnownFailure(testResult)) {
                result++;
            }
        }
        return result;
    }

    public int getFewAccountsCount(ITestContext context) {
        return getFewAccountsCount(context.getSkippedTests());
    }

    private int getFewAccountsCount(IResultMap results) {
        int result = 0;
        for (ITestResult testResult : results.getAllResults()) {
            if (isSkippedAccount(testResult.getThrowable())) {
                   result++;
            }
        }
        return result;
    }

    public static boolean isSkippedAccount(Throwable th) {
        return th instanceof MissedAccountException;
    }

    public static String getUrl(ITestResult testResult) {
        String branch = getBranch(testResult);
        String testCaseId = getAttribute(testResult, "testCaseId");
        if (StringUtils.isNotEmpty(branch)) {
            return "<span class=\"testCaseId\"><a target=\"_blank\" href=\""+formatPlatformCase(testResult)+"\">"+testCaseId+"</a>&nbsp;</span>";
        } else if (StringUtils.isNotEmpty(testCaseId)) {
            return "<span class=\"testCaseId\"><a target=\"_blank\" href=\""+formatTestLinkCase(testCaseId)+"\">"+testCaseId+"</a>&nbsp;</span>";
        } else {
            return "";
        }
    }

    private static String getBranch(ITestResult testResult) {
        String branch = getAttribute(testResult, "tcmBranch");
        if (StringUtils.isEmpty(branch)) {
            branch = getAttribute(testResult, "platformBranch");
        }
        return branch;
    }

    private static String formatTestLinkCase(String id) {
        String key = "50X";
        if (id.contains("-")) {
            key = id.split("-")[0];
        }
        return String.format(TESTLINK_URL, key, id);
    }

    private static String formatPlatformCase(ITestResult testResult) {
        String branch = getBranch(testResult);
        String project = (String)testResult.getAttribute("tcmProject");
        if (StringUtils.isEmpty(project)) {
            project = "pas";
        }
        Method method = testResult.getMethod().getConstructorOrMethod().getMethod();
        return String.format(PLATFORM_TCM_URL, project, branch, method.getDeclaringClass().getCanonicalName()+"."+method.getName());
    }

    public static String getAttribute(ITestResult result, String key) {
        Object attribute = result.getAttribute(key);
        if (attribute == null) {
            return "";
        }
        return attribute.toString();
    }

    public String[] getAttributeArray(ITestResult result, String key) {
        String[] attribute = (String[]) result.getAttribute(key);
        if (attribute != null && attribute.length > 0) {
            return attribute;
        }
        return new String[]{};
    }

    public int getStringArrayLength(String[] array) {
        if (array != null) {
            return array.length;
        }
        return 0;
    }

    public static boolean isNotNull(String value) {
        return StringUtils.isNotEmpty(value);
    }

    public int getTotalTestMethods(ITestContext context) {
        return context.getAllTestMethods().length;
    }

    public static String getFileName(ITestResult testResult, int testNumber) {
        String name = (String) testResult.getAttribute("name");
        String fileName;
        if (isNotNull(name)) {
            fileName = String.format("test_%s%s_%04d.html", prepareValidFilename(testResult.getName()), name, testNumber);
        } else {
            fileName = String.format("test_%s_%04d.html", prepareValidFilename(testResult.getName()), testNumber);
        }
        return fileName;
    }

    private static final String INVALID_CHARACTER_REGEX = "[^A-Za-z0-9_.\\s-]";
    private static final int MAX_FILENAME_LENGTH = 240;

    private static String prepareValidFilename(String s) {
        String replaced = s.replaceAll(INVALID_CHARACTER_REGEX, "_");
        return replaced.length() >= MAX_FILENAME_LENGTH ?
                replaced.substring(0, MAX_FILENAME_LENGTH) :
                replaced;
    }

    /**
     * @return A comma-separated string listing all dependent groups.  Returns an
     *         empty string it there are no dependent groups.
     */
    public String getDependentGroups(ITestResult result) {
        String[] groups = result.getMethod().getGroupsDependedUpon();
        return commaSeparate(Arrays.asList(groups));
    }

    /**
     * @param result The test result to be checked for dependent methods.
     * @return True if this test was dependent on any methods, false otherwise.
     */
    public boolean hasDependentMethods(ITestResult result) {
        return result.getMethod().getMethodsDependedUpon().length > 0;
    }

    /**
     * @return A comma-separated string listing all dependent methods.  Returns an
     *         empty string it there are no dependent methods.
     */
    public String getDependentMethods(ITestResult result) {
        String[] methods = result.getMethod().getMethodsDependedUpon();
        return commaSeparate(Arrays.asList(methods));
    }

    public boolean hasGroups(ISuite suite) {
        return !suite.getMethodsByGroups().isEmpty();
    }

    /**
     * Takes a list of Strings and combines them into a single comma-separated
     * String.
     *
     * @param strings The Strings to combine.
     * @return The combined, comma-separated, String.
     */
    private String commaSeparate(Collection<String> strings) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            buffer.append(string);
            if (iterator.hasNext()) {
                buffer.append(", ");
            }
        }
        return buffer.toString();
    }

    /**
     * Replace any angle brackets, quotes, apostrophes or ampersands with the
     * corresponding XML/HTML entities to avoid problems displaying the String in
     * an XML document.  Assumes that the String does not already contain any
     * entities (otherwise the ampersands will be escaped again).
     *
     * @param s The String to escape.
     * @return The escaped String.
     */
    public String escapeString(String s) {
        return StringUtils.escapeString(s);
    }

    /**
     * Works like {@link #escapeString(String)} but also replaces line breaks with
     * &lt;br /&gt; tags and preserves significant whitespace.
     *
     * @param s The String to escape.
     * @return The escaped String.
     */
    public String escapeHTMLString(String s) {
        return StringUtils.escapeHTMLString(s);
    }

    public String removeBrs(String s) {
        if (s == null) {
            return null;
        }
        return s.replaceAll("<br>", "");
    }

    /**
     * TestNG returns a compound thread ID that includes the thread name and its numeric ID,
     * separated by an 'at' sign.  We only want to use the thread name as the ID is mostly
     * unimportant and it takes up too much space in the generated report.
     *
     * @param threadId The compound thread ID.
     * @return The thread name.
     */
    public String stripThreadName(String threadId) {
        if (threadId == null) {
            return null;
        } else {
            int index = threadId.lastIndexOf('@');
            return index >= 0 ? threadId.substring(0, index) : threadId;
        }
    }

    /**
     * Find the earliest start time of the specified methods.
     *
     * @param methods A list of test methods.
     * @return The earliest start time.
     */
    public long getStartTime(List<ITestNGMethod> methods) {
        long startTime = System.currentTimeMillis();
        for (ITestNGMethod method : methods) {
            startTime = Math.min(startTime, method.getDate());
        }
        return startTime;
    }

    public String convertDate(Object date) {
        if (date == null) {
            return "00:00:00.000";
        } else if (date instanceof String) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(Long.valueOf((String)date));
            date = calendar.getTime();
        }
        return new SimpleDateFormat("HH:mm:ss.SSS").format(date);
    }

    public long getEndTime(ISuite suite, ITestNGMethod method, List<ITestNGMethod> methods) {
        boolean found = false;
        for (ITestNGMethod m : methods) {
            if (m == method) {
                found = true;
            }
            // Once a method is found, find subsequent method on same thread.
            else if (found && m.getId().equals(method.getId())) {
                return m.getDate();
            }
        }
        return getEndTime(suite, method);
    }

    /**
     * Returns the timestamp for the time at which the suite finished executing.
     * This is determined by finding the latest end time for each of the individual
     * tests in the suite.
     *
     * @param suite The suite to find the end time of.
     * @return The end time (as a number of milliseconds since 00:00 1st January 1970 UTC).
     */
    private long getEndTime(ISuite suite, ITestNGMethod method) {
        // Find the latest end time for all tests in the suite.
        for (Map.Entry<String, ISuiteResult> entry : suite.getResults().entrySet()) {
            ITestContext testContext = entry.getValue().getTestContext();
            for (ITestNGMethod m : testContext.getAllTestMethods()) {
                if (method == m) {
                    return testContext.getEndDate().getTime();
                }
            }
            // If we can't find a matching test method it must be a configuration method.
            for (ITestNGMethod m : testContext.getPassedConfigurations().getAllMethods()) {
                if (method == m) {
                    return testContext.getEndDate().getTime();
                }
            }
            for (ITestNGMethod m : testContext.getFailedConfigurations().getAllMethods()) {
                if (method == m) {
                    return testContext.getEndDate().getTime();
                }
            }
        }
        throw new IllegalStateException("Could not find matching end time.");
    }

    private boolean isCorrectStatus(char status) {
        return status == STATUS_PASSED || status == STATUS_WARNING || status == STATUS_FAILED;
    }

    public List<LogMessage> getRefactoredTestOutput(ITestResult result) {
        List<String> tmp = REPORT_LOGGER.getTestOutput(result);
        if (result.getAttribute("otherResults") != null) {
            List<ITestResult> other = (List<ITestResult>) result.getAttribute("otherResults");
            for (ITestResult itr:other) {
                tmp.addAll(REPORT_LOGGER.getTestOutput(itr));
            }
        }
        List<LogMessage> tmp2 = new ArrayList<>();
        for (int i = tmp.size() - 1; i >= 0; i--) {
            LogMessage message = LogMessage.valueOf(tmp.get(i));
            tmp2.add(message);
        }
        if (result.getAttribute("otherResults") != null) {
            tmp2.sort(new Comparator<LogMessage>() {
                @Override
                public int compare(LogMessage o1, LogMessage o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
        }
        List<LogMessage> res = new ArrayList<>();

        int level = 100;
        int status_level = 100;
        char status = '*';

        for (int i = 0; i < tmp2.size(); i++) {
            LogMessage logMessage = tmp2.get(i);

            // get level (business, component, technical)
            int currentLevel = logMessage.getLevel();
            // get status (passed, warning, failed)
            char currentStatus = logMessage.getStatus();

            if (isCorrectStatus(status) && currentLevel > status_level && currentStatus != STATUS_FAILED) {
                logMessage.setStatus(status);
                status_level++;
            }

            if (isCorrectStatus(currentStatus) && status != STATUS_FAILED) {
                status = currentStatus;
                status_level = currentLevel;
            }
            if (currentLevel == LEVEL_BUSINESS) {
                status = '*';
            }

            if (currentLevel > level) {
                if (currentLevel > level + 1) {
                    res.add(0, LogMessage.createExpandableMessage("Lower level information", currentLevel - 1));
                }
                res.add(0, LogMessage.createExpandableMessage(logMessage.getText(), currentLevel, logMessage.getStatus(), logMessage.getTimestamp()));
            } else {
                res.add(0, logMessage);
            }

            level = currentLevel;

        }
        return res;
    }

    public boolean isScreenshot(String s) {
        return s.contains("png") || s.contains("jpg") || s.contains("jpeg");
    }

    public ArrayList<String> getScreenshotInfo(String s) {
        ArrayList<String> result = new ArrayList<>();
        int separator = s.indexOf('|');
        if (separator == -1) {
            result.add(s);
            result.add(s);
        } else {
            String path = s.substring(0, separator);
            String info = s.substring(separator + 1, s.length());
            if (path.contains("_scrolled")) {
                result.add(path.replace("_scrolled", ""));
            }
            result.add(path);
            result.add(info);
        }
        return result;
    }

    public String removeDoubleQuotes(String s) {
        return s.replaceAll("\"", "'");
    }

    public static Map<String, ISuiteResult> sortByComparator(Map<String, ISuiteResult> map) {
        List<Map.Entry<String, ISuiteResult>> list = new ArrayList<>(map.entrySet());

        //sort list based on comparator
        Collections.sort(list, ENTRY_COMPARATOR);

        //put sorted list into map again
        Map<String, ISuiteResult> result = new LinkedHashMap<>();
        for (Map.Entry<String, ISuiteResult> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public String removeMainPackageName(String fullName) {
        return fullName.replace("com.ringcentral.qa.uitests.mobileweb.tests.", "").replace("com.ringcentral.qa.uitests.servicesite.tests.", "");
    }

    private static KnownFailures getKnownFailures() {
        return ConfigurationHolder.getInstance().getConfiguration().getKnownFailures();
    }

    public static boolean isKnownFailure(ITestResult result) {
        // check defined issues
        if (isKnownIssue(result)) {
            return true;
        }

        // common functionality
        KnownFailures knownFailures = getKnownFailures();
        String className = result.getTestClass().getName();
        String testName = result.getMethod().getMethodName();

        FailureDescription failure = knownFailures.getFailure(className, testName);
        if (failure != null) {
            try {
                return matchLocalFailure(result, failure);
            } catch (PatternSyntaxException e) {
                logIncorrectPattern(e.getMessage());
                return false;
            }
        } else {
            // check global failures
            return matchGlobalFailures(result);
        }
    }

    public Collection<String> getIssueKeys(ITestResult result) {
        // defined issues
        Set<String> issues = new HashSet<>(getDefinedIssues(result));

        // global
        issues.addAll(getMatchingGlobalIssues(result));

        // common
        String className = result.getTestClass().getName();
        String testName = result.getMethod().getMethodName();

        FailureDescription failure = getKnownFailures().getFailure(className, testName);
        if (failure != null) {
            issues.addAll(failure.getIssues());
        }
        return issues;
    }

    public Collection<JiraIssue> getAttachedIssues(ITestResult result) {
        Set<JiraIssue> jiraIssues = new HashSet<>();
        final String[] tickets = getAttribute(result, "jiraTicket").split(",");
        jiraIssues.addAll(HTMLReporter.JIRA_CONNECTOR.getIssues(Arrays.asList(tickets)));

        return jiraIssues;
    }

    public Collection<JiraIssue> getIssues(ITestResult result) {
        // defined issues
        Set<String> issues = new HashSet<>(getDefinedIssues(result));
        Set<JiraIssue> jiraIssues = new HashSet<>();
        // global
        issues.addAll(getMatchingGlobalIssues(result));

        // common
        String className = result.getTestClass().getName();
        String testName = result.getMethod().getMethodName();

        FailureDescription failure = getKnownFailures().getFailure(className, testName);
        if (failure != null) {
            issues.addAll(failure.getIssues());
        }
        Map<String, JiraIssue> map = HTMLReporter.JIRA_CONNECTOR.getKnownFailures();
        for(String issue: issues) {
            jiraIssues.add(map.get(issue));
        }
        return jiraIssues;
    }

    private Collection<String> getDefinedIssues(ITestResult result) {
        return Arrays.asList(getAttributeArray(result, "bug"));
    }

    private static boolean isKnownIssue(ITestResult result) {
        return result.getAttribute("bug") != null;
    }

    private static boolean matchLocalFailure(ITestResult result, FailureDescription failure) {
        FailedInfo failedInfo = extractFailedInfo(result);
        // check message matching
        if (failure.getMessage() != null) {
            Pattern pattern = Pattern.compile(failure.getMessage());
            for (String failedMessage : failedInfo.failedMessages) {
                // message should match at least one regexp, local or global
                if (!pattern.matcher(failedMessage).matches() && !matchGlobalFailure(failedMessage)) {
                    return false;
                }
            }

            // check stacktrace for match
            if (!failedInfo.stacktrace.isEmpty()) {
                boolean stackTraceMatches = false;
                for (int i = 0; i < failedInfo.stacktrace.size() && !stackTraceMatches; i++) {
                    String stackTraceElement = failedInfo.stacktrace.get(i);
                    stackTraceMatches = pattern.matcher(stackTraceElement).matches() || matchGlobalFailure(stackTraceElement);
                }
                if (!stackTraceMatches) {
                    return false;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(failure.getInvocationNumbers())) {
            return failure.getInvocationNumbers().contains(result.getAttribute("current_param_index"));
        }
        // check assert numbers matching
        return CollectionUtils.isEmpty(failure.getAssertNumbers()) || failure.getAssertNumbers().equals(failedInfo.failedAsserts);
    }

    private static boolean matchGlobalFailures(ITestResult result) {
        if (!getKnownFailures().getGlobalFailures().isEmpty()) {
            FailedInfo failedInfo = extractFailedInfo(result);

            if (!failedInfo.failedMessages.isEmpty()) {
                // all messages should match at least one global regexp
                for (String failedMessage : failedInfo.failedMessages) {
                    if (!matchGlobalFailure(failedMessage)) {
                        return false;
                    }
                }
                return true;
            }

            if (!failedInfo.stacktrace.isEmpty()) {
                // if any stacktrace element matches
                for (String stackTraceElement : failedInfo.stacktrace) {
                    if (matchGlobalFailure(stackTraceElement)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean matchGlobalFailure(String failedMessage) {
        List<FailureDescription> globalFailures = getKnownFailures().getGlobalFailures();
        for (FailureDescription failure : globalFailures) {
            try {
                if (failedMessage.matches(failure.getMessage())) {
                    return true;
                }
            } catch (PatternSyntaxException e) {
                logIncorrectPattern(e.getMessage());
            }
        }

        return false;
    }

    private Collection<String> getMatchingGlobalIssues(ITestResult result) {
        Set<String> issues = new HashSet<>();
        List<FailureDescription> globalFailures = getKnownFailures().getGlobalFailures();

        if (!globalFailures.isEmpty()) {
            FailedInfo failedInfo = extractFailedInfo(result);

            for (String failedMessage : failedInfo.failedMessages) {
                for (FailureDescription failure : globalFailures) {
                    try {
                        if (failedMessage.matches(failure.getMessage())) {
                            issues.addAll(failure.getIssues());
                        }
                    } catch (PatternSyntaxException e) {
                        logIncorrectPattern(e.getMessage());
                    }
                }
            }

            for (String stackTraceElement: failedInfo.stacktrace) {
                for (FailureDescription failure : globalFailures) {
                    try {
                        if (stackTraceElement.matches(failure.getMessage())) {
                            issues.addAll(failure.getIssues());
                        }
                    } catch (PatternSyntaxException e) {
                        logIncorrectPattern(e.getMessage());
                    }
                }
            }
        }

        return issues;
    }

    private static FailedInfo extractFailedInfo(ITestResult result) {
        List<Integer> failedAsserts = new ArrayList<>();
        List<String> failedMessages = new ArrayList<>();

        int assertNum = 1;
        List<String> logs = REPORT_LOGGER.getTestOutput(result);
        for (String log: logs) {
            char status = log.charAt(1);
            if (status == STATUS_FAILED) {
                failedMessages.add(LogMessage.valueOf(log).getText());
                failedAsserts.add(assertNum++);
            } else if (status == STATUS_PASSED) {
                assertNum++;
            }
        }

        // add exception and its causes with stacktrace to failed messages
        Throwable throwable = result.getThrowable();
        List<String> stackTrace = new ArrayList<>();
        if (throwable != null) {
            stackTrace = extractFullTrace(throwable);
        }

        return new FailedInfo(failedMessages, failedAsserts, stackTrace);
    }

    private static List<String> extractFullTrace(Throwable th) {
        List<String> trace = new ArrayList<>();
        trace.add(th.toString());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            trace.add(stackTraceElement.toString());
        }

        if (th.getCause() != null) {
            trace.addAll(extractFullTrace(th.getCause()));
        }

        return trace;
    }

    private static final class FailedInfo {
        private List<String> failedMessages;
        private List<Integer> failedAsserts;
        private List<String> stacktrace;

        private FailedInfo(List<String> failedMessages, List<Integer> failedAsserts, List<String> stacktrace) {
            this.failedMessages = failedMessages;
            this.failedAsserts = failedAsserts;
            this.stacktrace = stacktrace;
        }
    }

    private static void logIncorrectPattern(String message) {
        LOGGER.warn("Incorrect pattern syntax: {}", message);
    }

    public boolean hasPDV(ITestResult testResult) {
        return testResult.getAttribute("pdvServerType") != null;
    }

    public String getPDV(ITestResult testResult) {
        return testResult.getAttribute("pdvServerType") + ": \"" + testResult.getAttribute("pdvServerAddress") + "\"";
    }

    public boolean hasConfType(ITestResult testResult) {
        return testResult.getAttribute("conf_method_type") != null;
    }

    public String getConfType(ITestResult testResult) {
        return "(" + testResult.getAttribute("conf_method_type") + ")";
    }

    public int getFailedRetries(ITestResult testResult) {
        if (testResult.getAttribute("failedRetriesMade") == null) {
            return 0;
        } else {
            return (Integer)testResult.getAttribute("failedRetriesMade");
        }
    }

    public int getSkippedRetries(ITestResult testResult) {
        if (testResult.getAttribute("missAccountsRetriesMade") == null) {
            return 0;
        } else {
            return (Integer)testResult.getAttribute("missAccountsRetriesMade");
        }
    }

    public int getKnownRetries(ITestResult testResult) {
        if (testResult.getAttribute("knownRetriesMade") == null) {
            return 0;
        } else {
            return (Integer)testResult.getAttribute("knownRetriesMade");
        }
    }

    public Object getFirstAccount(ITestResult testResult) {
        return getAccountByIndex(testResult, 0);
    }

    public Object getAccountByIndex(ITestResult testResult, int index) {
        final List<Object> accountList = (List<Object>) testResult.getAttribute("accountList");
        if (accountList == null || accountList.size() <= index) {
            return null;
        } else {
            return accountList.get(index);
        }
    }

    public String getServiceSite() {
        return serviceSite;
    }

    public int getCurrentAccIndex() {
        return currentAccIndex;
    }

    int currentAccIndex = 0;

    public int getOverallIterator() {
        return overallIterator;
    }

    public void setOverallIterator(int overallIterator) {
        this.overallIterator = overallIterator;
    }

    int overallIterator = 0;

    public void clearSupplementaryData() {
        currentAccIndex = 0;
        overallIterator = 0;
    }
    public void setCurrentAccIndex(int accIndex) {
        currentAccIndex = accIndex;
    }

    public Throwable getThrowable(ITestResult testResult) {
        if (testResult != null && testResult.getThrowable() != null && !(testResult.getThrowable() instanceof RCFailure)) {
            return testResult.getThrowable();
        }
        return null;
    }

    public int getRowSpanSrv(Server srv) {
        return srv.getVersions().size()+1;
    }

    public int getRowSpanPod(POD pod) {
        int res = 1;
        for (Server srv:pod.getServers()) {
            res += getRowSpanSrv(srv);
        }
        return res;
    }

    public int getRowSpanPop(POP pop) {
        int res = 1;
        for (POD pod:pop.getPODs()) {
            res += getRowSpanPod(pod);
        }
        return res;
    }

    public String encodePassword(String password) {
        if (password == null) {
             return "";
        }
        try {
            return URLEncoder.encode(password, "utf8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public String getDashLink() {
        return CI_DASH_LINK;
    }

    public boolean dashboardEnabled(ITestResult itr) {
        return itr.getTestContext().getSuite().getAttribute("run")!=null;
    }
}
