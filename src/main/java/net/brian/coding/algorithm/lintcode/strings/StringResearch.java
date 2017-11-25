package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * 题目要求：对于一个给定的 source 字符串和一个 target 字符串，你应该在source字符串中找出target字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1
 * 注意事项：字符串里的字符不需要连续或者有序。
 * 题目链接：http://www.lintcode.com/zh-cn/problem/strstr/
 * 
 * 思路分析： 
 * 时间复杂度：
 *
 */
public class StringResearch {
    /*
     * @param source: source string to be scanned.
     * @param target: target string containing the sequence of characters to match
     * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
     */
    public int strStr(String source, String target) {
        // write your code here
    	if(source == null || target == null) return -1;
    	if("".equals(target)) return 0;
    	if(!source.contains(target) || source.length() < target.length()) return -1;
    	return source.indexOf(target);
    }
    
	@Test
	public void testCase() {
		System.out.println(strStr("source", "target"));// -1
		System.out.println(strStr("abcdabcdefg", "bcd"));// 1
		System.out.println(strStr("", ""));// 0
	}
}