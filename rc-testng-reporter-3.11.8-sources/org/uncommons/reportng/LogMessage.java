package org.uncommons.reportng;

/**
 * @author Anton Gnutov
 */
public final class LogMessage {
    private static final String TIMESTAMP_SEPARATOR = "~ ";
    private static final String DEFAULT_TIMESTAMP = "00:00:00.000";

    private int level;
    private char status;
    private String text;
    private String timestamp;
    boolean expandable = false;

    private LogMessage(int level, char status, String text, String timestamp) {
        this.level = level;
        this.status = status;
        this.text = text;
        this.timestamp = timestamp;
    }

    public static LogMessage valueOf(String logMessage) {
        // get level (business, component, technical)
        int level = Character.digit(logMessage.charAt(0), 10);

        // get status (passed, warning, failed)
        char status = logMessage.charAt(1);

        String text;
        String timestamp;
        int separator = logMessage.indexOf(TIMESTAMP_SEPARATOR);
        if (separator < 0) {
            timestamp = DEFAULT_TIMESTAMP;
            text = logMessage.substring(2);
        } else {
            timestamp = logMessage.substring(2, separator);
            text = logMessage.substring(separator + TIMESTAMP_SEPARATOR.length());
        }

        return new LogMessage(level, status, text, timestamp);
    }

    public static LogMessage createExpandableMessage(String message, int level) {
        return createExpandableMessage(message, level, ' ');
    }

    public static LogMessage createExpandableMessage(String message, int level, char status) {
        return createExpandableMessage(message, level, status, DEFAULT_TIMESTAMP);
    }

    public static LogMessage createExpandableMessage(String message, int level, char status, String timestamp) {
        LogMessage logMessage = new LogMessage(level, status, message, timestamp);
        logMessage.expandable = true;
        return logMessage;
    }

    public int getLevel() {
        return level;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isExpandable() {
        return expandable;
    }
}
