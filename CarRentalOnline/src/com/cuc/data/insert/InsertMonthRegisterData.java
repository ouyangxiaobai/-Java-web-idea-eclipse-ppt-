package com.cuc.data.insert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cuc.dao.IMemberDAO;
import com.cuc.dao.IMonthRegisterDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.dao.imp.MonthRegisterDAO;
import com.cuc.model.MonthRegister;

/**
 *插入月注册量数据
 */
public class InsertMonthRegisterData {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "2016-08-01";

		while (!dateStr.equals("2017-01-01")) {

			String year = dateStr.split("-")[0];
			String month = dateStr.split("-")[1];

			IMemberDAO memberDAO = new MemberDAO();
			int regCount = Integer.parseInt(memberDAO.getMonthRegCount(year,
					month)[0].toString());

			MonthRegister register = new MonthRegister();
			register.setYear(year);
			register.setMonth(month);
			register.setRegCount(regCount);

			IMonthRegisterDAO registerDAO = new MonthRegisterDAO();
			System.out.println("result:" + registerDAO.insert(register));

			Date d = sdf.parse(dateStr);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			calendar.add(Calendar.MONTH, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

			dateStr = sdf.format(d);
			System.out.println("日期：" + dateStr);
			
		}

	}
}
