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
		<base href="<%=basePath%>">

		<title>背景简介 - 租车服务有限公司</title>

		<link href="<%=basePath%>member/yizuche/public.css" type="text/css"
			rel="stylesheet">

		<link href="<%=basePath%>member/yizuche/index.css" type="text/css"
			rel="stylesheet">

		<link href="<%=basePath%>member/yizuche/css.css" rel="stylesheet"
			type="text/css">

	</head>

	<body>

<!-- 头部 -->
		<jsp:include page="Header.jsp" />
		<!--head end -->

		<div class="about_banner">
			<img src="<%=basePath%>member/yizuche/1447905061.jpg" width="1920"
				height="420">
		</div>
		<!--公司介绍-->
		<div class="whitebg" >
			<div class="iconbox">
				<div class="about_hd">
					<h2 >
						公司介绍
					</h2>
					<p>
						COMPANY PROFILE
					</p>
				</div>
				<div class="about_bd">
					<div class="about_company_pic mt40">
						<img src="<%=basePath%>member/yizuche/video.jpg" title="公司简介">
					</div>
					<div class="about_company_text">
						<p>
							电动车服务有限公司，作为福建领先的电动车租赁服务提供商，凭借雄厚的实力基础，为客户提供专业优质的用车服务。

							作为一家集专车服务、公务用车、企业用车、以租代购、机场接送、商务考察、会议接待、婚庆礼宾、司机租赁等多功能服务于一身的综合性电动车服务公司，
							2015年顺利承接第一届全国青年运动会赛事组委会及运动员专车服务项目，赢得了社会各界的高度赞誉。

							专业化司机团队，全程保驾护航，以全方位、高品质的卓越服务，让客户真切的感受到公司服务至上的经营理念。易租车结合福建本土市场特点，研发出多套具有领先水平的电动车调度管理系统，辅以GPS全球定位、智能数据分析处理等技术，组建了一个强大的中央数据服务中心，力求将资源管理能力发挥到极致，实现了立足于福州，经营幅射全国的强大功能，让广大客户轻松享受愉悦的租车方式。

							“车出必达，服务至上”是易租人的承诺。 “规范化、亲情化、个性化、专业化”是易租人不变的服务准则。
						</p>
					</div>
				</div>
				<ul class="about_num fn_cle">
					<li>
						当日注册用户突破
						<span>500</span>人
					</li>
					<li>
						公司拥有车辆超
						<span>200</span>部
					</li>
					<li>
						当日用户在线预订超
						<span>200</span>单
					</li>
					<li>
						公司团队员工超
						<span>150</span>人
					</li>
				</ul>
			</div>
		</div>
		<!--公司理念-->
		<div class="philosophybg">
			<div class="iconbox philosophy">
				<div class="about_hd">
					<h2>
						公司理念
					</h2>
					<p style="color: #666">
						COMPANY PHILOSOPHY
					</p>
				</div>
				<div class="about_bd">
					<ul>
						<li>
							<dl>
								<dt>
									<img src="<%=basePath%>member/yizuche/company01.png">
								</dt>
								<dd class="philo-tit">
									易租车使命
								</dd>
								<dd class="philo-con">
									互联网金融生态系统的颠覆，最革命的创造！
								</dd>
							</dl>
						</li>
						<li class="philo-limt">
							<dl>
								<dt>
									<img src="<%=basePath%>member/yizuche/company02.png">
								</dt>
								<dd class="philo-tit">
									易租车理念
								</dd>
								<dd class="philo-con">
									定义互联网时代的风投类活期理财，最高标准！
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt>
									<img src="<%=basePath%>member/yizuche/company03.png">
								</dt>
								<dd class="philo-tit">
									易租车蓝图
								</dd>
								<dd class="philo-con">
									让互联网金融时代所有活期理财，最高收益！
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 公司团队-->
		<div class="whitebg team" >
			<div class="about_hd">
				<h2>
					公司团队
				</h2>
				<p>
					COMPANY TEAM
				</p>
			</div>
			<div class="iconbox" style="position: relative;">
				<div class="team_hd">

					<ul>
						<li>
							<img src="<%=basePath%>member/yizuche/memo7.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo3.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo1.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo6.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo5.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo2.png">
							<i></i>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/memo4.png">
							<i></i>
						</li>
					</ul>

				</div>
			</div>

		</div>
		<!-- 团队风采-->
		<div class="whitebg">
			<div class="iconbox team">
				<div class="about_hd">
					<h2>
						团队风采
					</h2>
					<p>
						TEAM STYLE
					</p>
				</div>
				<div class="company-showcase-con">
					<ul>
						<li>
							<img src="<%=basePath%>member/yizuche/team01.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team02.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team03.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team04.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team05.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team06.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team07.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
						<li>
							<img src="<%=basePath%>member/yizuche/team08.png">
							<div class="dask">
								<p>
									会议大厅
								</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--成长历程-->
		<div class="bluebg" style="display: none;">
			<div class="iconbox our_history">
				<div class="about_hd">
					<h2>
						成长历程
					</h2>
					<p>
						Our history
					</p>
				</div>
				<div class="about_bd">
					<ul>
						<li>
							<p class="time">
								2013,6
							</p>
							<p class="text">
								福州易租车梦想团队由来自中国最大的电动车租赁公司的两名80后资深合伙人创始。
							</p>
						</li>
						<li>
							<p class="time">
								2014,1
							</p>
							<p class="text">
								福州易租车名字的正式定名，寓意每一位为对鲸鱼信任，而默默努力的鲸鱼金融精英,都像这个地球上最大的精灵一样，在蓝色海洋里奋斗不息，拥有最伟大的互联网梦
							</p>
						</li>
						<li>
							<p class="time">
								2014,1
							</p>
							<p class="text">
								2014,1福州易租车梦想团队正式放弃市场主流的以联网金融史上，最新首家独立研发出互联网基因,银行担保,银行信用背书的债权基金。即日结息，最高收益宝宝商业模式。
							</p>
						</li>
						<li>
							<p class="time">
								2014,9
							</p>
							<p class="text">
								在50位福州易租车金融精英共同努力下，历时15个月,完成鲸鱼宝交易平台研发工作。鲸鱼金融依托中国最大的互联网交易平台平安银行旗下全资子公司
							</p>
						</li>
						<li>
							<p class="time">
								2014,11
							</p>
							<p class="text">
								历时3个月的金融系统安全性五星测试后"鲸鱼宝"APP以极致、简单、易用的理念，正式亮相APP STORE及Android市场。
							</p>
						</li>
						<li>
							<p class="time">
								2014,12
							</p>
							<p class="text">
								声宝发展成为平安银行旗下全资子公司最大的债权持仓机构。
							</p>
						</li>
					</ul>
				</div>
			</div>
		</div>
<!----------------------底部开始------------------------------------------------>

		<center>
			<div style="clear: both; ">
				<p>
					Copyright©2022 All Rights Reserved. 基于Java web的校园电动车租赁系统
				</p>
			</div>
		</center>
		<!----------------------底部结束------------------------------------------------>
	</body>
</html>
