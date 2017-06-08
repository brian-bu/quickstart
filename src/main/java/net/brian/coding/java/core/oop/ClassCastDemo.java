package net.brian.coding.java.core.oop;

import org.junit.Test;

/**
 * JVM的设计原则：对象的引用关系只有对象的创建者持有和使用，JVM不可以干预对象的引用关系
 * 因为JVM并不知道对象是怎么被使用的，这就涉及JVM并不知道对象的运行时类型而只知道编译时类型
 * 
 * 转型分为强制转型和自动转型
 * 父类引用强制转换成子类叫向下转型，所以要明确的指定要转换成哪一个子类
 * 子类转换成父类是向上转型
 * 
 * 向下转型可能引发ClassCastException，JVM在做类型转换的时候会按照如下规则进行检查：
 * a.对于普通对象，该对象必须是目标类的实例或目标类的子类的实例，如果目标类是接口，会把它当做实现了该接口的一个子类
 * b.对于数组类型，目标类必须是数组类型或java.lang.Object/java.lang.Cloneable/java.io.Serializable
 * 如果不满足上述条件就会引发ClassCastException，避免这个错误的两种方式：
 * a.利用泛型的机制确保在编译期进行强转的安全性检查
 * b.强转之前最好用instanceof判断一下，这个是在运行期
 * 对于instanceof的代码示例单独写一个类：
 * @see net.brian.coding.java.core.jdk.keywords.InstanceofDemo
 * 
 * 强转本质上是引用类型之间的强转，但是底层的对象是不会变的
 *
 */
public class ClassCastDemo {
	/**
	 * 被转型变量的编译时类型是目标类型的父类：向下转型
	 * 需要强制转换，如果转换失败会抛出ClassCastingException
	 */
	@SuppressWarnings("unused")
	public void testDownCasting() {
		Object obj = "Hello";
		// obj变量的编译类型为Object，是String类型的父类，可以强制类型转换
		// 而且obj变量实际上引用的也是String对象，所以运行时也正常
		String objStr = (String) obj;
		System.out.println(objStr);
		// 定义一个objPri变量，编译类型为Object，实际类型为Integer
		Object objPri = new Integer(5);
		// objPri变量的编译类型为Object，是String类型的父类，可以强制类型转换
		// 但强转本质上是引用类型之间的强转，无论怎样底层的对象是不会变的
		// 因此这次强转之后是让本来指向一个Integer的引用声明成了String类型
		// obj变量实际上引用的是Integer对象，也即强转如果成功，将会变成：
		// String str = new Integer(5);这显然是不可能的
		// 所以下面代码在运行的时候就会引发ClassCastingException
		String str = (String) objPri;
	}
	
	/**
	 * 被转型变量的编译时类型是目标类型的子类：向上转型
	 * 不需要强制转换，转换是在运行时自动完成的
	 */
	@Test
	public void testUpCasting() {
		String s1 = "Hello";
		String str1 = "He";
		String str2 = "llo";
		String s2 = str1 + str2;
		// String类型的对象，用Object声明出来，向上转型
		// 不过通过调用equals也可以发现向上转型会自动丢失一些子类的特征，比如对equals方法的覆盖
		// 强转之后调用的不再是String覆盖父类的方法，而是父类Object自己的方法
		Object obj = s1;
		// 这个equals是Object的而不是String的
		// 但是传进去的参数却是String，这种转型也是向上转型，也是自动的
		System.out.println(obj.equals(s2));
	}
	
	/**
	 * 如果既不是向上转型也不是向下转型，那就是被转型变量的编译时类型和目标类型相同
	 * 如果两者类型不同，比如把String强转成Math，那么程序连编译检查都过不去
	 */
	@SuppressWarnings("unused")
	public void testWrongTypeCasting() {
		String s1 = "Hello World";
		// 被转型变量的编译时类型和目标类型相同，这种情况能通过编译，但是没有必要这样
		String s2 = (String)s1;
		// 因为s的编译时类型是String，String不是Math类型
		// String也不是Math的子类，也不是Math的父类，所以下面代码导致编译错误
		// Math m = (Math)s1;
	}
	
}
