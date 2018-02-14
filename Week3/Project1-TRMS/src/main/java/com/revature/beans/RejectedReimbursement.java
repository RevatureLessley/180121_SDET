package com.revature.beans;

public class RejectedReimbursement {

  int  rej_id;
  int rei_id;
  String reason;
  
  public RejectedReimbursement(int rej_id, int rei_id, String reason) {
	super();
	this.rej_id = rej_id;
	this.rei_id = rei_id;
	this.reason = reason;
}
  
  public void setRej_id(int rej_id) {this.rej_id = rej_id;}
  public void setRei_id(int rei_id) {this.rei_id = rei_id;}
  public void setReason(String reason) {this.reason = reason;}

  public int getRej_id() {return rej_id;}
  public int getRei_id() {return rei_id;}
  public String getReason() {return reason;}

}
