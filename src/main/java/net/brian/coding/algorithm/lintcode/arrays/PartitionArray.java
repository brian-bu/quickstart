package net.brian.coding.algorithm.lintcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
/**
 * 
 * ��ĿҪ�󣺸���һ���������� nums ��һ������ k���������飨���ƶ����� nums �е�Ԫ�أ���ʹ�ã�
 * ����С��k��Ԫ���Ƶ���ߣ����д��ڵ���k��Ԫ���Ƶ��ұߡ��������黮�ֵ�λ�ã��������е�һ��λ�� i������ nums[i] ���ڵ��� k��
 * ע�������Ӧ�������Ļ������� nums����������ֻ�Ǽ���� k С����������������� nums �е�����Ԫ�ض��� k С���򷵻� nums.length��
 * ��Ŀ���ӣ�http://www.lintcode.com/zh-cn/problem/partition-array/
 * 
 * ˼·������ 
 * 
 * ʱ�临�Ӷȣ�
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
