<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.Order"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>支付成功页面</title>
		<link href="<%=basePath%>member/css/secondCommon.css" rel="stylesheet">
		<link href="<%=basePath%>member/css/Thirdstep.css" rel="stylesheet">
	</head>

	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<div class="payment-wrap">
			<!--支付信息-->
			<div class="payment-info-box">
				<div class="payment-info">
					<div class="pay-success">
						<span></span> 订单支付成功!
					</div>
					<div class="pay-help">
						取车时请务必携带本人
						<span>有效身份证、本人信用卡</span>
					</div>
				</div>
				<%
					Order order = (Order) session.getAttribute("order");
				%>
				<div id="divOrderInfo" class="payment-detail">

					<span class="pay-type">支付方式:网上支付</span>
					<span class="pay-price">订单总价:<em>￥<%=order.getTotalMoney()%></em>(只接受银行卡支付，不接受现金）</span>
				</div>
				<div class="payment-remark highlight no-margin">
					<p>
						请在规定时间内到本营业门店取车，否则订单将自动取消。
					</p>
				</div>
				<div class="payment-link">
					<a href="<%=basePath %>member/welcome.jsp" title="返回首页">返回首页</a>
					<a
						href="<%=basePath %>member/memberInfoCenter.jsp"
						title="查看订单">查看订单</a>
				</div>
			</div>
			<!--后台下单隐藏-->

		</div>
		<!--支付信息-->




		<jsp:include page="Footer.jsp"></jsp:include>

	</body>
</html>
