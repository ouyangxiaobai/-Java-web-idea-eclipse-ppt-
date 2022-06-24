<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.model.BusinessStore"%>
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
		<script src="<%=basePath%>script/Popt.js"></script>
		<script src="<%=basePath%>script/cityJson.js"></script>
		<script src="<%=basePath%>script/citySet.js"></script>
		<style type="text/css">
html,body,h1,h2,h3,h4,h5,h6,div,ul,ol,li,dl,dt,dd,iframe,textarea,input,button,p,strong,b,i,a,span,del,pre,table,tr,th,td,form,fieldset,.pr,.pc
	{
	margin: 0;
	padding: 0;
	word-wrap: break-word;
	font-family: verdana, Microsoft YaHei, Tahoma, sans-serif; *
	font-family: Microsoft YaHei, verdana, Tahoma, sans-serif;
}

body,ul,ol,li,dl,dd,p,h1,h2,h3,h4,h5,h6,form,fieldset,.pr,.pc,em,del {
	font-style: normal;
	font-size: 100%;
}

ul,ol,dl {
	list-style: none;
}

._citys {
	width: 450px;
	display: inline-block;
	border: 2px solid #eee;
	padding: 5px;
	position: relative;
}

._citys span {
	color: #56b4f8;
	height: 15px;
	width: 15px;
	line-height: 15px;
	text-align: center;
	border-radius: 3px;
	position: absolute;
	right: 10px;
	top: 10px;
	border: 1px solid #56b4f8;
	cursor: pointer;
}

._citys0 {
	width: 100%;
	height: 34px;
	display: inline-block;
	border-bottom: 2px solid #56b4f8;
	padding: 0;
	margin: 0;
}

._citys0 li {
	display: inline-block;
	line-height: 34px;
	font-size: 15px;
	color: #888;
	width: 80px;
	text-align: center;
	cursor: pointer;
}

.citySel {
	background-color: #56b4f8;
	color: #fff !important;
}

._citys1 {
	width: 100%;
	display: inline-block;
	padding: 10px 0;
}

._citys1 a {
	width: 83px;
	height: 35px;
	display: inline-block;
	background-color: #f5f5f5;
	color: #666;
	margin-left: 6px;
	margin-top: 3px;
	line-height: 35px;
	text-align: center;
	cursor: pointer;
	font-size: 13px;
	overflow: hidden;
}

._citys1 a:hover {
	color: #fff;
	background-color: #56b4f8;
}

.AreaS {
	background-color: #56b4f8 !important;
	color: #fff !important;
}

.inner>.add {
	display: none;
	position: absolute;
	top: -1000000px;
	visibility: hidden
}

.fdad,.adsbygoogle {
	display: none;
	position: absolute;
	top: -1000000px;
	visibility: hidden
}
</style>
	</head>

	<body>

		<div class="container">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">营业门店添加</b>
					</div>

					<div class="box_center">
						<%
							BusinessStore store = (BusinessStore) request.getAttribute("store");
						%>
						<form
							action="<%=basePath%>servlet/SysAdminServlet?method=updateStoreInfo"
							method="post" class="jqtransform" enctype="multipart/form-data">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="td_right">
											账号：
										</td>
										<td class="">
											<input type="hidden" name="id" id="id"
												value=" <%=store.getStoreId()%>" />
											<input type="text" name="no" id="no" class="input-text lh30"
												size="40" value="<%=store.getStoreNo()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											密码：
										</td>
										<td>
											<input type="password" name="password" id="password"
												class="input-text lh30" size="40"
												value="<%=store.getStorePassword()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											门店名称：
										</td>
										<td>
											<input type="text" name="name" id="name"
												class="input-text lh30" size="40"
												value="<%=store.getStoreName()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											联系电话：
										</td>
										<td>
											<input type="text" name="phone" id="phone"
												class="input-text lh30" size="40"
												value="<%=store.getStorePhone()%>">
										</td>
									</tr>
									<tr>
										<td class="td_right">
											所在城市：
										</td>
										<td class="">
											<div style="width: 420px;">
												<input type="text" id="city" name="city"
													class="input-text lh30" size="40"
													value="<%=store.getStoreProvince()%>-<%=store.getStoreCity()%>-<%=store.getStoreDistrict()%>">

											</div>
											<script type="text/javascript">
	$("#city").click( function(e) {
		SelCity(this, e);

	});
</script>
											<span class="fl"> </span>
										</td>
									</tr>
									<tr>
										<td class="td_right">
											具体地址：
										</td>
										<td>
											<textarea rows="5" cols="50" style="height: 80px;"
												class="input-text" name="address" id="address"><%=store.getStoreAddress()%></textarea>
										</td>
									</tr>

									<tr>
										<td class="td_right">
											门店状态：
										</td>
										<td class="">
											<span class="fl">
												<div class="select_border">
													<div class="select_containers ">
														<select name="state" class="select">
															<%
																if (store.getState() == 0) {
															%>
															<option value="0" selected="selected">
																关闭
															</option>
															<option value="1">
																开启
															</option>
															<%
																} else if (store.getState() == 1) {
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
											照片：
										</td>
										<td class="">
											<img id="image1"
												src="<%=basePath%>uploadFiles/<%=store.getStoreImage()%>"
												width="180" height="140" />
											<input type="hidden" name="oldImg"
												value="<%=store.getStoreImage()%>" />

										</td>
									</tr>

									<tr>
										<td class="td_right">
											上传图片：
										</td>
										<td class="">
											<input type="file" name="img" class="input-text lh30"
												size="10">
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
