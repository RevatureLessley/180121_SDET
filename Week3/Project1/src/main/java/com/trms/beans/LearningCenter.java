package com.trms.beans;

public class LearningCenter {
	private int centerId;
	private String centerName;
	private String addr;
	private String city;
	private String state;
	private int zip;
	private String country;
	
	
	public LearningCenter() {
		
	}


	public LearningCenter(int centerId, String centerName, String addr, String city, String state, int zip,
			String country) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}


	public int getCenterId() {
		return centerId;
	}


	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}


	public String getCenterName() {
		return centerName;
	}


	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
	
}
