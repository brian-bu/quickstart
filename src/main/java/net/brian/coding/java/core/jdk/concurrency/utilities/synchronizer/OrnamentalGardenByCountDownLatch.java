package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 
 * CountDownLatch的典型用法是将一个程序分为n个互相独立的可解决任务
 * 并创建值为0的CountDownLatch，当每个任务完成时，都会在这个锁存器上调用countDown()。
 * 等待问题被解决的任务在这个锁存器上调用await()，将它们自己拦住，直至锁存器计数结束
 * 
 * 这里用CountDownLatch解决OrnamentalGarden中Entrance产生的结果相互关联的问题
 * 
 */
class CountByCountDownLatch {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) 
			Thread.yield();
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class EntranceByCountDownLatch implements Runnable {
	private static CountByCountDownLatch count = new CountByCountDownLatch();
	private static List<EntranceByCountDownLatch> entrances = new ArrayList<EntranceByCountDownLatch>();
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;

	public static void cancel() {
		canceled = true;
	}

	public EntranceByCountDownLatch(int id) {
		this.id = id;
		entrances.add(this);
	}

	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("Stopping " + this);
	}

	public synchronized int getValue() {
		return number;
	}

	public String toString() {
		return "EntranceByCountDownLatch " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (EntranceByCountDownLatch entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class OrnamentalGardenByCountDownLatch {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new EntranceByCountDownLatch(i));
		TimeUnit.SECONDS.sleep(3);
		EntranceByCountDownLatch.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.out.println("Some tasks were not terminated!");
		System.out.println("Total: " + EntranceByCountDownLatch.getTotalCount());
		System.out.println("Sum of EntranceByCountDownLatchs: " + EntranceByCountDownLatch.sumEntrances());
	}
}