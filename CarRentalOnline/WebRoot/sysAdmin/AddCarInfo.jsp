<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.dao.IBrandDAO"%>
<%@page import="com.cuc.dao.imp.BrandDAO"%>
<%@page import="com.cuc.dao.IBusinessStoreDAO"%>
<%@page import="com.cuc.dao.imp.BusinessStoreDAO"%>
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

		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/main.css">
		<script src="<%=basePath%>/member/laydate/laydate.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.min.js"></script>

		<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
	
	
	
	}
	
	
	
	

</SCRIPT>

<style>

a {
	color: #07c;
	text-decoration: none;
	border: 0;
	background-color: transparent;
}

body,div,q,iframe,form,h5 {
	margin: 0;
	padding: 0;
}

img,fieldset {
	border: none 0;
}

body,td,textarea {
	word-break: break-all;
	word-wrap: break-word;
	line-height: 1.6;
}

body,input,textarea,select,button {
	margin: 0;
	font-size: 14px;
	font-family: Tahoma, SimSun, sans-serif;
}

div,p,table,th,td {
	font-size: 1em;
	font-family: inherit;
	line-height: inherit;
}

h5 {
	font-size: 12px;
}

ol li,ul li {
	margin-bottom: 0.5em;
}

pre,code {
	font-family: "Courier New", Courier, monospace;
	word-wrap: break-word;
	line-height: 1.4;
	font-size: 12px;
}

pre {
	background: #f6f6f6;
	border: #eee solid 1px;
	margin: 1em 0.5em;
	padding: 0.5em 1em;
}

#content {
	padding-left: 50px;
	padding-right: 50px;
}

#content h2 {
	font-size: 20px;
	color: #069;
	padding-top: 8px;
	margin-bottom: 8px;
}

#content h3 {
	margin: 8px 0;
	font-size: 14px;
	COLOR: #693;
}

#content h4 {
	margin: 8px 0;
	font-size: 16px;
	COLOR: #690;
}

#content div.item {
	margin-top: 10px;
	margin-bottom: 10px;
	border: #eee solid 4px;
	padding: 10px;
}

hr {
	clear: both;
	margin: 7px 0; +
	margin: 0;
	border: 0 none;
	font-size: 1px;
	line-height: 1px;
	color: #069;
	background-color: #069;
	height: 1px;
}

.infobar {
	background: #fff9e3;
	border: 1px solid #fadc80;
	color: #743e04;
}

.buttonStyle {
	width: 64px;
	height: 22px;
	line-height: 22px;
	color: #369;
	text-align: center;
	background: url(images/buticon.gif) no-repeat left top;
	border: 0;
	font-size: 12px;
}

.buttonStyle:hover {
	background: url(images/buticon.gif) no-repeat left -23px;
}
</style>
		<script type="text/javascript">
	function getTypeByBrand(brand) {
		var url = "<%=basePath%>servlet/BrandServlet?method=getTypeByBrand";
		$.post(url, {
			"brand" :brand
		}, function(data) {
			var aa = JSON.parse(data)

			var str = "<select>";
			var data = data.split(",");
			for ( var i = 0; i < data.length; i++) {
				var type = data[i].split('\"');
				str = str + "<option name=\"" + type[1] + "\">" + type[1]
						+ "</option>";
			}
			str = str + "</select>";
			document.getElementById("carType").innerHTML = str;
		});
	}

	function alarm() {
		alert("???????????????jpg,gif,bmp???????????????");
	}
	function checkExt() {
	
	
		var allowArray = new Array("jpg", "gif", "bmp");
		var simg = document.getElementById("simg").value;

		var fileExt = simg.substr(simg.lastIndexOf(".") + 1);
		var flag = false;
		for ( var i = 0; i < allowArray.length; i++) {
			if (allowArray[i] == fileExt.toLowerCase()) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			alert("????????????????????????????????????????????????jpg,gif,bmp???????????????");
		}
		return flag;
	}

	function previewImg(filepath) {

		var image1 = document.getElementById("img");
		image1.style.display = "block";
		image1.src = filepath;

	}
