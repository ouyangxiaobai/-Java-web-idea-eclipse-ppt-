package com.cuc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IDayCarRentalDAO;
import com.cuc.dao.IMonthCarRentalDAO;
import com.cuc.dao.imp.DayCarRentalDAO;
import com.cuc.dao.imp.MonthCarRentalDAO;
import com.cuc.util.PageUtil;

public class CarRentalServlet extends HttpServlet {

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
		}

		if (method.equals("showDayCarRental")) {
			showDayCarRental(request, response);
		} else if (method.equals("showDayCarRentalByCondition")) {
			showDayCarRentalByCondition(request, response);
		} else if (method.equals("showMonthCarRentalByCondition")) {
			showMonthCarRentalByCondition(request, response);
		}

	}

	private void showMonthCarRentalByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginYear = request.getParameter("beginYear");
		String beginMonth = request.getParameter("beginMonth");
		String endYear = request.getParameter("endYear");
		String endMonth = request.getParameter("endMonth");

		int pageSize = 5;

		IMonthCarRentalDAO monthCarRentalDAO = new MonthCarRentalDAO();
		int rsCount = Integer.parseInt(monthCarRentalDAO
				.getMonthCarRentalRsCountByCondition(beginYear, beginMonth,
						endYear, endMonth)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> carRentalList = monthCarRentalDAO
				.getMonthCarRentalByCondition(beginYear, beginMonth, endYear,
						endMonth, pageSize, currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("carRentalList", carRentalList);

		request.getRequestDispatcher(
				"/statistics/Month_CarRentalStatistics.jsp").forward(request,
				response);

	}

	private void showDayCarRentalByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

		int pageSize = 5;

		IDayCarRentalDAO carRentalDAO = new DayCarRentalDAO();
		int rsCount = Integer.parseInt(carRentalDAO
				.getDayCarRentalRsCountByCondition(beginTime, endTime)[0]
				.toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> carRentalList = carRentalDAO
				.getDayCarRentalByCondition(beginTime, endTime, pageSize,
						currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("carRentalList", carRentalList);

		request.getRequestDispatcher("/statistics/CarRentalStatistics.jsp")
				.forward(request, response);

	}

	private void showDayCarRental(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int pageSize = 5;

		IDayCarRentalDAO carRentalDAO = new DayCarRentalDAO();
		int rsCount = Integer.parseInt(carRentalDAO.getDayCarRentalRsCount()[0]
				.toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> carRentalList = carRentalDAO.getDayCarRental(
				pageSize, currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("carRentalList", carRentalList);

		request.getRequestDispatcher("/statistics/CarRentalStatistics.jsp")
				.forward(request, response);

	}

}
