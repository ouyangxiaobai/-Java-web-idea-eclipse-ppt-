<%@page import="com.cuc.model.Member"%>
<%@page import="com.cuc.dao.imp.FrequentContactsDAO"%>
<%@page import="com.cuc.dao.IFrequentContactsDAO"%>
<%@page import="com.cuc.model.FrequentContacts"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=basePath%>member/css/bootstrap.min.css"
			rel="stylesheet">
		<link href="<%=basePath%>member/css/Global.css" rel="stylesheet">
		<script type="text/javascript"
			src="<%=basePath%>member/script/zDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>member/script/zDrag.js"></script>
		<script type="text/javascript">
	var china = new Object();
	china['北京市'] = new Array('北京市区', '北京市辖区');
	china['上海市'] = new Array('上海市区', '上海市辖区');
	china['天津市'] = new Array('天津市区', '天津市辖区');
	china['重庆市'] = new Array('重庆市区', '重庆市辖区');
	china['河北省'] = new Array('石家庄', '唐山市', '邯郸市', '秦皇市岛', '保市定', '张家市口', '承德市',
			'廊坊市', '沧州市', '衡水市', '邢台市');
	china['山西省'] = new Array('太原市', '大同市', '阳泉市', '长治市', '晋城市', '朔州市', '晋中市',
			'运城市', '忻州市', '临汾市', '吕梁市');
	china['辽宁省'] = new Array('沈阳市', '大连市', '鞍山市', '抚顺市', '本溪市', '丹东市', '锦州市',
			'营口市', '阜新市', '辽阳市', '盘锦市', '铁岭市', '朝阳市', '葫芦岛市');
	china['吉林省'] = new Array('长春市', '吉林市', '四平市', '辽源市', '通化市', '白山市', '松原市',
			'白城市', '延边州', '长白山管委会');
	china['黑龙江省'] = new Array('哈尔滨市', '齐齐哈尔市', '大庆市', '佳木斯市', '牡丹江市', '七台河市',
			'双鸭山市', '黑河市', '鸡西市', '伊春市', '绥化市', '鹤岗市', '加格达奇市');
	china['江苏省'] = new Array('南京市', '苏州市', '无锡市', '常州市', '南通市', '扬州市', '镇江市',
			'泰州市', '盐城市', '连云港市', '宿迁市', '淮安市', '徐州市');
	china['浙江省'] = new Array('杭州市', '宁波市', '温州市', '嘉兴市', '湖州市', '绍兴市', '金华市',
			'衢州市', '舟山市', '台州市', '丽水市');
	china['安徽省'] = new Array('合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市', '淮北市', '铜陵市',
			'安庆市', '黄山市', '滁州市', '阜阳市', '宿州市', '巢湖市', '六安市', '亳州市', '池州市',
			'宣城市');
	china['福建省'] = new Array('福州市', '厦门市', '莆田市', '三明市', '泉州市', '漳州市', '南平市',
			'龙岩市', '宁德市');
	china['江西省'] = new Array('南昌市', '景德镇市', '萍乡市', '九江市', '新余市', '鹰潭市', '赣州市',
			'吉安市', '宜春市', '抚州市', '上饶市');
	china['山东省'] = new Array('济南市', '青岛市', '淄博市', '枣庄市', '东营市', '烟台市', '潍坊市',
			'济宁市', '泰安市', '威海市', '日照市', '莱芜市', '临沂市', '德州市', '聊城市', '滨州市',
			'菏泽市');
	china['河南省'] = new Array('郑州市', '开封市', '洛阳市', '平顶山市', '安阳市', '鹤壁市', '新乡市',
			'焦作市', '濮阳市', '许昌市', '漯河市', '三门峡市', '南阳市', '商丘市', '信阳市', '周口市',
			'驻马店市');
	china['湖北省'] = new Array('武汉市', '黄石市', '十堰市', '荆州市', '宜昌市', '襄樊市', '鄂州市',
			'荆门市', '孝感市', '黄冈市', '咸宁市', '随州市');
	china['湖南省'] = new Array('长沙市', '株洲市', '湘潭市', '衡阳市', '邵阳市', '岳阳市', '常德市',
			'张家界市', '益阳市', '郴州市', '永州市', '怀化市', '娄底市');
	china['广东省'] = new Array('广州市', '深圳市', '珠海市', '汕头市', '韶关市', '佛山市', '江门市',
			'湛江市', '茂名市', '肇庆市', '惠州市', '梅州市', '汕尾市', '河源市', '阳江市', '清远市',
			'东莞市', '中山市', '潮州市', '揭阳市', '云浮市');
	china['海南省'] = new Array('文昌市', '琼海市', '万宁市', '五指山市', '东方市', '儋州市');
	china['四川省 '] = new Array('成都市', '自贡市', '攀枝花市', '泸州市', '德阳市', '绵阳市', '广元市',
			'遂宁市', '内江市', '乐山市', '南充市', '眉山市', '宜宾市', '广安市', '达州市', '雅安市',
			'巴中市', '资阳市');
	china['贵州省'] = new Array('贵阳市', '六盘水市', '遵义市', '安顺市');
	china['云南省'] = new Array('昆明市', '曲靖市', '玉溪市', '保山市', '昭通市', '丽江市', '普洱市',
			'临沧市');
	china['陕西省'] = new Array('西安市', '铜川市', '宝鸡市', '咸阳市', '渭南市', '延安市', '汉中市',
			'榆林市', '安康市', '商洛市');
	china['甘肃省'] = new Array('兰州市', '金昌市', '白银市', '天水市', '嘉峪关市', '武威市', '张掖市',
			'平凉市', '酒泉市', '庆阳市', '定西市', '陇南市');
	china['青海省'] = new Array('西宁市');
	china['台湾省'] = new Array('台北市', '高雄市', '基隆市', '台中市', '台南市', '新竹市', '嘉义市');
	china['广西壮族自治区'] = new Array('南宁市', '柳州市', '桂林市', '梧州市', '北海市', '防城港市',
			'钦州市', '贵港市', '玉林市', '百色市', '贺州市', '河池市', '来宾市', '崇左市');
	china['内蒙古自治区'] = new Array('呼和浩特市', '包头市', '乌海市', '赤峰市', '通辽市', '鄂尔多斯市',
			'呼伦贝尔市', '巴彦淖尔市', '乌兰察布市');
	china['西藏自治区'] = new Array('拉萨市');
	china['宁夏回族自治区'] = new Array('银川市', '石嘴山市', '吴忠市', '固原市', '中卫市');
	china['新疆维吾尔自治区'] = new Array('乌鲁木齐市', '克拉玛依市');
	china['香港特别行政区'] = new Array('台北市', '高雄市', '基隆市', '台中市', '台南市', '新竹市',
			'嘉义市');
	function chinaChange(province, city) {
		var pv, cv;
		var i, ii;
		pv = province.value;
		cv = city.value;
		city.length = 1;
		if (pv == '0')
			return;
		if (typeof (china[pv]) == 'undefined')
			return;

		for (i = 0; i < china[pv].length; i++) {
			ii = i + 1;

			city.options[ii] = new Option();
			city.options[ii].text = china[pv][i];
			city.options[ii].value = china[pv][i];
		}
		city.options[0].text = "请选择市区";

	};
	function del() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
		<title>常用租车人</title>
	</head>
	<body>
		<div id="content">
			<div class="content-bd">
				<div class="col-main">
					<div class="renter">
						<h2>
							添加联系人
						</h2>
						<div class="mod renter-list">
							<ul class="driver-list">
								<li class="row-title">
									<span class="row-first-cell cell-name">姓名</span>
									<span class="cell-phone">联系方式</span>
									<span class="cell-idtype">证件类型</span>
									<span class="cell-idnumber">证件号码</span>
									<span class="cell-passport">联系地址</span>
									<span class="cell-operate">操作</span>
								</li>
								<%
									Member member = (Member) session.getAttribute("member");
									//ArrayList<FrequentContacts> fclist = new ArrayList<FrequentContacts>();
									//IFrequentContactsDAO freContactsDAO = new FrequentContactsDAO();
									//fclist = freContactsDAO.getByMemberId(member.getMemberId());
									ArrayList<FrequentContacts> fclist = (ArrayList<FrequentContacts>) request
											.getAttribute("fclist");
									if (fclist != null && fclist.size() != 0) {
										for (int i = 0; i < fclist.size(); i++) {
											FrequentContacts fc = fclist.get(i);
											if (i % 2 == 0) {
								%>
								<li class="" data-driver-id="<%=fc.getFrequentId()%>"
									data-name="<%=fc.getFrequentName()%>"
									data-cellphone="<%=fc.getFrequentPhone()%>"
									data-idcardtype="<%=fc.getIdType()%>"
									data-idcard-number="<%=fc.getIdentity()%>" data-year="2015"
									data-month="1">
									<span class="row-first-cell cell-name"><%=fc.getFrequentName()%></span>
									<span class="cell-phone"><%=fc.getFrequentPhone()%></span>
									<span class="cell-idtype"><%=fc.getIdType()%></span>
									<span class="cell-idnumber"><%=fc.getIdentity()%></span>
									<span class="cell-passport"><%=fc.getFrequentProvince()%>
										<%=fc.getFrequentCity()%> <%=fc.getFrequentAddresss()%></span>
									<span class="cell-operate"> <a class="delete1"
										href="<%=basePath%>servlet/MemberServlet?method=delFrequent&id=<%=fc.getFrequentId()%>" onclick='javascript:return del()'>删除</a>

									</span>
									</span>
								</li>
								<%
									} else {
								%>
								<li class="ck" data-driver-id="<%=fc.getFrequentId()%>"
									data-name="<%=fc.getFrequentName()%>"
									data-cellphone="<%=fc.getFrequentPhone()%>"
									data-idcardtype="<%=fc.getIdType()%>"
									data-idcard-number="<%=fc.getIdentity()%>" data-year="2015"
									data-month="1">
									<span class="row-first-cell cell-name"><%=fc.getFrequentName()%></span>
									<span class="cell-phone"><%=fc.getFrequentPhone()%></span>
									<span class="cell-idtype"><%=fc.getIdType()%></span>
									<span class="cell-idnumber"><%=fc.getIdentity()%></span>
									<span class="cell-passport"><%=fc.getFrequentProvince()%>
										<%=fc.getFrequentCity()%> <%=fc.getFrequentAddresss()%></span>
									<span class="cell-operate"> <a class="delete1"
										href="<%=basePath%>servlet/MemberServlet?method=delFrequent&id=<%=fc.getFrequentId()%>">删除</a>

									</span>
									</span>
								</li>
								<%
									}
								%>
								<%
									}
									}
								%>
							</ul>
							<p class="ui-checkbox">
								<label>
									<input type="checkbox" id="checkboxAgree" checked="checked">
									同意
								</label>
								<a href="javascript:void(0)" class="btn" data-toggle="modal"
									data-target="#agreementModal">《添加联系人须知》</a>
							</p>
							<div class="operate-panel">
								<a id="btnAddDriver" 
									href="javascript:void(0)"><img
										src="<%=basePath%>member/images/changyongzucheren.png">
								</a>
							</div>
						</div>
						<div class="mod renter-panel renter-add edit-panel"
							style="display: none;"
							data-bind="template:{name: &#39;editor-template&#39;}"></div>
					</div>
				</div>
			</div>
		</div>
		</div>

		<!-- Script -->
		<script src="<%=basePath%>member/script/jquery"></script>
		<script src="<%=basePath%>member/script/bootstrap"></script>

		<!-- 模态框（Modal） -->
		<div class="modal fade" id="agreementModal" tabindex="-1"
			role="dialog" aria-hidden="false">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								×
							</button>
							<h4 class="modal-title">
								联系人须知
							</h4>
						</div>
						<div class="modal-body">
							<p>
								尊敬的客户：
							</p>
							<p style="text-indent: 2em;">
								如您需要添加为联系人（以下简称第三人），您必须保证第三人的行为能力有效、全部身份信息真实有效；您将为第三人在贵账户内的操作行为（如注销、更改基本信息、下订单等）承担无限连带责任；您将为第三人与我司履行签署的服务协议及全部租车文件约定之权利义务承担无限连带责任，包括但不限于归还车辆、支付租赁费用、缴纳违章罚款、承担交通事故责任等。如您仍需添加联系人，即视为认可如上全部约定。
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary btn-agree">
								已阅读并同意条款
							</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
		<!-- 模态框（Modal） -->

		<script src="<%=basePath%>member/script/knockout-3.3.0.js"></script>
		<script type="text/html" id="editor-template"> 
        <div class="arrow-top"></div>
        <div class="btn-close"></div>
        <form action="<%=basePath%>servlet/MemberServlet?method=addFrequent&id=<%=member.getMemberId()%>" method="post">
            <input type="hidden" name="Id" data-bind="value:Id" />
            <table width="100%">
                <tr>
                    <td class="cell-title" width="40%">姓名：</td>
                    <td width="60%">
                        <input type="text" name="Name" data-bind="value:Name" />
                    </td>
                </tr>
                <tr>
                    <td class="cell-title">联系方式：</td>
                    <td>
                        <input type="text" name="CellPhone" data-bind="value:CellPhone" maxlength="11" />
                    </td>
                </tr>
                <tr>
                    <td class="cell-title">证件类型：</td>
                    <td>
                        <select name="IdCardType" data-bind="value:CardType"  style="height:30px;">
                            <option value="身份证">身份证</option>
                            <option value="港澳居民来往内地通行证">港澳居民来往内地通行证</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="cell-title">证件号码：</td>
                    <td>
                        <input type="text" name="IdCardNo" data-bind="value:CardNo" maxlength="18" />
                    </td>
                </tr>
				
				
				 <tr>
                    <td class="cell-title">城市：</td>
                    <td>
                        <select name="privince"  onchange="chinaChange(this,document.getElementById('city'));" style="width:20%;height:30px;">
								<option value ="请选择省份">请选择省份</option>
								<option value ="北京市">
								北京市 </option><option value ="天津市">
								天津市 </option><option value ="上海市">
