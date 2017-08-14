package com.ringcentral.qa.reporter.logger.storage;

import java.util.List;

/**
 * @author Anton Gnutov
 */
public interface LogStorage {
    void initialize();

    void put(String key, String context, String message);

    List<String> get(String key);

    Iterable<String> getAll();

    void clear();

    void close();
}
