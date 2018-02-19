package com.trms.services;

import java.util.List;

import com.trms.beans.LearningCenter;
import com.trms.daos.LearningCenterDao;
import com.trms.daos.LearningCenterDaoImpl;

public class LearningCenterService {
	public static List<LearningCenter> getLearningCenters() {
		LearningCenterDao dao = new LearningCenterDaoImpl();
		
		return dao.getLearningCenters();
	}
}
