package com.miniproject.services;

import com.miniproject.beverages.BeverageBrand;
import com.miniproject.dao.BeverageBrandDao;
import com.miniproject.dao.BeverageBrandDaoImpl;

public class BeverageBrandService {
	public static BeverageBrand getBevBrand(int inBrandId) {
		BeverageBrandDao dao = new BeverageBrandDaoImpl();
		return dao.getBeverageBrand(inBrandId);
	}
	
	public static String getBrandName(int inBrandId) {
		BeverageBrandDao dao = new BeverageBrandDaoImpl();
		return dao.getBrand(inBrandId);
	}
}
