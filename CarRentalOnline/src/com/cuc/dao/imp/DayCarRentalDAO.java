package com.cuc.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IDayCarRentalDAO;
import com.cuc.model.DayCarRental;
import com.cuc.util.SQLUtil;

public class DayCarRentalDAO implements IDayCarRentalDAO {

	public boolean insert(ArrayList<DayCarRental> dayCarRentalList) {

		boolean status = false;

		String sql = "insert into t_dayCarRental(carId,year,month,day,rentalNum) values(?,?,?,?,?)";

		ArrayList<Object[]> paramArrayList = new ArrayList<Object[]>();
		for (int i = 0; i < dayCarRentalList.size(); i++) {
			DayCarRental dayCarRental = dayCarRentalList.get(i);

			Object[] objs = new Object[5];
			objs[0] = dayCarRental.getCarId();
			objs[1] = dayCarRental.getYear();
			objs[2] = dayCarRental.getMonth();
			objs[3] = dayCarRental.getDay();
			objs[4] = dayCarRental.getRentalNum();

			paramArrayList.add(objs);
		}

		try {
			status = SQLUtil.getInstance().update(sql, paramArrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<String[]> getMonthCarRentalNum(String year,
			String month) {
		
		if(month.length()<2){
			month = "0"+month;
		}

		String sql="SELECT t_daycarrental.carId,sum(t_daycarrental.rentalNum) as rentalNum "+
				" FROM t_daycarrental "+
				" WHERE CONCAT(t_daycarrental.`year`, IF( LENGTH(t_daycarrental.`month`)=1,CONCAT(0,t_daycarrental.`month`),t_daycarrental.`month`))  = '"+year+month+"' "+  
				" GROUP BY t_daycarrental.carId";
		
		return SQLUtil.getInstance().search(sql);
	}

	public ArrayList<String[]> getDayCarRental(int pageSize, int currentPage) {

		int start = (currentPage-1)*pageSize+1;
		
		String sql=" SELECT * " +
					" FROM t_daycarrental " +
					" ORDER BY t_daycarrental.`year`desc ,t_daycarrental.`month`desc ,t_daycarrental.`day` desc ,t_daycarrental.rentalNum desc " +
					" limit " + (start - 1) + "," + pageSize;

		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayCarRentalRsCount() {
		
		String sql=" SELECT count(*)  FROM t_daycarrental " ;

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> getDayCarRentalByCondition(String beginTime,
			String endTime, int pageSize, int currentPage) {
		
		int start = (currentPage-1)*pageSize+1;

		String sql="SELECT * " +
				" FROM t_daycarrental " +
				" WHERE DATE_FORMAT( CONCAT(t_daycarrental.`year`, IF( LENGTH(t_daycarrental.`month`)=1,CONCAT(0,t_daycarrental.`month`),t_daycarrental.`month`),  IF( LENGTH( t_daycarrental.`day`)=1,CONCAT(0, t_daycarrental.`day`), t_daycarrental.`day`)   ),'%Y-%m-%d') >= '"+beginTime+"' " +
				" AND DATE_FORMAT( CONCAT(t_daycarrental.`year`, IF( LENGTH(t_daycarrental.`month`)=1,CONCAT(0,t_daycarrental.`month`),t_daycarrental.`month`),  IF( LENGTH( t_daycarrental.`day`)=1,CONCAT(0, t_daycarrental.`day`), t_daycarrental.`day`)   ),'%Y-%m-%d') < '"+endTime+"' " +
				" ORDER BY t_daycarrental.`year`desc ,t_daycarrental.`month`desc ,t_daycarrental.`day` desc,t_daycarrental.rentalNum desc "+
				" limit " + (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getDayCarRentalRsCountByCondition(String beginTime,
			String endTime) {
		
		String sql="SELECT count(*) " +
					" FROM t_daycarrental " +
					" WHERE DATE_FORMAT( CONCAT(t_daycarrental.`year`, IF( LENGTH(t_daycarrental.`month`)=1,CONCAT(0,t_daycarrental.`month`),t_daycarrental.`month`),  IF( LENGTH( t_daycarrental.`day`)=1,CONCAT(0, t_daycarrental.`day`), t_daycarrental.`day`)   ),'%Y-%m-%d') >= '"+beginTime+"' " +
					" AND DATE_FORMAT( CONCAT(t_daycarrental.`year`, IF( LENGTH(t_daycarrental.`month`)=1,CONCAT(0,t_daycarrental.`month`),t_daycarrental.`month`),  IF( LENGTH( t_daycarrental.`day`)=1,CONCAT(0, t_daycarrental.`day`), t_daycarrental.`day`)   ),'%Y-%m-%d') < '"+endTime+"' ";
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

}
