package com.project.beans;

public class Reimbursement {
	private String username;
	private double pending;
	private double awarded;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Reimbursement(String username, double pending, double awarded) {
		super();
		this.username = username;
		this.pending = pending;
		this.awarded = awarded;
	}
	public Reimbursement() {
		super();
	}
	@Override
	public String toString() {
		return "Reimbursement [username=" + username + ", pending=" + pending + ", awarded=" + awarded + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(awarded);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pending);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(awarded) != Double.doubleToLongBits(other.awarded))
			return false;
		if (Double.doubleToLongBits(pending) != Double.doubleToLongBits(other.pending))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
