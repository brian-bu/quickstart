package net.brian.coding.java.core.jdk.valueclasses;

import java.util.StringTokenizer;

import org.junit.Test;

/**
 * �����ᵽ�Ķ�����replace��concat��substring��toLowerCase�����ķ���
 * ������һ����ͬ����ǣ������ڷ����ڲ����´����µ�String���󣬲��ҷ�������µĶ���
 * ��ԭ���Ķ����ǲ��ᱻ�ı�ġ���Ҳ��Ϊʲô�ȷ��������ڷ���ֵ��ԭ��
 * ������String�����õ�����Щ��������ı�����ֵ�����뽫���÷�������ֵ��ֵ�����÷�
 */
public class StringResultingReturning {

	// jdk1.5ǰ��ֻ�������ַ�������ʱ+��Ч�ʵ��ڴ��˷Ѵ�
	// ����Ϊ�����������ַ�����ʹ��StringBuffer�����㣬����ֻ�������ַ�������ʱ��ʹ��concat
	// jdk1.5֮������˱������Ż���ʹ��+��ʱ�������Զ�����StringBuidler�����ԾͲ���concat��
	@Test
	public void testConcat() {
		String output1 = "Hello ", output2 = "Hello ", extension = "world!";
		// ���ﾭ��concat֮������û�н�concat�ķ���ֵ����output1������output1�Ծ���concat֮ǰ��ֵ
		output1.concat(extension);
		System.out.println("StringApiDemo -- testConcat() -- output1:: " + output1);
		output2 = output2.concat(extension);
		System.out.println("StringApiDemo -- testConcat() -- output2:: " + output2);
	}

	@Test
	public void testSubstring() {

	}

/**
 * replace��replaceAll���������ͬ�㣺
 * 
 * ��ͬ�㣺����ȫ���滻������Դ�ַ����е�ĳһ�ַ����ַ���ȫ������ָ�����ַ����ַ�����
 * ��ͬ�㣺replaceAll֧��������ʽ����˻�Բ������н����������������ǣ�
 * ��replaceAll�滻��\��Ϊ"\\"����Ҫ��replaceAll("\\\\","\\\\\\\\")����replace��replace("\\","\\\\")
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
	 * ����split�������ַ������зָ���Ȼ�򵥡�����ǿ�󣬵������������е�ϵͳ��Ƶ��ʹ��ʱ�����Ƿǳ����õ�
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
	 * StringTokenizer��JDK���ṩ��ר�����������ַ����ָ�Ĺ����࣬�������ͬ�����ܵ��ַ����ָ�
	 * ��ʹ����δ�����StringTokenizer���󲻶ϵı����������٣���Ч��Ҳ���ڲ���split�������ָ��ַ�����
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
	 * ������JDK�ṩ��String���indexOf������substring����
	 * substring���������˿ռ任ȡʱ��ļ�������������ִ���ٶ���Ի�ܿ죬ֻҪ������ڴ���������⼴��
	 * ��indexOfҲ��һ��ִ�зǳ���ķ����������Ĵ���ֻ���߼�����ֱ����������ά���Բ�
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
				String splitStr = null; // ������ȡ���ַ���
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
	 * charAt������indexOf������Ч���϶��Ǻܸߵķ���
	 * ���´�����ʾ���ж�10000000���ַ����Ŀ�ͷ���β�Ƿ��ǡ�abc����������ʹ��charAt������ʵ��
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
