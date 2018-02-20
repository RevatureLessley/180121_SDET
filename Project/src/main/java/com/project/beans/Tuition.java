package com.project.beans;

import java.time.LocalDate;
import java.util.Arrays;

public class Tuition {
	
	private String username;
	private LocalDate start_date;
	private LocalDate end_date;
	private String location;
	private String description;
	private double cost;
	private String grading_formate;
	private String event_type;
	private byte[] attachment;
	
	public Tuition(String username, LocalDate start_date, LocalDate end_date, String location, String description,
			double cost, String grading_formate, String event_type, byte[] attachment) {
		super();
		this.username = username;
		this.start_date = start_date;
		this.end_date = end_date;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.grading_formate = grading_formate;
		this.event_type = event_type;
		this.attachment = attachment;
	}

	public Tuition() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getGrading_formate() {
		return grading_formate;
	}

	public void setGrading_formate(String grading_formate) {
		this.grading_formate = grading_formate;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "Tuition [username=" + username + ", start_date=" + start_date + ", end_date=" + end_date + ", location="
				+ location + ", description=" + description + ", cost=" + cost + ", grading_formate=" + grading_formate
				+ ", event_type=" + event_type + ", attachment=" + Arrays.toString(attachment) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attachment);
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + ((event_type == null) ? 0 : event_type.hashCode());
		result = prime * result + ((grading_formate == null) ? 0 : grading_formate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
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
		Tuition other = (Tuition) obj;
		if (!Arrays.equals(attachment, other.attachment))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (event_type == null) {
			if (other.event_type != null)
				return false;
		} else if (!event_type.equals(other.event_type))
			return false;
		if (grading_formate == null) {
			if (other.grading_formate != null)
				return false;
		} else if (!grading_formate.equals(other.grading_formate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
}
