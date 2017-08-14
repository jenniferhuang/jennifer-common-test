package com.ringcentral.qa.reporter.customgroups;

import org.testng.ITestResult;
import java.util.Set;

/**
 * @author alexey.ilyin
 */
public interface CustomGroups {
    void updateGroupsBasedOnTestResult(Set<String> groups,ITestResult iTestResult);
}
