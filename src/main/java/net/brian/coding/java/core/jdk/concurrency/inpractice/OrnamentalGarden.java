package net.brian.coding.java.core.jdk.concurrency.inpractice;
// TIJ-4-P693
//: concurrency/OrnamentalGarden.java������ʾ����ֹ���⣬���һ���һ����Դ�����ʾ��
import java.util.concurrent.*;
import java.util.*;

class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		// �⽫�����д�Լһ���ʱ������ò������ȥ��������increment��synchronized�ؼ���
		// �򽫻�����������ͬʱ���ʲ��޸�count��������yield�����������ķ�����
		// �����ճ��򽫻����
		if (rand.nextBoolean()) 
			Thread.yield();
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class Entrance implements Runnable {
	private static Count count = new Count();
	// ���field������Entrance�����ģ��������������һ�μ���������ʱ����ڶ��д��������Ϊentrances��List
	// �����Entrance entrance = new Entrance(1);������ʽ����ôÿ��������һ��entrance���ᴴ��һ�����field
	// ���ֻ�ǵ����ĵ��ù�������new Entrance(1)�������۵��ö��ٴΣ���ֻ������һ��field
	// ʾ�������StaticFieldDemo.java
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	// FIXME: �̣߳���˵final�಻��Ҫ��ͬ��������String��Integer�ȣ��������һ��field����Ϊfinal��Ҳ����Ҫͬ����
	private final int id;
	// volatile����ͨ������ϵ�ʱ�����Ѿ����̰߳�ȫ���ˣ��������canceled���cancel������Ͽ���ȷ��canceled��Ļ�ȡ���̰߳�ȫ��
	// �˻��ƻ�����ͨ��synchronized����cancel����ʵ�֣�������һ������cancel�����Ǿ�̬��������������������ģ��������synchronized��������������൱�������������࣬�����������Ҳ����
	private static volatile boolean canceled = false;

	// Atomic operation on a volatile field:
	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents
		// garbage collection of dead tasks:
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
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class OrnamentalGarden {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Entrance(i));
		// Run for a while, then stop and collect the data:
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.out.println("Some tasks were not terminated!");
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}