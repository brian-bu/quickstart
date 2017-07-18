package net.brian.coding.java.core.jdk.jvm.initialization;

/**
 * Java中赋值顺序： 
 * 1. 父类的静态变量赋值
 * 2. 自身的静态变量赋值 
 * 3. 父类成员变量赋值 
 * 4. 父类块赋值 
 * 5. 父类构造函数赋值 
 * 6. 自身成员变量赋值 
 * 7. 自身块赋值 
 * 8. 自身构造函数赋值
 * 
 * 类的初始化会从祖先类到子类、按出现顺序，对类变量的初始化语句、静态初始化语句块依次进行初始化
 * 而对类实例的初始化也类似，会从祖先类到子类、按出现顺序，对类成员的初始化语句、实例初始化块、构造方法依次进行初始化
 * 本例包含上述所有的情况并展示了每一个部分在初始化中的顺序：
 * (1).首先T类被加载、连接后进行初始化，会先对字段k、t1、t2、i、n以及static块进行初始化。
 * (2).t1实例的初始化会初始化实例成员j，(实际上先进行父类实例内容的初始化)先调用静态方法print，并执行实例初始化块{}，输出： 1: j i=0
 * n= 0(i和n都还没有初始化) 2:构造块 i=1 n=1 (3)随后调用t1实例的构造函数，输出： 3:t1 i=2 n=2
 * (4).类似有t2实例的初始化： 4: j i=3 n= 3 5:构造块 i=4 n=4 6:t2 i=5 n=5 (5).i的初始化： 7.i i=6
 * n=6 (6).n的初始化和静态块的初始化： 8.静态块 i=7 n=99(n已经被初始化) (7).t实例的初始化： 9.j i=8 n= 100
 * 10.构造块 i=9 n= 101 11.init i=10 n= 102
 *
 */
public class InitializationSequence implements Cloneable {
	public static int k = 0;
	public static InitializationSequence t1 = new InitializationSequence("t1");
	public static InitializationSequence t2 = new InitializationSequence("t2");
	public static int i = print("i");
	public static int n = 99;

	public int j = print("j");
	{
		print("构造块");
	}

	static {
		print("静态块");
	}

	public InitializationSequence(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		++i;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		return ++i;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		InitializationSequence t = new InitializationSequence("init");
	}
}