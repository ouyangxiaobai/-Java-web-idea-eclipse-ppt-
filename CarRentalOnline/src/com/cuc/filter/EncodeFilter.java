package com.cuc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cuc.util.QuartzController;

public class EncodeFilter implements Filter {

	public void destroy() {

		System.out.println("doFilter 销毁了");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("启动了Filter!!!");

		// 查找下订单已超过半个小时还未支付的订单，修改该订单状态为已取消
//		 String CancelOrderJob = "com.cuc.quartz.CancelOrderJob";
//		 QuartzController.addJob("CancelOrderJob", CancelOrderJob,
//		 "0 */1 * * * ?");// 每分钟做一次

		// 查找订单已支付已超过取车时间的订单，修改该订单状态为已取消
		// String CancelOrderJob2 = "com.cuc.quartz.CancelOrderJob2";
		// QuartzController.addJob("CancelOrderJob2", CancelOrderJob2,
		// "0 */1 * * * ?");// 每分钟做一次

		// 在每天的00:00:00做日注册量统计
//		String calDayRegisterCountJob = "com.cuc.quartz.CalDayRegisterCountJob";
//		QuartzController.addJob("CalDayRegisterCountJob",calDayRegisterCountJob, "00 00 0 * * ?");
		
		//在每月的1号的00:00:00统计上月的月注册量
//		String calMonthRegisterCountJob = "com.cuc.quartz.CalMonthRegisterCountJob";
//		QuartzController.addJob("CalMonthRegisterCountJob",calMonthRegisterCountJob, "00 00 0 1 * ?");
		
		//在每天的00：30做前一天的车辆租赁次数统计
//		String calDayCarRentalJob = "com.cuc.quartz.CalDayCarRentalJob";   
//		QuartzController.addJob("CalDayCarRentalJob", calDayCarRentalJob, "00 30 0 * * ?");
		
		//在每个月的1号的01：00做前一月的车辆租赁次数统计
//		String calMonthCarRentalJob = "com.cuc.quartz.CalMonthCarRentalJob";
//		QuartzController.addJob("CalMonthCarRentalJob", calMonthCarRentalJob, "00 00 1 1 * ?");

		//在每天的00:30做前一天的所有营业门店收益统计
//		String calDayStoreProfitJob = "com.cuc.quartz.CalDayStoreProfitJob";
//		QuartzController.addJob("CalDayStoreProfitJob", calDayStoreProfitJob, "00 30 0 * * ?");
		
		//在每个月的1号的01：00做前一月的每家营业门店的营业额每月的统计
//		String calMonthStoreProfitJob = "com.cuc.quartz.CalMonthStoreProfitJob";
//		QuartzController.addJob("CalMonthStoreProfitJob", calMonthStoreProfitJob, "00 00 1 1 * ?");
	}

}
