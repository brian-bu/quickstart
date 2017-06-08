package net.brian.coding.java.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
	public static final String SEPARATOR = String.valueOf((char) 29);
	
	/**
	 * �ָ�̶���ʽ�ַ���
	 */
	public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

	// ��java.net.URLClassLoaderҲ����jvm�ĺ������������Դ���п�����
	// ���ڰ�/java/net/URLת����java.net.URL.class
	public static String pathParser(String target) {
		return target.replace('.', '/').concat(".class");
	}

	/**
	 * ���ɰ���26��Сд��ĸ��10�����������ֵ�ָ�����ȵ�����ַ���
	 * ����ķ����������ڲ���Ҫ������������ַ����Ĳ���
	 * �����Ҫѭ���ϰ���������ܴ������ַ�����������������ͻ��׳��쳣���£�
	 * java.lang.OutOfMemoryError: Java heap space
	 * 
	 * @see net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
	 */
	public static String createRandomString(int length) { // length��ʾ�����ַ����ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		// ���length������0����ô���򲻻�������ѭ����ֱ��ִ�������return���
		// �������ﲢ����Ҫ�ж�length�Ƿ����0
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			//FIXME: jvm���ţ����OutOfMemoryError
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * ʵ���У�java.util.Random ���������� java.lang.Math.random()
	 */
	public static int createRandomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static String null2Empty(String str) {
		if (str == null || str.trim().equals("null")) {
			return "";
		}
		return str;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}
	
	public static String fileSystemSeperatorConvertor(String targetStr) {
		// String[] str = {"/", "\\\\", "//"};
		String result = null;
		if(null != targetStr) {
			targetStr.replaceAll("", File.separator);
		}
		return result;
	}
	
	public static String escapeHtmlCharacaters(String target) {
		if (null != target) {
			target = target.replaceAll("&quot;", "\"");
			target = target.replaceAll("&nbsp;", " ");
			target = target.replaceAll("&amp;", "&");
			target = target.replaceAll("&lt;", "<");
			target = target.replaceAll("&gt;", ">");
		}
		return target;
	}

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    // �к�����Ŀ���� - start
    public static String format2FourGit(String target, String template) {
		DecimalFormat df = new DecimalFormat(template);
		String formattedStr = df.format(target);
		System.out.println(formattedStr);
		return formattedStr;
	}
	public static String getDomainName(String url)
    {
        if (url == null)
        {
            throw new IllegalArgumentException("�����urlΪ��");
        }
        Pattern pattern = Pattern.compile("(<=http://|\\.)[^.]*");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        System.out.println(matcher.group());
        return matcher.group();
    }
	public static boolean ifMatchURLPatterns(String url) {
		// Commented pattern can't match http://localhost:8080
		// ^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+[^//]*?\\.(com|cn|net|org|biz|info|cc|tv|me)
		Pattern pattern = Pattern.compile("^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(url);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
    // �к�����Ŀ���� - end
}