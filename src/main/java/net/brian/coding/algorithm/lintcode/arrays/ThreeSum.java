package net.brian.coding.algorithm.lintcode.arrays;

import java.util.List;

import org.junit.Test;
/**
 * 
 * 题目要求：给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组
 * 注意事项：在三元组(a, b, c)，要求a <= b <= c。结果不能包含重复的三元组
 * 题目链接：http://www.lintcode.com/zh-cn/problem/3sum/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
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
