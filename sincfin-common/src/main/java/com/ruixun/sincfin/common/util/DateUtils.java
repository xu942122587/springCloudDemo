package com.ruixun.sincfin.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	// 默认日期格式
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	// 默认时间格式
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 默认的中国时间格式
	public static final String DEFAULT_CN_DATETIME_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	// 默认精确到毫秒的时间格式
	public final static String DEFAULT_MS_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String DEFAULT_NUMBER_DATETIME_FORMAT = "yyyyMMddHHmmss";
	// 模式时间格式
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	
	
	// 默认日期格式
	public static final DateFormat O_DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	// 默认时间格式
	public static final DateFormat O_DEFAULT_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
	// 默认的中国时间格式
	public static final DateFormat O_DEFAULT_CN_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_CN_DATETIME_FORMAT);
	// 默认精确到毫秒的时间格式
	public final static DateFormat O_DEFAULT_MS_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_MS_DATETIME_FORMAT);
	// 默认精确到毫秒的时间格式
	public final static DateFormat O_DEFAULT_NUMBER_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_NUMBER_DATETIME_FORMAT);
	// 模式时间格式
	public static final DateFormat O_DEFAULT_TIME_FORMAT = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
	
	
	/**
	 * 当前时间long
	 * 
	 * @param isNano 是否为高精度时间
	 * @return 时间
	 */
	public static long current(boolean isNano) {
		return isNano ? System.nanoTime() : System.currentTimeMillis();
	}
	
	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static String today() {
		return formatDate();
	}
	
	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String now() {
		return formatDateTime();
	}
	
	/**
	 * 获取当前日期对象
	 * @return
	 */
	public static Date getDate() {
		return new Date();
	}
	
	/**
	 * 转换为Calendar对象
	 * 
	 * @param date 日期对象
	 * @return Calendar对象
	 */
	public static Calendar calendar(Date date) {
		return calendar(date.getTime());
	}
	
	/**
	 * 转换为Calendar对象
	 * 
	 * @param millis 时间戳
	 * @return Calendar对象
	 */
	public static Calendar calendar(long millis) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}
	
	/**
	 * 获取当前Calendar
	 * 
	 * @param date 日期对象
	 * @return Calendar对象
	 */
	public static Calendar calendar() {
		return Calendar.getInstance();
	}
	
	/**
     * 获取当前年
     * 
     * @return
     */
    public static int getNowYear() {
    	final Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }
    
    /**
     * 获取当前月
     * 
     * @return
     */
    public static int getNowMonth() {
    	final Calendar d = Calendar.getInstance();
    	return d.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 获取当前日
     * 
     * @return
     */
    public static int getNowMonthDay() {
    	final Calendar d = Calendar.getInstance();
    	return d.getActualMaximum(Calendar.DATE);
    }
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(O_DEFAULT_DATE_FORMAT,date);
	}
	
	public static String formatDate() {
		return formatDate(getDate());
	}
	
	public static String formatDateTime(Date date) {
		return format(O_DEFAULT_DATETIME_FORMAT,date);
	}
	
	public static String formatDateTime() {
		return formatDateTime(getDate());
	}
	
	
	public static String formatCNDateTime(Date date) {
		return format(O_DEFAULT_CN_DATETIME_FORMAT,date);
	}
	
	public static String formatCNDateTime() {
		return formatCNDateTime(getDate());
	}
	
	public static String formatMSDateTime(Date date) {
		return format(O_DEFAULT_MS_DATETIME_FORMAT,date);
	}
	
	public static String formatMSDateTime() {
		return formatMSDateTime(getDate());
	}
	
	public static String formatTime(Date date) {
		return format(O_DEFAULT_TIME_FORMAT,date);
	}
	
	public static String formatTime() {
		return formatTime(getDate());
	}
	/**
	 * 获取指定格式的时间字符串
	 *
	 * @param format
	 * @param date
	 * @return
	 */
	public static String format(String format, Date date) {
		return format(new SimpleDateFormat(format),date);
	}
	/**
	 * 获取指定格式的时间字符串
	 *
	 * @param format
	 * @param date
	 * @return
	 */
	public static String format(DateFormat format, Date date) {
		return format.format(date);
	}
	
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static Date parseDate(String strDate) {
		return parse(O_DEFAULT_DATE_FORMAT,strDate);
	}
	
	public static Date parseDateTime(String strDate) {
		return parse(O_DEFAULT_DATETIME_FORMAT,strDate);
	}
	
	public static Date parseCNDateTime(String strDate) {
		return parse(O_DEFAULT_CN_DATETIME_FORMAT,strDate);
	}
	
	public static Date parseMSDateTime(String strDate) {
		return parse(O_DEFAULT_MS_DATETIME_FORMAT,strDate);
	}
	
	public static Date parseTime(String strDate) {
		return parse(O_DEFAULT_TIME_FORMAT,strDate);
	}
	
	/**
	 * 将字符串转换为日期
	 *
	 * @param strDate
	 * @param strFormat
	 * @return
	 */
	public static Date parse(String format , String strDate) {
		return parse(new SimpleDateFormat(format),strDate);
	}
	
	/**
	 * 将字符串转换为日期
	 *
	 * @param strDate
	 * @param strFormat
	 * @return
	 */
	public static Date parse(DateFormat format , String strDate) {
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 计算相对于dateToCompare的年龄，长用于计算指定生日在某年的年龄
	 * 
	 * @param birthDay 生日
	 * @param dateToCompare 需要对比的日期
	 * @return 年龄
	 */
	public static int age(Date birthDay, Date dateToCompare) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToCompare);

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(StringUtils.format("Birthday is after date {}!", formatDate(dateToCompare)));
		}

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int age = year - cal.get(Calendar.YEAR);

		int monthBirth = cal.get(Calendar.MONTH);
		if (month == monthBirth) {
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			if (dayOfMonth < dayOfMonthBirth) {
				// 如果生日在当月，但是未达到生日当天的日期，年龄减一
				age--;
			}
		} else if (month < monthBirth) {
			// 如果当前月份未达到生日的月份，年龄计算减一
			age--;
		}

		return age;
	}
	
	/**
     * 判断是否在一个时间段内
     * 
     * @param time 要判断的时间
     * @param begin 开始时间
     * @param end 结束时间
     * @return boolean true：在开始和结束范围内，false:不在开始和结束范围内
     */
    public static boolean isIn(Date date, Date beginDate, Date endDate) {
        return date.getTime() >= beginDate.getTime() && date.getTime() <= endDate.getTime();
    }
    
    /**
    * 计算 day 天后的时间
    * 
    * @param date
    * @param day
    * @return
    */
    public static Date addDay(Date date, int day) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, day);
	    return calendar.getTime();
    }
    
    public static Date addYear(Date date, int year) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
    	return calendar.getTime();
    }
    
	
	
	/**
	 * 秒数转为时间格式(HH:mm:ss)<br>
	 * 参考：https://github.com/iceroot
	 * 
	 * @param seconds
	 *            需要转换的秒数
	 * @return 转换后的字符串
	 * @since 3.1.2
	 */
	public static String secondToTime(int seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException("Seconds must be a positive number!");
		}

		int hour = seconds / 3600;
		int other = seconds % 3600;
		int minute = other / 60;
		int second = other % 60;
		final StringBuilder sb = new StringBuilder();
		if (hour < 10) {
			sb.append("0");
		}
		sb.append(hour);
		sb.append(":");
		if (minute < 10) {
			sb.append("0");
		}
		sb.append(minute);
		sb.append(":");
		if (second < 10) {
			sb.append("0");
		}
		sb.append(second);
		return sb.toString();
	}

	
	public static String getWeekOfDate(String datestr) {
		Date date = parseLongDateTime(datestr);
		String[] weekOfDays = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}
	
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}
	
	/**
	 * 将字符串转换为日期时间
	 *
	 * @param strDate
	 *            yyyyMMddHHmmss格式日期字符串
	 * @return
	 */
	public static Date parseLongDateTime(String strDate) {
		return parse(strDate, DEFAULT_NUMBER_DATETIME_FORMAT);
	}

	public static Date parseLongDateTime4Min(String strDate) {
		return parse(strDate, DEFAULT_NUMBER_DATETIME_FORMAT);
	}
	
	public static Date addDateMinu(Date date, int minus) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minus);
		date = cal.getTime();
		return date;
	}


	
}
