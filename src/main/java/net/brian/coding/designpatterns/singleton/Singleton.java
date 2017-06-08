package net.brian.coding.designpatterns.singleton;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item03: Enforce the singleton property with a private constructor or an enum type
 * 
 * 本类示范了各种单例模式的变体，其中对于枚举实现的单例：单例本质上是单元素的枚举
 *
 */
public enum Singleton {
	LAZYLOADING(1), HUANGREYLOADING(2), ENUMLOADING(3), STATICINNERCLASS(4), VOLATILELOCK(5);

	Singleton(int type) {
		switch (type) {
		case 1:
			Singleton1.getInstance();
		case 2:
			Singleton3.getInstance();
		case 3:
			Singleton5.whateverMethod();
		case 4:
			Singleton4.getInstance();
		case 5:
			Singleton6.getSingleton();
		}
	}
}

enum B {
	// 括号里面的数据类型和构造器中的数据类型必须保持一致。
	VALUE_B1("B1"), VALUE_B2("B2");

	private String id;

	private B(String id) {

		this.id = id;

	}

	public static B getById(String id) {
		// 可以把实例当作构造器使用来生成新对象。
		return VALUE_B1;
	}

}

// 懒汉模式，延迟加载，线程不安全。
class Singleton1 {
	private static Singleton1 instance;

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}

// 懒汉模式，延迟加载，线程安全。在JDK1.5之后需要双重校验锁的方法才能实现单例。
class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {
	}

	public static synchronized Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}

// 饿汉模式，不会出现懒汉模式的线程安全问题，在类的初始化时就实例化。
class Singleton3 {
	private static Singleton3 instance = new Singleton3();

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		return instance;
	}
}

// 静态内部类方式，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
class Singleton4 {
	private static class SingletonHolder {
		private static final Singleton4 INSTANCE = new Singleton4();
	}

	private Singleton4() {
	}

	public static final Singleton4 getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

// 枚举方式，Effective Java推荐使用，它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
enum Singleton5 {
	INSTANCE;
	public static Singleton5 whateverMethod() {
		return INSTANCE;
	}
}

// 双重校验锁方式
class Singleton6 {
	private volatile static Singleton6 singleton;

	private Singleton6() {
	}

	public static Singleton6 getSingleton() {
		if (singleton == null) {
			synchronized (Singleton6.class) {
				if (singleton == null) {
					singleton = new Singleton6();
				}
			}
		}
		return singleton;
	}
}