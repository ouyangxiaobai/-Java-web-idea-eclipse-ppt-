package com.cuc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuc.dao.IMessageDAO;
import com.cuc.dao.imp.MessageDAO;
import com.cuc.util.PageUtil;

public class MessageServlet extends HttpServlet {

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
		if (method.equals("welShowMessage")) {
			welShowMessage(request, response);
		}

	}

	/**
	 * 显示所有已经通过审核的留言信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void welShowMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IMessageDAO messageDAO = new MessageDAO();

		PageUtil pageUtil = new PageUtil(request);
		pageUtil.setPageSize(5);// 设置每页显示条数
		int count = messageDAO.getStateCount(1);// 获取共有多少条已审核的留言数
		pageUtil.setRsCount(count);

		int pageCount = pageUtil.getPageCount();// 计算总页数
		int currentPage = pageUtil.getCurrentPage();

		String pageTool = pageUtil.createPageTool(pageUtil.BbsImage);// 创建分页工具条
		request.setAttribute("pageTool", pageTool);

		ArrayList<String[]> list = messageDAO.searchAllMessageByState(1,
				pageUtil.getPageSize(), currentPage);
		request.setAttribute("messagelist", list);
		request.getRequestDispatcher("/member/message.jsp").forward(request,
				response);

	}
}
