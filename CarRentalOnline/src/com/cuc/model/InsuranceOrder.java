package com.cuc.model;


public class InsuranceOrder {
	private int oId;// 订单保险编号
	private int orderId;// 订单编号
	private int insuranceId;// 保险编号
	private float price;// 价格

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOId() {
		return oId;
	}

	public void setOId(int id) {
		oId = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

}
