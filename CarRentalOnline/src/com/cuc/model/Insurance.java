package com.cuc.model;

public class Insurance {
	private int insuranceId;// 保险编号
	private String insuranceName;// 名称
	private float insurancePrice;// 价格
	private String insuranceContent;// 内容描述
	private int state;// 可用状态

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public float getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(float insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public String getInsuranceContent() {
		return insuranceContent;
	}

	public void setInsuranceContent(String insuranceContent) {
		this.insuranceContent = insuranceContent;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
