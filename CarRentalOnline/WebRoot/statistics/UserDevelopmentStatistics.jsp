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
							<b class="pl15">用户发展情况</b>
						</div>
						<center>
							<form
								action="<%=basePath%>servlet/RegisterServlet?method=searchMonthRegisterByConditon"
								method="post">
								<div class="box_center pt10 pb10">

									<table class="form_table" border="0" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr>

												<td>
													按月份：
													<select style="width: 70px; font-size: 16px;" name="beginYear"
														class="select">
														<option>
															2022
														</option>
														<option>
															2021
														</option>
														<option>
															2020
														</option>
														<option>
															2019
														</option>
														<option>
															2018
														</option>
														<option>
															2017
														</option>
														<option>
															2016
														</option>
													</select>
													&nbsp;
													<select style="width: 50px; font-size: 16px;" name="beginMonth"
														class="select">
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
													<select style="width: 70px; font-size: 16px;" name="endYear"
														class="select">
														<option>
															2022
														</option>
														<option>
															2021
														</option>
														<option>
															2020
														</option>
														<option>
															2019
														</option>
														<option>
															2018
														</option>
														<option>
															2017
														</option>
														<option>
															2016
														</option>
													</select>
													&nbsp;
													<select style="width: 50px; font-size: 16px;" name="endMonth"
														class="select">
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
								action="<%=basePath%>servlet/RegisterServlet?method=searchDayRegisterByConditon"
								method="post" style="margin-top: -20px;">
								<div class="box_center pt10 pb10">
									<table class="form_table" border="0" cellpadding="0"
										cellspacing="0">
										<tbody>
											<%--<tr>--%>
												<%--<td>--%>
													<%--按日期：--%>
												<%--</td>--%>
												<%--<td>--%>
													<%--<input class="laydate-icon" onclick="laydate()"--%>
														<%--style="width: 150px; height: 30px; font-size: 16px;"--%>
														<%--name="beginTime">--%>

													<%--至--%>
													<%--<input class="laydate-icon" id="demo"--%>
														<%--style="width: 150px; height: 30px; font-size: 16px;"--%>
														<%--name="endTime">--%>
													<%--&nbsp;&nbsp;&nbsp;--%>
												<%--</td>--%>

												<%--<td>--%>
													<%--<input type="submit" name="button"--%>
														<%--class="btn btn82 btn_search" value="查询">--%>
												<%--</td>--%>
											<%--</tr>--%>
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
													年份
												</th>
												<th width="40">
													月份
												</th>
												<th width="40">
													日期
												</th>
												<th width="40">
													注册用户量
												</th>
											</tr>
											<%
												ArrayList<String[]> dayRegisterList = (ArrayList<String[]>)request.getAttribute("dayRegisterList");
												if(dayRegisterList!=null){
													for(int i=0;i< dayRegisterList.size();i++){
														String[] dayRegister = dayRegisterList.get(i);
											 %>
											<tr class="tr">
												<td class="td_center">
													<%=dayRegister[0] %>
												</td>
												<td class="td_center">
													<%=dayRegister[1] %>
												</td>
												<td class="td_center">
													<%=dayRegister[2] %>
												</td>
												<td class="td_center">
													<%=dayRegister[3] %>
												</td>
												<td class="td_center">
													<%=dayRegister[4] %>
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
