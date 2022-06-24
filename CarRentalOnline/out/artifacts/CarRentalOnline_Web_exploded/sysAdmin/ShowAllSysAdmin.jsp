<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.dao.ISysAdminDAO"%>
<%@page import="com.cuc.dao.imp.SysAdminDAO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'ShowAllSysAdmin.jsp' starting page</title>

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
			$( function() {
				$(".list_table").colResizable( {
					liveDrag :true,
					gripInnerHtml :"<div class='grip'></div>",
					draggingClass :"dragging",
					minWidth :30
				});

			});

			function del() {
				var msg = "您真的确定要删除吗？\n\n请确认！";
				if (confirm(msg) == true) {
					return true;
				} else {
					return false;
				}
			}
		</script>
	</head>

	<body>
		<div class="container">
			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">企业管理员信息</b>
						</div>
						<div class="box_center">
							<center>
								<div id="table" class="mt10" style="width: 90%;">
									<div class="box span10 oh">
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="list_table">
											<tr>
												<th width="20">
													编号
												</th>
												<th width="40">
													账号
												</th>
												<th width="40">
													姓名
												</th>
												<th width="60">
													电话
												</th>
												<th width="200">
													联系地址
												</th>
												<th width="30">
													操作
												</th>
											</tr>
											<%
												ISysAdminDAO sysAdminDAO = new SysAdminDAO();
												ArrayList<String[]> adminList = sysAdminDAO.searchAllSysAdmin();
												if (adminList != null && adminList.size() != 0) {
													for (int i = 0; i < adminList.size(); i++) {
														String[] admin = adminList.get(i);
											%>
											<tr class="tr">
												<td class="td_center">
													<%=admin[0]%>
												</td>
												<td class="td_center">
													<%=admin[1]%>
												</td>
												<td class="td_center">
													<%=admin[3]%>
												</td>
												<td class="td_center">
													<%=admin[4]%>
												</td>
												<td class="td_center">
													<%=admin[5]%>
												</td>
												<td class="td_center">
													<a
														href="<%=basePath%>servlet/SysAdminServlet?method=delSysAdminById&id=<%=admin[0]%>"
														onclick='javascript:return del()'> <img
															src="<%=basePath%>images/delete.jpg" width="20px;"
															height="20px;"> </a>
												</td>

											</tr>
											<%
												}
												}
											%>



										</table>
									</div>
								</div>
							</center>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
