package com.store.api.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	/**
	 * 验证手机号码的有效性
	 * 
	 */
	public static boolean checkMobile(String mobile) {
		if (isEmpty(mobile))
			return false;
		return Pattern.matches("^1([3|4|5|8][0-9]{9})+$", mobile);
	}

	/**
	 * 验证订单号码的有效性
	 * 
	 */
	public static boolean checkOrderId(String orderId) {
		if (isEmpty(orderId))
			return false;
		return Pattern.matches("^([0-9])+$", orderId);
	}

	/**
	 * double 保留两位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String ceil(Double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

	/**
	 * 按年/月/日拼装文件地址
	 * 
	 * @param basePath
	 * @return
	 */
	public static String buildFilePath(String basePath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		StringBuffer sb = new StringBuffer();
		sb.append(basePath);
		if (basePath.endsWith("/") || basePath.endsWith("\\"))
			sb.append(year);
		else
			sb.append("/").append(year);
		sb.append("/").append(month);
		sb.append("/").append(day).append("/");
		return sb.toString();
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param format 默认格式为‘yyyy-MM-dd HH:mm’
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sm;
		if (isEmpty(format))
			sm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else
			sm = new SimpleDateFormat(format);
		return sm.format(date);
	}

	/**
	 * 字符串转成日期毫秒数
	 * 
	 * @param str
	 * @return
	 */
	public static long parseDateStr(String str, boolean ifEnd) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sm.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (null != date) {
			if (ifEnd) {
				return getNextDayMills(date);
			} else
				return date.getTime();
		} else
			return 0;
	}

	/**
	 * 字符串转成日期毫秒数
	 * 
	 * @param str
	 * @return
	 */
	public static long parseDateStr(String str, String format) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		if (!Utils.isEmpty(format))
			sm = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sm.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (null != date) {
			return date.getTime();
		} else
			return 0;
	}

	/**
	 * 将"null"字符串或者null值转换成""
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStringToEmptyString(String str) {
		if (str == null) {
			str = "";
		}
		if (str.equalsIgnoreCase("null")) {
			str = "";
		}
		return str.trim();
	}

	/**
	 * 是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		str = nullStringToEmptyString(str);
		String regex = "\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean validate = m.matches();
		return validate;
	}

	public static boolean isNumberOrFloat(String str) {
		str = nullStringToEmptyString(str);
		String regex = "\\d+\\.?\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean validate = m.matches();
		return validate;
	}

	/**
	 * 检测字符串是否为空，或者空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		str = nullStringToEmptyString(str);
		String regex = "\\s*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean validate = m.matches();
		return validate;
	}

	public static String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					sb.append(",");
				sb.append(list.get(i));
			}
		}
		return sb.toString();
	}

	/**
	 * * 保留小数
	 * 
	 * @param args
	 *
	 * @param dbStr 数字字符串
	 * @param size 保留位数
	 * @return
	 */

	public static String doubleToSize(String dbStr, int size) {
		BigDecimal bd = new BigDecimal(dbStr);
		bd = bd.setScale(size, BigDecimal.ROUND_HALF_UP);
		return bd + "";
	}

	public static Integer[] stringToIntegerArray(String str) {
		String[] modelStr = str.split(",");
		Integer[] models = new Integer[modelStr.length];
		for (int i = 0; i < modelStr.length; i++) {
			models[i] = Integer.valueOf(modelStr[i]);
		}
		return models;
	}

	/**
	 * 过滤非中文,英文数字外的其它字符
	 * 
	 * @param str
	 * @return
	 */
	public static String isChinessNumEnglish(String str) {
		char[] chs = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chs.length; i++) {
			if (String.valueOf(chs[i]).matches("[0-9]|[a-z]|[A-Z]") || String.valueOf(chs[i]).matches("[\u4e00-\u9fa5]")) {
				sb.append(String.valueOf(chs[i]));
			}
		}
		return sb.toString();
	}

	/**
	 * 获取今天0点0分0秒的毫秒数
	 * 
	 * @return
	 */
	public static long getCurrDayMills() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}

	/**
	 * 获取明天0点0分0秒的毫秒数
	 * 
	 * @return
	 */
	public static long getNextDayMills() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime().getTime();
	}

	public static long getNextDayMills(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime().getTime();
	}

	/**
	 * 字符串转换为int数组
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static int[] string2IntArray(String str, String split) {
		if (str == null)
			return null;
		String[] chs = str.split(split);
		int[] arr = new int[chs.length];
		for (int i = 0; i < chs.length; i++) {
			arr[i] = Integer.parseInt(chs[i]);
		}
		return arr;
	}

	public static void main(String[] args) {
		System.out.println(formatDate(new Date(getNextDayMills()), "yyyy-MM-dd HH:mm:ss"));

	}
}
