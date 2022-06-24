package com.cuc.dao;

import java.util.ArrayList;

public interface IBrandDAO {

	/**
	 * 获取所有车辆品牌
	 * 
	 * @return
	 */
	public ArrayList<String[]> getAllBrand();

	/**
	 * 根据车辆品牌获取所有的车型
	 * 
	 * @param brand
	 * @return
	 */
	public ArrayList<String[]> getTypeByBrand(String brand);
}
