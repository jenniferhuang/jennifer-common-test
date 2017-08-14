package org.uncommons.reportng;

import org.testng.ISuiteResult;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Yury Kryazhev
 */
public class SuiteResultMapEntryComparator implements Comparator<Map.Entry<String, ISuiteResult>> {
    @Override
    public int compare(Map.Entry<String, ISuiteResult> o1, Map.Entry<String, ISuiteResult> o2) {
        return o1.getValue().getTestContext().getStartDate()
	           .compareTo(o2.getValue().getTestContext().getStartDate());
    }
}
