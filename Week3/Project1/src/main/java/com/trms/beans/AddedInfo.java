package com.trms.beans;

public class AddedInfo {
	private int infoId;
	private int infoRid;
	private int infoEmpId;
	private String infoEmpName;
	private String infoMessage;
	private String infoSubject;
	
	
	public AddedInfo() {
	}
	
	
	
	public AddedInfo(int infoRid, int infoEmpId, String infoMessage) {
		this.infoRid = infoRid;
		this.infoEmpId = infoEmpId;
		this.infoMessage = infoMessage;
	}



	public AddedInfo(int infoId, String fName, String lName, String infoMessage) {
		this.infoId = infoId;
		this.infoEmpName = fName + " " + lName;
		this.infoMessage = infoMessage;
	}



	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public int getInfoRid() {
		return infoRid;
	}
	public void setInfoRid(int infoRid) {
		this.infoRid = infoRid;
	}
	public int getInfoEmpId() {
		return infoEmpId;
	}
	public void setInfoEmpId(int infoEmpId) {
		this.infoEmpId = infoEmpId;
	}
	public String getInfoEmpName() {
		return infoEmpName;
	}
	
	public void setInfoEmpName(String infoEmpName) {
		this.infoEmpName = infoEmpName;
	}
	
	public void setInfoEmpName(String fName, String lName) {
		this.infoEmpName = fName + " " + lName;
	}
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}



	public String getInfoSubject() {
		return infoSubject;
	}



	public void setInfoSubject(String infoSubject) {
		this.infoSubject = infoSubject;
	}
	
	
	
	
}
