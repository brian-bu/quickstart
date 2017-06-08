package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

//: concurrency/ThreadVariations.java
// Creating threads with inner classes.
import java.util.concurrent.*;

/**
 * �ڲ����߳�
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

// չʾ���ڷ����ڲ���δ����̣߳�����׼���������߳�ʱ�Ϳ��Ե��ø÷���
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}
	// �߳̿�ʼ֮��÷��������أ����߳�ֻ��ִ��һЩ�������������������ĺ��Ĺ���
	// Ҳ��������ಢ����Ϊ���̶߳���������ȷʵ����ĳЩ�������õ��̣߳����ʱ���û��Ҫ�ڹ�������ʹ���߳�
	// ���ʱ��Ϳ���дһ������ר��ִ���߳���صĲ���
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