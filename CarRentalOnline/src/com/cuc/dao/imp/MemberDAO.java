package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IMemberDAO;
import com.cuc.model.Member;
import com.cuc.util.DBUtil;
import com.cuc.util.SQLUtil;

public class MemberDAO implements IMemberDAO {

	public Member getByMemberId(int memberId) {

		return null;
	}

	public Member memberLogin(String memberNo, String memberPassowrd) {
		Member member = new Member();

		Connection con = DBUtil.getInstance().getConnection();
		String sql = "select * from t_member where memberNo=? and memberPassword=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			pstmt.setString(2, memberPassowrd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member.setMemberId(rs.getInt("memberId"));
				member.setMemberNo(rs.getString("memberNo"));
				member.setMemberPassword(rs.getString("memberPassword"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberSex(rs.getString("memberSex"));
				member.setMemberPhone(rs.getString("memberPhone"));
				member.setMemberImage(rs.getString("memberImage"));
			}
		} catch (SQLException e) {
			return null;
		} finally {
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return member;
	}

	public ArrayList<String[]> pageSearchAllMember(int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = "select * from t_member order by registerTime desc limit "
				+ (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public boolean updateMember(Member member) {
		String sql = "update t_member set memberName=?,memberSex=?,memberPhone=?,memberImage=? where memberId=?";
		Object[] paramArray = new Object[5];
		paramArray[0] = member.getMemberName();
		paramArray[1] = member.getMemberSex();
		paramArray[2] = member.getMemberPhone();
		paramArray[3] = member.getMemberImage();
		paramArray[4] = member.getMemberId();
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean addMember(Member member) {
		String sql = "insert into t_member(memberNo,memberPassword,memberName,memberPhone,registerTime) values(?,?,?,?,now())";
		Object[] paramArray = new Object[4];
		paramArray[0] = member.getMemberNo();
		paramArray[1] = member.getMemberPassword();
		paramArray[2] = member.getMemberName();
		paramArray[3] = member.getMemberPhone();
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean changePassword(int memberId, String newPassword) {
		String sql = "update t_member set memberPassword=? where memberId=?";
		Object[] paramArray = new Object[2];
		paramArray[0] = newPassword;
		paramArray[1] = memberId;
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean isSameMemberNo(String memberNo) {
		
		String sql = "select * from t_member where memberNo=?";
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return false;
	}

	public Object[] getMemberCount() {

		String sql = "select count(*) from t_member";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public Object[] getDayRegCount(String date) {

		String sql = " SELECT COUNT(*) " + " FROM t_member "
				+ " WHERE t_member.registerTime LIKE '" + date + "%' ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public Object[] getMonthRegCount(String year, String month) {

		if (month.length() < 2) {
			month = "0" + month;
		}

		String sql = " SELECT COUNT(*) " + " FROM t_member "
				+ " WHERE t_member.registerTime LIKE '" + year + "-" + month
				+ "%'";
		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public Object[] getMemberByNo(String memberNo) {

		String sql = "select * from t_member where memberNo=" + memberNo;

		return SQLUtil.getInstance().getSingleRow(sql);
	}
}
