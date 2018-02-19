package com.trms.beans;

public class EventType {
	private int eventId;
	private String eventName;
	private float eventCoverage;
	
	
	
	public EventType() {
	
	}
	
	public EventType(int eventId, String eventName, float eventCoverage) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventCoverage = eventCoverage;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public float getEventCoverage() {
		return eventCoverage;
	}
	public void setEventCoverage(float eventCoverage) {
		this.eventCoverage = eventCoverage;
	}
	
	
	
	
}
