package org.uncommons.reportng.mock;

import org.testng.*;
import org.testng.internal.IConfiguration;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author alexey.ilyin
 */
public class ReportTestRunner extends TestRunner {
    private Date startDate;
    private Date endDate;
    private List<ITestNGMethod> allMethodsList = new ArrayList<>();
    private ITestNGMethod[] allMethods = null;

    public ReportTestRunner(IConfiguration configuration, ISuite suite, XmlTest test, boolean skipFailedInvocationCounts, List<IInvokedMethodListener> listeners) {
        super(configuration, suite, test, skipFailedInvocationCounts, listeners);
    }

    @Override
    public Date getStartDate() {
        if (startDate == null) {
            collectData();
        }
        return startDate;
    }

    private void collectData() {
        updateResults(getPassedTests().getAllResults());
        updateResults(getSkippedTests().getAllResults());
        updateResults(getFailedTests().getAllResults());
        updateResults(getFailedConfigurations().getAllResults());
        allMethods = allMethodsList.toArray(new ITestNGMethod[allMethodsList.size()]);
    }

    private void updateResults(Set<ITestResult> allResults) {
        for(ITestResult itr: allResults) {
            if (startDate == null || new Date(itr.getStartMillis()).before(startDate)) {
                startDate = new Date(itr.getStartMillis());
            }
            if (endDate == null || new Date(itr.getEndMillis()).after(endDate)) {
                endDate = new Date(itr.getEndMillis());
            }
            if (!allMethodsList.contains(itr.getMethod())) {
                allMethodsList.add(itr.getMethod());
            }
        }
    }

    @Override
    public Date getEndDate() {
        if (endDate == null) {
            collectData();
        }
        return endDate;
    }

    @Override
    public ITestNGMethod[] getAllTestMethods() {
        if (allMethods == null) {
            collectData();
        }
        return allMethods;
    }
}
