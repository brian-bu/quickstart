package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class JVM_SurvivorDemo {
	private static final int _1MB = 1024 * 1024;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[_1MB * 4];
		allocation4 = new byte[_1MB * 4];
		allocation4 = null;
		allocation4 = new byte[_1MB * 4];
	}
}
