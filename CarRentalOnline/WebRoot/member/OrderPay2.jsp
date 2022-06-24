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

		<title>支付页面</title>
		<link href="<%=basePath%>member/css/secondCommon.css" rel="stylesheet">
		<link href="<%=basePath%>member/css/Thirdstep.css" rel="stylesheet">

	</head>

	<body>
<div  style="width: 1100px;">
		<div class="payment-wrap">
			<!--支付信息-->
			<div class="payment-info-box" style="width: 1100px;">
				<div class="payment-info">
					<div class="pay-success">
						<span></span> 请尽快支付
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
					<span class="pay-order">订单号:<%=orderMap.get("orderId")%></span>
					<span class="pay-type">支付方式:网上支付</span>
					<span class="pay-price">订单总价:<em>￥<%=orderMap.get("totalMoney")%></em>(只接受银行卡支付，不接受现金）</span>
				</div>
				<div class="payment-remark highlight no-margin">
					<p>
						请在30分钟内完成支付，否则订单将自动取消。
					</p>
				</div>
				<div class="payment-link">
					
					
				</div>
			</div>
			<!--后台下单隐藏-->

		</div>
		<div class="pay-online-wrap" style="width: 1100px;">
			<div class="pay-online-tabbox">
				<div class="pay-online-title">
					选择付款方式
				</div>
				<div class="pay-online-tab tab-wyzf active">
					网银支付
				</div>
			</div>
			<div class="pay-online-box">
				<!--网银支付-->
				<div class="paybox1" id="J_bankbox">
					<ul class="bank-list">
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_pay_01.png"
									alt="支付宝">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_pay_03.png"
									alt="财付通">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_pay_04.png"
									alt="快钱">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_01.png"
									alt="招商银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_02.png"
									alt="中国工商银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_03.png"
									alt="中国建设银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_04.png"
									alt="中国农业银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_05.png"
									alt="中国银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_06.png"
									alt="交通银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_07.png"
									alt="上海浦东发展银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_08.png"
									alt="中国民生银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_09.png"
									alt="深圳发展银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_10.png"
									alt="广发银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_11.png"
									alt="中国光大银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_12.png"
									alt="中信银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_13.png"
									alt="兴业银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_14.png"
									alt="上海银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_15.png"
									alt="宁波银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_16.png"
									alt="杭州银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_17.png"
									alt="平安银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_18.png"
									alt="北京农村商业银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_19.png"
									alt="中国邮政储蓄银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_20.png"
									alt="富滇银行">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_21.png"
									alt="VISA">
							</label>
						</li>
						<li>
							<label>
								<input name="paytype" value="" type="radio">
								<img
									src="<%=basePath%>member/images/payImages/finish_bank_22.png"
									alt="MasterCard">
							</label>
						</li>
					</ul>
					<div class="payment-btnbox">
						<a
							href="<%=basePath%>servlet/OrderServlet?method=orderPay2&orderId=<%=orderMap.get("orderId")%>"
							title="确认支付" class="thickbox" type="button">确认支付</a>
					</div>
				</div>
				<!--网银支付-->
			</div>
		</div>

</div>
	</body>
</html>
