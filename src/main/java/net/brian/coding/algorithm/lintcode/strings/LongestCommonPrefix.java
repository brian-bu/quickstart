package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * 题目要求：给k个字符串，求出他们的最长公共前缀(LCP)
 * 题目链接：http://www.lintcode.com/zh-cn/problem/longest-common-prefix/
 * 
 * 思路分析：首先对数组排序是没有意义的，我们只需要保证遍历数组检查非空的同时，选取最短的那个数组元素，然后用这个数组元素的每一个字符和其它数组元素的相同位置的字符进行一一比较
 * 每一次for循环比较出不同则返回result，比较所有都相同则将这个字符拼接到result后面。
 * 时间复杂度：O(n²)
 * 
 * 既然每次都是到了for循环的时候犯蒙，那就要先通过时间复杂度预估需要几层for循环，然后分别确定每个for循环需要做的事情，这就是理清逻辑的过程。
 * 其实理清逻辑最难的地方就在于嵌套for循环，这个逻辑理清了，代码就知道该怎么写了。所以每次写代码前要先考虑清楚for循环的逻辑
 *
 */
public class LongestCommonPrefix {
	/*
	 * @param strs: A list of strings
	 * 
	 * @return: The longest common prefix
	 */
	public String longestCommonPrefix(String[] strs) {
		// write your code here
		int minLen = Integer.MAX_VALUE;
		if (strs == null)
			return "";
		for (String str : strs) {
			if (str == null || "".equals(str))
				return "";
			int currentLen = str.length();
			minLen = (currentLen < minLen) ? currentLen : minLen;
		}
		String result = "";
		int count = 0;
		// 外层for循环：遍历最短数组元素的每一个字符
		for (int i = 0; i < minLen; i++) {
			// char的默认值是0，这个0是int的0，而不是'0'或者"0"，所以是否经过赋值可以通过和int型的0比较得知
			char compareChar = 0;
			// 内层for循环：用得到的字符分别和每一个数组元素相同位置i上的字符进行比较，全部相同则将这个字符追加到result，否则直接返回
			for (String str : strs) {
				if (compareChar == 0) {
					compareChar = str.charAt(i);
					continue;
				} else if (compareChar != str.charAt(i)) {
					System.out.println("Value of count:: " + count);
					return result;
				}
			}
			if (compareChar != 0) {
				result += compareChar;
				count++;
			}
		}
		// 如果数组中所有的元素都相等就会走到这里
		return result;
	}

	@Test
	public void testCase() {
		System.out.println(longestCommonPrefix(new String[] { "ABCD", "ABCD", "ABCD" }));// "A"
		System.out.println(longestCommonPrefix(new String[] { "ABCD", "ABEF", "ACEF" }));// "A"
		System.out.println(longestCommonPrefix(new String[] { "ABCDEFG", "ABCEFG", "ABCEFA" }));// "ABC"
	}
}
