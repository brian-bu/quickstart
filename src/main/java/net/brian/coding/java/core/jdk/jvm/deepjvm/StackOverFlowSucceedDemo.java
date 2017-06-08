package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class StackOverFlowSucceedDemo {
	public StackOverFlowSucceedDemo() {
		mockConstructorFailed();
	}
	public StackOverFlowSucceedDemo mockConstructorFailed() {
		// 无论什么原因产生构造器和构造器包含的方法之间的互相调用都会抛出StackOverFlowError
		// 没有这句对构造器的调用这句话就不抛Error了，这个方法就相当于构造器，返回的是当前类的引用，构造器也返回当前类的引用。
		// 因此这个方法不能是static的，static方法是属于类的，无法返回当前类的引用
		// System.out.println("this == new StackOverFlowDemo():: " + (this == new StackOverFlowSucceedDemo()));
		return this;
	}

	public StackOverFlowSucceedDemo mockConstructorSucceed() {
		// 无论什么原因产生构造器和构造器包含的方法之间的互相调用都会抛出StackOverFlowError
		return new StackOverFlowSucceedDemo();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StackOverFlowSucceedDemo sofd = new StackOverFlowSucceedDemo();
	}
}
