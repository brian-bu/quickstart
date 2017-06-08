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
 * ʵ����һ�����Թ۲쵽�ļ��ϰ�װ����������ͻ����ڽ�Ԫ����ӵ�������ʱԤ��֪ͨ�������һ���۲���ģʽ
 * 
 * Ϊ�˱������ʧ�ܺͰ�ȫ��ʧ�ܣ���һ����ͬ���ķ������ߴ�����У���Զ��Ҫ�����Կͻ��˵Ŀ���
 * ���仰˵����һ����ͬ���������ڲ�����Ҫ������Ƴɱ����ǵķ������������ɿͻ����Ժ�������ʽ�ṩ�ķ���
 * �Ӱ�����ͬ���������ĽǶ������������ķ���ʱ�����ġ�����಻֪���÷�������ʲô���飬Ҳ�޷�������
 * �����������������ã���ͬ�������е������ᵼ���쳣����������������
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
	 *  ����ͬ�����³���Ĳ���ȷ��֮һ�������ƻ�
	 *  @see net.brian.coding.java.core.jdk.concurrency.mechanism.exception.ConcurrentModificationExceptionDemo
	 *  ����ͬ�����³���Ĳ���ȷ��֮��������
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
	 *  �����������ĵ����Ƴ�ͬ�����������������ͬ��������CopyOnWriteArrayList
	 *  CopyOnWriteArrayList��ArrayList��һ�ֱ��壬ͨ�����¿��������ײ����飬������ʵ�����е�д����
	 *  �����ڲ�������Զ���Ķ�����˵�������Ҫ�������ٶ�Ҳ�ǳ���
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