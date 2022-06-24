package com.cuc.model;

public class DayCarRental {
	private int id;
	private int carId;
	private int year;
	private int month;
	private int day;
	private int rentalNum;

	public DayCarRental(int carId, int day, int id, int month, int rentalNum,
			int year) {
		super();
		this.carId = carId;
		this.day = day;
		this.id = id;
		this.month = month;
		this.rentalNum = rentalNum;
		this.year = year;
	}

	public DayCarRental() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
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

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getRentalNum() {
		return rentalNum;
	}

	public void setRentalNum(int rentalNum) {
		this.rentalNum = rentalNum;
	}

}
