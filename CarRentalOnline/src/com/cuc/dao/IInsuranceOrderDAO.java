package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.InsuranceOrder;

public interface IInsuranceOrderDAO {

	public boolean addInsuranceOrder(
			ArrayList<InsuranceOrder> insuranceOrderList);

	public ArrayList<String[]> searchInsuranceByOrderId(int orderId);

}
