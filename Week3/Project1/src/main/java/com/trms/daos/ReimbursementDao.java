package com.trms.daos;

import java.io.File;
import java.util.List;

import com.trms.beans.Reimbursement;

public interface ReimbursementDao {
	public int insertReimbursement(Reimbursement r);
	public int insertAttachment(File f, int r_id);
	public int getReimburseByEmpId(int empId);
}
