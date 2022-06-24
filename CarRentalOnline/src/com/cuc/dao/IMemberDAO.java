package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Member;

public interface IMemberDAO {
	/**
	 * 租赁会员登录
	 * 
	 * @param memberNo
	 *            账号
	 * @param memberPassowrd
	 *            密码
	 * @return 登录会员的所有信息
	 */
	public Member memberLogin(String memberNo, String memberPassowrd);

	/**
	 * 判断是否用户名已经注册过
	 * 
	 * @param memberNo
	 *            要注册的账号用户名
	 * @return 是否已经存在该用户名账号
	 */
	public boolean isSameMemberNo(String memberNo);

	/**
	 * 租赁会员修改个人信息
	 * 
	 * @param member
	 *            会员信息
	 * @return 是否修改成功
	 */
	public boolean updateMember(Member member);

	/**
	 * 通过会员编号获取该会员信息
	 * 
	 * @param memberId
	 *            会员编号
	 * @return 会员信息
	 */
	public Member getByMemberId(int memberId);

	/**
	 * 会员信息注册
	 * 
	 * @param member
	 *            会员信息
	 * @return 返回是否添加会员信息成功
	 */
	public boolean addMember(Member member);
	
	public Object[] getMemberByNo(String memberNo);

	/**
	 * 租赁会员用户修改个人账号密码
	 * 
	 * @param memberId
	 *            租赁会员编号
	 * @param newPassword
	 *            新密码
	 * @return 是否成功修改个人账号密码
	 */
	public boolean changePassword(int memberId, String newPassword);

	/**
	 * 分页获取会员用户信息
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> pageSearchAllMember(int pageSize, int currentPage);

	/**
	 * 获取会员信息总条数
	 * 
	 * @return Object[]
	 */
	public Object[] getMemberCount();

	/**
	 * 获取date日期的日注册用户量
	 * 
	 * @param date
	 * @return
	 */
	public Object[] getDayRegCount(String date);
	
	/** 获取year\month的月注册用户量
	 * @param year
	 * @param month
	 * @return
	 */
	public Object[] getMonthRegCount(String year,String month);
	
	

	
	
	
	
}
