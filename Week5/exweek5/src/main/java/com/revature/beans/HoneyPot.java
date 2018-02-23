package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HONEYPOT")
public class HoneyPot {
	@Id
	@Column
	@SequenceGenerator(sequenceName="HP_SEQ", name="HP_SEQ")
	@GeneratedValue(generator="HP_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer honeyPotId;
	@Column
	private double volume;
	@Column
	private double honeyAmount;
	
	
	
	public HoneyPot() {
	}

	public HoneyPot(double volume, double honeyAmount) {
		this.volume = volume;
		this.honeyAmount = honeyAmount;
	}
	
	public HoneyPot(Integer honeyPotId, double volume, double honeyAmount) {
		this.honeyPotId = honeyPotId;
		this.volume = volume;
		this.honeyAmount = honeyAmount;
	}
	public Integer getHoneyPotId() {
		return honeyPotId;
	}
	public void setHoneyPotId(Integer honeyPotId) {
		this.honeyPotId = honeyPotId;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getHoneyAmount() {
		return honeyAmount;
	}
	public void setHoneyAmount(double honeyAmount) {
		this.honeyAmount = honeyAmount;
	}

	@Override
	public String toString() {
		return "HoneyPot [honeyPotId=" + honeyPotId + ", volume=" + volume + ", honeyAmount=" + honeyAmount + "]";
	}
	
	
}
