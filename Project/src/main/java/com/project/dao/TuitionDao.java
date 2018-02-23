package com.project.dao;

import java.util.List;

import com.project.beans.Tuition;

public interface TuitionDao {
	
	public List<Tuition> getAllTuition();
	public boolean addTuition(Tuition t);
	public boolean deleteTuitonByUsername(String u);
	public boolean deleteTuitonByTuitionId(int i);
	
}
