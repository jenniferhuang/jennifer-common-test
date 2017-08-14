package com.ringcentral.qa.reporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.internal.Configuration;
import org.testng.xml.XmlTest;

import java.util.Date;

/**
 * Implements custom suite results for grouping by classname during reporting
 * @author alexey.ilyin
 */
public class CustomSuiteResult implements ISuiteResult, Comparable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSuiteResult.class);
    private static final long serialVersionUID = 1;
    private ITestContext mTestContext = null;
    @Override
    public String getPropertyFileName() {
        return null;
    }

    @Override
    public ITestContext getTestContext() {
        return mTestContext;
    }

    public void createTestContext(ISuite suite, XmlTest test) {
        mTestContext = new TestRunner(new Configuration(), suite, test, false, null) {
            private Date startDate = null;
            private Date endDate = null;
            @Override
            public Date getStartDate() {
                if (startDate == null) {
                    checkDates();
                }
                return startDate;
            }

            private void checkDates() {
                long started = Long.MAX_VALUE;
                long finished = 0;
                for(ITestResult itr:getFailedTests().getAllResults()) {
                    if(itr.getStartMillis()<started) {
                        started = itr.getStartMillis();
                    }
                    if(itr.getEndMillis()>finished) {
                        finished = itr.getEndMillis();
                    }
                }
                for(ITestResult itr:getPassedTests().getAllResults()) {
                    if(itr.getStartMillis()<started) {
                        started = itr.getStartMillis();
                    }
                    if(itr.getEndMillis()>finished) {
                        finished = itr.getEndMillis();
                    }
                }
                for(ITestResult itr:getSkippedTests().getAllResults()) {
                    if(itr.getStartMillis()<started) {
                        started = itr.getStartMillis();
                    }
                    if(itr.getEndMillis()>finished) {
                        finished = itr.getEndMillis();
                    }
                }
                startDate = new Date(started);
                endDate = new Date(finished);
            }

            @Override
            public Date getEndDate() {
                if (endDate == null) {
                    checkDates();
                }
                return endDate;
            }
            @Override
            public ITestNGMethod[] getAllTestMethods() {
                return new ITestNGMethod[getFailedTests().size()+getPassedTests().size()+getSkippedTests().size()];
            }
        };
    }

    @Override
    public int compareTo(Object o) {
        int result = 0;
        try {
            CustomSuiteResult other = (CustomSuiteResult) o;
            String n1 = getTestContext().getName();
            String n2 = other.getTestContext().getName();
            result = n1.compareTo(n2);
        }
        catch(Exception ex) {
            LOGGER.debug(ex.getMessage(),ex);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(this.getClass() == o.getClass())) && ((CustomSuiteResult) o).getTestContext().getName().equals(this.getTestContext().getName());
    }

    @Override
    public int hashCode() {
        return this.getTestContext().getName().hashCode();
    }
}
