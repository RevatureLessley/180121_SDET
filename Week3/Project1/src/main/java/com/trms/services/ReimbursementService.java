package com.trms.services;

import com.trms.beans.Reimbursement;
import com.trms.daos.ReimbursementDao;
import com.trms.daos.ReimbursementDaoImpl;

public class ReimbursementService {
	public static int insertReimbursement(Reimbursement r) {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		return dao.insertReimbursement(r);
	}
}
