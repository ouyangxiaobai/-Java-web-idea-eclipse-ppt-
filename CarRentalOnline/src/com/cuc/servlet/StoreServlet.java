package com.cuc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IBusinessStoreDAO;
import com.cuc.dao.ICarDAO;
import com.cuc.dao.imp.BusinessStoreDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.model.BusinessStore;
import com.cuc.util.PageUtil;

public class StoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");

		System.out.println("method=" + method);

		if (method == null) {
			method = "";
		}
		if (method.equals("getStoreName")) {
			getStoreName(request, response);
			return;
		}

		if (method.equals("isSameNo")) {
			isSameNo(request, response);
			return;
		}

		if (method.equals("logout")) {
			logout(request, response);
			return;
		}
		HttpSession session = request.getSession();
		BusinessStore store = (BusinessStore) session.getAttribute("store");
		if (store == null) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('请先登录！');javascript:history.back(-1);</SCRIPT>");

			return;
		}

		if (method.equals("changePassword")) {
			changePassword(request, response);
		} else if (method.equals("pageSearchCar")) {
			pageSearchCar(request, response, store.getStoreId());
		}

	}

	private void isSameNo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		String no = request.getParameter("no");

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();

		if (storeDAO.isSomeNo(no)) {
			// 存在相同账号
			out.print("1");
		} else {
			out.print("-1");
		}

	}

	private void pageSearchCar(HttpServletRequest request,
			HttpServletResponse response, int storeId) throws ServletException,
			IOException {
		System.out.println("进入分页查看车辆信息");
		ICarDAO carDAO = new CarDAO();
		Object totalCount = carDAO.getRSCountByStoreId(storeId)[0];

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(3);
		pageUtil.setRsCount(Integer.parseInt(totalCount.toString()));

		int pageCount = pageUtil.getPageCount();
		int currentCount = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);
		request.setAttribute("pageTool", pageTool);

		ArrayList<String[]> carList = carDAO.pageSearchByStoreId(storeId,
				pageUtil.getPageSize(), currentCount);

		request.setAttribute("carList", carList);
		request.getRequestDispatcher("/businessStore/CarList.jsp").forward(
				request, response);
	}

	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("进入修改密码");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id" + id);
		String againstPassword = request.getParameter("againstPassword");
		System.out.println("againstPassword" + againstPassword);
		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
		if (storeDAO.changePassword(id, againstPassword)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('密码修改成功！');javascript:history.back(-1);</SCRIPT>");
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('密码修改失败！');javascript:history.back(-1);</SCRIPT>");
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		String path = request.getContextPath();
		System.out.println("退出登录方法中的路径->" + path);
		response.sendRedirect(path + "/member/welcome.jsp");
	}

	private void getStoreName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

//		 String province = "'"
//		 + new String(request.getParameter("province").getBytes(
//		 "iso-8859-1"), "utf-8") + "'";
//		 String city = "'"
//		 + new String(request.getParameter("city")
//		 .getBytes("iso-8859-1"), "utf-8") + "'";
		String province = "'" + request.getParameter("province") + "'";
		String city = "'" + request.getParameter("city") + "'";

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
		ArrayList<String[]> nameList = storeDAO.getBusinessNameByCity(province,
				city);
		if (nameList.size() != 0 && nameList != null) {
			for (int i = 0; i < nameList.size(); i++) {
				String[] pc = nameList.get(i);
				response.getWriter().println(pc[0]);
			}
		}
	}

}
