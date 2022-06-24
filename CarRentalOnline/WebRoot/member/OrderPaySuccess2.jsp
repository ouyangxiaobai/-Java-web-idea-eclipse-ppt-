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
		<div class="payment-wrap">
			<!--支付信息-->
			<div class="payment-info-box" style="width: 1100px;">
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
					Map<String, Object> orderMap = (Map<String, Object>) request
							.getAttribute("orderMap");
				%>
				<div id="divOrderInfo" class="payment-detail">

					<span class="pay-type">支付方式:网上支付</span>
					<span class="pay-price">订单总价:<em>￥<%=orderMap.get("totalMoney")%></em>(只接受银行卡支付，不接受现金）</span>
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

		</div>
		<!--支付信息-->

	</body>
</html>
