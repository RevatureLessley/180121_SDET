package com.revature.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Attachments {

	private int a_id;
	private int rei_id;
	List<File> attachments;  // Create a List of File Objects to hold all attachments
	
	
	public Attachments() {this.attachments = new ArrayList<File>();}
	
	public Attachments( int rei_id, List<File> attachments) {
		this.rei_id = rei_id;
		this.attachments = attachments;
	}
	
	public Attachments(int a_id, int rei_id, List<File> attachments) {
		this.a_id = a_id;
		this.rei_id = rei_id;
		this.attachments = attachments;
	}

	public void setA_id(int a_id) {this.a_id = a_id;}
	public void setRei_id(int rei_id) {this.rei_id = rei_id;}
	public void setAttachments(List<File> attachments) {this.attachments = attachments;}

	public int getA_id() {return a_id;}
	public int getRei_id() {return rei_id;}
	public List<File> getAttachments() {return attachments;}
}
