package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * 定义如下继承关系：Object - ParentForInitialization - SubClassForInitialization
 * 
 * 则程序初始化步骤如下：
 * 1.执行Object类非静态初始化块（如果有的话）
 * 2.隐式或显式的调用Object类的一个或多个构造器执行初始化
 * 3.执行ParentForInitialization类非静态初始化块（如果有的话）
 * 4.隐式或显式的调用ParentForInitialization类的一个或多个构造器执行初始化
 * 5.执行SubClassForInitialization类非静态初始化块（如果有的话）
 * 6.隐式或显式的调用SubClassForInitialization类的一个或多个构造器执行初始化
 *
 */
public class ConstructorInitialization {

	public static void main(String[] args) {
		// 注意这里对子类的main方法直接调用的方式
		SubClassForInitialization.main(args);
	}
}

class ParentForInitialization {
	public static final int constantInt = 0;

	static {
		System.out.println("Entering ParentForInitialization static block...");
		// Cannot reference a field before it is defined.
		// System.out.println("Entering ParentForInitialization static block --
		// constantStr:: " + constantStr);
		// The final field ParentForInitialization.constantInt cannot be assigned.
		// constantInt = 1;
		System.out.println("Entering ParentForInitialization static block -- constantInt:: " + constantInt);
	}

	public ParentForInitialization(String str) {
		//FIXME: 初始化：调用的是子类的methodbody方法
		// 不要在父类构造器中调用其它方法，以防形成递归调用
		methodbody();
		// But invoking final methods or private methods is okay.
		System.out.println("ParentForInitialization() -- parameter:: " + str);
	}

	public static String constantStr = "ParentForInitialization";

	public void methodbody() {
		System.out.println("ParentForInitialization -- Entering methodbody...");
	}

}

class SubClassForInitialization extends ParentForInitialization {
	public SubClassForInitialization(String str) {
		super(str);
		System.out.println("SubClassForInitialization() -- parameter:: " + str);
	}

	static {
		System.out.println("Entering SubClassForInitialization static block...");
		System.out.println("Entering SubClassForInitialization static block -- constantStr:: " + constantStr);
	}

	@Override
	public void methodbody() {
		System.out.println("SubClassForInitialization -- Entering overriden methodbody...");
	}

	public static void main(String[] args) {
//		 ParentForInitialization demo = new SubClassForInitialization("SubClassForInitialization");
//		 demo.methodbody();

		new SubClassForInitialization("Another SubClassForInitialization").methodbody();
	}
}
