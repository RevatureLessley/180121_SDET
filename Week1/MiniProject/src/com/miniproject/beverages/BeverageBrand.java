package com.miniproject.beverages;

public class BeverageBrand {
	private int beverageBrandId;
	private String brandName;
	private String bevType;
	
	public BeverageBrand(int beverageBrandId, String brandName, String bevType) {
		super();
		this.beverageBrandId = beverageBrandId;
		this.brandName = brandName;
		this.bevType = bevType;
	}

	public int getBeverageBrandId() {
		return beverageBrandId;
	}

	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBevType() {
		return bevType;
	}
	
	public void setBevType(String bevType) {
		this.bevType = bevType;
	}
}
