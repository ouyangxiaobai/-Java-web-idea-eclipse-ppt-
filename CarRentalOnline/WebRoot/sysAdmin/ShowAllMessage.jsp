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
	
</script>
	</head>
	<body>

		<div id="table" class="mt10">
			<div class="box span10 oh">
				<table border="0" cellpadding="0" cellspacing="0" class="list_table">
					<tr>
						<th width="120">
							照片
						</th>
						<th width="550">
							内容
						</th>
						<th width="80">
							状态
						</th>
						<th width="80">
							操作
						</th>

					</tr>

					<%
						ArrayList<String[]> messageList = (ArrayList<String[]>) request
								.getAttribute("messageList");
						if (messageList != null) {
							for (int i = 0; i < messageList.size(); i++) {
								String[] message = messageList.get(i);
					%>

					<tr class="tr">
						<td class="td_center">
							<input name="" type="image" width="150px" height="110px"
								src="<%=basePath%>uploadFiles/<%=message[4]%>" />
						</td>
						<td class="">
							<p style="margin-left: 5px;margin-right: 5px;">
								<%=message[2]%>
							</p>
							<p style="margin: 5px; padding-top: 5px;">
							留言分享时间：<%=message[3].substring(0,19)%>
							</p>
						</td>
						<td class="td_center">
						<%if(message[5].equals("0")){
						%>
						待审核
						<%}else if(message[5].equals("1")){%>
						通过
						<%}else if(message[5].equals("2")){%>
						不通过
						<%}%>
						</td>
						<td class="td_center">
							<form
								action="<%=basePath%>servlet/SysAdminServlet?method=changeMessageState&messageId=<%=message[0]%>"
								method="post">
								<input type="submit" name="button" class="btn btn82 btn_del"
									value="通过" >
							</form>

						</td>
					</tr>

					<%
						}
						}
					%>


				</table>
				<div style="margin-top: 10px;">
					<%=request.getAttribute("pageTool")%></div>
			</div>
		</div>


	</body>
</html>
