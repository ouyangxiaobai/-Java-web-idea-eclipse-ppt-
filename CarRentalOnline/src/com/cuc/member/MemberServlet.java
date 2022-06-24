package com.cuc.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.ICarDAO;
import com.cuc.dao.IFrequentContactsDAO;
import com.cuc.dao.IMemberDAO;
import com.cuc.dao.IMessageDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.dao.imp.FrequentContactsDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.dao.imp.MessageDAO;
import com.cuc.model.Car;
import com.cuc.model.FrequentContacts;
import com.cuc.model.Member;
import com.cuc.model.Message;
import com.cuc.util.CommonUtil;
import com.cuc.util.FileUpload;

public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if (method == null) {
			method = "";
		}

		if (method.equals("register")) {// 注册
			register(request, response);
			return;
		}

		if (method.equals("logout")) {
			logout(request, response);
			return;
		}

		if (method.equals("isSameNo")) {
			isSameNo(request, response);// 判断注册的会员账号是否重复
			return;
		}

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		if (member == null) {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('请先注册，并登录！');javascript:history.back(-1);</SCRIPT>");

			return;
		} else if (method.equals("insertMessage")) {// 添加体验说留言
			insertMessage(request, response);
		} else if (method.equals("updatePassword")) {
			updatePassword(request, response);
		} else if (method.equals("delFrequent")) {
			delFrequent(request, response, member.getMemberId());
		} else if (method.equals("addFrequent")) {
			addFrequent(request, response);
		} else if (method.equals("showMyMessageAll")) {
			showMyMessageAll(request, response, member.getMemberId());
		} else if (method.equals("FrequentInfo")) {
			FrequentInfo(request, response, member.getMemberId());
		} else if (method.equals("delMessage")) {
			delMessage(request, response, member.getMemberId());
		} else if (method.equals("preUpdateMemberInfo")) {
			preUpdateMemberInfo(request, response);
		} else if (method.equals("updateMemberInfo")) {
			updateMemberInfo(request, response);
		}
	}

	private void isSameNo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		String memberNo = request.getParameter("no");

		IMemberDAO memberDAO = new MemberDAO();

		boolean obj = memberDAO.isSameMemberNo(memberNo);
		if (obj) {
			out.write("1");
			return;
		} else {
			out.write("-1");
			return;
		}
	}

	private void updateMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/uploadFiles");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据

			int memberId = Integer.parseInt(fu.getParameter("memberId"));

			String name = fu.getParameter("name");
			String phone = fu.getParameter("phone");
			String sex = fu.getParameter("sex");
			String memberNo = fu.getParameter("memberNo");

			Member member = new Member();
			member.setMemberNo(memberNo);
			member.setMemberId(memberId);
			member.setMemberName(name);
			member.setMemberPhone(phone);
			member.setMemberSex(sex);

			String str[] = fu.getParameters("tt");
			String oldphoto = fu.getParameter("oldMemberImg");
			try {
				member.setMemberImage(fileArr[0]);
				// 删除原照片
				File f = new File(realPath + "\\" + oldphoto);
				f.delete();
			} catch (ArrayIndexOutOfBoundsException e) {
				member.setMemberImage(oldphoto);
			}

			IMemberDAO memberDAO = new MemberDAO();
			if (memberDAO.updateMember(member)) {
				CommonUtil.showAlertMessage(response, "个人信息修改成功！");
				
				request.getSession().setAttribute("member", member);
				
			} else {
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				CommonUtil.showAlertMessage(response, "个人信息修改失败！");
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

	private void preUpdateMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/member/UpdateMemberInfo.jsp").forward(
				request, response);

	}

	private void delMessage(HttpServletRequest request,
			HttpServletResponse response, int memberId)
			throws ServletException, IOException {
		IMessageDAO messageDAO = new MessageDAO();
		int messageId = Integer.parseInt(request.getParameter("id"));
		if (messageDAO.deleteByMessageId(messageId)) {
			// 删除照片
			String imageName = request.getParameter("imageName");
			String realPath = this.getServletContext().getRealPath(
					"/uploadFiles");
			System.out.println("delMessage中删除的地址在--->" + realPath);
			File file = new File(realPath + "\\" + imageName);
			file.delete();
			showMyMessageAll(request, response, memberId);
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('删除失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	/**
	 * 查看常用租车人
	 * 
	 * @param request
	 * @param response
	 * @param memberId
	 * @throws IOException
	 * @throws ServletException
	 */
	private void FrequentInfo(HttpServletRequest request,
			HttpServletResponse response, int memberId)
			throws ServletException, IOException {
		IFrequentContactsDAO freContactsDAO = new FrequentContactsDAO();
		ArrayList<FrequentContacts> fclist = freContactsDAO
				.getByMemberId(memberId);
		request.setAttribute("fclist", fclist);
		request.getRequestDispatcher("/member/FrequentInfo.jsp").forward(
				request, response);
	}

	/**
	 * 查看个人体验留言信息
	 * 
	 * @param request
	 * @param response
	 * @param memberId
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showMyMessageAll(HttpServletRequest request,
			HttpServletResponse response, int memberId)
			throws ServletException, IOException {
		IMessageDAO messageDAO = new MessageDAO();
		ArrayList<Message> msgList = messageDAO.getMessageByMemberId(memberId,
				3);
		request.setAttribute("msgList", msgList);
		request.getRequestDispatcher("/member/AllMessage.jsp").forward(request,
				response);

	}

	private void addFrequent(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int memberId = Integer.parseInt(request.getParameter("id"));
		FrequentContacts fc = new FrequentContacts();
		fc.setMemberId(memberId);
		fc.setFrequentName(request.getParameter("Name"));
		fc.setFrequentPhone(request.getParameter("CellPhone"));
		fc.setIdType(request.getParameter("IdCardType"));
		fc.setIdentity(request.getParameter("IdCardNo"));
		fc.setFrequentProvince(request.getParameter("privince"));
		fc.setFrequentCity(request.getParameter("city"));
		fc.setFrequentAddresss(request.getParameter("address"));
		IFrequentContactsDAO frequentContactsDAO = new FrequentContactsDAO();
		if (frequentContactsDAO.addFrequentContacts(fc)) {
			FrequentInfo(request, response, memberId);
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('添加常用租车人失败！');javascript:history.back(-1);</SCRIPT>");
			return;
		}
	}

	private void delFrequent(HttpServletRequest request,
			HttpServletResponse response, int memberId) throws IOException,
			ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		IFrequentContactsDAO frequentContactsDAO = new FrequentContactsDAO();
		if (frequentContactsDAO.deleteByFrequentId(id)) {

			FrequentInfo(request, response, memberId);
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('该联系人已存在订单，不可删除！');javascript:history.back(-1);</SCRIPT>");
		}
	}

	private void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String againstPassword = request.getParameter("againstPassword");

		IMemberDAO memberDAO = new MemberDAO();
		if (memberDAO.changePassword(id, againstPassword)) {
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

	private void insertMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		Member member = (Member) request.getSession().getAttribute("member");

		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/uploadFiles");
		System.out.println("realPath路径为----->" + realPath);
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			System.out.println("fileArr------>" + fileArr[0]);
			Message message = new Message();
			message.setMemberId(member.getMemberId());
			String Comment = fu.getParameter("txtComment");
			message.setMessageContent(Comment);
			message.setMessageImage(fileArr[0]);
			message.setState(0);
			String str[] = fu.getParameters("tt");
			IMessageDAO messageDAO = new MessageDAO();
			if (messageDAO.addMessage(message)) {
				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('留言成功，请等待管理人员审核。');javascript:history.back(-1);</SCRIPT>");
				return;
			} else {
				// 删除照片
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				response
						.getWriter()
						.println(
								"<SCRIPT LANGUAGE='JavaScript'>alert('留言失败！');javascript:history.back(-1);</SCRIPT>");
				return;
			}
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

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String userNo = request.getParameter("userNo");

		IMemberDAO memberDAO = new MemberDAO();

		boolean obj = memberDAO.isSameMemberNo(userNo);
		if (obj) {

			CommonUtil.showAlertMessage(response, "账号已被注册过，请重新更换账号！");

			return;
		}

		String password = request.getParameter("passwd2");
		String realName = request.getParameter("realName");
		String phone = request.getParameter("phone");

		Member member = new Member();
		member.setMemberNo(userNo);
		member.setMemberPassword(password);
		member.setMemberName(realName);
		member.setMemberPhone(phone);
		if (memberDAO.addMember(member)) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			request.getRequestDispatcher("/member/welcome.jsp").forward(
					request, response);
			return;
		} else {
			response
					.getWriter()
					.println(
							"<SCRIPT LANGUAGE='JavaScript'>alert('注册失败');javascript: history.back(-1);</SCRIPT>");
			return;
		}

	}
}
