package com.cuc.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cuc.dao.IOrderDAO;
import com.cuc.dao.imp.OrderDAO;

/**
 * 查找订单已超过30分钟还未支付的订单，并修改该订单状态为已取消
 * 
 */
public class CancelOrderJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("do CancelOrderJob ");

		Date now = new Date();// 当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String orderTime = df.format(now.getTime() - 2 * 60 * 1000);// 往前30分钟时间(这次做测试为2分钟)

		IOrderDAO orderDAO = new OrderDAO();

		ArrayList<String[]> orderList = orderDAO.searchTimeOutOrder(orderTime);

		if (orderList != null) {
			for (String[] order : orderList) {

				int orderId = Integer.parseInt((order[0]));// 获取超时订单编号
				orderDAO.updateOrderState(orderId, "已取消");// 将该订单状态设置为 已取消 状态
			}
		}

	}

}
