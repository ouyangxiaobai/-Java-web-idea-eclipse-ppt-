<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.dao.ICarDAO"%>
<%@page import="com.cuc.dao.imp.CarDAO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<link href="<%=basePath%>member/css/bootstrap.css" rel="stylesheet">
		<link href="<%=basePath%>member/css/Global.css" rel="stylesheet">

	</head>

	<body>
		<div>

			<div id="content">
				<div class="content-bd">
					<div class="col-main">
						<div class="mod J_TabSwitch order-list self-order">
							<div class="hd clearfix">
								<h2>
									用户车辆订单
								</h2>
								<ul class="ui-tab-hd">
									<li class="ck">
										<a class="orderTab" href="javascript:void(0)">全部订单</a>
									</li>
									<li class="">
										<a class="orderTab" href="javascript:void(0)">待付款</a>
									</li>
									<li class="">
										<a class="orderTab" href="javascript:void(0)">未出车</a>
									</li>
									<li class="">
										<a class="orderTab" href="javascript:void(0)">租赁中</a>
									</li>
									<li>
										<a class="orderTab" href="javascript:void(0)">待还车</a>
									</li>
									<li>
										<a class="orderTab" href="javascript:void(0)">已完成</a>
									</li>
									<li>
										<a class="orderTab" href="javascript:void(0)">已取消</a>
									</li>
								</ul>
							</div>
							<div class="bd">

								<div class="ui-tab-bd" >
									<div class="ui-table-list all"
										style="display: block;">
										<table width="100%">
											<tbody>
												<tr >
													<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> allOrderList = (ArrayList<String[]>) request
															.getAttribute("allOrderList");
													if (allOrderList != null) {
														for (String[] order : allOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=order[4]%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td >

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
									<div class="ui-table-list reserving"
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
												<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> daiZhiFuOrderList = (ArrayList<String[]>) request
															.getAttribute("daiZhiFuOrderList");
													if (daiZhiFuOrderList != null) {
														for (String[] order : daiZhiFuOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=4%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
									<div class="ui-table-list renting" data-status="Lease"
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
												<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> daiChuCheOrderList = (ArrayList<String[]>) request
															.getAttribute("daiChuCheOrderList");
													if (daiChuCheOrderList != null) {
														for (String[] order : daiChuCheOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=4%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
									<div class="ui-table-list complete" 
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
												<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> zuLinZhongOrderList = (ArrayList<String[]>) request
															.getAttribute("zuLinZhongOrderList");
													if (zuLinZhongOrderList != null) {
														for (String[] order : zuLinZhongOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=4%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
									<div class="ui-table-list canceled"
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
										<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> daiHuanCheOrderList = (ArrayList<String[]>) request
															.getAttribute("daiHuanCheOrderList");
													if (daiHuanCheOrderList != null) {
														for (String[] order : daiHuanCheOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=4%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
									<div class="ui-table-list pending"
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
											<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> yiWanChengOrderList = (ArrayList<String[]>) request
															.getAttribute("yiWanChengOrderList");
													if (yiWanChengOrderList != null) {
														for (String[] order : yiWanChengOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="#"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>

									<div class="ui-table-list pending"
										style="display: none;">
										<table width="100%">
											<tbody>
												<tr>
												<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="20%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="18%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="18%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
												</tr>
												<%
													ArrayList<String[]> yiQuXiaoOrderList = (ArrayList<String[]>) request
															.getAttribute("yiQuXiaoOrderList");
													if (yiQuXiaoOrderList != null) {
														for (String[] order : yiQuXiaoOrderList) {
												%>

												<tr>
													<td class="row-first-cell" style="font-size: 20px;">
														<a href="<%=basePath %>servlet/OrderServlet?method=orderDetail&orderId=<%=order[0]%>&carId=<%=4%>"><%=order[0]%></a>
														<br>
													</td>
													<td style="font-size: 15px;">
														<%
															ICarDAO carDAO = new CarDAO();
																	Map<String, Object> carMap = carDAO.searchByCarId(Integer
																			.parseInt(order[4]));
														%>
														<%=carMap.get("carBrand")%><%=carMap.get("carType")%>
														<%--/<%=carMap.get("carForm")%>/<%=carMap.get("carGear")%>/<%=carMap.get("carDisplacement")%>--%>

														<br>
													</td>

													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[8]%></strong>
														</font>
														<br>
													</td>
													<td style="font-size: 15px;">
														<font color="#ff8000"> <strong>¥<%=order[9]%></strong>
														</font>
													</td>

													<td style="font-size: 15px;">
														<%=order[6].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<%=order[7].substring(0, 19)%>
													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[10]%></span>
													</td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Script -->
		<script src="<%=basePath%>member/script/jquery"></script>
		<script src="<%=basePath%>member/script/bootstrap"></script>

		<script src="<%=basePath%>member/script/global.js"></script>



	</body>
</html>
