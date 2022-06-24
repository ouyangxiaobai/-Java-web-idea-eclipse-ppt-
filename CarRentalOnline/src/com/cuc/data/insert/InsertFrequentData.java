package com.cuc.data.insert;

import java.util.Date;
import java.util.Random;

import com.cuc.dao.IFrequentContactsDAO;
import com.cuc.dao.imp.FrequentContactsDAO;
import com.cuc.util.SQLUtil;

/**
 * 添加常用租车人信息 每个会员用户添加两条常用租车人信息
 * 
 * @author Administrator
 * 
 */
public class InsertFrequentData {

	public static void main(String[] args) {

		Date d1 = new Date();

		String[] idTypes = { "身份证", "军官证", "身份证", "护照", "身份证", "台胞证", "身份证",
				"港澳居民来往内地通行证", "身份证", "身份证", "身份证" };

		String[] provinces = { "福建省", "安徽省", "江西省", "吉林省", "湖南省", "湖北省", "浙江省",
				"江苏省", "云南省" };
		String[][] citys = { { "福州市", "厦门市", "莆田市" }, { "合肥市", "黄山市", "宿州市" },
				{ "南昌市", "九江市", "抚州市" }, { "长春市", "四平市", "吉林市" },
				{ "长沙市", "株洲市", "湘潭市" }, { "武汉市", "黄石市", "十堰市" },
				{ "杭州市", "宁波市", "温州市" }, { "南京市", "无锡市", "徐州市" },
				{ "昆明市", "曲靖市", "玉溪市" } };

		String sql = "insert into t_frequentcontacts(memberId,frequentName,frequentPhone,idType,Identity,frequentProvince,frequentCity,frequentAddresss) values(?,?,?,?,?,?,?,?)";

		//10021
		for (int i = 3; i < 10021; i++) {
			for (int j = 0; j < 2; j++) {

				Random rd = new Random();
				int idType_num = rd.nextInt(idTypes.length);

				String idType = idTypes[idType_num];// 证件类型

				String identity = InsertMemberData.getIdentity();// 证件号

				String frequentName = InsertMemberData.getChineseName();// 名字

				String tel = InsertMemberData.getTel();// 电话

				int provinces_num = rd.nextInt(provinces.length);
				String province = provinces[provinces_num];// 省份

				String city = citys[provinces_num][rd.nextInt(3)];// 城市

				String address = InsertMemberData.getRoad();// 具体地址

				Object[] paramArray = new Object[8];
				paramArray[0] = i;//memberId
				paramArray[1] = frequentName;
				paramArray[2] = tel;
				paramArray[3] = idType;
				paramArray[4] = identity;
				paramArray[5] = province;
				paramArray[6] = city;
				paramArray[7] = address;

				System.out.println("result:"
						+ SQLUtil.getInstance().update(sql, paramArray));
			}

		}

		Date d2 = new Date();

		System.out.println("用时：" + (d2.getTime() - d1.getTime()));

	}

}
