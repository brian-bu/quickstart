package net.brian.coding.java.core.jdk.valueclasses;

import static java.lang.System.out;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
/**
 * 
 * 对于String不可变的方方面面：
 * 对于String的源码，其中一共有三个域：value，offset和count。
 * 
 * 这三个变量都是private的，并且没有提供公共方法来修改这些值
 * 所以在String类的外部无法修改String
 * 因此一旦初始化就不能修改，并且在String类的外部不能访问这三个成员。
 * 
 * 此外，value，offset和count这三个变量都是final的
 * 也就是说在String类内部，一旦这三个值初始化了， 也不能被改变。
 * 所以可以认为String对象是不可变的了。
 *
 */
public class StringDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String str;

	private static synchronized String changeString() {
		str = "Hello world!";
		return str;
	}

	public void syncString() {
		String changedStr = null;
		changedStr = changeString();
		System.out.println(changedStr);
	}

	private String testNullObjCasting() {
		return (String) null;
	}

	public void testInstanceofString() {
		String str = null;
		System.out.println(str == testNullObjCasting());
		System.out.println(str == null);
		System.out.println(testNullObjCasting() instanceof String);
		System.out.println(testNullObjCasting() instanceof Object);
	}

	// Constants Pool related.
	public void testOperator() {
		String a = "abc";
		String b = "abc";
		String c = new String("abc");
		String d = "ab" + "c";
		System.out.println("StringDemo -- testOperator() -- ( a==b ):: " + (a == b));
		System.out.println("StringDemo -- testOperator() -- ( a==c ):: " + (a == c));
		System.out.println("StringDemo -- testOperator() -- ( a==d ):: " + (a == d));
		System.out.println("StringDemo -- testOperator() -- ( c==d ):: " + (c == d));
	}
	
	private String variableParam(String... args) {
		String nullStr = null;
		String emptyStr = "";
		for(int i = 0; i < args.length; i++) {
			nullStr = nullStr + args[i];
			emptyStr = emptyStr + args[i];
		}
		//XXX: AOP打印log重构：To be implemented by net.brian.utils.LogAspect start.
		out.println("StringDemo -- variableParam -- emptyStr:: " + emptyStr);
		out.println("StringDemo -- variableParam -- nullStr:: " + nullStr);
		out.println("StringDemo -- variableParam -- args.length:: " + args.length);
		// To be implemented by net.brian.utils.LogAspect end.
		return emptyStr;
	}

	
	@Test
	public void finalClass() {
		String s = "ABCabc";
		System.out.println("s = " + s);
		// 这句代码执行过之后，又创建了一个新的对象“123456”
		// 引用s重新指向了这个心的对象，原来的对象“ABCabc”还在内存中存在，并没有改变。
		s = "123456";
		// 从这里的输出看出s的值确实改变了。那么怎么还说String对象是不可变的呢？
		// 其实这里存在一个误区： s只是一个String对象的引用，它指向了一个具体的对象，并不是对象本身。
		System.out.println("s = " + s);

	}
	
	public void finalClassForSet() {
		// 如果字符串可以被改变，那么以上用法将有可能违反Set的设计原则，因为Set要求其中的元素不可以重复。
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
	}
	
	/**
	 * 这段英文是jdk中对于String(String original)这个构造器的注释：
	 * 
	 * Initializes a newly created {@code String} object so that it represents
     * the same sequence of characters as the argument; in other words, the
     * newly created string is a copy of the argument string. Unless an
     * explicit copy of {@code original} is needed, use of this constructor is
     * unnecessary since Strings are immutable.
	 */
	public void testStringConstructors() {
		// 不推荐这样做
		str = new String("Hello world");
	}
}
