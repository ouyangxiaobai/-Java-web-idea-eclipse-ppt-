package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.BusinessStore;

public interface IBusinessStoreDAO {
	/**
	 * 添加营业门店
	 * 
	 * @param store
	 *            营业门店相关信息
	 * @return 是否添加营业门店成功
	 */
	public boolean addBusinessStore(BusinessStore store);

	/**
	 * 修改营业门店信息
	 * 
	 * @param store
	 *            营业门店相关信息
	 * @return 是否成功修改营业门店信息
	 */
	public boolean updateBusinessStore(BusinessStore store);

	/**
	 * 查询所有营业门店信息
	 * 
	 * @return 所有营业门店信息
	 */
	public ArrayList<String[]> searchAllBusinessStore();

	/**
	 * 通过营业门店名称获取该门店信息
	 * 
	 * @param storeName
	 *            营业门店名称
	 * @return 该营业门店名称的信息
	 */
	public BusinessStore getByBusinessStoreName(String storeName);

	/**
	 * 营业门店登录
	 * 
	 * @param storeNo
	 *            营业门店账号
	 * @param storePassword
	 *            账号密码
	 * @return 所登录的营业门店信息
	 */
	public BusinessStore businessStoreLogin(String storeNo, String storePassword);

	/**
	 * 通过营业门店编号获取该门店信息
	 * 
	 * @param storeId
	 *            营业门店编号
	 * @return 该营业门店信息
	 */
	public BusinessStore getByBusinessStoreId(int storeId);

	/**
	 * 获取数据库中所有门店省份城市(不重复)
	 * 
	 * @return ArrayList<String[]> 省份 城市
	 */
	public ArrayList<String[]> getAllBusinessCity();

	/**
	 * 根据所在城市获取所在营业门店名称--营业门店状态要 为1
	 * 
	 * @param storeProvince
	 *            营业门店省份
	 * @param storeCity
	 *            营业门店城市
	 * @return ArrayList<String[]>营业门店名称
	 */
	public ArrayList<String[]> getBusinessNameByCity(String storeProvince,
			String storeCity);

	/**
	 * 营业门店修改门店账号密码
	 * 
	 * @param storeId
	 *            营业门店编号
	 * @param newPassword
	 *            新密码
	 * @return boolean 是否成功修改
	 */
	public boolean changePassword(int storeId, String newPassword);

	/**
	 * 通过营业门店编号修改门店状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	public boolean updateStoreState(int id, int state);
	
	public boolean isSomeNo(String no);
}
