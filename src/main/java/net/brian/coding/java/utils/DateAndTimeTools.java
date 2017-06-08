package net.brian.coding.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * ����ʱ�������һЩ���ߵķ�װ
 * ͨ������Calendar������ת�����������ģ�
 * Calendar <-(getTime&setTime)-> Date <-(SimpleDateFormat)-> String 
 * 
 * �ھɰ汾JDKʱ���� java.util.Date�࣬�� Date�಻����ʵ�ֹ��ʻ�
 * ���ͨ�����Ƽ�ʹ�� java.util.Calendar �����ʱ������ڷ���Ĵ���
 * ���ڴ�������Ҫ�ļ������Ѿ���ʾ����Ŀ�ͷ��������static���������ǣ�
 * SimpleDateFormat�������ڹ���������һ���ַ������涨���ڵĸ�ʽ����ʵ�ֹ��ʻ�
 * ͨ��Calendar.getInstance()��ȡCalendar��ʵ��
 * 
 * ����Ҫ˵����������Calendar�������ھͲ��ò�д�����Ĵ��룬�����Ƽ�һ���Joda
 * Joda֧�ֺ�JDK�Ļ������ԣ�Joda�����ܹ�����java.util.Date��Calendar��ʵ��
 * ��ʹ���ܹ��������е�����JDK�Ĵ��룬�������ܹ�ʹ��Joda�����ӵ�����/ʱ�����
 * ����Joda����ϸʹ�ã������
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
	 * ����ת�����ַ���:Date to String
	 */
	public static String castDateToString(Date date) {
	    return  sdf.format(date);
	}
	
	/**
	 * �ַ���ת��������:String to Date
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
	 * ��ȡ�͸���Calendar���ڵ���ϸ��������
	 */
	public static void getAndSetCalendarDetails() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR); //��ǰ���
		int month = now.get(Calendar.MONTH) + 1; //��ǰ�£�ע��� 1
		int day = now.get(Calendar.DATE); //��ǰ��
		Date date = now.getTime(); //ֱ��ȡ��һ�� Date ���͵�����
		System.out.println("The year is:: " + year + "\nThe month is:: "+ month + "\nThe day is:: " + day);
		System.out.println("So the Date now is:: " + date);// ����˳�������һ��Date.toString()
		now.set(Calendar.YEAR, year - 1);//�ѵ�ǰ������ǰ����һ��
		System.out.println("Now the Date of Year changed:: " + now.get(Calendar.YEAR));
		now.setTime(specialDate);//�ѵ�ǰ�������ó�һ���ض���ʱ��
		System.out.println("Now the Date changed:: " + now.getTime());
	}
	
	/**
	 * ��ĳ������Ϊ��׼��ͨ��Calendar�ṩ��API�����伸��ǰ/�󡢼���ǰ/�󣬻�������ʱ�䵥λǰ�������
	 * ����תCalendar:Date to Calendar
	 * 
	 * @param specialDate
	 */
	public static void countSpecialDateDifference(Date specialDate) {
		System.out.println("Date now by system:: " + castDateToString(now.getTime()));
		//����ĳ���ض���ʱ�� date (Date ��) ����
		now.setTime(specialDate); //ע���ڴ˴��� now ��ֵ��Ϊ�ض�����
		now.add(Calendar.YEAR, 1); //�ض�ʱ���1���
		now.add(Calendar.YEAR, -1); //�ض�ʱ���1��ǰ
	}
	
	/**
	 * ���㴫���ַ���ת��������֮�������ʱ��Ĳ�ֵ
	 * 
	 * @param pastStr
	 */
	public static void countTimeDifference(String pastStr) {
		long nowInMillis = now.getTime().getTime(); // Date.getTime() ��ú���������
		long past = castStringToDate(pastStr).getTime();
		long betweenDate = (past - nowInMillis) / (1000 * 60 * 60 * 24); // �����������죬����Ժ��뵽���ת����ʽ
		System.out.println("betweenDate:: " + betweenDate);
	}
	
	/**
	 * ͨ�� after() �� before() ����compareTo() �����������ڱȽ�
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
