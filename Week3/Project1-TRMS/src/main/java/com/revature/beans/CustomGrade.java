package com.revature.beans;

public class CustomGrade {
	private int reid;
	private int empid;
	private String fname;
	private String lname;
	private String customFormat;
	private String formatDesc;
	
	public CustomGrade(int reid, int empid, String fname, String lname, String customFormat, String formatDesc) {
		super();
		this.reid = reid;
		this.empid = empid;
		this.fname = fname;
		this.lname = lname;
		this.customFormat = customFormat;
		this.formatDesc = formatDesc;
	}
	
	public void setReid(int reid) {this.reid = reid;}
	public void setEmpid(int empid) {this.empid = empid;}
	public void setFname(String fname) {this.fname = fname;}
	public void setLname(String lname) {this.lname = lname;}
	public void setCustomFormat(String customFormat) {this.customFormat = customFormat;}
	public void setFormatDesc(String formatDesc) {this.formatDesc = formatDesc;}

	public int getReid() {return reid;}
	public int getEmpid() {return empid;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getCustomFormat() {return customFormat;}
	public String getFormatDesc() {return formatDesc;}
	
	
}
