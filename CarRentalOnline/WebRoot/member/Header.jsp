<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cuc.model.Member"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'Header.jsp' starting page</title>

		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/member/shenzhou/style1.3.17.7.css">
		<link href="<%=basePath%>/member/shenzhou/zc_global.css"
			rel="stylesheet" type="text/css">
		<title>基于Java web的校园电动车租赁系统—中国最大租车公司!天天超低价!!!</title>
		<link href="<%=basePath%>/member/shenzhou/index.css" rel="stylesheet"
			type="text/css">

	</head>

	<body>
		<!--head start -->
		<div class="zc_head_bd">
			<!--导航头部开始-->
			<div class="zc_main">
				<a href="#" class="zc_logo"></a>
				<ul class="zc_menu">
					<li class="homecur" >
						<a href="<%=basePath%>member/welcome.jsp" target="_top">首页</a>
					</li>
					<li>
						<a  href="<%=basePath%>member/StoreInfo.jsp" target="_top">商家店铺</a>
					</li>
					<li>
						<a 
							href="<%=basePath%>servlet/MessageServlet?method=welShowMessage" target="_top">体验分享</a>
					</li>
					<li>
						<a  href="<%=basePath %>member/AboutUs.jsp" target="_top">背景简介</a>
					</li>
					
				</ul>
				<%
					Member member = (Member) session.getAttribute("member");
					if (member == null) {
				%>
				<div class="zcindex_login">
					<div class="wdl" id="notLogin" style="display: block;">
						<a href="<%=basePath%>/member/login.jsp" class="colfabe00"
							id="loginBT" target="_top">登录</a>
						<b>/</b>
						<a href="#" class="colfabe00" target="_top">注册</a>
						<!-- <a href="#" class="colc8c8ce ml20">个人中心</a> -->
					</div>
				</div>
				<%
					} else {
				%>
				<div class="zcindex_login">
					<div class="wdl" id="notLogin" style="display: block;">
						<a href="#" class="colfabe00" id="loginBT">欢迎，<%=member.getMemberName()%></a>
						<b>/</b>
						<a href="<%=basePath%>servlet/MemberServlet?method=logout"
							class="colfabe00">退出</a>
						<a href="<%=basePath%>member/memberInfoCenter.jsp"
							class="colc8c8ce ml20">个人中心</a>
					</div>
				</div>
				<%
					}
				%>
			</div>
			<!--导航头部结束-->
			<!--联系电话开始-->
			<div class="zc_phone_bd">
				<div class="p-re zc_p_h">
					<span class="zc-tel"></span>
					<div class="zc_ga_phonebox">
						<img src="<%=basePath%>/member/shenzhou/zc_gaphone.png"
							alt="港澳台及海外电话861058209555">
					</div>
				</div>
			</div>
			<!--联系电话结束-->
		</div>
	</body>
</html>
