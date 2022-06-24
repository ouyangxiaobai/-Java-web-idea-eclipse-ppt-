<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cuc.model.SysAdmin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'SysAdminInfo.jsp' starting page</title>

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
function addSysAdminInfo() {
	var url ="<%=basePath%>servlet/SysAdminServlet?method=addSysAdminInfo";
		$.post(url, {
			"no" :$("#no").val(),
			"password" :$("#password").val(),
			"name" :$("#name").val(),
			"phone" :$("#phone").val(),
			"address" :$("#address").val()
		}, function(data) {
			alert(data);
		});
	}

	function ShowAllSysAdmin() {
		location.href = "ShowAllSysAdmin.jsp";
	}
</script>
	</head>

	<body>
		<%
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
		%>
		<div class="container">

			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<form
							action="<%=basePath%>servlet/SysAdminServlet?method=updateAdminInfo"
							method="post">
							<div class="box_top">
								<b class="pl15">企业管理人员信息</b>
								<input type="submit" name="button" style="float: right;"
									class="btn btn82 btn_config" value="保存">
								<input type="button" name="button" style="float: right;"
									class="btn btn82 btn_add" value="新增"
									onclick="addSysAdminInfo()">
								<input type="button" name="button" style="float: right;"
									class="btn btn82 btn_search" value="查看"
									onclick="ShowAllSysAdmin()">
							</div>
							<div class="box_center">

								<input name="id" type="hidden" value="<%=admin.getSId()%>">

								<table class="form_table pt15 pb15" width="100%" border="0"
									cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_right">
											账号：
										</td>
										<td class="">
											<input type="text" name="no" id="no" class="input-text lh30"
												size="40" value="<%=admin.getSNo()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											密码：
										</td>
										<td class="">
											<input type="password" name="password" id="password"
												class="input-text lh30" size="40"
												value="<%=admin.getSPassword()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											姓名：
										</td>
										<td class="">
											<input type="text" name="name" id="name"
												class="input-text lh30" size="40"
												value="<%=admin.getSName()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											电话：
										</td>
										<td class="">
											<input type="text" name="phone" id="phone"
												class="input-text lh30" size="40"
												value="<%=admin.getSPhone()%>">
									</tr>
									<tr>
										<td class="td_right">
											联系地址：
										</td>
										<td class="">
											<textarea name="address" id="address" id="address" cols="30"
												rows="10" class="textarea"><%=admin.getSAddress()%></textarea>
										</td>
									</tr>

								</table>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
