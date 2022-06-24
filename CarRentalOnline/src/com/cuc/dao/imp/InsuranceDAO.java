package com.cuc.dao.imp;

import java.util.ArrayList;
import java.util.Map;

import com.cuc.dao.IInsuranceDAO;
import com.cuc.model.Insurance;
import com.cuc.util.SQLUtil;

public class InsuranceDAO implements IInsuranceDAO {

	public boolean addInsurance(Insurance insurance) {

		String sql = "insert into t_insurance(insuranceName,insurancePrice,insuranceContent,state) values(?,?,?,?)";

		Object[] paramArray = new Object[4];
		paramArray[0] = insurance.getInsuranceName();
		paramArray[1] = insurance.getInsurancePrice();
		paramArray[2] = insurance.getInsuranceContent();
		paramArray[3] = insurance.getState();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	@Deprecated
	public boolean deleteByInsuranceId(int InsuranceId) {

		String sql = "delete from t_insurance where InsuranceId=?";

		Object[] paramArray = new Object[1];
		paramArray[0] = InsuranceId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public Insurance getByInsuranceId(int InsuranceId) {

		String sql = "select * from t_insurance where insuranceId="
				+ InsuranceId;

		Map<String, Object> map = SQLUtil.getInstance().getSingleObject(sql);

		Insurance insurance = new Insurance();
		insurance.setInsuranceId(Integer.parseInt(map.get("insuranceId")
				.toString()));
		insurance.setInsuranceName((String) map.get("insuranceName"));
		insurance.setInsurancePrice(Float.parseFloat(map.get("insurancePrice")
				.toString()));
		insurance.setInsuranceContent((String) map.get("insuranceContent"));
		insurance.setState(Integer.parseInt(map.get("state").toString()));

		return insurance;
	}

	public boolean updateInsurance(Insurance insurance) {

		String sql = "update t_insurance set insuranceName=?,insurancePrice=?,insuranceContent=?,state=? where insuranceId=?";

		Object[] paramArray = new Object[5];
		paramArray[0] = insurance.getInsuranceName();
		paramArray[1] = insurance.getInsurancePrice();
		paramArray[2] = insurance.getInsuranceContent();
		paramArray[3] = insurance.getState();
		paramArray[4] = insurance.getInsuranceId();

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> searchAllInsurance() {

		String sql = "select * from t_insurance";

		return SQLUtil.getInstance().search(sql);
	}

	public boolean changeInsuranceState(int InsuranceId, int state) {

		String sql = "update t_insurance set state=? where insuranceid=?";

		Object[] paramArray = new Object[2];
		paramArray[0] = state;
		paramArray[1] = InsuranceId;

		return SQLUtil.getInstance().update(sql, paramArray);
	}

	public ArrayList<String[]> searchInsuranceByState(int state) {

		String sql = "select * from t_insurance where state="+state;
		
		return SQLUtil.getInstance().search(sql);
	}

}
