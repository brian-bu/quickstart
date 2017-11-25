package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * 题目要求：比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是大写字母。 
 * 注意事项：在A中出现的B字符串里的字符不需要连续或者有序。 
 * 题目链接：http://www.lintcode.com/zh-cn/problem/compare-strings/
 * 
 * 思路分析：
 * 时间复杂度：
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
