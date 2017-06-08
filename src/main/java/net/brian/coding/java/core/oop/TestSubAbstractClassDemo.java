
package net.brian.coding.java.core.oop;

public class TestSubAbstractClassDemo {
	public static void main(String[] args) {
		AbstractClassDemo demo = new SubAbstractClassDemo();
		demo.anotherTest();
	}
}

class SubAbstractClassDemo extends AbstractClassDemo {

	@Override
	void anotherTest() {
		System.out.println("SubAbstractClassDemo -- anotherTest():: Implementation of AbstractClassDemo.");
	}

}

abstract class AbstractClassDemo {

	// Initialization is not required.
	static int i;

	// Method body is required.
	void test() {
	}

	// Can't have method body.
	abstract void anotherTest();

}