package com.trms.beans;

/**
 * Beans class for the table that handles the grade formats a reimbursement can have in order to pass.
 * @author Lynda
 *
 */
public class GradeFormat {
	private int formatId;
	private String formatName;
	private String formatReq;
	
	
	public GradeFormat() {

	}


	public GradeFormat(int formatId, String formatName, String formatReq) {
		this.formatId = formatId;
		this.formatName = formatName;
		this.formatReq = formatReq;
	}


	public int getFormatId() {
		return formatId;
	}


	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}


	public String getFormatName() {
		return formatName;
	}


	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}


	public String getFormatReq() {
		return formatReq;
	}


	public void setFormatReq(String formatReq) {
		this.formatReq = formatReq;
	}
	
	
	
}
