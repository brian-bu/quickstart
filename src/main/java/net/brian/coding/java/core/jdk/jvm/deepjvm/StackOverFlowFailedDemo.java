package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class StackOverFlowFailedDemo {
	private StackOverFlowFailedDemo() {
		mockConstructorFailed();
	}

	private static final StackOverFlowFailedDemo singleInstance = new StackOverFlowFailedDemo();

	public static StackOverFlowFailedDemo getSingleInstance() {
		return singleInstance;
	}

	public StackOverFlowFailedDemo mockConstructorFailed() {
		// 这里不会有StackOverFlow是因为我们只有这么一个实例用来在内存中申请一个对象，不会产生无穷尽的相互调用
		// FIXME: 初始化：这里返回的是false，为什么呢？不是说构造函数返回的是当前的类的引用么？this不是也是当前的类的引用么？为什么不相等？
		 System.out.println("this == new StackOverFlowDemo():: " + (this == singleInstance));
		return this;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StackOverFlowFailedDemo sofd = new StackOverFlowFailedDemo();
	}
}
