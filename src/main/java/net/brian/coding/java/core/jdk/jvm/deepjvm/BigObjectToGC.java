package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class BigObjectToGC {

	public static void main(String[] args) {
		int _1MB = 1024 * 1024;
		byte[] b = new byte[1120 * _1MB ];
		b.toString();
		System.out.println(b.toString());
	}
}
