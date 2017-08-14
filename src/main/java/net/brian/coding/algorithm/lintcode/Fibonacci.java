package net.brian.coding.algorithm.lintcode;

import org.junit.Test;

/**
 * http://www.lintcode.com/zh-cn/problem/fibonacci/
 * 
 * ֮ǰ��IDE�Թ��ݹ���������ַ�ʽ���������ַ�ʽ�����ܴ�����n�ܴ����������еݹ�ķ�ʽ�ᵼ��StackOverflowError��
 * ������ķ�ʽ�ᵼ��OOM��java.lang.OutOfMemoryError: Requested array size exceeds VM limit��
 * �����ַ�����������һЩjava�ײ�Ļ��ƣ��ݹ�����飩���������ǽ��������ķ�ʽʵ�֣����������յĳ��� ������ע�͵���һ��:
 * sum = (sum2 + sum1 < 0) ? sum : (sum2 + sum1); �������֮ǰ���뷨�����ÿ�������Ŀ�����show offһ�£�������������һ��
 * sum2 + sum1 < 0������forѭ����ԶԶû�н������ͻᷢ�����򲻶�ִ��sum=sum�������ĸ�ֵָ��ֱ��i=n����forѭ���˳���
 *
 */
public class Fibonacci {
	/*
	 * @param : an integer
	 * 
	 * @return: an ineger f(n)
	 */
	public int fibonacci(int n) {
		// write your code here
		if (n > Integer.MAX_VALUE)
			return fibonacci(n);
		if (n == 1)
			return 0;
		if (n == 2)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public int fibonacci2(int n) {
		int[] result = new int[n];
		if (n == 1)
			return 0;
		if (n == 2)
			return 1;
		for (int i = 2; i < result.length; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result[n - 1];
	}

	public int fibonacci3(int n) {
		if (n <= 1) {
			return 0;
		}
		if (n == 2 || n == 3) {
			return 1;
		}
		int sum = 0;
		int sum1 = 1;
		int sum2 = 1;
		for (int i = 3; i < n; i++) {
			// sum = (sum2 + sum1 < 0) ? sum : (sum2 + sum1);
			if (sum2 + sum1 < 0)
				break;
			sum = sum2 + sum1;
			sum1 = sum2;
			sum2 = sum;
		}
		return sum;
	}

	@Test
	public void testFibonacci() {
		System.out.println(fibonacci(1));
		System.out.println(fibonacci(2));
		System.out.println(fibonacci(10));
		System.out.println(fibonacci(Integer.MAX_VALUE));
	}

	@Test
	public void testFibonacci2() {
		System.out.println(fibonacci2(1));
		System.out.println(fibonacci2(2));
		System.out.println(fibonacci2(10));
		System.out.println(fibonacci2(Integer.MAX_VALUE));
	}

	@Test
	public void testFibonacci3() {
		System.out.println(fibonacci3(1));
		System.out.println(fibonacci3(2));
		System.out.println(fibonacci3(10));
		System.out.println(fibonacci3(Integer.MAX_VALUE));
	}
}