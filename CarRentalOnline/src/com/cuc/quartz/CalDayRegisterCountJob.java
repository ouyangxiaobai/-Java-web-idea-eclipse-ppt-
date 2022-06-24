package com.cuc.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cuc.dao.IDayRegisterDAO;
import com.cuc.dao.IMemberDAO;
import com.cuc.dao.imp.DayRegisterDAO;
import com.cuc.dao.imp.MemberDAO;
import com.cuc.model.DayRegister;

/**
 * 每天的00:00:00统计前一天的日注册量
 * 
 * @author Administrator
 * 
 */
public class CalDayRegisterCountJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("do CalDayRegisterCountJob");

		Date d = new Date();// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String calDate = sdf.format(d);

		int year = Integer.parseInt(calDate.split("-")[0]);
		int month = Integer.parseInt(calDate.split("-")[1]);
		int day = Integer.parseInt(calDate.split("-")[2]);

		IMemberDAO memberDAO = new MemberDAO();
		int regCount = Integer.parseInt(memberDAO.getDayRegCount(calDate)[0]
				.toString());

		DayRegister register = new DayRegister();
		register.setYear(year);
		register.setMonth(month);
		register.setDay(day);
		register.setRegCount(regCount);

		IDayRegisterDAO registerDAO = new DayRegisterDAO();
		System.out.println(registerDAO.insert(register));

	}

}
