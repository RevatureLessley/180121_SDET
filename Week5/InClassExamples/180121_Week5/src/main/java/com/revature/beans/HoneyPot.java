package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
			@NamedQuery(name="getAllHoneypots", query="FROM HoneyPot")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="getSmallHoneypots",
			query="SELECT * FROM HONEYPOT WHERE VOLUME < :maxVolume",
			resultClass=HoneyPot.class)
})

@Entity
@Table(name="HONEYPOT")
public class HoneyPot {
	@Id
	@Column(name="HONEYPOT_ID")
	@SequenceGenerator(sequenceName="HP_SEQ", name="HP_SEQ")
	@GeneratedValue(generator="HP_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer honeyPotId;
	@Column
	private double volume;
	@Column
	private double honeyAmount;
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
	public HoneyPot(Integer honeyPotId, double volume, double honeyAmount) {
		super();
		this.honeyPotId = honeyPotId;
		this.volume = volume;
		this.honeyAmount = honeyAmount;
	}
	public HoneyPot(double volume, double honeyAmount) {
		super();
		this.volume = volume;
		this.honeyAmount = honeyAmount;
	}
	public HoneyPot() {

	}
	@Override
	public String toString() {
		return "HoneyPot [honeyPotId=" + honeyPotId + ", volume=" + volume + ", honeyAmount=" + honeyAmount + "]";
	}
	
	
}
