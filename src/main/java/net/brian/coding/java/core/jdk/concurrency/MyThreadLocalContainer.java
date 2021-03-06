package net.brian.coding.java.core.jdk.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 本例就是为了模拟ThreadLocal做一个类似的MyThreadLocal进而理解其内部工作原理
 *
 */
public class MyThreadLocalContainer {
	public static void main(String[] args) {
		if (args != null && args.length != 0 && args[0].equals("thread")) {
			SequenceTestForThreadLocal thread = new SequenceTestForThreadLocal();
			ClientThread thread1 = new ClientThread(thread);
			ClientThread thread2 = new ClientThread(thread);
			ClientThread thread3 = new ClientThread(thread);
			thread1.start();
			thread2.start();
			thread3.start();
		} else {
			SequenceTestForMyThreadLocal myThread = new SequenceTestForMyThreadLocal();
			ClientThread myThread1 = new ClientThread(myThread);
			ClientThread myThread2 = new ClientThread(myThread);
			ClientThread myThread3 = new ClientThread(myThread);
			myThread1.start();
			myThread2.start();
			myThread3.start();
		}
	}
}
/**
 * 
 * 重点是这个类，它实现了自己的ThreadLocal，这里声明了MyThreadLocal可以是任意类型对象的容器
 *
 */
class MyThreadLocal<T> {
	// 自己实现一个ThreadLocal的关键点1：首先要保证hashmap的线程安全，同时注意key-value的数据类型
	private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

	public void set(T value) {
		container.put(Thread.currentThread(), value);
	}

	public T get() {
		// 自己实现一个ThreadLocal的关键点2：要知道map里放的是什么，也即key-value对
		// key是thread，value是线程对应的值，若为空则用initialValue方法返回一个默认值作为结果
		Thread thread = Thread.currentThread();
		T value = container.get(thread);
		if (value == null && !container.containsKey(thread)) {
			value = initialValue();
			container.put(thread, value);
		}
		return value;
	}

	public void remove() {
		container.remove(Thread.currentThread());
	}

	// 自己实现一个ThreadLocal的关键点3：该方法返回值是ThreadLocalMap中的value，使用时由Client自己建一个内部类实现该方法
	protected T initialValue() {
		return null;
	}
}

class SequenceTestForThreadLocal implements Sequence {
	private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}

}

class SequenceTestForMyThreadLocal implements Sequence {
	private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}
}

interface Sequence {
	int getNumber();
}

class ClientThread extends Thread {
	private Sequence sequence;

	public ClientThread(Sequence seq) {
		this.sequence = seq;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
		}
	}
}