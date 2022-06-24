<%@page import="com.cuc.model.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.dao.IMessageDAO"%>
<%@page import="com.cuc.dao.imp.MessageDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cuc.dao.IBusinessStoreDAO"%>
<%@page import="com.cuc.dao.imp.BusinessStoreDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	//以下代码是页面保护代码
	//清除过期缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>member/shenzhou/style1.3.17.7.css">
		<link href="<%=basePath%>member/shenzhou/zc_global.css"
			rel="stylesheet" type="text/css">
		<title>基于Java web的校园电动车租赁系统—中国最大租车公司!天天超低价!!!</title>
		<link href="<%=basePath%>member/shenzhou/index.css" rel="stylesheet"
			type="text/css">

		<!--JS轮播代码开始-->
		<link href="<%=basePath%>member/css/jquery.slideBox.css"
			rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>member/laydate/laydate.js"></script>
		<script src="<%=basePath%>member/script/jquery-1.7.1.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>member/script/jquery.slideBox.js"
			type="text/javascript"></script>
		<script>
	jQuery(function($) {
		$('#demo1').slideBox({
			hideClickBar : false,//不自动隐藏点选按键
		});
		$('#demo2').slideBox({
			direction : 'top',//left,top#方向
			duration : 0.3,//滚动持续时间，单位：秒
			easing : 'linear',//swing,linear//滚动特效
			delay : 5,//滚动延迟时间，单位：秒
			startIndex : 1
		//初始焦点顺序
		});
		$('#demo3').slideBox({
			duration : 0.3,//滚动持续时间，单位：秒
			easing : 'linear',//swing,linear//滚动特效
			delay : 5,//滚动延迟时间，单位：秒
			hideClickBar : false,//不自动隐藏点选按键
			clickBarRadius : 10
		});
		$('#demo4').slideBox({
			hideBottomBar : true
		//隐藏底栏
		});
	});;
	!function() {
		laydate({
			elem : '#demo'
		})
	}();
	
	
	function getStoreName(){
	
		var procit = document.getElementById("procit");
		var prov=procit[procit.selectedIndex].value; 
		var toprocity = document.getElementById("toprocity");
		var newstr = prov.split(" ");
	
	
		//get请求
		var url = "<%=basePath%>/servlet/StoreServlet?method=getStoreName&province="+newstr[0]+"&city="+newstr[1];
	//	var url = "<%=basePath%>/servlet/StoreServlet?method=getStoreName&province="+encodeURI(newstr[0])+"&city="+encodeURI(encodeURI(newstr[1]));
		$.get(url,function(data,status){
      		//alert("数据：" + data + "\n状态：" + status);
      		var str="<select>"; 
      		var data = data.split("\n");
      		for(var i=0;i<data.length-1;i++) 
		    {         
		    	str=str+"<option value="+data[i]+">"+data[i]+"</option>";     
		    } 
      		str=str+"</select>"; 
      		document.getElementById("storeName").innerHTML=str; 
      		document.getElementById("tostoreName").innerHTML=str; 
    	});
    	
    	var optionNum=procit.selectedIndex;
    	$("#toprocity option:eq("+optionNum+")").attr("selected","selected");
	}
	
	function getStoreName2(){
		var procit = document.getElementById("toprocity");
		var prov=procit[procit.selectedIndex].value; 
		
		var newstr = prov.split(" ");
		
		//get请求
		var url = "<%=basePath%>/servlet/StoreServlet?method=getStoreName&province="+newstr[0]+"&city="+newstr[1];
		$.get(url,function(data,status){
      		//alert("数据：" + data + "\n状态：" + status);
      		var str="<select>"; 
      		var data = data.split("\n");
      		for(var i=0;i<data.length-1;i++) 
		    {         
		    	str=str+"<option>"+data[i]+"</option>";     
		    } 
      		str=str+"</select>"; 
      		document.getElementById("tostoreName").innerHTML=str; 
    	});
    	
	}
	
