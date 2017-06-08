package net.brian.coding.java.core.pool;

import java.lang.reflect.Field;

import org.junit.Test;
/**
 * 
 * String��ֱ�����ڴ��п�����һ���ַ���������������Integer�漰�����Զ�װ�䣺
 * ����Integer i = 100;ϵͳ�ڳ�ʼ��ʱ�Զ�Ϊ����ִ���ˣ� Integer i = Integer.valueOf(100);
 * ��׷�����valueOf��Դ����Է������Ǵӳ�����ȡ����-128~127֮���ֵ
 * Ҳ����˵����String�ĳ������޷�����Ԥ֪�����õ���Щ�������������һ����һ��
 * ������Integer��˵�����Ԥ�⵽��õģ�������jvm������ʱ��Ͱ���Щ����new�÷ŵ���������
 *
 */
public class ConstantPoolForString {
	// ����Integer������
	public void testIngeterCache() {
		Integer a = 10;
		Integer b = 10;
		Integer c = 1000;
		Integer d = 1000;
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(c == d);
	}

	@Test
	public void youdontKnowLoveFarHigh3() {
		try {
			String test = "aaaa";
			String test2 = test;
			String test3 = new String(test);
			String test4 = new String(test.toCharArray());
			Field values = String.class.getDeclaredField("value");

			values.setAccessible(true);
			char[] ref = (char[]) values.get(test);
			ref[0] = 'b';
			System.out.println("aaaa");
			// Output aaaaaaaa aaaa aaaa aaaa without change by reflection.
			System.out.println("aaaa" + test + " " + test2 + " " + test3 + " " + test4);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
			System.err.println("StringDemo -- youdontKnowLoveFarHigh3() -- Exception:: " + ex);
			return;
		}

	}

//	@Test
	public void youdontKnowLoveFarHigh2() {
		final String mutableStr1 = getString();
		String mutableStr2 = mutableStr1.toString();
		// String��Ȼ��final�ģ���������ֵȴ�ǲ�ȷ���ģ���Ҫ����ʱ����ȷ���������������ߵ�����ȴ��һ�µ�
		// ���Ե�����һ����ֵ��ˢ��������֮����һ��Ҳָ��ó������е�String�����Է���true��
		System.out.println(
				"StringDemo -- youdontKnowLoveFarHigh() -- mutableStr:: " + (mutableStr1 == mutableStr2.intern()));//true
		System.out.println(
				"StringDemo -- youdontKnowLoveFarHigh() -- mutableStr:: " + (mutableStr2 == mutableStr1.intern()));//true
	}

	private String getString() {
		double randomDouble = Math.random() * 1.5 + 2;
		System.out.println("StringDemo -- getString() -- randomDouble:: " + randomDouble);
		return String.valueOf(randomDouble);
	}

	/**
	 * ͨ��final������Stringָ���ֵ��ʹ�����޷��ı䣬����ʱ����ͨ����setAccessible�����������true��������ȫ�Լ��
	 * ֱ�Ӹ���������ֵ�����հѸ��ĺ��ֵͨ��intern����ˢ�����������������ֻ��ͨ���Ƿ��ֶθ�����String��ָ��
	 * ���ǳ�����������Ӧ��������String�����ˡ�internֻ����ʵ��ִ������ע����д�Ĺ��ܣ�
	 * ��鳣�����Ƿ�������String�������ڣ�������ˢ�������ز���������
	 * ���intern��final�����ͻ����ʵ�ǲ���ͻ�ģ�internĬ����Ϊ�������Ķ��������final�Ĳ����Ѿ�����ֵ
	 * final�ѱ���ֵ�Ͳ����ܱ��ı䣬������Ը�String�����õĸ��ľ��ǰ�ȫ��
	 * ����ʵ�ϲ�����ģ���Ϊ�����ؿ϶��Ѿ��������String����ʱָ���ĳ����ˣ������������ֻ�Ƿ���һ���Ѵ��ڶ�������ö��ѡ�
	 */
	public void youdontKnowLoveFarHigh() {
		final String mutableStr = "Hello world";
		// Compile error cause final String can't be re-assigned again.
		// mutableStr = "Hello Brian";
		System.out.println("StringDemo -- youdontKnowLoveFarHigh() -- mutableStr before:: " + mutableStr.intern());
		System.out.println(
				"StringDemo -- youdontKnowLoveFarHigh() -- mutableStr before:: " + (mutableStr == mutableStr.intern()));
		// Reflection however, is another way to re-assign legally, though not
		// recommended.
		Field values = null;
		try {
			values = String.class.getDeclaredField("value");
		} catch (NoSuchFieldException e) {
			System.err.println("StringDemo -- youdontKnowLoveFarHigh() -- NoSuchFieldException:: " + e);
			return;
		} catch (SecurityException e) {
			System.err.println("StringDemo -- youdontKnowLoveFarHigh() -- SecurityException:: " + e);
			return;
		}
		values.setAccessible(true);
		char[] ref = new char[11];
		try {
			// Normally we need do this: Object obj =
			// String.class.newInstance();
			// And pass this obj into the get method below,
			// However String is an immutable class so we don't have to do this.
			ref = (char[]) values.get(mutableStr);
		} catch (IllegalArgumentException e) {
			System.err.println("StringDemo -- youdontKnowLoveFarHigh() -- IllegalArgumentException:: " + e);
			return;
		} catch (IllegalAccessException e) {
			System.err.println("StringDemo -- youdontKnowLoveFarHigh() -- IllegalAccessException:: " + e);
			return;
		}
		ref[0] = 'H';
		ref[1] = 'e';
		ref[2] = 'l';
		ref[3] = 'l';
		ref[4] = 'o';
		ref[5] = ' ';
		ref[6] = 'K';
		ref[7] = 'i';
		ref[8] = 't';
		ref[9] = 't';
		ref[10] = 'y';
		// Even we change the value, the pointer of mutableStr is still pointing
		// to "Hello world" in constant pool during class loading.
		System.out.println("StringDemo -- youdontKnowLoveFarHigh() -- mutableStr:: " + mutableStr);
		// This time we flush the modified value of mutableStr to constant pool
		// during runtime, we can't change the pointer of mutableStr cause it is
		// final, but we can change the value of the object it pointing at.
		System.out.println("StringDemo -- youdontKnowLoveFarHigh() -- mutableStr after:: " + mutableStr.intern());
	}
}
