package com.ringcentral.qa.reporter;

import org.testng.SkipException;

/**
 * @author Anton Gnutov
 */
public abstract class MissedAccountException extends SkipException {
    private boolean skip = true;
    private final Long job;
    private final String scenario;
    private final String pod;

    protected MissedAccountException(String skipMessage, Throwable cause, Long job, String scenario, String pod) {
        super(skipMessage, cause);
        this.job = job;
        this.scenario = scenario;
        this.pod = pod;
    }

    @Override
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Long getJob() {
        return job;
    }

    public String getScenario() {
        return scenario;
    }

    public String getPod() {
        return pod;
    }
}
