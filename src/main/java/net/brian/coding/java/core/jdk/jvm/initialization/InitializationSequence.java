package net.brian.coding.java.core.jdk.jvm.initialization;

/**
 * Java中赋值顺序： 
 * 1. 父类的静态变量赋值
 * 2. 自身的静态变量赋值 
 * =====分割线=====
 * 3. 父类成员变量赋值 
 * 4. 父类块赋值 
 * 5. 父类构造函数赋值 
 * =====分割线=====
 * 6. 自身成员变量赋值 
 * 7. 自身块赋值 
 * 8. 自身构造函数赋值
 * 
 *
 */
public class InitializationSequence extends A {
	/**
	 * 如果没有这行new InitializationSequence();的代码则按照Java中赋值顺序应该有如下输出
	 * A static
	 * InitializationSequence static
	 * 先对两个类进行静态初始化，这个过程需要jvm对两个类进行类加载，首先会先把静态成员load进去
	 * A instantiated
	 * A constructor.
	 * 然后加载父类的所有信息，先是实例化块，然后是构造器，构造器是最后一步，这一步做完这个类和对象就可用了
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * 最后是子类的加载
	 * 
	 * 注意在main方法中无论是InitializationSequence t = new InitializationSequence();还是直接new InitializationSequence();
	 * 其结果都是一样的，也就是说，即便没有声明t而只是单纯的调用构造器，非静态初始化块照样会被执行
	 */
	/**
	 * 如果有这行new InitializationSequence();的代码则按照Java中赋值顺序应该有如下输出
	 * A static
	 * 先对静态块初始化，初始化完A的时候初始化public static InitializationSequence t1 = new InitializationSequence();
	 * 发现需要new InitializationSequence()，构造子类的构造器，需要按照顺序分别执行父类的实例化、构造器、子类的实例化、构造器。
	 * 因此有如下四行结果输出：
	 * A instantiated
	 * A constructor
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * 静态初始化public static InitializationSequence t1 = new InitializationSequence();结束，接下来静态初始化静态代码块
	 * InitializationSequence static
	 * 静态初始化静态代码块
	 * A instantiated
	 * A constructor
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * 再次按照顺序分别执行父类的实例化、构造器、子类的实例化、构造器。
	 */
	 public static InitializationSequence t1 = new InitializationSequence();
	
	/**
	 * 这行代码导致递归调用，不要这样写
	 */
	// public InitializationSequence t2 = new InitializationSequence();
	static {
		System.out.println("InitializationSequence static");
	}
	{
		System.out.println("InitializationSequence instantiated");
	}

	public InitializationSequence() {
		System.out.println("InitializationSequence constructor");
	}

	public static void main(String[] args) {
		new InitializationSequence();
	}
}

class A {
	public A() {
		System.out.println("A constructor");
	}

	{
		System.out.println("A instantiated");
	}
	static {
		System.out.println("A static");
	}

}