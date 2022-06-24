<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.dao.ICarDAO"%>
<%@page import="com.cuc.dao.imp.CarDAO"%>
<%@page import="com.cuc.dao.IBusinessStoreDAO"%>
<%@page import="com.cuc.dao.imp.BusinessStoreDAO"%>
<%@page import="com.cuc.model.BusinessStore"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="<%=basePath%>member/laydate/laydate.js"></script>
		<link rel="stylesheet" href="<%=basePath%>css/style.css" />
		<script type="text/javascript" src="<%=basePath%>script/tinybox.js"></script>
		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/main.css">
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/colResizable-1.3.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/common.js"></script>
		<script type="text/javascript">
	$( function() {
		$(".list_table").colResizable( {
			liveDrag :true,
			gripInnerHtml :"<div class='grip'></div>",
			draggingClass :"dragging",
			minWidth :30
		});

	});
</script>

	</head>

	<body>
		<div class="container" style="margin-top: -10px;">
			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">车辆租赁统计</b>
						</div>
						<center>
							<form
								action="<%=basePath%>servlet/CarRentalServlet?method=showMonthCarRentalByCondition"
								method="post">
								<div class="box_center pt10 pb10">

									<table class="form_table" border="0" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr>
												<td>
													按月份：
													<select style="width: 70px; font-size: 16px;"
														name="beginYear" class="select">
														<option>
															2016
														</option>
														<option>
															2015
														</option>
														<option>
															2014
														</option>
														<option>
															2013
														</option>
														<option>
															2012
														</option>
														<option>
															2011
														</option>
														<option>
															2010
														</option>
														<option>
															2009
														</option>
														<option>
															2008
														</option>
														<option>
															2007
														</option>
														<option>
															2006
														</option>
														<option>
															2005
														</option>
														<option>
															2004
														</option>
														<option>
															2003
														</option>
														<option>
															2002
														</option>
														<option>
															2001
														</option>
														<option>
															2000
														</option>
													</select>
													&nbsp;
													<select style="width: 50px; font-size: 16px;"
														name="beginMonth" class="select">
														<option>
															01
														</option>
														<option>
															02
														</option>
														<option>
															03
														</option>
														<option>
															04
														</option>
														<option>
															05
														</option>
														<option>
															06
														</option>
														<option>
															07
														</option>
														<option>
															08
														</option>
														<option>
															09
														</option>
														<option>
															10
														</option>
														<option>
															11
														</option>
														<option>
															12
														</option>

													</select>
													至
													<select style="width: 70px; font-size: 16px;"
														name="endYear" class="select">
														<option>
															2016
														</option>
														<option>
															2015
														</option>
														<option>
															2014
														</option>
														<option>
															2013
														</option>
														<option>
															2012
														</option>
														<option>
															2011
														</option>
														<option>
															2010
														</option>
														<option>
															2009
														</option>
														<option>
															2008
														</option>
														<option>
															2007
														</option>
														<option>
															2006
														</option>
														<option>
															2005
														</option>
														<option>
															2004
														</option>
														<option>
															2003
														</option>
														<option>
															2002
														</option>
														<option>
															2001
														</option>
														<option>
															2000
														</option>
													</select>
													&nbsp;
													<select style="width: 50px; font-size: 16px;"
														name="endMonth" class="select">
														<option>
															01
														</option>
														<option>
															02
														</option>
														<option>
															03
														</option>
														<option>
															04
														</option>
														<option>
															05
														</option>
														<option>
															06
														</option>
														<option>
															07
														</option>
														<option>
															08
														</option>
														<option>
															09
														</option>
														<option>
															10
														</option>
														<option>
															11
														</option>
														<option>
															12
														</option>

													</select>


												</td>
												<td>
													&nbsp;&nbsp;&nbsp;
													<input type="submit" name="button"
														class="btn btn82 btn_search" value="查询">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</form>
							<form
								action="<%=basePath%>servlet/CarRentalServlet?method=showDayCarRentalByCondition"
								method="post" style="margin-top: -20px;">
								<div class="box_center pt10 pb10">
									<table class="form_table" border="0" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr>
												<td>
													按日期：
												</td>
												<td>
													<input class="laydate-icon" onclick="laydate()"
														style="width: 150px; height: 30px; font-size: 16px;"
														name="beginTime">

													至
													<input class="laydate-icon" id="demo"
														style="width: 150px; height: 30px; font-size: 16px;"
														name="endTime">
													&nbsp;&nbsp;&nbsp;
												</td>

												<td>
													<input type="submit" name="button"
														class="btn btn82 btn_search" value="查询">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</form>

						</center>

						<div class="box_center">
							<center>
								<div id="table" class="mt10" style="width: 99%;">
									<div class="box span10 oh">
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="list_table">
											<tr>
												<th width="10">
													编号
												</th>
												<th width="30">
													车辆
												</th>
												<th width="30">
													车牌号
												</th>
												<th width="45">
													所属门店
												</th>
												<th width="20">
													类型
												</th>
												<th width="30">
													租金
												</th>
												<th width="30">
													年份
												</th>
												<th width="30">
													月份
												</th>
												
												<th width="30">
													租赁次数
												</th>
											</tr>
											<%
											ICarDAO carDAO = new CarDAO();
											IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
											
											
												ArrayList<String[]> carRentalList = (ArrayList<String[]>) request
														.getAttribute("carRentalList");
												if(carRentalList!=null){
													for(int i=0;i<carRentalList.size();i++){
														String[] carRental = carRentalList.get(i);
											%>
											<tr class="tr">
												<td class="td_center">
													<%=i+1 %>
												</td>
												<td class="td_center">
													<%
												   int carId = Integer.parseInt(carRental[0]);
											       Map<String, Object> carMap =   carDAO.searchByCarId(carId);
												 %>
													<%=carMap.get("carBrand") %><%=carMap.get("carType") %>
												</td>
												<td class="td_center">
													<%=carMap.get("carNumber") %>
												</td>
												<td class="td_center">
													<%
												   int storeId = Integer.parseInt(carMap.get("storeId").toString());
											        BusinessStore store =   storeDAO.getByBusinessStoreId(storeId);
												 %>
													<%=store.getStoreName() %>

												</td>
												<td class="td_center">
													<%=carMap.get("carForm") %>
												</td>
												<td class="td_center">
													<%=carMap.get("carMoney") %>
												</td>
												<td class="td_center">
													<%=carRental[1] %>
												</td>
												<td class="td_center">
													<%=carRental[2] %>
												</td>
												
												<td class="td_center">
													<%=carRental[3] %>
												</td>
												<%}} %>
											
										</table>
									</div>
								</div>
								<div style="margin: 30px;">
									<%=request.getAttribute("pageTool")%></div>

							</center>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
	;
	! function() {
		laydate( {
			elem :'#demo'
		})
	}();
</script>
	</body>
</html>
