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
 * ����ͬ�����³���Ĳ���ȷ��֮��������
 * ��̨�̵߳���s.removeObserver����ͼ����observers�����޷���ø�������Ϊ���߳��Ѿ�������
 * �����ڼ䣬���߳�һֱ�ȴ���̨�߳�����ɶԹ۲��ߵ�ɾ�������������������ԭ��
 * 
 * ����ͬ�����³���Ĳ���ȷ��֮һ����
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.exception.ConcurrentModificationExceptionDemo
 *
 * ע�⣺���б�ʵ�������net.brian.coding.designpatterns.observer.ObservableSet<E>���ж��ڲ�ͬ��notifyElementAdded�����ĵ���
 * �������õ�Ӧ���Ǹ����е�notifyElementAdded2����
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
