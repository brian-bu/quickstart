package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}
	// 这里的call方法是对Callable接口定义的方法的实现
	// 方法返回值是个泛型，注意和接口的类型参数保持一致，即Callable<String>，方法返回值就要是String
	@Override
	public String call() {
		return "result of TaskWithResult " + id;
	}
}

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// 贯穿整个代码的主线就是下面这句注释：
		// submit - Callable<V> - V call() - Future<V> - ArrayList<Future<V>>.add(Future<V>)
		// ArrayList<Future<V>>.get(i) - Future<V> - V future.get()
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// submit方法将产生Future对象，它用Callable返回结果的特定类型进行了参数化
			results.add(exec.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : results)
			try {
				// 你可以用isDone方法查询Future是否完成。当任务完成时，它具有一个结果，可以调用get方法来获取该结果
				// 若不用isDone进行检查就直接调用get，则get将被阻塞直至结果准备就绪
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