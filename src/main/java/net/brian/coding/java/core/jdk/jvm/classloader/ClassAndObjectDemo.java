package net.brian.coding.java.core.jdk.jvm.classloader;

import org.junit.Test;

/**
 * a.基本类型的class实例的获取 
 * b.引用类型的class实例的获取 
 * c.静态变量加不加final的区别
 * 
 * 对于javaweb技术内幕一书第五章的读书笔记总结提炼为两句话，都和Class类相关：
 * a.理解Javadeclass信息一个最快捷的方法就是看反射相关的API 
 * b.理解class文件的结构，其实不一定用Oolong，用javap就可以了
 *
 */
public class ClassAndObjectDemo {
	// 1、直接放到常量池里，从外部类调用该常量时不需要初始化当前类，静态代码块中的代码不执行证明了这一点
	public static final int i = 0;
	// 2、变量存放在堆内存，需要初始化类的时候处理，从外部类调用该变量时静态代码块中的代码得到执行证明了这一点
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
