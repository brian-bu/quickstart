package net.brian.coding.algorithm.lintcode.arrays;

import org.junit.Test;
/**
 * 
 * 题目要求：给出一个无序的整数数组，找出其中没有出现的最小正整数
 * 注意事项：
 * 题目链接：http://www.lintcode.com/zh-cn/problem/first-missing-positive/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
 *
 */
public class FirstMissingPositive {
	/*
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
    	return 0;
    }
    
    @Test
	public void testCase() {
		System.out.println(firstMissingPositive(new int[]{1,2,0}));// 3
		System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));// 2
	}
}
