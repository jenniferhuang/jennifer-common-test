package com.ringcentral.qa.reporter.logger.storage;

import com.ringcentral.qa.common.logging.LogMessage;

import java.util.concurrent.BlockingQueue;

/**
 * @author Anton Gnutov
 */
public class LogThread extends Thread {
    private final BlockingQueue<LogMessage> queue;
    private final LogStorage dbStorage;

    public LogThread(BlockingQueue<LogMessage> queue, LogStorage dbStorage) {
        this.queue = queue;
        this.dbStorage = dbStorage;
    }

    @Override
    public void run() {
        dbStorage.initialize();

        while (!isInterrupted()) {
            try {
                LogMessage logMessage = queue.take();
                log(logMessage);
            } catch (InterruptedException e) {
                // this should not happen, but...
            }
        }
    }

    private void log(LogMessage message) {
        dbStorage.put(message.getUuid(), message.getContext(), message.getMessage());
    }
}
