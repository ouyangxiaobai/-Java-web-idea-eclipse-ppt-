package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Insurance;

public interface IInsuranceDAO {
	/**
	 * 企业管理员添加保险信息
	 * 
	 * @param insurance
	 *            保险信息
	 * @return 返回是否成功添加保险信息
	 */
	public boolean addInsurance(Insurance insurance);

	/**
	 * 企业管理员通过保险编号删除保险信息
	 * 
	 * @param InsuranceId
	 *            保险编号
	 * @return 返回是否成功删除保险信息
	 */
	public boolean deleteByInsuranceId(int InsuranceId);

	/**
	 * 企业管理员修改保险信息
	 * 
	 * @param insurance
	 *            保险信息
	 * @return 返回是否成功修改保险信息
	 */
	public boolean updateInsurance(Insurance insurance);

	/**
	 * 企业管理员通过编号保险编号获取该保险信息
	 * 
	 * @param InsuranceId
	 *            保险编号
	 * @return 返回该保险编号的保险信息
	 */
	public Insurance getByInsuranceId(int InsuranceId);

	/**
	 * 获取所有租赁保险信息
	 * 
	 * @return
	 */
	public ArrayList<String[]> searchAllInsurance();

	/**
	 * 更改租赁保险信息状态
	 * 
	 * @param InsuranceId
	 * @param state
	 * @return
	 */
	public boolean changeInsuranceState(int InsuranceId, int state);
	
	/**查找某状态下的所有保险信息
	 * @param state
	 * @return
	 */
	public ArrayList<String[]> searchInsuranceByState(int state);

}
