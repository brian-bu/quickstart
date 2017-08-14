package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.Serializable;
/**
 * 
 * 反射可以破坏单例模式，除了反射以外，使用序列化与反序列化也同样会破坏单例
 * 而序列化也是通过反射调用无参数的构造方法创建一个新的对象
 *
 */
public class BrokenSingleton implements Serializable {
	private static final long serialVersionUID = 1L;
	private volatile static BrokenSingleton singleton;

	private BrokenSingleton() {
	}

	public static BrokenSingleton getSingleton() {
		if (singleton == null) {
			synchronized (BrokenSingleton.class) {
				if (singleton == null) {
					singleton = new BrokenSingleton();
				}
			}
		}
		return singleton;
	}
}
