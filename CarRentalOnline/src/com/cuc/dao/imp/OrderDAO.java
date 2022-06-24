package com.cuc.dao.imp;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.dao.IOrderDAO;
import com.cuc.model.Order;
import com.cuc.util.SQLUtil;

public class OrderDAO implements IOrderDAO {

	public boolean addOrder(Order order) {
		String sql = "insert into t_order(fromStoreId,toStoreId,frequentId,carId,orderTime,collectionTime,returnTime,price,totalMoney,orderState,remark,songCheShangMenAddress,shangMenQuCheAddress) values(?,?,?,?,now(),?,?,?,?,?,?,?,?)";

		Object[] paramArray = new Object[12];
		paramArray[0] = order.getFromStoreId();
		paramArray[1] = order.getToStoreId();
		paramArray[2] = order.getFrequentId();
		paramArray[3] = order.getCarId();
		paramArray[4] = order.getCollectionTime();
		paramArray[5] = order.getReturnTime();
		paramArray[6] = order.getPrice();
		paramArray[7] = order.getTotalMoney();
		paramArray[8] = order.getOrderState();
		paramArray[9] = order.getRemark();

		
		paramArray[10] = order.getSongCheShangMenAddress();
		paramArray[11] = order.getShangMenQuCheAddress();
		
		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<Order> getByMemberId(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String[]> getByStoreId(int storeId, String orderState,
			int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = "SELECT * " + "FROM t_order "
				+ "WHERE t_order.fromStoreId =" + storeId
				+ " AND t_order.orderState like '%" + orderState + "%' "
				+ "ORDER BY orderTime desc " + "limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public boolean updateOrderState(int orderId, String orderState) {

		String sql = "update t_order set orderState=? where orderId=?";

		Object[] paramArray = new Object[2];
		paramArray[0] = orderState;
		paramArray[1] = orderId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean updateBaoXiu(int orderId,String location) {

		String sql = "update t_order set baoxiu=1,location=? where orderId=?";

		Object[] paramArray = new Object[2];
		paramArray[0] = location;
		paramArray[1] = orderId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}
	public boolean updateweixiu(int orderId) {

		String sql = "update t_order set baoxiu=3 where orderId=?";

		Object[] paramArray = new Object[1];
		paramArray[0] = orderId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public int getNewOrderId() {

		String sql = "select max(orderId) from t_order";

		int orderId = Integer
				.parseInt((SQLUtil.getInstance().getSingleRow(sql)[0]
						.toString()));

		return orderId;
	}

	public ArrayList<String[]> searchTimeOutOrder(String nowDate) {

		String sql = "select * from t_order where t_order.orderState='待支付' AND "
				+ "t_order.orderTime <= '" + nowDate + "'";
		return SQLUtil.getInstance().search(sql);
	}

	public ArrayList<String[]> searchOrderByMemberId(int memberId,
			String orderState) {

		String sql = " SELECT " + "	t_order.* " + " FROM " + "	t_order, "
				+ "	t_frequentcontacts, " + "	t_member " + " WHERE "
				+ "	t_order.frequentId = t_frequentcontacts.frequentId "
				+ " AND t_member.memberId = t_frequentcontacts.memberId "
				+ " AND t_member.memberId = " + memberId;

//		if (orderState != null) {
//
//			sql += " AND t_order.orderState = '" + orderState + "' ";
//		}
		sql += " ORDER BY t_order.orderTime DESC";

		return SQLUtil.getInstance().search(sql);
	}

	public Map<String, Object> getOrderByOrderId(int orderId) {

		String sql = "select * from t_order where orderId=" + orderId;

		return SQLUtil.getInstance().getSingleObject(sql);
	}

	public ArrayList<String[]> searchDaiChuCheTimeOutOrder() {

		String sql = " SELECT * " + " FROM t_order "
				+ " WHERE t_order.orderState='待出车' AND "
				+ " collectionTime < NOW() ";

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRsCountByStoreId(int storeId, String orderState) {

		String sql = "SELECT count(*) as rsCount " + "FROM t_order "
				+ "WHERE t_order.fromStoreId =" + storeId
				+ " AND t_order.orderState like '%" + orderState + "%' "
				+ "ORDER BY orderTime desc ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchDaiHuanCheOrderByToStoreId(int toStoreId,
			int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = "SELECT * from t_order where toStoreId=" + toStoreId
				+ " and orderState='待还车'" + "limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRsCountDaiHuanCheOrderByToStoreId(int toStoreId) {

		String sql = "SELECT count(*) from t_order where toStoreId="
				+ toStoreId + " and orderState='待还车'";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public Object[] getRsCountStoreAllOrder(int storeId) {

		String sql = " SELECT " + " 	count(*) " + " FROM " + " 	t_order "
				+ " WHERE " + " 	( " + " 		fromStoreId =" + storeId
				+ " 		AND ( " + " 			orderState = '待支付' "
				+ " 			OR orderState = '待出车' " + " 			OR orderState = '租赁中' "
				+ " 			OR orderState = '已完成' " + " 			OR orderState = '已取消' "
				+ " 		) " + " 	) " + " OR ( " + " 	toStoreId =" + storeId
				+ " 	AND orderState = '待还车' " + " ) " + " ORDER BY "
				+ " 	orderTime DESC ";

		return SQLUtil.getInstance().getSingleRow(sql);

	}

	public ArrayList<String[]> searchStoreAllOrder(int storeId, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = " SELECT " + " 	* " + " FROM " + " 	t_order " + " WHERE "
				+ " 	( " + " 		fromStoreId =" + storeId + " 		AND ( "
				+ " 			orderState = '待支付' " + " 			OR orderState = '待出车' "
				+ " 			OR orderState = '租赁中' " + " 			OR orderState = '已完成' "
				+ " 			OR orderState = '已取消' " + " 		) " + " 	) " + " OR ( "
				+ " 	toStoreId =" + storeId + " 	AND orderState = '待还车' "
				+ " ) " + " ORDER BY " + " 	orderTime DESC " + " limit "
				+ (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRsCountAllOrder(String orderState) {

		String sql = " SELECT count(*) " + " FROM t_order "
				+ " WHERE t_order.orderState LIKE '%" + orderState + "%' "
				+ " ORDER BY orderTime DESC";

		return SQLUtil.getInstance().getSingleRow(sql);
	}
	public Object[] baoxiuzhongordercout(String orderState) {

		String sql = " SELECT count(*) " + " FROM t_order "
				+ " WHERE baoxiu=1 "
				+ " ORDER BY orderTime DESC";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchAllOrder(String orderState, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = " SELECT * " + " FROM t_order "
				+ " WHERE t_order.orderState LIKE '%" + orderState + "%' "
				+ " ORDER BY orderTime DESC" + " limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}
	public ArrayList<String[]> allbaoxiu(String orderState, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = " SELECT * " + " FROM t_order "
				+ " WHERE baoxiu=1 "
				+ " ORDER BY orderTime DESC" + " limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRsCountCanReturnCar(int storeId) {

		String sql = " SELECT " + " count(*) " + " FROM " + "	t_order "
				+ " WHERE " + "		t_order.toStoreId=" + storeId + " AND ( "
				+ "	t_order.orderState = '租赁中' "
				+ "	OR t_order.orderState = '待还车' " + " ) ";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchCanReturnCar(int storeId, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = " SELECT " + "  * " + " FROM " + "	t_order " + " WHERE "
				+ "		t_order.toStoreId=" + storeId + " AND ( "
				+ "	t_order.orderState = '租赁中' "
				+ "	OR t_order.orderState = '待还车' " + " ) " + " limit "
				+ (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public boolean updateIsCancelByDaiChuCheTimeOut(int orderId,
			int isCancelByDaiChuCheTimeOut) {

		String sql = "update t_order set isCancelByDaiChuCheTimeOut=? where orderId=? ";

		Object[] paramArray = new Object[2];
		paramArray[0] = isCancelByDaiChuCheTimeOut;
		paramArray[1] = orderId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> getDayStoreProfit(String date) {

		String sql=" SELECT "+
				" A.storeId, "+
				" 		IFNULL(sumMoney, 0) "+
				" 		FROM "+
				" 			t_businessstore A "+
				" 		LEFT JOIN ( "+
				" 			SELECT "+
				" 				t_order.fromStoreId, "+
				" 				SUM(t_order.totalMoney) sumMoney "+
				" 			FROM "+
				" 				t_order "+
				" 			WHERE "+
				" 				t_order.orderTime LIKE '"+date+"%' "+
				" 			AND ( "+
				" 				t_order.orderState = '待出车' "+
				" 			    OR t_order.orderState = '租赁中' "+
				" 			    OR t_order.orderState = '待还车' "+
				" 			    OR t_order.orderState = '已完成' "+
				" 			    OR ( "+
				" 				    t_order.orderState = '已取消' "+
				" 				    AND t_order.isCancelByDaiChuCheTimeOut = 1 "+
				" 			     ) "+
				" 			) "+
				" 			GROUP BY "+
				" 				t_order.fromStoreId, "+
				" 				YEAR (t_order.orderTime), "+
				" 				MONTH (t_order.orderTime), "+
				" 				DAY (t_order.orderTime) "+
				" 		) B ON A.storeId = B.fromStoreId ";
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getAllOrderByConditionRsCount(String orderId,
			String orderState, String beginTime, String endTime) {

		String sql=" SELECT count(*) "+
					" FROM t_order "+
					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%'  ";
		
		//如果没有选择开始时间和结束时间
		if( !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' ";
		}
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchAllOrderByCondition(String orderId,
			String orderState, String beginTime, String endTime, int pageSize,
			int currentPage) {
		
		int start = (currentPage - 1) * pageSize + 1;
		
		String sql=" SELECT * "+
					" FROM t_order "+
					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%'  ";
					
		//如果没有选择开始时间和结束时间
		if(  !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' " ;
		}
					
		sql +=	" ORDER BY orderTime desc "+ 
				" limit "+ (start - 1) + "," + pageSize;
					
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getAllStoreOrderByConditionRsCount(int storeId,
			String orderId, String orderState, String beginTime, String endTime) {
		
		
		String sql=" SELECT count(*) "+
					" FROM t_order "+
					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%' AND fromStoreId ="+storeId;
		
		//如果没有选择开始时间和结束时间
		if( !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' ";
		}

//		String sql=" SELECT count(*) "+
//					" FROM t_order "+
//					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%' AND "+
//					" orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' AND fromStoreId ="+storeId;
		
		
		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchAllStoreOrderByCondition(int storeId,
			String orderId, String orderState, String beginTime,
			String endTime, int pageSize, int currentPage) {
		
		int start = (currentPage - 1) * pageSize + 1;
		
		
		String sql=" SELECT * "+
					" FROM t_order "+
					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%'  AND fromStoreId ="+storeId;
		
		//如果没有选择开始时间和结束时间
		if(  !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' " ;
		}
				
		sql +=	" ORDER BY orderTime desc "+ 
			" limit "+ (start - 1) + "," + pageSize;
		
//		String sql=" SELECT * "+
//					" FROM t_order "+
//					" WHERE orderId LIKE '%"+orderId+"%' AND orderState like '"+orderState+"%' AND "+
//					" orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' AND fromStoreId ="+storeId+
//					" ORDER BY orderTime desc "+ 
//					" limit "+ (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getCanReturnOrderRsCount(int storeId, String orderId,
			String beginTime, String endTime) {
		
		String sql=" SELECT count(*) from t_order "+ 
				" where t_order.toStoreId =1 AND "+
				" ( "+
				" t_order.orderState='租赁中' OR "+
				" t_order.orderState='待还车' "+
				" ) AND t_order.orderId LIKE '%"+orderId+"%'";
		
		//如果没有选择开始时间和结束时间
		if( !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' ";
		}

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> searchCanReturnOrderByCondition(int storeId,
			String orderId, String beginTime, String endTime, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql=" SELECT * from t_order "+ 
				" where t_order.toStoreId =1 AND "+
				" ( "+
				" t_order.orderState='租赁中' OR "+
				" t_order.orderState='待还车' "+
				" ) AND t_order.orderId LIKE '%"+orderId+"%'";

		//如果没有选择开始时间和结束时间
		if( !beginTime.equals("") && !endTime.equals("") ){
			sql+=" AND orderTime BETWEEN '"+beginTime+"' AND '"+endTime+"' ";
		}
		
		sql +=	" ORDER BY orderTime desc "+ 
		" limit "+ (start - 1) + "," + pageSize;
		
		return SQLUtil.getInstance().search(sql);
	}

}
