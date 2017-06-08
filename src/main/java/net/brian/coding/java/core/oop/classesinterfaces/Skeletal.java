package net.brian.coding.java.core.oop.classesinterfaces;

public interface Skeletal {
	void method();
	void additionalMethod();
	void anotherMethod();
	// New feature since JDK8.
	default void testDefaultForJDK8() {}
}
