package net.brian.coding.java.core.jdk.concurrency.utilities;

public class ExpensiveObjectCreating {
	@SuppressWarnings("unused")
	private volatile double d; // Prevent optimization
	private static int counter = 0;
	private final int id = counter++;
	// ����һ��volatile��double��ͨ��forѭ�������������㣬����TIJ�ǳ��ֹ���εİ������Ĵ�������ԭ��Fat.java
	public ExpensiveObjectCreating() {
		// Expensive, interruptible operation:
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void operation() {
		System.out.println(this);
	}

	public String toString() {
		return "ExpensiveObjectCreating id: " + id;
	}
}