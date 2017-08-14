package com.ringcentral.qa.reporter.logger;

import com.ringcentral.qa.common.logging.LogMessage;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * <code>ReportLogger</code>
 *
 * @author Anton Gnutov
 */
public interface ReportLogger {
    /**
     * Logs message into logger
     * @param message message to be logged
     */
    void log(String message);

    /**
     * Answers current test result
     * @return <code>ITestResult</code> object
     */
    ITestResult getCurrentTestResult();

    /**
     * Answers UUID corresponded to current test result
     * @return <code>UUID</code> object
     */
    String getCurrentUUID();

    /**
     * Sets current test result
     * @param result current result object
     */
    void setCurrentTestResult(ITestResult result);

    /**
     * Answers list of messages assigned to current test result
     * @param result object for searching log messages
     * @return list of strings
     */
    List<String> getTestOutput(ITestResult result);

    /**
     * Answers list of messages assigned to unique identifier
     * @param uuid object for searching log messages
     * @return list of strings
     */
    List<String> getTestOutput(String uuid);

    /**
     * Answers <code>Iterable</code> object with all logged messages inside
     * @return <code>Iterable</code> object
     */
    Iterable<String> getAllOutput();

    /**
     * Answers current suite
     * @return <code>ISuite</code> object
     */
    ISuite getCurrentSuite();

    /**
     * Sets current suite
     * @param suite current suite object
     */
    void setCurrentSuite(ISuite suite);

    /**
     * Answers log messages queue
     */
    BlockingQueue<LogMessage> getQueue();

    /**
     * Answers list of messages assigned to unique identifier
     * @param uuid object for searching log messages
     * @param quick if true - do only one check for remaining for this test messages
     * @return list of strings
     */
    List<String> getTestOutput(String uuid, boolean quick);
}
