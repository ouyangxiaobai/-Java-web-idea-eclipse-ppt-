<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>

	<frameset cols="230,*" framespacing='0' border='0'
		bordercolor='#000000'>
		<frame src='<%=basePath%>member/operate.jsp' name='content'>

		<frame src='<%=basePath%>member/operateContent.jsp' name='caozuo'>

	</frameset>

</html>