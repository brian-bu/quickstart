package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.
import java.util.concurrent.*;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item69: Prefer concurrency utilities to wait and notify
 * 
 * TIJ��������wait��notifyʵ�������������ߵ����ӣ�Effective Java�Ƽ��ò������������wait��notify���ֵ������ֵ�ʵ�ַ�ʽ
 * ��Ϊ��ȷ��ʹ��wait��notify���ѣ�Ӧ��ʹ�ø��߼��Ĳ������������棬���ڸ��߼��Ĳ������ߴ��·�Ϊ���ࣺ
 * a.Executor @see net.brian.coding.java.core.jdk.concurrency.threadpool.executorandtask.ThreadAndRunnableDemo
 * 
 * b.��������(Concurrent Collections)��Ϊ��׼�ļ��Ͻӿڣ�List��Queue��Map���ṩ�����ܵĲ���ʵ�֣����Ƕ����ڲ�ʵ����ͬ����������౾������
 * ����ʹ�ò������ϣ���ConcurrentHashMap��������ͬ�����ϣ���Hashtable�����Դ����������Ӧ�ó��������
 * ��Щ����������������������չ�����ǻ�һֱ�ȴ������������Գɹ�ִ��Ϊֹ
 * ����BlockingQueue�����Ӷ�����ɾ��������ͷԪ�أ��������Ϊ�վ͵ȴ������������������������ڹ������У�Ҳ���������������߶���
 * һ�����߶���������߳��ڹ�����������ӹ�����Ŀ����������Ŀ����ʱ��һ�����߶���������߳���ӹ���������ȡ�����в���������Ŀ
 * �����ExecutorService��ʵ�ֶ�ʹ��BlockingQueue
 * ������һ����LinkedBlockingQueueʵ�ֵĹ������У������
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections.ProducerConsumerByLinkedBlockingQueue
 * 
 * c.ͬ������Synchronizer����һЩʹ�̵߳ȴ���һ���̵߳Ķ�����������Э������
 * ��õ�ͬ������CountDownLatch��Semaphore�������õ���CyclicBarrier��Exchanger
 * ������Щͬ��������ϸʹ�ã�
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.ExchangerDemo
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.HorseRaceByCyclicBarrier
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.OrnamentalGardenByCountDownLatch
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer.PhilosophersMealBySemaphore
 *
 */
public class ProducerConsumerByWaitNotify {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public ProducerConsumerByWaitNotify() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) {
		new ProducerConsumerByWaitNotify();
	}
}

class Chef implements Runnable {
	private ProducerConsumerByWaitNotify restaurant;
	private int count = 0;

	public Chef(ProducerConsumerByWaitNotify r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null)
						wait(); // ... for the meal to be taken
				}
				if (++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.println("Order up! ");
				// wait��notify��notifyAll��ִ�б���Ҫ��ͬ����������ͬ��������У�ִ��ǰ�����ȡ������
				// ����ͻ��׳�IllegalMonitorStateException�쳣
				// ���ڱ���notifyAll�ĵ��ñ����Ȳ���waiPerson�Ķ���������Point1����wait�����ĵ���ǡǡ���ͷ������
				// ������ȷ����Ϊ�˻���ĳ����������Ȼ�ȡ�����������ͬ��notifyAll����������ͬʱ��ȡͬһ�Ѷ�����
				// ��˲�ͬ��notifyAll�Ļ��Ѳ��������������ͻ
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					// �����notifyAll��Ϊ�˻�ȡWaitPerson�Ķ�������Ҳ����Point1���Ķ�����
					// ��Ϊ�������кܶ����񶼽�������ͬ���飬��ȡ��Point1���Ķ�������ִ��wait���ͷŶ���������ȴ�״̬
					// ���ԣ�����notifyAll��notify����ȫһЩ����Ϊ�������Ի��ѵȴ���ǰ�Ķ���������������
					// ��ÿ�����񶼱���������֪ͨ�Ƿ����Լ���أ������������Ǹ��Զ����Լ��Ķ�����
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}

class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {
	private ProducerConsumerByWaitNotify restaurant;

	public WaitPerson(ProducerConsumerByWaitNotify r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Point1
				synchronized (this) {
					while (restaurant.meal == null) {
						// ����ܿ���ĳ��������������WaitPerson������ʱͻȻ���㲢���߶���
						// Ψһ��ȫ�ķ�ʽ����ʹ��wait�Ĺ��÷���while����wait������ѯ
						// ����Ҫȷ����ѯ��ǡ����ͬ�����ڲ�����ֹ��ʧ�źŵĿ�����
						wait(); // ... for the chef to produce a meal
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll(); // Ready for another
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}