上海市 </option><option value ="重庆市">
重庆市 </option><option value ="河北省">
河北省 </option><option value ="山西省">
山西省 </option><option value ="辽宁省">
辽宁省 </option><option value ="吉林省">
吉林省 </option><option value ="黑龙江省">
黑龙江省</option><option value ="江苏省"> 
江苏省 </option><option value ="浙江省">
浙江省 </option><option value ="安徽省">
安徽省 </option><option value ="福建省">
福建省 </option><option value ="江西省">
江西省 </option><option value ="山东省">
山东省 </option><option value ="河南省">
河南省 </option><option value ="湖北省">
湖北省 </option><option value ="湖南省">
湖南省 </option><option value ="广东省">
广东省 </option><option value ="海南省">
海南省 </option><option value ="四川省">
四川省 </option><option value ="贵州省">
贵州省 </option><option value ="云南省">
云南省 </option><option value ="陕西省">
陕西省 </option><option value ="甘肃省">
甘肃省 </option><option value ="青海省">
青海省 </option><option value ="台湾省">
台湾省 </option><option value ="广西壮族自治区">
广西壮族自治区</option><option value ="内蒙古自治区"> 
内蒙古自治区</option><option value ="西藏自治区"> 
西藏自治区</option><option value ="宁夏回族自治区"> 
宁夏回族自治区 </option><option value ="新疆维吾尔自治区">
新疆维吾尔自治区</option><option value ="香港特别行政区">
香港特别行政区</option><option value ="澳门特别行政区">
澳门特别行政区</option>

							</select>
							
							<select name="city" id="city" style="width:20%;height:30px;" >
								<option value="请选择市区">
									请选择市区
								</option>
							</select>
							
                    </td>
                </tr>
				
				 <tr>
                    <td class="cell-title">具体地址：</td>
                    <td>
                        <input type="text" style="width=250px;" name="address" data-bind="value:CardNo"  />
                    </td>
                </tr>

            </table>
 <div class="operate-panel">
            <!--<a class="ui-btn ui-btn-orange-l btn-save" href="javascript:void(0)" data-bind="css:{'add-new-driver':IsNew}"><span>保 存</span></a>-->
<input type="submit" name="submit" value="" style="margin-left:350px; height: 50px;width:145px;background-image: url('<%=basePath%>member/images/save.png');">
        </div>
        </form>
       
    </script>
		<script src="<%=basePath%>member/script/mydrivers.js"></script>


	</body>
</html>