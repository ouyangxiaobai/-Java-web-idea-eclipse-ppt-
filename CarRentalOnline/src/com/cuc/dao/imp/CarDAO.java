package com.cuc.dao.imp;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.dao.ICarDAO;
import com.cuc.model.Car;
import com.cuc.util.SQLUtil;

public class CarDAO implements ICarDAO {

	public boolean addCar(Car car) {
		String sql = "insert into t_car(storeId,carNumber,carBrand,carType,carForm,carMoney,carGear,carDisplacement,carBufyData,compartment,seat,engineNum,carframeNum,carImage,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] paramArray = new Object[15];
		paramArray[0] = car.getStoreId();
		paramArray[1] = car.getCarNumber();
		paramArray[2] = car.getCarBrand();
		paramArray[3] = car.getCarType();
		paramArray[4] = car.getCarForm();
		paramArray[5] = car.getCarMoney();
		paramArray[6] = car.getCarGear();
		paramArray[7] = car.getCarDIsplacement();
		paramArray[8] = car.getCarBufyData();
		paramArray[9] = car.getCompartment();
		paramArray[10] = car.getSeat();
		paramArray[11] = car.getEngineNum();
		paramArray[12] = car.getCarframeNum();
		paramArray[13] = car.getCarImage();
		paramArray[14] = car.getState();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public boolean deleteByCarId(int carId) {

		String sql = "delete from t_car where carId=?";

		Object[] paramArray = new Object[1];
		paramArray[0] = carId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public Map<String, Object> searchByCarId(int carId) {

		String sql = "select * from t_car where carId=" + carId;

		Map<String, Object> car = SQLUtil.getInstance().getSingleObject(sql);

		return car;
	}

	public boolean updateCar(Car car) {
		String sql = "update "
				+ " t_car set storeId=?,carNumber=?,carBrand=?,carType=?,carForm=?,carMoney=?,carGear=?,carDisplacement=?,"
				+ "carBufyData=?,compartment=?,seat=?,engineNum=?,carframeNum=?,carImage=?,state=? "
				+ " where carId=? ";

		Object[] paramArray = new Object[16];
		paramArray[0] = car.getStoreId();
		paramArray[1] = car.getCarNumber();
		paramArray[2] = car.getCarBrand();
		paramArray[3] = car.getCarType();
		paramArray[4] = car.getCarForm();
		paramArray[5] = car.getCarMoney();
		paramArray[6] = car.getCarGear();
		paramArray[7] = car.getCarDIsplacement();
		paramArray[8] = car.getCarBufyData();
		paramArray[9] = car.getCompartment();
		paramArray[10] = car.getSeat();
		paramArray[11] = car.getEngineNum();
		paramArray[12] = car.getCarframeNum();
		paramArray[13] = car.getCarImage();
		paramArray[14] = car.getState();
		paramArray[15] = car.getCarId();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> SearchByStoreNameAndTime(String storeName,
			String beginTime, String endTime, int pageSize, int currentPage) {

		/*
		 * --租客用户根据预订日期查询门店可租赁电动车信息（主要功能） --租车用户在基于Java web的校园电动车租赁系统
		 * --1.查询营业门店为‘'福建师范大学西门店'’的 --2.且租车日期在2016-05-01 10:00～2016-05-05
		 * 10:00的 --3.可以租赁的所有电动车信息。
		 */
		// select carId,carBrand,carType,carForm,carMoney,
		// carGear,carDisplacement,compartment,seat,carImage
		// from t_car ,t_businessstore
		// where t_car.storeId=t_businessstore.storeId and
		// storeName='福建师范大学西门店' and t_car.state=1 AND
		// carId not in
		// (
		// Select carId
		// from t_order
		// where collectionTime between '2016-05-01 10:00' and '2016-05-05
		// 10:00' or
		// returnTime between '2016-05-01 10:00' and '2016-05-05 10:00' and
		// t_order.orderState= '待支付' or
		// orderState = '待取车' or
		// orderState='租赁中' or
		// orderState='待还车'
		// )
		// ArrayList<Car> carList = null;
		//
		// Connection con = DBUtil.getInstance().getConnection();
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;
		// // 别人预定了 为 1.待支付
		// // 支付后为 2.待出车
		// // 出车后 3.租赁中
		// // 租赁日期到后4.待还车
		// // 还车后为5.已完成
		// // 若在3之前取消订单，为6.已取消订单
		// String sql = "select * "
		// + "from t_car ,t_businessstore "
		// + "where t_car.storeId=t_businessstore.storeId and "
		// + "storeName=? and t_car.state=1 AND "
		// + "carId not in "
		// + "("
		// + "	Select carId"
		// + "	from t_order "
		// + "	where  collectionTime  between ? and ? or "
		// + "	returnTime  between  ? and ? and "
		// + "	t_order.orderState= '待支付' or "
		// + "	orderState = '待出车' or "
		// + "	orderState='租赁中' or " + "	orderState='待还车' " + ") ";
		//
		// try {
		// pstmt = con.prepareStatement(sql);
		// pstmt.setString(1, storeName);
		// pstmt.setDate(2, (java.sql.Date) beginTime);
		// pstmt.setDate(3, (java.sql.Date) endTime);
		// pstmt.setDate(4, (java.sql.Date) beginTime);
		// pstmt.setDate(5, (java.sql.Date) endTime);
		// rs = pstmt.executeQuery();
		// carList = new ArrayList<Car>();
		// Car c = new Car();
		// while (rs.next()) {
		// c.setCarId(rs.getInt("carId"));
		// c.setStoreId(rs.getInt("storeId"));
		// c.setCarNumber(rs.getString("carNumber"));
		// c.setCarBrand(rs.getString("carBrand"));
		// c.setCarType(rs.getString("carType"));
		// c.setCarForm(rs.getString("carForm"));
		// c.setCarMoney(rs.getFloat("carMoney"));
		// c.setCarGear(rs.getString("carGear"));
		// c.setCarDIsplacement(rs.getString("carDisplacement"));
		// c.setCarBufyData(rs.getString("carBufyData"));
		// c.setCompartment(rs.getString("compartment"));
		// c.setSeat(rs.getString("seat"));
		// c.setEngineNum(rs.getString("engineNum"));
		// c.setCarframeNum(rs.getString("carframeNum"));
		// c.setCarImage(rs.getString("carImage"));
		// carList.add(c);
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return null;
		// }
		//
		// return carList;
		int start = (currentPage - 1) * pageSize + 1;

		String sql = "select * " + "from t_car ,t_businessstore "
				+ "where t_car.storeId=t_businessstore.storeId and "
				+ "storeName='"
				+ storeName
				+ "' and t_car.state=1 AND "
				+ "carId not in "
				+ "("
				+ "	Select carId"
				+ "	from t_order "
				+ "	where  (collectionTime  between '"
				+ beginTime
				+ "' and '"
				+ endTime
				+ "' or "
				+ "	returnTime  between '"
				+ beginTime
				+ "' and '"
				+ endTime
				+ "' )and "
				+ "	(t_order.orderState= '待支付' or "
				+ "	orderState = '待出车' or "
				+ "	orderState='租赁中' or "
				+ "	orderState='待还车' )"
				+ ")"
				+ " limit " + (start - 1) + "," + pageSize;

		System.out.println("--->" + sql);

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRSCountByStoreNameAndTime(String storeName,
			String beginTime, String endTime) {

		String sql = "select count(*) " + "from t_car ,t_businessstore "
				+ "where t_car.storeId=t_businessstore.storeId and "
				+ "storeName='" + storeName + "' and t_car.state=1 AND "
				+ "carId not in " + "(" + "	Select carId" + "	from t_order "
				+ "	where  (collectionTime  between '" + beginTime + "' and '"
				+ endTime + "' or " + "	returnTime  between '" + beginTime
				+ "' and '" + endTime + "') and ("
				+ "	t_order.orderState= '待支付' or " + "	orderState = '待出车' or "
				+ "	orderState='租赁中' or " + "	orderState='待还车' )" + ")";

		System.out.println("->" + sql);

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> pageSearchByStoreId(int storeId, int pageSize,
			int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;
		String sql = "select * from t_car where storeId=" + storeId
				+ " order by carBrand DESC " + "limit " + (start - 1) + ","
				+ pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRSCountByStoreId(int storeId) {

		String sql = "select count(*) as rscount from t_car where storeId="
				+ storeId;

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> pageSearchAllCar(int pageSize, int currentPage) {

		int start = (currentPage - 1) * pageSize + 1;

		String sql = "select * from t_car limit " + (start - 1) + ","
				+ pageSize;
		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getAllCarRSCount() {

		String sql = "select count(*) as rscount from t_car";

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public ArrayList<String[]> pageSearchCarByCondition(int storeId,
			String[] carBrand, float[] price, String[] carGear, String[] seat,
			String beginTime, String endTime, int pageSize, int currentPage) {

		String sql = "SELECT * " + "FROM t_car" + " WHERE " + "(" + "	("
				+ "		t_car.storeId=" + storeId + "" + "	) AND" + "	(";

		for (int i = 0; i < carBrand.length; i++) {
			sql = sql + "t_car.carBrand like '%" + carBrand[i] + "%' OR ";
		}
		sql = sql.substring(0, sql.length() - 3);
		sql = sql + ") And";
		sql = sql + "	(";

		for (int i = 0; i < carGear.length; i++) {
			sql = sql + "		t_car.carGear like '%" + carGear[i] + "%' OR";
		}
		sql = sql.substring(0, sql.length() - 2);
		sql = sql + ") And";
		sql = sql + "	(";

		for (int i = 0; i < seat.length; i++) {
			sql = sql + "		t_car.seat like '%" + seat[i] + "%' OR";
		}
		sql = sql.substring(0, sql.length() - 2);
		sql = sql + ") ";

		if (price.length != 0) {
			sql = sql + "	And(";
			for (int i = 0; i < price.length; ++i) {
				sql = sql + "		t_car.carMoney>" + price[i]
						+ " AND t_car.carMoney<=" + price[++i] + " OR";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql = sql + "	)";
		}

		sql = sql + ") AND t_car.state=1 AND " + "	t_car.carId NOT in" + "("
				+ "	Select carId" + "	from t_order "
				+ "	where ( collectionTime  between '" + beginTime + "' and '"
				+ endTime + "' or " + "	returnTime  between '" + beginTime
				+ "' and '" + endTime + "' )and ("
				+ "	t_order.orderState= '待支付' or " + "	orderState = '待出车' or "
				+ "	orderState='租赁中' or " + "	orderState='待还车' )" + ")";
		

//		int start = (currentPage - 1) * pageSize + 1;
//
//		String sql = "SELECT * " + "FROM t_car" + " WHERE " + "(" + "	("
//				+ "		t_car.storeId=" + storeId + "" + "	) AND" + "	(";
//
//		for (int i = 0; i < carBrand.length; i++) {
//			sql = sql + "t_car.carBrand like '%" + carBrand[i] + "%' OR ";
//		}
//		sql = sql.substring(0, sql.length() - 3);
//		sql = sql + ") And";
//		sql = sql + "	(";
//
//		for (int i = 0; i < carGear.length; i++) {
//			sql = sql + "		t_car.carGear like '%" + carGear[i] + "%' OR";
//		}
//		sql = sql.substring(0, sql.length() - 2);
//		sql = sql + ") And";
//		sql = sql + "	(";
//
//		for (int i = 0; i < seat.length; i++) {
//			sql = sql + "		t_car.seat like '%" + seat[i] + "%' OR";
//		}
//		sql = sql.substring(0, sql.length() - 2);
//		sql = sql + ") ";
//
//		if (price.length != 0) {
//			sql = sql + "	And(";
//			for (int i = 0; i < price.length; ++i) {
//				sql = sql + "		t_car.carMoney>" + price[i]
//						+ " AND t_car.carMoney<=" + price[++i] + " OR";
//			}
//			sql = sql.substring(0, sql.length() - 2);
//			sql = sql + "	)";
//		}
//
//		sql = sql + ") AND t_car.state=1 AND " + "	t_car.carId NOT in" + "("
//				+ "	Select carId" + "	from t_order "
//				+ "	where ( collectionTime  between '" + beginTime + "' and '"
//				+ endTime + "' or " + "	returnTime  between '" + beginTime
//				+ "' and '" + endTime + "' )and ("
//				+ "	t_order.orderState= '待支付' or " + "	orderState = '待出车' or "
//				+ "	orderState='租赁中' or " + "	orderState='待还车' )" + ")"
//				+ " limit " + (start - 1) + "," + pageSize;

		return SQLUtil.getInstance().search(sql);
	}

	public Object[] getRSCountBySearchCarCondition(int storeId,
			String[] carBrand, float[] price, String[] carGear, String[] seat,
			String beginTime, String endTime) {

		String sql = "SELECT count(*) " + "FROM t_car" + " WHERE " + "(" + "	("
				+ "		t_car.storeId=" + storeId + "" + "	) AND" + "	(";

		for (int i = 0; i < carBrand.length; i++) {
			sql = sql + "t_car.carBrand like '%" + carBrand[i] + "%' OR ";
		}

		sql = sql.substring(0, sql.length() - 3);
		sql = sql + ") And";
		sql = sql + "	(";

		for (int i = 0; i < carGear.length; i++) {
			sql = sql + "		t_car.carGear like '%" + carGear[i] + "%' OR";
		}

		sql = sql.substring(0, sql.length() - 2);
		sql = sql + ") And";
		sql = sql + "	(";

		for (int i = 0; i < seat.length; i++) {
			sql = sql + "		t_car.seat like '%" + seat[i] + "%' OR";
		}
		sql = sql.substring(0, sql.length() - 2);
		sql = sql + ") ";

		if (price.length != 0) {
			sql = sql + "	And(";
			for (int i = 0; i < price.length; ++i) {
				sql = sql + "		t_car.carMoney>" + price[i]
						+ " AND t_car.carMoney<=" + price[++i] + " OR";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql = sql + "	)";
		}

		sql = sql + ") AND t_car.state=1 AND " + "	t_car.carId NOT in" + "("
				+ "	Select carId" + "	from t_order "
				+ "	where ( collectionTime  between '" + beginTime + "' and '"
				+ endTime + "' or " + "	returnTime  between '" + beginTime
				+ "' and '" + endTime + "') and ("
				+ "	t_order.orderState= '待支付' or " + "	orderState = '待出车' or "
				+ "	orderState='租赁中' or " + "	orderState='待还车' )" + ")";

		System.out.println(sql);

		return SQLUtil.getInstance().getSingleRow(sql);
	}

	public boolean changeCarStore(int carId, int storeId) {

		String sql = "update t_car set storeId=? where carId=? ";

		Object[] paramArray = new Object[2];
		paramArray[0] = storeId;
		paramArray[1] = carId;

		return SQLUtil.getInstance().update(sql, paramArray);

	}

	public ArrayList<String[]> getDayCarRentalNum(String date) {

		String sql="SELECT A.carId,IFNULL(rentalNum,0) as rentalNum "+
					" FROM t_car A LEFT JOIN ( "+
					"	SELECT t_order.carId,COUNT(*) as rentalNum "+
					"	FROM t_order "+
					"   where t_order.orderTime LIKE '"+date+"%' AND "+
					"		t_order.orderState='待出车'  "+
					"	GROUP BY t_order.carId "+
					" ) B ON A.carId=B.carId "+
					" ORDER BY rentalNum DESC";
		
		return SQLUtil.getInstance().search(sql);
	}

}
