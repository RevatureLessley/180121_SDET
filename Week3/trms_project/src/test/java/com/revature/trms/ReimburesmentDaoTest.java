package com.revature.trms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.beans.Reimbursements;
import com.revature.dao.ReimbursementDao;

public class ReimburesmentDaoTest {
	ReimbursementDao dao = new ReimbursementDao();
	List<Reimbursements> r = new ArrayList<>();
	Reimbursements rtest = new Reimbursements();
	@Test
	public void EmpLoginTest() {
		rtest.setRtype("Certification");
		rtest.setCost(300.0);
		int rid = 6;
		r = dao.getMyReimbursements(rid);
		assertEquals(r.get(0).getRtype(), rtest.getRtype());
	}
	@Test
	public void MaxTest() {
		int max = 62;
		int maxtest;
		maxtest = dao.getMostRecentEntry();
		assertEquals(max, maxtest);
	}
	@Test
	public void deptHeadEidTest() {
		r = dao.getDeptHeadReimbursements(2);
		System.out.println(r.get(0).getEid());
		assertEquals(r.get(0).getEid(), 4);
	}
}