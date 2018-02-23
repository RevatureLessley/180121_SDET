package com.revature.dao;

import java.util.List;

import com.revature.beans.CustomGrade;

public interface CustomGradeDao {

	public void addCustomFormat (CustomGrade cust);
	public List<CustomGrade> getCustomFormatById(int reid);
	public List<CustomGrade> getAllCustomFormatById(int emp_id);
	
}
