package com.cuc.dao.imp;

import java.util.ArrayList;

import com.cuc.dao.IBaseDAO;
import com.cuc.util.SQLUtil;

public class BaseDAO implements IBaseDAO {

	public boolean delete(String table, String where, Object[] paramArray) {

		String sql = "delete from " + table + "where " + where;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean insert(String table, String columnName, Object[] paramArray) {

		int size = paramArray.length;

		String sql = "insert into" + table + "(" + columnName + ") values(";

		for (int i = 0; i < size; i++) {
			sql = sql + "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> search(String table, String where) {

		String sql = "select * from " + table + " where " + where;

		return SQLUtil.getInstance().search(sql);
	}

	public boolean update(String table, String condition, String where,
			Object[] paramArray) {

		String sql = "update " + table + " set " + condition + " where "
				+ where;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

}
