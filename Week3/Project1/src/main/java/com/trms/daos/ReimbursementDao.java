package com.trms.daos;

import java.io.File;
import java.util.List;

import com.trms.beans.AddedInfo;
import com.trms.beans.Reimbursement;

public interface ReimbursementDao {
	// Attachment related methods
	public int insertReimbursement(Reimbursement r);
	public int insertAttachment(File f, int r_id);
	
	// Info related methods
	public List<AddedInfo> getAddedInfoBy(int rId);
	
	// Get
	public int getReimburseByEmpId(int empId);
	public Reimbursement getReimbBy(int rId);
	public List<Reimbursement> getPersonalReimb(int empId);
	public List<Reimbursement> getReimburse(int empId);
	public int getNumberAttachments(int rId);
	public int getEmpIdByReimburse(int rId);
	
	// Set
	public int updateGrade(int rId, float grade);
	
	// Approval related methods
	public int updateApproved(int rId, int response);
	public int setApproveId(int rId, int empId);
	public int setApproveLvl(int rId, int newLvl);
}
