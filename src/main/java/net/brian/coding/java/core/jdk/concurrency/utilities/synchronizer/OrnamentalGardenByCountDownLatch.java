package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 
 * CountDownLatch�ĵ����÷��ǽ�һ�������Ϊn����������Ŀɽ������
 * ������ֵΪ0��CountDownLatch����ÿ���������ʱ������������������ϵ���countDown()��
 * �ȴ����ⱻ���������������������ϵ���await()���������Լ���ס��ֱ����������������
 * 
 * ������CountDownLatch���OrnamentalGarden��Entrance�����Ľ���໥����������
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