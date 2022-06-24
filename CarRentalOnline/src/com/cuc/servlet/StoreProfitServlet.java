package com.cuc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IDayStoreProfitDAO;
import com.cuc.dao.IMonthStoreProfitDAO;
import com.cuc.dao.imp.DayStoreProfitDAO;
import com.cuc.dao.imp.MonthStoreProfitDAO;
import com.cuc.model.BusinessStore;
import com.cuc.util.PageUtil;

public class StoreProfitServlet extends HttpServlet {

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

		if (method.equals("showDayStoreProfit")) {// 查询所有营业门店每日营业信息
			showDayStoreProfit(request, response);
		} else if (method.equals("showDayStoreProfitByCondition")) {// 企业管理人员按某个日期时间段查询所有营业门店营业信息
			showDayStoreProfitByCondition(request, response);
		} else if (method.equals("showMonthStoreProfitByCondition")) {// 企业管理人员按某个月份时间段查询所有营业门店营业信息
			showMonthStoreProfitByCondition(request, response);
		} else if (method.equals("storeShowDayStoreProfit")) {// 营业门店管理人员查询该营业门店营业信息
			storeShowDayStoreProfit(request, response);
		} else if (method.equals("storeShowDayStoreProfitByCondition")) {// 营业门店管理人员按某个日期时间段查询该营业门店营业信息
			storeShowDayStoreProfitByCondition(request, response);
		} else if (method.equals("storeShowMonthStoreProfitByCondition")) {// 营业门店管理人员按某个月份时间段查询该营业门店营业信息
			storeShowMonthStoreProfitByCondition(request, response);
		}

	}

	private void storeShowMonthStoreProfitByCondition(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		int storeId = store.getStoreId();

		String beginYear = request.getParameter("beginYear");
		String beginMonth = request.getParameter("beginMonth");
		String endYear = request.getParameter("endYear");
		String endMonth = request.getParameter("endMonth");

		String beginDate = beginYear + "" + beginMonth;
		String endDate = endYear + "" + endMonth;

		int pageSize = 5;

		IMonthStoreProfitDAO monthStoreProfitDAO = new MonthStoreProfitDAO();

		int rsCount = Integer.parseInt(monthStoreProfitDAO
				.getMonthStoreProfitByConditionRsCount(beginDate, endDate,
						storeId)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = monthStoreProfitDAO
				.getMonthStoreProfitByCondition(beginDate, endDate, storeId,
						pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher(
				"/statistics/Store_Month_StoreProfitStatistics.jsp").forward(
				request, response);

	}

	private void storeShowDayStoreProfitByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		int storeId = store.getStoreId();

		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

		int pageSize = 5;

		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();
		int rsCount = Integer.parseInt(storeProfitDAO
				.getDayStoreProfitByConditionRsCount(beginTime, endTime,
						storeId)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = storeProfitDAO
				.getDayStoreProfitByCondition(beginTime, endTime, storeId,
						pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher(
				"/statistics/Store_StoreProfitStatistics.jsp").forward(request,
				response);
	}

	private void storeShowDayStoreProfit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		int storeId = store.getStoreId();

		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();

		int rsCount = Integer.parseInt((storeProfitDAO
				.getDayStoreProfitByStoreIdRsCount(storeId)[0].toString()));

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = storeProfitDAO
				.getDayStoreProfitByStoreId(storeId, pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher(
				"/statistics/Store_StoreProfitStatistics.jsp").forward(request,
				response);

	}

	private void showMonthStoreProfitByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginYear = request.getParameter("beginYear");
		String beginMonth = request.getParameter("beginMonth");
		String endYear = request.getParameter("endYear");
		String endMonth = request.getParameter("endMonth");

		String beginDate = beginYear + "" + beginMonth;
		String endDate = endYear + "" + endMonth;

		int pageSize = 5;

		IMonthStoreProfitDAO monthStoreProfitDAO = new MonthStoreProfitDAO();

		int rsCount = Integer
				.parseInt(monthStoreProfitDAO
						.getMonthStoreProfitByConditionRsCount(beginDate,
								endDate, null)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = monthStoreProfitDAO
				.getMonthStoreProfitByCondition(beginDate, endDate, null,
						pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher(
				"/statistics/Month_StoreProfitStatistics.jsp").forward(request,
				response);
	}

	private void showDayStoreProfitByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

		int pageSize = 5;

		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();
		int rsCount = Integer
				.parseInt(storeProfitDAO.getDayStoreProfitByConditionRsCount(
						beginTime, endTime, null)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = storeProfitDAO
				.getDayStoreProfitByCondition(beginTime, endTime, null,
						pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/statistics/StoreProfitStatistics.jsp")
				.forward(request, response);

	}

	private void showDayStoreProfit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();

		int rsCount = Integer.parseInt((storeProfitDAO
				.getDayStoreProfitRsCount()[0].toString()));

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> storeProfitList = storeProfitDAO.getDayStoreProfit(
				pageSize, currentPage);

		request.setAttribute("storeProfitList", storeProfitList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/statistics/StoreProfitStatistics.jsp")
				.forward(request, response);

	}
}
