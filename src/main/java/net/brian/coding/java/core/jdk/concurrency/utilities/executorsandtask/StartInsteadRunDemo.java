package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

//: concurrency/ExceptionThread.java

public class StartInsteadRunDemo {

	public void run() {
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		StartInsteadRunDemo thread = new StartInsteadRunDemo();
		try {
			// 这里直接调用run方法就不能算是多线程了，因此把Runnable注释掉之后发现这就是单线程里的一个普通的方法。
			thread.run();
		} catch (Exception e) {
			System.out.println("hello exception...");
		}
	}
}