package com.ringcentral.qa.reporter.logger;

import com.ringcentral.qa.common.logging.LogMessage;
import com.ringcentral.qa.reporter.logger.storage.LogStorage;
import com.ringcentral.qa.reporter.logger.storage.LogThread;
import com.ringcentral.qa.reporter.logger.storage.UUIDStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Anton Gnutov
 */
abstract class AbstractReportLogger implements ReportLogger {
    private static final long SECOND = 1000L;
    private static final int MAX_WAIT_COUNT = 600;

    protected final BlockingQueue<LogMessage> queue;
    protected final UUIDStorage uuidStorage;
    private final LogStorage dbStorage;
    private LogThread logThread;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportLogger.class);

    AbstractReportLogger(LogStorage dbStorage, UUIDStorage uuidStorage) {
        this.queue = new LinkedBlockingQueue<>();
        this.dbStorage = dbStorage;
        this.uuidStorage = uuidStorage;

        startLogThread();
    }

    protected void startLogThread() {
        LOGGER.info("Starting logging thread...");
        logThread = new LogThread(queue, dbStorage);
        logThread.start();
    }

    protected void waitQueue() {
        int waitCount = 0;
        while (!queue.isEmpty()) {
            if (!logThread.isAlive()) {
                LOGGER.warn("Logging thread is died. Restarting...");
                startLogThread();
            }

            if (waitCount >= MAX_WAIT_COUNT) {
                LOGGER.warn("Maximum wait timeout is reached. Interrupting logging thread...");
                logThread.interrupt();
            }

            LOGGER.info("Waiting log messages queue... {} message(s) are still remaining", queue.size());
            try {
                Thread.sleep(SECOND);
            } catch (InterruptedException e) {
                //do nothing
            } finally {
                waitCount++;
            }
        }
    }

    protected void waitQueueWithUuid(String uuid) {
        int waitCount = 0;
        boolean found = true;
        while(!queue.isEmpty() && found) {
            if (!logThread.isAlive()) {
                LOGGER.warn("Logging thread is died. Restarting...");
                startLogThread();
            }

            if (waitCount >= 1) {
                LOGGER.warn("Maximum wait timeout is reached. Not all information will be available");
            }
            found = false;
            Iterator<LogMessage> iterator = queue.iterator();
            int remaining = 0;
            while(iterator.hasNext()) {
                LogMessage lm = iterator.next();
                if (uuid.equals(lm.getUuid())) {
                    found = true;
                    remaining ++;
                }
            }
            if (remaining == 0) {
                break;
            }
            LOGGER.info("Waiting log messages queue... {} message(s) are still remaining for uuid {}", remaining, uuid);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //do nothing
            } finally {
                waitCount++;
            }
        }
    }

    @Override
    public List<String> getTestOutput(ITestResult result) {
        return getTestOutput(uuidStorage.get(result), false);
    }

    @Override
    public List<String> getTestOutput(String uuid) {
        return getTestOutput(uuid, false);
    }

    @Override
    public List<String> getTestOutput(String uuid, boolean quick) {
        if (quick) {
            waitQueueWithUuid(uuid);
        } else {
            waitQueue();
        }
        return dbStorage.get(uuid);
    }

    @Override
    public Iterable<String> getAllOutput() {
        waitQueue();
        return dbStorage.getAll();
    }
}
