<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<link href="<%=basePath%>member/css/zc1css.css" rel="stylesheet"
			type="text/css">
		<link href="<%=basePath%>member/css/base.css" rel="stylesheet"
			type="text/css">

		<script type="text/javascript"
			src="<%=basePath%>member/script/jquery-1.7.2.min.js"></script>



		<style>
body,td,th {
	font-family: Arial, "微软雅黑";
}

body {
	background: #f3f5f5;
}

.pplist {
	padding: 10px;
}

.pplist div {
	margin: 0px 20px 10px 0px;
	width: 120px;
	display: block;
	float: left
}

.btys {
	float: right;
	margin-right: 20px;
	width: 100px;
	border: none;
	height: 30px;
	background: #de1921;
	color: #fff;
	text-align: center;
	line-height: 30px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	cursor: pointer;
	margin-top: 30px;
}
</style>
		<title>短租自驾</title>

	</head>

	<body>
		<style type="text/css">
#nav {
	margin: 5px;
	width: 970px;
	height: 50px;
	margin-top: 33px;
	margin-left: 65px;
	line-height: 60px;
	font-size: 18px;
	font-family: "微软雅黑", Arial, Helvetica, sans-serif;
}

#nav ul {
	float: left;
	width: 650px;
}

#nav li {
	position: relative;
	list-style: none;
	width: 98px;
	float: left;
	height: 50px;
}

#nav li a {
	padding: 5px;
	width: 90px;
	color: #000000;
	text-decoration: none;
	height: 38px;
	display: block;
	text-align: center;
	line-height: 40px;
}

#nav li a:hover {
	line-height: 40px;
	height: 38px;
	border-bottom: 2px #FF0000 solid;
}

#nav ul ul {
	position: absolute;
	top: 50px;
	left: 0px;
	background: #FFF;
	visibility: hidden;
}

#nav ul :hover ul {
	visibility: visible;
	width: 100px;
	z-index: 9999999999;
}

#nav ul ul li a {
	display: block;
	width: 90px;
	margin: 0;
	height: 38px;
	float: left;
}

#nav table {
	position: absolute;
	top: 0;
	left: 0;
}

