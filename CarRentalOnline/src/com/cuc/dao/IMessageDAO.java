package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Message;

public interface IMessageDAO {
	/**
	 * 租赁会员用户添加体验留言信息
	 * 
	 * @param message
	 *            留言信息
	 * @return 是否成功添加留言信息
	 */
	public boolean addMessage(Message message);

	/**
	 * 企业管理员通过留言编号删除留言信息
	 * 
	 * @param messageId
	 *            留言编号
	 * @return 是否成功删除留言编号信息
	 */
	public boolean deleteByMessageId(int messageId);

	/**
	 * 查询所有留言信息(留言表跟租赁会员表联合查询)
	 * 
	 * @return 返回留言内容信息以及包括发布该条留言的租赁会员用户的账号信息
	 */
	public ArrayList<String[]> searchAllMessage(int pageSize,
			int currentPage);

	/**
	 * 更改留言状态(企业系统管理人员审核留言)
	 * 
	 * @param messageId
	 * @param state
	 * @return
	 */
	public boolean updateMessageState(int messageId, int state);

	/**
	 * 如果要查询该会员所有留言信息，state 传入大于2的值；如果要查询待审核留言,state传入1
	 * 
	 * @param memberId
	 *            会员编号
	 * @param state
	 *            留言审核状态
	 * @return
	 */
	public ArrayList<Message> getMessageByMemberId(int memberId, int state);

	/**
	 * 获取该状态下的所有留言信息,并分页显示
	 * 
	 * @param state
	 *            状态
	 * @param pageSize
	 *            分页大小
	 * @param currentPage
	 *            当前页数
	 * @return
	 */
	public ArrayList<String[]> searchAllMessageByState(int state, int pageSize,
			int currentPage);

	/**
	 * 获取状态为state（0,1,2）的留言的总条数
	 * 
	 * @param state
	 * @return
	 */
	public int getStateCount(int state);
}
