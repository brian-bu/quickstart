package net.brian.coding.java.core.jdk.valueclasses;

import java.util.StringTokenizer;

import org.junit.Test;

/**
 * 这里提到的都是像replace，concat，substring，toLowerCase这样的方法
 * 它们有一个共同点就是：都是在方法内部重新创建新的String对象，并且返回这个新的对象
 * 而原来的对象是不会被改变的。这也是为什么等方法都存在返回值的原因。
 * 单纯用String的引用调用这些方法不会改变对象的值，必须将调用方法返回值赋值给调用方
 */
public class StringResultingReturning {

	// jdk1.5前，只有两个字符串连结时+号效率低内存浪费大
	// 若仅为了连接两个字符串而使用StringBuffer不划算，所以只有两个字符串连接时请使用concat
	// jdk1.5之后进行了编译器优化，使用+号时编译器自动调用StringBuidler，所以就不用concat了
	@Test
	public void testConcat() {
		String output1 = "Hello ", output2 = "Hello ", extension = "world!";
		// 这里经过concat之后由于没有将concat的返回值赋给output1，所以output1仍旧是concat之前的值
		output1.concat(extension);
		System.out.println("StringApiDemo -- testConcat() -- output1:: " + output1);
		output2 = output2.concat(extension);
		System.out.println("StringApiDemo -- testConcat() -- output2:: " + output2);
	}

	@Test
	public void testSubstring() {

	}

/**
 * replace和replaceAll的区别和相同点：
 * 
 * 相同点：都是全部替换，即把源字符串中的某一字符或字符串全部换成指定的字符或字符串；
 * 不同点：replaceAll支持正则表达式，因此会对参数进行解析（两个参数均是）
 * 用replaceAll替换“\”为"\\"，就要用replaceAll("\\\\","\\\\\\\\")，而replace则replace("\\","\\\\")
 * 
 * @see net.brian.coding.utils.StringTools
 */
	@Test
	public void testReplace() {

	}
/**
 * @see net.brian.coding.utils.StringTools
 */
	@Test
	public void testReplaceAll() {

	}

	/**
	 * 采用split方法对字符串进行分割虽然简单、功能强大，但是在性能敏感的系统中频繁使用时性能是非常不好的
	 */
	@Test
	public void testSplit() {
		String str = null;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 1000; i++) {
			sb.append(i).append(";");
		}
		str = sb.toString();

		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			str.split(";");
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	/**
	 * StringTokenizer是JDK中提供的专门用来进行字符串分割的工具类，该类进行同样功能的字符串分割
	 * 即使在这段代码中StringTokenizer对象不断的被创建并销毁，其效率也高于采用split方法来分割字符串。
	 */
	@Test
	public void testStringTokenizer() {
		String str = null;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 1000; i++) {
			sb.append(i).append(";");
		}
		str = sb.toString();

		long begin = System.currentTimeMillis();

		StringTokenizer st = new StringTokenizer(str, ";");
		for (int i = 0; i < 10000; i++) {
			while (st.hasMoreTokens()) {
				st.nextToken();
			}
			st = new StringTokenizer(str, ";");
		}

		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	/**
	 * 采用了JDK提供的String类的indexOf方法与substring方法
	 * substring方法采用了空间换取时间的技术，所以它的执行速度相对会很快，只要处理好内存溢出的问题即可
	 * 而indexOf也是一个执行非常快的方法。这样的代码只是逻辑并不直观清晰，可维护性差
	 */
	@Test
	public void testOptimizedSplit() {
		String str = null;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 1000; i++) {
			sb.append(i).append(";");
		}
		str = sb.toString();

		long begin = System.currentTimeMillis();

		String temp = str;
		for (int i = 0; i < 10000; i++) {
			while (true) {
				@SuppressWarnings("unused")
				String splitStr = null; // 保留截取的字符串
				int index = temp.indexOf(";");
				if (index < 0) {
					break;
				}
				splitStr = temp.substring(0, index);
				temp = temp.substring(index + 1);
			}
			temp = str;
		}

		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	/**
	 * charAt方法与indexOf方法在效率上都是很高的方法
	 * 如下代码所示，判断10000000次字符串的开头与结尾是否是“abc”，单纯的使用charAt方法来实现
	 */
	@Test
	public void testCharAt() {
		String str = null;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 1000; i++) {
			sb.append(i).append(";");
		}
		str = sb.toString();

		long begin = System.currentTimeMillis();

		for (int i = 0; i < 10000000; i++) {
			int len = str.length();
			if (str.charAt(0) == 'a' && str.charAt(1) == 'b' && str.charAt(2) == 'c')
				;
			if (str.charAt(len - 3) == 'a' && str.charAt(len - 2) == 'b' && str.charAt(len - 1) == 'c')
				;
		}

		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}
