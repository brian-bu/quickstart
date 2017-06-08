package net.brian.coding.java.core.oop;

public class ExtendedInterfaceDemo implements DerivedInterface {

	@Override
	public void method1() {
		System.out.println("Derived interface:: method1()...");
	}

	@Override
	public void method2() {
		System.out.println("Derived interface:: method2()...");
	}

	@Override
	public void method3() {
		System.out.println("Derived interface:: method3()...");
	}

}

class PreviousInterfaceDemo implements PreviousInterface {
	public static void main(String[] args) {
		PreviousInterface previousDemo = new ExtendedInterfaceDemo();
		previousDemo.method1();
		previousDemo.method2();
	}

	@Override
	public void method1() {
		System.out.println("Previous interface:: method1()...");
	}

	@Override
	public void method2() {
		System.out.println("Previous interface:: method2()...");
	}
}

interface PreviousInterface {
	void method1();

	void method2();
}

interface DerivedInterface extends PreviousInterface {
	void method3();
}