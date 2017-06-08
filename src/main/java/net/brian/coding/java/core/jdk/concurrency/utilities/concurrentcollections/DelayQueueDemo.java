package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

//: concurrency/DelayQueueDemo.java
import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

/**
 * 
 * �޽��BlockingQueue�����ڷ���ʵ����Delayed�ӿڵĶ���
 * �������͵Ķ��е����ã����еĶ���ֻ���ڵ��ڲ��ܴӶ���ȡ��
 * ���ֶ���������ģ���ͷ�����ӳٵ��ڵ�ʱ���
 * 
 * ������Delayed��������������񣬶�DelayedTaskConsumer�Ὣ�����������
 * Ҳ���ǵ���ʱ���������Ӷ�����ȡ��������
 * ���DelayQueue�����Կ��������ȼ����е�һ�ֱ���
 * 
 * jdkԴ������public class DelayQueue<E extends Delayed> extends AbstractQueue<E> implements BlockingQueue<E>
 * ������˵��DelayQueue��һ��BlockingQueue�����Ҳ˵����Point 0����DelayedTaskһ��Ҫʵ��Delayed����ӿ�
 * ��ʵ��Delayed����ӿھ�һ��Ҫʵ��getDelay����������������������������������ִ�е��Ⱥ�˳����һ��֪ʶ��ʹ�������
 *
 */

public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		// Point 0
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// Point 1��������������ӵ�˳��
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
	// ���List���������񱻴�����˳��Ҳ����add������Ԫ�ص�˳��
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
		// ��Point 1���Ĵ��뽫�᲻����List����addԪ��DelayedTask
		sequence.add(this);
	}
	
	// OOP�����������Delayed�ӿ��еķ�����Ҳ�ǲ������ģʽ�����֣��㷨��һ��������Ϊ������������
	// unit��Ϊ��������������������ǰʱ���봥��ʱ��֮��Ĳ�ת��Ϊ������Ҫ��ĵ�λ������֪����Щ��λ��ʲô
	// ������������֪�ӳٵ����ж೤ʱ�䣬�����ӳ��ڶ೤ʱ��֮ǰ�Ѿ�����
	// ������������Ĺؼ��������������Ϊ�����ִ���ṩ��˳��Ҳ�����������������������˳���ִ��˳��һ��
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
		// ѧϰһ�����ִ�log�ķ�����[4258] Task 19 
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}
	// ���Ƕ�����ṩ��һ�ֹر����������;������������������Point 2�����������Ϊ���е����һ��Ԫ��
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
				// ����������DelayQueue��������е�����DelayTask
				q.take().run(); // Run task with the current thread
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
}