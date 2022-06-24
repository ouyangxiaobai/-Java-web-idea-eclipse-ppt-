package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.cuc.dao.IFrequentContactsDAO;
import com.cuc.model.FrequentContacts;
import com.cuc.util.DBUtil;
import com.cuc.util.SQLUtil;

public class FrequentContactsDAO implements IFrequentContactsDAO {

	public boolean addFrequentContacts(FrequentContacts contacts) {
		String sql = "insert into t_frequentcontacts(memberId,frequentName,frequentPhone,idType,Identity,frequentProvince,frequentCity,frequentAddresss) values(?,?,?,?,?,?,?,?)";
		Object[] paramArray = new Object[8];
		paramArray[0] = contacts.getMemberId();
		paramArray[1] = contacts.getFrequentName();
		paramArray[2] = contacts.getFrequentPhone();
		paramArray[3] = contacts.getIdType();
		paramArray[4] = contacts.getIdentity();
		paramArray[5] = contacts.getFrequentProvince();
		paramArray[6] = contacts.getFrequentCity();
		paramArray[7] = contacts.getFrequentAddresss();
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean deleteByFrequentId(int frequentId) {
		String sql = "delete from t_frequentcontacts where frequentId=?";
		Object[] paramArray = new Object[1];
		paramArray[0] = frequentId;
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean updateFrequentContacts(FrequentContacts contacts) {
		String sql = "update t_frequentcontacts set frequentName=?,frequentPhone=?,idType=?,Identity=?,frequentProvince=?,frequentCity=?,frequentAddresss=? where frequentId=?";
		Object[] paramArray = new Object[8];
		paramArray[0] = contacts.getFrequentName();
		paramArray[1] = contacts.getFrequentPhone();
		paramArray[2] = contacts.getIdType();
		paramArray[3] = contacts.getIdentity();
		paramArray[4] = contacts.getFrequentProvince();
		paramArray[5] = contacts.getFrequentCity();
		paramArray[6] = contacts.getFrequentAddresss();
		paramArray[7] = contacts.getFrequentId();
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<FrequentContacts> getByMemberId(int memberId) {

		ArrayList<FrequentContacts> fcList = new ArrayList<FrequentContacts>();

		Connection con = DBUtil.getInstance().getConnection();
		String sql = "select * from t_frequentcontacts where memberId=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FrequentContacts fc = new FrequentContacts();
				fc.setFrequentId(rs.getInt("frequentId"));
				fc.setMemberId(rs.getInt("memberId"));
				fc.setFrequentName(rs.getString("frequentName"));
				fc.setFrequentPhone(rs.getString("frequentPhone"));
				fc.setIdType(rs.getString("idType"));
				fc.setIdentity(rs.getString("Identity"));
				fc.setFrequentProvince(rs.getString("frequentProvince"));
				fc.setFrequentCity(rs.getString("frequentCity"));
				fc.setFrequentAddresss(rs.getString("frequentAddresss"));
				fcList.add(fc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return fcList;
	}

	public Map<String, Object> getFrequentByFrequentId(int frequentId) {

		String sql = "select * from t_frequentcontacts where frequentId="
				+ frequentId;

		return SQLUtil.getInstance().getSingleObject(sql);
	}

}
