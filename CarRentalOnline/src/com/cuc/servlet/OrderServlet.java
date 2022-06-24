package com.cuc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.ICarDAO;
import com.cuc.dao.IInsuranceOrderDAO;
import com.cuc.dao.IOrderDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.dao.imp.InsuranceOrderDAO;
import com.cuc.dao.imp.OrderDAO;
import com.cuc.model.BusinessStore;
import com.cuc.model.InsuranceOrder;
import com.cuc.model.Member;
import com.cuc.model.Order;
import com.cuc.util.CommonUtil;
import com.cuc.util.PageUtil;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		System.out.println("OrderServlet->" + method);

		if (method == null) {
			method = "";
		}

		if (method.equals("sendOrder")) {
			sendOrder(request, response);// 发送订单
		} else if (method.equals("insertOrder")) {// 添加订单
			insertOrder(request, response);
		} else if (method.equals("orderPay")) {// 订单支付
			orderPay(request, response);
		} else if (method.equals("MemberSearchOrder")) {// 会员查询订单信息
			MemberSearchOrder(request, response);
		} else if (method.equals("MemberSearchOrderbaoxiu")) {// 会员查询订单信息
			MemberSearchOrderbaoxiu(request, response);
		}  else if (method.equals("baoxiu")) {// 会员查询订单信息
			baoxiu(request, response);
		} else if (method.equals("orderDetail")) {// 租赁会员查询订单详情
			orderDetail(request, response);
		} else if (method.equals("cancelOrder")) {// 取消订单
			cancelOrder(request, response);
		} else if (method.equals("orderPay2")) {
			orderPay2(request, response);
		} else if (method.equals("jumpOrderPayPage")) {// 跳转到支付页面
			jumpOrderPayPage(request, response);
		} else if (method.equals("storeSearchAllOrder")) {// 营业门店查询所有订单信息
			storeSearchAllOrder(request, response);
		} else if (method.equals("storeShowCancelOrder")) {// 营业门店查询已取消订单信息
			storeShowCancelOrder(request, response);
		} else if (method.equals("storeShowFinishedOrder")) {// 营业门店查询已完成订单信息
			storeShowFinishedOrder(request, response);
		} else if (method.equals("storeSearchDaiHuanCheOrder")) {// 营业门店查询待还车订单信息
			storeSearchDaiHuanCheOrder(request, response);
		} else if (method.equals("storeSearchDaiZhiFuOrder")) {// 营业门店查询待支付订单信息
			storeSearchDaiZhiFuOrder(request, response);
		} else if (method.equals("storeSearchDaiChuCheOrder")) {// 营业门店查询待出车订单信息
			storeSearchDaiChuCheOrder(request, response);
		} else if (method.equals("storeSearchRetalingOrder")) {// 营业门店查询租赁中订单信息
			storeSearchRetalingOrder(request, response);
		} else if (method.equals("storeSearchOrderDetial")) {// 营业门店查询订单详情信息
			storeSearchOrderDetial(request, response);
		} else if (method.equals("outCar")) {// 营业门店出车操作
			outCar(request, response);
		} else if (method.equals("returnCar")) {// 营业门店还车操作
			returnCar(request, response);
		} else if (method.equals("sysSearchAllOrder")) {// 系统管理员查询所有订单信息
			sysSearchAllOrder(request, response);
		} else if (method.equals("sysSearchDaiZhiFuOrder")) {// 系统管理员查询待支付订单信息
			sysSearchDaiZhiFuOrder(request, response);
		} else if (method.equals("sysSearchDaiChuCheOrder")) {// 系统管理员查询待出车订单信息
			sysSearchDaiChuCheOrder(request, response);
		} else if (method.equals("sysSearchRetalingOrder")) {// 系统管理员查询租赁中订单信息
			sysSearchRetalingOrder(request, response);
		}else if (method.equals("baoxiuzhongorder")) {// 系统管理员查询租赁中订单信息
			baoxiuzhongorder(request, response);
		}else if (method.equals("baoxiuzhongorderweixiu")) {// 系统管理员查询租赁中订单信息
			baoxiuzhongorderweixiu(request, response);
		}else if (method.equals("baoxiuzhongordermen")) {// 系统管理员查询租赁中订单信息
			baoxiuzhongordermen(request, response);
		} else if (method.equals("sysSearchDaiHuanCheOrder")) {// 系统管理员查询待还车订单信息
			sysSearchDaiHuanCheOrder(request, response);
		} else if (method.equals("sysShowFinishedOrder")) {// 系统管理员查询已完成订单信息
			sysShowFinishedOrder(request, response);
		} else if (method.equals("sysShowCancelOrder")) {// 系统管理员查询已取消订单信息
			sysShowCancelOrder(request, response);
		} else if (method.equals("storeSearchCanReturnOrder")) {// 营业门店查询可以还车订单信息
			storeSearchCanReturnOrder(request, response);
		} else if (method.equals("sysSearchAllOrderByCondition")) {// 系统管理员按条件查询所有订单信息
			sysSearchAllOrderByCondition(request, response);
		} else if (method.equals("storeSearchAllOrderByCondition")) {// 营业门店查询按条件查询所有订单信息
			storeSearchAllOrderByCondition(request, response);
		} else if (method.equals("storeSearchAllCanReturnOrderByCondition")) {// 营业门店按条件查询所有可以还车订单信息
			storeSearchAllCanReturnOrderByCondition(request, response);
		}
	}

	private void storeSearchAllCanReturnOrderByCondition(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");


		int storeId = store.getStoreId();
		String orderId = request.getParameter("orderId");

		if (orderId == null) {
			orderId = "";
		}

		String beginTime = request.getParameter("beginTime");
		if (beginTime == null) {// 分页工具存在缺陷
			beginTime = "";
		}

		String endTime = request.getParameter("endTime");
		if (endTime == null) {
			endTime = "";
		}

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer.parseInt(orderDAO
				.getCanReturnOrderRsCount(storeId, orderId,
						 beginTime, endTime)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		ArrayList<String[]> orderList = orderDAO
				.searchCanReturnOrderByCondition(storeId, orderId,
						beginTime, endTime, pageSize, currentPage);

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/businessStore/ReturnCar.jsp").forward(
				request, response);

	}

	private void storeSearchAllOrderByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		String jumpUrl = request.getParameter("jumpUrl");
		String orderState = request.getParameter("orderState");

		if (orderState == null) {
			orderState = "";
		}

		int storeId = store.getStoreId();
		String orderId = request.getParameter("orderId");

		if (orderId == null) {
			orderId = "";
		}

		String beginTime = request.getParameter("beginTime");
		if (beginTime == null) {// 分页工具存在缺陷
			beginTime = "";
		}

		String endTime = request.getParameter("endTime");
		if (endTime == null) {
			endTime = "";
		}

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer.parseInt(orderDAO
				.getAllStoreOrderByConditionRsCount(storeId, orderId,
						orderState, beginTime, endTime)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		ArrayList<String[]> orderList = orderDAO
				.searchAllStoreOrderByCondition(storeId, orderId, orderState,
						beginTime, endTime, pageSize, currentPage);

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/businessStore/" + jumpUrl).forward(
				request, response);

	}

	private void sysSearchAllOrderByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mark = request.getParameter("mark");

		String orderState = null;

		if ("1".equals(mark)) {// 查询所有状态的订单
			orderState = "";
		} else if ("2".equals(mark)) {
			orderState = "待支付";
		} else if ("3".equals(mark)) {
			orderState = "待出车";
		} else if ("4".equals(mark)) {
			orderState = "租赁中";
		} else if ("5".equals(mark)) {
			orderState = "待还车";
		} else if ("6".equals(mark)) {
			orderState = "已完成";
		} else if ("7".equals(mark)) {
			orderState = "已取消";
		}

		String orderId = request.getParameter("orderId");

		if (orderId == null) {
			orderId = "";
		}

		String beginTime = "";
		beginTime = request.getParameter("beginTime");
		if (beginTime == null) {
			beginTime = "";
		}

		String endTime = "";
		endTime = request.getParameter("endTime");
		if (endTime == null) {
			endTime = "";
		}

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer.parseInt(orderDAO.getAllOrderByConditionRsCount(
				orderId, orderState, beginTime, endTime)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.searchAllOrderByCondition(
				orderId, orderState, beginTime, endTime, pageSize, currentPage);
		for(String[] a:orderList){
			try {
				int days=daysBetween(a[6],a[7]);
				System.out.println(days);
				a[14]=days+"天";
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("mark", mark);

		request.getRequestDispatcher("/sysAdmin/ShowAllOrderList.jsp").forward(
				request, response);

	}

	private void storeSearchCanReturnOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer.parseInt(orderDAO.getRsCountCanReturnCar(store
				.getStoreId())[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.searchCanReturnCar(store
				.getStoreId(), pageSize, currentPage);

		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/businessStore/ReturnCar.jsp").forward(
				request, response);

	}

	private void sysShowCancelOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "已取消");

	}

	private void sysShowFinishedOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "已完成");

	}

	private void sysSearchDaiHuanCheOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "待还车");

	}

	private void sysSearchRetalingOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "租赁中");

	}
