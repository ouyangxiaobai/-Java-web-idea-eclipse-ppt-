<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.cuc.model.SysAdmin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	SysAdmin admin = (SysAdmin) session.getAttribute("admin");
	if (admin == null) {
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
		<title>基于Java web的校园电动车租赁系统管理平台--企业管理人员</title>
		<link rel="stylesheet"
			href="<%=basePath%>businessStore/css/common.css">
		<link rel="stylesheet" href="<%=basePath%>businessStore/css/style.css">
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>businessStore/js/jquery.SuperSlide.js"></script>
		<script type="text/javascript">
	$( function() {
		$(".sideMenu").slide( {
			titCell :"h3",
			targetCell :"ul",
			defaultIndex :0,
			effect :'slideDown',
			delayTime :'500',
			trigger :'click',
			triggerTime :'150',
			defaultPlay :true,
			returnDefault :false,
			easing :'easeInQuint',
			endFun : function() {
				scrollWW();
			}
		});
		$(window).resize( function() {
			scrollWW();
		});
	});
	function scrollWW() {
		if ($(".side").height() < $(".sideMenu").height()) {
			$(".scroll").show();
			var pos = $(".sideMenu ul:visible").position().top - 38;
			$('.sideMenu').animate( {
				top :-pos
			});
		} else {
			$(".scroll").hide();
			$('.sideMenu').animate( {
				top :0
			});
			n = 1;
		}
	}

	var n = 1;
	function menuScroll(num) {
		var Scroll = $('.sideMenu');
		var ScrollP = $('.sideMenu').position();
		/*alert(n);
		alert(ScrollP.top);*/
		if (num == 1) {
			Scroll.animate( {
				top :ScrollP.top - 38
			});
			n = n + 1;
		} else {
			if (ScrollP.top > -38 && ScrollP.top != 0) {
				ScrollP.top = -38;
			}
			if (ScrollP.top < 0) {
				Scroll.animate( {
					top :38 + ScrollP.top
				});
			} else {
				n = 1;
			}
			if (n > 1) {
				n = n - 1;
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
						<a href="<%=basePath%>sysAdmin/SysAdminInfo.jsp" target="content"><img
								src="<%=basePath%>businessStore/images/a.png" alt="" width="60"
								height="60"> </a>
					</div>
					<div id="base_info" class="fr">
						<div class="help_info">
							<a href="1" id="hp">&nbsp;</a>
							<a href="2" id="gy">&nbsp;</a>
							<a href="<%=basePath%>servlet/SysAdminServlet?method=logout"
								id="out">&nbsp;</a>
						</div>
						<div class="info_center">
							<%=admin.getSNo()%>
							<!-- <span id="nt">通知</span><span><a href="#" id="notice">0</a>
							</span> -->
						</div>
					</div>
				</div>
			</div>
			<div id="side_here">
				<div id="side_here_l" class="fl"></div>
				<div id="here_area" class="fl">
					当前位置：
				</div>
			</div>
		</div>
		<div class="side">
			<div class="sideMenu" style="margin: 0 auto">
				<h3>
					租车会员信息
				</h3>
				<ul>

					<li id="li1">
						<a id="a1"
							href="<%=basePath%>servlet/SysAdminServlet?method=pageShowAllMemberInfo"
							target="content"> 会员信息 </a>
					</li>

					<li id="li2">
						<a id="a2" href="<%=basePath%>sysAdmin/AddMemberInfo.jsp"
							target="content"> 注册会员</a>
					</li>
				</ul>
				<h3>
					营业门店信息
				</h3>
				<ul>

					<li>
						<a href="<%=basePath%>servlet/SysAdminServlet?method=ShowAllStore"
							target="content"> 门店信息</a>
					</li>

					<li>
						<a href="<%=basePath%>sysAdmin/AddStoreInfo.jsp" target="content">
							添加门店 </a>
					</li>
				</ul>
				<h3>
					租赁车辆信息
				</h3>
				<ul>

					<li>
						<a href="<%=basePath%>servlet/CarServlet?method=ShowAllCar"
							target="content"> 所有车辆信息</a>
					</li>


					<li>
						<a href="<%=basePath%>servlet/CarServlet?method=sysAdminJumpToAddCarInfo"
							target="content"> 添加车辆</a> 
					</li>
				</ul>
				<!-- <h3>
					车辆业务管理
				</h3>
				<ul>
					<li>
						门店出车
					</li>
					<li>
						门店还车
					</li>
				</ul> -->
				<h3>
					订单信息管理
				</h3>
				<ul>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysSearchAllOrder"
							target="content">全部订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysSearchDaiZhiFuOrder"
							target="content">待支付订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysSearchDaiChuCheOrder"
							target="content"> 待出车订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysSearchRetalingOrder"
							target="content"> 租赁中订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysSearchDaiHuanCheOrder"
							target="content"> 待还车订单</a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysShowFinishedOrder"
							target="content">已完成订单 </a>
					</li>
					<li>
						<a
							href="<%=basePath%>servlet/OrderServlet?method=sysShowCancelOrder"
							target="content">已取消订单 </a>
					</li>
					<li>
						<a
								href="<%=basePath%>servlet/OrderServlet?method=baoxiuzhongorder"
								target="content">报修中的订单 </a>
					</li>
				</ul>

				<h3>
					体验留言信息
				</h3>
				<ul>

					<li>
						<a id="a11"
							href="<%=basePath%>servlet/SysAdminServlet?method=pageShowAllMessage"
							target="content"> 全部留言信息</a>
					</li>
				</ul>

				<h3>
					租赁保险信息
				</h3>
				<ul>

					<li>
						<a
							href="<%=basePath%>servlet/SysAdminServlet?method=ShowAllInsurance"
							target="content"> 浏览租赁保险信息</a>
					</li>

					<li>
						<a href="<%=basePath%>sysAdmin/AddInsuranceInfo.jsp"
							target="content"> 添加租赁保险信息</a>
					</li>
				</ul>
				<h3>
					统计与分析信息
				</h3>
				<ul>
					<li><a href="<%=basePath%>servlet/StoreProfitServlet?method=showDayStoreProfit"
							target="content">企业营业情况统计</a>
						
					</li>
					<li><a href="<%=basePath%>servlet/RegisterServlet?method=showDayRegister"
							target="content">用户发展情况统计</a>
						
					</li>
					<li><a href="<%=basePath%>servlet/CarRentalServlet?method=showDayCarRental"
							target="content">车辆出租情况统计</a>
					
					</li>
				</ul>
			</div>
		</div>
		<div class="main">
			<iframe id="content" src="/CarRentalOnline/WelcomePage.jsp"
				frameborder="no" scrolling="auto" width="100%" height="auto"
				allowtransparency="true" name="content"></iframe>
		</div>
		<div class="bottom">
			<div id="bottom_bg">
				版权
			</div>
		</div>
		<div class="scroll">
			<a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏"
				onclick="menuScroll(1);"></a>
			<a href="javascript:;" class="next" title="使用鼠标滚轴滚动侧栏"
				onclick="menuScroll(2);"></a>
		</div>
		<script type="text/javascript">
	$("#a1").click( function() {
		$("#a1 li").attr("class", "on");
		$("#a2 li").removeClass("on");
	});
	$("#a2").click( function() {
		$("#a1 li").removeClass("on");
		$("#a2 li").attr("class", "on");
	});
</script>
	</body>
</html>