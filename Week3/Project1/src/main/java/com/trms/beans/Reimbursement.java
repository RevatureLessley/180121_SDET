package com.trms.beans;

import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reimbursement {
	private int reimburseId;
	private int empId;
	private int eventId;
	private String eventStr;
	private int formatId;
	private String formatStr;
	private int centerId;
	private String centerStr;
	private float cost;
	private float projectedReimb;
	private String description;
	private float grade;
	private float passGrade;
	private int workDaysMissed;
	private LocalDateTime dateTime;
	private Date date;
	private String dateStr;
	private String timeStr;
	private LocalDateTime timestamp;
	private String workJustification;
	private int nextApprovalId;
	private int apprLvl;
	private int nextInfoReq;
	private int urgent;
	private int approved;
	private String approveStr;
	private List<AddedInfo> addedInfo;
	private List<File> attachments;
	private boolean files;
	
	public Reimbursement() {
		attachments = new ArrayList<File>();
		this.cost = 0f;
		this.projectedReimb = 0f;
		this.grade = 0f;
		this.passGrade = 75f;
		this.workDaysMissed = 0;
		this.urgent = 0;
		this.apprLvl = 2;
	}
	
	
	public Reimbursement(int reimburseId, String eventStr, String formatStr, String centerStr, float cost,
			float projectedReimb, Date date, int approved) {
		this.reimburseId = reimburseId;
		this.eventStr = eventStr;
		this.formatStr = formatStr;
		this.centerStr = centerStr;
		this.cost = cost;
		this.projectedReimb = projectedReimb;
		this.date = date;
		this.approved = approved;
	}


	public Reimbursement(int reimburseId, String eventStr, String formatStr, String centerStr, float cost,
			float projectedReimb, Date date, int approved, List<File> attachments) {
		this.reimburseId = reimburseId;
		this.eventStr = eventStr;
		this.formatStr = formatStr;
		this.centerStr = centerStr;
		this.cost = cost;
		this.projectedReimb = projectedReimb;
		this.date = date;
		this.approved = approved;
		this.attachments = attachments;
	}


	public Reimbursement(int empId, int eventId, int formatId, int centerId, float cost, float projectedReimb,
			String description, float grade, float passGrade, int workDaysMissed, LocalDateTime dateTime,
			LocalDateTime timestamp, String workJustification, int nextApprovalId) {
		this.empId = empId;
		this.eventId = eventId;
		this.formatId = formatId;
		this.centerId = centerId;
		this.cost = cost;
		this.projectedReimb = projectedReimb;
		this.description = description;
		this.grade = grade;
		this.passGrade = passGrade;
		this.workDaysMissed = workDaysMissed;
		this.dateTime = dateTime;
		this.timestamp = timestamp;
		this.workJustification = workJustification;
		this.nextApprovalId = nextApprovalId;
	}
	
	
	
	public int getReimburseId() {
		return reimburseId;
	}


	public void setReimburseId(int reimburseId) {
		this.reimburseId = reimburseId;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public int getEventId() {
		return eventId;
	}


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}


	public int getFormatId() {
		return formatId;
	}


	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}


	public int getCenterId() {
		return centerId;
	}


	public String getEventStr() {
		return eventStr;
	}


	public void setEventStr(String eventStr) {
		this.eventStr = eventStr;
	}


	public String getFormatStr() {
		return formatStr;
	}


	public void setFormatStr(String formatStr) {
		this.formatStr = formatStr;
	}


	public String getCenterStr() {
		return centerStr;
	}


	public void setCenterStr(String centerStr) {
		this.centerStr = centerStr;
	}


	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}


	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}


	public float getProjectedReimb() {
		return projectedReimb;
	}


	public void setProjectedReimb(float projectedReimb) {
		this.projectedReimb = projectedReimb;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getGrade() {
		return grade;
	}


	public void setGrade(float grade) {
		this.grade = grade;
	}


	public float getPassGrade() {
		return passGrade;
	}


	public void setPassGrade(float passGrade) {
		this.passGrade = passGrade;
	}


	public int getWorkDaysMissed() {
		return workDaysMissed;
	}


	public void setWorkDaysMissed(int workDaysMissed) {
		this.workDaysMissed = workDaysMissed;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDateStr() {
		return dateStr;
	}


	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}


	public String getTimeStr() {
		return timeStr;
	}


	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getWorkJustification() {
		return workJustification;
	}


	public void setWorkJustification(String workJustification) {
		this.workJustification = workJustification;
	}


	public int getNextApprovalId() {
		return nextApprovalId;
	}


	public void setNextApprovalId(int nextApprovalId) {
		this.nextApprovalId = nextApprovalId;
	}


	public int getApprLvl() {
		return apprLvl;
	}


	public void setApprLvl(int apprLvl) {
		this.apprLvl = apprLvl;
	}


	public String getApproveStr() {
		return approveStr;
	}


	public void setApproveStr(String approveStr) {
		this.approveStr = approveStr;
	}


	public int getNextInfoReq() {
		return nextInfoReq;
	}


	public void setNextInfoReq(int nextInfoReq) {
		this.nextInfoReq = nextInfoReq;
	}


	public int getUrgent() {
		return urgent;
	}


	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}


	public int getApproved() {
		return approved;
	}


	public void setApproved(int approved) {
		this.approved = approved;
	}


	public List<File> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	public void addFile(File f) {
		attachments.add(f);
	}

	public boolean isFiles() {
		return files;
	}

	public void setFiles(boolean files) {
		this.files = files;
	}


	public List<AddedInfo> getAddedInfo() {
		return addedInfo;
	}


	public void setAddedInfo(List<AddedInfo> addedInfo) {
		this.addedInfo = addedInfo;
	}
	
	
	
}
