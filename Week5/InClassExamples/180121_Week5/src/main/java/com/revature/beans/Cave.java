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
	
	//In this case, we map to the 'dwelling' variable of the 'Bear' class.
	@OneToMany(mappedBy="dwelling", fetch=FetchType.EAGER)
	private Set<Bear> residents;
	
	@Column
	private String caveType;
	
	@Column
	private double squareFootage;

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

	public double getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(double squareFootage) {
		this.squareFootage = squareFootage;
	}

	public Cave(Integer caveId, Set<Bear> residents, String caveType, double squareFootage) {
		super();
		this.caveId = caveId;
		this.residents = residents;
		this.caveType = caveType;
		this.squareFootage = squareFootage;
	}
	
	public Cave(Set<Bear> residents, String caveType, double squareFootage) {
		super();
		this.residents = residents;
		this.caveType = caveType;
		this.squareFootage = squareFootage;
	}
	
	public Cave(String caveType, double squareFootage) {
		super();
		this.residents = new HashSet<Bear>();
		this.caveType = caveType;
		this.squareFootage = squareFootage;
	}
	
	public Cave() {
		this.residents = new HashSet<Bear>();
	}

	@Override
	public String toString() {
		return "Cave [caveId=" + caveId + ", residents=" + residents + ", caveType=" + caveType + ", squareFootage="
				+ squareFootage + "]";
	}
	
	
	
}
