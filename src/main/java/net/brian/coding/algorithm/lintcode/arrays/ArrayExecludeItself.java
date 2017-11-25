package net.brian.coding.algorithm.lintcode.arrays;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 题目要求：给定一个整数数组A。定义B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]， 计算B的时候请不要使用除法。
 * 注意事项：
 * 题目链接：http://www.lintcode.com/zh-cn/problem/product-of-array-exclude-itself/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
 *
 */
public class ArrayExecludeItself {
	/*
     * @param nums: Given an integers array A
     * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
    	return null;
    }
    
    @Test
	public void testCase() {
    	// Arrays.asList(new int[]{1,2,3})
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
		System.out.println(productExcludeItself(list));// [6, 3, 2]
	}
}
