package com.revature.dao;

import java.io.File;

import com.revature.beans.Attachments;

public interface GradeDao {

	public int insertGrade(String grade, int rei_id, int b);
	public int insertGradeAttachment(Attachments attachment);
	
	public int totalGradeAttachments();
	public int getAid(int rei_id);
	public File getFile(int a_id);
	public String getFileName(int a_id);
	public String getFileType(int a_id);
}
