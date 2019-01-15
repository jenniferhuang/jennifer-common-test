package com.jennifer.test;

/**
 * Created by jennifer.huang on 11/5/18.
 */
public class ScheduleMeetingData {
    private String meetingName;
    private Integer duration_hours;
    private Integer duration_minutes;
    private String timeZone;
    private String repeat;
    private String audioOption;
    private String meetingPassword;
    private Boolean isHostVideoOn;
    private Boolean isAttendeeVideoOn;
    private Boolean isAllowJoinBeforeHost;
    private Boolean isUsePMI;


    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getDuration_hours() {
        return duration_hours;
    }

    public void setDuration_hours(Integer duration_hours) {
        this.duration_hours = duration_hours;
    }

    public Integer getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(Integer duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getAudioOption() {
        return audioOption;
    }

    public void setAudioOption(String audioOption) {
        this.audioOption = audioOption;
    }

    public String getMeetingPassword() {
        return meetingPassword;
    }

    public void setMeetingPassword(String meetingPassword) {
        this.meetingPassword = meetingPassword;
    }

    public Boolean isHostVideoOn() {
        return isHostVideoOn;
    }

    public void setHostVideoOn(Boolean hostVideoOn) {
        isHostVideoOn = hostVideoOn;
    }

    public Boolean isAttendeeVideoOn() {
        return isAttendeeVideoOn;
    }

    public void setAttendeeVideoOn(Boolean attendeeVideoOn) {
        isAttendeeVideoOn = attendeeVideoOn;
    }

    public Boolean isAllowJoinBeforeHost() {
        return isAllowJoinBeforeHost;
    }

    public void setAllowJoinBeforeHost(Boolean allowJoinBeforeHost) {
        isAllowJoinBeforeHost = allowJoinBeforeHost;
    }

    public Boolean isUsePMI() {
        return isUsePMI;
    }

    public void setUsePMI(Boolean usePMI) {
        isUsePMI = usePMI;
    }

}
