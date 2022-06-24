package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.MonthCarRental;

public interface IMonthCarRentalDAO {

	public boolean insert(ArrayList<MonthCarRental> monthCarRentalList);

	public ArrayList<String[]> getMonthCarRentalByCondition(String beginYear,
			String beginMonth, String endYear, String endMonth, int pageSize,
			int currentPage);

	public Object[] getMonthCarRentalRsCountByCondition(String beginYear,
			String beginMonth, String endYear, String endMonth);
}
