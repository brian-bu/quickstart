package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item68: Prefer executors and tasks to threads
 * 
 * 对于“Prefer executors and tasks to threads”：
 * a.其中executors指的是Executors下定义的一些API
 * 比如，Executors.newSingleThreadExecutor()创建单一线程的线程池，此外还有：
 * Executors.newCachedThreadPool()可作为小程序或者轻载服务器使用，它的特点是创建一部分线程缓存在线程池中
 * 在这个缓存的线程池中，被提交的任务没有排成队列，而是直接交给线程执行，如果没有线程可用则创建新的线程
 * 对于大负载服务器就会很糟，大负载服务器更适合于用Executors.newFixedThreadPool()来提供固定线程数目的线程池
 * b.其中tasks是指：Runnable和Callable，二者很相似，但是Callable允许任务结束后有返回值，见：
 * @see net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask.CallableDemo
 *
 * 对于Executors框架看Executors的源码，发现其内部是通过工作队列LinkedBlockingQueue实现的，关于工作队列的讨论详见：
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.waitnotify.ProducerConsumerByWaitNotify
 *
 * 总之，不仅应该尽量不去编写自己的工作队列而是使用Executors框架，而且也尽量不要去使用Thread创建线程
 * 之前，对于Thread，它既是工作单元，又是执行机制。
 * 现在，Thread仅作为执行机制，也就是可以给Thread传Callable或者Runnable类型的参数作为线程的执行机制
 * 但是对于工作单元，已经不再推荐使用Thread，而是使用任务，也就是“Prefer executors and tasks to threads”中的tasks
 *
 */
public class ThreadAndRunnableDemo {
	public static void main(String[] args) {
		// 利用Thread继承类生成的对象，用到了Thread的构造器，构造器中有init方法可以创建线程
		// 由于就是Thread，所以可以直接调用start方法，但是缺点是需要直接继承Thread
		ThreadDemo threadDemo = new ThreadDemo();
		// 这个try-catch块也是形同虚设，代码将永远不能走到catch块里
		try{
			threadDemo.start();
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		
		// 利用Runnable创建线程其实还是要用到Thread类的构造器，但是这次不必再继承Thread
		RunnableDemo runnableDemo = new RunnableDemo();
		// 下面两个方式都可以run一个线程，是Runnable实现类创建线程的两种不同方式
		// 但是问题在于这个try-catch，它将永远不会执行catch块里的语句而是直接向外界抛出异常
		try{
			new Thread(runnableDemo).start();
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		try{
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(runnableDemo);
		} catch (RuntimeException rte) {
			System.err.println("Hello Exception!");
		}
		// 需要通过Thread的setDefaultUncaughtExceptionHandler设置异常输出
		// 可以向这个方法传入一个Thread.UncaughtExceptionHandler的实现类作为参数
		// 可以新建一个类，也可以像下面这样创建一个匿名的类
		// 有了这句代码，就可以正确处理异常了，处理方法写在方法uncaughtException中
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			@Override
			public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
				System.out.println("Hello Exception!");
			}});
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(runnableDemo);
	}
}
class RunnableDemo implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}
class ThreadDemo extends Thread {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}