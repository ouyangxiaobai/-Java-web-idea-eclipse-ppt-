package com.cuc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IBrandDAO;
import com.cuc.dao.imp.BrandDAO;
import com.google.gson.Gson;

public class BrandServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		if (method == null) {
			method = "";
			return;
		} else if (method.equals("getAllBrand")) {
			getAllBrand(request, response);
		} else if ("getTypeByBrand".equals(method)) {
			getTypeByBrand(request, response);
		}

	}

	private void getTypeByBrand(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String brand = request.getParameter("brand");

		IBrandDAO brandDAO = new BrandDAO();
		ArrayList<String[]> typeList = brandDAO.getTypeByBrand(brand);

		Gson gson = new Gson();
		String brandListJson = gson.toJson(typeList);
		response.getOutputStream().print(brandListJson);

	}

	private void getAllBrand(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		IBrandDAO brandDAO = new BrandDAO();
		ArrayList<String[]> brandList = brandDAO.getAllBrand();

		Gson gson = new Gson();
		String brandListJson = gson.toJson(brandList);

		response.getOutputStream().print(brandListJson);

	}
}
