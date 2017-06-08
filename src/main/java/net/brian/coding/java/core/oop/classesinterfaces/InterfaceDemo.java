package net.brian.coding.java.core.oop.classesinterfaces;

// Illegal modifier for the interface InterfaceDemo; only public & abstract are permitted
public /* final */abstract interface InterfaceDemo {
	
	// Must be initialized. The constant is public static final by default.
	int i = 2;

	// Method body is not allowed.
	void test();
}
