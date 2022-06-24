<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.FrequentContacts"%>
<%@page import="com.cuc.dao.IFrequentContactsDAO"%>
<%@page import="com.cuc.dao.imp.FrequentContactsDAO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="<%=basePath%>member/css/Global.css" rel="stylesheet">
	</head>

	<body>

		<div class="mod renter-list">
			<ul class="driver-list">
				<li class="row-title">
					<span class="row-first-cell cell-name">姓名</span>
					<span class="cell-phone">手机号码</span>
					<span class="cell-idtype">证件类型</span>
					<span class="cell-idnumber">证件号码</span>
					<span class="cell-passport">联系地址</span>

				</li>
				<%
					IFrequentContactsDAO freContactsDAO = new FrequentContactsDAO();
					int memberId = Integer.parseInt(request.getParameter("id"));
					ArrayList<FrequentContacts> fclist = freContactsDAO
							.getByMemberId(memberId);
					if (fclist != null && fclist.size() != 0) {
						for (int i = 0; i < fclist.size(); i++) {
							FrequentContacts fc = fclist.get(i);
							if (i % 2 == 0) {
				%>
				<li class="" data-driver-id="<%=fc.getFrequentId()%>"
					data-name="<%=fc.getFrequentName()%>"
					data-cellphone="<%=fc.getFrequentPhone()%>"
					data-idcardtype="<%=fc.getIdType()%>"
					data-idcard-number="<%=fc.getIdentity()%>" data-year="2015"
					data-month="1">
					<span class="row-first-cell cell-name"><%=fc.getFrequentName()%></span>
					<span class="cell-phone"><%=fc.getFrequentPhone()%></span>
					<span class="cell-idtype"><%=fc.getIdType()%></span>
					<span class="cell-idnumber"><%=fc.getIdentity()%></span>
					<span class="cell-passport"><%=fc.getFrequentProvince()%> <%=fc.getFrequentCity()%>
						<%=fc.getFrequentAddresss()%></span>

				</li>
				<%
					} else {
				%>
				<li class="ck" data-driver-id="<%=fc.getFrequentId()%>"
					data-name="<%=fc.getFrequentName()%>"
					data-cellphone="<%=fc.getFrequentPhone()%>"
					data-idcardtype="<%=fc.getIdType()%>"
					data-idcard-number="<%=fc.getIdentity()%>" data-year="2015"
					data-month="1">
					<span class="row-first-cell cell-name"><%=fc.getFrequentName()%></span>
					<span class="cell-phone"><%=fc.getFrequentPhone()%></span>
					<span class="cell-idtype"><%=fc.getIdType()%></span>
					<span class="cell-idnumber"><%=fc.getIdentity()%></span>
					<span class="cell-passport"><%=fc.getFrequentProvince()%> <%=fc.getFrequentCity()%>
						<%=fc.getFrequentAddresss()%></span>

				</li>
				<%
					}
				%>
				<%
					}
					}
				%>
			</ul>
		</div>
	</body>
</html>
