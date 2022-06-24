<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<title>基于Java web的校园电动车租赁系统--会员个人信息中心</title>
		<link rel="stylesheet" href="<%=basePath%>member/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>member/css/css.css" />
		<script type="text/javascript"
			src="<%=basePath%>member/script/jquery1.9.0.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>member/script/bootstrap.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>member/script/sdmenu.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>member/script/laydate/laydate.js"></script>
	</head>
	<body>
		<div id="middle">
			<div class="left">

				<script type="text/javascript">
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
</script>

				<div id="my_menu" class="sdmenu">
					<div>
						<span>用户管理</span>
						<a href="<%=basePath%>member/operateContent.jsp" target="caozuo">基本信息</a>
						<a href="<%=basePath%>member/ChangePassword.jsp" target="caozuo">修改密码</a>
					</div>
					<div class="collapsed">
						<span>联系人管理</span>
						<a href="<%=basePath%>servlet/MemberServlet?method=FrequentInfo" target="caozuo">添加联系人</a>
					</div>
					<div class="collapsed">
						<span>用户订单</span>
						<a href="<%=basePath%>servlet/OrderServlet?method=MemberSearchOrder" target="caozuo">全部订单</a>
					</div>
					<div class="collapsed">
						<span>报修管理</span>
						<a href="<%=basePath%>servlet/OrderServlet?method=MemberSearchOrderbaoxiu" target="caozuo">订单报修</a>
					</div>

					<div class="collapsed">
						<span>用户留言</span>
						<a
							href="<%=basePath%>servlet/MemberServlet?method=showMyMessageAll"
							target="caozuo">全部留言</a>
					</div>

				</div>

			</div>
</div>
			<div class="Switch"></div>
			<script type="text/javascript">
	$(document).ready( function(e) {
		$(".Switch").click( function() {
			$(".left").toggle();

		});
	});
</script>
	</body>
</html>