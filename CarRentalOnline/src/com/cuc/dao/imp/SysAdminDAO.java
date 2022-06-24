package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.ISysAdminDAO;
import com.cuc.model.SysAdmin;
import com.cuc.util.DBUtil;
import com.cuc.util.SQLUtil;

public class SysAdminDAO implements ISysAdminDAO {

	public boolean addSysAdmin(SysAdmin admin) {
		String sql = "insert into t_sysadmin(sno,spassword,sname,sphone,saddress) values(?,?,?,?,?)";
		Object[] paramArray = new Object[5];
		paramArray[0] = admin.getSNo();
		paramArray[1] = admin.getSPassword();
		paramArray[2] = admin.getSName();
		paramArray[3] = admin.getSPhone();
		paramArray[4] = admin.getSAddress();
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public SysAdmin sysAdminLogin(String adminNo, String adminPassword) {
		SysAdmin admin = new SysAdmin();
		String sql = "select sid,sno,spassword,sname,sphone,saddress from t_sysadmin where sno=? and spassword=?";
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, adminNo);
			pstm.setString(2, adminPassword);
			rs = pstm.executeQuery();
			while (rs.next()) {
				admin.setSId(rs.getInt("sid"));
				admin.setSNo(rs.getString("sno"));
				admin.setSPassword(rs.getString("spassword"));
				admin.setSName(rs.getString("sname"));
				admin.setSPhone(rs.getString("sphone"));
				admin.setSAddress(rs.getString("saddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.getInstance().close(con, pstm, rs);
		}

		return admin;
	}

	public SysAdmin getBySysAdminId(int adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateSysAdmin(SysAdmin admin) {

		String sql = "update t_sysadmin set sno=?,spassword=?,sname=?,sphone=?,saddress=? where sid=?";

		Object[] paramArray = new Object[6];
		paramArray[0] = admin.getSNo();
		paramArray[1] = admin.getSPassword();
		paramArray[2] = admin.getSName();
		paramArray[3] = admin.getSPhone();
		paramArray[4] = admin.getSAddress();
		paramArray[5] = admin.getSId();

		return SQLUtil.getInstance().update(sql, paramArray);

	}

	public ArrayList<String[]> searchAllSysAdmin() {
		String sql = "select * from t_sysadmin";

		return SQLUtil.getInstance().search(sql);
	}

	public boolean delSysAdminById(int adminId) {
		String sql = "delete from t_sysadmin where sid=?";
		Object[] paramArray = new Object[1];
		paramArray[0] = adminId;
		return SQLUtil.getInstance().update(sql, paramArray);
	}

}
