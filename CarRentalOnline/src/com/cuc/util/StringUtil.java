package com.cuc.util;

public class StringUtil {

	/**
	 * 判断输入的字符串参数是否为空。
	 * 
	 * @param args
	 *            输入的字串
	 * @return true/false
	 */
	public static boolean validateNull(String args) {
		if (args == null || args.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断输入的字符串参数是否为空或者是"null"字符,如果是,就返回target参数,如果不是,就返回source参数。
	 */
	public static String chanageNull(String source, String target) {
		if (source == null || source.length() == 0
				|| source.equalsIgnoreCase("null")) {
			return target;
		} else {
			return source;
		}
	}

	/**
	 * 过滤<, >,\n 字符的方法。
	 * 
	 * @param input
	 *            需要过滤的字符
	 * @return 完成过滤以后的字符串
	 */
	public static String filterHtml(String input) {
		if (input == null) {
			return null;
		}
		if (input.length() == 0) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;");
		input = input.replaceAll("\"", "&quot;");
		return input.replaceAll("\n", "<br>");
	}
}
