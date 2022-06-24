package com.cuc.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

public class CommonUtil {
	// 更改状态
	public static int changeState(int state) {
		return state == 1 ? 0 : 1;
	}

	// 显示提示信息
	public static void showAlertMessage(HttpServletResponse response, String msg)
			throws IOException {
		response.getWriter().println(
				"<SCRIPT LANGUAGE='JavaScript'>alert('" + msg
						+ "');javascript:history.back(-1);</SCRIPT>");

	}

	// 显示提示信息
	public static void showAlertMessageNoBack(HttpServletResponse response,
			String msg) throws IOException {
		response.getWriter().println(
				"<SCRIPT LANGUAGE='JavaScript'>alert('" + msg + "');</SCRIPT>");

	}

	// 生成num位数的随机数字符串
	public static String randomCode(int num) {

		String code = "";

		Random random = new Random();

		for (int i = 0; i < num; i++) {
			code += random.nextInt(10);
		}

		return code;

	}

	/**
	 * 判断数组values中是否有存在s字符串，如果有，转变为""
	 * 
	 * @param values
	 * @param s
	 * @return
	 */
	public static String[] changeValues(String[] values, String s) {

		if (ArrayHelper.inArray(values, s)) {
			for (int i = 0; i < values.length; i++) {
				values[i] = "";
			}
		}

		return values;
	}

	/**
	 * 给定开始时间和结束时间，计算中间天数
	 * 
	 * @param beginDate 
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static double differentDays(String from, String to)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date beginDate = sdf.parse(from);
		Date endDate = sdf.parse(to);

		double days = ((endDate.getTime() - beginDate.getTime()) * 1.0 / (1000 * 3600 * 24));

		return Math.ceil(days);// 向上取整--时间不满一天也算一天
	}

	public static long calMin(String from, String to) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d = sdf.parse(from);
		Date d2 = sdf.parse(to);
		
		long c = Math.abs(d.getTime() - d2.getTime()) / (1000 * 60);

		return c;
	}
}
