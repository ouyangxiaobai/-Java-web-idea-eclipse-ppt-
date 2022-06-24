package com.cuc.dao.imp;

import java.util.ArrayList;

import com.cuc.dao.IDayRegisterDAO;
import com.cuc.model.DayRegister;
import com.cuc.util.SQLUtil;

public class DayRegisterDAO implements IDayRegisterDAO {

	public boolean insert(DayRegister register) {

		String sql = "insert into t_dayregister(year,month,day,regCount) values(?,?,?,?)";

		Object[] paramArray = new Object[4];
		paramArray[0] = register.getYear();
		paramArray[1] = register.getMonth();
		paramArray[2] = register.getDay();
		paramArray[3] = register.getRegCount();

		return SQLUtil.getInstance().update(sql, paramArray);

	}

	public ArrayList<String[]> getAllDayRegisterList(int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = "SELECT * " + " FROM t_dayregister "
				+ " ORDER BY `year` desc , `month` DESC ,`day` DESC "
				+ " limit " + (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayRegisterCount() {
		
		String sql = "SELECT count(*) FROM t_dayregister ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public Object[] getDayRegisterByConditionRsCount(String beginTime,
			String endTime) {

		String sql="SELECT count(*)  FROM t_dayregister "+
		" WHERE DATE_FORMAT( CONCAT(t_dayregister.`year`, IF( LENGTH(t_dayregister.`month`)=1,CONCAT(0,t_dayregister.`month`),t_dayregister.`month`),  IF( LENGTH( t_dayregister.`day`)=1,CONCAT(0, t_dayregister.`day`), t_dayregister.`day`)   ),'%Y-%m-%d') >= '"+beginTime+"' AND "+ 
		" DATE_FORMAT( CONCAT(t_dayregister.`year`, IF( LENGTH(t_dayregister.`month`)=1,CONCAT(0,t_dayregister.`month`),t_dayregister.`month`),  IF( LENGTH( t_dayregister.`day`)=1,CONCAT(0, t_dayregister.`day`), t_dayregister.`day`)   ),'%Y-%m-%d') <= '"+endTime+"'"+
		" ORDER BY `year` desc , `month` DESC ,`day` DESC ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> getDayRegisterByContidion(String beginTime,
			String endTime, int pageSize, int currentPage) {
		
		int start = (currentPage - 1) * pageSize + 1;
		
		String sql="SELECT *  FROM t_dayregister "+
					" WHERE DATE_FORMAT( CONCAT(t_dayregister.`year`, IF( LENGTH(t_dayregister.`month`)=1,CONCAT(0,t_dayregister.`month`),t_dayregister.`month`),  IF( LENGTH( t_dayregister.`day`)=1,CONCAT(0, t_dayregister.`day`), t_dayregister.`day`)   ),'%Y-%m-%d') >= '"+beginTime+"' AND "+ 
					" DATE_FORMAT( CONCAT(t_dayregister.`year`, IF( LENGTH(t_dayregister.`month`)=1,CONCAT(0,t_dayregister.`month`),t_dayregister.`month`),  IF( LENGTH( t_dayregister.`day`)=1,CONCAT(0, t_dayregister.`day`), t_dayregister.`day`)   ),'%Y-%m-%d') <= '"+endTime+"'"+
					" ORDER BY `year` desc , `month` DESC ,`day` DESC "+ " limit " + (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public ArrayList<String[]> getMonthRegisterByCondition(String beginDate,
			String endDate, int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;
		
		String sql=" SELECT * "+
					" FROM t_monthregister "+
					" WHERE CONCAT(t_monthregister.`year`, IF( LENGTH(t_monthregister.`month`)=1,CONCAT(0,t_monthregister.`month`),t_monthregister.`month`))  >= '"+beginDate+"' AND "+ 
					" CONCAT(t_monthregister.`year`, IF( LENGTH(t_monthregister.`month`)=1,CONCAT(0,t_monthregister.`month`),t_monthregister.`month`)  ) < '"+endDate+"' "+
					" ORDER BY `year` desc , `month` DESC  "+ " limit " + (start - 1) + "," + pageSize ;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getMonthRegisterByConditionRsCount(String beginDate,
			String endDate) {

		String sql=" SELECT count(*) "+
					" FROM t_monthregister "+
					" WHERE CONCAT(t_monthregister.`year`, IF( LENGTH(t_monthregister.`month`)=1,CONCAT(0,t_monthregister.`month`),t_monthregister.`month`))  >= '"+beginDate+"' AND "+ 
					" CONCAT(t_monthregister.`year`, IF( LENGTH(t_monthregister.`month`)=1,CONCAT(0,t_monthregister.`month`),t_monthregister.`month`)  ) < '"+endDate+"' ";
				
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

}
