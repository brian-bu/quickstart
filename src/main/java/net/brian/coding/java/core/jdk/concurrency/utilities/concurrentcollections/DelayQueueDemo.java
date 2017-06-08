package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

//: concurrency/DelayQueueDemo.java
import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

/**
 * 
 * 无界的BlockingQueue，用于放置实现了Delayed接口的对象
 * 这种类型的队列的作用：其中的对象只能在到期才能从队列取走
 * 这种队列是有序的，队头对象延迟到期的时间最长
 * 
 * 本例中Delayed对象自身就是任务，而DelayedTaskConsumer会将最紧急的任务
 * 也就是到期时间最长的任务从队列中取出并运行
 * 因此DelayQueue还可以看作是优先级队列的一种变体
 * 
 * jdk源码中有public class DelayQueue<E extends Delayed> extends AbstractQueue<E> implements BlockingQueue<E>
 * 这首先说明DelayQueue是一个BlockingQueue，其次也说明了Point 0处的DelayedTask一定要实现Delayed这个接口
 * 而实现Delayed这个接口就一定要实现getDelay方法，而这个方法决定了这个队列中任务执行的先后顺序，这一切知识点就串起来了
 *
 */

public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		// Point 0
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// Point 1：这里是任务添加的顺序
		// Fill with tasks that have random delays:
		for (int i = 0; i < 20; i++)
			queue.put(new DelayedTask(rand.nextInt(5000)));
		// Point 2: Set the stopping point
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));
	}
}

class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	// 这个List保存了任务被创建的顺序，也就是add语句添加元素的顺序
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
		// 在Point 1处的代码将会不断向List里面add元素DelayedTask
		sequence.add(this);
	}
	
	// OOP：这个方法是Delayed接口中的方法，也是策略设计模式的体现，算法的一部分是作为参数传进来的
	// unit作为参数传进来，用它将当前时间与触发时间之间的差转换为调用者要求的单位，无需知道这些单位是什么
	// 它可以用来告知延迟到期有多长时间，或者延迟在多长时间之前已经到期
	// 这是整个程序的关键，正是这个方法为任务的执行提供了顺序，也正是这个方法导致任务的添加顺序和执行顺序不一致
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}

	public int compareTo(Delayed arg) {
		DelayedTask that = (DelayedTask) arg;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	public void run() {
		System.out.println(this + " ");
	}

	public String toString() {
		// 学习一下这种打log的方法：[4258] Task 19 
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}
	// 这个嵌套类提供了一种关闭所有事物的途径，具体做法就是在Point 2处，将其放置为队列的最后一个元素
	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;

		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			exec = e;
		}

		public void run() {
			for (DelayedTask pt : sequence) {
				System.out.println(pt.summary() + " ");
			}
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted())
				// 可用于运行DelayQueue这个队列中的所有DelayTask
				q.take().run(); // Run task with the current thread
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
}