package com.cuc.model;

public class MonthStoreProfit {
	private int id;
	private int storeId;
	private int year;
	private int month;

	private float sumMoney;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public float getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(float sumMoney) {
		this.sumMoney = sumMoney;
	}

}
