<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.FrequentContacts"%>
<%@page import="com.cuc.dao.IBusinessStoreDAO"%>
<%@page import="com.cuc.dao.imp.BusinessStoreDAO"%>
<%@page import="com.cuc.model.BusinessStore"%>
<%@page import="com.cuc.util.CommonUtil"%>
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

		<title>车辆预定平台--预定车辆</title>

		<link href="<%=basePath%>member/css/secondCommon.css" rel="stylesheet">
		<link href="<%=basePath%>member/css/Step2.css" rel="stylesheet">

	</head>
	<body>
		<jsp:include page="Header.jsp" />
		<!--存放内容-->
		<%
			String fromStoreName = (String) session
					.getAttribute("fromStoreName");//订车门店名称
			String beginTime = (String) session.getAttribute("beginTime");//开始时间
			String tostoreName = (String) session.getAttribute("tostoreName");//还车门店名称
			String endTime = (String) session.getAttribute("endTime");//结束时间
		%>
		<div class="step2-progress-box" style="margin-top: 20px;">
		</div>
		<div class="step2-wrap" style="margin-left: 100px;">
			<form action="<%=basePath%>servlet/OrderServlet?method=sendOrder"
				method="post">
				<div class="step2-box">
					<div class="step2-box-left">
						<div class="order-info-box">
							<%
								Map<String, Object> carMap = (Map<String, Object>) request
										.getAttribute("carMap");
							%>

							<div class="car-image-box">
								<!-- 车辆编号和车辆租赁价格 -->
								<input type="hidden" value="<%=carMap.get("carId")%>"
									name="carId">
								<input type="hidden" value="<%=carMap.get("carMoney")%>"
									name="carMoney">
								<input type="hidden"
									value="<%=CommonUtil.differentDays(beginTime + ":00", endTime
							+ ":00")%>"
									name="day">
								<img
									src="<%=basePath%>uploadFiles/car/<%=carMap.get("carImage")%>"
									alt="<%=carMap.get("carBrand")%><%=carMap.get("carType")%>">
								<span>租 期： <em> <%=CommonUtil.differentDays(beginTime + ":00", endTime
							+ ":00")%> </em>天 </span>
							</div>
							<div class="store-info-box">
								<div class="car-name">
									<a href="javascript:history.back(-1);" title="返回修改">返回修改</a>
									<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
									<%--<span><%=carMap.get("carGear")%>--%>
										<%--/<%=carMap.get("carForm")%>  </span>--%>
								</div>
								<ul>
									<li>
										<span class="store-title">取车地点</span><span class="store-title">还车地点</span>
									</li>
									<li>
										<%
											IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
											BusinessStore fromStore = storeDAO
													.getByBusinessStoreName(fromStoreName);
											BusinessStore toStore = storeDAO
													.getByBusinessStoreName(tostoreName);
										%>
										<input type="hidden" value="<%=fromStore.getStoreId()%>"
											name="fromStoreId">
										<input type="hidden" value="<%=toStore.getStoreId()%>"
											name="toStoreId">
										<span>取车门店：<%=fromStoreName%></span><span>还车门店：<%=tostoreName%></span>
									</li>
									<li>
										<span>取车时间：<%=beginTime%></span><span>还车时间：<%=endTime%></span>
									</li>
									<li>

										<span>取车地址：<%=fromStore.getStoreAddress()%></span><span>还车地址：<%=toStore.getStoreAddress()%></span>
									</li>
									<li style="padding-top: 5px;">
										<span class="store-title">订单说明：</span>
									</li>
									<li class="rule-toggle">
										<a href="javascript:void(0);" id="gz">退改规则</a>不限公里数，超时费50元/小时,预授权5000元（可退）,违章押金2000元（可退）。
									</li>
									<li style="color: red;">
										温馨提示：
										<a href="http://www.1hai.cn/help/xxsm.aspx#clfs-FJ"
											title="限行城市规则" style="color: red;" target="_blank">点击阅读限行城市规则</a>
									</li>
								</ul>
							</div>
						</div>


						<div class="payment-box">
							<div class="title">
								支付方式
							</div>
							<div class="payment-list">
								<ul>
									<li>
										<label>
											<input name="pay" id="OnLine" checked="checked"
												class="ba-analysis" type="radio">
											在线支付
											<span> 请在1小时内完成在线支付 </span>
										</label>
									</li>
								</ul>
							</div>
						</div>
						<div class="service-box">
							<div class="title">
								其他服务
							</div>
							<div class="service-list">
								<ul>
									<%
										ArrayList<String[]> insuranceList = (ArrayList<String[]>) request
												.getAttribute("insuranceList");
										for (int i = 0; i < insuranceList.size(); i++) {
											String[] insurance = insuranceList.get(i);
											if(i==0){
											
									%>
									<li>
										<!-- 此处value 中的值为 保险编号id-保险价格price -->
										<input name="insurancePrice"
											value="<%=insurance[0]%>-<%=insurance[2]%>-<%=insurance[1]%>"
											class="ba-analysis" type="checkbox" checked="checked">
										<div class="service-name">
											<%=insurance[1]%>
										</div>
										<div class="service-price">
											￥<%=insurance[2]%>
											<span></span>
										</div>
										<div class="service-des">
											<%=insurance[3]%>
										</div>
									</li>
											<%}else{ %>
									<li>
										<!-- 此处value 中的值为 保险编号id-保险价格price -->
										<input name="insurancePrice"
											value="<%=insurance[0]%>-<%=insurance[2]%>-<%=insurance[1]%>"
											class="ba-analysis" type="checkbox" >
										<div class="service-name">
											<%=insurance[1]%>
										</div>
										<div class="service-price">
											￥<%=insurance[2]%>
											<span></span>
										</div>
										<div class="service-des">
											<%=insurance[3]%>
										</div>
									</li>
										<%} %>
									<%
										}
									%>

								</ul>
							</div>
						</div>
						<div class="invoice-box" id="invoiceList">

							<!--step2 设置常用联系人信息-->
							<div class="remark-box">
								<div class="title">
									联系人
								</div>
								<div class="remark-list">
									<p>
										<%
											ArrayList<FrequentContacts> contactsList = (ArrayList<FrequentContacts>) request
													.getAttribute("contactsList");
											for (int i = 0; i < contactsList.size(); i++) {
												FrequentContacts contacts = contactsList.get(i);
										%>
										<input type="radio" name="contactsId" id="contactsId"
											value="<%=contacts.getFrequentId()%>">
										<label for="radio">
											<%=contacts.getFrequentName()%>
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%
											}
										%>
									</p>

								</div>
							</div>
							<!--step2 设置常用联系人信-->

							<!--step2 备注信息-->
							<div class="remark-box">
								<div class="title">
									备注信息
								</div>
								<div class="remark-list">
									<%
										String[] isSongCheShangMen = (String[])session.getAttribute("isSongCheShangMen");
										if(isSongCheShangMen!=null){
									 %>
									<p>
										上车送门地址：<input type="text" name="songCheShangMenAddress" style="width: 300px;height: 30px;"/>
										</p>
									<%} %>
									<%
										String[] isShangMenQuChe = (String[])session.getAttribute("isShangMenQuChe");
										if(isShangMenQuChe!=null){
										
									 %>
									<p>上门取车地址：<input type="text" name="shangMenQuCheAddress" style="width: 300px;height: 30px;"/></p>
									<%} %>
									<p>
										添加备注
									</p>
									<textarea class="remark-txt1" id="remark1" name="remark"
										cols="80" rows="6"></textarea>
								</div>
							</div>
							<!--step2 备注信息-->
							<center>

								<button type="submit"
									style="background-image: url('<%=basePath%>member/images/tijiaodingdan.png');width:185px;
										height:54px;">

								</button>
							</center>
						</div>
					</div>
				</div>
			</form>
			<!--footer-->
			<div class="footer">
				<div class="footer-box">
					<div class="footer-left">
						<p>
							<a href="http://www.1hai.cn/aboutus.aspx" rel="nofollow"
								target="_blank">关于一嗨</a> |
							<a href="http://www.1hai.cn/job/index.aspx" target="_blank">招募英才</a>
							|
							<a href="http://www.1hai.cn/activity/mobile.aspx" target="_blank">移动客户端</a>
							|
							<a href="http://www.1hai.cn/help/index.aspx?from=foot"
								rel="nofollow" target="_blank">帮助中心</a> |
							<a href="http://www.1hai.cn/help/advice.aspx" rel="nofollow"
								target="_blank">建议专区</a> |
							<a href="http://www.1hai.cn/help/contactus.aspx" rel="nofollow"
								target="_blank">联系我们</a> |
							<a href="http://www.1hai.cn/more/link.aspx" rel="nofollow"
								target="_blank">友情链接</a> | &nbsp;投诉通道：400-821-1608&nbsp;
						</p>
						<p>
							Copyright © 2022 基于Java web的校园电动车租赁系统
							<a href="#" target="_blank"
								rel="nofollow">2022</a>
						</p>
					</div>
				</div>
			</div>
			<!--footer-->

			<script src="<%=basePath%>member/script/jquery"></script>
			<script src="<%=basePath%>member/script/secondStep"></script>
	</body>
</html>
