package net.brian.coding.java.core.jdk.jvm.deepjvm;

import org.junit.Test;

public class TestGC {
	public Object instance = null;
	
	// 下面程序证明JVM并不是使用引用计数.
	public void testCounting() {
		TestGC gc1 = new TestGC();
		TestGC gc2 = new TestGC();
		gc1.instance = gc2;
		gc2.instance = gc1;
		gc1 = null;
		gc2 = null;
		System.gc();
	}
	
	private void isAlive() {
		System.out.println("I'm still alive.");
	}
	
	private static TestGC SAVE_HOOK = null;
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!");
		TestGC.SAVE_HOOK = this;
	}
	
	@Test
	public void testFinalize() {
		SAVE_HOOK = new TestGC();
		// First try for finalize method.
		SAVE_HOOK = null;
		System.gc();
		// finalize会在调用gc之后的某个时刻执行，因为方法优先级很低，所以需要暂停一段时间使之被执行。
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(SAVE_HOOK != null) {
			isAlive();
		} else {
			System.out.println("Object already dead!");
		}
		// Same code to trigger finalize method again.
		SAVE_HOOK = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(SAVE_HOOK != null) {
			isAlive();
		} else {
			System.out.println("Object already dead!");
		}
	}
}
