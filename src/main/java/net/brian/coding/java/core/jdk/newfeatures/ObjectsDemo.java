package net.brian.coding.java.core.jdk.newfeatures;

import java.util.Objects;

import org.junit.Test;

public class ObjectsDemo /*
							 * extends Objects: Compilation error: The type
							 * ObjectsDemo cannot subclass the final class
							 * Objects
							 */ {
	/**
	 * @since 1.7
	 */
	private Objects objs;

	// We don't have any api return the type of Objects and we don't have
	// available constructor to initialize this reference. So when this
	// constructor is called, only null can be passed as the parameter.
	public ObjectsDemo(Objects objs) {
		this.objs = objs;
	}

	// It's nonsense to get the reference from Objects, cause the constructor of
	// Objects is private and all the methods defined in this class are static
	// so that we don't need a reference any more.
	public Objects getObjs() {
		return objs;
	}

	public void testObjectsAPIs() {
		System.out.println("ObjectsDemo -- testObjectsAPIs -- nonNull:: " + Objects.nonNull("Hello world"));
		System.out.println("ObjectsDemo -- testObjectsAPIs -- nonNull:: " + Objects.nonNull(getObjs()));
	}

	public static void main(String[] args) {
		Objects objs = null;
		ObjectsDemo demo = new ObjectsDemo(objs);
		demo.testObjectsAPIs();
	}
}
