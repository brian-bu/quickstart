package net.brian.coding.algorithm.lintcode.arrays;

import java.util.List;

import org.junit.Test;
/**
 * 
 * ��ĿҪ�󣺸���һ����n������������S����S���ҵ���������a, b, c���ҵ�����ʹ��a + b + c = 0����Ԫ��
 * ע���������Ԫ��(a, b, c)��Ҫ��a <= b <= c��������ܰ����ظ�����Ԫ��
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/3sum/
 * 
 * ˼·������ 
 * 
 * ʱ�临�Ӷȣ�
 *
 */
public class ThreeSum {
	/*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
    	return null;
    }
    
    @Test
	public void testCase() {
		System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));// (-1, 0, 1) (-1, -1, 2)
	}
}
