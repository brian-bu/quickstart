package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.Serializable;

/**
 * 
 * 反射会调用要被反序列化的类的readResolve方法，因此只要在Singleton中定义readResolve方法，并在该方法中指定要返回的对象的生成策略，就可以方式单例被破坏。
 *
 */
public class FixedSingleton implements Serializable{
	private static final long serialVersionUID = 1L;
	private volatile static FixedSingleton singleton;
    private FixedSingleton (){}
    public static FixedSingleton getSingleton() {
        if (singleton == null) {
            synchronized (FixedSingleton.class) {
                if (singleton == null) {
                    singleton = new FixedSingleton();
                }
            }
        }
        return singleton;
    }
 
    private Object readResolve() {
        return singleton;
    }
}