package net.brian.coding.java.core.jdk.keywords;
/**
 * 
 * instanceof运算符前面操作数的编译时类型必须是如下3种情况：
 * 要么与后面的类相同，要么是后面的类的父类，要么是后面类的子类
 * 如果Math instanceof String这种，编译检查都过不去，直接报编译错误
 *
 */
public class InstanceofDemo {
	public static void main(String[] args) {
		String s = "Hello";
		Object obj1 = null;
		Object obj2 = obj1;
		Object obj3 = new Object();
		// String既是Object的实例也是String的实例，所以肯定返回true
		System.out.println("s instanceof Object || s instanceof String：： " + (s instanceof Object && s instanceof String));
		// obj3显然只能是Object的实例不能是String的实例，所以这里返回false
		System.out.println("obj3 instanceof String:: " + (obj3 instanceof String));
		// 无论是直接声明为null还是指向了一个为null的对象，最终的类型都是null，而null是还没有类型的对象
		// 所以null instanceof任何类型的对象总是返回false
		System.out.println("obj1 instanceof Object || obj2 instanceof Object:: " + (obj1 instanceof Object || obj2 instanceof Object));
		// 编译的时候就可以检测出String和Math之间不符合instanceof的使用条件所以这里会报编译错误
	//	System.out.println("s instanceof Math:: " + (s instanceof Math));
	}
}