//	$(document).ready(function(){
//  $("button").click(function(){
//    $.get("<%=basePath%>/servlet/StoreServlet?method=getStoreName&province=福建省&city=福州市",function(data,status){
 //     alert("数据：" + data + "\n状态：" + status);
//    });
//  });
//});
	
</script>
		<!--JS轮播代码结束-->
		<!--------------------------------------1嗨体验说css---------------------------------------------->
		<link href="<%=basePath%>member/css/idx" rel="stylesheet">
		<!--------------------------------------1嗨体验说css--------------------------------------------->
	</head>
	<body>

		<!-- 头部 -->
	<jsp:include page="Header.jsp" /> 
		<!--head end -->

		<div id="demo1" class="slideBox"
			style="width: 100%; height: 100%; margin: 0 auto;">
			<ul class="items">
				<li>
					<a href="#" title="万辆新车放肆租"><img width="100%"
							src="<%=basePath%>member/images/1/1.jpg"> </a>
				</li>
				<li>
					<a href="#" title="充100送20"><img width="100%"
							src="<%=basePath%>member/images/1/2.jpg"> </a>
				</li>
				<li>
					<a href="#" title="新用户专享150元见面礼"><img width="100%"
							src="<%=basePath%>member/images/1/3.jpg"> </a>
				</li>
				<li>
					<a href="#" title="上门取送车10元冰点价"><img width="100%"
							src="<%=basePath%>member/images/1/4.jpg"> </a>
				</li>
			</ul>
		</div>

		<!---------------------------快速预订租赁车辆开始--------------------------------------------------------------------->

		<form action="<%=basePath%>servlet/CarServlet?method=searchCar"
			method="post">
			<div style="top: 100px;" class="zc-search">
				<div class="zc_index_select">
					<div>
						<!-- 短租自驾开始 -->
						<div class="indexContents cur">
							<div class="zc_index_dz">
								<div class="h20 p-re zc-iptbox" id="search_short_tips"></div>
								<dl class="zc_item_cont fl">
									<dt>
										<strong>取车</strong>
										<label id="shangmensongche_label" class="songchediv">
											<input type="checkbox" name="isSongCheShangMen" value="scsm"/>
											送车上门
										</label>
									</dt>
									<dd>
										<select name="procit" id="procit" onchange="getStoreName()"
											style="width: 190px; height: 30px" id="fromCityName"
											autocomplete="on" link-input="fromCityId" maxlength="10">
											<option value="0">
												--请选择所在城市--
											</option>
											<%
												IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
												ArrayList<String[]> cityList = storeDAO.getAllBusinessCity();
												if (cityList != null && cityList.size() != 0) {
													for (int i = 0; i < cityList.size(); i++) {
														String[] pc = cityList.get(i);
											%>
											<option value="<%=pc[0]%> <%=pc[1]%>">
												<%=pc[0]%><%=pc[1]%>
											</option>
											<%
												}
												}
											%>
										</select>
									</dd>
									<dd class="">
										<select name="storeName" id="storeName"
											style="width: 190px; height: 30px" name="fromStore"
											id="fromStore" autocomplete="on" link-input="fromCityId"
											maxlength="10">

										</select>
										<div class="areaSelection hide" style="display: none;"></div>
									</dd>
									<dd class="h15"></dd>
									<dd class="p-re">
										<%
											SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
											// new Date()为获取当前系统时间
										%>
										<input name="beginDate" style="width: 190px; height: 30px"
											value="<%=df.format(new Date())%>" id="beginDate"
											class="laydate-icon" onclick="laydate()">
									</dd>
									<dd>
										<select style="width: 190px; height: 30px" name="beginHour"
											id="beginHour" autocomplete="on" link-input="fromCityId"
											maxlength="10">
											<option value="08:30">
												08:30
											</option>
											<option value="09:00">
												09:00
											</option>
											<option value="09:30">
												09:30
											</option>
											<option value="10:00">
												10:00
											</option>
											<option value="10:30">
												10:30
											</option>
											<option value="11:00">
												11:00
											</option>
											<option value="11:30">
												11:30
											</option>
											<option value="12:00">
												12:00
											</option>
											<option value="12:30">
												12:30
											</option>
											<option value="13:00">
												13:00
											</option>
											<option value="13:30">
												13:30
											</option>
											<option value="14:00">
												14:00
											</option>
											<option value="14:30">
												14:30
											</option>
											<option value="15:00">
												15:00
											</option>
											<option value="15:30">
												15:30
											</option>
											<option value="16:00">
												16:00
											</option>
											<option value="16:30">
												16:30
											</option>
											<option value="17:00">
												17:00
											</option>
											<option value="17:30">
												17:30
											</option>
											<option value="18:00">
												18:00
											</option>
											<option value="18:30">
												18:30
											</option>
											<option value="19:00">
												19:00
											</option>
											<option value="19:30">
												19:30
											</option>
											<option value="20:00">
												20:00
											</option>
											<option value="20:30">
												20:30
											</option>
											<option value="21:00">
												21:00
											</option>

										</select>
										<!--取车时间开始-->
										<div class="sz_time" style="display: none;">
											<ol class="clearfix xbox">
												<li class="graybor">
													<div class="iptSeup areaup"></div>
												</li>
												<li class="bluebor">
													&nbsp;
												</li>
											</ol>
											<div class="sz_timeinfo clearfix fromtime"></div>
										</div>
										<!--取车时间结束-->
									</dd>
								</dl>
								<dl class="zc_item_cont fr">
									<dt>
										<strong>还车</strong>
										<label id="shangmequche_label" class="quchediv">
											<input type="checkbox" name="isShangMenQuChe" value="smqc"/>
											上门取车
										</label>
									</dt>
									<dd>
										<select style="width: 190px; height: 30px" name="toprocity"
											onchange="getStoreName2()" id="toprocity" autocomplete="on"
											link-input="fromCityId" maxlength="10">
											<option value="0">
												--请选择所在城市--
											</option>
											<%
												if (cityList != null && cityList.size() != 0) {
													for (int i = 0; i < cityList.size(); i++) {
														String[] pc = cityList.get(i);
											%>
											<option value="<%=pc[0]%> <%=pc[1]%>">
												<%=pc[0]%><%=pc[1]%>
											</option>
											<%
												}
												}
											%>
										</select>
									</dd>
									<dd>
										<select name="tostoreName" style="width: 190px; height: 30px"
											id="tostoreName" autocomplete="on" link-input="fromCityId"
											maxlength="10">

										</select>
										<div class="areaSelection hide" style="display: none;"></div>
									</dd>
									<dd class="h15"></dd>
									<dd class="p-re">
										<input name="endDate" style="width: 190px; height: 30px"
											value="<%=df.format(new Date())%>" id="beginDate"
											class="laydate-icon" onclick="laydate()">
									</dd>
									<dd>
										<select style="width: 190px; height: 30px" name="endHour"
											id="endHour" autocomplete="on" link-input="fromCityId"
											maxlength="10">
											<option value="08:30">
												08:30
											</option>
											<option value="09:00">
												09:00
											</option>
											<option value="09:30">
												09:30
											</option>
											<option value="10:00">
												10:00
											</option>
											<option value="10:30">
												10:30
											</option>
											<option value="11:00">
												11:00
											</option>
											<option value="11:30">
												11:30
											</option>
											<option value="12:00">
												12:00
											</option>
											<option value="12:30">
												12:30
											</option>
											<option value="13:00">
												13:00
											</option>
											<option value="13:30">
												13:30
											</option>
											<option value="14:00">
												14:00
											</option>
											<option value="14:30">
												14:30
											</option>
											<option value="15:00">
												15:00
											</option>
											<option value="15:30">
												15:30
											</option>
											<option value="16:00">
												16:00
											</option>
											<option value="16:30">
												16:30
											</option>
											<option value="17:00">
												17:00
											</option>
											<option value="17:30">
												17:30
											</option>
											<option value="18:00">
												18:00
											</option>
											<option value="18:30">
												18:30
											</option>
											<option value="19:00">
												19:00
											</option>
											<option value="19:30">
												19:30
											</option>
											<option value="20:00">
												20:00
											</option>
											<option value="20:30">
												20:30
											</option>
											<option value="21:00">
												21:00
											</option>
										</select>
										<!--还车时间开始-->
										<div class="sz_time" style="display: none;">
											<ol class="clearfix xbox">
												<li class="graybor">

													<br>
													<br>
												</li>
												<li class="bluebor">
													（）&nbsp;
													<br>
													<br>
												</li>
											</ol>
											<div class="sz_timeinfo clearfix totime"></div>
										</div>
										<!--还车时间结束-->
									</dd>
								</dl>
								<div style="margin-bottom: 30px" class="h20 clear"></div>
								<div class="clear">
									<input type="submit" value="立即选车" class="s_btn" title="立即选车"
										id="short_goselectcar">
								</div>
							</div>
						</div>
						<!-- 短租自驾结束 -->
					</div>
				</div>
			</div>
		</form>
		<!-----------------------------快速预订租赁车辆结束------------------------------------------------------------------->

		<!------------------------------基于Java web的校园电动车租赁系统内容部分开始--------------------------------------------------------------->
		<div style="position: absolute; top: 550px" class="zc_brand">
			<div class="zc_main">
				<ul>
					<li>
						<i class="zc-yecx"></i>
						<p>
							<b>100+</b>车型
						</p>
					</li>
					<li>
						<i class="zc-yewd"></i>
						<p>
							<b>1000+</b>网点
						</p>
					</li>
					<li>
						<i class="zc-yecxn"></i>
						<p>
							<b>100%</b>车险
						</p>
					</li>
					<li>
						<i class="zc-yelc"></i>
						<p>
							无限里程
						</p>
					</li>
				</ul>
			</div>
			<div class="main">
				<div class="main-box">
					<div style="width: 1300px;" class="main-box-left">
						<!--热门车型-->
						<div class="hotcar-box">
							<div class="title">
								热门车型
								<a href="#" target="_blank">更多</a>
							</div>
							<ul style="height: 280px;">
								<li style="height: 330px">
									<em class="hotcar-icon"></em>
									<div class="hotcar-img">
										<a href="#" target="_blank"> <img
												style="width: 285px; height: 205px"
												src="<%=basePath%>member/images/2/1.jpg">
										</a>
									</div>
									<%--<div style="margin-left: 20px" class="hotcar-des">--%>
										<%--<div style="float: left" class="car-name">--%>
											<%--<a style="color: #666; font-size: 16px; font-family: '微软雅黑';"--%>
												<%--href="#" target="_blank">丰田普拉多</a>--%>
										<%--</div>--%>
										<%--<div style="float: left; clear: both" class="car-type">--%>
											<%--运动 操控 SUV|5座--%>
										<%--</div>--%>
									<%--</div>--%>
								</li>
								<li>
									<em class="hotcar-icon"></em>
									<div class="hotcar-img">
										<a href="#" target="_blank"> <img
												style="width: 285px; height: 205px"
												src="<%=basePath%>member/images/2/2.jpg"> </a>
									</div>
									<%--<div style="margin-left: 20px" class="hotcar-des">--%>
										<%--<div style="float: left; clear: both" class="car-name">--%>
											<%--<a style="color: #666; font-size: 16px; font-family: '微软雅黑';"--%>
												<%--href="#" target="_blank">大众朗逸</a>--%>
										<%--</div>--%>
										<%--<div style="float: left; clear: both" class="car-type">--%>
											<%--省油 舒适 |三厢|5座--%>
										<%--</div>--%>
									<%--</div>--%>
								</li>
								<li>
									<em class="hotcar-icon"></em>
									<div class="hotcar-img">
										<a style="color: #666" href="#" target="_blank"> <img
												style="width: 285px; height: 205px"
												src="<%=basePath%>member/images/2/3.jpg">
										</a>
									</div>
									<%--<div style="margin-left: 20px" class="hotcar-des">--%>
										<%--<div style="float: left; clear: both" class="car-name">--%>
											<%--<a style="color: #666; font-size: 16px; font-family: '微软雅黑';"--%>
												<%--href="#" target="_blank">路虎发现神行</a>--%>
										<%--</div>--%>
										<%--<div style="float: left; clear: both" class="car-type">--%>
											<%--豪华 气派 SUV|5座--%>
										<%--</div>--%>
									<%--</div>--%>
								</li>
								<li>
									<em class="hotcar-icon"></em>
									<div class="hotcar-img">
										<a href="#" target="_blank"> <img
												style="width: 285px; height: 205px"
												src="<%=basePath%>member/images/2/4.jpg"> </a>
									</div>
									<%--<div style="margin-left: 20px" class="hotcar-des">--%>
										<%--<div style="float: left; clear: both" class="car-name">--%>
											<%--<a style="color: #666; font-size: 16px; font-family: '微软雅黑';"--%>
												<%--href="#" target="_blank">标致3008</a>--%>
										<%--</div>--%>
										<%--<div style="float: left; clear: both" class="car-type">--%>
											<%--时尚 运动|SUV|5座--%>
										<%--</div>--%>
									<%--</div>--%>
								</li>
							</ul>
							<%--<div class="car-list-brands">--%>
								<%--<a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/cc821522-8f47-4a44-969c-8967afd8b5c5.jpg">--%>
								<%--</span>一汽马自达</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/71e5afaa-ee14-4ce7-bfeb-d46d1e805a48.jpg">--%>
								<%--</span>大众</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/8be9cae8-da73-45f9-8e94-c2f090a5aab9.jpg">--%>
								<%--</span>大众斯柯达</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/b40b9276-db53-4897-81c7-f7bd9b08abc0.jpg">--%>
								<%--</span>标致</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/f018db1a-2802-4e49-8c6b-9eafc6756815.jpg">--%>
								<%--</span>MG</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/roewe.jpg"> </span>荣威</a><a--%>
									<%--href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/5b9423e6-7ef1-45e3-8f3a-73c31838007c.jpg">--%>
								<%--</span>本田</a><a href="#" target="_blank"><span><img--%>
											<%--src="<%=basePath%>/member/images/chevrolet.jpg"> </span>雪佛兰</a><a--%>
									<%--href="#" class="all-brand">全部品牌</a>--%>
							<%--</div>--%>
						</div>
						<!--/热门车型-->
					</div>
				</div>
			</div>

			<!------------------------体验说开始----------------------------------------------->
			<div class="tys-box">
				<div class="title">
					用户分享帖
					<a href="<%=basePath%>servlet/MessageServlet?method=welShowMessage">更多</a>
				</div>
				<div class="tys-list">
					<ul style="height: 205px">
						<%
							IMessageDAO messMemberDAO = new MessageDAO();
							ArrayList<String[]> list = messMemberDAO.searchAllMessageByState(1,
									4, 1);
							if (list != null && list.size() != 0) {
								for (int i = 0; i < list.size(); i++) {
									String content = list.get(i)[5];
						%>
						<li>
							<em></em>
							<div class="tys-txt">
								<%=content%>...
							</div>
							<a href="#" target="_blank">[去看看]</a>
						</li>
						<%
								}
							}
						%>
					</ul>
				</div>
			</div>
		</div>
		<!----------------------体验说结束----------------------------------------------------->

		<!------------------------------------------------基于Java web的校园电动车租赁系统内容部分结束------------------------------------------------>
		<!----------------------底部开始------------------------------------------------>

		<center>
			<div style="clear: both; margin-top: 800px">
				<p >
					Copyright©2022 All Rights Reserved. 基于Java web的校园电动车租赁系统
				</p>
			</div>
		</center>
		<!----------------------底部结束------------------------------------------------>
	</body>
</html>