package com.ringcentral.qa.reporter.logger;

import com.ringcentral.qa.reporter.logger.storage.H2DBStorage;
import com.ringcentral.qa.reporter.logger.storage.UUIDStorage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>ReportLoggerContainer</code> represents singleton
 *
 * @author Anton Gnutov
 */
public final class ReportLoggerContainer {
    private static final String DB_URL_PATTERN = "jdbc:h2:file:target/logs/%s-" + new SimpleDateFormat("YYYYMMdd-HHmm").format(new Date());
    private static final ReportLoggerContainer INSTANCE = new ReportLoggerContainer(DB_URL_PATTERN);

    private final ReportLogger logger;
    private final ReportLogger slf4jlogger;

    private ReportLoggerContainer(String url) {
        UUIDStorage uuidStorage = new UUIDStorage();

        logger = new LowMemoryReportLogger(new H2DBStorage(String.format(url, "reporter")), uuidStorage);
        slf4jlogger = new Slf4jReportLogger(new H2DBStorage(String.format(url, "log4j"), true), uuidStorage);
    }

    public synchronized static ReportLoggerContainer getInstance() {
        return INSTANCE;
    }

    public ReportLogger getReportLogger() {
        return logger;
    }

    public ReportLogger getSlf4jReportLogger() {
        return slf4jlogger;
    }
}
