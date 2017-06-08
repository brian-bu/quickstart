package net.brian.coding.java.core.jdk.concurrency.mechanism.exception;

import java.util.HashSet;

import net.brian.coding.designpatterns.observer.ObservableSet;
import net.brian.coding.designpatterns.observer.SetObserver;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item67: Avoid excessive synchronization
 * 
 * 过度同步导致程序的不正确性之一：数据破坏
 * 打印0-23之后观察者并没有取消预订而是抛出了ConcurrentModificationException
 * 本例的问题在于
 * 过度同步导致程序的不正确性之二见：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking.DeadLockingByObservableSet
 *
 * 注意：运行本实例请更改net.brian.coding.designpatterns.observer.ObservableSet<E>类中对于不同的notifyElementAdded方法的调用
 * 本例调用的应该是该类中的notifyElementAdded2方法
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
