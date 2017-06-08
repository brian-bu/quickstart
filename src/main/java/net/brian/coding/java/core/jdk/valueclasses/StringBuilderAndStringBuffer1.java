package net.brian.coding.java.core.jdk.valueclasses;

import org.junit.Test;

import net.brian.coding.java.utils.ConcurrencyTools;

/**
 * 
 * ��д������֤Buffer��Builder���̰߳�ȫ��
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

	// չʾjava.lang.StringBuilder.append(int)������
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
		// �����������Ĺؼ������������̲߳������������ʱ��Ϳ��������߳���builder��buffer�Ĺ���������
		// �����װ�õ�API�Ժ�Ҳ������Ϊ���������߳�ģ����̻߳�����API
		ConcurrencyTools.triggerFixedAmoutOfThreadsByThreadPool(t, amount, milliSeconds);
		// �������Ϊamount���ǰ�ȫ��
		System.out.println(t.builder.length());
		// System.out.println(t.buffer.length());
	}

}