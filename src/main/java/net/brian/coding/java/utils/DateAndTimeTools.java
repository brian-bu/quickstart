package net.brian.coding.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * 对于时间操作的一些工具的封装
 * 通常利用Calendar的日期转化都是这样的：
 * Calendar <-(getTime&setTime)-> Date <-(SimpleDateFormat)-> String 
 * 
 * 在旧版本JDK时代用 java.util.Date类，但 Date类不便于实现国际化
 * 因此通常更推荐使用 java.util.Calendar 类进行时间和日期方面的处理
 * 日期处理最重要的几个类已经在示范类的开头声明成了static的域，它们是：
 * SimpleDateFormat：可以在构造器传入一个字符串来规定日期的格式方便实现国际化
 * 通过Calendar.getInstance()获取Calendar的实例
 * 
 * 这里要说明的是利用Calendar处理日期就不得不写大量的代码，所以推荐一款工具Joda
 * Joda支持和JDK的互操作性，Joda的类能够生成java.util.Date和Calendar的实例
 * 这使您能够保留现有的依赖JDK的代码，但是又能够使用Joda处理复杂的日期/时间计算
 * 对于Joda的详细使用，请见：
 * https://www.ibm.com/developerworks/cn/java/j-jodatime.html
 *
 */
public final class DateAndTimeTools {
	
	private DateAndTimeTools() {}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static String dateString_01 = "2017-02-19 11:11:11";
	static String dateString_02 = "2017-03-19 11:11:11";
	
	static Calendar now = Calendar.getInstance();
	static Date specialDate = castStringToDate(dateString_01);
	
	/**
	 * 日期转换成字符串:Date to String
	 */
	public static String castDateToString(Date date) {
	    return  sdf.format(date);
	}
	
	/**
	 * 字符串转换成日期:String to Date
	 */
	public static Date castStringToDate(String dateString) {
	    Date parsedDate = null;
		try {
			parsedDate = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return parsedDate;
	}
	
	/**
	 * 获取和更改Calendar日期的详细参数内容
	 */
	public static void getAndSetCalendarDetails() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR); //当前年份
		int month = now.get(Calendar.MONTH) + 1; //当前月，注意加 1
		int day = now.get(Calendar.DATE); //当前日
		Date date = now.getTime(); //直接取得一个 Date 类型的日期
		System.out.println("The year is:: " + year + "\nThe month is:: "+ month + "\nThe day is:: " + day);
		System.out.println("So the Date now is:: " + date);// 这里顺便测试了一下Date.toString()
		now.set(Calendar.YEAR, year - 1);//把当前日期向前设置一年
		System.out.println("Now the Date of Year changed:: " + now.get(Calendar.YEAR));
		now.setTime(specialDate);//把当前日期设置成一个特定的时间
		System.out.println("Now the Date changed:: " + now.getTime());
	}
	
	/**
	 * 以某个日期为基准，通过Calendar提供的API计算其几天前/后、几年前/后，或者其他时间单位前后的日期
	 * 日期转Calendar:Date to Calendar
	 * 
	 * @param specialDate
	 */
	public static void countSpecialDateDifference(Date specialDate) {
		System.out.println("Date now by system:: " + castDateToString(now.getTime()));
		//根据某个特定的时间 date (Date 型) 计算
		now.setTime(specialDate); //注意在此处将 now 的值改为特定日期
		now.add(Calendar.YEAR, 1); //特定时间的1年后
		now.add(Calendar.YEAR, -1); //特定时间的1年前
	}
	
	/**
	 * 计算传入字符串转换成日期之后和现在时间的差值
	 * 
	 * @param pastStr
	 */
	public static void countTimeDifference(String pastStr) {
		long nowInMillis = now.getTime().getTime(); // Date.getTime() 获得毫秒型日期
		long past = castStringToDate(pastStr).getTime();
		long betweenDate = (past - nowInMillis) / (1000 * 60 * 60 * 24); // 计算间隔多少天，则除以毫秒到天的转换公式
		System.out.println("betweenDate:: " + betweenDate);
	}
	
	/**
	 * 通过 after() 与 before() 或者compareTo() 方法进行日期比较
	 * @param dateString_01
	 * @param dateString_02
	 */
	public static void compareDates(String dateString_01, String dateString_02) {
		try {
			Date date1 = sdf.parse(dateString_01);
			Date date2 = sdf.parse(dateString_02);
			System.out.println(date1.before(date2)); // true
			System.out.println(date2.before(date1)); // false
			System.out.println(date2.after(date1)); // true
			System.out.println(date1.compareTo(date2)); // -1
			System.out.println(date2.compareTo(date1)); // 1
			System.out.println(date2.compareTo(date2)); // 0
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		countTimeDifference(dateString_01);
		countSpecialDateDifference(specialDate);
		getAndSetCalendarDetails();
	}
}
