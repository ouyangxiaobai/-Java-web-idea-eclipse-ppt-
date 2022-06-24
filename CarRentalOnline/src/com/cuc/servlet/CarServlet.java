package com.cuc.servlet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IBaseDAO;
import com.cuc.dao.IBusinessStoreDAO;
import com.cuc.dao.ICarDAO;
import com.cuc.dao.IFrequentContactsDAO;
import com.cuc.dao.IInsuranceDAO;
import com.cuc.dao.imp.BaseDAO;
import com.cuc.dao.imp.BusinessStoreDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.dao.imp.FrequentContactsDAO;
import com.cuc.dao.imp.InsuranceDAO;
import com.cuc.model.BusinessStore;
import com.cuc.model.Car;
import com.cuc.model.FrequentContacts;
import com.cuc.model.Member;
import com.cuc.util.ArrayHelper;
import com.cuc.util.CommonUtil;
import com.cuc.util.FileUpload;
import com.cuc.util.PageUtil;

public class CarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");

		System.out.println("CarServlet method=" + method);

		if (method == null) {
			method = "";
		}

		if (method.equals("delCarById")) {
			delCarById(request, response);
		} else if (method.equals("showCarInfoById")) {
			showCarInfoById(request, response);
		} else if (method.equals("ShowAllCar")) {
			ShowAllCar(request, response);
		} else if (method.equals("updateCarState")) {
			updateCarState(request, response);
		} else if (method.equals("searchCar")) {// 首页上查询可租赁电动车
			searchCar(request, response);
		} else if (method.equals("searchByCondition")) {
			searchByCondition(request, response);
		} else if (method.equals("orderCar")) {// 预定车辆订单核对页面
			orderCar(request, response);
		} else if (method.equals("updateCarInfo")) {
			updateCarInfo(request, response);
		} else if (method.equals("jumpToAddCarInfo")) {
			jumpToAddCarInfo(request, response);
		} else if (method.equals("addCarInfo")) {// 门店添加车辆信息
			addCarInfo(request, response);
		} else if (method.equals("sysAdminJumpToAddCarInfo")) {
			sysAdminJumpToAddCarInfo(request, response);
		} else if (method.equals("sysAdminAddCarInfo")) {// 企业管理员添加车辆信息
			sysAdminAddCarInfo(request, response);
		}
	}

	private void sysAdminAddCarInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath(
				"/uploadFiles/car");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:

			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据

			int storeId = Integer.parseInt(fu.getParameter("storeId"));
			String carNumber = fu.getParameter("carNumber");

			if (carNumber.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "车牌号不能为空！");
				return;
			}

			String carBrand = fu.getParameter("carBrand");

			if (carBrand.trim().equals("0")) {
				CommonUtil.showAlertMessage(response, "车辆品牌不能为空！");
				return;
			}

			String carType = fu.getParameter("carType");

//			if (carType.trim().equals("0")) {
//				CommonUtil.showAlertMessage(response, "车辆型号不能为空！");
//				return;
//			}

			String carForm = fu.getParameter("carForm");
			String engineNum = fu.getParameter("engineNum");
//
//			if (engineNum.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "发动机号不能为空！");
//				return;
//			}
//
//			String carframeNum = fu.getParameter("carframeNum");
//
//			if (carframeNum.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "车架号不能为空！");
//				return;
//			}

			String carGear = fu.getParameter("carGear");

			String carDisplacement = fu.getParameter("carDisplacement");
			String carDisplacementUnits = fu
					.getParameter("carDisplacementUnits");

