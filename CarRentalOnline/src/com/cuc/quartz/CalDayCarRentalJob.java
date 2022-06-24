package com.cuc.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cuc.dao.ICarDAO;
import com.cuc.dao.IDayCarRentalDAO;
import com.cuc.dao.imp.CarDAO;
import com.cuc.dao.imp.DayCarRentalDAO;
import com.cuc.model.DayCarRental;

/**
 * 在每天的00：30做前一天的车辆租赁次数统计
 * 
 */
public class CalDayCarRentalJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("do CalDayCarRentalJob");

		ICarDAO carDAO = new CarDAO();

		Date d = new Date();// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String calDate = sdf.format(d);// 前一天的日期

		int year = Integer.parseInt(calDate.split("-")[0]);
		int month = Integer.parseInt(calDate.split("-")[1]);
		int day = Integer.parseInt(calDate.split("-")[2]);

		ArrayList<String[]> calRentalNumList = carDAO
				.getDayCarRentalNum(calDate);

		ArrayList<DayCarRental> dayCarRentalList = new ArrayList<DayCarRental>();

		DayCarRental carRental = null;

		for (String[] calRentalNum : calRentalNumList) {

			carRental = new DayCarRental();
			carRental.setCarId(Integer.parseInt(calRentalNum[0]));
			carRental.setRentalNum(Integer.parseInt(calRentalNum[1]));
			carRental.setYear(year);
			carRental.setMonth(month);
			carRental.setDay(day);

			dayCarRentalList.add(carRental);

		}

		IDayCarRentalDAO dayCarRentalDAO = new DayCarRentalDAO();

		dayCarRentalDAO.insert(dayCarRentalList);
	}
}
