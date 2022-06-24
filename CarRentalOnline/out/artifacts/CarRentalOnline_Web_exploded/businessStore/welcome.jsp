<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.model.BusinessStore"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	BusinessStore store = (BusinessStore) session.getAttribute("store");
	if (store == null) {
		response
				.getWriter()
				.write(
						"<script language =javascript>window.open('../member/login.jsp','_top')</script>");

		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>基于Java web的校园电动车租赁系统--营业门店后台管理</title>
		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/style.css">
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.SuperSlide.js"></script>
		<script type="text/javascript">
  $(function(){
      $(".sideMenu").slide({
         titCell:"h3", 
         targetCell:"ul",
         defaultIndex:0, 
         effect:'slideDown', 
         delayTime:'500' , 
         trigger:'click', 
         triggerTime:'150', 
         defaultPlay:true, 
         returnDefault:false,
         easing:'easeInQuint',
         endFun:function(){
              scrollWW();
         }
       });
      $(window).resize(function() {
          scrollWW();
      });
  });
  function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();
  /*alert(n);
  alert(ScrollP.top);*/
  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}
  </script>
	</head>
	<body>
		<div class="top">
			<div id="top_t">
				<div id="logo" class="fl"></div>
				<div id="photo_info" class="fr">
					<div id="photo" class="fl">
						&nbsp;
						<a href="#"><img src="<%=basePath%>businessStore/images/a.png"
								alt="" width="60" height="60"> </a>
					</div>
					<div id="base_info" class="fr">
						<div class="help_info">
							<a href="1" id="hp">&nbsp;</a>
							<a href="2" id="gy">&nbsp;</a>
							<a href="<%=basePath%>servlet/StoreServlet?method=logout"
								id="out">&nbsp;</a>
						</div>
						<div class="info_center">
							<%=store.getStoreName()%>
						</div>
					</div>
				</div>
			</div>
			<div id="side_here">
				<div id="side_here_l" class="fl"></div>
				<div id="here_area" class="fl">
				</div>
			</div>
		</div>
		<div class="side">
			<div class="sideMenu" style="margin: 0 auto">
				<h3>
					商家管理
				</h3>
				<ul>

					<li>
						<a href="<%=basePath%>businessStore/StoreInfo.jsp"
							target="content"> 商家基本资料</a>
					</li>

					<li>
						<a href="<%=basePath%>businessStore/ChangePassword.jsp"
							target="content"> 修改密码 </a>
					</li>
				</ul>
				<h3>
					电动车管理
				</h3>
				<ul>
					<li>
						<a href="<%=basePath%>servlet/StoreServlet?method=pageSearchCar"
							target="content"> 电动车基本信息</a>
					</li>
					<li><a href="<%=basePath%>servlet/CarServlet?method=jumpToAddCarInfo"
							target="content"> 添加电动车</a>
						
					</li>
				</ul>
				<h3>
					商家交易管理
				</h3>
				<ul>
					<li>

						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchDaiChuCheOrder"
							target="content"> 出车管理</a>

					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchCanReturnOrder"
							target="content"> 还车管理</a>
					</li>

				</ul>
				<h3>
					车辆订单管理
				</h3>
				<ul>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchAllOrder"
							target="content">全部订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchDaiZhiFuOrder"
							target="content">待支付订单</a>

					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchDaiChuCheOrder"
							target="content"> 待出车订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchRetalingOrder"
							target="content"> 租赁中订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeSearchDaiHuanCheOrder"
							target="content"> 待还车订单</a>

					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeShowFinishedOrder"
							target="content">已完成订单 </a>

					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=storeShowCancelOrder"
							target="content">已取消订单 </a>

					</li>
					<li>
						<a
								href="<%=basePath%>servlet/OrderServlet?method=baoxiuzhongordermen"
								target="content">报修中的订单 </a>
					</li>
				</ul>
				<h3>
					统计与分析信息
				</h3>
				<ul>
					<li><a href="<%=basePath%>servlet/StoreProfitServlet?method=storeShowDayStoreProfit"
							target="content">企业营业情况统计</a>
						
					</li>
					
				</ul>
			</div>
		</div>
		<div class="main">
			<iframe id="content" src="<%=basePath%>businessStore/StoreInfo.jsp"
				frameborder="no" scrolling="auto" width="100%" height="auto"
				allowtransparency="true" name="content"></iframe>
		</div>
		<div class="bottom">
			<div id="bottom_bg">
				Copyright©2022 All Rights Reserved. 基于Java web的校园电动车租赁系统
			</div>
		</div>
		<div class="scroll">
			<a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏"
				onclick="menuScroll(1);"></a>
			<a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏"
				onclick="menuScroll(2);"></a>
		</div>
	</body>
</html>