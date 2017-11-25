package net.brian.coding.algorithm.lintcode.arrays;

import org.junit.Test;

/**
 * 
 * 题目要求：给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
 * 注意事项：你可以假设只有一组答案
 * 题目链接：http://www.lintcode.com/zh-cn/problem/two-sum/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
 *
 */
public class TwoSum {
	/*
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
    	return null;
    }
    
    @Test
	public void testCase() {
		System.out.println(twoSum(new int[]{2, 7, 11, 15}, 9));// [1, 2]
	}
}
