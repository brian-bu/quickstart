package net.brian.coding.java.core.pool;

import java.lang.reflect.Field;

import org.junit.Test;
/**
 * 
 * String是直接在内存中开辟了一个字符串常量，而对于Integer涉及到了自动装箱：
 * 对于Integer i = 100;系统在初始化时自动为我们执行了： Integer i = Integer.valueOf(100);
 * 而追踪这个valueOf的源码可以发现它是从常量池取出的-128~127之间的值
 * 也就是说对于String的常量池无法事先预知都会用到哪些常量，所以添加一个算一个
 * 而对于Integer来说大概能预测到最常用的，所以在jvm启动的时候就把这些对象new好放到常量池里
 *
 */
public class ConstantPoolForString {
	// 测试Integer常量池
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
		// String虽然是final的，但是它的值却是不确定的，需要运行时才能确定出来，但是两者的引用却是一致的
		// 所以当其中一个的值被刷到常量池之后另一个也指向该常量池中的String，所以返回true。
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
	 * 通过final限制了String指向的值，使得它无法改变，运行时我们通过对setAccessible方法传入参数true来跳过安全性检查
	 * 直接更改了它的值，最终把更改后的值通过intern方法刷到常量池里，这样我们只是通过非法手段更改了String的指向
	 * 但是常量池里现在应该有两个String常量了。intern只是忠实的执行了它注释里写的功能：
	 * 检查常量池是否存在这个String（不存在），把它刷到常量池并返回引用
	 * 这个intern和final概念不冲突吗？其实是不冲突的，intern默认认为调用它的对象如果是final的并且已经被赋值
	 * final已被赋值就不可能被改变，因此它对该String的引用的更改就是安全的
	 * （事实上不会更改，因为常量池肯定已经存在这个String声明时指定的常量了，所以这个方法只是返回一个已存在对象的引用而已。
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
