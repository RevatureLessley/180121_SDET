package com.revature.beans;

public class Status {
	
	private int rei_id;
	private int emp_id;
	private String rfname;
	private String rlfname;
	private String afname;
	private String alname;
	private int arstatus;
	private String note;
	
	public Status(int rei_id, int emp_id, String rfname, String rlfname, String afname, String alname, int arstatus,
			String note) {
		super();
		this.rei_id = rei_id;
		this.emp_id = emp_id;
		this.rfname = rfname;
		this.rlfname = rlfname;
		this.afname = afname;
		this.alname = alname;
		this.arstatus = arstatus;
		this.note = note;
	}
	
	public void setRei_id(int rei_id) {this.rei_id = rei_id;}
	public void setEmp_id(int emp_id) {this.emp_id = emp_id;}
	public void setRfname(String rfname) {this.rfname = rfname;}
	public void setLfname(String rlfname) {this.rlfname = rlfname;}
	public void setAfname(String afname) {this.afname = afname;}
	public void setAlname(String alname) {this.alname = alname;}
	public void setArstatus(int arstatus) {this.arstatus = arstatus;}
	public void setNote(String note) {this.note = note;}
	
	public int getRei_id() {return rei_id;}
	public int getEmp_id() {return emp_id;}
	public String getRfname() {return rfname;}
	public String getRlfname() {return rlfname;}
	public String getAfname() {return afname;}
	public String getAlname() {return alname;}
	public int getArstatus() {return arstatus;}
	public String getNote() {return note;}
	
	
	
}
