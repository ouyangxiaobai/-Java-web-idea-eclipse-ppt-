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
									车辆订单
								</h2>
								<ul class="ui-tab-hd">

									<li class="">
										<a class="orderTab" href="javascript:void(0)">租赁中</a>
									</li>

								</ul>
							</div>
							<div class="bd">

								<div class="ui-tab-bd" >

									<div class="ui-table-list complete" 
										style="">
										<table width="100%">
											<tbody>
												<tr>
												<th class="row-first-cell" width="12%" style="font-size: 20px;">
														订单编号
													</th>
													<th width="10%" style="font-size: 20px;">
														电动车品牌
													</th>
													<th width="10%" style="font-size: 20px;">
														日租金
													</th>
													<th width="10%" style="font-size: 20px;">
														总租金
													</th>
													<th width="13%" style="font-size: 20px;">
														取车时间
													</th>
													<th width="13%" style="font-size: 20px;">
														还车时间
													</th>
													<th width="14%" style="font-size: 20px;">
														订单状态
													</th>
													<th width="10%" style="font-size: 20px;">
														报修状态
													</th>
													<th width="10%" style="font-size: 20px;">
														报修地点
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
													<td style="font-size: 15px;">
														<%
															if(order[15].equals("0")){
														%>
														<a onclick="baoxiu(<%=order[0]%>)" href="#">未报修</a>
														<br>
														<%}else if(order[15].equals("1")){ %>
														<span>报修中</span>
														<%}else if(order[15].equals("3")){ %>
														<span>维修完成</span>
														<%}else{ %>
														<span>维修中</span>
														<br>
														<%} %>

													</td>
													<td style="font-size: 15px;">
														<span class="orange"><%=order[16]%></span>
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
		<script language="javascript">
			function baoxiu(orderId){
				locationvar = prompt("请输入保修地点:","山东省临沂市");
				if (locationvar != null){
					console.log(locationvar)
					alert("报修成功！");
					window.location.href="http://localhost:8080/CarRentalOnline/servlet/OrderServlet?method=baoxiu&orderId="+orderId+"&location="+locationvar;
				}else{
					alert("你已经取消填写了！");
				}
			}
		</script>
		<!-- Script -->
		<script src="<%=basePath%>member/script/jquery"></script>
		<script src="<%=basePath%>member/script/bootstrap"></script>

		<script src="<%=basePath%>member/script/global.js"></script>



	</body>
</html>
