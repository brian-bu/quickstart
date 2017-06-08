package net.brian.coding.designpatterns.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item67: Avoid excessive synchronization
 * 
 * 实现了一个可以观察到的集合包装，本类允许客户端在将元素添加到集合中时预定通知，这就是一个观察者模式
 * 
 * 为了避免活性失败和安全性失败，在一个被同步的方法或者代码块中，永远不要放弃对客户端的控制
 * 换句话说，在一个被同步的区域内部，不要调用设计成被覆盖的方法，或者是由客户端以函数的形式提供的方法
 * 从包含该同步区域的类的角度来看，这样的方法时外来的。这个类不知道该方法会做什么事情，也无法控制它
 * 根据外来方法的作用，从同步区域中调用它会导致异常，死锁或者数据损坏
 * 
 */
public class ObservableSet<E> extends ForwardingSet<E> {
	public ObservableSet(Set<E> set) {
		super(set);
	}

	private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}

	// This method is the culprit
	private void notifyElementAdded(E element) {
		synchronized (observers) {
			for (SetObserver<E> observer : observers)
				observer.added(this, element);
		}
	}

	/**
	 *  Alien method moved outside of synchronized block - open calls - Page 268
	 *  过度同步导致程序的不正确性之一：数据破坏
	 *  @see net.brian.coding.java.core.jdk.concurrency.mechanism.exception.ConcurrentModificationExceptionDemo
	 *  过度同步导致程序的不正确性之二：死锁
	 *  @see net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking.DeadLockingByObservableSet
	 * @param element
	 */
	@SuppressWarnings("unused")
	private void notifyElementAdded2(E element) {
		List<SetObserver<E>> snapshot = null;
		synchronized (observers) {
			snapshot = new ArrayList<SetObserver<E>>(observers);
		}
		for (SetObserver<E> observer : snapshot)
			observer.added(this, element);
	}

	/**
	 *  Thread-safe observable set with CopyOnWriteArrayList - Page 269
	 *  将外来方法的调用移出同步代码块进而避免过度同步：利用CopyOnWriteArrayList
	 *  CopyOnWriteArrayList是ArrayList的一种变体，通过重新拷贝整个底层数组，在这里实现所有的写操作
	 *  由于内部数组永远不改动，因此迭代不需要锁定，速度也非常快
	 */
	private final List<SetObserver<E>> observers3 = new CopyOnWriteArrayList<SetObserver<E>>();

	public void addObserver3(SetObserver<E> observer) {
		observers3.add(observer);
	}

	public boolean removeObserver3(SetObserver<E> observer) {
		return observers3.remove(observer);
	}

	@SuppressWarnings("unused")
	private void notifyElementAdded3(E element) {
		for (SetObserver<E> observer : observers3)
			observer.added(this, element);
	}

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added) {
			// @see net.brian.coding.designpatterns.observer.NormalTest
			notifyElementAdded(element);
			//notifyElementAdded2(element);
			//notifyElementAdded3(element);
		}
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c)
			result |= add(element); // calls notifyElementAdded
		return result;
	}
}