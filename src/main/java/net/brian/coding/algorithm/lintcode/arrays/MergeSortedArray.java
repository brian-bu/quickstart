package net.brian.coding.algorithm.lintcode.arrays;

import org.junit.Test;

/**
 * 
 * ��ĿҪ�󣺺ϲ������������������A��B���һ���µ����顣
 * ע���������Լ���A�����㹻�Ŀռ䣨A����Ĵ�С���ڻ����m+n��ȥ���B�е�Ԫ�ء�
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/merge-sorted-array/
 * 
 * ˼·������ 
 * 
 * ʱ�临�Ӷȣ�
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
