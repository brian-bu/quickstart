package net.brian.coding.algorithm.lintcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
/**
 * 
 * 题目要求：给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
 * 所有小于k的元素移到左边；所有大于等于k的元素移到右边。返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
 * 注意事项：你应该真正的划分数组 nums，而不仅仅只是计算比 k 小的整数数，如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。
 * 题目链接：http://www.lintcode.com/zh-cn/problem/partition-array/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
 *
 */
public class PartitionArray {
	 /*
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
    	if(nums == null || nums.length <= 1) return -1;
    	Arrays.sort(nums); 
    	for(int i = 0; i < nums.length; i++) {
    		if(nums[i] >= k) return i;
    	}
    	return -1;
    }
    
    @Test
	public void testCase() {
		System.out.println(partitionArray(new int[]{3,2,2,1}, 2));// 1
	}
}
