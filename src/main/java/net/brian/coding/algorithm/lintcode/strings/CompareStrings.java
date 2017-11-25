package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * ��ĿҪ�󣺱Ƚ������ַ���A��B��ȷ��A���Ƿ����B�����е��ַ����ַ���A��B�е��ַ����Ǵ�д��ĸ�� 
 * ע�������A�г��ֵ�B�ַ�������ַ�����Ҫ������������ 
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/compare-strings/
 * 
 * ˼·������
 * ʱ�临�Ӷȣ�
 *
 */
public class CompareStrings {
	/*
	 * @param A: A string
	 * 
	 * @param B: A string
	 * 
	 * @return: if string A contains all of the characters in B return true else
	 * return false
	 */
	public boolean compareStrings(String A, String B) {
		// write your code here
		if(A == null || B == null) return false;
		if("".equals(B)) return true;
		if(A.length() < B.length()) return false;
		char[] littleChar = B.toCharArray();
		for(char test : littleChar) {
			String testStr = String.valueOf(test);
			if(!A.contains(testStr)) return false;
			A = A.replaceFirst(testStr, "");
		}
		return true;
	}

	@Test
	public void testCase() {
		System.out.println(compareStrings("ABCD", "ACD"));// true
		System.out.println(compareStrings("A", ""));// true
		System.out.println(compareStrings("", ""));// true
		System.out.println(compareStrings("ABCD", "AABC"));// false
	}
}
