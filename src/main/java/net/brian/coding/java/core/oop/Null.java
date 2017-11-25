package net.brian.coding.java.core.oop;

public class Null {

	public static void haha() {
		System.out.println("haha");
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// null可以被声明为任何类型，(String)null也是合法的，但是任何null类型的对象都只能访问静态方法，对于实例方法是不能访问的
		((Null) null).haha();
	}

}