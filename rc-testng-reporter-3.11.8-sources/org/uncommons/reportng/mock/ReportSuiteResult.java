package org.uncommons.reportng.mock;

import org.testng.*;
import org.testng.internal.Configuration;
import org.testng.xml.XmlTest;

import java.util.*;

/**
 * @author alexey.ilyin
 */
public class ReportSuiteResult implements ISuiteResult {
    private final ITestContext testContext;

    public ReportSuiteResult(ISuite suite, String name) {
        XmlTest test = new XmlTest();
        test.setName(name);
        test.setSuite(suite.getXmlSuite());
        testContext = new ReportTestRunner(new Configuration(),suite,test, false, Collections.<IInvokedMethodListener>emptyList());
    }
    @Override
    public String getPropertyFileName() {
        return null;
    }

    @Override
    public ITestContext getTestContext() {
        return testContext;
    }
}
