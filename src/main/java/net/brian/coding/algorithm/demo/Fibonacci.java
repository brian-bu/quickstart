package net.brian.coding.algorithm.demo;

public class Fibonacci {
	public static long F(int N) {
		if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N-1) + F(N-2);
	}
	public static void main(String[] args) {
		for(int N = 0; N < 100; N++) {
			System.out.println(N + " " + F(N));
		}
	}
	//TODO: 算法：思考为什么这是一种更好的实现
	public static long[] A(long[] a) {
		long start = System.currentTimeMillis();
		a[0] = 0;
		a[1] = 1;
		for(int N = 2; N < 100; N++) {
			a[N] = a[N - 1] + a[N + 1];
			System.out.println(N + " " + a[N]);
		}
		long end = System.currentTimeMillis();
		System.out.println("Spent " + (end - start) + "ms to reach 100.");
		return a;
	}
}
