package net.brian.coding.java.core.jdk.valueclasses;

import org.junit.Test;
/**
 * 
 * 包括对java的装箱拆箱的示例和IntegerCache常量池的示例
 * int是32位(bit)或4个字节(byte)
 * Integer是一个对象，需要存储对象的元数据。但是int是一个原始类型的数据，所以占用的空间更少。
 *
 */
public class IntegerDemo {
	// @Test
	public void testIntegerCompare() {
		System.out.println("IntegerDemo -- testIntegerCompare -- If x > y:: " + Integer.compare(50, 23));
		System.out.println("IntegerDemo -- testIntegerCompare -- If x < y:: " + Integer.compare(-1, 23));
		System.out.println("IntegerDemo -- testIntegerCompare -- If x = y:: " + Integer.compare(5, 5));
	}

	@Test
	public void testIntegerCache() {
		// No auto-boxing
		int i = 100;
		int j = 100;
		int k = 1000;
		int l = 1000;
		// Auto-boxing, related to IntegerCache
		// Integer a= Integer.valueOf(100);
		Integer a = 100;
		Integer b = 100;
		// Integer c= Integer.valueOf(1000);
		Integer c = 1000;
		Integer d = 1000;
		
		// Auto-unboxing to get value from d and assign to t
		// But t is still int and d still Integer, t only get the value from d
		// t can't invoke any methods cause it's primitive
		// d however, can invoke methods in Integer.
		int t = d;
		
		// Mixture of int-related and Integer-related. Comparing the value, not the objects.
		System.out.println("IntegerDemo -- testIntegerCache() -- (i == j):: " + (i == j));
		System.out.println("IntegerDemo -- testIntegerCache() -- (i == a):: " + (i == a));
		System.out.println("IntegerDemo -- testIntegerCache() -- (k == l):: " + (k == l));
		System.out.println("IntegerDemo -- testIntegerCache() -- (k == c):: " + (k == c));
		// Integer-related only. Comparing the objects. Consider the constant pool.
		System.out.println("IntegerDemo -- testIntegerCache() -- (a == b):: " + (a == b));
		System.out.println("IntegerDemo -- testIntegerCache() -- (c == d):: " + (c == d));
		// Still mixture of int-related and Integer-related. Comparing the value, not the objects.
		System.out.println("IntegerDemo -- testIntegerCache() -- (t == d):: " + (t == d));
		System.out.println("IntegerDemo -- testIntegerCache() -- (t == c):: " + (t == c));
	}/*
		 * Output: 
		 * IntegerDemo -- testIntegerCache() -- (i == j):: true
		 * IntegerDemo -- testIntegerCache() -- (i == a):: true
		 * IntegerDemo -- testIntegerCache() -- (k == l):: true
		 * IntegerDemo -- testIntegerCache() -- (k == c):: true
		 * IntegerDemo -- testIntegerCache() -- (a == b):: true
		 * IntegerDemo -- testIntegerCache() -- (c == d):: false
		 * IntegerDemo -- testIntegerCache() -- (t == d):: true
		 * IntegerDemo -- testIntegerCache() -- (t == c):: true
		 */

	// @Test
	public void testNullIntegerInvocation() {
		int a = nullIntegerFactory();
		System.out.println("IntegerDemo -- testNullIntegerInvocation():: " + a);
	}

	private Integer nullIntegerFactory() {
		return null;
	}
	public static void main(String[] args) {
		int i = Integer.MAX_VALUE + 10;
		System.out.println("Integer.MAX_VALUE:: " + Integer.MAX_VALUE);
		System.out.println("i:: " + i);
	}
}
