package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BEAR")
public class Bear {
	@Id
	@Column
	@SequenceGenerator(sequenceName="BEAR_SEQ", name="BEAR_SEQ")
	@GeneratedValue(generator="BEAR_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer bearId;
	
	//By default, fetchtypes are LAZY
	/*
	 * Fetchtypes:
	 * -In hibernate, we have an option to save on time and memory by utilizing
	 * LAZY fetching. In LAZY fetching, we grab our bear instance when we hit database,
	 * but we only populate data for instance variables that are not HAS-A relationships.
	 */
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="BEAR_HOME")
	private Cave dwelling;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HONEYPOT_ID")
	private HoneyPot honeyPot;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PARENT_CUB", 
				joinColumns=@JoinColumn(name="BEAR_ID"), //reference id of class we are in
				inverseJoinColumns=@JoinColumn(name="CUB_ID")) //Reference id of class of instance variable
	private Set<Bear> cubs;											//<------------This one
	@Column
	private String color;
	@Column
	private String type;
	private Double weight;
	private Double height;
	public Integer getBearId() {
		return bearId;
	}
	public void setBearId(Integer bearId) {
		this.bearId = bearId;
	}
	public Cave getDwelling() {
		return dwelling;
	}
	public void setDwelling(Cave dwelling) {
		this.dwelling = dwelling;
	}
	public HoneyPot getHoneyPot() {
		return honeyPot;
	}
	public void setHoneyPot(HoneyPot honeyPot) {
		this.honeyPot = honeyPot;
	}
	public Set<Bear> getCubs() {
		return cubs;
	}
	public void setCubs(Set<Bear> cubs) {
		this.cubs = cubs;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Bear(Integer bearId, Cave dwelling, HoneyPot honeyPot, Set<Bear> cubs, String color, String type,
			Double weight, Double height) {
		this.bearId = bearId;
		this.dwelling = dwelling;
		this.honeyPot = honeyPot;
		this.cubs = cubs;
		this.color = color;
		this.type = type;
		this.weight = weight;
		this.height = height;
	}
	public Bear(Cave dwelling, HoneyPot honeyPot, Set<Bear> cubs, String color, String type,
			Double weight, Double height) {
		this.dwelling = dwelling;
		this.honeyPot = honeyPot;
		this.cubs = cubs;
		this.color = color;
		this.type = type;
		this.weight = weight;
		this.height = height;
	}
	public Bear(Cave dwelling, HoneyPot honeyPot, String color, String type,
			double weight, double height) {
		this.dwelling = dwelling;
		this.honeyPot = honeyPot;
		this.cubs = new HashSet<Bear>();
		this.color = color;
		this.type = type;
		this.weight = weight;
		this.height = height;
	}
	public Bear() {
		this.cubs = new HashSet<Bear>(); //Make sure your collections are not null, but empty
	}
	@Override
	public String toString() {
		return "Bear [bearId=" + bearId + ", cubs=" + cubs + ", color=" + color + ", type=" + type + ", weight="
				+ weight + ", height=" + height + "]";
	}
	
	
	
}
