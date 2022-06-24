<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cuc.model.BusinessStore"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	BusinessStore store = (BusinessStore) session.getAttribute("store");
	if (store == null) {
		response
				.getWriter()
				.write(
						"<script language =javascript>window.open('../member/login.jsp','_top')</script>");

		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>商家基本资料</title>

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
		<style type="text/css">
body table {
	color: #424242;
}
</style>
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

		<div class="container">

			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">商家基本信息</b>
						</div>
						<center>
							<div class="box_center"  >

								<table width="800" border="0" style="margin-bottom: 100px;">
									<tr>
										<td width="320" rowspan="6">
											<img name="" 
												src="<%=basePath%>uploadFiles/<%=store.getStoreImage()%>"
												width="300px" height="250px" />
											&nbsp;
										</td>
										<td width="138" height="50">
											<strong>登录账号：</strong>
										</td>
										<td width="328" height="50">
											<%=store.getStoreNo()%>
										</td>
									</tr>
									<tr>
										<td height="50">
											<strong>线下门店名称：</strong>
										</td>
										<td height="50">
											<%=store.getStoreName()%>
										</td>
									</tr>
									<tr>
										<td height="50">
											<strong>联系方式：</strong>
										</td>
										<td height="50">
											<%=store.getStorePhone()%>
										</td>
									</tr>
									<tr>
										<td height="50">
											<strong>所在城市：</strong>
										</td>
										<td height="50">
											<%=store.getStoreProvince()%>
											<%=store.getStoreCity()%>
											<%=store.getStoreDistrict()%>
										</td>
									</tr>
									<tr>
										<td height="50">
											<strong>详细位置：</strong>
										</td>
										<td height="50">
											<%=store.getStoreAddress()%>
										</td>
									</tr>

								</table>
							</div>
						</center>
					</div>
				</div>
			</div>

		</div>
	</body>
</html>
