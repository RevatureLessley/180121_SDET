package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CAVE")
public class Cave {
	@Id
	@Column
	@SequenceGenerator(sequenceName="CAVE_SEQ", name="CAVE_SEQ")
	@GeneratedValue(generator="CAVE_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer caveId;
	@OneToMany(mappedBy="dwelling", fetch=FetchType.EAGER)
	private Set<Bear> residents;
	@Column
	private String caveType;
	@Column
	private double sqFootage;
	
	
	
	
	public Cave() {
	}
	public Cave(String caveType, double sqFootage) {
		this.residents = new HashSet<Bear>();
		this.caveType = caveType;
		this.sqFootage = sqFootage;
	}
	public Cave(Set<Bear> residents, String caveType, double sqFootage) {
		this.residents = residents;
		this.caveType = caveType;
		this.sqFootage = sqFootage;
	}
	public Cave(Integer caveId, Set<Bear> residents, String caveType, double sqFootage) {
		this.caveId = caveId;
		this.residents = residents;
		this.caveType = caveType;
		this.sqFootage = sqFootage;
	}
	public Integer getCaveId() {
		return caveId;
	}
	public void setCaveId(Integer caveId) {
		this.caveId = caveId;
	}
	public Set<Bear> getResidents() {
		return residents;
	}
	public void setResidents(Set<Bear> residents) {
		this.residents = residents;
	}
	public String getCaveType() {
		return caveType;
	}
	public void setCaveType(String caveType) {
		this.caveType = caveType;
	}
	public double getSqFootage() {
		return sqFootage;
	}
	public void setSqFootage(double sqFootage) {
		this.sqFootage = sqFootage;
	}
	@Override
	public String toString() {
		return "Cave [caveId=" + caveId + ", caveType=" + caveType + ", sqFootage=" + sqFootage + "]";
	}



}
