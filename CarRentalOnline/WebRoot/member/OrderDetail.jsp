<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cuc.dao.IFrequentContactsDAO"%>
<%@page import="com.cuc.dao.imp.FrequentContactsDAO"%>
<%@page import="com.cuc.util.CommonUtil"%>
<%@page import="com.cuc.dao.IBusinessStoreDAO"%>
<%@page import="com.cuc.dao.imp.BusinessStoreDAO"%>
<%@page import="com.cuc.model.BusinessStore"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<link href="<%=basePath%>member/css/Global.css" rel="stylesheet">
		<script type="text/javascript">
	function del() {
		var msg = "您真的确定要取消订单吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
	</head>

	<body>

		<%
	Map<String, Object> orderMap =	(Map<String, Object> )request.getAttribute("orderMap");
	Map<String, Object> carMap =	(Map<String, Object> )request.getAttribute("carMap");
	ArrayList<String[]> insuranceList =	(ArrayList<String[]> )request.getAttribute("insuranceList");
 		%>
		<div>
			<div id="content">
				<div class="content-bd">
					<div class="col-main">

						<div class="order-status status-orange">
							<h2>
								<%=orderMap.get("orderState") %>
							</h2>
							<div class="dashed-border"></div>
							<p>
								订单号：<%=orderMap.get("orderId") %>
								<%
IFrequentContactsDAO contactsDAO = new FrequentContactsDAO();
Map<String,Object> frequentMap =  contactsDAO.getFrequentByFrequentId(Integer.parseInt(orderMap.get("frequentId").toString() ));
 %>
								<span> | </span>租车人：<%=frequentMap.get("frequentName") %>
								<span> | </span>支付方式：在线支付
								<span>|</span>支付状态：<%=orderMap.get("orderState") %>
							</p>
						</div>
						<div class="ui-bfc mod car-info">
							<div class="ui-bfc-hd car-info-img">
								<img
									src="<%=basePath %>uploadFiles/car/<%=carMap.get("carImage") %>"
									alt="标致301">
								<p>
									租期：
									<%
String beginTime = orderMap.get("collectionTime").toString();
String endTime = orderMap.get("returnTime").toString();
double diffDay = CommonUtil.differentDays(beginTime,endTime);

 %>
									<span class="orange"><%=diffDay %></span>天
								</p>
							</div>
							<div class="ui-bfc-bd car-info-detail">
								<h1>
									<%=carMap.get("carBrand") %><%=carMap.get("carType") %>
									<span><%=carMap.get("carGear") %>/<%=carMap.get("carDisplacement") %>/<%=carMap.get("compartment") %>/<%=carMap.get("seat") %>&nbsp;&nbsp;</span>
								</h1>
								<table width="100%">
									<tbody>
										<tr>
											<th colspan="2">
												取车地点
											</th>
											<th colspan="2">
												还车地点
											</th>
										</tr>
										<tr>
											<td width="12%">
												取车门店：
											</td>
											<%
											int fromStoreId =  Integer.parseInt(orderMap.get("fromStoreId").toString());
											int toStoreId =  Integer.parseInt(orderMap.get("toStoreId").toString());
											IBusinessStoreDAO storeDAO = new BusinessStoreDAO();
											BusinessStore fromStore =  storeDAO.getByBusinessStoreId(fromStoreId);
												BusinessStore toStore =  storeDAO.getByBusinessStoreId(toStoreId);
											 %>
											<td class="cell-cnt" width="40%">
												<%=fromStore.getStoreName() %>
											</td>
											<td width="12%">
												还车门店：
											</td>
											<td class="cell-cnt" width="40%">
												<%=toStore.getStoreName() %>
											</td>
										</tr>
										<tr>
											<td>
												取车时间：
											</td>
											<td class="cell-cnt">
												<%=beginTime %>
											</td>
											<td>
												还车时间：
											</td>
											<td class="cell-cnt">
												<%=endTime %>
											</td>
										</tr>

										<tr>
											<td>
												取车地点：
											</td>
											<td class="cell-cnt">
												<%=fromStore.getStoreAddress()%>
											</td>
											<td>
												还车地点：
											</td>
											<td class="cell-cnt">
												<%=toStore.getStoreAddress()%>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /order-info -->
						<div class="mod price-info" style="margin-bottom: 40px;">
							<h2 class="hd">
								费用明细
							</h2>
							<div class="bd">
								<div class="price-info-item">
									<div class="price-info-item-hd">
										<h3>
											车辆租赁费及门店服务费
											<span class="insurance-count">(预付价)</span>
										</h3>
										<p>
											<span class="ui-rmb">¥</span><%=Float.parseFloat(orderMap.get("price").toString())*diffDay %>
										</p>
									</div>
									<div class="dashed-border db-gray-normal"></div>
								</div>

								<%
									if(insuranceList!=null){
									for(int i = 0;i<insuranceList.size();i++){
									String[] insurance = insuranceList.get(i);
								
								 %>


								<div class="price-info-item">
									<div class="price-info-item-hd">
										<h3>
											<%=insurance[1] %>

										</h3>
										<p>
											<span class="ui-rmb">¥</span><%=insurance[2] %>
										</p>
									</div>
									<div class="dashed-border db-gray-normal"></div>
								</div>
								<%}} %>
<%
String songCheShangMenAddress = orderMap.get("songCheShangMenAddress").toString();
if(songCheShangMenAddress.length()!=0){
 %>
								<div class="price-info-item">
									<div class="price-info-item-hd">
										<h3>
											送车上门地址：
											<span class="insurance-count"> <span class="ui-rmb"><%=orderMap.get("songCheShangMenAddress") %></span>
											</span>

										</h3>
										<p>

										</p>
									</div>
									<div class="dashed-border db-gray-normal"></div>
								</div>
<%} %>
<%
String shangMenQuCheAddress = orderMap.get("shangMenQuCheAddress").toString();
if(shangMenQuCheAddress.length()!=0){
 %>
								<div class="price-info-item">
									<div class="price-info-item-hd">
										<h3>
											上门取车地址：
											<span class="insurance-count"> <span class="ui-rmb"><%=orderMap.get("shangMenQuCheAddress") %></span>
											</span>

										</h3>
										<p>

										</p>
									</div>
									<div class="dashed-border db-gray-normal"></div>
								</div>
<%} %>

								<div class="price-info-item">
									<div class="price-info-item-hd">
										<h3>
											备注：
											<span class="insurance-count"> <span class="ui-rmb"><%=orderMap.get("remark") %></span>
											</span>

										</h3>
										<p>
										</p>
									</div>
									<div class="dashed-border db-gray-normal"></div>
								</div>

								<div class="price-info-item">
								</div>
								<div class="price-info-total">
									<span class="txt">总计：</span>
									<sup class="ui-rmb">
										¥
									</sup>
									<%=orderMap.get("totalMoney") %>
								</div>
							</div>
							<div class="prepay-tips">

								<p>
									<%
									if(orderMap.get("orderState").equals("待支付")){
									 %>
									请您在半小时内完成支付，否则订单将自动取消。
									<%}else{ %>
									非常感谢您的预定，祝您一路平安！
									<%} %>

								</p>

							</div>
						</div>
						<%
						if(orderMap.get("orderState").equals("待支付")){
						 %>
						<div id="prepayCountDown">
							距离最后付款时间还有:
							<span>00</span> 小时
							<span>58</span> 分钟
							<span>58</span> 秒
						</div>
						<div class="order-operate">
							<a id="btn-pay" class="ui-btn ui-btn-orange-l btn-pay"
								href="<%=basePath %>servlet/OrderServlet?method=jumpOrderPayPage&orderId=<%=orderMap.get("orderId") %>"><span>立即支付</span>
							</a>

							<a id="btn-cancel" class="ui-btn ui-btn-white-l btn-cancel"
								href="<%=basePath %>servlet/OrderServlet?method=cancelOrder&orderId=<%=orderMap.get("orderId") %>&carId=<%=carMap.get("carId") %>"
								onclick='javascript:return del()'><span>取消订单</span> </a>
						</div>
						<%} %>
					</div>
				</div>
			</div>
		</div>

		<!-- Script -->
		<script src="<%=basePath%>member/script/jquery"></script>
		<script src="<%=basePath%>member/script/jquery.js"></script>
		<script>
		<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		long c= CommonUtil.calMin(nowTime, orderMap.get("orderTime").toString());
		%>
	var mins = 30-<%=c%>;
	function addMinites(mins) {
		var nowMils = new Date().valueOf();
		var untilMils = nowMils + mins * 1000 * 60;
		return new Date(untilMils);
	}

	Date.prototype.Format = function(fmt) { //author: meizz
		var o = {
			"M+" :this.getMonth() + 1, //月份
			"d+" :this.getDate(), //日
			"h+" :this.getHours(), //小时
			"m+" :this.getMinutes(), //分
			"s+" :this.getSeconds(), //秒
			"q+" :Math.floor((this.getMonth() + 3) / 3), //季度
			"S" :this.getMilliseconds()
		//毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	var finalTime = addMinites(mins).Format('yyyy/MM/dd hh:mm:ss');
	$('#prepayCountDown')
			.countdown(
					finalTime,
					function(event) {
						$(this)
								.html(
										event.strftime('距离最后付款时间还有:' +
										//'<span>%D</span> 天 ' +
												'<span>%H</span> 小时 ' + '<span>%M</span> 分钟 ' + '<span>%S</span> 秒'));
					}).on('finish.countdown', function(event) {
				//alert('倒计时完了');
					$(this).hide();
					$('.order-operate').hide();
					//$(this).html('This offer has expired!')
					//  .parent().addClass('disabled');

				});
</script>
	</body>
</html>
