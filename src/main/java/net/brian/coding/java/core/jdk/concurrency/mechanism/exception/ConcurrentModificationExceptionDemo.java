package net.brian.coding.java.core.jdk.concurrency.mechanism.exception;

import java.util.HashSet;

import net.brian.coding.designpatterns.observer.ObservableSet;
import net.brian.coding.designpatterns.observer.SetObserver;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item67: Avoid excessive synchronization
 * 
 * ����ͬ�����³���Ĳ���ȷ��֮һ�������ƻ�
 * ��ӡ0-23֮��۲��߲�û��ȡ��Ԥ�������׳���ConcurrentModificationException
 * ��������������
 * ����ͬ�����³���Ĳ���ȷ��֮������
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking.DeadLockingByObservableSet
 *
 * ע�⣺���б�ʵ�������net.brian.coding.designpatterns.observer.ObservableSet<E>���ж��ڲ�ͬ��notifyElementAdded�����ĵ���
 * �������õ�Ӧ���Ǹ����е�notifyElementAdded2����
 */
public class ConcurrentModificationExceptionDemo {
	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23)
					s.removeObserver(this);
			}
		});

		for (int i = 0; i < 100; i++)
			set.add(i);
	}
}
