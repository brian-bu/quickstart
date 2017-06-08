package net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item69: Prefer concurrency utilities to wait and notify
 * 
 * 没有理由在新代码中使用wait和notify，如果在维护使用wait和notify的代码，务必确保始终是利用标准的模式从while循环内部调用wait
 * wait()方法之所以应该在循环调用，是因为当线程获取到 CPU开始执行的时候，其他条件可能还没有满足，所以在处理前，循环检测条件是否满足会更好
 * 也即：while循环确保线程调用wait等待之前不会意外的先调用notify/notifyAll，否则notify先于wait调用，则无法保证该线程会从等待中醒来
 * 对于唤醒wait，应该优先使用notifyAll而不是notify
 */
// TIJ并发章节练习21题
public class WaitAndNotifyDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		Runnable1 runnable1 = new Runnable1();
		pool.execute(runnable1);
		// 确保先运行Runnable1
		TimeUnit.SECONDS.sleep(5);
		Runnable2 runnable2 = new Runnable2(runnable1);
		pool.execute(runnable2);
		pool.shutdown();
	}
}

class Runnable1 implements Runnable {

	@Override
	public void run() {
		try {
			// 如果Runnable1和Runnable2共享Runnable1的对象作为锁，那么就一定要确保Runnable1先运行
			// 这里先让Runnable1休眠1秒以确保Runnable2先运行，我们就看到Runnable2先获得锁，然后执行休眠3秒的任务
			// 然后释放锁之后Runnable1才获得该锁，然后进入同步块执行wait方法，但是这时已经没有线程可以notify了
			// 线程2已经执行完毕，所以这个程序会一直wait下去，因此不能简单的以为一定是Runnable1先运行
			// 而是要在Runnable1开始运行之后，等到调用wait方法使得当前的Runnable1线程让出该锁，这样Runnable2才能开始运行
			// TimeUnit.SECONDS.sleep(1);
			synchronized (this) {
				System.out.println("Runnable1 Current Thread:: " + Thread.currentThread().getName());
				System.out.println("Runnable1 this:: " + this);
				wait();
				System.out.println("Runnable1 awaked!");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

class Runnable2 implements Runnable {
	private Runnable1 runnable = null;

	public Runnable2(Runnable1 runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		synchronized (runnable) {
			try {
				System.out.println("Runnable2 Current Thread:: " + Thread.currentThread().getName());
				System.out.println("Runnable2 runnable:: " + runnable);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Waking up Runnable1...");
			runnable.notify();
		}
	}

}