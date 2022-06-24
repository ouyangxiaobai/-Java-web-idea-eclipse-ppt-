<%@page import="com.cuc.model.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.util.StringUtil"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Member member = (Member) session.getAttribute("member");
	if (member == null) {
		response
				.getWriter()
				.write(
						"<script language =javascript>window.open('login.jsp','_top')</script>");

		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>基本资料</title>
	</head>
	<body>
		<div style="border: #CCC 1px solid; height: 100%; width: 100%;">
			<!--头部我的信息-->
			<div
				style="background-color: #CCC; height: 45px; line-height: 45px; margin-top: -19px">
				<h3 style="color: #575757; margin-left: 15px">
					<strong>基本资料</strong>
				</h3>
			</div>
			<div style="height: 480px; color: #404040; font-weight: bold;">
				<div
					style="padding-left: 180px; padding-top: 50px; font-weight: bold;">
					<div style="float: left; padding-top: 25px">
						<%
							if (StringUtil.validateNull(member.getMemberImage())) {
						%>
						<img src="<%=basePath%>/member/images/moren.png" width="143"
							height="143">
						<%
							} else {
						%>
						<img src="<%=basePath%>uploadFiles/<%=member.getMemberImage()%>"
							width="143" height="143">
						<%
							}
						%>
					</div>
					<div>
						<table width="426" height="219" border="0">
							<tr>
								<td width="143" style="text-align: center">
									用户账号：
								</td>
								<td width="273"><%=member.getMemberNo()%></td>
							</tr>
							<tr>
								<td style="text-align: center">
									用户称呼：
								</td>
								<td><%=member.getMemberName()%></td>
							</tr>
							<tr>
								<td style="text-align: center">
									用户性别：
								</td>
								<td><%=member.getMemberSex()%></td>
							</tr>
							<tr>
								<td style="text-align: center">
									联系方式：
								</td>
								<td><%=member.getMemberPhone()%></td>
							</tr>
						</table>
						<p>
							&nbsp;
						</p>
						<p>
							<a href="<%=basePath%>member/ChangePassword.jsp"> <img
									src="<%=basePath%>member/images/changePassword.png" width="185"
									height="54" /> </a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="<%=basePath%>servlet/MemberServlet?method=preUpdateMemberInfo"><img
									src="<%=basePath%>member/images/updateInfo.bmp" width="183"
									height="54" /> </a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>