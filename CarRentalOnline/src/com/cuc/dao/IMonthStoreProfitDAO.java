package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.MonthStoreProfit;

public interface IMonthStoreProfitDAO {

	public boolean insert(ArrayList<MonthStoreProfit> monthStoreProfitList);

	public ArrayList<String[]> getMonthStoreProfitByCondition(String beginDate,
			String endDate, Integer StoreId, int pageSize, int currentPage);

	public Object[] getMonthStoreProfitByConditionRsCount(String beginDate,
			String endDate, Integer StoreId);

}
