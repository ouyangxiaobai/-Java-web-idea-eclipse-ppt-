package com.cuc.model;

public class FrequentContacts {
	private int frequentId;// 常用联系人编号
	private int memberId;// 会员编号
	private String frequentName;// 常用联系人姓名
	private String frequentPhone;// 电话
	private String idType;// 证件类型
	private String Identity;// 证件号码
	private String frequentProvince;// 省份
	private String frequentCity;// 城市
	private String frequentAddresss;// 具体地址

	public int getFrequentId() {
		return frequentId;
	}

	public void setFrequentId(int frequentId) {
		this.frequentId = frequentId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFrequentName() {
		return frequentName;
	}

	public void setFrequentName(String frequentName) {
		this.frequentName = frequentName;
	}

	public String getFrequentPhone() {
		return frequentPhone;
	}

	public void setFrequentPhone(String frequentPhone) {
		this.frequentPhone = frequentPhone;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdentity() {
		return Identity;
	}

	public void setIdentity(String identity) {
		Identity = identity;
	}

	public String getFrequentProvince() {
		return frequentProvince;
	}

	public void setFrequentProvince(String frequentProvince) {
		this.frequentProvince = frequentProvince;
	}

	public String getFrequentCity() {
		return frequentCity;
	}

	public void setFrequentCity(String frequentCity) {
		this.frequentCity = frequentCity;
	}

	public String getFrequentAddresss() {
		return frequentAddresss;
	}

	public void setFrequentAddresss(String frequentAddresss) {
		this.frequentAddresss = frequentAddresss;
	}

}
