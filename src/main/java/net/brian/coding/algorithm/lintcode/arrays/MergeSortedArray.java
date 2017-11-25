package net.brian.coding.algorithm.lintcode.arrays;

import org.junit.Test;

/**
 * 
 * 题目要求：合并两个排序的整数数组A和B变成一个新的数组。
 * 注意事项：你可以假设A具有足够的空间（A数组的大小大于或等于m+n）去添加B中的元素。
 * 题目链接：http://www.lintcode.com/zh-cn/problem/merge-sorted-array/
 * 
 * 思路分析： 
 * 
 * 时间复杂度：
 *
 */
public class MergeSortedArray {
	/*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
    }
    
    @Test
	public void testCase() {
    	int[] A = new int[4];
    	A[0] = 1;
    	A[1] = 2;
    	A[2] = 3;
    	mergeSortedArray(A, 3, new int[]{4,5}, 2);
		System.out.println(A);// [1, 2, 3, 4, 5]
	}
}
