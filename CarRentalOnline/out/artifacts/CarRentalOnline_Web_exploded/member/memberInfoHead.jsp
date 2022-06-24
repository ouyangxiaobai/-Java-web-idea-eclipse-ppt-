<%@page import="com.cuc.model.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	//以下代码是页面保护代码
	//清除过期缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/member/shenzhou/style1.3.17.7.css">
<link href="<%=basePath%>/member/shenzhou/zc_global.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="zc_head_bd">
		<!--导航头部开始-->
		<div class="zc_main">
			<a href="#" class="zc_logo" alt="神州租车"></a>
			<ul class="zc_menu">
				<li class="homecur"><a href="#">首页</a>
				</li>
				<li><a href="#">租车预定</a>
				</li>
				<li><a href="#">营业门店</a>
				</li>
				<li><a href="#">用户体验</a>
				</li>
				<li><a href="#">联系我们</a>
				</li>
				<li><a href="#">帮助中心</a>
				</li>
			</ul>
			<%
				Member member = (Member) session.getAttribute("member");
				if (member == null) {
			%>
			<div class="zcindex_login">
				<div class="wdl" id="notLogin" style="display: block;">
					<a href="<%=basePath%>/member/login.jsp" target="_parent" class="colfabe00" 
						id="loginBT">登录</a> <b>/</b> <a href="<%=basePath%>/member/login.jsp" target="_parent" class="colfabe00">注册</a>
					<!-- <a href="#" class="colc8c8ce ml20">个人中心</a> -->
				</div>
			</div>
			<%
				} else {
			%>
			<div class="zcindex_login">
				<div class="wdl" id="notLogin" style="display: block;">
					<a href="#" class="colfabe00" id="loginBT">欢迎，<%=member.getMemberName()%></a>
					<b>/</b> <a href="<%=basePath%>servlet/MemberServlet?method=logout"
						class="colfabe00">退出</a> <a href="#" class="colc8c8ce ml20">个人中心</a>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<!--导航头部结束-->
</body>
</html>