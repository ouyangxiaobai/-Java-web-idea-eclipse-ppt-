<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.dao.IMessageDAO"%>
<%@page import="com.cuc.dao.imp.MessageDAO"%>
<%@page import="java.util.ArrayList"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<script language="javascript">
	function countChar(textareaNamezzjs, spanName) {
		document.getElementById(spanName).innerHTML = document
				.getElementById(textareaNamezzjs).value.length;
	}
	function alarm(){
		alert("只允许上传jpg,gif,bmp类型的文件");
	}
	function checkExt(){
		var allowArray = new Array("jpg","gif","bmp");
		var simg = document.getElementById("simg").value;
		var fileExt = simg.substr(simg.lastIndexOf(".")+1);
		var flag = false;
		for(var i = 0;i<allowArray.length;i++){
			if (allowArray[i] == fileExt.toLowerCase()){
				flag = true;
				break;
			}
		}
		if (flag == false){
			alert("不允许上传的文件类型，只允许上传jpg,gif,bmp类型的文件");
		}
		return flag;
	}
	
	
	
</script>

		<link rel="stylesheet" href="<%=basePath%>/member/css/tiyan.css"
			type="text/css"></link>
		<title>租车体验说</title>
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="Header.jsp" />
		<!--head end -->
		<!-----------头部照片内容开始-------------------------->
		<div id="wrap-banner">
			<div class="box-banner">
				<div class="box-exam">
					<span class="exam-tit" id="examtit">体验内容示例</span>
				</div>
				<p class="stp-a">
					发布初次驾车心得，如：第一次骑电动车上班，电动车很好骑…
				</p>
				<p class="stp-b">
					发布体验后至个人中心-
					<a href="<%=basePath%>member/memberInfoCenter.jsp" >我的留言</a>查询审核信息。
				</p>
			</div>
		</div>
		<!----------头部照片内容结束--------------------------->
		<!----------提交体验留言内容开始--------------------------->

		<div id="wrap-fm">
			<div class="box-fm">
				<form
					action="<%=basePath%>servlet/MemberServlet?method=insertMessage"
					method="post" enctype="multipart/form-data"
					onSubmit="return checkExt();">
					<div class="form-post">
						<!--提交-->
						<div id="mycomment" class="box-write" style="display: block;">
							<h3 class="write-tit">
								<span class="hid-txt">发布体验内容+人车合影</span>
								<strong class="zishu"> <span id="num">0</span><span
									class="color">字数</span> </strong>
							</h3>
							<textarea name="txtComment" id="txtComment" class="area"
								onkeydown="countChar('txtComment','num');"
								onkeyup="countChar('txtComment','num');"></textarea>
							<div class="box-up-subm">
								<span class="sp-up">上传合照</span>
								<input type="file" name="simg" id="simg" class="up-file">
								<strong class="img-req">（仅支持jpg,gif,bmp图片文件）</strong>
								<input type="submit" name="btnComment" value="提交"
									id="btnComment" class="subm-ty com-btn-style">
								<!-- <p class="err" id="err" style="display: none"> 内容字数不足</p>-->
							</div>
						</div>
						<!--提交结束-->
					</div>
				</form>
				<div class="latest-notice">
					<p>
						<strong>活动对象：</strong><br><br>
						所有注册会员用户。
					</p>
				</div>
			</div>
		</div>

		<!----------提交体验留言内容结束--------------------------->
		<!----------租赁会员体验留言内容开始------------------------>
		<div id="main">
			<div class="main-con">
				<div class="wrap-tylist">
					<table id="DataList_ppl_ty" cellspacing="0"
						style="width: 100%; border-collapse: collapse;">
						<tbody>
							<%
								ArrayList<String[]> list = (ArrayList<String[]>) request
										.getAttribute("messagelist");
								for (int i = 0; i < list.size(); i++) {
									String[] msg = list.get(i);
									msg[3] = msg[3].substring(0, 4) + "****"
											+ msg[3].substring(8, 11);
							%>
							<tr>
								<td>
									<div class="box-tylist" id="ppl3960">
										<strong class="u-npho">用户账号: <%=msg[2]%> <span>(<%=msg[3]%>)</span>：</strong>
										<p class="up-con">
											<%=msg[5]%>
										</p>
										<div class="box-uimg">
											<img src="<%=basePath%>uploadFiles/<%=msg[7]%>"
												id="DataList_ppl_ty_mypicture_0">
										</div>
										<div class="up-time">
											分享于：<%=msg[6].substring(0,19)%>
										</div>
									</div>
								</td>
							</tr>
							<%
								}
							%>

						</tbody>
					</table>
					<div class="wrap-page">
						<%=request.getAttribute("pageTool")%>
					</div>
				</div>
			</div>
			<div class="side-bar">
            <img src="http://s2.1haimg.cn/image/tiyan/top-cor.png" class="img-top">
           
        </div>
		</div>
		<!----------租赁会员体验留言内容结束------------------------>
	</body>
</html>