private void baoxiuzhongorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	baoxiuzhongorder(request, response, "租赁中");

	}private void baoxiuzhongorderweixiu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		baoxiuzhongorderweixiu(request, response, "租赁中");

	}private void baoxiuzhongordermen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		baoxiuzhongordermen(request, response, "租赁中");

	}

	private void sysSearchDaiChuCheOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "待出车");

	}

	private void sysSearchDaiZhiFuOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "待支付");

	}

	private void sysSearchAllOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sysSearchAllOrderByState(request, response, "");

	}

	/**
	 * 企业管理人员
	 * 
	 * @param request
	 * @param response
	 * @param orderState
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sysSearchAllOrderByState(HttpServletRequest request,
			HttpServletResponse response, String orderState)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer
				.parseInt(orderDAO.getRsCountAllOrder(orderState)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.searchAllOrder(orderState,
				pageSize, currentPage);
		for(String[] a:orderList){
			try {
				int days=daysBetween(a[6],a[7]);
				System.out.println(days);
				a[14]=days+"天";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		if (orderState.equals("")) {// 查询所有订单
			request.setAttribute("mark", "1");
		} else if (orderState.equals("待支付")) {
			request.setAttribute("mark", "2");
		} else if (orderState.equals("待出车")) {
			request.setAttribute("mark", "3");
		} else if (orderState.equals("租赁中")) {
			request.setAttribute("mark", "4");
		} else if (orderState.equals("待还车")) {
			request.setAttribute("mark", "5");
		} else if (orderState.equals("已完成")) {
			request.setAttribute("mark", "6");
		} else if (orderState.equals("已取消")) {
			request.setAttribute("mark", "7");
		}

		request.getRequestDispatcher("/sysAdmin/ShowAllOrderList.jsp").forward(
				request, response);
	}
	public void baoxiuzhongorder(HttpServletRequest request,
			HttpServletResponse response, String orderState)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer
				.parseInt(orderDAO.baoxiuzhongordercout(orderState)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.allbaoxiu(orderState,
				pageSize, currentPage);
		for(String[] a:orderList){
			try {
				int days=daysBetween(a[6],a[7]);
				System.out.println(days);
				a[14]=days+"天";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		if (orderState.equals("")) {// 查询所有订单
			request.setAttribute("mark", "1");
		} else if (orderState.equals("待支付")) {
			request.setAttribute("mark", "2");
		} else if (orderState.equals("待出车")) {
			request.setAttribute("mark", "3");
		} else if (orderState.equals("租赁中")) {
			request.setAttribute("mark", "4");
		} else if (orderState.equals("待还车")) {
			request.setAttribute("mark", "5");
		} else if (orderState.equals("已完成")) {
			request.setAttribute("mark", "6");
		} else if (orderState.equals("已取消")) {
			request.setAttribute("mark", "7");
		}
//
//		request.getRequestDispatcher("/sysAdmin/ShowBoxiuOrderList.jsp").forward(
//				request, response);
		request.getRequestDispatcher("/sysAdmin/ShowBoxiuOrderListmen.jsp").forward(
				request, response);
	}
	public void baoxiuzhongorderweixiu(HttpServletRequest request,
			HttpServletResponse response, String orderState)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();
		int orderId = Integer.parseInt(request.getParameter("orderId"));

//		报修
		orderDAO.updateweixiu(orderId);
		int rsCount = Integer
				.parseInt(orderDAO.baoxiuzhongordercout(orderState)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.allbaoxiu(orderState,
				pageSize, currentPage);
		for(String[] a:orderList){
			try {
				int days=daysBetween(a[6],a[7]);
				System.out.println(days);
				a[14]=days+"天";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		if (orderState.equals("")) {// 查询所有订单
			request.setAttribute("mark", "1");
		} else if (orderState.equals("待支付")) {
			request.setAttribute("mark", "2");
		} else if (orderState.equals("待出车")) {
			request.setAttribute("mark", "3");
		} else if (orderState.equals("租赁中")) {
			request.setAttribute("mark", "4");
		} else if (orderState.equals("待还车")) {
			request.setAttribute("mark", "5");
		} else if (orderState.equals("已完成")) {
			request.setAttribute("mark", "6");
		} else if (orderState.equals("已取消")) {
			request.setAttribute("mark", "7");
		}

		request.getRequestDispatcher("/sysAdmin/ShowBoxiuOrderListmen.jsp").forward(
				request, response);
	}
public void baoxiuzhongordermen(HttpServletRequest request,
			HttpServletResponse response, String orderState)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();

		int rsCount = Integer
				.parseInt(orderDAO.baoxiuzhongordercout(orderState)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.allbaoxiu(orderState,
				pageSize, currentPage);
		for(String[] a:orderList){
			try {
				int days=daysBetween(a[6],a[7]);
				System.out.println(days);
				a[14]=days+"天";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		if (orderState.equals("")) {// 查询所有订单
			request.setAttribute("mark", "1");
		} else if (orderState.equals("待支付")) {
			request.setAttribute("mark", "2");
		} else if (orderState.equals("待出车")) {
			request.setAttribute("mark", "3");
		} else if (orderState.equals("租赁中")) {
			request.setAttribute("mark", "4");
		} else if (orderState.equals("待还车")) {
			request.setAttribute("mark", "5");
		} else if (orderState.equals("已完成")) {
			request.setAttribute("mark", "6");
		} else if (orderState.equals("已取消")) {
			request.setAttribute("mark", "7");
		}

		request.getRequestDispatcher("/sysAdmin/ShowBoxiuOrderListmen.jsp").forward(
				request, response);
	}

	private void returnCar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 1.将该订单的状态修改为 已完成.

		// BusinessStore store = (BusinessStore) request.getSession()
		// .getAttribute("store");

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		// int carId = Integer.parseInt(request.getParameter("carId"));

		IOrderDAO orderDAO = new OrderDAO();
		// ICarDAO carDAO = new CarDAO();

		if (orderDAO.updateOrderState(orderId, "已完成")) {
			// if(carDAO.changeCarStore(carId, storeId))
			CommonUtil.showAlertMessage(response, "还车操作成功！");
		}

	}

	private void outCar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/*
		 * 2.租客支付完成，此时订单的状态变成 待出车。 门店管理员登录上后台，查看后台，进行出车操作。之后车辆 所在门店 变成 toStorId
		 * ，也就是还车门店编号。 此时订单的状态变成 租赁中。
		 */

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int carId = Integer.parseInt(request.getParameter("carId"));

		IOrderDAO orderDAO = new OrderDAO();
		ICarDAO carDAO = new CarDAO();

		if (orderDAO.updateOrderState(orderId, "租赁中")) {
			Map<String, Object> orderMap = orderDAO.getOrderByOrderId(orderId);

			int toStoreId = Integer.parseInt(orderMap.get("toStoreId")
					.toString());// 将要还车的门店编号
			if (carDAO.changeCarStore(carId, toStoreId)) {
				CommonUtil.showAlertMessage(response, "出车操作成功!");

				searchOrderDetail(request, response, orderId, carId,
						"/businessStore/OrderDetail.jsp");
			} else {
				CommonUtil.showAlertMessage(response, "出车操作失败!");
			}

		} else {
			CommonUtil.showAlertMessage(response, "出车操作失败!");
		}

	}

	private void storeSearchOrderDetial(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int carId = Integer.parseInt(request.getParameter("carId"));

		if (request.getParameter("go") == null) {
			searchOrderDetail(request, response, orderId, carId,
					"/businessStore/OrderDetail.jsp");

		} else {
			searchOrderDetail(request, response, orderId, carId,
					"/businessStore/ReturnCarOrderDetail.jsp");
		}

	}

	private void storeSearchRetalingOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "租赁中", "ShowRentaingOrderList.jsp");

	}

	private void storeSearchDaiChuCheOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "待出车", "ShowDaiChuCheOrderList.jsp");

	}

	private void storeSearchDaiZhiFuOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "待支付", "ShowDaiZhiFuOrderList.jsp");
	}

	private void storeSearchDaiHuanCheOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "待还车",
				"ShowDaiHuanCheOrderList.jsp");

		// IOrderDAO orderDAO = new OrderDAO();
		//
		// BusinessStore store = (BusinessStore) request.getSession()
		// .getAttribute("store");
		//
		// int storeId = store.getStoreId();
		//
		// int rsCount = Integer.parseInt(orderDAO
		// .getRsCountDaiHuanCheOrderByToStoreId(storeId)[0].toString());
		//
		// int pageSize = 5;
		//
		// PageUtil util = new PageUtil(request);
		// util.setPageSize(pageSize);
		// util.setRsCount(rsCount);
		//
		// util.getPageCount();
		//
		// int currentPage = util.getCurrentPage();
		//
		// String pageTool = util.createPageTool(PageUtil.BbsImage);
		//
		// ArrayList<String[]> orderList = orderDAO
		// .searchDaiHuanCheOrderByToStoreId(storeId, pageSize,
		// currentPage);
		//
		// request.setAttribute("orderList", orderList);
		// request.setAttribute("pageTool", pageTool);
		//
		// request.getRequestDispatcher(
		// "/businessStore/ShowDaiHuanCheOrderList.jsp").forward(request,
		// response);
	}

	private void storeShowFinishedOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "已完成", "ShowFinishedOrderList.jsp");
	}

	private void storeShowCancelOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "已取消", "ShowCancelOrderList.jsp");
	}

	private void storeSearchAllOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		searchStateOrder(request, response, "", "ShowAllOrderList.jsp");

		// IOrderDAO orderDAO = new OrderDAO();
		//
		// BusinessStore store = (BusinessStore) request.getSession()
		// .getAttribute("store");
		//
		// int storeId = store.getStoreId();
		//
		// int rsCount = Integer.parseInt(orderDAO
		// .getRsCountStoreAllOrder(storeId)[0].toString());
		//
		// int pageSize = 5;
		//
		// PageUtil util = new PageUtil(request);
		// util.setPageSize(pageSize);
		// util.setRsCount(rsCount);
		//
		// util.getPageCount();
		//
		// int currentPage = util.getCurrentPage();
		//
		// String pageTool = util.createPageTool(PageUtil.BbsImage);
		//
		// ArrayList<String[]> orderList = orderDAO.searchStoreAllOrder(storeId,
		// pageSize, currentPage);
		//
		// request.setAttribute("orderList", orderList);
		// request.setAttribute("pageTool", pageTool);
		//
		// request.getRequestDispatcher("/businessStore/ShowAllOrderList.jsp")
		// .forward(request, response);

	}

	/**
	 * 完成营业门店各种订单状态查看业务
	 * 
	 * @param request
	 * @param response
	 * @param orderState
	 *            订单状态
	 * @param jumpUrl
	 *            跳转页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchStateOrder(HttpServletRequest request,
			HttpServletResponse response, String orderState, String jumpUrl)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();

		BusinessStore store = (BusinessStore) request.getSession()
				.getAttribute("store");

		int storeId = store.getStoreId();

		int rsCount = Integer.parseInt(orderDAO.getRsCountByStoreId(storeId,
				orderState)[0].toString());

		int pageSize = 5;

		PageUtil util = new PageUtil(request);
		util.setPageSize(pageSize);
		util.setRsCount(rsCount);

		util.getPageCount();

		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		ArrayList<String[]> orderList = orderDAO.getByStoreId(storeId,
				orderState, pageSize, currentPage);

		request.setAttribute("orderList", orderList);
		request.setAttribute("pageTool", pageTool);

		request.getRequestDispatcher("/businessStore/" + jumpUrl).forward(
				request, response);

	}

	private void jumpOrderPayPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));

		IOrderDAO orderDAO = new OrderDAO();
		Map<String, Object> orderMap = orderDAO.getOrderByOrderId(orderId);

		request.setAttribute("orderMap", orderMap);

		request.getRequestDispatcher("/member/OrderPay2.jsp").forward(request,
				response);

	}

	private void orderPay2(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));

		IOrderDAO orderDAO = new OrderDAO();

		if (orderDAO.updateOrderState(orderId, "待出车")) {
			Map<String, Object> orderMap = orderDAO.getOrderByOrderId(orderId);

			request.setAttribute("orderMap", orderMap);
			// 跳转到订单支付成功页面
			request.getRequestDispatcher("/member/OrderPaySuccess2.jsp")
					.forward(request, response);
		} else {
		}

	}

	private void cancelOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int carId = Integer.parseInt(request.getParameter("carId"));

		IOrderDAO orderDAO = new OrderDAO();
		if (orderDAO.updateOrderState(orderId, "已取消")) {
			searchOrderDetail(request, response, orderId, carId,
					"/member/OrderDetail.jsp");
		} else {
			CommonUtil.showAlertMessage(response, "订单取消失败，请重新再试！");
		}

	}

	private void orderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int carId = Integer.parseInt(request.getParameter("carId"));

		searchOrderDetail(request, response, orderId, carId,
				"/member/OrderDetail.jsp");

	}

	public void searchOrderDetail(HttpServletRequest request,
			HttpServletResponse response, int orderId, int carId, String jumpUrl)
			throws ServletException, IOException {

		IOrderDAO orderDAO = new OrderDAO();
		Map<String, Object> orderMap = orderDAO.getOrderByOrderId(orderId);

		ICarDAO carDAO = new CarDAO();
		Map<String, Object> carMap = carDAO.searchByCarId(carId);

		IInsuranceOrderDAO insuranceOrderDAO = new InsuranceOrderDAO();
		ArrayList<String[]> insuranceList = insuranceOrderDAO
				.searchInsuranceByOrderId(orderId);

		request.setAttribute("orderMap", orderMap);
		request.setAttribute("carMap", carMap);
		request.setAttribute("insuranceList", insuranceList);// "/member/OrderDetail.jsp"

		request.getRequestDispatcher(jumpUrl).forward(request, response);
	}

	private void MemberSearchOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member) request.getSession().getAttribute("member");

		IOrderDAO orderDAO = new OrderDAO();

		ArrayList<String[]> allOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), null);

		ArrayList<String[]> daiZhiFuOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "待支付");

		ArrayList<String[]> daiChuCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待出车");

		ArrayList<String[]> zuLinZhongOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "租赁中");

		ArrayList<String[]> daiHuanCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待还车");

		ArrayList<String[]> yiWanChengOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "已完成");

		ArrayList<String[]> yiQuXiaoOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "已取消");

		request.setAttribute("allOrderList", allOrderList);
		request.setAttribute("daiZhiFuOrderList", daiZhiFuOrderList);
		request.setAttribute("daiChuCheOrderList", daiChuCheOrderList);
		request.setAttribute("zuLinZhongOrderList", zuLinZhongOrderList);
		request.setAttribute("daiHuanCheOrderList", daiHuanCheOrderList);
		request.setAttribute("yiWanChengOrderList", yiWanChengOrderList);
		request.setAttribute("yiQuXiaoOrderList", yiQuXiaoOrderList);

		request.getRequestDispatcher("/member/OrderList.jsp").forward(request,
				response);

	}
	private void MemberSearchOrderbaoxiu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member) request.getSession().getAttribute("member");

		IOrderDAO orderDAO = new OrderDAO();

		ArrayList<String[]> allOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), null);

		ArrayList<String[]> daiZhiFuOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "待支付");

		ArrayList<String[]> daiChuCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待出车");

		ArrayList<String[]> zuLinZhongOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "租赁中");

		ArrayList<String[]> daiHuanCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待还车");

		ArrayList<String[]> yiWanChengOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "已完成");

		ArrayList<String[]> yiQuXiaoOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "已取消");


		request.setAttribute("zuLinZhongOrderList", zuLinZhongOrderList);

		request.getRequestDispatcher("/member/OrderListZhulinzhong.jsp").forward(request,
				response);

	}
	private void baoxiu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member) request.getSession().getAttribute("member");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String location = request.getParameter("location");

		IOrderDAO orderDAO = new OrderDAO();
//		报修
		orderDAO.updateBaoXiu(orderId,location);

		ArrayList<String[]> allOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), null);

		ArrayList<String[]> daiZhiFuOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "待支付");

		ArrayList<String[]> daiChuCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待出车");

		ArrayList<String[]> zuLinZhongOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "租赁中");

		ArrayList<String[]> daiHuanCheOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "待还车");

		ArrayList<String[]> yiWanChengOrderList = orderDAO
				.searchOrderByMemberId(member.getMemberId(), "已完成");

		ArrayList<String[]> yiQuXiaoOrderList = orderDAO.searchOrderByMemberId(
				member.getMemberId(), "已取消");


		request.setAttribute("zuLinZhongOrderList", zuLinZhongOrderList);

		request.getRequestDispatcher("/member/OrderListZhulinzhong.jsp").forward(request,
				response);

	}

	// 订单支付
	private void orderPay(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Order order = (Order) request.getSession().getAttribute("order");
		IOrderDAO orderDAO = new OrderDAO();

		if (orderDAO.updateOrderState(order.getOrderId(), "待出车")) {
			// 跳转到订单支付成功页面
			response
					.sendRedirect("/CarRentalOnline/member/OrderPaySuccess.jsp");
		} else {
			// 不会订单支付失败。不做了~~~
		}

	}

	// 添加订单
	private void insertOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Order order = (Order) request.getSession().getAttribute("order");
		String[] insurancePrices = (String[]) request.getSession()
				.getAttribute("insurancePrices");

		int length = insurancePrices.length;

		int[] insuranceId = new int[length];// 保险编号id
		float[] insurancePrice = new float[length];// 保险价格

		for (int i = 0; i < length; i++) {
			String[] ip = insurancePrices[i].split("-");

			insuranceId[i] = Integer.parseInt(ip[0]);
			insurancePrice[i] = Float.parseFloat(ip[1]);
		}

		IOrderDAO orderDAO = new OrderDAO();
		if (orderDAO.addOrder(order)) {

			int orderId = orderDAO.getNewOrderId();

			order.setOrderId(orderId);

			ArrayList<InsuranceOrder> list = new ArrayList<InsuranceOrder>();

			for (int i = 0; i < insuranceId.length; i++) {
				InsuranceOrder insuranceOrder = new InsuranceOrder();
				insuranceOrder.setInsuranceId(insuranceId[i]);
				insuranceOrder.setOrderId(orderId);
				insuranceOrder.setPrice(insurancePrice[i]);
				list.add(insuranceOrder);
			}

			IInsuranceOrderDAO insuranceOrderDAO = new InsuranceOrderDAO();
			if (insuranceOrderDAO.addInsuranceOrder(list)) {

				response.sendRedirect("/CarRentalOnline/member/OrderPay.jsp");
			} else {
				CommonUtil.showAlertMessage(response, "订单提交失败，请重新再试！");
			}

		} else {
			CommonUtil.showAlertMessage(response, "订单提交失败，请重新再试！");
		}
	}

	// 发送订单
	private void sendOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		float day = Float.parseFloat((request.getParameter("day")));// 租车天数

		int fromStoreId = Integer.parseInt(request.getParameter("fromStoreId"));// 租车门店编号
		int toStoreId = Integer.parseInt(request.getParameter("toStoreId"));// 还车门店编号
		
		String contactsStr = request.getParameter("contactsId");// 常用租车人编号
		
		//未选中常用租车人
		if(contactsStr==null){
			CommonUtil.showAlertMessage(response, "请选择常用租车人！");
			return;
		}
		
		int contactsId = Integer.parseInt(contactsStr);// 常用租车人编号
		
		int carId = Integer.parseInt(request.getParameter("carId"));// 租赁车辆编号
		String beginTime = (String) request.getSession().getAttribute(
				"beginTime");// 开始时间
		String endTime = (String) request.getSession().getAttribute("endTime");// 结束时间
		float carMoney = Float.parseFloat(request.getParameter("carMoney"));// 租赁价格
		String remark = request.getParameter("remark");// 备注

		String songCheShangMenAddress = "";
		String shangMenQuCheAddress = "";

		HttpSession session = request.getSession();
		String[] isSongCheShangMen = (String[]) session
				.getAttribute("isSongCheShangMen");
		String[] isShangMenQuChe = (String[]) session
				.getAttribute("isShangMenQuChe");
		if (isSongCheShangMen != null) {
			songCheShangMenAddress = request
					.getParameter("songCheShangMenAddress");// 送车上门地址
		}
		if (isShangMenQuChe != null) {
			shangMenQuCheAddress = request.getParameter("shangMenQuCheAddress");// 上门取车地址
		}

		String[] insurancePrices = request.getParameterValues("insurancePrice");// 保险服务编号id和价格price
		// 、
		// 保险名称
		

		int length = insurancePrices.length;

		int[] insuranceId = new int[length];// 保险编号id
		float[] insurancePrice = new float[length];// 保险价格

		for (int i = 0; i < length; i++) {
			String[] ip = insurancePrices[i].split("-");

			insuranceId[i] = Integer.parseInt(ip[0]);
			insurancePrice[i] = Float.parseFloat(ip[1]);
		}

		float totalMoney = carMoney * day;// 租车价格
		for (float f : insurancePrice) {
			totalMoney += f;// 计算总价格
		}

		Order order = new Order();
		order.setFromStoreId(fromStoreId);
		order.setToStoreId(toStoreId);
		order.setFrequentId(contactsId);
		order.setCarId(carId);
		order.setCollectionTime(beginTime);
		order.setReturnTime(endTime);
		order.setPrice(carMoney);
		order.setTotalMoney(totalMoney);
		order.setOrderState("待支付");
		order.setRemark(remark);
		order.setSongCheShangMenAddress(songCheShangMenAddress);
		order.setShangMenQuCheAddress(shangMenQuCheAddress);

		request.getSession().setAttribute("order", order);// 将订单信息存到session
		request.getSession().setAttribute("insurancePrices", insurancePrices);// 订单保险服务信息存到session
		request.setAttribute("insurancePrices", insurancePrices);// 订单保险服务信息
		request.setAttribute("day", day);
		request.getRequestDispatcher("/member/OrderCharge.jsp").forward(
				request, response);

	}





	public static int daysBetween(String smdate,String bdate) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}
