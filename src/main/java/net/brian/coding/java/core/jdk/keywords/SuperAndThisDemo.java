package net.brian.coding.java.core.jdk.keywords;
/**
 * 
 * 以代码为例讲解super和this关键字
 * 
 * super用于显式调用父类的构造器，this用于显式调用本类另一个重载构造器
 * super不能用于return super;this可以用于return this;
 * 不可以把super当成变量使用，但是可以用于修饰变量和方法以表示这些变量和方法来自父类
 * 此外还可以通过父类类名访问父类的类变量
 *
 */
public class SuperAndThisDemo {
	public static void main(String[] args) {
		new SubClassForFinal("Brian", 12345, 27);
	}
}

class SubClassForFinal extends ParentForFinal {
	int age;

	public SubClassForFinal(String name, int id, int age) {
		// 显式的调用父类构造器，前面是不可以写除了注释以外的任何代码的
		super(name, id);
		System.out.println("SubClassForFinal(String name, int id, int age)...");
		// 编译错误：类名只能调用类变量
		// System.out.println("super.name:: " + ParentForFinal.name);
		// 这里不会有编译错误，super不仅可以调用类变量，还可以调用实例变量。
		System.out.println("super.name:: " + super.name);
		this.age = age;
	}
}
class ParentForFinal {
	String name;
	int id;
	public ParentForFinal(String name, int age) {
		// 显式的调用本类另一个重载构造器，前面是不可以写除了注释以外的任何代码的
		this();
		System.out.println("ParentForFinal(String name, int age)...");
		this.name = name;
		this.id = age;
	}
	public ParentForFinal() {
		System.out.println("ParentForFinal()...");
	}
}