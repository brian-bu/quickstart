package net.brian.coding.java.core.oop;

public class Null {

	public static void haha() {
		System.out.println("haha");
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// Notes: How can this be? null can be declared as any type, even (String)null is legal.
		// But it can only access to the static methods, if non-static methods are supposed to invoke,
		// then we need create a new object and this way won't work.
		((Null) null).haha();
	}

}