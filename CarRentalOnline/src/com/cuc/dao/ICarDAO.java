package com.cuc.dao;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.model.Car;

public interface ICarDAO {
	/**
	 * 营业门店或企业管理员添加车辆信息
	 * 
	 * @param car
	 *            车辆信息
	 * @return 是否车辆信息添加成功
	 */
	public boolean addCar(Car car);

	/**
	 * 修改车辆信息
	 * 
	 * @param car
	 *            车辆信息
	 * @return 是否车辆信息修改成功
	 */
	public boolean updateCar(Car car);

	/**
	 * 通过车辆编号删除车辆信息
	 * 
	 * @param carId
	 *            车辆编号
	 * @return 是否车辆信息删除成功
	 */
	public boolean deleteByCarId(int carId);

	/**
	 * 营业门店分页查询门店所有车辆信息
	 * 
	 * @param storeId
	 *            营业门店编号
	 * @param pageSize
	 *            分页大小
	 * @param currentPage
	 *            当前页数
	 * @return ArrayList<String[]> 该页营业门店车辆信息
	 */
	public ArrayList<String[]> pageSearchByStoreId(int storeId, int pageSize,
			int currentPage);

	/**
	 * 获取该编号门店下的所有车辆数
	 * 
	 * @param storeId
	 *            营业门店编号
	 * @return Object[] 门店下的车辆数
	 */
	public Object[] getRSCountByStoreId(int storeId);

	/**
	 * 通过车辆编号获取车辆信息(修改时先调用获取该车辆信息)
	 * 
	 * @param carId
	 *            车辆编号
	 * @return 返回该车辆编号的车辆信息
	 */
	public Map<String, Object> searchByCarId(int carId);

	/**
	 * 租车用户在基于Java web的校园电动车租赁系统 1.查询营业门店为‘'XXXXXX'’的 -- 2.且租车日期在2016-05-01 10:00～2016-05-05
	 * 10:00的 3.可以租赁的所有电动车信息。
	 * 
	 * @param storeName
	 *            营业门店名称
	 * @param beginTime
	 *            取车起始时间
	 * @param endTime
	 *            还车结束时间
	 * @return 所有在该门店下该短时间内可以租赁的所有电动车信息
	 */
	public ArrayList<String[]> SearchByStoreNameAndTime(String storeName,
			String beginTime, String endTime, int pageSize, int currentSize);

	public ArrayList<String[]> pageSearchAllCar(int pageSize, int currentPage);

	public Object[] getAllCarRSCount();

	public Object[] getRSCountByStoreNameAndTime(String storeName,
			String beginTime, String endTime);

	public ArrayList<String[]> pageSearchCarByCondition(int storeId,
			String[] carBrand, float[] price, String[] carGear, String[] seat,
			String beginTime, String endTime, int pageSize, int currentPage);

	public Object[] getRSCountBySearchCarCondition(int storeId,
			String[] carBrand, float[] price, String[] carGear, String[] seat,
			String beginTime, String endTime);

	/**
	 * 出车操作用到
	 * 
	 * @param carId
	 * @param store
	 * @return
	 */
	public boolean changeCarStore(int carId, int storeId);

	/**
	 * 查询某一天的所有车辆租赁次数
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<String[]> getDayCarRentalNum(String date);

}
