package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IMessageDAO;
import com.cuc.model.Message;
import com.cuc.util.DBUtil;
import com.cuc.util.SQLUtil;

public class MessageDAO implements IMessageDAO {

	public boolean addMessage(Message message) {
		String sql = "insert into t_message(memberId,messageContent,messageDate,messageImage,state) values(?,?,now(),?,?)";

		Object[] paramArray = new Object[4];
		paramArray[0] = message.getMemberId();
		paramArray[1] = message.getMessageContent();
		paramArray[2] = message.getMessageImage();
		paramArray[3] = message.getState();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean deleteByMessageId(int messageId) {
		String sql = "delete from t_message where messageId=?";

		Object[] paramArray = new Object[1];
		paramArray[0] = messageId;
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> searchAllMessageByState(int state, int pageSize,
			int currentPage) {
		int start = (currentPage - 1) * pageSize + 1;
		// int end = currentPage * pageSize;
		String sql = "SELECT t_message.messageId,t_member.memberId, t_member.memberNo, t_member.memberPhone, t_member.memberName, t_message.messageContent, t_message.messageDate, t_message.messageImage"
				+ " FROM t_member, t_message"
				+ " WHERE t_member.memberId = t_message.memberId"
				+ " AND t_message.state = "
				+ state
				+ " ORDER BY t_message.messageDate DESC"
				+ " limit "
				+ (start - 1) + "," + pageSize;

		ArrayList<String[]> list = SQLUtil.getInstance().search(sql);
		return list;
	}

	public boolean updateMessageState(int messageId, int state) {
		String sql = "update t_message set state=? where messageId=?";

		Object[] paramArray = new Object[2];
		paramArray[0] = state;
		paramArray[1] = messageId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<Message> getMessageByMemberId(int memberId, int state) {
		ArrayList<Message> messageList = new ArrayList<Message>();
		String sql = "select * from t_message where memberId=? and state<?";
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, state);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message m = new Message();
				m.setMessageId(rs.getInt("messageId"));
				m.setMemberId(memberId);
				m.setMessageContent(rs.getString("messageContent"));
				m.setMessageDate(rs.getString("messageDate"));
				m.setState(rs.getInt("state"));
				m.setMessageImage(rs.getString("messageImage"));
				messageList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return messageList;
	}

	public int getStateCount(int state) {
		int count = 0;
		String sql = "select count(*) as rscount from t_message where state=?";
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, state);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("rscount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<String[]> searchAllMessage(int pageSize, int currentPage) {

		System.out.println("SQL searchAllMessage->pageSize:" + pageSize
				+ "currentPage:" + currentPage);

		int start = (currentPage - 1) * pageSize + 1;
		
		System.out.println("start:" + start);

		String sql = "select * from t_message order by t_message.state asc limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}
}