</script>

	</head>

	<body>
		<div class="container">

			<div id="forms" class="mt10">
				<div class="box">
					<form action="<%=basePath%>servlet/CarServlet?method=sysAdminAddCarInfo"
						method="post" enctype="multipart/form-data"
						onSubmit="return checkExt();">
						<div class="box_border">
							<div class="box_top">
								<b class="pl15">??????????????????</b>
								<input style="float: right;" type="submit" name="button"
									class="btn btn82 btn_add" value="??????">
							</div>
							<center>
								<table width="944" border="0"
									style="margin-bottom: 50px; margin-top: 40px;">
									<tr>
										<td colspan="2" rowspan="5">
											<img name="img"
												src="<%=basePath%>businessStore/images/add_car_img.png">
											<input type="file" name="simg" id="simg"
												class="input-text lh30" size="10"
												onchange="previewImg(this.value);">
<div style="padding-top: 10px;">
										<strong>????????????</strong><select name="storeId" 
													class="select" style="width: 200px;margin-left: 12px;">
										<%
											IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
											ArrayList<String[]> storeList= storeDAO.searchAllBusinessStore();
											for(String[] store:storeList){
										 %>
													<option value="<%=store[0] %>" >
														<%=store[3] %>
													</option>
										<%} %>
												</select> 	
