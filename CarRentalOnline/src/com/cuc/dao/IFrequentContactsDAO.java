package com.cuc.dao;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.model.FrequentContacts;

public interface IFrequentContactsDAO {
	/**
	 * 租赁会员用户添加自己的常用联系人信息(租车订单时候可以直接选择常用联系人，不用手动再输入)
	 * 
	 * @param contacts
	 *            常用联系人信息
	 * @return 返回是否成功添加常用联系人信息
	 */
	public boolean addFrequentContacts(FrequentContacts contacts);

	/**
	 * 通过租赁会员编号获取该租赁会员的所有常用联系人信息
	 * 
	 * @param memberId
	 *            租赁会员编号
	 * @return 返回该租赁会员的所有常用联系人信息
	 */
	public ArrayList<FrequentContacts> getByMemberId(int memberId);

	/**
	 * 租赁会员用户修改某个常用联系人信息
	 * 
	 * @param contacts
	 *            常用联系人信息
	 * @return 是否成功修改常用联系人信息
	 */
	public boolean updateFrequentContacts(FrequentContacts contacts);

	/**
	 * 租赁会员用户通过常用联系人编号获取该常用联系人信息
	 * 
	 * @param frequentId
	 *            常用联系人编号
	 * @return 返回该常用联系人编号的常用联系人信息
	 */
	public boolean deleteByFrequentId(int frequentId);

	public Map<String, Object> getFrequentByFrequentId(int frequentId);

}
