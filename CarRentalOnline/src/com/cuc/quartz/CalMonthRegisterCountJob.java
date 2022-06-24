package com.cuc.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cuc.dao.IMemberDAO;
import com.cuc.dao.IMonthRegisterDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.dao.imp.MonthRegisterDAO;
import com.cuc.model.MonthRegister;

/**
 * 每月的1号的00:00:00统计前一月的月注册量 00 00 00 01 * ?
 */
public class CalMonthRegisterCountJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("do CalMonthRegisterCountJob");

		Date d = new Date();// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -1);// 把月期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String calDate = sdf.format(d);

		String year = calDate.split("-")[0];
		String month = calDate.split("-")[1];

		IMemberDAO memberDAO = new MemberDAO();
		int regCount = Integer.parseInt(memberDAO.getMonthRegCount(year, month)[0]
				.toString());

		MonthRegister register = new MonthRegister();
		register.setYear(year);
		register.setMonth(month);
		register.setRegCount(regCount);

		IMonthRegisterDAO registerDAO = new MonthRegisterDAO();
		System.out.println(registerDAO.insert(register));

	}

}
