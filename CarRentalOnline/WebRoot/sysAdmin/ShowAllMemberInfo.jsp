<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.util.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
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
							<b class="pl15">注册会员信息</b>
						</div>
						<div class="box_center">
							<center>
								<div id="table" class="mt10" style="width: 90%;">
									<div class="box span10 oh">
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="list_table">
											<tr>
												<th width="20">
													头像
												</th>
												<th width="35">
													账号
												</th>
												<th width="35">
													姓名
												</th>
												<th width="20">
													性别
												</th>
												<th width="35">
													电话
												</th>
												<th width="70">
													注册时间
												</th>
												<th width="60">
													查看
												</th>
											</tr>
											<%
												ArrayList<String[]> memberList = (ArrayList<String[]>)request.getAttribute("memberList");
												if (memberList != null && memberList.size() != 0) {
													for (int i = 0; i < memberList.size(); i++) {
														String[] member = memberList.get(i);
											%>
											<tr class="tr">
												<td class="td_center">
													<%
													if(StringUtil.validateNull(member[6])){
													%>
														<img src="<%=basePath %>member/images/moren.png" width="55px;" height="55px;" alt="无">
													<%}else{%>
													<img src="<%=basePath %>uploadFiles/<%=member[6]%>" width="55px;" height="55px;" alt="无">
													<%} %>
												</td>
												<td class="td_center">
													<%=member[1]%>
												</td>
												<td class="td_center">
													<%=member[3]%>
												</td>
												<td class="td_center">
													<%=member[4]%>
												</td>
												<td class="td_center">
													<%=member[5]%>
												</td>
												<td class="td_center">
													<%=member[7].substring(0,19)%>
												</td>
												<td class="td_center">
													<button onclick="TINY.box.show({iframe:'<%=basePath %>sysAdmin/frequentContent.jsp?id=<%=member[0]%>',boxid:'frameless',width:1000,height:350,fixed:false,maskid:'bluemask',maskopacity:40})">常用联系人</button>
												</td>
											</tr>
											<%
												}
												}
											%>
										</table>
									</div>
								</div>
								<div style="margin-top:30px;margin-bottom: 30px;">
							<%=request.getAttribute("pageTool") %>
							</div>
							</center>
						</div>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
