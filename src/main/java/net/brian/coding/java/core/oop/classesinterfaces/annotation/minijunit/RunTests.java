package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * �������ǽ�ʾע�⹤��ԭ����࣬��mini junit������ļ�����������Ҫ��һ��ʾ����
 * 
 * ���÷������б����ע������ԭ��
 * ���Թ�������������ʹ����ȫƥ�����������ͨ������Method.invoke����ʽ�������������б�ע��Test�ķ���
 *
 */
public class RunTests {
	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;
		Class<?> testClass = Class.forName(args[0]);
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					// ����׽���Է����׳����쳣����ӡ
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + " failed: " + exc);
				} catch (Exception exc) {
					// ����׽Test�÷����󣬲���ӡ����Ӧ�Ĵ�����Ϣ
					System.out.println("INVALID @Test: " + m);
				}
			}

			// Array ExceptionTest processing code - Page 174
			if (m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("Test %s failed: no exception%n", m);
				} catch (Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					// ��δ�����ȡ��ע�������ֵ������������ò����׳����쳣�Ƿ�Ϊ��ȷ������
					Class<? extends Exception>[] excTypes = m.getAnnotation(
							ExceptionTest.class).value();
					// ����ע��������������﷨ʮ�������ص㣬��δ��븺�����һ�����������ɸ��쳣������
					// ʹ�ó���������׳��κ�һ����������ָ�����쳣����ͨ������
					int oldPassed = passed;
					for (Class<? extends Exception> excType : excTypes) {
						if (excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					if (passed == oldPassed)
						System.out.printf("Test %s failed: %s %n", m, exc);
				}
			}
		}
		System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
	}
}