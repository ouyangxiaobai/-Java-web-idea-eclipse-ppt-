package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.DayCarRental;

public interface IDayCarRentalDAO {

	public boolean insert(ArrayList<DayCarRental> dayCarRentalList);

	/**
	 * 获取某年某月的车辆租赁次数统计
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public ArrayList<String[]> getMonthCarRentalNum(String year, String month);

	/**
	 * 查询所有日期的车辆预定次数
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> getDayCarRental(int pageSize, int currentPage);

	/**
	 * 查询所有日期的车辆预定次数的条数
	 * 
	 * @return
	 */
	public Object[] getDayCarRentalRsCount();

	/**在指定日期之间查询车辆预定次数
	 * @param beginTime
	 * @param endTime
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> getDayCarRentalByCondition(String beginTime,
			String endTime, int pageSize, int currentPage);
	
	/**在指定日期之间查询车辆预定次数的条数
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Object[] getDayCarRentalRsCountByCondition(String beginTime,
			String endTime);
	
	
}
