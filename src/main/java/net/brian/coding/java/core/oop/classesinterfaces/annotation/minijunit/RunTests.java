package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 这个类才是揭示注解工作原理的类，是mini junit这里面的几个类里最重要的一个示例类
 * 
 * 利用反射运行标记了注解的类的原理：
 * 测试工具在命令行上使用完全匹配的类名，并通过调用Method.invoke反射式地运行类中所有标注了Test的方法
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
					// 负责捕捉测试方法抛出的异常并打印
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + " failed: " + exc);
				} catch (Exception exc) {
					// 负责捕捉Test用法错误，并打印出相应的错误消息
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
					// 这段代码提取了注解参数的值，并用它检验该测试抛出的异常是否为正确的类型
					Class<? extends Exception>[] excTypes = m.getAnnotation(
							ExceptionTest.class).value();
					// 利用注解中数组参数的语法十分灵活的特点，这段代码负责解析一个定义了若干个异常的数组
					// 使得程序可以在抛出任何一种在数组中指定的异常都能通过测试
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