package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * @see net.brian.coding.java.core.jdk.jvm.initialization.InitializationSequence
 * 
 * 对于InitializationSequence的继续讨论：当只有一个类的时候的初始化顺序：
 * 先static，然后实例化，然后构造器
 * 无论是Client端调用还是static显式初始化的时候调用，只要调用了构造器就会对应先非静态初始化块再构造器的调用
 * 因此首先本类最开始的两个显式静态初始化调用两次构造器，输出：
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 * 接下来开始轮到static静态初始化块，输出：Static InitializationSequence2
 * 最后输出main函数中调用的构造器对应的输出：
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 *
 */
public class InitializationSequence2 {
	public static InitializationSequence2 t1 = new InitializationSequence2();
	public static InitializationSequence2 t2 = new InitializationSequence2();
	 {
		System.out.println("Instantiate InitializationSequence2");
	}
	static {
		System.out.println("Static InitializationSequence2");
	}

	public InitializationSequence2() {
		System.out.println("Construct InitializationSequence2");
	}

	public static void main(String[] args) {
		new InitializationSequence2();
	}
}
