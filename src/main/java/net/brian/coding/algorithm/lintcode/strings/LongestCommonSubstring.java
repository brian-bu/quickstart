package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * 题目要求：给出两个字符串，找到最长公共子串，并返回其长度。 
 * 注意事项：子串的字符应该连续的出现在原字符串中，这与子序列有所不同
 * 题目链接：http://www.lintcode.com/zh-cn/problem/longest-common-substring/
 * 
 * 思路分析： substring方法选取任意长度的子串，然后和另一个字符串用contains对比，每当contains返回true都将子串长度cur和全局的最终结果result值比较，result返回两者中的最大值。最终输出result。
 * 时间复杂度：O(n²)
 *
 */
public class LongestCommonSubstring {
	/*
	 * @param A: A string
	 * 
	 * @param B: A string
	 * 
	 * @return: the length of the longest common substring.
	 */
	public int longestCommonSubstring(String A, String B) {
		// write your code here
		if (A == null || B == null || "".equals(B) || "".equals(A))
			return 0;
		if (A.equals(B))
			return A.length();
		int currentLen = 0;
		String currentStr = "";
		int resultLen = 0;
		String min = (A.length() < B.length()) ? A : B;
		String max = (A.length() < B.length()) ? B : A;
		for (int i = 0; i < min.length(); i++) {
			// 其它的都是扯淡，只有内层的for循环才是O(n²)实现最大公共字串的关键，也就是本解法的关键点
			// 对于begin：begin其实很简单，从0开始自增就行了，关键是end
			// 对于end：一方面end的自增是要和begin的增幅同步的，但是另一方面也是通过end的大小来确定截取子串的长度
			// 对于（end = min.length() - i）：i越来越大，end就越来越小，从而控制了截取子串的长度从大到小
			for (int begin = 0, end = min.length() - i; end <= min.length(); begin++, end++) {
				//TODO: substring或contains本身是否会引入时间复杂度？
				currentStr = min.substring(begin, end);
				if (max.contains(currentStr)) {
					System.out.println("currentStr:: " + currentStr);
					currentLen = currentStr.length();
					resultLen = (currentLen < resultLen) ? resultLen : currentLen;
				}
			}
		}
		return resultLen;
	}

	@Test
	public void testCase() {
		System.out.println(longestCommonSubstring("ABCD", "CBCE"));// 2
		System.out.println(longestCommonSubstring("", "CBCE"));// 0
		System.out.println(longestCommonSubstring("ABCD", ""));// 0
		System.out.println(longestCommonSubstring("ABCD", null));// 0
		System.out.println(longestCommonSubstring(null, "CBCE"));// 0
		System.out.println(longestCommonSubstring("EHWX", "CBCE"));// 1
		System.out.println("'AB' == 'A' + new String('B'):: " + ("AB" == "A" + new String("B")));
		System.out.println(longestCommonSubstring("AB", "A" + new String("B")));// 2
		// 多次出现A和AB，还有一次是AbCD，但结果应该是ABC，返回3
		System.out.println(longestCommonSubstring("ABCD", "AbCDFABHRATHSDSABCGHD"));// 3
		// 是找出两个字符串最大公共子串，所以把两个字符串反过来应该返回相同的结果：3
		// 所以找最大公共子串的时候也不能用一个字符串的char去另一个字符串中挨个搜，最暴力的解法就是把两个字符串所有可能子串全列出来，然后比较 
		System.out.println(longestCommonSubstring("AbCDFABHRATHSDSABCGHD", "ABCD"));// 3
	}
}
