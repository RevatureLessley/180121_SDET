package com.revature.dao;

import java.io.File;

import com.revature.beans.Attachments;

public interface AttachmentDao {
	
	public int totatlAttachments();
	public int insertAttachment (Attachments a);
	public int getCountById(int id);
	public int getAid(int rei_id);
	
	public File getFile(int a_id);
	public String getFileName(int a_id);
	public String getFileType(int a_id);
}
