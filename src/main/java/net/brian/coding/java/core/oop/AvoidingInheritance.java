package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item04: Enforce noninstantiability with a private constructor
 * 
 * 禁止继承或者实例化构造器的方式方法示例分析:
 * a.私有化构造器，这样构造器就完全不能用了，就不能实例化了，而且也禁止了继承
 * b.声明为final类，这样构造器依旧可以用，即仍旧可以实例化，而且彻底禁止了继承
 * c.声明为抽象类，这样子类依旧可以调用父类的构造器，并没有真正禁止实例化，而且还可以继承
 *
 */
public class AvoidingInheritance {
	public static class TestCollections /*extends java.util.Collections*/ {
		// Collections是通过私有化构造器禁止外界继承，因为Collections就是一个工具类，也不需要用到构造器初始化什么
	}
	public static class TestString /*extends String*/ {
		// 对于String就和Collections不同了，String是需要构造器完成一定初始化操作的
		// 因此String的不可继承和Collections类还不一样，String是直接将类声明为final的进而禁止继承
	}
	
	public static class AbstractClass {public AbstractClass() {System.out.println("AbstractClass()");}}
	public static class DerivedClass extends AbstractClass {
		//不要企图将一个类设置成抽象类来防止该类被实例化，因为该类可以被子类化，然后子类就是可实例化的类了，这样同样暴露了父类的构造器
		public DerivedClass() {
			super();
		}
	}
	public class PrivateConstructor {
		private PrivateConstructor() {
			// 为了避免类似反射的原理恶意调用这个构造器，可以考虑在私有化构造器的同时抛出一个Error
			throw new AssertionError();
		}
	}
}
