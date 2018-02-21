package com.project1.beans;

public class Reimbursement {
	String email;
    double available;
    double pending;
    double awarded;
    double total;
    String lastReibursementDate;
    
	public Reimbursement(String email, double available, double pending, double awarded, double total,
			String lastReibursementDate) {
		super();
		this.email = email;
		this.available = available;
		this.pending = pending;
		this.awarded = awarded;
		this.total = total;
		this.lastReibursementDate = lastReibursementDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAvailable() {
		return available;
	}
	public void setAvailable(double available) {
		this.available = available;
	}
	public double getPending() {
		return pending;
	}
	public void setPending(double pending) {
		this.pending = pending;
	}
	public double getAwarded() {
		return awarded;
	}
	public void setAwarded(double awarded) {
		this.awarded = awarded;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getLastReibursementDate() {
		return lastReibursementDate;
	}
	public void setLastReibursementDate(String lastReibursementDate) {
		this.lastReibursementDate = lastReibursementDate;
	}

	@Override
	public String toString() {
		return "Reimbursement [email=" + email + ", available=" + available + ", pending=" + pending + ", awarded="
				+ awarded + ", total=" + total + ", lastReibursementDate=" + lastReibursementDate + "]";
	}
	
	
}
