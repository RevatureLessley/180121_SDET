package com.revature.dao;

import com.revature.beans.Attachments;

public interface AttachmentDao {
	
	public int totatlAttachments();
	public int insertAttachment (Attachments a);
	public int getCountById(int id);
}
