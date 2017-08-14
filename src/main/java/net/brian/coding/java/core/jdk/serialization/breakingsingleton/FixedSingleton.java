package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.Serializable;

/**
 * 
 * ��������Ҫ�������л������readResolve���������ֻҪ��Singleton�ж���readResolve���������ڸ÷�����ָ��Ҫ���صĶ�������ɲ��ԣ��Ϳ��Է�ʽ�������ƻ���
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