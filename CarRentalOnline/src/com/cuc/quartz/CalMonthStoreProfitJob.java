package com.cuc.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cuc.dao.IDayStoreProfitDAO;
import com.cuc.dao.IMonthStoreProfitDAO;
import com.cuc.dao.imp.DayStoreProfitDAO;
import com.cuc.dao.imp.MonthStoreProfitDAO;
import com.cuc.model.MonthStoreProfit;

/**
 * 在每个月的1号的01：00做前一月的每家营业门店的每月收益统计
 * 
 */
public class CalMonthStoreProfitJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		IDayStoreProfitDAO storeProfitDAO = new DayStoreProfitDAO();

		Date d = new Date();// 获取当前日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String calDate = sdf.format(d);

		int year = Integer.parseInt(calDate.split("-")[0]);
		int month = Integer.parseInt(calDate.split("-")[1]);

		ArrayList<String[]> calStoreProfitList = storeProfitDAO
				.getMonthStoreProfitByDate("" + year + "" + month);

		ArrayList<MonthStoreProfit> monthStoreProfitList = new ArrayList<MonthStoreProfit>();

		MonthStoreProfit storeProfit = null;

		System.out.println("size" + calStoreProfitList.size());

		for (String[] calStoreProfit : calStoreProfitList) {
			storeProfit = new MonthStoreProfit();
			storeProfit.setStoreId(Integer.parseInt(calStoreProfit[0]));
			storeProfit.setSumMoney(Float.parseFloat(calStoreProfit[1]));
			storeProfit.setYear(year);
			storeProfit.setMonth(month);

			monthStoreProfitList.add(storeProfit);

		}

		IMonthStoreProfitDAO monthStoreProfitDAO = new MonthStoreProfitDAO();

		monthStoreProfitDAO.insert(monthStoreProfitList);

	}

}
