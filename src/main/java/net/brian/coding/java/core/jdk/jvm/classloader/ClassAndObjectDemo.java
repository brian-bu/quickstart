package net.brian.coding.java.core.jdk.jvm.classloader;

import org.junit.Test;

import net.brian.coding.designpatterns.singleton.Singleton;
import net.brian.coding.java.utils.entity.DemoBean;
/**
 * 
 * ����javaweb������Ļһ������µĶ���ʼ��ܽ�����Ϊ���仰������Class����أ�
 * a.���Javadeclass��Ϣһ�����ݵķ������ǿ�������ص�API
 * b.���class�ļ��Ľṹ����ʵ��һ����Oolong����javap�Ϳ�����
 *
 */
public class ClassAndObjectDemo {
	// 1��ֱ�ӷŵ�����������ⲿ����øó���ʱ����Ҫ��ʼ����ǰ�࣬��̬������еĴ��벻ִ��֤������һ��
	public static final int i = 0;
	// 2����������ڶ��ڴ棬��Ҫ��ʼ�����ʱ�������ⲿ����øñ���ʱ��̬������еĴ���õ�ִ��֤������һ��
	public static int j = 0;

	static {
		System.out.println("ClassAndObjectDemo -- static block...");
	}

	@SuppressWarnings("static-access")
	public void testStatic() {
		ClassAndObjectDemo t1 = new ClassAndObjectDemo();
		ClassAndObjectDemo t2 = new ClassAndObjectDemo();
		// ��ʹ�������������󣬵���TestArea.iҲֻ��һ�ݴ洢�ռ䣬������������һ��i��Ҳ�������Ա�ǹ��������������ж�����ġ�
		System.out.println(t1.i == t2.i);
		// Ҳ����ͨ������ֱ�����ã�������Ҫ�������ã����ǷǾ�̬����������
		System.out.println(ClassAndObjectDemo.i);
		// ������==��equals������
		System.out.println(t1 == t2);
		System.out.println(t1.equals(t2));
	}

	@Test
	public void testPrimitiveClass() {
		Class<?> clazz1 = Boolean.TYPE;
		Class<Boolean> clazz2 = boolean.class;
		System.out.println("ClassAndObjectDemo -- testPrimitiveClass -- clazz1 == clazz2:: " + (clazz1 == clazz2));
	}

	@Test
	public void testObjectClass() {
		Class<?> clazz1 = null;
		try {
			clazz1 = Class.forName("net.brian.utils.DemoBean");
		} catch (ClassNotFoundException e) {
		}
		Class<DemoBean> clazz2 = DemoBean.class;
		Class<? extends DemoBean> clazz3 = new DemoBean().getClass();
		System.out.println("ClassAndObjectDemo -- testObjectClass -- clazz1 == clazz2:: " + (clazz1 == clazz2));
		System.out.println("ClassAndObjectDemo -- testObjectClass -- clazz2 == clazz3:: " + (clazz2 == clazz3));
		System.out.println("ClassAndObjectDemo -- testObjectClass -- clazz1 == clazz3:: " + (clazz1 == clazz3));
	}

	@SuppressWarnings("unused")
	@Test
	public void testWaysToCreatingObj() {
		Object obj1 = new Object();
		Object obj2 = Singleton.ENUMLOADING;
	}

}
