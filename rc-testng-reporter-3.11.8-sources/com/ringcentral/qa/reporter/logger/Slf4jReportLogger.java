package com.ringcentral.qa.reporter.logger;

import com.ringcentral.qa.reporter.logger.storage.LogStorage;
import com.ringcentral.qa.common.logging.LogMessage;
import com.ringcentral.qa.reporter.logger.storage.UUIDStorage;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.util.concurrent.BlockingQueue;

/**
 * @author Anton Gnutov
 */
public class Slf4jReportLogger extends AbstractReportLogger {

    public Slf4jReportLogger(LogStorage dbStorage, UUIDStorage uuidStorage) {
        super(dbStorage, uuidStorage);
    }

    public void log(String message) {
        throw new UnsupportedOperationException("This operation is implemented in appropriate log4j Appender");
    }

    public ITestResult getCurrentTestResult() {
        throw new UnsupportedOperationException();
    }

    public String getCurrentUUID() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCurrentTestResult(ITestResult result) {
        throw new UnsupportedOperationException();
    }

    public ISuite getCurrentSuite() {
        throw new UnsupportedOperationException();
    }

    public void setCurrentSuite(ISuite suite) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockingQueue<LogMessage> getQueue() {
        return queue;
    }
}
