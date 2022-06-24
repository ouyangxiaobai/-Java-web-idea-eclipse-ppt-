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
						"<script language =javascript>window.open('../member/welcome.jsp','_top')</script>");

		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>营业门店信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"
			src="<%=basePath%>member/script/jquery-1.7.2.min.js"></script>
		<link href="<%=basePath%>member/css/xiniu.css" rel="stylesheet"
			type="text/css">
		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/main.css">
		<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
		if (myform.password.value.length == 0) {
			alert("请填写密码！");
			myform.password.focus();
			return false;
		}
		if (myform.password.value!=<%=store.getStorePassword()%> ) {
			alert("密码不正确！");
			myform.password.focus();
			return false;
		}
		if (myform.tbPassword.value.length == 0) {
			alert("请填写新密码！");
			myform.tbPassword.focus();
			return false;
		}
		if (myform.againstPassword.value.length == 0) {
			alert("请再次填写新密码！");
			myform.againstPassword.focus();
			return false;
		}
		if (myform.tbPassword.value != myform.againstPassword.value) {
			alert("两次密码不一样！");
			myform.tbPassword.focus();
			return false;
		}
		return true;
	}
</SCRIPT>
	</head>

	<body>
		<div class="container">
			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">修改密码</b>
						</div>
						<center>
							<form
								action="<%=basePath%>servlet/StoreServlet?method=changePassword&id=<%=store.getStoreId()%>"
								method="post" id="form1" onSubmit="return validate(this);">
								<table style="margin-top: 30px;" border="0">
									<tr>
										<td style="width: 200px;">
											<div class="ywz_zhuce_youjian">
												原密码：
											</div>
										</td>
										<td style="width: 200px;">
											<div class="ywz_zhuce_kuangzi">
												<input name="password" type="password" id="password"
													class="ywz_zhuce_kuangwenzi1">
											</div>
										</td>
									</tr>
									<tr>
										<td style="width: 200px;">
											<div class="ywz_zhuce_youjian">
												设置密码：
											</div>
										</td>
										<td>
											<div class="ywz_zhuce_xiaoxiaobao">
												<div class="ywz_zhuce_kuangzi">
													<input name="tbPassword" type="password" id="tbPassword"
														class="ywz_zhuce_kuangwenzi1">
												</div>
												<div class="ywz_zhuce_huixian" id="pwdLevel_1"></div>
												<div class="ywz_zhuce_huixian" id="pwdLevel_2"></div>
												<div class="ywz_zhuce_huixian" id="pwdLevel_3"></div>
												<div class="ywz_zhuce_hongxianwenzi">
													弱
												</div>
												<div class="ywz_zhuce_hongxianwenzi">
													中
												</div>
												<div class="ywz_zhuce_hongxianwenzi">
													强
												</div>
											</div>
										</td>
										<td>
											<div class="ywz_zhuce_yongyu1">
												<span id="pwd_tip" style="color: #898989"><font
													style="color: #F00">*</font> 6-16位，由字母（区分大小写）、数字、符号组成</span>
												<span id="pwd_err" style="color: #f00; display: none;">6-16位，由字母（区分大小写）、数字、符号组成</span>
											</div>
										</td>
									</tr>

									<tr>
										<td>
											<div class="ywz_zhuce_youjian">
												确认密码：
											</div>
										</td>
										<td>
											<div class="ywz_zhuce_kuangzi">
												<input name="againstPassword" type="password"
													id="againstPassword" class="ywz_zhuce_kuangwenzi1">

											</div>
										</td>
									</tr>
								</table>
								<div
									style="clear: both; padding-top: 30px; padding-bottom: 50px;">
									<input type="submit" name="submit" value=" "
										class="button_blue">
								</div>
							</form>
							<script type="text/javascript"
								src="<%=basePath%>member/script/pwd.js"></script>

						</center>
					</div>
				</div>
			</div>

		</div>

	</body>
</html>
