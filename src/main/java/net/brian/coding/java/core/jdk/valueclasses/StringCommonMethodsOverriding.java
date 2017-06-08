package net.brian.coding.java.core.jdk.valueclasses;

import org.junit.Test;

import net.brian.coding.java.utils.StringUtil;

/**
 * 
 * 这个类里面包括hashCode、equals、toString等通过重载Object类获得的一些方法
 *
 */
public class StringCommonMethodsOverriding {
	String output1 = "Hello";
	// 在覆盖toString方法的时候，在toString的方法体中应禁止使用this，以防止无意识的递归
	@Test
	public void testToString() {
		System.out.println(output1);
	}
	
	@Test
	public void testHashCode() {
		// String类不可变，所以一旦对象被创建，该hash值也无法改变。
		// 所以，每次想要使用该对象的hashcode的时候，直接返回即可。
		output1.hashCode();
	}
	
	public void testEquals() {
		boolean flag = !("" != "");
		System.out.println("Output flag: " + flag);
		int i = 3;
		String seq = Integer.toString(i + 1);
		System.out.println("Output seq value: " + seq);
		System.out.println("If 4 equals seq: " + ("4".equalsIgnoreCase(seq)));
		System.out.println("If 4 == seq: " + ("4" == seq));
		System.out.println("If 4 == seq.intern(): " + ("4" == seq.intern()));
		String str = null;
		String testStr = StringUtil.null2Empty(str);
		System.out.println("If testStr is empty: " + (testStr == ""));
		System.out.println("If null is empty: " + (null == ""));
	}

	public void testTwoBarEqualsSignWithHashCode() {
		String a = "JAVA";
		String b = "JAVA";
		String c = new String("JAVA");
		String d = "JA";
		String e = "VA";
		// This is combination of literals, and the result 'JAVA' is already in
		// the constants pool.
		// Hence will use the one in the constants pool as a result.
		// So f is also pointing to the constants pool.
		String f = "JA" + "VA";
		String g = d + e;
		String h = c;
		/* true */
		System.out.println(a == b);
		System.out.println(c == h);
		System.out.println(a == f);
		/* false */
		System.out.println(a == g);
		System.out.println(f == g);
		// a is pointing to area in the constant pool, c is newly created
		// in the stack where it outside the constant pool.
		System.out.println(a == c);
		System.out.println(c == f);
		System.out.println(c == g);
		// This is combination of objects, not literals, they are not the same.
		System.out.println(a == g);
		System.out.println("a : " + a.hashCode());
		System.out.println("b : " + b.hashCode());
		System.out.println("c : " + c.hashCode());
		System.out.println("d : " + d.hashCode());
		System.out.println("e : " + e.hashCode());
		System.out.println("f : " + f.hashCode());
		System.out.println("g : " + g.hashCode());
		System.out.println("h : " + h.hashCode());
	}
}
