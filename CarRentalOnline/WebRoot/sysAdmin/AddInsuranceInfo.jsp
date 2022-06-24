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
		<link rel="stylesheet" href="<%=basePath%>css/style.css" />
		<script type="text/javascript" src="<%=basePath%>script/tinybox.js"></script>
		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/main.css">
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.min.js"></script>

		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/common.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
	
		if ($("#name").val().length == 0) {
			alert("保险名称不能为空！");
			$("#name").focus();
			return false;
		}


		if ($("#price").val().length == 0) {
			alert("价格不能为空！");
			$("#price").focus();
			return false;
		}
		
	
		
		if (!$.isNumeric($("#price").val() )) {
			alert("价格不是数字！");
			$("#price").focus();
			return false;
		}
		
		
		
		
		if ($("#content").val().length == 0) {
			alert("保险描述内容不能为空！");
			$("#content").focus();
			return false;
		}


		return true;
	}

</SCRIPT>


	</head>

	<body>
		<div class="container">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">租赁保险添加</b>
					</div>

					<div class="box_center">

						<form
							action="<%=basePath%>servlet/SysAdminServlet?method=addInsuranceInfo"
							method="post" class="jqtransform" onsubmit="return validate(this) ">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="td_right">
											保险名称：
										</td>
										<td class="">
											<input type="text" name="name" id="name"
												class="input-text lh30" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											保险价格：
										</td>
										<td>
											<input type="text" name="price" id="price"
												class="input-text lh30" size="40">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											内容描述：
										</td>
										<td>
											<textarea rows="5" cols="50" style="height: 80px;"
												class="input-text" name="content" id="content"></textarea>
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
															<option value="0">
																关闭
															</option>
															<option value="1">
																开启
															</option>
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
