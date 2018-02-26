package com.revature.beans;

public class Reimbursements {
	
	private int rid;
	private int eid;
	private double cost;
	private double reimbursement;
	private String rtype;
	private String description;
	private String status;
	private String name;
	
	public Reimbursements(int rid, int eid, double cost, double reimbursement, String rtype, String description,
			String status) {
		super();
		this.rid = rid;
		this.eid = eid;
		this.cost = cost;
		this.reimbursement = reimbursement;
		this.rtype = rtype;
		this.description = description;
		this.status = status;
	}
	public Reimbursements(int rid, int eid, double cost, double reimbursement, String rtype, String description,
			String status, String name) {
		super();
		this.rid = rid;
		this.eid = eid;
		this.cost = cost;
		this.reimbursement = reimbursement;
		this.rtype = rtype;
		this.description = description;
		this.status = status;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Reimbursements [rid=" + rid + ", eid=" + eid + ", cost=" + cost + ", reimbursement=" + reimbursement
				+ ", rtype=" + rtype + ", description=" + description + ", status=" + status + "]";
	}
	public Reimbursements() {}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getReimbursement() {
		return reimbursement;
	}
	public void setReimbursement(double reimbursement) {
		this.reimbursement = reimbursement;
	}
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}