package com.cuc.dao;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.model.Order;

public interface IOrderDAO {
	/**
	 * 租赁会员用户租赁车辆之后添加订单(订单状态为：待支付)
	 * 
	 * @param order
	 *            订单信息
	 * @return 是否成功添加订单
	 */
	public boolean addOrder(Order order);

	/**
	 * 修改订单状态信息
	 * 
	 * @param orderId
	 *            订单编号
	 * @param orderState
	 *            订单状态
	 * @return 是否成功修改订单状态信息
	 */
	public boolean updateOrderState(int orderId, String orderState);
	public boolean updateBaoXiu(int orderId,String location);
	public boolean updateweixiu(int orderId);

	/**
	 * 租赁会员查询自己的订单信息(租赁会员表、常用联系人信息表、订单表、车辆表)
	 * 
	 * @param memberId
	 *            租赁会员编号
	 * @return 返回该租赁会员的订单信息
	 */
	public ArrayList<Order> getByMemberId(int memberId);

	/**
	 * 某营业门店查询 某订单状态下的订单信息
	 * 
	 * @param storeId
	 *            门店编号
	 * @return 返回该营业门店下的租赁订单信息(包括租赁会员的一些基本信息以及车辆信息)
	 */
	public ArrayList<String[]> getByStoreId(int storeId, String orderState,
			int pageSize, int currentPage);

	/**
	 * 营业门店查询自己所有订单信息
	 * 
	 * @param storeId
	 * @return
	 */
	public ArrayList<String[]> searchStoreAllOrder(int storeId, int pageSize,
			int currentPage);

	public Object[] getRsCountStoreAllOrder(int storeId);

	public Object[] getRsCountByStoreId(int storeId, String orderState);

	/**
	 * 企业管理员获取某种状态下的所有订单信息
	 * 
	 * @return 返回所有订单信息
	 */
	public ArrayList<String[]> searchAllOrder(String orderState, int pageSize,
			int currentPage);

	public ArrayList<String[]> allbaoxiu(String orderState, int pageSize,
			int currentPage);

	public Object[] getRsCountAllOrder(String orderState);
	public Object[] baoxiuzhongordercout(String orderState);

	/**
	 * 返回最新一条插入的orderId
	 * 
	 * @return
	 */
	public int getNewOrderId();

	/**
	 * 查找当前时间下已超过还未支付的订单
	 * 
	 * @param nowDate
	 *            当前时间
	 * @return
	 */
	public ArrayList<String[]> searchTimeOutOrder(String nowDate);

	/**
	 * 租赁会员查询自己订单
	 * 
	 * @param memberId
	 * @param orderState
	 *            如果orderState为NULL，则查询该会员所有订单信息
	 * @return
	 */
	public ArrayList<String[]> searchOrderByMemberId(int memberId,
			String orderState);

	public Map<String, Object> getOrderByOrderId(int orderId);

	/**
	 * 企业管理人员按条件查询所有订单信息
	 * 
	 * @param orderId
	 * @param orderState
	 * @param beginTime
	 * @param endTime
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> searchAllOrderByCondition(String orderId,
			String orderState, String beginTime, String endTime, int pageSize,
			int currentPage);

	public Object[] getAllOrderByConditionRsCount(String orderId,
			String orderState, String beginTime, String endTime);

	/**
	 * 营业门店管理人员按条件查询所有订单信息
	 * 
	 * @param storeId
	 * @param orderId
	 * @param orderState
	 * @param beginTime
	 * @param endTime
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> searchAllStoreOrderByCondition(int storeId,
			String orderId, String orderState, String beginTime,
			String endTime, int pageSize, int currentPage);

	public Object[] getAllStoreOrderByConditionRsCount(int storeId,
			String orderId, String orderState, String beginTime, String endTime);

	/**
	 * 查找订单已支付已超过取车时间的订单，修改该订单状态为已取消
	 * 
	 * @return
	 */
	public ArrayList<String[]> searchDaiChuCheTimeOutOrder();

	/**
	 * 查询该toStoreId 待还车订单
	 * 
	 * @param toStoreId
	 * @return
	 */
	public ArrayList<String[]> searchDaiHuanCheOrderByToStoreId(int toStoreId,
			int pageSize, int currentPage);

	public Object[] getRsCountDaiHuanCheOrderByToStoreId(int toStoreId);

	/**
	 * 查询可以还车操作的订单信息
	 * 
	 * @param storeId
	 * @return
	 */
	public ArrayList<String[]> searchCanReturnCar(int storeId, int pageSize,
			int currentPage);

	public Object[] getRsCountCanReturnCar(int storeId);

	/**
	 * 设置订单被取消(因超过取车时间)
	 * 
	 * @param orderId
	 * @param isCancelByDaiChuCheTimeOut
	 * @return
	 */
	public boolean updateIsCancelByDaiChuCheTimeOut(int orderId,
			int isCancelByDaiChuCheTimeOut);

	/**
	 * 获取date日期的每家门店的收益
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<String[]> getDayStoreProfit(String date);

	/**
	 * 营业门店管理员查询可以还车的订单
	 * 
	 * @param storeId
	 * @param orderId
	 * @param beginTime
	 * @param endTime
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ArrayList<String[]> searchCanReturnOrderByCondition(int storeId,
			String orderId, String beginTime, String endTime, int pageSize,
			int currentPage);

	public Object[] getCanReturnOrderRsCount(int storeId, String orderId,
			String beginTime, String endTime);
}
