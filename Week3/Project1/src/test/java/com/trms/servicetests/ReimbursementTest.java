package com.trms.servicetests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.trms.daos.ReimbursementDao;
import com.trms.daos.ReimbursementDaoImpl;
import com.trms.services.ReimbursementService;

public class ReimbursementTest {

	@Test
	public void correctEmpIdFromReimbursement() {
		assertEquals(2, ReimbursementService.getEmpIdByReimburse(6));
	}
	
	@Test
	public void numberOfAttachments() {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		assertEquals(1, dao.getNumberAttachments(10));
	}
	
	@Test
	public void numberOfAttachments01() {
		ReimbursementDao dao = new ReimbursementDaoImpl();
		assertEquals(0, dao.getNumberAttachments(8));
	}

}
