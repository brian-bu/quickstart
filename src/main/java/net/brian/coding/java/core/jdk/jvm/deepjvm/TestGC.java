package net.brian.coding.java.core.jdk.jvm.deepjvm;

import org.junit.Test;

public class TestGC {
	public Object instance = null;
	
	// �������֤��JVM������ʹ�����ü���.
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
		// finalize���ڵ���gc֮���ĳ��ʱ��ִ�У���Ϊ�������ȼ��ܵͣ�������Ҫ��ͣһ��ʱ��ʹ֮��ִ�С�
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
