package net.brian.coding.java.core.jdk.newfeatures;

/**
 * 
 * 回调机制是一种常见的设计模式，它把工作流内的某个过程方法按照约定的接口暴露给外部使用者
 * 为外部使用者提供数据，或要求外部使用者提供数据。
 * 
 * 从调用方式上，可以把他们分为三类：同步调用、回调、异步调用。
 * 同步调用：一种阻塞式调用，调用方要等待对方执行完毕才能返回，它是一种单向调用；
 * 回调：一种双向调用模式，也就是说，被调用方在接口被调用时也会调用对方的接口
 * 异步调用：一种类似消息或事件的机制，不过它的调用方向刚好相反
 * 接口的服务在收到某种讯息或发生某种事件时，会主动通知客户方（即调用客户方的接口）。
 * 回调和异步调用的关系非常紧密：使用回调来实现异步消息的注册，通过异步调用来实现消息的通知。
 * 
 * 这是一个普通的回调代替模板模式的例子： 
 * 回调这种方式摒弃了模板模式继承抽象类的方式，因此更加简便灵活
 * 不需要为了实现抽象方法而总是继承抽象类，而是只需要通过回调来增加一个方法即可
 * 
 * 注意：A发送消息给B，B处理好A要求的事情后，将结果返回给A，A再对B返回的结果来做进一步的处理。
 *
 */
public class CallbackBetterThanTemplate implements CallBack, Runnable {

	/**
	 * 远程接收消息的类，模拟point-to-point
	 */
	private Remote remote;
	private String message;

	public CallbackBetterThanTemplate(Remote remote, String message) {
		super();
		this.remote = remote;
		this.message = message;
	}

	public void sendMessage() {
		System.out.println(Thread.currentThread().getName());
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("Message has been sent by Local~!");
	}
	
	@Override
	public void execute(String msg) {
		System.out.println(msg);
		System.out.println(Thread.currentThread().getName());
		Thread.interrupted();
	}

	public static void main(String[] args) {
		CallbackBetterThanTemplate local = new CallbackBetterThanTemplate(new Remote(), "Hello");
		local.sendMessage();
	}

	@Override
	public void run() {
		// 如果这里不是用this，而是用其他的CallBack接口的实现类的话，那就不能称之为“回调”了
		// 在OO的世界里，那就属于“委派”。也就是说，“回调”必须是消息的发送者来处理消息结果
		remote.executeMessage(message, this);
	}
}

interface CallBack {
	/**
	 * 回调函数包含下面几个特性：
	 * 属于工作流的一个部分；
	 * 必须按照工作流指定的调用约定来申明（定义）；
	 * 他的调用时机由工作流决定，回调函数的实现者不能直接调用回调函数来实现工作流的功能；
	 * 
	 * @param msg
	 */
	void execute(String msg);
}

class Remote {
	public void executeMessage(String msg, CallBack callBack) {
		/** 模拟远程类正在处理其他事情，可能需要花费许多时间 **/
		for (int i = 0; i < 1000000000; i++) {

		}
		System.out.println("I hava executed the message by Local");
		/** 执行回调 **/
		callBack.execute(msg);
	}
}