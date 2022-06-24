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
		<base href="<%=basePath%>">
		<link href="<%=basePath%>member/css/secondCommon.css" rel="stylesheet">
		<link href="<%=basePath%>member/css/Step2.css" rel="stylesheet">
	
	</head>
	<body>
		<jsp:include page="Header.jsp" />
		<%
			Order order = (Order) session.getAttribute("order");
		%>

		<div class="step2-wrap" style="margin-left: 100px; margin-top: 10px;">
			<div class="step2-box-right price-box-float" id="priceall">
				<div class="price-detail">
					<div class="title">
						费用明细
					</div>
					<ul>
						<li class="line-clear" id="baseRatePrice">
							<em class="price-mid "> ￥<%=order.getPrice()
							* Float.parseFloat((request.getAttribute("day")
									.toString()))%><i class="price-drop-open"></i><i
								class="price-drop-close"></i> </em>车辆租赁费及门店服务费
							<span>车辆租金<%=order.getPrice()%>元 * <%=request.getAttribute("day")%>天</span>
						</li>

						<!--保险费-->

						<%
							String[] insurancePrice = (String[]) request
									.getAttribute("insurancePrices");
								if(insurancePrice!=null){
							for (int i = 0; i < insurancePrice.length; i++) {
								String[] ip = insurancePrice[i].split("-");
						%>

						<li>
							<em class="price-mid ">￥<%=ip[1]%></em><%=ip[2]%>
							
						</li>
						<%
							}}
						%>
						<li id="promotionPriceList" class="hidelable">
						</li>
					</ul>
					<div class="price-box" id="totalAmount">
						<span class="sub-load hidelable" id="priceloading"></span><span
							class="price-txt" id="priceTitle">总计:</span><span
							class="price-total" id="priceTotal"><em>￥</em><%=order.getTotalMoney()%></span>
					</div>
					<div class="price-btnbox">
						<a href="<%=basePath %>servlet/OrderServlet?method=insertOrder" title="提交订单" id="btnSubmit" style="margin: 10px;">提交订单</a>
						<a href="javascript:history.back(-1);" title="返回修改" id="btnSubmit" style="margin: 10px;">返回修改</a>

					</div>
					<div class="price-help">
						<p
							style="border-bottom: 1px dashed #EB5A01; padding-bottom: 5px; margin-bottom: 5px;">
							<b>请在30分钟内支付完，否则订单将自动取消</b>
						</p>
						<p
							style="display: none; border-bottom: 1px dashed #EB5A01; padding-bottom: 5px; margin-bottom: 5px;">
							<b>此单可参加周年庆返现活动，您需支付整单金额，返还金额（基本租车费的50%）将在还车后3-5个工作日退还至您的一卡行账户</b>
						</p>
						首次租车请使用本人信用卡，本人二代有效身份证，本人有效地方证件副本。
						<p>
							本订单仅为客户租车预约登记，提交该订单后，客户需要到门店办理具体租车手续，具体权利义务以签署的合同为准。
						</p>
					</div>
					<div id="LimitRemind"
						style="border-top: 1px solid #ccc; color: #EB5A01; background: #FFFCD2; padding: 15px; line-height: 20px; display: none;"></div>
				</div>
			</div>
		</div>
		<jsp:include page="Footer.jsp"></jsp:include>
		<!--footer-->

		<script src="<%=basePath%>member/script/jquery"></script>
		<script src="<%=basePath%>member/script/secondStep"></script>

	</body>
</html>