</div>
										</td>
										<td width="88" height="35" align="right">
											<strong>??????</strong>
										</td>
										<td width="250">
											<input type="text" name="carNumber" class="input-text lh30"
												size="36" style="padding-left: 8px;">
										</td>
										<%--<td width="72" align="right">--%>
											<%--<strong>??????</strong>--%>
										<%--</td>--%>
										<%--<td width="221">--%>
											<%--<span class="fl">--%>
												<%--<div class="select_border">--%>
													<%--<div class="select_containers ">--%>
														<%--<select name="carGear" class="select"--%>
															<%--style="width: 250px;">--%>

															<%--<option value="??????" selected="selected">--%>
																<%--??????--%>
															<%--</option>--%>
															<%--<option value="??????">--%>
																<%--??????--%>
															<%--</option>--%>

														<%--</select>--%>
													<%--</div>--%>
												<%--</div> </span>--%>
										<%--</td>--%>
									</tr>
									<tr>
										<td height="37" align="right">
											<strong>??????</strong>
										</td>
										<td height="37">
											<span class="fl">
												<div class="select_border">
													<div class="select_containers ">
														<select name="carBrand" class="select"
															style="width: 250px;" id="carBrand"
															onchange="getTypeByBrand(this.value)">
															<%
																IBrandDAO brandDAO = new BrandDAO();
																ArrayList<String[]> brandList = brandDAO.getAllBrand();
															%>
															<option value="0">
																--?????????????????????--
															</option>
															<%
																for (String[] brand : brandList) {
															%>

															<option value=<%=brand[0]%>>
																<%=brand[0]%>
															</option>

															<%
																}
															%>
														</select>
													</div>
												</div> </span>
										</td>
										<%--<td align="right">--%>
											<%--<strong>??????</strong>--%>
										<%--</td>--%>
										<%--<td>--%>
											<%--<span class="fl">--%>
												<%--<div class="select_border">--%>
													<%--<input type="text" name="carDisplacement"--%>
														<%--class="input-text lh30" size="24">--%>
													<%--<div class="select_containers ">--%>
														<%--<select name="carDisplacementUnits" class="select"--%>
															<%--style="width: 70px;">--%>

															<%--<option value="L" selected="selected">--%>
																<%--L--%>
															<%--</option>--%>
															<%--<option value="T">--%>
																<%--T--%>
															<%--</option>--%>

														<%--</select>--%>
													<%--</div>--%>
												<%--</div> </span>--%>
										<%--</td>--%>
									</tr>
									<tr >
										<td height="42" align="right">
											<strong>??????</strong>
										</td>
										<td height="42">
											<span class="fl">
												<div class="select_border">
													<div class="select_containers ">
														<select id="carType" name="carType" class="select"
															style="width: 250px;">
															<option value="0" selected="selected">
																--?????????????????????--
															</option>
														</select>
													</div>
												</div> </span>
										</td>
										<%--<td align="right">--%>
											<%--<strong>??????</strong>--%>
										<%--</td>--%>
										<%--<td>--%>
											<%--<span class="fl">--%>
												<%--<div class="select_border">--%>
													<%--<div class="select_containers ">--%>
														<%--<select name="seat" id="seat" class="select"--%>
															<%--style="width: 250px;">--%>

															<%--<option value="2" selected="selected">--%>
																<%--2???--%>
															<%--</option>--%>
															<%--<option value="5">--%>
																<%--5???--%>
															<%--</option>--%>
															<%--<option value="7">--%>
																<%--7???--%>
															<%--</option>--%>
															<%--<option value="8">--%>
																<%--8???--%>
															<%--</option>--%>

														<%--</select>--%>

													<%--</div>--%>
												<%--</div> </span>--%>
										<%--</td>--%>
									</tr>
									<%--<tr>--%>
										<%--<td height="40" align="right">--%>
											<%--<strong>??????</strong>--%>
										<%--</td>--%>
										<%--<td height="40">--%>
											<%--<span class="fl">--%>
												<%--<div class="select_border">--%>
													<%--<div class="select_containers ">--%>
														<%--<select name="carForm" class="select"--%>
															<%--style="width: 250px;">--%>

															<%--<option value="?????????" selected="selected">--%>
																<%--?????????--%>
															<%--</option>--%>
															<%--<option value="?????????">--%>
																<%--?????????--%>
															<%--</option>--%>
															<%--<option value="SUV">--%>
																<%--SUV--%>
															<%--</option>--%>
															<%--<option value="?????????">--%>
																<%--?????????--%>
															<%--</option>--%>

														<%--</select>--%>
													<%--</div>--%>
												<%--</div> </span>--%>
										<%--</td>--%>
										<%--&lt;%&ndash;<td align="right">&ndash;%&gt;--%>
											<%--&lt;%&ndash;<strong>??????</strong>&ndash;%&gt;--%>
										<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
										<%--&lt;%&ndash;<td>&ndash;%&gt;--%>
											<%--&lt;%&ndash;<span class="fl">&ndash;%&gt;--%>
												<%--&lt;%&ndash;<div class="select_border">&ndash;%&gt;--%>
													<%--&lt;%&ndash;<div class="select_containers ">&ndash;%&gt;--%>
														<%--&lt;%&ndash;<select name="compartment" id="compartment" class="select"&ndash;%&gt;--%>
															<%--&lt;%&ndash;style="width: 250px;">&ndash;%&gt;--%>
															<%--&lt;%&ndash;<option id="compartmentop1" value="??????">&ndash;%&gt;--%>
																<%--&lt;%&ndash;??????&ndash;%&gt;--%>
															<%--&lt;%&ndash;</option>&ndash;%&gt;--%>
															<%--&lt;%&ndash;<option id="compartmentop2" value="??????">&ndash;%&gt;--%>
																<%--&lt;%&ndash;??????&ndash;%&gt;--%>
															<%--&lt;%&ndash;</option>&ndash;%&gt;--%>
															<%--&lt;%&ndash;<option id="compartmentop3" value="SUV">&ndash;%&gt;--%>
																<%--&lt;%&ndash;SUV&ndash;%&gt;--%>
															<%--&lt;%&ndash;</option>&ndash;%&gt;--%>
														<%--&lt;%&ndash;</select>&ndash;%&gt;--%>
													<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
												<%--&lt;%&ndash;</div> </span>&ndash;%&gt;--%>
										<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
									<%--</tr>--%>
									<tr>
										<%--<td height="40" align="right">--%>
											<%--<strong>????????????</strong>--%>
										<%--</td>--%>
										<%--<td height="40">--%>
											<%--<input type="text" name="engineNum" class="input-text lh30"--%>
												<%--size="36" style="padding-left: 8px;">--%>
										<%--</td>--%>
										<td align="right">
											<strong>????????????</strong>
										</td>
										<td>
											<input name="buyDate" style="width: 250px; height: 30px"
												id="buyDate" class="laydate-icon" onclick="laydate()">
										</td>
									</tr>
									<tr>
										<td width="49" height="46" align="left">
											<strong>??????</strong>
										</td>
										<td width="220">
											<input type="text" name="carMoney" class="input-text lh30"
												size="32" style="padding-left: 8px;">
										</td>
										<%--<td align="right">--%>
											<%--<strong>?????????</strong>--%>
										<%--</td>--%>
										<%--<td>--%>
											<%--<input type="text" name="carframeNum" class="input-text lh30"--%>
												<%--size="36" style="padding-left: 8px;">--%>
										<%--</td>--%>
										<td align="right">
											<strong>????????????</strong>
										</td>
										<td>
											<span class="fl">
												<div class="select_border">
													<div class="select_containers ">
														<select name="state" id="state" class="select"
															style="width: 250px;">


															<option id="close" value="0">
																??????
															</option>
															<option id="open" value="1" selected="selected">
																??????
															</option>

														</select>
													</div>
												</div> </span>
										</td>
									</tr>
								</table>

							</center>
						</div>
					</form>
				</div>
			</div>
		</div>


	</body>
</html>
