package com.ringcentral.qa.reporter.logger;

import com.ringcentral.qa.reporter.logger.storage.LogStorage;
import com.ringcentral.qa.common.logging.LogMessage;
import com.ringcentral.qa.reporter.logger.storage.UUIDStorage;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * <code>LowMemoryReportLogger</code>
 *
 * @author Anton Gnutov
 */
public class LowMemoryReportLogger extends AbstractReportLogger {

    /**
     * when tests are run in parallel, each thread may be working with different
     * 'current test result'. Also, this value should be inherited if the test code
     * spawns its own thread.
     */
    private ThreadLocal<ITestResult> currentTestResult = new InheritableThreadLocal<>();

    /**
     * when tests are run in parallel, each thread may be working with different
     * 'suite'.
     */
    private ThreadLocal<ISuite> currentSuite = new InheritableThreadLocal<>();

    public LowMemoryReportLogger(LogStorage dbStorage, UUIDStorage uuidStorage) {
        super(dbStorage, uuidStorage);
    }

    @Override
    public void log(String message) {
        log(message, getCurrentTestResult());
    }

    private void log(String message, ITestResult result) {
        try {
            queue.put(new LogMessage(uuidStorage.get(result), null, message));
        } catch (InterruptedException e) {
            // this should not happen
        }
    }

    @Override
    public ITestResult getCurrentTestResult() {
        return currentTestResult.get();
    }

    @Override
    public String getCurrentUUID() {
        return uuidStorage.get(getCurrentTestResult());
    }

    @Override
    public void setCurrentTestResult(ITestResult result) {
        currentTestResult.set(result);
    }

    @Override
    public ISuite getCurrentSuite() {
        return currentSuite.get();
    }

    @Override
    public void setCurrentSuite(ISuite suite) {
        currentSuite.set(suite);
    }

    @Override
    public BlockingQueue<LogMessage> getQueue() {
        throw new UnsupportedOperationException();
    }

}
