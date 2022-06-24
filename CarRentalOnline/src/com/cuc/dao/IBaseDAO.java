package com.cuc.dao;

import java.util.ArrayList;

public interface IBaseDAO {

	public ArrayList<String[]> search(String table, String where);

	/**
	 * @see delete(t_user, id=?, Object[] paramArray)
	 * @see delete from t_user where id=?
	 * 
	 * @param table
	 *            t_user
	 * @param where
	 *            id=?
	 * @param paramArray
	 * @return
	 */
	public boolean delete(String table, String where, Object[] paramArray);

	public boolean update(String table, String condition, String where,
			Object[] paramArray);

	public boolean insert(String table, String columnName, Object[] paramArray);

}
