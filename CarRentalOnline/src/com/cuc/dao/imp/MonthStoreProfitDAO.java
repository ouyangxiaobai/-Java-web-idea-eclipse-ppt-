package com.cuc.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IMonthStoreProfitDAO;
import com.cuc.model.MonthStoreProfit;
import com.cuc.util.SQLUtil;

public class MonthStoreProfitDAO implements IMonthStoreProfitDAO {

	public boolean insert(ArrayList<MonthStoreProfit> monthStoreProfitList) {
		boolean status = false;

		String sql = "insert into t_monthstoreprofit(storeId,year,month,sumMoney) values(?,?,?,?)";

		ArrayList<Object[]> paramArrayList = new ArrayList<Object[]>();
		for (int i = 0; i < monthStoreProfitList.size(); i++) {
			MonthStoreProfit monthStoreProfit = monthStoreProfitList.get(i);

			Object[] objs = new Object[4];
			objs[0] = monthStoreProfit.getStoreId();
			objs[1] = monthStoreProfit.getYear();
			objs[2] = monthStoreProfit.getMonth();
			objs[3] = monthStoreProfit.getSumMoney();

			paramArrayList.add(objs);
		}

		try {
			status = SQLUtil.getInstance().update(sql, paramArrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<String[]> getMonthStoreProfitByCondition(String beginDate,
			String endDate, Integer StoreId, int pageSize, int currentPage) {
		
		int start = (currentPage - 1) * pageSize + 1;
		
		String sql=" SELECT * "+
					" FROM t_monthstoreprofit "+
					" WHERE CONCAT(t_monthstoreprofit.`year`,IF (	LENGTH(t_monthstoreprofit.`month`) = 1,CONCAT(0, t_monthstoreprofit.`month`),	t_monthstoreprofit.`month`)) >= '"+beginDate+"' AND "+ 
					" CONCAT(t_monthstoreprofit.`year`,IF (LENGTH(t_monthstoreprofit.`month`) = 1,CONCAT(0, t_monthstoreprofit.`month`),t_monthstoreprofit.`month`)) < '"+endDate+"' ";
					
					if(StoreId!=null){
						sql=sql+" AND t_monthstoreprofit.storeId=1 ";
					}
		sql=sql+
					" ORDER BY t_monthstoreprofit.`year` DESC,t_monthstoreprofit.`month` DESC,t_monthstoreprofit.sumMoney desc "+
		            " limit " + (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getMonthStoreProfitByConditionRsCount(String beginDate,
			String endDate, Integer StoreId) {

		String sql=" SELECT count(*) "+
		" FROM t_monthstoreprofit "+
		" WHERE CONCAT(t_monthstoreprofit.`year`,IF (	LENGTH(t_monthstoreprofit.`month`) = 1,CONCAT(0, t_monthstoreprofit.`month`),	t_monthstoreprofit.`month`)) >= '"+beginDate+"' AND "+ 
		" CONCAT(t_monthstoreprofit.`year`,IF (LENGTH(t_monthstoreprofit.`month`) = 1,CONCAT(0, t_monthstoreprofit.`month`),t_monthstoreprofit.`month`)) < '"+endDate+"' ";
		
		if(StoreId!=null){
			sql=sql+" AND t_monthstoreprofit.storeId=1 ";
		}
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

}