hr {
	display: none;
}
</style>
		<jsp:include page="Header.jsp" />
		<table style="margin: auto" align="center" border="0" width="1100">
			<tbody>
				<%--<tr>--%>
					<%--<td align="center">--%>
						<%--<img style="margin-top: 10px;"--%>
							<%--src="<%=basePath%>member/images/searchCarBG.jpg">--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td>
						<div class="main_wrap">
							<div class="carlist" style="margin: auto; width: 1116px">
								<div class="orderinfo"
									style="height: auto; padding-bottom: 30px;">
									<div class="title">
										您已预订的信息如下
									</div>
									<div class="forminfo">
										<div class="col_2">
											<div class="nameinfo">
												取车时间：<%=session.getAttribute("beginTime")%>
											</div>
										</div>
										<div class="col_2">
											<div class="nameinfo">
												还车时间：<%=session.getAttribute("endTime")%>
											</div>
										</div>
										<div class="col_2">
											<div class="nameinfo">
												取车门店：<%=session.getAttribute("fromStoreName")%>
											</div>
										</div>
										<div class="col_2">
											<div class="nameinfo">
												还车门店：<%=session.getAttribute("tostoreName")%>
											</div>
										</div>

										<div class="nameinfo "
											style="width: 100%; margin-top: 20px; text-align: center">
											<a id="showtext2" href="<%=basePath%>member/welcome.jsp">修改预订信息</a>
										</div>
									</div>

									<p class="alert" style="display: block;">
										友情提示：请合理安排您的用车时间
									</p>

									<!--预订信息结束-->

								</div>
								<form name="form1" id="form1" method="post"
									action="<%=basePath%>servlet/CarServlet?method=searchByCondition">
									<div class="brand" style="height: auto;">
										<table align="center" border="0" cellpadding="0"
											cellspacing="0" width="1019">
											<tbody>
												<%--<tr>--%>
													<%--<td class="nameinfo pplist" align="right" valign="top"--%>
														<%--width="92">--%>
														<%--品牌：--%>
													<%--</td>--%>
													<%--<td class="pplist" align="left" valign="top" width="927">--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="不限" checked="checked"--%>
																	<%--type="checkbox">--%>
																<%--&nbsp;不限--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="奥迪" type="checkbox">--%>
																<%--&nbsp;奥迪--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="宝马" type="checkbox">--%>
																<%--&nbsp;宝马--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="宝骏" type="checkbox">--%>
																<%--&nbsp;宝骏--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="保时捷" type="checkbox">--%>
																<%--&nbsp;保时捷--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="奔腾" type="checkbox">--%>
																<%--&nbsp;奔腾--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="比亚迪" type="checkbox">--%>
																<%--&nbsp;比亚迪--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="奔驰" type="checkbox">--%>
																<%--&nbsp;奔驰--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="本田" type="checkbox">--%>
																<%--&nbsp;本田--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="标致" type="checkbox">--%>
																<%--&nbsp;标致--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="别克" type="checkbox">--%>
																<%--&nbsp;别克--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="长城" type="checkbox">--%>
																<%--&nbsp;长城--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="长安" type="checkbox">--%>
																<%--&nbsp;长安--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="东风" type="checkbox">--%>
																<%--&nbsp;东风--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="大众" type="checkbox">--%>
																<%--&nbsp;大众--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="丰田" type="checkbox">--%>
																<%--&nbsp;丰田--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="福特" type="checkbox">--%>
																<%--&nbsp;福特--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="海马" type="checkbox">--%>
																<%--&nbsp;海马--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="红旗" type="checkbox">--%>
																<%--&nbsp;红旗--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="吉利" type="checkbox">--%>
																<%--&nbsp;吉利--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="捷豹" type="checkbox">--%>
																<%--&nbsp;捷豹--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="凯迪拉克" type="checkbox">--%>
																<%--&nbsp;凯迪拉克--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="铃木" type="checkbox">--%>
																<%--&nbsp;铃木--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="马自达" type="checkbox">--%>
																<%--&nbsp;马自达--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="起亚" type="checkbox">--%>
																<%--&nbsp;起亚--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="斯柯达" type="checkbox">--%>
																<%--&nbsp;斯柯达--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="雪铁龙" type="checkbox">--%>
																<%--&nbsp;雪铁龙--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="雪佛兰" type="checkbox">--%>
																<%--&nbsp;雪佛兰--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="pp" value="日产" type="checkbox">--%>
																<%--&nbsp;日产--%>
															<%--</label>--%>
														<%--</div>--%>
													<%--</td>--%>
												<%--</tr>--%>
												<tr>
													<td class="nameinfo pplist" align="right" valign="top">
														价格：
													</td>
													<td class="pplist" align="left" valign="top">
														<div>
															<label>
																<input name="hyj" value="不限" checked="checked"
																	type="checkbox">
																&nbsp;不限
															</label>
														</div>
														<div>
															<label>
																<input name="hyj" value="0-3" type="checkbox">
																&nbsp;0-3元
															</label>
														</div>
														<div>
															<label>
																<input name="hyj" value="3-6" type="checkbox">
																&nbsp; 4-6元
															</label>
														</div>
														<div>
															<label>
																<input name="hyj" value="6-9" type="checkbox">
																&nbsp;7-9元
															</label>
														</div>
														<div>
															<label>
																<input name="hyj" value="9-12" type="checkbox">
																&nbsp;10-12元
															</label>
														</div>
													</td>
												</tr>
												<%--<tr>--%>
													<%--<td class="nameinfo pplist" align="right" valign="top">--%>
														<%--变速箱：--%>
													<%--</td>--%>
													<%--<td class="pplist" align="left" valign="top">--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="bsx" value="不限" checked="checked"--%>
																	<%--type="checkbox">--%>
																<%--&nbsp;不限--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="bsx" value="自动" type="checkbox">--%>
																<%--&nbsp;自动--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="bsx" value="手动" type="checkbox">--%>
																<%--&nbsp;手动--%>
															<%--</label>--%>
														<%--</div>--%>

													<%--</td>--%>
												<%--</tr>--%>
												<%--<tr>--%>
													<%--<td class="nameinfo pplist" align="right" valign="top">--%>
														<%--座位：--%>
													<%--</td>--%>
													<%--<td class="pplist" align="left" valign="top">--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="cx" value="0" checked="checked"--%>
																	<%--type="checkbox">--%>
																<%--&nbsp;不限--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="cx" value="2" type="checkbox">--%>
																<%--&nbsp;2座--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="cx" value="4" type="checkbox">--%>
																<%--&nbsp;4座--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="cx" value="5" type="checkbox">--%>
																<%--&nbsp;5座--%>
															<%--</label>--%>
														<%--</div>--%>
														<%--<div>--%>
															<%--<label>--%>
																<%--<input name="cx" value="7" type="checkbox">--%>
																<%--&nbsp;7座--%>
															<%--</label>--%>
														<%--</div>--%>
													<%--</td>--%>
												<%--</tr>--%>

												<tr>
													<td colspan="2" style="text-align: center; height: 40px;"
														align="center" valign="middle">
														<input class="btys" value="筛选" type="submit">
													</td>
												</tr>
											</tbody>
										</table>
										<p>
											&nbsp;
										</p>
									</div>
								</form>
								<div class="carinfo">
									<ul class="filter_car">
										<li class="default choose" id="js_DefaultSort">
											匹配车型
										</li>
									</ul>
									<div class="pick_car">
										<ul id="CarList">
											<%
												ArrayList<String[]> carList = (ArrayList<String[]>) request
														.getAttribute("carList");
												if (carList == null && carList.size() == 0) {
											%>
											<!-- 查询的车辆为空 -->
											<%
												} else {
													for (int i = 0; i < carList.size(); i++) {
														String[] car = carList.get(i);
											%>


											<li style="z-index: 213;">
												<div class="car_img">
													<a href="http://www.1zc.net.cn/detail.php?id=26"><img
															src="<%=basePath%>uploadFiles/car/<%=car[14]%>"
															height="107" width="182"> </a>
												</div>
												<div class="attribute">
													<span class="car_name"><a
														href="http://www.1zc.net.cn/detail.php?id=26"><strong><%=car[3]%><%=car[4]%></strong>
													</a> </span>
													<div class="clear"></div>
													<p class="attr">
														<%--<%=car[7]%>--%>
														<%--<%=car[8]%>|<%=car[10]%>|<%=car[11]%>座--%>
													</p>
													<div class="clear"></div>
													<ul class="property">
														<li style="z-index: 223;">
															基本保险：
															<span class="red">40</span> 元/天
														</li>
														<li style="border-bottom-style: none; z-index: 221;">
															限公里数：
															<span class="red">无限制</span> 公里
														</li>
													</ul>
												</div>
												<div class="price" style="position: relative; z-index: 98">
													<div class="priceinner">
														<div class="day">
															￥
															<span class="red"><%=car[6]%></span>/天
														</div>
													</div>
												</div>
												<form
													action="<%=basePath%>servlet/CarServlet?method=orderCar&carId=<%=car[0]%>"
													method="post">
													<input name="按钮" class="book js_btnBooking "
														style="border: none;" value="立即预订" type="submit">
                                                    <%--<input name="按钮" class="book js_btnBooking "--%>
                                                           <%--style="border: none;background: blue" value="收藏" type="submit">--%>
												</form>
											</li>

											<%
												}
												}
											%>

										</ul>
									</div>
								</div>
								<dir style="margin: 20px;">
<%
String pageTool = (String)request.getAttribute("pageTool");
if(pageTool!=null){
 %>
									<%=request.getAttribute("pageTool")%>
<%} %>
								</dir>


							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="footer" style="margin-top: 0">

			<div class="footerBtm">
				<p>
					<a href="#">关于易租车</a>|
					<a href="#">免责声明</a>|
					<a href="#">版权申明</a>|
					<a href="#">帮助中心</a>|
					<a href="#">网站联盟</a>|
					<a href="#">友情链接</a>|
					<a href="http://www.1zc.net.cn/Home/NewsList?t_id=43">租车攻略</a>
				</p>
				<p>
					Copyright&#169;2011-2015 dafang24.com All Rights Reserved.
					闽ICP备11005157号-3
				</p>

			</div>
			<!-- footerBtm -->
		</div>


	</body>
</html>
