package net.brian.coding.java.core.jdk.jvm.classloader;

import org.junit.Test;

/**
 * a.�������͵�classʵ���Ļ�ȡ 
 * b.�������͵�classʵ���Ļ�ȡ 
 * c.��̬�����Ӳ���final������
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

	@Test
	public void testPrimitiveClass() {
		Class<?> clazz1 = Boolean.TYPE;
		Class<Boolean> clazz2 = boolean.class;
		System.out.println("clazz1 == clazz2:: " + (clazz1 == clazz2));
	}

	@Test
	public void testObjectClass() {
		Class<?> clazz1 = null;
		try {
			clazz1 = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
		}
		Class<Object> clazz2 = Object.class;
		Class<? extends Object> clazz3 = new Object().getClass();
		System.out.println(
				"clazz1 == clazz2 == clazz3:: " + ((clazz1 == clazz2) && (clazz2 == clazz3) && (clazz1 == clazz3)));
	}

}
