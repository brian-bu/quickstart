package net.brian.coding.java.core.jdk.concurrency;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 多线程下使用ArrayList，使用t1和t2两个线程同时向numberList添加数据，由于ArrayList不是线程安全的，所以会抛异常：
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 10
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 163
 * ......
 * 注意这个异常不是每次都会抛，这也正是线程安全的概念最好的体现：
 * 多个线程访问某个类时，这个类始终都能表现出正确的行为，就称这个类是线程安全的。
 * 这里不是线程安全的ArrayList就是“不能始终”表现正确行为，但有时也会表现正确行为。
 * 
 * 修改本类的正确做法就是使用线程安全容器替换ArrayList，见：
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
