package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

//: concurrency/ThreadVariations.java
// Creating threads with inner classes.
import java.util.concurrent.*;

/**
 * 内部类线程
 */

// Using a named inner class:
class InnerThread1 {
	private int countDown = 5;
	@SuppressWarnings("unused")
	private Inner inner;

	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0)
						return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}

		public String toString() {
			return getName() + ": " + countDown;
		}
	}

	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}

// Using an anonymous inner class:
class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}

			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

// Using a named Runnable implementation:
class InnerRunnable1 {
	private int countDown = 5;
	@SuppressWarnings("unused")
	private Inner inner;

	private class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0)
						return;
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("sleep() interrupted");
			}
		}

		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}

// Using an anonymous Runnable implementation:
class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;

	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}

			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		}, name);
		t.start();
	}
}

// 展示了在方法内部如何创建线程，当你准备好运行线程时就可以调用该方法
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}
	// 线程开始之后该方法将返回，该线程只是执行一些辅助操作而不是这个类的核心工作
	// 也就是这个类并不是为了线程而生，但是确实会在某些操作中用到线程，这个时候就没必要在构造器中使用线程
	// 这个时候就可以写一个方法专门执行线程相关的操作
	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0)
								return;
							sleep(10);
						}
					} catch (InterruptedException e) {
						System.out.println("sleep() interrupted");
					}
				}

				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}
}

public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}
} 