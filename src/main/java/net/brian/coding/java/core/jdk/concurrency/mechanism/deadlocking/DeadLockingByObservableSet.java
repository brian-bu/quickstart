package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.brian.coding.designpatterns.observer.ObservableSet;
import net.brian.coding.designpatterns.observer.SetObserver;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item67: Avoid excessive synchronization
 * 
 * 过度同步导致程序的不正确性之二：死锁
 * 后台线程调用s.removeObserver，企图锁定observers，但无法获得该锁，因为主线程已经有锁了
 * 在这期间，主线程一直等待后台线程来完成对观察者的删除，这正是造成死锁的原因
 * 
 * 过度同步导致程序的不正确性之一见：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.exception.ConcurrentModificationExceptionDemo
 *
 * 注意：运行本实例请更改net.brian.coding.designpatterns.observer.ObservableSet<E>类中对于不同的notifyElementAdded方法的调用
 * 本例调用的应该是该类中的notifyElementAdded2方法
 */
public class DeadLockingByObservableSet {
	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<Integer>(
				new HashSet<Integer>());

		// Observer that uses a background thread needlessly
		set.addObserver(new SetObserver<Integer>() {
			public void added(final ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23) {
					ExecutorService executor = Executors
							.newSingleThreadExecutor();
					final SetObserver<Integer> observer = this;
					try {
						executor.submit(new Runnable() {
							public void run() {
								s.removeObserver(observer);
							}
						}).get();
					} catch (ExecutionException ex) {
						throw new AssertionError(ex.getCause());
					} catch (InterruptedException ex) {
						throw new AssertionError(ex.getCause());
					} finally {
						executor.shutdown();
					}
				}
			}
		});

		for (int i = 0; i < 100; i++)
			set.add(i);
}
}
