package com.cuc.model;

public class Order {
	private int orderId;// 订单编号
	private int fromStoreId;// 取车营业门店编号
	private int toStoreId;// 还车营业门店编号
	private int frequentId;// 常用联系人编号
	private int carId;// 车辆编号
	private String orderTime;// 下单时间
	private String collectionTime;// 取车时间
	private String returnTime;// 还车时间
	private float price;// 租车价格
	private float totalMoney;// 总价格
	private String orderState;// 订单状态
	private String remark;// 备注
	private int isCancelByDaiChuCheTimeOut;// 订单被取消(因超过取车时间)
	private String songCheShangMenAddress;//上门取车地址
	private String shangMenQuCheAddress;//上门取车地址

	public int getIsCancelByDaiChuCheTimeOut() {
		return isCancelByDaiChuCheTimeOut;
	}

	public void setIsCancelByDaiChuCheTimeOut(int isCancelByDaiChuCheTimeOut) {
		this.isCancelByDaiChuCheTimeOut = isCancelByDaiChuCheTimeOut;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFromStoreId() {
		return fromStoreId;
	}

	public void setFromStoreId(int fromStoreId) {
		this.fromStoreId = fromStoreId;
	}

	public int getToStoreId() {
		return toStoreId;
	}

	public void setToStoreId(int toStoreId) {
		this.toStoreId = toStoreId;
	}

	public int getFrequentId() {
		return frequentId;
	}

	public void setFrequentId(int frequentId) {
		this.frequentId = frequentId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSongCheShangMenAddress() {
		return songCheShangMenAddress;
	}

	public void setSongCheShangMenAddress(String songCheShangMenAddress) {
		this.songCheShangMenAddress = songCheShangMenAddress;
	}

	public String getShangMenQuCheAddress() {
		return shangMenQuCheAddress;
	}

	public void setShangMenQuCheAddress(String shangMenQuCheAddress) {
		this.shangMenQuCheAddress = shangMenQuCheAddress;
	}
	

}
