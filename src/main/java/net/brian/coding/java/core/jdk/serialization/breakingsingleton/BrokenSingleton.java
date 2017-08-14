package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.Serializable;
/**
 * 
 * ��������ƻ�����ģʽ�����˷������⣬ʹ�����л��뷴���л�Ҳͬ�����ƻ�����
 * �����л�Ҳ��ͨ����������޲����Ĺ��췽������һ���µĶ���
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
