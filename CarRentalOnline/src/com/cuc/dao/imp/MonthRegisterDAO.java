package com.cuc.dao.imp;

import com.cuc.dao.IMonthRegisterDAO;
import com.cuc.model.MonthRegister;
import com.cuc.util.SQLUtil;

public class MonthRegisterDAO implements IMonthRegisterDAO {

	public boolean insert(MonthRegister register) {
		
		String sql = "insert into t_monthregister(year,month,regCount) values(?,?,?)";

		Object[] paramArray = new Object[3];
		paramArray[0] = register.getYear();
		paramArray[1] = register.getMonth();
		paramArray[2] = register.getRegCount();

		return SQLUtil.getInstance().update(sql, paramArray);

	}

}
