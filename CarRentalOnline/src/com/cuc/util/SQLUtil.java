package com.cuc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLUtil {
	private static SQLUtil instance = null;

	public static synchronized SQLUtil getInstance() {
		if (instance == null) {
			instance = new SQLUtil();
		}
		return instance;
	}

	/**
	 * 预处理，实现数据库增、删、改
	 * 
	 * @param sql
	 * @param paramArray
	 * @return
	 * @throws SQLException
	 */
	public boolean update(String sql, Object[] paramArray) {

		boolean update = true;

		Connection con = DBUtil.getInstance().getConnection();

		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			if (paramArray != null) {
				for (int i = 0; i < paramArray.length; i++) {
					pstm.setObject(i + 1, paramArray[i]);
				}
			}
			pstm.executeUpdate();
		} catch (SQLException e) {
			update = false;
		} finally {
			DBUtil.getInstance().close(con, pstm, null);
		}
		return update;
	}

	/**
	 * 批量预处理，实现数据库批量的增、删、改
	 * 
	 * @param sql
	 * @param paramArray
	 * @return
	 */
	public boolean update(String sql, ArrayList<Object[]> paramArrayList)
			throws SQLException {

		boolean update = true;

		Connection con = DBUtil.getInstance().getConnection();

		con.setAutoCommit(false);// 取消自动提交

		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			if (paramArrayList != null) {// Object[] paramArray : paramArrayList
				for (int j = 0; j < paramArrayList.size(); j++) {
					Object[] paramArray = paramArrayList.get(j);
					
					for (int i = 0; i < paramArray.length; i++) {
						pstm.setObject(i + 1, paramArray[i]);
					}
					pstm.addBatch();
					
					if (j % 10000 == 0) {// 每1w条记录插入一次
						pstm.executeBatch();
						con.commit();
					}
				}
				// 最后插入不足1w条的数据
				pstm.executeBatch();
				con.commit();
			}
		} catch (SQLException e) {
			update = false;
		} finally {
			DBUtil.getInstance().close(con, pstm, null);
		}
		return update;
	}

	/**
	 * 改造之后的预处理，实现数据库修改
	 * 
	 * @param table
	 *            数据表
	 * @param condition
	 *            要修改的字段
	 * @param where
	 *            条件
	 * @param paramArray
	 *            传入的参数
	 * @return
	 */
	public boolean update(String table, String condition, String where,
			Object[] paramArray) {

		String sql = "update " + table + " set " + condition + " where "
				+ where;

		boolean update = true;

		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			if (paramArray != null) {
				for (int i = 0; i < paramArray.length; i++) {
					pstm.setObject(i + 1, paramArray[i]);
				}
			}
			pstm.executeUpdate();
		} catch (SQLException e) {
			update = false;
		} finally {
			DBUtil.getInstance().close(con, pstm, null);
		}
		return update;
	}
	
	/**
	 * 在JAVA中查询刚插入的记录ID 利用JDBC的getGeneratedKeys获得INSERT插入后生成的主键ID
	 * 
	 * @param sql
	 * @return
	 */
	public int insertGetId(String sql, Object[] paramArray) {

		Connection conn = DBUtil.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int retId = 0;
		try {
			preparedStatement = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			if (paramArray != null) {
				for (int i = 0; i < paramArray.length; i++) {
					preparedStatement.setObject(i + 1, paramArray[i]);
				}
			}
			preparedStatement.executeUpdate();

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {

				retId = rs.getInt(1);
			}

		} catch (SQLException e) {
			return retId;
		} finally {
			DBUtil.getInstance().close(conn, preparedStatement, rs);
		}
		return retId;

	}


	/**
	 * 登录验证
	 * 
	 * @param sql
	 * @return
	 */
	public String login(String sql) {
		String login = "";

		Connection con = DBUtil.getInstance().getConnection();
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				login = "欢迎光临！";
			} else {
				login = "帐号、密码或身份错误！";
			}
		} catch (SQLException e) {
			login = "数据库访问异常！";
		} finally {
			DBUtil.getInstance().close(con, stm, rs);
		}
		return login;
	}

	/**
	 * 查询多条记录，用于表格显示数据
	 * 
	 * @param sql
	 * @return
	 */
	public ArrayList<String[]> search(String sql) {
		ArrayList<String[]> search = new ArrayList<String[]>();

		Connection con = DBUtil.getInstance().getConnection();
		Statement stm = null;
		ResultSet rs = null;

		String[] content = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				content = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					// if (rs.getMetaData().getColumnType(i) == 93) {
					// content[i - 1] = rs.getDate(i).toString();
					// } else {
					// content[i - 1] = rs.getString(i);
					// }
					content[i - 1] = rs.getString(i);
				}
				search.add(content);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			DBUtil.getInstance().close(con, stm, rs);
		}
		return search;
	}

	/**
	 * 取字段名
	 * 
	 * @param sql
	 * @return
	 */
	public ArrayList<String> getColumnName(String sql) {
		ArrayList<String> columnName = new ArrayList<String>();

		Connection con = DBUtil.getInstance().getConnection();
		Statement stm = null;
		ResultSet rs = null;

		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();// 获取元数据
			int column = rsmd.getColumnCount();
			for (int i = 1; i <= column; i++) {
				columnName.add(rsmd.getColumnName(i).toString());
			}
		} catch (SQLException e) {
			return null;
		} finally {
			DBUtil.getInstance().close(con, stm, rs);
		}
		return columnName;
	}

	/**
	 * 取单个对象的所有属性值
	 * 
	 * @param sql
	 * @return
	 */
	public Object[] getSingleRow(String sql) {
		Object obj[] = null;

		Connection con = DBUtil.getInstance().getConnection();
		Statement stm = null;
		ResultSet rs = null;

		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			if (rs.next()) {
				obj = new Object[colCount];
				for (int i = 1; i <= colCount; i++) {
					obj[i - 1] = rs.getObject(i);
				}
			}
		} catch (SQLException e) {
			return null;
		} finally {
			DBUtil.getInstance().close(con, stm, rs);
		}
		return obj;
	}

	/**
	 * 取单个对象
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> getSingleObject(String sql) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<String> columnName = getColumnName(sql);
		Object obj[] = getSingleRow(sql);
		if (columnName == null || obj == null) {
			return null;
		}
		for (int i = 0; i < columnName.size(); i++) {
			map.put(columnName.get(i), obj[i]);
		}
		return map;
	}

}
