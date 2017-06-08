package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;
/**
 * 
 * Sample类里面有8个静态方法，4个是测试，没有被Test注解标记的4个方法会被测试工具忽略
 * 总之Sample包含四项测试，一项会通过，两项会失败，另一项无效
 *
 */
public class Sample {
	@Test
	public static void m1() {
	} // Test should pass

	public static void m2() {
	}

	@Test
	public static void m3() { // Test Should fail
		throw new RuntimeException("Boom");
	}

	public static void m4() {
	}

	@Test
	public void m5() {
	} // INVALID USE: nonstatic method

	public static void m6() {
	}

	@Test
	public static void m7() { // Test should fail
		throw new RuntimeException("Crash");
	}

	public static void m8() {
	}
}