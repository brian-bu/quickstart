package net.brian.coding.algorithm.lintcode.strings;

import org.junit.Test;

/**
 * 
 * ��ĿҪ�󣺶���һ�������� source �ַ�����һ�� target �ַ�������Ӧ����source�ַ������ҳ�target�ַ������ֵĵ�һ��λ��(��0��ʼ)����������ڣ��򷵻� -1
 * ע������ַ�������ַ�����Ҫ������������
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/strstr/
 * 
 * ˼·������ 
 * ʱ�临�Ӷȣ�
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