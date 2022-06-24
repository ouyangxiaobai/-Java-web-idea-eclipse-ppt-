<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.Insurance"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
			src="<%=basePath%>businessStore/js/common.js"></script>

	</head>

	<body>
		<div class="container">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">租赁保险信息添加</b>
					</div>

					<div class="box_center">
						<%
							Insurance insurance = (Insurance) request.getAttribute("insurance");
						%>
						<form
							action="<%=basePath%>servlet/SysAdminServlet?method=updateInsuranceInfo"
							method="post" class="jqtransform">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="td_right">
											保险名称：
										</td>
										<td class="">
											<input type="hidden" name="id" id="id"
												value="<%=insurance.getInsuranceId()%>">
											<input type="text" name="name" id="name"
												class="input-text lh30" size="40"
												value="<%=insurance.getInsuranceName()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											保险价格：
										</td>
										<td>
											<input type="text" name="price" id="price"
												class="input-text lh30" size="40"
												value="<%=insurance.getInsurancePrice()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											内容描述：
										</td>
										<td>
											<textarea rows="5" cols="50" style="height: 80px;"
												class="input-text" name="content" id="content"><%=insurance.getInsuranceContent()%></textarea>
										</td>
									</tr>

									<tr>
										<td class="td_right">
											可用状态：
										</td>
										<td class="">
											<span class="fl">
												<div class="select_border">
													<div class="select_containers ">
														<select name="state" class="select">
															<%
																if (insurance.getState() == 0) {
															%>
															<option value="0" selected="selected">
																关闭
															</option>
															<option value="1">
																开启
															</option>
															<%
																} else {
															%>
															<option value="0">
																关闭
															</option>
															<option value="1" selected="selected">
																开启
															</option>
															<%
																}
															%>

														</select>
													</div>
												</div> </span>
										</td>
									</tr>
									<tr>
										<td class="td_right">
											&nbsp;
										</td>
										<td class="">
											<input type="submit" name="button"
												class="btn btn82 btn_save2" value="保存">
											<input type="reset" name="button" class="btn btn82 btn_res"
												value="重置">
										</td>
									</tr>
								</tbody>
							</table>
						</form>

					</div>

				</div>
			</div>
		</div>

	</body>
</html>
