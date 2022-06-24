package com.cuc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IBusinessStoreDAO;
import com.cuc.dao.IInsuranceDAO;
import com.cuc.dao.IMemberDAO;
import com.cuc.dao.IMessageDAO;
import com.cuc.dao.ISysAdminDAO;
import com.cuc.dao.imp.BusinessStoreDAO;
import com.cuc.dao.imp.InsuranceDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.dao.imp.MessageDAO;
import com.cuc.dao.imp.SysAdminDAO;
import com.cuc.model.BusinessStore;
import com.cuc.model.Insurance;
import com.cuc.model.Member;
import com.cuc.model.SysAdmin;
import com.cuc.util.CommonUtil;
import com.cuc.util.FileUpload;
import com.cuc.util.PageUtil;

public class SysAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		System.out.println("method->" + method);

		if (method == null) {
			method = "";
		}

		if (method.equals("logout")) {
			logout(request, response);
			return;
		}

		HttpSession session = request.getSession();
		SysAdmin admin = (SysAdmin) session.getAttribute("admin");

		if (admin == null) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('请先登录！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

		if (method.equals("updateAdminInfo")) {
			updateAdminInfo(request, response);
		} else if (method.equals("addSysAdminInfo")) {
			addSysAdminInfo(request, response);
		} else if (method.equals("delSysAdminById")) {
			delSysAdminById(request, response);
		} else if (method.equals("pageShowAllMemberInfo")) {
			pageShowAllMemberInfo(request, response);
		} else if (method.equals("pageShowAllMessage")) {
			pageShowAllMessage(request, response);
		} else if (method.equals("changeMessageState")) {//审核留言信息
			changeMessageState(request, response);
		} else if (method.equals("ShowAllStore")) {
			ShowAllStore(request, response);
		} else if (method.equals("updateStoreState")) {
			updateStoreState(request, response);
		} else if (method.equals("regMember")) {
			regMember(request, response);
		} else if (method.equals("addStoreInfo")) {
			addStoreInfo(request, response);
		} else if (method.equals("preupdate")) {
			preupdate(request, response);
		} else if (method.equals("updateStoreInfo")) {
			updateStoreInfo(request, response);
		} else if (method.equals("ShowAllInsurance")) {
			ShowAllInsurance(request, response);
		} else if (method.equals("updateInsuranceState")) {
			updateInsuranceState(request, response);
		} else if (method.equals("addInsuranceInfo")) {
			addInsuranceInfo(request, response);
		} else if (method.equals("preupdateInsurance")) {
			preupdateInsurance(request, response);
		} else if (method.equals("updateInsuranceInfo")) {
			updateInsuranceInfo(request, response);
		}
	}

	private void updateInsuranceInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		String content = request.getParameter("content");
		int state = Integer.parseInt(request.getParameter("state"));

		Insurance insurance = new Insurance();
		insurance.setInsuranceId(id);
		insurance.setInsuranceName(name);
		insurance.setInsurancePrice(price);
		insurance.setInsuranceContent(content);
		insurance.setState(state);

		IInsuranceDAO insuranceDAO = new InsuranceDAO();
		if (insuranceDAO.updateInsurance(insurance)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('租赁保险信息修改成功！');javascript:history.back(-1);</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('租赁保险信息修改失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void preupdateInsurance(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		IInsuranceDAO insuranceDAO = new InsuranceDAO();
		request.setAttribute("insurance", insuranceDAO.getByInsuranceId(id));

		request.getRequestDispatcher("/sysAdmin/UpdateInsuranceInfo.jsp")
				.forward(request, response);

	}

	private void addInsuranceInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		String content = request.getParameter("content");
		int state = Integer.parseInt(request.getParameter("state"));

		Insurance insurance = new Insurance();
		insurance.setInsuranceName(name);
		insurance.setInsurancePrice(price);
		insurance.setInsuranceContent(content);
		insurance.setState(state);

		IInsuranceDAO insuranceDAO = new InsuranceDAO();

		if (insuranceDAO.addInsurance(insurance)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('租赁保险信息添加成功！');javascript:history.back(-1);</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('租赁保险信息添加失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void updateInsuranceState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		if (state == 0) {
			state = 1;
		} else if (state == 1) {
			state = 0;
		}

		IInsuranceDAO insuranceDAO = new InsuranceDAO();

		if (insuranceDAO.changeInsuranceState(id, state)) {
			ShowAllInsurance(request, response);
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('更改失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

	}

	private void ShowAllInsurance(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IInsuranceDAO insuranceDAO = new InsuranceDAO();

		ArrayList<String[]> insuranceList = insuranceDAO.searchAllInsurance();

		request.setAttribute("insuranceList", insuranceList);

		request.getRequestDispatcher("/sysAdmin/ShowAllInsuranceInfo.jsp")
				.forward(request, response);

	}

	private void updateStoreInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String realPath = this.getServletContext().getRealPath("/uploadFiles");// 上传路径

		FileUpload upload = new FileUpload();
		upload.setUploadPath(realPath + "\\");
		upload.setRequest(request);

		int i = upload.process();
		switch (i) {
		case 0:
			String[] fileArr = upload.getUpdFileNames();

			String[] nativePlace = upload.getParameter("city").split("-");

			String province = nativePlace[0];
			String city = nativePlace[1];
			String district = nativePlace[2];

			BusinessStore store = new BusinessStore();
			store.setStoreId(Integer.parseInt(upload.getParameter("id")));
			store.setStoreNo(upload.getParameter("no"));
			store.setStorePassword(upload.getParameter("password"));
			store.setStoreName(upload.getParameter("name"));
			store.setStorePhone(upload.getParameter("phone"));
			store.setStoreProvince(province);
			store.setStoreCity(city);
			store.setStoreDistrict(district);
			store.setStoreAddress(upload.getParameter("address"));
			store.setState(Integer.parseInt(upload.getParameter("state")));

			String oldImg = upload.getParameter("oldImg");

			try {
				store.setStoreImage(fileArr[0]);

				File file = new File(realPath + "\\" + oldImg);
				file.delete();

			} catch (ArrayIndexOutOfBoundsException e) {
				store.setStoreImage(oldImg);
			}

			IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
			if (storeDAO.updateBusinessStore(store)) {
				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('营业门店信息修改成功！');window.open('../servlet/SysAdminServlet?method=ShowAllStore','content')</SCRIPT>");
			} else {
				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('营业门店信息修改失败！');javascript:history.back(-1);</SCRIPT>");
			}

			break;

		case 1:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('request对象不存在！');javascript:history.back(-1);</SCRIPT>");
			return;
		case 2:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('没有设置保存路径！');javascript:history.back(-1);</SCRIPT>");
			break;
		case 3:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('表单没设置enctype=multipart/form-data！');javascript:history.back(-1);</SCRIPT>");
			break;
		case 4:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('上传操作失败！');javascript:history.back(-1);</SCRIPT>");
			break;
		}
		return;
	}

	private void preupdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
		BusinessStore store = storeDAO.getByBusinessStoreId(id);

		request.setAttribute("store", store);

		request.getRequestDispatcher("/sysAdmin/UpdateStoreInfo.jsp").forward(
				request, response);
	}

	private void addStoreInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String realPath = this.getServletContext().getRealPath("/uploadFiles");
		System.out.println("addStoreInfo中realPath为：" + realPath);

		FileUpload upload = new FileUpload();
		upload.setRequest(request);
		upload.setUploadPath(realPath + "\\");

		int i = upload.process();
		switch (i) {
		case 0:
			String[] fileArr = upload.getUpdFileNames();

			String[] nativePlace = upload.getParameter("city").split("-");

			String province = nativePlace[0];
			String city = nativePlace[1];
			String district = nativePlace[2];

			BusinessStore store = new BusinessStore();
			store.setStoreNo(upload.getParameter("no"));

			// 判断门店账号是否已经被注册
			IBusinessStoreDAO storeDAO1 = new BusinessStoreDAO();
			if (storeDAO1.isSomeNo(upload.getParameter("no"))) {
				CommonUtil.showAlertMessage(response, "该营业门店账号已经被注册！");
				return;
			}

			store.setStorePassword(upload.getParameter("password"));
			store.setStoreName(upload.getParameter("name"));
			store.setStorePhone(upload.getParameter("phone"));
			store.setStoreProvince(province);
			store.setStoreCity(city);
			store.setStoreDistrict(district);
			store.setStoreAddress(upload.getParameter("address"));
			store.setState(Integer.parseInt(upload.getParameter("state")));

			if (fileArr.length != 0) {
				store.setStoreImage(fileArr[0]);
			}

			IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
			if (storeDAO.addBusinessStore(store)) {
				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('营业门店信息注册成功！');javascript:history.back(-1);</SCRIPT>");
			} else {
				File file = new File(realPath + "\\" + fileArr[0]);
				file.delete();

				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('营业门店信息注册失败,请重新再试！');javascript:history.back(-1);</SCRIPT>");

			}
			break;
		case 1:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('request对象不存在！');javascript:history.back(-1);</SCRIPT>");
			return;
		case 2:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('没有设置保存路径！');javascript:history.back(-1);</SCRIPT>");
			break;
		case 3:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('表单没设置enctype=multipart/form-data！');javascript:history.back(-1);</SCRIPT>");
			break;
		case 4:
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('上传操作失败！');javascript:history.back(-1);</SCRIPT>");
			break;
		}

	}

	private void regMember(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String no = request.getParameter("no");

		IMemberDAO memberDAO = new MemberDAO();

		boolean obj = memberDAO.isSameMemberNo(no);
		if (obj) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('账号已被注册过，请重新更换账号！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");

		Member member = new Member();
		member.setMemberNo(no);
		member.setMemberPassword(password);
		member.setMemberName(name);
		member.setMemberPhone(phone);

		if (memberDAO.addMember(member)) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('注册成功！');javascript:history.back(-1);</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('注册失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void updateStoreState(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		if (state == 0) {
			state = 1;
		} else if (state == 1) {
			state = 0;
		}

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();

		if (storeDAO.updateStoreState(id, state)) {
			ShowAllStore(request, response);
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('更改失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void ShowAllStore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
		ArrayList<String[]> storeList = storeDAO.searchAllBusinessStore();
		request.setAttribute("storeList", storeList);

		request.getRequestDispatcher("/sysAdmin/ShowAllStoreInfo.jsp").forward(
				request, response);
	}

	private void changeMessageState(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int messageId = Integer.parseInt(request.getParameter("messageId"));

		IMessageDAO messageDAO = new MessageDAO();
		if (messageDAO.updateMessageState(messageId, 1)) {
			pageShowAllMessage(request, response);
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

	}

	private void pageShowAllMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IMessageDAO messageDAO = new MessageDAO();

		int WaiteMessage = messageDAO.getStateCount(0);
		int passMessage = messageDAO.getStateCount(1);
		int failMessage = messageDAO.getStateCount(2);
		int totalMessage = WaiteMessage + passMessage + failMessage;

		System.out.println("总留言条数为：" + totalMessage);

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setRsCount(totalMessage);
		pageUtil.setPageSize(5);
		int pageCount = pageUtil.getPageCount();
		int currentPage = pageUtil.getCurrentPage();

		System.out
				.println("pageShowAllMessage->当前页currentPage为：" + currentPage);

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);
		request.setAttribute("pageTool", pageTool);

		ArrayList<String[]> messageList = messageDAO.searchAllMessage(pageUtil
				.getPageSize(), currentPage);
		request.setAttribute("messageList", messageList);

		request.getRequestDispatcher("/sysAdmin/ShowAllMessage.jsp").forward(
				request, response);

	}

	private void pageShowAllMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IMemberDAO memberDAO = new MemberDAO();
		Object rowCount = memberDAO.getMemberCount()[0];

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setRsCount(Integer.parseInt(rowCount.toString()));
		pageUtil.setPageSize(5);

		int pageCount = pageUtil.getPageCount();// 计算总页数 一定要有 不然会没有计算
		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(PageUtil.BbsImage);
		request.setAttribute("pageTool", pageTool);

		ArrayList<String[]> memberList = memberDAO.pageSearchAllMember(pageUtil
				.getPageSize(), currentPage);
		request.setAttribute("memberList", memberList);

		request.getRequestDispatcher("/sysAdmin/ShowAllMemberInfo.jsp")
				.forward(request, response);
	}

	private void delSysAdminById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ISysAdminDAO sysAdminDAO = new SysAdminDAO();
		if (sysAdminDAO.delSysAdminById(id)) {

			response
					.getWriter()
					.write(
							"<script language =javascript>window.open('../sysAdmin/ShowAllSysAdmin.jsp','content')</script>");

			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('删除失败！');javascript:history.back(-1);</SCRIPT>");
		}

	}

	private void addSysAdminInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		SysAdmin admin = new SysAdmin();
		admin.setSNo(no);
		admin.setSPassword(password);
		admin.setSName(name);
		admin.setSPhone(phone);
		admin.setSAddress(address);

		ISysAdminDAO adminDAO = new SysAdminDAO();

		if (adminDAO.addSysAdmin(admin)) {
			response.getWriter().println("添加成功！");
			return;
		} else {
			response.getWriter().println("添加失败！");
			return;
		}

	}

	private void updateAdminInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		SysAdmin admin = new SysAdmin();
		admin.setSId(id);
		admin.setSNo(no);
		admin.setSPassword(password);
		admin.setSName(name);
		admin.setSPhone(phone);
		admin.setSAddress(address);

		ISysAdminDAO sysAdminDAO = new SysAdminDAO();
		if (sysAdminDAO.updateSysAdmin(admin)) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('修改成功！');javascript:history.back(-1);</SCRIPT>");
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('修改失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		String path = request.getContextPath();
		response.sendRedirect(path + "/member/welcome.jsp");
	}

}
