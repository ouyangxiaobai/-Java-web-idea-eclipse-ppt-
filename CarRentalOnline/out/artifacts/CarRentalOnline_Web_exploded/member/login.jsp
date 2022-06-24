<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>基于Java web的校园电动车租赁系统</title>
<script type="text/javascript" src="./script/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="./script/login.js"></script>
<link href="./css/login2.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

	function validate(myform) {
	
		if ($("#u").val().length == 0) {
			alert("账号不能为空！");
			$("#u").focus();
			return false;
		}

		if ($("#u").val().length < 1 || $("#no").val().length > 16) {
			alert("账号长度、格式错误！");
			$("#no").focus();
			return false;
		}
		if ($("#p").val().length == 0) {
			alert("密码不能为空！");
			$("#p").focus();
			return false;
		}
		if ($("#p").val().length < 1 || $("#p").val().length > 16) {
			alert("密码长度、格式错误！");
			$("#p").focus();
			return false;
		}

		if ($("#codeNum").val().length == 0) {
			alert("验证码不能为空！");
			$("#codeNum").focus();
			return false;
		}


		return true;
	}
	
	
	function isSameNo(){
	
	
		if($("#user").val().length!=0){
		
			var url="<%=basePath%>servlet/MemberServlet?method=isSameNo";
			
				 $.post(url,{"no":$("#user").val()},function(result){
					 if(result=="-1"){
						  $("#userCue").html("<font color=\"red\"><b>账号可用！</b></font>");
					  }else{
					 	 $("#userCue").html("<font color=\"red\"><b>账号已被注册！</b></font>");
					  }
				  });
		}
	}


</script>
</head>
<body style="zoom: 1;">
	<SCRIPT LANGUAGE="JavaScript">
	function changeCode() {  
	       var img=document.getElementById("checkCode"); 
	       img.src="<%=basePath%>member/imgcode.jsp?date=" + new Date();//此处很重要，不加后面的就不会变
		}
	</SCRIPT>
	<h1>
		基于Java web的校园电动车租赁系统<sup>2022</sup>
	</h1>
	<div class="login" style="margin-top:50px;">
		<div class="header">
			<div class="switch" id="switch">
				<a class="switch_btn" id="switch_qlogin" href="javascript:void(0);"
					tabindex="7">快速登录</a> <a class="switch_btn_focus" id="switch_login"
					href="javascript:void(0);" tabindex="8">快速注册</a>
				<div class="switch_bottom" id="switch_bottom"
					style="position: absolute; width: 70px; left: 154px;"></div>
			</div>
		</div>
		<div class="web_qr_login" id="web_qr_login"
			style="display: none; height: 235px;">

			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form action="<%=basePath%>servlet/LoginServlet"
							name="loginform" accept-charset="utf-8" id="login_form"
							class="loginForm" method="post" onsubmit="return validate(this) ">
							<input type="hidden" name="did" value="0"> <input
								type="hidden" name="to" value="log">
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="u" name="username" class="inputstyle" >

								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">
									<input type="password" id="p" name="password" class="inputstyle">
								</div>
							</div>
							<div class="codeArea" id="codeArea">
								<label class="input-tips" for="p">验证码：</label>
								<div class="inputOuter" id="pArea">
									<input type="text" id="codeNum" name="codeNum"
										class="inputstyle"> <img id="checkCode"
										style=" float: right; margin-right: -70px"
										src="<%=basePath%>member/imgcode.jsp" onclick="changeCode();"
										title="点击图片刷新验证码" />
								</div>
							</div>
							<div class="shenFenArea" id="shenFenArea">
								<label style="margin-top: -13px;margin-left: 15px" class="input-tips" for="u">登录信息：</label>
								<div  style="margin-top: 20px" id="uArea">
									<label> <input type="radio" name="identity"
										value="1" id="RadioGroup1_0" checked="checked"/> 租赁人员</label> <label> <input
										type="radio" name="identity" value="2" id="RadioGroup1_1" />
										门店人员</label> <label> <input type="radio" name="identity"
										value="3" id="RadioGroup1_1" /> 内部人员</label>
								</div>

							</div>
							<div style="padding-left:50px;margin-top:20px;">
								<input type="submit" name="subLogin" value="登 录"
									style="width:150px;" class="button_blue">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>

		<!--注册-->
		<div class="qlogin" id="qlogin" style="display: block;">
			<div class="web_login">
				<form name="form2" id="regUser" accept-charset="utf-8"
					action="<%=basePath%>servlet/MemberServlet?method=register"
					method="post">
					<input type="hidden" name="to" value="reg"> <input
						type="hidden" name="did" value="0">
					<ul class="reg_form" id="reg-ul">
						<div id="userCue" class="cue">快速注册请注意格式</div>
						<li><label for="user" class="input-tips2">账号：</label>
							<div class="inputOuter2">
								<input type="text" id="user" name="userNo" maxlength="16"
									class="inputstyle2"  onblur="isSameNo()">
							</div>
						</li>
						<li><label for="passwd" class="input-tips2">密码：</label>
							<div class="inputOuter2">
								<input type="password" id="passwd" name="passwd" maxlength="16"
									class="inputstyle2">
							</div>
						</li>
						<li><label for="passwd2" class="input-tips2">确认密码：</label>
							<div class="inputOuter2">
								<input type="password" id="passwd2" name="passwd2"
									maxlength="16" class="inputstyle2">
							</div>
						</li>
						<li><label for="realName " class="input-tips2">姓名：</label>
							<div class="inputOuter2">
								<input type="text" id="realName" name="realName" maxlength="10"
									class="inputstyle2">
							</div>
						</li>
						<li><label for="phone" class="input-tips2">联系方式：</label>
							<div class="inputOuter2">
								<input type="text" id="phone" name="phone" maxlength="11"
									class="inputstyle2">
							</div>
						</li>
						<li>
							<div class="inputArea">
								<input type="button" id="reg"
									style="margin-top:10px;margin-left:85px;" class="button_blue"
									value="同意协议并注册"> <a href="#" class="zcxy"
									target="_blank">注册协议</a>
							</div>
						</li>
						<div class="cl"></div>
					</ul>
				</form>
			</div>
		</div>
		<!--注册end-->
	</div>
	<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body>
</html>