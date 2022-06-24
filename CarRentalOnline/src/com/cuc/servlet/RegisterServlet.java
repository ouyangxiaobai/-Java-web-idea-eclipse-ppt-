package com.cuc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IDayRegisterDAO;
import com.cuc.dao.imp.DayRegisterDAO;
import com.cuc.util.PageUtil;

public class RegisterServlet extends HttpServlet {

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

		if (method.equals("showDayRegister")) {
			showDayRegister(request, response);
		} else if (method.equals("searchDayRegisterByConditon")) {
			searchDayRegisterByConditon(request, response);
		}else if(method.equals("searchMonthRegisterByConditon")){
			searchMonthRegisterByConditon(request,response);
		}

	}

	private void searchMonthRegisterByConditon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginYear = request.getParameter("beginYear"); 
		String beginMonth = request.getParameter("beginMonth"); 
		String endYear = request.getParameter("endYear"); 
		String endMonth = request.getParameter("endMonth");
		
		String beginDate = beginYear+beginMonth;
		String endDate = endYear+endMonth;

		IDayRegisterDAO dayRegisterDAO = new DayRegisterDAO();
		int pageSize = 5;
		int rsCount = Integer.parseInt(dayRegisterDAO
				.getMonthRegisterByConditionRsCount(beginDate, endDate)[0]
				.toString());

		PageUtil pageUtil = new PageUtil(request);

		pageUtil.setPageSize(pageSize);
		pageUtil.setRsCount(rsCount);

		pageUtil.getPageCount();

		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> dayRegisterList = dayRegisterDAO
				.getMonthRegisterByCondition(beginDate, endDate, pageSize,
						currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("dayRegisterList", dayRegisterList);

		request.getRequestDispatcher(
				"/statistics/Month_UserDevelopmentStatistics.jsp").forward(request,
				response);
		
	}

	private void searchDayRegisterByConditon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

		IDayRegisterDAO dayRegisterDAO = new DayRegisterDAO();
		int pageSize = 5;
		int rsCount = Integer.parseInt(dayRegisterDAO
				.getDayRegisterByConditionRsCount(beginTime, endTime)[0]
				.toString());

		PageUtil pageUtil = new PageUtil(request);

		pageUtil.setPageSize(pageSize);
		pageUtil.setRsCount(rsCount);

		pageUtil.getPageCount();

		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> dayRegisterList = dayRegisterDAO
				.getDayRegisterByContidion(beginTime, endTime, pageSize,
						currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("dayRegisterList", dayRegisterList);

		request.getRequestDispatcher(
				"/statistics/UserDevelopmentStatistics.jsp").forward(request,
				response);

	}

	private void showDayRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IDayRegisterDAO dayRegisterDAO = new DayRegisterDAO();

		int pageSize = 5;
		int rsCount = Integer.parseInt(dayRegisterDAO.getDayRegisterCount()[0]
				.toString());

		PageUtil pageUtil = new PageUtil(request);

		pageUtil.setPageSize(pageSize);
		pageUtil.setRsCount(rsCount);

		pageUtil.getPageCount();

		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> dayRegisterList = dayRegisterDAO
				.getAllDayRegisterList(pageSize, currentPage);

		request.setAttribute("pageTool", pageTool);
		request.setAttribute("dayRegisterList", dayRegisterList);

		request.getRequestDispatcher(
				"/statistics/UserDevelopmentStatistics.jsp").forward(request,
				response);

	}

}
