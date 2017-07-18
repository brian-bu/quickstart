package net.brian.coding.java.core.jdk.jvm;

/**
 * 
 * 本程序摘自How Tomcat Works中文版P246页代码清单16-1，是一个关闭钩子的示例程序:
 * 通过start方法创建一个关闭钩子，然后通过System.in.read()等待用户输入，按下回车键程序退出，退出前执行钩子，效果是输出：Shutting down...
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