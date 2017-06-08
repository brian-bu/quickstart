package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.
import java.util.concurrent.*;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item69: Prefer concurrency utilities to wait and notify
 * 
 * TIJ给出的用wait和notify实现生产者消费者的例子，Effective Java推荐用并发工具类代替wait、notify这种刀耕火种的实现方式
 * 因为正确的使用wait和notify很难，应该使用更高级的并发工具来代替，对于更高及的并发工具大致分为三类：
 * a.Executor @see net.brian.coding.java.core.jdk.concurrency.threadpool.executorandtask.ThreadAndRunnableDemo
 * 
 * b.并发集合(Concurrent Collections)：为标准的集合接口（List、Queue、Map）提供高性能的并发实现，它们都是内部实现了同步，无需对类本身锁定
 * 优先使用并发集合（如ConcurrentHashMap）而不是同步集合（如Hashtable）可以大幅提升并发应用程序的性能
 * 有些集合甚至利用阻塞进行扩展，它们会一直等待或阻塞到可以成功执行为止
 * 比如BlockingQueue：它从队列中删除并返回头元素，如果队列为空就等待。这样就允许将阻塞队列用于工作队列，也就是生产者消费者队列
 * 一个或者多个生产者线程在工作队列中添加工作项目，当工作项目可用时，一个或者多个消费者线程则从工作队列中取出队列并处理工作项目
 * 大多数ExecutorService的实现都使用BlockingQueue
 * 这里有一个用LinkedBlockingQueue实现的工作队列，详见：
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections.ProducerConsumerByLinkedBlockingQueue
 * 
 * c.同步器（Synchronizer）：一些使线程等待另一个线程的对象，允许它们协调动作
 * 最常用的同步器有CountDownLatch和Semaphore，不常用的是CyclicBarrier和Exchanger
 * 对于这些同步器的详细使用：
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
				// wait、notify和notifyAll的执行必须要在同步方法或者同步代码块中，执行前必须获取对象锁
				// 否则就会抛出IllegalMonitorStateException异常
				// 对于本例notifyAll的调用必须先捕获waiPerson的对象锁，而Point1处的wait方法的调用恰恰能释放这把锁
				// 这样就确保了为了唤醒某个任务必须先获取其对象锁，不同的notifyAll方法不可能同时获取同一把对象锁
				// 因此不同的notifyAll的唤醒操作并不会产生冲突
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					// 这里的notifyAll是为了获取WaitPerson的对象锁，也就是Point1处的对象锁
					// 因为：可能有很多任务都进入过这个同步块，获取了Point1处的对象锁，执行wait并释放对象锁进入等待状态
					// 所以：调用notifyAll比notify更安全一些，因为这样可以唤醒等待当前的对象锁的所有任务
					// 而每个任务都必须决定这个通知是否与自己相关，具体做法就是各自定义自己的对象锁
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
						// 这里很可能某个其它的任务在WaitPerson被唤醒时突然插足并拿走订单
						// 唯一安全的方式就是使用wait的惯用法：while包裹wait进行轮询
						// 这里要确保轮询在恰当的同步块内部并防止错失信号的可能性
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