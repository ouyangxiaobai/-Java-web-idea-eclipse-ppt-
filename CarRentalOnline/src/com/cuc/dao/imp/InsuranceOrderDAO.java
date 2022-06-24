package com.cuc.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IInsuranceOrderDAO;
import com.cuc.model.InsuranceOrder;
import com.cuc.util.SQLUtil;

public class InsuranceOrderDAO implements IInsuranceOrderDAO {

	public boolean addInsuranceOrder(
			ArrayList<InsuranceOrder> insuranceOrderList) {

		boolean status = false;

		String sql = "insert into t_orderininsurance(orderId,insuranceId,price) values(?,?,?)";

		ArrayList<Object[]> paramArrayList = new ArrayList<Object[]>();
		for (int i = 0; i < insuranceOrderList.size(); i++) {
			InsuranceOrder insuranceOrder = insuranceOrderList.get(i);

			Object[] objs = new Object[3];
			objs[0] = insuranceOrder.getOrderId();
			objs[1] = insuranceOrder.getInsuranceId();
			objs[2] = insuranceOrder.getPrice();

			paramArrayList.add(objs);
		}

		try {
			status = SQLUtil.getInstance().update(sql, paramArrayList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<String[]> searchInsuranceByOrderId(int orderId) {

		String sql = "SELECT t_insurance.insuranceId,t_insurance.insuranceName,t_orderininsurance.price "
				+ " FROM t_insurance,t_orderininsurance "
				+ " WHERE t_insurance.insuranceId=t_orderininsurance.insuranceId AND "
				+ " t_orderininsurance.orderId= " + orderId;

		return SQLUtil.getInstance().search(sql);
	}

}
