package com.ringcentral.qa.reporter;

import org.testng.ITestResult;
import org.uncommons.reportng.TestResultComparator;

import java.util.*;

/**
 * Class with ClassResults which can be retrieved by two different ways
 * - as simple list of results
 * - as grouped by methods
 * @author Alexey Ilyin
 */
public class ClassResults {
    private static final Comparator<ITestResult> RESULT_COMPARATOR = new TestResultComparator();
    private List<ITestResult> plainList = new ArrayList<ITestResult>();
    private SortedMap<String, GroupedResult> grouped = new TreeMap<String, GroupedResult>();
    public void addResult(ITestResult result) {
        plainList.add(result);
        if (!grouped.containsKey(result.getName())) {
            grouped.put(result.getName(), new GroupedResult(result.getName()));
        }
        grouped.get(result.getName()).addResult(result);
    }

    public List<ITestResult> getPlainList() {
        Collections.sort(plainList,RESULT_COMPARATOR);
        return plainList;
    }

    public SortedMap<String, GroupedResult> getGrouped() {
        return grouped;
    }
}
