package net.brian.coding.java.core.jdk.concurrency;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ���߳���ʹ��ArrayList��ʹ��t1��t2�����߳�ͬʱ��numberList������ݣ�����ArrayList�����̰߳�ȫ�ģ����Ի����쳣��
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 10
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 163
 * ......
 * ע������쳣����ÿ�ζ����ף���Ҳ�����̰߳�ȫ�ĸ�����õ����֣�
 * ����̷߳���ĳ����ʱ�������ʼ�ն��ܱ��ֳ���ȷ����Ϊ���ͳ���������̰߳�ȫ�ġ�
 * ���ﲻ���̰߳�ȫ��ArrayList���ǡ�����ʼ�ա�������ȷ��Ϊ������ʱҲ�������ȷ��Ϊ��
 * 
 * �޸ı������ȷ��������ʹ���̰߳�ȫ�����滻ArrayList������
 * @see net.brian.coding.java.core.jdk.concurrency.BiasedLocking
 *
 */
public class ThreadUnsafeArrayList {
	public static List<Integer> numberList = new ArrayList<Integer>();

	public static class AddToList implements Runnable {
		int startNum = 0;

		public AddToList(int startNumber) {
			startNum = startNumber;
		}

		@Override
		public void run() {
			int count = 0;
			while (count < 1000000) {
				numberList.add(startNum);
				startNum += 2;
				count++;
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new AddToList(0));
		Thread t2 = new Thread(new AddToList(1));
		t1.start();
		t2.start();
	}
}
