package com.ringcentral.qa.reporter;

import org.testng.ITestResult;
import org.uncommons.reportng.ReportNGUtils;
import org.uncommons.reportng.TestResultComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Alexey Ilyin
 */
public class GroupedResult {
    private static final Comparator<ITestResult> RESULT_COMPARATOR = new TestResultComparator();
    private String methodName = "";
    private int totalCount = 0;
    private int failed = 0;
    private int passed = 0;
    private int skipped = 0;
    private int missedAccounts = 0;
    private int knownFailures = 0;
    private List<ITestResult> results = new ArrayList<ITestResult>();

    public GroupedResult(String name) {
        this.methodName = name;
    }

    public void addResult(ITestResult result) {
        totalCount ++;
        results.add(result);
        switch(result.getStatus()) {
            case ITestResult.SUCCESS:
                passed++;
                break;
            case ITestResult.FAILURE:case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                if (ReportNGUtils.isKnownFailure(result)) {
                    knownFailures ++;
                } else {
                    failed ++;
                }
                break;
            case ITestResult.SKIP:
                if (ReportNGUtils.isSkippedAccount(result.getThrowable())) {
                    missedAccounts++;
                } else {
                    skipped++;
                }
                break;
            default:
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getStatus() {
        if (passed == totalCount) {
            return "Passed";
        }
        if (failed == totalCount) {
            return "Failed";
        }
        if (missedAccounts == totalCount) {
            return "Missing accounts";
        }
        if (knownFailures == totalCount) {
            return "Expected";
        }
        if (skipped == totalCount) {
            return "Skipped";
        }
        return "Unstable";
    }

    public String getStatusStyle() {
        if (passed == totalCount) {
            return "passed";
        }
        if (failed == totalCount) {
            return "failed";
        }
        if (missedAccounts == totalCount) {
            return "skipped";
        }
        if (skipped == totalCount) {
            return "skipped";
        }
        if (knownFailures == totalCount) {
            return "expectedFailed";
        }
        return "unstable";
    }

    public String getDescription() {
        StringBuilder result = new StringBuilder("<i>Total invocations:</i> ").append(totalCount);
        if (passed > 0 && totalCount > 0) {
            BigDecimal passRate = BigDecimal.valueOf(((double)passed*100)/totalCount);
            passRate = passRate.setScale(2, BigDecimal.ROUND_FLOOR);
            result.append(" <i>Pass rate:</i> ").append(passRate.toString()).append("%");
        }
        if (failed >0 && totalCount > 0) {
            result.append(", <b>Failed:</b> ").append(failed);
        }
        if (missedAccounts > 0  && totalCount > 0) {
            result.append(", <i>Missing accounts:</i> ").append(missedAccounts);
        }
        if (skipped  > 0  && totalCount > 0) {
            result.append(", <i>Skipped:</i> ").append(skipped);
        }
        if (knownFailures  > 0  && totalCount > 0) {
            result.append(", <b>Expected:</b> ").append(knownFailures);
        }
        return result.toString();
    }

    public List<ITestResult> getResults() {
        Collections.sort(results, RESULT_COMPARATOR);
        return results;
    }

}
