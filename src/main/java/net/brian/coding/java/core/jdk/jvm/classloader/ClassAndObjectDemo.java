package net.brian.coding.java.core.jdk.jvm.classloader;

import org.junit.Test;

import net.brian.coding.designpatterns.singleton.Singleton;
import net.brian.coding.java.utils.entity.DemoBean;
/**
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

	@SuppressWarnings("static-access")
	public void testStatic() {
		ClassAndObjectDemo t1 = new ClassAndObjectDemo();
		ClassAndObjectDemo t2 = new ClassAndObjectDemo();
		// 即使创建了两个对象，但是TestArea.i也只有一份存储空间，这两个对象共享一个i，也就是类成员是供类派生出的所有对象共享的。
		System.out.println(t1.i == t2.i);
		// 也可以通过类名直接引用，而不需要对象引用，这是非静态类做不到的
		System.out.println(ClassAndObjectDemo.i);
		// 以下是==和equals的区别
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
