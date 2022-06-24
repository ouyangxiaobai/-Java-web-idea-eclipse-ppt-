<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.util.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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

			function del(state) {
			
				var msg = "您真的确定要关闭该营业门店吗？\n\n请确认！";
			
				if(state==0){
					var msg = "您真的确定要启用该营业门店吗？\n\n请确认！";
				}
				
				if (confirm(msg) == true) {
					return true;
				} else {
					return false;
				}
			}
		</script>

  </head>
  
  <body>
   <div class="container" style=" margin-top: -13px;">
			<div id="forms" class="mt10">
				<div class="box">
					<div class="box_border">
						<div class="box_top">
							<b class="pl15">营业门店信息</b>
						</div>
						<div class="box_center">
							<center>
								<div id="table" class="mt10" style="width: 99%;">
									<div class="box span10 oh">
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="list_table">
											<tr>
												<th width="20">
													编号
												</th>
												<th width="30">
													照片
												</th>
												<th width="35">
													账号
												</th>
												<th width="60">
													门店
												</th>
												<th width="60">
													电话
												</th>
												<th width="200">
													地址
												</th>
												<th width="30">
													状态
												</th>
												<th width="70">
													操作
												</th>
											</tr>
											<%
												ArrayList<String[]> storeList = (ArrayList<String[]>)request.getAttribute("storeList");
												if (storeList != null && storeList.size() != 0) {
													for (int i = 0; i < storeList.size(); i++) {
														String[] store = storeList.get(i);
											%>
											<tr class="tr">
												<td class="td_center">
													<%=store[0]%>
												</td>
												<td class="td_center">
												<%
													if(StringUtil.validateNull(store[4])){
													%>
														<img src="<%=basePath %>member/images/moren.png" width="55px;" height="55px;" alt="无">
													<%}else{%>
													<img src="<%=basePath %>uploadFiles/<%=store[4]%>" width="55px;" height="55px;" alt="无">
													<%} %>
												</td>
												<td class="td_center">
													<%=store[1]%>
												</td>
												<td class="td_center">
													<%=store[3]%>
												</td>
												<td class="td_center">
													<%=store[5]%>
												</td>
												<td>
													<%=store[6]%> <%=store[7]%> <%=store[8]%> <%=store[9]%> 
												</td>
												<td class="td_center">
													<%
													if(store[10].equals("0")){
													 %>
													 关闭
													 <%}else{ %>
													 启用
													 <%} %>
												</td>
												<td class="td_center">
												<a href="<%=basePath%>servlet/SysAdminServlet?method=preupdate&id=<%=store[0]%>"> 
														<input type="image" name="imageField" id="imageField" src="<%=basePath%>images/update.png" width="15px;"/>编辑  
												</a>
												
												<%
												if(store[10].equals("1")){
												 %>
												 <a href="<%=basePath%>servlet/SysAdminServlet?method=updateStoreState&id=<%=store[0]%>&state=<%=store[10] %>" onclick='javascript:return del(1)'> 
														<input type="image" name="imageField" id="imageField" src="<%=basePath%>images/open.png" width="15px;" />关闭 
												</a>
												<%}else if(store[10].equals("0")){ %>
												<a href="<%=basePath%>servlet/SysAdminServlet?method=updateStoreState&id=<%=store[0]%>&state=<%=store[10] %>" onclick='javascript:return del(0)'> 
														<input type="image" name="imageField" id="imageField" src="<%=basePath%>images/closes.png" width="15px;" />启用 
												</a>
												<%} %>
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
