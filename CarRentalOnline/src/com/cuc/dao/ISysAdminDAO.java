package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.SysAdmin;

public interface ISysAdminDAO {
	/**
	 * 企业管理员注册管理账号
	 * 
	 * @param admin
	 *            管理员信息
	 * @return 是否成功添加企业管理员信息
	 */
	public boolean addSysAdmin(SysAdmin admin);

	/**
	 * 企业管理员登录
	 * 
	 * @param adminNo
	 *            企业管理员账号
	 * @param adminPassword
	 *            企业管理员密码
	 * @return 企业管理员信息
	 */
	public SysAdmin sysAdminLogin(String adminNo, String adminPassword);

	/**
	 * 通过企业管理员编号获取管理员信息
	 * 
	 * @param adminId
	 *            企业管理员编号
	 * @return 企业管理员信息
	 */
	public SysAdmin getBySysAdminId(int adminId);

	/**
	 * 更新企业管理员信息
	 * 
	 * @param admin
	 *            企业管理员信息
	 * @return 是否成功更新企业管理员信息
	 */
	public boolean updateSysAdmin(SysAdmin admin);

	/**
	 * 获取所有企业管理员信息
	 * 
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> searchAllSysAdmin();

	/**
	 * 根据编号删除企业管理员信息
	 * 
	 * @param adminId
	 * @return boolean
	 */
	public boolean delSysAdminById(int adminId);
	
}
