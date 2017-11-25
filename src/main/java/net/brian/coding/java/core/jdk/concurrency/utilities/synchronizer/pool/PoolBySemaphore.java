package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.pool;

//: concurrency/Pool.java
// Using a Semaphore inside a Pool, to restrict
// the number of tasks that can use a resource.
import java.util.concurrent.*;
import java.util.*;

/**
 * 
 * ����������Lock��synchronized�����κ�ʱ�̶�ֻ����һ���������һ����Դ �����ź�������n������ͬʱ���������Դ
 * 
 * �����Զ����Ϊ��������ع������������޵Ķ��󣬵�Ҫʹ�ö���ʱ����ǩ�����ǣ�ʹ�����ǩ��
 *
 */
public class PoolBySemaphore<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkedOut;
	private Semaphore available;

	public PoolBySemaphore(Class<T> classObject, int size) {
		this.size = size;
		checkedOut = new boolean[size];
		// ����size���ź�����true��ʾ������Щ�ź�����ѭ�Ƚ��ȳ�
		available = new Semaphore(size, true);
		// Load pool with objects that can be checked out:
		for (int i = 0; i < size; ++i)
			try {
				// Assumes a default constructor:
				items.add(classObject.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	// ����ռ��һ����Դ�ķ�����װ
	public T checkOut() throws InterruptedException {
		// ռ��һ����Դ
		available.acquire();
		return getItem();
	}

	// �����ͷ�һ����Դ�ķ�����װ
	public void checkIn(T x) {
		if (releaseItem(x))
			// �ͷ�һ����Դ
			available.release();
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; ++i)
			if (!checkedOut[i]) {
				checkedOut[i] = true;
				return items.get(i);
			}
		return null; // Semaphore prevents reaching here
	}

	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (index == -1)
			return false; // Not in the list
		if (checkedOut[index]) {
			checkedOut[index] = false;
			return true;
		}
		return false; // Wasn't checked out
	}
} /// :~
