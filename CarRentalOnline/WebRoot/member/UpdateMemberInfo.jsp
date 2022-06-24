<%@page import="com.cuc.model.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.util.StringUtil"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Member member = (Member) session.getAttribute("member");
	if (member == null) {
		response
				.getWriter()
				.write(
						"<script language =javascript>window.open('login.jsp','_top')</script>");

		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>我的信息</title>

		<script type="text/javascript">
	function alarm() {
		alert("只允许上传jpg,gif,bmp类型的文件");
	}
	function checkExt() {
		var allowArray = new Array("jpg", "gif", "bmp");
		var simg = document.getElementById("simg").value;
		if (simg == "") {
			return true;
		} else {

			var fileExt = simg.substr(simg.lastIndexOf(".") + 1);
			var flag = false;
			for ( var i = 0; i < allowArray.length; i++) {
				if (allowArray[i] == fileExt.toLowerCase()) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				alert("不允许上传的文件类型，只允许上传jpg,gif,bmp类型的文件");
			}
			return flag;
		}
	}
</script>
	</head>
	<body>
		<div style="border: #CCC 1px solid; height: 100%; width: 100%;">
			<!--头部我的信息-->
			<div
				style="background-color: #CCC; height: 45px; line-height: 45px; margin-top: -19px">
				<h3 style="color: #575757; margin-left: 15px">
					<strong>我的信息</strong>
				</h3>
			</div>
			<div style="height: 480px; color: #404040; font-weight: bold;">
				<center>
					<form id="form1" name="form1" method="post"
						action="<%=basePath%>servlet/MemberServlet?method=updateMemberInfo"
						style="margin-top: 50px;" enctype="multipart/form-data">
						<table width="507" border="0">
							<tr>
								<td width="88" height="28">
									<strong> 真实姓名 </strong>
								</td>
								<td width="409" height="28">

									<label for="name"></label>
									<input type="text" name="name" id="name"
										value="<%=member.getMemberName()%>" />
									<input type="hidden" name="memberId" id="memberId"
										value="<%=member.getMemberId()%>" />
									<input type="hidden" name="memberNo" id="memberNo"
										value="<%=member.getMemberNo()%>" />
								</td>
							</tr>
							<tr>
								<td height="28">
									<strong> 会员性别 </strong>
								</td>
								<td height="28">
									<p>
										<label for="sex"></label>
										<select name="sex" id="sex">
											<%
												String sex = member.getMemberSex();
												if ("男".equals(sex)) {
											%>
											<option value="男" selected="selected">
												男
											</option>
											<option value="女">
												女
											</option>
											<%
												} else {
											%>

											<option value="男">
												男
											</option>
											<option value="女" selected="selected">
												女
											</option>

											<%
												}
											%>
										</select>
										<br />
									</p>
								</td>
							</tr>
							<tr>
								<td height="28">
									<strong> 电话号码 </strong>
								</td>
								<td height="28">
									<label for="phone"></label>
									<input type="text" name="phone" id="phone"
										value="<%=member.getMemberPhone()%>" />
								</td>
							</tr>
							<tr>
								<td height="113">
									<strong> 个人头像 </strong>
								</td>
								<td height="113">
									<%
										if (StringUtil.validateNull(member.getMemberImage())) {
									%>
									<img src="<%=basePath%>/member/images/moren.png" width="143"
										height="143">
									<%
										} else {
									%>
									<img
										src="<%=basePath%>uploadFiles/<%=member.getMemberImage()%>"
										width="143" height="143" name="oldMemberImg">

									<input type="hidden"  name="oldMemberImg" id="oldMemberImg"
										class="input-text lh30" size="10" value="<%=member.getMemberImage()%>">
									<%
										}
									%>
									<input type="file" name="simg" id="simg"
										class="input-text lh30" size="10">
								</td>
							</tr>
							<tr>
								<td height="67">
									&nbsp;
								</td>
								<td height="67">
									<input style="width:189px;height:54px;background-image: url('<%=basePath%>member/images/updateInfo.bmp');"
										type="submit" value=""/>
								</td>
							</tr>
						</table>
					</form>
				</center>
			</div>
		</div>
	</body>
</html>