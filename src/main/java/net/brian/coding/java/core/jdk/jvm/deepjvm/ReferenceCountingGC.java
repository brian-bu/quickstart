package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class ReferenceCountingGC {
	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	@SuppressWarnings("unused")
	private byte[] bigSize = new byte[2 * _1MB];

	public static void testGC() {
		ReferenceCountingGC gc1 = new ReferenceCountingGC();
		ReferenceCountingGC gc2 = new ReferenceCountingGC();
		gc1.instance = gc2;
		gc2.instance = gc1;
		gc1 = null;
		gc2 = null;
		System.gc();
	}

	public static void main(String[] args) {
		testGC();
	}
}