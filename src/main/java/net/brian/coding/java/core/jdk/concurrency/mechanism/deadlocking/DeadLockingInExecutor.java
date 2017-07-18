package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * 本例的教训：每当提交一个有依赖性的Executor任务时，要清楚的知道可能会出现线程“饥饿”死锁
 * 因此需要在代码或配置Executor的配置文件中记录线程池的大小限制或配置限制
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