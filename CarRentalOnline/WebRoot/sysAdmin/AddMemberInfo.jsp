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
		<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
	
		if ($("#no").val().length == 0) {
			alert("账号不能为空！");
			$("#no").focus();
			return false;
		}

		if ($("#no").val().length < 6 || $("#no").val().length > 16) {
			alert("账号长度、格式错误！");
			$("#no").focus();
			return false;
		}
		

		if ($("#password").val().length == 0) {
			alert("密码不能为空！");
			$("#password").focus();
			return false;
		}

		if ($("#password").val().length < 6 || $("#password").val().length > 16) {
			alert("密码长度、格式错误！");
			$("#password").focus();
			return false;
		}

		if ($("#name").val().length == 0) {
			alert("姓名不能为空！");
			$("#name").focus();
			return false;
		}

		if ($("#name").val().length < 1 || $("#name").val().length > 4) {
			alert("姓名长度、格式错误！");
			$("#name").focus();
			return false;
		}

		if ($("#phone").val().length == 0) {
			alert("电话不能为空！");
			$("#phone").focus();
			return false;
		}


		if (!$("#phone").val().match(/^(((13[0-9]{1})|175|132|184)+\d{8})$/)) {
			alert("手机号码格式不正确！");
			$("#phone").focus();
			return false;
		}

		return true;
	}
	
	
	
	function isSameNo(){
	
		if($("#no").val().length!=0){
		
			var url="<%=basePath%>servlet/MemberServlet?method=isSameNo";
			
				 $.post(url,{"no":$("#no").val()},function(result){
					 if(result=="-1"){
						  $("#notip").html("账号可用！");
					  }else{
					 	 $("#notip").html("账号已被注册！");
					  }
				  });
		}
	}
	

</SCRIPT>
	<body>
		<div class="container">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">注册会员</b>
					</div>

					<div class="box_center">

						<form name="validateForm"
							action="<%=basePath%>servlet/SysAdminServlet?method=regMember"
							method="post" class="jqtransform"
							onsubmit="return validate(this) ">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="td_right">
											账号：
										</td>
										<td class="">
											<input type="text" name="no" id="no" class="input-text lh30"
												size="40" onblur="isSameNo()"><span id="notip"  style="color: red;" ></span>
										</td>
									</tr>
									<tr>
										<td class="td_right">
											密码：
										</td>
										<td>
											<input type="password" name="password" id="password"
												class="input-text lh30" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											姓名：
										</td>
										<td>
											<input type="text" name="name" id="name"
												class="input-text lh30" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											电话：
										</td>
										<td>
											<input type="text" name="phone" id="phone"
												class="input-text lh30" size="40">
										</td>
									</tr>

									<tr>
										<td class="td_right">
											&nbsp;
										</td>
										<td class="">
											<input type="submit" name="button"
												class="btn btn82 btn_save2" value="保存" >
											<input type="reset" name="button" class="btn btn82 btn_res"
												value="重置">
										</td>
									</tr>
								</tbody>
							</table>
						</form>

					</div>

				</div>
			</div>
		</div>
	</body>
</html>
