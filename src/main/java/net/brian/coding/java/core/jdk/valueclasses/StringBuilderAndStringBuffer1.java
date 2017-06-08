package net.brian.coding.java.core.jdk.valueclasses;

import org.junit.Test;

import net.brian.coding.java.utils.ConcurrencyTools;

/**
 * 
 * 编写程序验证Buffer和Builder的线程安全性
 *
 */
public class StringBuilderAndStringBuffer1 implements Runnable {
	StringBuilder builder = new StringBuilder();
	// StringBuffer buffer = new StringBuffer();

	public void run() {
		try {
			Thread.sleep((int) (Math.random() * 2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		builder.append(1);
		// buffer.append(1);
	}

	// 展示java.lang.StringBuilder.append(int)的作用
	@Test
	public void testAppend() {
		StringBuilder builder = new StringBuilder();
		builder.append(1);
		builder.append(1);
		System.out.println(builder);
	}

	public static void main(String[] args) {
		int amount = 10000;
		int milliSeconds = 10;
		StringBuilderAndStringBuffer1 t = new StringBuilderAndStringBuffer1();
		// 这句是整个类的关键：创建大量线程产生竞争，这个时候就看出来多线程下builder和buffer的工作能力了
		// 这个封装好的API以后也可以作为批量产生线程模拟多线程环境的API
		ConcurrencyTools.triggerFixedAmoutOfThreadsByThreadPool(t, amount, milliSeconds);
		// 如果长度为amount就是安全的
		System.out.println(t.builder.length());
		// System.out.println(t.buffer.length());
	}

}