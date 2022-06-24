package com.cuc.dao.imp;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.cuc.dao.IBusinessStoreDAO;
import com.cuc.model.BusinessStore;
import com.cuc.util.DBUtil;
import com.cuc.util.SQLUtil;

public class BusinessStoreDAO implements IBusinessStoreDAO {

	public boolean addBusinessStore(BusinessStore store) {
		String sql = "insert into t_businessstore(storeNo,storePassword,storeName,storeImage,storePhone,storeProvince,storeCity,storeDistrict,storeAddress,state) values(?,?,?,?,?,?,?,?,?,?)";

		Object[] paramArray = new Object[10];
		paramArray[0] = store.getStoreNo();
		paramArray[1] = store.getStorePassword();
		paramArray[2] = store.getStoreName();
		paramArray[3] = store.getStoreImage();
		paramArray[4] = store.getStorePhone();
		paramArray[5] = store.getStoreProvince();
		paramArray[6] = store.getStoreCity();
		paramArray[7] = store.getStoreDistrict();
		paramArray[8] = store.getStoreAddress();
		paramArray[9] = store.getState();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public BusinessStore getByBusinessStoreName(String storeName) {

		String sql = "select storeId,storeNo,storePassword,storeName,storeImage,storePhone,storeProvince,storeCity,storeDistrict,storeAddress,state from t_businessstore where storeName='"
				+ storeName + "'";

		System.out.println(sql);

		Map<String, Object> map = null;
		map = SQLUtil.getInstance().getSingleObject(sql);

		BusinessStore store = new BusinessStore();
		store.setStoreId(Integer.parseInt(map.get("storeId").toString()));
		store.setStoreNo((String) map.get("storeNo"));
		store.setStorePassword((String) map.get("storePassword"));
		store.setStoreName((String) map.get("storeName"));
		store.setStoreImage((String) map.get("storeImage"));
		store.setStorePhone((String) map.get("storePhone"));
		store.setStoreProvince((String) map.get("storeProvince"));
		store.setStoreCity((String) map.get("storeCity"));
		store.setStoreDistrict((String) map.get("storeDistrict"));
		store.setStoreAddress((String) map.get("storeAddress"));
		store.setState(Integer.parseInt(map.get("state").toString()));
		return store;
	}

	public ArrayList<String[]> searchAllBusinessStore() {

		String sql = "select * from t_businessstore";

		return SQLUtil.getInstance().search(sql);
	}

	public boolean updateBusinessStore(BusinessStore store) {
		String sql = "update t_businessstore set storeNo=?,storeName=?,storeImage=?,storePhone=?,storeProvince=?,storeCity=?,storeDistrict=?,storeAddress=?,state=? where storeId=?";

		Object[] paramArray = new Object[10];
		paramArray[0] = store.getStoreNo();
		paramArray[1] = store.getStoreName();
		paramArray[2] = store.getStoreImage();
		paramArray[3] = store.getStorePhone();
		paramArray[4] = store.getStoreProvince();
		paramArray[5] = store.getStoreCity();
		paramArray[6] = store.getStoreDistrict();
		paramArray[7] = store.getStoreAddress();
		paramArray[8] = store.getState();
		paramArray[9] = store.getStoreId();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public BusinessStore businessStoreLogin(String storeNo, String storePassword) {
		BusinessStore store = new BusinessStore();
		String sql = "select storeId,storeNo,storePassword,storeName,storeImage,storePhone,storeProvince,storeCity,storeDistrict,storeAddress,state from t_businessstore where storeNo=? and storePassword=?";
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, storeNo);
			pstm.setString(2, storePassword);
			rs = pstm.executeQuery();
			while (rs.next()) {
				store.setStoreId(rs.getInt("storeId"));
				store.setStoreNo(rs.getString("storeNo"));
				store.setStorePassword(rs.getString("storePassword"));
				store.setStoreName(rs.getString("storeName"));
				store.setStoreImage(rs.getString("storeImage"));
				store.setStorePhone(rs.getString("storePhone"));
				store.setStoreProvince(rs.getString("storeProvince"));
				store.setStoreCity(rs.getString("storeCity"));
				store.setStoreDistrict(rs.getString("storeDistrict"));
				store.setStoreAddress(rs.getString("storeAddress"));
				store.setState(rs.getInt("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.getInstance().close(con, pstm, rs);
		}

		return store;
	}

	public BusinessStore getByBusinessStoreId(int storeId) {

		String sql = "select storeId,storeNo,storePassword,storeName,storeImage,storePhone,storeProvince,storeCity,storeDistrict,storeAddress,state from t_businessstore where storeId="
				+ storeId;

		Map<String, Object> map = null;
		map = SQLUtil.getInstance().getSingleObject(sql);

		BusinessStore store = new BusinessStore();
		store.setStoreId(storeId);
		store.setStoreNo((String) map.get("storeNo"));
		store.setStorePassword((String) map.get("storePassword"));
		store.setStoreName((String) map.get("storeName"));
		store.setStoreImage((String) map.get("storeImage"));
		store.setStorePhone((String) map.get("storePhone"));
		store.setStoreProvince((String) map.get("storeProvince"));
		store.setStoreCity((String) map.get("storeCity"));
		store.setStoreDistrict((String) map.get("storeDistrict"));
		store.setStoreAddress((String) map.get("storeAddress"));
		store.setState(Integer.parseInt(map.get("state").toString()));

		return store;
	}

	public ArrayList<String[]> getAllBusinessCity() {
		ArrayList<String[]> cityList = new ArrayList<String[]>();
		String sql = "select distinct t_businessstore.storeProvince,t_businessstore.storeCity from t_businessstore where state=1 order by t_businessstore.storeProvince desc";

		cityList = SQLUtil.getInstance().search(sql);
		return cityList;

	}

	public ArrayList<String[]> getBusinessNameByCity(String storeProvince,
			String storeCity) {

		String sql = "select storeName from t_businessstore where storeProvince="
				+ storeProvince
				+ " and storeCity="
				+ storeCity
				+ " and state = 1";
		System.out.println(sql);
		return SQLUtil.getInstance().search(sql);
	}

	public boolean changePassword(int storeId, String newPassword) {
		String sql = "update t_businessstore set storePassword=? where storeId=?";

		Object[] paramArray = new Object[2];
		paramArray[0] = newPassword;
		paramArray[1] = storeId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean updateStoreState(int id, int state) {

		String sql = "update t_businessstore set state=? where storeId=?";
		Object[] paramArray = new Object[2];
		paramArray[0] = state;
		paramArray[1] = id;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean isSomeNo(String no) {

		String sql = "select * from t_businessstore where storeNo='" + no+"'";

		Object[] obj = SQLUtil.getInstance().getSingleRow(sql);

		if (obj == null) {
			return false;
		} else {
			return true;
		}

	}

}
