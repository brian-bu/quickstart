package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Joining.java
import static java.lang.System.out;

class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	public void run() {
		try {
			// 这里要把休眠时间设置的足够大，就是为了给即将被加入的线程B足够的时间创建线程
			// 否则线程B还没跑起来线程A就结束了，那就无法看到join方法的作用了。
			sleep(duration);
		} catch (InterruptedException e) {
			out.println(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
			return;
		}
		out.println(getName() + " has awakened");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			out.println("Interrupted");
		}
		// 根据控制台的输出可以看出无论是线程A被唤醒还是被打断，线程B的这句都会执行，而且后于线程A
		// 也就是说join的作用就是：线程B本身也要run起来，不过在调用了线程A.join之后
		// 相当于“加入”了线程A，并会先等待A执行完了或被打断退出之后再执行
		out.println(getName() + " join completed");
	}
}

public class JoinAndInterruptDemo {
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500), grumpy = new Sleeper("Grumpy", 1500);
		@SuppressWarnings("unused")
		Joiner dopey = new Joiner("Dopey", sleepy), doc = new Joiner("Doc", grumpy);
		// interrupt如果用于debug，则需要让线程休眠足够长的时间，否则如果线程已经被唤醒并结束，这个时候再调用interrupt方法就没有效果了
		// 如果调用interrupt成功，则对应的线程就会走到catch块里面执行相应的代码，否则就会在休眠时间结束后继续剩下的任务
		grumpy.interrupt();
	}
}