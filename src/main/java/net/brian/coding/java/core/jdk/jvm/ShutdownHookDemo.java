package net.brian.coding.java.core.jdk.jvm;

/**
 * 
 * ������ժ��How Tomcat Works���İ�P246ҳ�����嵥16-1����һ���رչ��ӵ�ʾ������:
 * ͨ��start��������һ���رչ��ӣ�Ȼ��ͨ��System.in.read()�ȴ��û����룬���»س��������˳����˳�ǰִ�й��ӣ�Ч���������Shutting down...
 *
 */
public class ShutdownHookDemo {
	public void start() {
		System.out.println("Demo");
		ShutdownHook shutdownHook = new ShutdownHook();
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}

	public static void main(String[] args) {
		ShutdownHookDemo demo = new ShutdownHookDemo();
		demo.start();
		try {
			System.in.read();
		} catch (Exception e) {
			// No exception required here.
		}
	}
}

class ShutdownHook extends Thread {
	@Override
	public void run() {
		System.out.println("Shutting down...");
	}
}