//			if (carDisplacementUnits.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "排量不能为空！");
//				return;
//			}

			carDisplacement = carDisplacement + carDisplacementUnits;

			String seat = fu.getParameter("seat");

			String compartment = fu.getParameter("compartment");
			String buyDate = fu.getParameter("buyDate");

			if (buyDate.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "购入日期不能为空！");
				return;
			}

			String state = fu.getParameter("state");
			String carMoney = fu.getParameter("carMoney");

			if (carMoney.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "租金不能为空！");
				return;
			}

			Car car = new Car();
			car.setStoreId(storeId);
			car.setCarNumber(carNumber);
			car.setCarBrand(carBrand);
			car.setCarType(carType);
			car.setCarForm(carForm);
			car.setEngineNum(engineNum);
			car.setCarframeNum("");
			car.setCarGear(carGear);
			car.setCarDIsplacement(carDisplacement);
			car.setSeat(seat);
			car.setCompartment(compartment);
			car.setCarBufyData(buyDate);
			car.setState(Integer.parseInt(state));

			try {
				car.setCarMoney(Float.parseFloat(carMoney));
			} catch (Exception e) {
				CommonUtil.showAlertMessage(response, "租金数值格式错误！");
				return;
			}

			String str[] = fu.getParameters("tt");
			car.setCarImage(fileArr[0]);

			ICarDAO carDAO = new CarDAO();
			if (carDAO.addCar(car)) {
				CommonUtil.showAlertMessage(response, "车辆信息添加成功！");
			} else {
				// 删除照片
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				CommonUtil.showAlertMessage(response, "车辆信息添加失败！");
			}
			break;
		case 1:
			CommonUtil.showAlertMessage(response, "request对象不存在！");
			break;
		case 2:
			CommonUtil.showAlertMessage(response, "没有设置保存路径！");
			break;
		case 3:
			CommonUtil.showAlertMessage(response,
					"表单没设置enctype=multipart/form-data！");
			break;
		case 4:
			CommonUtil.showAlertMessage(response, "上传操作失败！");
			break;
		}

	}

	private void sysAdminJumpToAddCarInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/sysAdmin/AddCarInfo.jsp").forward(
				request, response);

	}

	private void addCarInfo(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException,
			IOException {

		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath(
				"/uploadFiles/car");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:

			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据

			HttpSession session = request.getSession();
			BusinessStore store = (BusinessStore) session.getAttribute("store");

			int storeId = store.getStoreId();
			String carNumber = fu.getParameter("carNumber");

			if (carNumber.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "车牌号不能为空！");
				return;
			}

			String carBrand = fu.getParameter("carBrand");

			if (carBrand.trim().equals("0")) {
				CommonUtil.showAlertMessage(response, "车辆品牌不能为空！");
				return;
			}

			String carType = fu.getParameter("carType");

//			if (carType.trim().equals("0")) {
//				CommonUtil.showAlertMessage(response, "车辆型号不能为空！");
//				return;
//			}

			String carForm = fu.getParameter("carForm");
			String engineNum = fu.getParameter("engineNum");

//			if (engineNum.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "发动机号不能为空！");
//				return;
//			}
//
//			String carframeNum = fu.getParameter("carframeNum");
//
//			if (carframeNum.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "车架号不能为空！");
//				return;
//			}

			String carGear = fu.getParameter("carGear");

			String carDisplacement = fu.getParameter("carDisplacement");
			String carDisplacementUnits = fu
					.getParameter("carDisplacementUnits");
//
//			if (carDisplacementUnits.trim().length() == 0) {
//				CommonUtil.showAlertMessage(response, "排量不能为空！");
//				return;
//			}

			carDisplacement = carDisplacement + carDisplacementUnits;

			String seat = fu.getParameter("seat");

			String compartment = fu.getParameter("compartment");
			String buyDate = fu.getParameter("buyDate");

			if (buyDate.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "购入日期不能为空！");
				return;
			}

			String state = fu.getParameter("state");
			String carMoney = fu.getParameter("carMoney");

			if (carMoney.trim().length() == 0) {
				CommonUtil.showAlertMessage(response, "租金不能为空！");
				return;
			}

			Car car = new Car();
			car.setStoreId(storeId);
			car.setCarNumber(carNumber);
			car.setCarBrand(carBrand);
			car.setCarType(carType);
			car.setCarForm(carForm);
			car.setEngineNum(engineNum);
			car.setCarframeNum("");
			car.setCarGear(carGear);
			car.setCarDIsplacement(carDisplacement);
			car.setSeat(seat);
			car.setCompartment(compartment);
			car.setCarBufyData(buyDate);
			car.setState(Integer.parseInt(state));
			car.setCarMoney(Float.parseFloat(carMoney));

			String str[] = fu.getParameters("tt");
			car.setCarImage(fileArr[0]);

			ICarDAO carDAO = new CarDAO();
			if (carDAO.addCar(car)) {
				CommonUtil.showAlertMessage(response, "车辆信息添加成功！");
			} else {
				// 删除照片
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				CommonUtil.showAlertMessage(response, "车辆信息添加失败！");
			}
			break;
		case 1:
			CommonUtil.showAlertMessage(response, "request对象不存在！");
			break;
		case 2:
			CommonUtil.showAlertMessage(response, "没有设置保存路径！");
			break;
		case 3:
			CommonUtil.showAlertMessage(response,
					"表单没设置enctype=multipart/form-data！");
			break;
		case 4:
			CommonUtil.showAlertMessage(response, "上传操作失败！");
			break;
		}

	}

	private void jumpToAddCarInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/businessStore/AddCarInfo.jsp").forward(
				request, response);

	}

	private void updateCarInfo(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException,
			IOException {

		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath(
				"/uploadFiles/car");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据

			int storeId = Integer.parseInt(fu.getParameter("storeId"));

			int carId = Integer.parseInt(fu.getParameter("carId"));
			String carNumber = fu.getParameter("carNumber");
			String carBrand = fu.getParameter("carBrand");
			String carType = fu.getParameter("carType");
			String carForm = fu.getParameter("carForm");
			String engineNum = fu.getParameter("engineNum");
			String carframeNum = fu.getParameter("carframeNum");
			String carGear = fu.getParameter("carGear");

			String carDisplacement = fu.getParameter("carDisplacement");
			String carDisplacementUnits = fu
					.getParameter("carDisplacementUnits");
			carDisplacement = carDisplacement + carDisplacementUnits;

			String seat = fu.getParameter("seat");

			String compartment = fu.getParameter("compartment");
			String buyDate = fu.getParameter("buyDate");
			String state = fu.getParameter("state");
			String carMoney = fu.getParameter("carMoney");

			Car car = new Car();
			car.setStoreId(storeId);
			car.setCarId(carId);
			car.setCarNumber(carNumber);
			car.setCarBrand(carBrand);
			car.setCarType(carType);
			car.setCarForm(carForm);
			car.setEngineNum(engineNum);
			car.setCarframeNum(carframeNum);
			car.setCarGear(carGear);
			car.setCarDIsplacement(carDisplacement);
			car.setSeat(seat);
			car.setCompartment(compartment);
			car.setCarBufyData(buyDate);
			car.setState(Integer.parseInt(state));
			car.setCarMoney(Float.parseFloat(carMoney));

			String str[] = fu.getParameters("tt");
			String oldphoto = fu.getParameter("oldCarImg");
			try {
				car.setCarImage(fileArr[0]);
				// 删除原照片
				File f = new File(realPath + "\\" + oldphoto);
				f.delete();
			} catch (ArrayIndexOutOfBoundsException e) {
				car.setCarImage(oldphoto);
			}
			ICarDAO carDAO = new CarDAO();
			if (carDAO.updateCar(car)) {
				CommonUtil.showAlertMessage(response, "车辆信息修改成功！");
			} else {
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				CommonUtil.showAlertMessage(response, "车辆信息修改失败！");
			}
			break;
		case 1:
			CommonUtil.showAlertMessage(response, "request对象不存在！");
			break;
		case 2:
			CommonUtil.showAlertMessage(response, "没有设置保存路径！");
			break;
		case 3:
			CommonUtil.showAlertMessage(response,
					"表单没设置enctype=multipart/form-data！");
			break;
		case 4:
			CommonUtil.showAlertMessage(response, "上传操作失败！");
			break;
		}

	}

	// 预定车辆订单核对页面
	private void orderCar(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null) {
			CommonUtil.showAlertMessage(response, "您还未登录，登录后才能预定哦！");
			return;
		}

		IFrequentContactsDAO contactsDAO = new FrequentContactsDAO();
		ArrayList<FrequentContacts> contactsList = contactsDAO
				.getByMemberId(member.getMemberId());

		if (contactsList == null || contactsList.size() == 0) {
			CommonUtil.showAlertMessage(response, "您还未添加常用租车人信息,请到个人中心添加后再预定！");
			return;
		}

		IInsuranceDAO insuranceDAO = new InsuranceDAO();
		ArrayList<String[]> insuranceList = insuranceDAO
				.searchInsuranceByState(1);// 查询状态为1的所有保险信息

		int carId = Integer.parseInt(request.getParameter("carId"));

		ICarDAO carDAO = new CarDAO();
		Map<String, Object> carMap = carDAO.searchByCarId(carId);

		request.setAttribute("carMap", carMap);
		request.setAttribute("insuranceList", insuranceList);
		request.setAttribute("contactsList", contactsList);

		request.getRequestDispatcher("/member/orderCar.jsp").forward(request,
				response);

	}

	// 按条件查询某门店下的各个车辆信息
	private void searchByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取选中的车品牌
		String[] carBrand = request.getParameterValues("pp");
		
		//如果不勾选车品牌
		if(carBrand==null){
			carBrand = new String[]{"不限"};
		}
		
		System.out.println("获取选中的车品牌共有" + carBrand.length + "个");
		for (int i = 0; i < carBrand.length; i++) {
			System.out.println(carBrand[i]);
		}

		// 判断品牌是否含有不限条件,有的话,将所有选中的品牌设置为""
		carBrand = CommonUtil.changeValues(carBrand, "不限");

		// 获取选中的价格
		String[] price = request.getParameterValues("hyj");
		
		if(price==null){
			price = new String[]{"不限"};
		}

		float[] money = null;

		// 判断选中的价格是否含有不限条件
		if (ArrayHelper.inArray(price, "不限")) {
			money = new float[0];
		} else {
			money = new float[price.length * 2];
			System.out.println("获取选中的价格共有" + price.length + "个");
			int k = 0;
			for (int i = 0; i < price.length; i++) {
				System.out.println(price[i]);
				for (int j = 0; j < 2; j++) {
					money[k++] = Float.parseFloat(price[i].split("-")[j]);
				}
			}
		}

		// 获取选中的变速箱
		String[] carGear = request.getParameterValues("bsx");
		
		if(carGear==null){
			carGear = new String[]{"不限"};
		}
		
		System.out.println(" 获取选中的变速箱共有" + carGear.length + "个");
		for (int i = 0; i < carGear.length; i++)
			System.out.println(carGear[i]);

		// 判断变速箱是否含有不限条件,有的话,将所有选中的变速箱设置为""
		carGear = CommonUtil.changeValues(carGear, "不限");

		// 获取选中的座位
		String[] seat = request.getParameterValues("cx");
		
		if(seat==null){
			seat = new String[]{"0"};
		}
		
		// 判断选中的座位是否含有不限条件('0'),有的话,将所有选中的座位设置为""
		seat = CommonUtil.changeValues(seat, "0");

		HttpSession session = request.getSession();
		String storeName = (String) session.getAttribute("fromStoreName");

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
		BusinessStore store = storeDAO.getByBusinessStoreName(storeName);

		String beginTime = (String) request.getSession().getAttribute(
				"beginTime");
		String endTime = (String) request.getSession().getAttribute("endTime");

		ICarDAO carDAO = new CarDAO();
		int rsCount = Integer.parseInt(carDAO.getRSCountBySearchCarCondition(
				store.getStoreId(), carBrand, money, carGear, seat, beginTime,
				endTime)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(5);
		util.setRsCount(rsCount);
		util.getPageCount();
		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);
		request.setAttribute("pageTool", null);

		ArrayList<String[]> carList = carDAO.pageSearchCarByCondition(store
				.getStoreId(), carBrand, money, carGear, seat, beginTime,
				endTime, util.getPageSize(), currentPage);

		request.setAttribute("carList", carList);

		request.getRequestDispatcher("/member/SearchCar.jsp").forward(request,
				response);

	}

	// 租赁会员查询某门店下指定日期时间段下可以租赁的车辆
	private void searchCar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String fromStoreName = request.getParameter("storeName");// 取车门店名称

		if (fromStoreName == null) {
			CommonUtil.showAlertMessage(response, "您还未选择取车门店。");
			return;
		}

		String beginDate = request.getParameter("beginDate");// 取车起始日期
		String beginHour = request.getParameter("beginHour");// 取车其实时间
		String tostoreName = request.getParameter("tostoreName");// 还车门店名称

		if (tostoreName == null) {
			CommonUtil.showAlertMessage(response, "您还未选择换车门店。");
			return;
		}

		String endDate = request.getParameter("endDate");// 还车结束日期
		String endHour = request.getParameter("endHour");// 还车结束时间

		// fromStoreName = new String(fromStoreName.getBytes("utf-8"),"utf-8");

		String beginTime = beginDate + " " + beginHour;
		String endTime = endDate + " " + endHour;

		// 判断取车时间是否小于还车时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date d1 = null;
		try {
			d1 = sdf.parse(beginTime);
			Date d2 = sdf.parse(endTime);
			if (d2.getTime() <= d1.getTime()) {
				CommonUtil.showAlertMessage(response, "还车时间必须大于取车时间，请重新选择！");

				return;
			}
		} catch (ParseException e) {

			CommonUtil.showAlertMessage(response, "时间日期格式错误，请重新选择时间日期！");
			return;
		}

		// 不能选择取车时间小于当前时间的时间
		if (d1.getTime() <= new Date().getTime()) {
			CommonUtil.showAlertMessage(response, "需提前一天租赁车辆。");
			return;
		}

		// 不能租赁当天的车辆，只能租赁明天之后的车辆(需要提前一天租赁)
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		if (sdf2.format(d1).equals(sdf2.format(new Date()))) {
			CommonUtil.showAlertMessage(response, "需提前一天租赁，不能租赁当日的车辆。");
			return;
		}

		ICarDAO carDAO = new CarDAO();
		int rsCount = Integer.parseInt(carDAO.getRSCountByStoreNameAndTime(
				fromStoreName, beginTime, endTime)[0].toString());

		PageUtil util = new PageUtil(request);
		util.setPageSize(5);
		util.setRsCount(rsCount);
		util.getPageCount();
		int currentPage = util.getCurrentPage();

		String pageTool = util.createPageTool(PageUtil.BbsImage);

		request.setAttribute("pageTool", pageTool);

		// 租赁会员查询某门店下指定日期时间段下可以租赁的车辆
		ArrayList<String[]> carList = carDAO.SearchByStoreNameAndTime(
				fromStoreName, beginTime, endTime, util.getPageSize(),
				currentPage);
		request.setAttribute("carList", carList);

		// 是否有勾选送车上门和上门取车
		String[] isSongCheShangMen = request
				.getParameterValues("isSongCheShangMen");
		String[] isShangMenQuChe = request
				.getParameterValues("isShangMenQuChe");

		// 存储订单基本信息到session 后面添加订单信息有用到
		HttpSession session = request.getSession();
		session.setAttribute("fromStoreName", fromStoreName);// 订车门店名称
		session.setAttribute("beginTime", beginTime);// 开始时间
		session.setAttribute("tostoreName", tostoreName);// 还车门店名称
		session.setAttribute("endTime", endTime);// 结束时间
		session.setAttribute("isSongCheShangMen", isSongCheShangMen);
		session.setAttribute("isShangMenQuChe", isShangMenQuChe);

		request.getRequestDispatcher("/member/SearchCar.jsp").forward(request,
				response);

	}

	private void updateCarState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		int state = Integer.parseInt(request.getParameter("state"));

		if (state == 0) {
			state = 1;
		} else if (state == 1) {
			state = 0;
		}

		IBaseDAO baseDAO = new BaseDAO();
		Object[] paramArray = new Object[2];
		paramArray[0] = state;
		paramArray[1] = id;
		boolean result = baseDAO.update("t_car", "state=?", "carId=?",
				paramArray);

		if (result) {
			response.getWriter().println(
					"<SCRIPT LANGUAGE='JavaScript'>alert('更改成功！');</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('更改失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void ShowAllCar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		ICarDAO carDAO = new CarDAO();
		int rsCount = Integer
				.parseInt((carDAO.getAllCarRSCount()[0].toString()));

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(5);
		pageUtil.setRsCount(rsCount);

		pageUtil.getPageCount();
		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);
		request.setAttribute("pageTool", pageTool);

		ArrayList<String[]> carList = carDAO.pageSearchAllCar(pageUtil
				.getPageSize(), currentPage);
		request.setAttribute("carList", carList);

		request.getRequestDispatcher("/businessStore/CarList.jsp").forward(
				request, response);

	}

	private void showCarInfoById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int carId = Integer.parseInt(request.getParameter("carId"));

		ICarDAO carDAO = new CarDAO();
		request.setAttribute("carInfo", carDAO.searchByCarId(carId));
		request.getRequestDispatcher("/businessStore/UpdateCarInfo.jsp")
				.forward(request, response);
		return;

	}

	private void delCarById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("删除车辆");
		int carId = Integer.parseInt(request.getParameter("carId"));
		System.out.println("carId->" + carId);
		ICarDAO carDAO = new CarDAO();
		if (carDAO.deleteByCarId(carId)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('删除成功！');javascript:history.back(-1);</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('删除失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

	}

}
