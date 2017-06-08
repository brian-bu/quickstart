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
	 * 分割固定格式字符串
	 */
	public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

	// 从java.net.URLClassLoader也就是jvm的核心类加载器的源码中看到的
	// 用于把/java/net/URL转换成java.net.URL.class
	public static String pathParser(String target) {
		return target.replace('.', '/').concat(".class");
	}

	/**
	 * 生成包含26个小写字母和10个阿拉伯数字的指定长度的随机字符串
	 * 这里的方法仅适用于不需要大量创建随机字符串的操作
	 * 如果需要循环上百万次这样很大量的字符串创建操作，这里就会抛出异常如下：
	 * java.lang.OutOfMemoryError: Java heap space
	 * 
	 * @see net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
	 */
	public static String createRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		// 如果length不大于0，那么程序不会进入这个循环，直接执行下面的return语句
		// 所以这里并不需要判断length是否大于0
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			//FIXME: jvm调优：解决OutOfMemoryError
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 实践中，java.util.Random 类总是优于 java.lang.Math.random()
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
    
    // 中航信项目经验 - start
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
            throw new IllegalArgumentException("输入的url为空");
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
    // 中航信项目经验 - end
}