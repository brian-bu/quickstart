package net.brian.coding.java.core.jdk.valueclasses;

public class WrapperTypesDemo {
	private static void testPrimitiveDatas() {
		float i = 0.6332f;
		float j = 0.6f;
		double k = 0.6;
		double l = 0.6332;
		int x = 2147483647;
		// How about (x+1)-1.
		System.out.println("WrapperTypesDemo -- testPrimitiveDatas() -- max int plus 1:: " + (x + 1));
		System.out.println("WrapperTypesDemo -- testPrimitiveDatas() -- If ((x + 1) < x):: " + ((x + 1) < x));
	}

	public static void main(String[] args) {
		testPrimitiveDatas();
	}
}
