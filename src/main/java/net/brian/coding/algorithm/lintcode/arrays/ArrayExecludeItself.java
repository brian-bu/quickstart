package net.brian.coding.algorithm.lintcode.arrays;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * ��ĿҪ�󣺸���һ����������A������B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]�� ����B��ʱ���벻Ҫʹ�ó�����
 * ע�����
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/product-of-array-exclude-itself/
 * 
 * ˼·������ 
 * 
 * ʱ�临�Ӷȣ�
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
