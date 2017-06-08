package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}
	// �����call�����Ƕ�Callable�ӿڶ���ķ�����ʵ��
	// ��������ֵ�Ǹ����ͣ�ע��ͽӿڵ����Ͳ�������һ�£���Callable<String>����������ֵ��Ҫ��String
	@Override
	public String call() {
		return "result of TaskWithResult " + id;
	}
}

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// �ᴩ������������߾����������ע�ͣ�
		// submit - Callable<V> - V call() - Future<V> - ArrayList<Future<V>>.add(Future<V>)
		// ArrayList<Future<V>>.get(i) - Future<V> - V future.get()
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// submit����������Future��������Callable���ؽ�����ض����ͽ����˲�����
			results.add(exec.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : results)
			try {
				// �������isDone������ѯFuture�Ƿ���ɡ����������ʱ��������һ����������Ե���get��������ȡ�ý��
				// ������isDone���м���ֱ�ӵ���get����get��������ֱ�����׼������
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
	}
}