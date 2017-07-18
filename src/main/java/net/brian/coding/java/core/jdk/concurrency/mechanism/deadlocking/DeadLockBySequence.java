package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;
/**
 * 
 * ��˳����������������߳��Թ̶���˳�������������ô�ڳ����оͲ��������˳����������
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
