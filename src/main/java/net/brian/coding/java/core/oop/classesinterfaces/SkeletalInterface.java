package net.brian.coding.java.core.oop.classesinterfaces;

public abstract/*
				 * We have to implement method() in Skeletal interface if we
				 * remove the abtstract modifier
				 */ class SkeletalInterface implements Skeletal {

	// The method() is abstract by default, hence we need provide an
	// implementation in the derived classes.

	// This has the same influence for derived classes: they need to provide
	// overriding behaviors.
	public abstract void anotherMethod();

	@Override
	// This is not abstract method and already provide implementation below for
	// the Skeletal interface, hence we don't need to implement in the derived
	// classes.
	public void additionalMethod() {
		method();
		System.out.println("SkeletalInterface - addtionalMethod():: No changed required for derived classes...");
	}

}
