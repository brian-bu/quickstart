package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;
/**
 * 
 * 锁顺序死锁：如果所有线程以固定的顺序来获得锁，那么在程序中就不会出现锁顺序死锁问题
 *
 */
public class DeadLockBySequence {
	private final Object left = new Object();
	private final Object right = new Object();
	public void leftRight() {
		synchronized(left) {
			synchronized(right) {
				doSomething();
			}
		}
	}
	
	private void doSomething() {
	}

	public void rightRight() {
		synchronized(right) {
			synchronized(left) {
				doSomethingElse();
			}
		}
	}

	private void doSomethingElse() {
		
	}
}
