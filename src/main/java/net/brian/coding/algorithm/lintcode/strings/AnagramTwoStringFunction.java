package net.brian.coding.algorithm.lintcode.strings;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * ��ĿҪ��д��һ������ anagram(s, t) �ж������ַ����Ƿ����ͨ���ı���ĸ��˳����һ�����ַ�����
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/two-strings-are-anagrams/
 * 
 * ˼·�������ȴ������������Ȼ��������������������ֻ��Ҫ���ַ����ֽ��char arrayȻ����Arrays.sort�����������򣬵õ�����������������ַ�һ�¾ͷ���true
 * ����Ҫע�⣬����ֻ��Ҫһ��forѭ���Ϳ����ˣ���Ϊ����ֻ�ǰ����������еĵ�i��ֵ���бȽϣ�����������sChar[i]��ÿһ��tChar����Ԫ�ؽ��бȽ�
 * 
 * ʱ�临�Ӷȣ�O(n)��������ȷʱ�临�Ӷȿ���ȷ����Ҫ����forѭ��
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
