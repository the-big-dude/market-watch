package com.nextlevel.monitoring.backend.service.dashboard.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventDto {


    private Date date;
    private String logLevel;
    private List<String> messageExtensions;
    private Long eventId;
    private Long eventSubId;
    private String vendorId;
    private Long version;
    private Long deviceType;
    private Long module;
    private String outcome;
    private String messageFull;
    private String deviceId;
    private long function;
    private long subjectIdentity;

    public EventDto(){
        
    }

    public String getDeviceId() {
		return deviceId;
	}

    public long getSubjectIdentity() {
        return subjectIdentity;
    }

    public void setSubjectIdentity(long subjectIdentity) {
        this.subjectIdentity = subjectIdentity;
    }

    public long getFunction() {
        return function;
    }

    public void setFunction(long function) {
        this.function = function;
    }

    public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public List<String> getMessageExtensions() {
        return messageExtensions;
    }

    public void setMessageExtensions(List<String> messageExtensions) {
        this.messageExtensions = messageExtensions;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventSubId() {
        return eventSubId;
    }

    public void setEventSubId(Long eventSubId) {
        this.eventSubId = eventSubId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Long getModule() {
        return module;
    }

    public void setModule(Long module) {
        this.module = module;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }


    public String getMessageFull() {
        return messageFull;
    }

    public void setMessageFull(String messageFull) {
        this.messageFull = messageFull;
    }

	// @Override
	// public String toString() {
	// 	return "EventDto [date=" + date + ", logLevel=" + logLevel + ", messageExtensions=" + messageExtensions
	// 			+ ", eventId=" + eventId + ", eventSubId=" + eventSubId + ", vendorId=" + vendorId + ", version="
	// 			+ version + ", deviceType=" + deviceType + ", module=" + module + ", outcome=" + outcome
	// 			+ ", messageFull=" + messageFull + ", deviceId=" + deviceId + "]";
	// }
    
}
