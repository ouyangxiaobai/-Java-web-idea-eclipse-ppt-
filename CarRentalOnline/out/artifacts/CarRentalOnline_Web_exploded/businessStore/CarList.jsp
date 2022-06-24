<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>电动车</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
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
	function del(state) {

		var msg = "您真的确定要停用该车辆吗？\n\n请确认！";

		if (state == 0) {
			var msg = "您真的确定要启用该车辆吗？\n\n请确认！";
		}

		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>


	</head>
	<body>
		<div id="table" class="mt10">
			<div class="box span10 oh">
				<table border="0" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<th width="120">
							电动车实物
						</th>
						<th width="45">
							车牌号
						</th>
						<th width="70">
							电动车品牌
						</th>
						<th width="70">
							电动车型号
						</th>
						<%--<th width="70">--%>
							<%--车型--%>
						<%--</th>--%>
						<th width="40">
							租金/天
						</th>
						<%--<th width="60">--%>
							<%--排挡--%>
						<%--</th>--%>
						<%--<th width="60">--%>
							<%--车排量--%>
						<%--</th>--%>
						<%--<th width="40">--%>
							<%--厢数--%>
						<%--</th>--%>
						<%--<th width="40">--%>
							<%--座数--%>
						<%--</th>--%>
						<%--<th width="80">--%>
							<%--发动机号--%>
						<%--</th>--%>
						<%--<th width="80">--%>
							<%--车架号--%>
						<%--</th>--%>
						<th width="90">
							购入日期
						</th>
						<th width="40">
							状态
						</th>
						<th width="80">
							操作
						</th>

					</tr>

					<%
						ArrayList<String[]> carList = (ArrayList<String[]>) request
								.getAttribute("carList");
						if (carList != null) {
							for (int i = 0; i < carList.size(); i++) {
								String[] car = carList.get(i);
					%>

					<tr class="tr">
						<td class="td_center">
							<input name="" type="image" width="150px" height="110px"
								src="<%=basePath%>uploadFiles/car/<%=car[14]%>" />
						</td>
						<td class="td_center">
							<%=car[2]%>

						</td>
						<td class="td_center">
							<%=car[3]%>
						</td>
						<td class="td_center">
							<%=car[4]%>
						</td>
						<%--<td class="td_center">--%>
							<%--<%=car[5]%>--%>
						<%--</td>--%>

						<td class="td_center">
							<%=car[6]%>
						</td>
						<%--<td class="td_center">--%>
							<%--<%=car[7]%>--%>
						<%--</td>--%>
						<%--<td class="td_center">--%>
							<%--<%=car[8]%>--%>
						<%--</td>--%>
						<%--<td class="td_center">--%>

							<%--<%=car[10]%>--%>
						<%--</td>--%>
						<%--<td class="td_center">--%>
							<%--<%=car[11]%>--%>
						<%--</td>--%>

						<%--<td class="td_center">--%>
							<%--<%=car[12]%>--%>
						<%--</td>--%>
						<%--<td class="td_center">--%>
							<%--<%=car[13]%>--%>
						<%--</td>--%>
						<td class="td_center">
							<%=car[9]%>
						</td>
						<td class="td_center">
							<%
								if (car[15].equals("0")) {
							%>
							<strong> <label style="color: red;">
									停用
								</label> </strong>
							<%
								} else {
							%>
							使用
							<%
								}
							%>
						</td>
						<td class="td_center">
							<%
								if (car[15].equals("0")) {
							%>
							<form
								action="<%=basePath%>servlet/CarServlet?method=updateCarState&state=0&carId=<%=car[0]%>"
								method="post">
								<input type="submit" name="button" class="btn btn82 btn_del"
									value="使用" onclick="return del(0)">
							</form>
							<%
								} else {
							%>
							<form
								action="<%=basePath%>servlet/CarServlet?method=updateCarState&state=1&carId=<%=car[0]%>"
								method="post">
								<input type="submit" name="button" class="btn btn82 btn_del"
									value="停用" onclick="return del(1)">
							</form>
							<%
								}
							%>


							<form
								action="<%=basePath%>servlet/CarServlet?method=showCarInfoById&carId=<%=car[0]%>"
								method="post">
								<input style="margin-top: 10px;" type="submit" name="button"
									class="btn btn82 btn_add" value="编辑">
							</form>
						</td>
					</tr>

					<%
						}
						}
					%>


				</table>
				<div style="margin-top: 10px;">
					<%=request.getAttribute("pageTool")%></div>
			</div>
		</div>
	</body>
</html>
