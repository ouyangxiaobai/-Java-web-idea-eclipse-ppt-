<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Footer.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
			<!--footer-->
			<div class="footer">
				<div class="footer-box">
					<div class="footer-left">
						<p>
							<a href="http://www.1hai.cn/aboutus.aspx" rel="nofollow"
								target="_blank">关于一嗨</a> |
							<a href="http://www.1hai.cn/job/index.aspx" target="_blank">招募英才</a>
							|
							<a href="http://www.1hai.cn/activity/mobile.aspx" target="_blank">移动客户端</a>
							|
							<a href="http://www.1hai.cn/help/index.aspx?from=foot"
								rel="nofollow" target="_blank">帮助中心</a> |
							<a href="http://www.1hai.cn/help/advice.aspx" rel="nofollow"
								target="_blank">建议专区</a> |
							<a href="http://www.1hai.cn/help/contactus.aspx" rel="nofollow"
								target="_blank">联系我们</a> |
							<a href="http://www.1hai.cn/more/link.aspx" rel="nofollow"
								target="_blank">友情链接</a> | &nbsp;投诉通道：xxxxxx&nbsp;
						</p>
						<p>
							Copyright © 2022 基于Java web的校园电动车租赁系统
							<a href="#" target="_blank"
								rel="nofollow">2022</a>
						</p>
					</div>
				</div>
			</div>
		</center>
  </body>
</html>
