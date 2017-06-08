package net.brian.coding.java.core.jdk.exception;

/**
 * 
 * 1. 无论是否有异常，finally块中的语句都会执行，无论try或catch模块是否有return；
 * 2.当try或catch模块有return语句，若finally模块中无return时
 * try或catch模块的return返回值在finally模执行前确定的，且return语句先于finally模块执行
 * 若finally模块有return，无论try或catch模块中是否有return，程序段最终从finally的return返回;
 * 但一般不推荐在finally语句中return
 * 3.若finally模块无return，return语句先于finally模块执行
 * try或catch模块的return返回值（若有的return的话）在finally模执行前就已经确定，且但方法最终是从return语句返回。
 * 4.对于try-catch-finally语句之后的return语句，finally模块是在return前执行的，且return返回值在finally模执行后确定的
 *
 * 
 */
public class FinallyReturnDemo {
	private Object sharedObj = new Object();

	private Object method1() {
		System.out.println("method1()");
		throw new RuntimeException();
		// 如果这里就是为了抛出异常而写的方法，那么它和return只能执行一个，另一个就变成了unreachable code。
		// return sharedObj;
	}

	private Object method2() {
		System.out.println("method2()");
		return sharedObj;
	}

	private Object method3() {
		System.out.println("method3()");
		return sharedObj;
	}

	@SuppressWarnings("finally")
	public Object testFinallyReturn() {
		try {
			return method1();
		} catch (RuntimeException rte) {
//			return method2();
		} finally {
//			return method3();
		}
		return null;
	}

	public static void main(String[] args) {
		FinallyReturnDemo demo = new FinallyReturnDemo();
		demo.testFinallyReturn();
	}
}
