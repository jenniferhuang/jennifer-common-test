package com.ringcentral.qa.reporter.customgroups;

import com.ringcentral.qa.common.utils.StringUtils;
import org.testng.ITestResult;

import java.util.Set;

/**
 * @author alexey.ilyin
 */
public class ScenarioAndBrandCustomGroups implements CustomGroups {
    @Override
    public void updateGroupsBasedOnTestResult(Set<String> groups, ITestResult iTestResult) {
        updateGroupsInternal(groups, iTestResult, true, true);
    }

    protected void updateGroupsInternal(Set<String> groups, ITestResult iTestResult, boolean byScenario, boolean byBrand) {
        String accountType = (String)iTestResult.getAttribute("accountType");
        if (StringUtils.isNotEmpty(accountType)) {
            accountType = accountType.toLowerCase();
            String scenario = accountType.contains("(")? accountType.substring(0, accountType.indexOf("(")): accountType;
            String brand = "brand_1210";
            if (accountType.contains("(")) {
                String brandStr = accountType.substring(accountType.indexOf("(")+1);
                if (brandStr.contains("brand")) {
                    brandStr = brandStr.substring(brandStr.indexOf("brand")+6);
                    brandStr = brandStr.replaceAll("[=']"," ").replaceAll("[,]",")").trim();
                    brand = "brand_" + brandStr.substring(0,brandStr.indexOf(")"));
                }
            }
            if (byScenario) {
                groups.add(scenario);
            }
            if (byBrand) {
                groups.add(brand);
            }
        }
    }
}
