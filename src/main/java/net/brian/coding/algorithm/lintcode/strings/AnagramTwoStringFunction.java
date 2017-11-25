package net.brian.coding.algorithm.lintcode.strings;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 题目要求：写出一个函数 anagram(s, t) 判断两个字符串是否可以通过改变字母的顺序变成一样的字符串。
 * 题目链接：http://www.lintcode.com/zh-cn/problem/two-strings-are-anagrams/
 * 
 * 思路分析：先处理特殊情况，然后是正常情况，正常情况只需要把字符串分解成char array然后用Arrays.sort方法进行排序，得到的两个新数组如果字符一致就返回true
 * 这里要注意，我们只需要一层for循环就可以了，因为我们只是把两个数组中的第i个值进行比较，而不是拿着sChar[i]和每一个tChar数组元素进行比较
 * 
 * 时间复杂度：O(n)。首先明确时间复杂度可以确定需要几层for循环
 *
 */
public class AnagramTwoStringFunction {
	/**
	 * @param s:
	 *            The first string
	 * @param b:
	 *            The second string
	 * @return true or false
	 */
	public boolean anagram(String s, String t) {
		// write your code here
		if("".equals(t) && "".equals(s)) return true;
		if(s == null || t == null || "".equals(t) || "".equals(s)) return false;
		if(s.length() != t.length()) return false;
		char [] sChar = s.toCharArray();
		char [] tChar = t.toCharArray();
		Arrays.sort(sChar);
		Arrays.sort(tChar);
		System.out.println(sChar);
		System.out.println(tChar);
		for(int i = 0; i < sChar.length; i++) {
			if(sChar[i] != tChar[i]) return false; 
		}
		return true;
	}

	@Test
	public void testCase() {
		System.out.println(anagram("abcd", "dcab"));// true.
		System.out.println(anagram("ab", "ab"));// true.
		System.out.println(anagram("ab", "ac"));// false.
		System.out.println(anagram("abc", "ac"));// false.
		System.out.println(anagram("", ""));// true.
		System.out.println(anagram(null, "ac"));// false.
	}
}
