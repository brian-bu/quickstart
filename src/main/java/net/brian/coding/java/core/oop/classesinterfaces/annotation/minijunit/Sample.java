package net.brian.coding.java.core.oop.classesinterfaces.annotation.minijunit;
/**
 * 
 * Sample��������8����̬������4���ǲ��ԣ�û�б�Testע���ǵ�4�������ᱻ���Թ��ߺ���
 * ��֮Sample����������ԣ�һ���ͨ���������ʧ�ܣ���һ����Ч
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