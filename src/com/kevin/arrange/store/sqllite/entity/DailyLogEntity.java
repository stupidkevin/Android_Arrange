package com.kevin.arrange.store.sqllite.entity;

public class DailyLogEntity {
	private int id;
	private String arragenEvent;
	private int status;
	private String submitDay;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArragenEvent() {
		return arragenEvent;
	}
	public void setArragenEvent(String arragenEvent) {
		this.arragenEvent = arragenEvent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSubmitDay() {
		return submitDay;
	}
	public void setSubmitDay(String submitDay) {
		this.submitDay = submitDay;
	}
	
	
}
