package com.cuc.model;

public class SysAdmin {
	private int sId;// 系统管理员编号
	private String sNo;// 系统管理员账号
	private String sPassword;// 密码
	private String sName;// 姓名
	private String sPhone;// 联系电话
	private String sAddress;// 地址

	public int getSId() {
		return sId;
	}

	public void setSId(int id) {
		sId = id;
	}

	public String getSNo() {
		return sNo;
	}

	public void setSNo(String no) {
		sNo = no;
	}

	public String getSPassword() {
		return sPassword;
	}

	public void setSPassword(String password) {
		sPassword = password;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String name) {
		sName = name;
	}

	public String getSPhone() {
		return sPhone;
	}

	public void setSPhone(String phone) {
		sPhone = phone;
	}

	public String getSAddress() {
		return sAddress;
	}

	public void setSAddress(String address) {
		sAddress = address;
	}

}
