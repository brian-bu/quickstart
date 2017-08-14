package net.brian.coding.algorithm.lintcode;

import org.junit.Test;

/**
 * http://www.lintcode.com/zh-cn/problem/fibonacci/
 * 
 * 之前在IDE试过递归和数组两种方式，但这两种方式都不能处理传参n很大的情况：其中递归的方式会导致StackOverflowError，
 * 而数组的方式会导致OOM（java.lang.OutOfMemoryError: Requested array size exceeds VM limit）
 * 这两种方法都依靠了一些java底层的机制（递归和数组），后来考虑仅用算术的方式实现，就有了最终的程序。 程序中注释掉了一句:
 * sum = (sum2 + sum1 < 0) ? sum : (sum2 + sum1); 这个是我之前的想法，觉得可以用三目运算符show off一下，但是如果真的有一刻
 * sum2 + sum1 < 0，但是for循环还远远没有结束，就会发生程序不断执行sum=sum；这样的赋值指令直到i=n导致for循环退出。
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