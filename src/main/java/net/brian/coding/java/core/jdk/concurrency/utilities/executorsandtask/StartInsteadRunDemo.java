package net.brian.coding.java.core.jdk.concurrency.utilities.executorsandtask;

//: concurrency/ExceptionThread.java

public class StartInsteadRunDemo {

	public void run() {
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		StartInsteadRunDemo thread = new StartInsteadRunDemo();
		try {
			// ����ֱ�ӵ���run�����Ͳ������Ƕ��߳��ˣ���˰�Runnableע�͵�֮��������ǵ��߳����һ����ͨ�ķ�����
			thread.run();
		} catch (Exception e) {
			System.out.println("hello exception...");
		}
	}
}