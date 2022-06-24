package com.cuc.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IMonthCarRentalDAO;
import com.cuc.model.MonthCarRental;
import com.cuc.util.SQLUtil;

public class MonthCarRentalDAO implements IMonthCarRentalDAO {

	public boolean insert(ArrayList<MonthCarRental> monthCarRentalList) {

		boolean status = false;

		String sql = "insert into t_monthCarRental(carId,year,month,rentalNum) values(?,?,?,?)";

		ArrayList<Object[]> paramArrayList = new ArrayList<Object[]>();
		for (int i = 0; i < monthCarRentalList.size(); i++) {
			MonthCarRental monthCarRental = monthCarRentalList.get(i);

			Object[] objs = new Object[4];
			objs[0] = monthCarRental.getCarId();
			objs[1] = monthCarRental.getYear();
			objs[2] = monthCarRental.getMonth();
			objs[3] = monthCarRental.getRentalNum();

			paramArrayList.add(objs);
		}

		try {
			status = SQLUtil.getInstance().update(sql, paramArrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
		
	}

	public ArrayList<String[]> getMonthCarRentalByCondition(String beginYear,
			String beginMonth, String endYear, String endMonth, int pageSize,
			int currentPage) {

		int start = (currentPage-1)*pageSize+1;
		
		String sql=" SELECT "+
					" t_daycarrental.carId,t_daycarrental.`year`,t_daycarrental.`month`, SUM(rentalNum) as sumrentalNum "+
					" FROM "+
					"	t_daycarrental "+
					" WHERE "+
					"	CONCAT( "+
					"		t_daycarrental.`year`, "+
					"	IF ( "+
					"		LENGTH(t_daycarrental.`month`) = 1, "+
					"		CONCAT(0, t_daycarrental.`month`), "+
					"		t_daycarrental.`month` "+
					"	) "+
					"	) >= '"+beginYear+beginMonth+"' "+
					" AND CONCAT( "+
					"	t_daycarrental.`year`, "+
					" IF ( "+
					"	LENGTH(t_daycarrental.`month`) = 1, "+
					"   CONCAT(0, t_daycarrental.`month`), "+
					"	t_daycarrental.`month` "+
					" ) "+
					" ) < '"+endYear+endMonth+"'"+
					" GROUP BY t_daycarrental.carId,t_daycarrental.`year`,t_daycarrental.`month`"+
					" ORDER BY t_daycarrental.`year` DESC,t_daycarrental.`month` DESC,t_daycarrental.rentalNum DESC"+
					" limit " + (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getMonthCarRentalRsCountByCondition(String beginYear,
			String beginMonth, String endYear, String endMonth) {
		
		String sql=	" SELECT "+
					" COUNT(A.carId) "+
					" FROM "+
					"	( "+
						" SELECT "+
						" t_daycarrental.carId,t_daycarrental.`year`,t_daycarrental.`month`, SUM(rentalNum) as sumrentalNum "+
						" FROM "+
						"	t_daycarrental "+
						" WHERE "+
						"	CONCAT( "+
						"		t_daycarrental.`year`, "+
						"	IF ( "+
						"		LENGTH(t_daycarrental.`month`) = 1, "+
						"		CONCAT(0, t_daycarrental.`month`), "+
						"		t_daycarrental.`month` "+
						"	) "+
						"	) >= '"+beginYear+beginMonth+"' "+
						" AND CONCAT( "+
						"	t_daycarrental.`year`, "+
						" IF ( "+
						"	LENGTH(t_daycarrental.`month`) = 1, "+
						"   CONCAT(0, t_daycarrental.`month`), "+
						"	t_daycarrental.`month` "+
						" ) "+
						" ) < '"+endYear+endMonth+"'"+
						" GROUP BY t_daycarrental.carId,t_daycarrental.`year`,t_daycarrental.`month`"+
						" ORDER BY t_daycarrental.`year` DESC,t_daycarrental.`month` DESC,t_daycarrental.rentalNum DESC"+
						")A";
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

}
