package com.revature.beans;

public class AprovedReimbursement {

	int as_id;
	int rei_id;
	int supervisor_approval;
	int head_approval;
	int benco_approval;
	
	public AprovedReimbursement(int as_id, int rei_id, int supervisor_approval, int head_approval, int benco_approval) {
		super();
		this.as_id = as_id;
		this.rei_id = rei_id;
		this.supervisor_approval = supervisor_approval;
		this.head_approval = head_approval;
		this.benco_approval = benco_approval;
	}
	
	public void setAs_id(int as_id) {this.as_id = as_id;}
	public void setRei_id(int rei_id) {this.rei_id = rei_id;}
	public void setSupervisor_approval(int supervisor_approval) {this.supervisor_approval = supervisor_approval;}
	public void setHead_approval(int head_approval) {this.head_approval = head_approval;}
	public void setBenco_approval(int benco_approval) {this.benco_approval = benco_approval;}
	
	public int getAs_id() {return as_id;}
	public int getRei_id() {return rei_id;}
	public int getSupervisor_approval() {return supervisor_approval;}
	public int getHead_approval() {return head_approval;}
	public int getBenco_approval() {return benco_approval;}
	
}
