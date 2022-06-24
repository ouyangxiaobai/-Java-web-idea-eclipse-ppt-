package com.cuc.dao.imp;

import java.util.ArrayList;

import com.cuc.dao.IBrandDAO;
import com.cuc.util.SQLUtil;

public class BrandDAO implements IBrandDAO {

	public ArrayList<String[]> getAllBrand() {

		String sql = "SELECT DISTINCT t_brand.brand FROM t_brand";

		return SQLUtil.getInstance().search(sql);
	}

	public ArrayList<String[]> getTypeByBrand(String brand) {

		String sql = "SELECT t_brand.type FROM t_brand WHERE t_brand.brand='"
				+ brand + "'";

		return SQLUtil.getInstance().search(sql);
	}

}
