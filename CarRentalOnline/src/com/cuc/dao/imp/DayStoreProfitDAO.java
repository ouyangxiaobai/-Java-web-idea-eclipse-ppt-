package com.cuc.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IDayStoreProfitDAO;
import com.cuc.model.DayStoreProfit;
import com.cuc.util.SQLUtil;

public class DayStoreProfitDAO implements IDayStoreProfitDAO {

	public boolean insert(ArrayList<DayStoreProfit> storePrifitList) {
		boolean status = false;

		String sql = "insert into t_daystoreprofit(storeId,year,month,day,sumMoney) values(?,?,?,?,?)";

		ArrayList<Object[]> paramArrayList = new ArrayList<Object[]>();

		for (int i = 0; i < storePrifitList.size(); i++) {
			DayStoreProfit dayStorePrifit = storePrifitList.get(i);

			Object[] objs = new Object[5];
			objs[0] = dayStorePrifit.getStoreId();
			objs[1] = dayStorePrifit.getYear();
			objs[2] = dayStorePrifit.getMonth();
			objs[3] = dayStorePrifit.getDay();
			objs[4] = dayStorePrifit.getSumMoney();

			paramArrayList.add(objs);
		}

		try {
			status = SQLUtil.getInstance().update(sql, paramArrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	public ArrayList<String[]> getDayStoreProfit(int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = " SELECT * " + " FROM t_daystoreprofit "
				+ " ORDER BY `year` DESC,`month` DESC,`day` DESC,sumMoney DESC"
				+ " limit " + (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayStoreProfitRsCount() {

		String sql = " SELECT count(*)  FROM t_daystoreprofit ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> getDayStoreProfitByCondition(String beginDate,
			String endDate, Integer storeId, int pageSize, int currentPage) {
		
		int start = (currentPage - 1) * pageSize + 1;

		String sql=" SELECT * "+
					" FROM "+
					"	t_daystoreprofit "+
					" WHERE "+
					"	DATE_FORMAT( "+
					"		CONCAT( "+
					"			t_daystoreprofit.`year`, "+
					"		IF ( "+
					"			LENGTH(t_daystoreprofit.`month`) = 1, "+
					"			CONCAT(0, t_daystoreprofit.`month`), "+
					"			t_daystoreprofit.`month` "+
					"		), "+
					"	IF ( "+
					"		LENGTH(t_daystoreprofit.`day`) = 1, "+
					"		CONCAT(0, t_daystoreprofit.`day`), "+
					"		t_daystoreprofit.`day` "+
					"	) "+
					"		), "+
					"		'%Y-%m-%d' "+
					"	) >= '"+beginDate+"' "+
					" AND DATE_FORMAT( "+
					"	CONCAT( "+
					"		t_daystoreprofit.`year`, "+
					"	IF ( "+
					"		LENGTH(t_daystoreprofit.`month`) = 1, "+
					"		CONCAT(0, t_daystoreprofit.`month`), "+
					"		t_daystoreprofit.`month` "+
					"	), "+
					" IF ( "+
					"	LENGTH(t_daystoreprofit.`day`) = 1, "+
					"	CONCAT(0, t_daystoreprofit.`day`), "+
					"	t_daystoreprofit.`day` "+
					" ) "+
					"	), "+
					"	'%Y-%m-%d' "+
					" ) < '"+endDate+"' ";
					if(storeId!=null){
						
						sql=sql+" AND t_daystoreprofit.storeId = 1 ";
					}
					sql=sql+
					" ORDER BY "+
					"	`year` DESC, "+
					"	`month` DESC, "+
					"	`day` DESC, "+
					"	sumMoney DESC "+
					" limit " + (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayStoreProfitByConditionRsCount(String beginDate,
			String endDate, Integer storeId) {

		String sql=" SELECT count(*) "+
					" FROM "+
					"	t_daystoreprofit "+
					" WHERE "+
					"	DATE_FORMAT( "+
					"		CONCAT( "+
					"			t_daystoreprofit.`year`, "+
					"		IF ( "+
					"			LENGTH(t_daystoreprofit.`month`) = 1, "+
					"			CONCAT(0, t_daystoreprofit.`month`), "+
					"			t_daystoreprofit.`month` "+
					"		), "+
					"	IF ( "+
					"		LENGTH(t_daystoreprofit.`day`) = 1, "+
					"		CONCAT(0, t_daystoreprofit.`day`), "+
					"		t_daystoreprofit.`day` "+
					"	) "+
					"		), "+
					"		'%Y-%m-%d' "+
					"	) >= '"+beginDate+"' "+
					" AND DATE_FORMAT( "+
					"	CONCAT( "+
					"		t_daystoreprofit.`year`, "+
					"	IF ( "+
					"		LENGTH(t_daystoreprofit.`month`) = 1, "+
					"		CONCAT(0, t_daystoreprofit.`month`), "+
					"		t_daystoreprofit.`month` "+
					"	), "+
					" IF ( "+
					"	LENGTH(t_daystoreprofit.`day`) = 1, "+
					"	CONCAT(0, t_daystoreprofit.`day`), "+
					"	t_daystoreprofit.`day` "+
					" ) "+
					"	), "+
					"	'%Y-%m-%d' "+
					" ) < '"+endDate+"' ";
					if(storeId!=null){
						
						sql=sql+" AND t_daystoreprofit.storeId = 1 ";
					}
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> getMonthStoreProfitByDate(String Date) {


		String sql=" SELECT t_daystoreprofit.storeId,SUM(sumMoney) as sumMoney "+
					" FROM t_daystoreprofit "+
					" WHERE  CONCAT(t_daystoreprofit.`year`, IF( LENGTH(t_daystoreprofit.`month`)=1,CONCAT(0,t_daystoreprofit.`month`),t_daystoreprofit.`month`))  = '"+Date+"' "+  
					" GROUP BY t_daystoreprofit.storeId";
					
		return SQLUtil.getInstance().search(sql);
	}

	public ArrayList<String[]> getDayStoreProfitByStoreId(int storeId,
			int pageSize, int currentPage) {
		
		int start= (currentPage-1)*pageSize+1;
		
		String sql=" SELECT * "+
					" FROM t_daystoreprofit "+
					" WHERE t_daystoreprofit.storeId="+storeId+
					" ORDER BY t_daystoreprofit.`year`  DESC,t_daystoreprofit.`month` DESC,t_daystoreprofit.`day` desc "+
					" limit " + (start - 1) + "," + pageSize;
		
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayStoreProfitByStoreIdRsCount(int storeId) {
		
		String sql=" SELECT count(*) "+
			" FROM t_daystoreprofit "+
			" WHERE t_daystoreprofit.storeId="+storeId;
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

}
