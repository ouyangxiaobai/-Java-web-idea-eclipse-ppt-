package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.DayStoreProfit;

public interface IDayStoreProfitDAO {

	public boolean insert(ArrayList<DayStoreProfit> storePrifitList);

	/**
	 * 获取所有营业门店每日收益
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> getDayStoreProfit(int pageSize, int currentPage);

	/**
	 * 获取所有营业门店每日收益的条数
	 * 
	 * @return
	 */
	public Object[] getDayStoreProfitRsCount();

	/**
	 * 查询某年某月某日至某年某月某日的营业门店收益
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param storeId
	 *            营业门店编号(可以为NULL，代表查询所有门店)
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> getDayStoreProfitByCondition(String beginDate,
			String endDate, Integer storeId, int pageSize, int currentPage);

	/**
	 * 查询某年某月某日至某年某月某日的营业门店收益的条数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param storeId
	 *            营业门店编号(可以为NULL，代表查询所有门店)
	 * @return
	 */
	public Object[] getDayStoreProfitByConditionRsCount(String beginDate,
			String endDate, Integer storeId);

	/**
	 * 获取 Date 年月份的每家营业门店的月收益
	 * 
	 * @param Date
	 * @return
	 */
	public ArrayList<String[]> getMonthStoreProfitByDate(String Date);

	/**
	 * 营业门店人员进入查询自己的每日营业收益
	 * 
	 * @param storeId
	 * @return
	 */
	public ArrayList<String[]> getDayStoreProfitByStoreId(int storeId,
			int pageSize, int currentPage);

	public Object[] getDayStoreProfitByStoreIdRsCount(int storeId);

}
