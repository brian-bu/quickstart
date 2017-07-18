package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * �����Ľ�ѵ��ÿ���ύһ���������Ե�Executor����ʱ��Ҫ�����֪�����ܻ�����̡߳�����������
 * �����Ҫ�ڴ��������Executor�������ļ��м�¼�̳߳صĴ�С���ƻ���������
 *
 */
public class DeadLockingInExecutor {
	ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public class RenderPageTask implements Callable<String> {
		public String call() throws Exception {
			Future<String> header, footer;
			header  = exec.submit(new LoadFileTask("header.html"));
			footer  = exec.submit(new LoadFileTask("footer.html"));
			String page = rederBody();
			// dead locking here
			return header.get() + page + footer.get();
		}

		private String rederBody() {
			return null;
		}
	}
}
class LoadFileTask implements Callable<String> {
	public LoadFileTask(String path) {
		System.out.println("wait a min...");
	}

	@Override
	public String call() throws Exception {
		return null;
	}
}