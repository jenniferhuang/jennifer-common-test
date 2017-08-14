package com.ringcentral.qa.reporter.logger.storage;

import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* <code>UUIDStorage</code>
*
* @author Anton Gnutov
*/
public class UUIDStorage {
    private Map<ITestResult, String> storage;

    public UUIDStorage() {
        this.storage = new HashMap<>();
    }

    public synchronized String get(ITestResult result) {
        String uuid = storage.get(result);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            storage.put(result, uuid);
        }
        return uuid;
    }

    public synchronized void clear() {
        storage.clear();
    }
}
