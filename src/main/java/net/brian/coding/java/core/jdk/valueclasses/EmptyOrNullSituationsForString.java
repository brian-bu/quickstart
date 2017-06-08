package net.brian.coding.java.core.jdk.valueclasses;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class EmptyOrNullSituationsForString {
	private static String str;

	@SuppressWarnings("unused")
	@Test
	public void test() {
		String nullStr = null;
		String emptyStr = "";
		System.out.println(nullStr);
		System.out.println(nullStr + null);
		// Compiler error: The operator + is undefined for the argument type(s) null, null
		// System.out.println(null + null);
		// null既不是Object类型，也不是String类型，因为null还是不存在的，不属于任何类型的对象
		System.out.println("If null belongs to Object: " + (null instanceof Object));
		System.out.println("If nullStr belongs to Object: " + (nullStr instanceof Object));
		System.out.println("If nullStr belongs to String: " + (nullStr instanceof String));
		System.out.println("If emptyStr belongs to Object: " + (emptyStr instanceof Object));
		System.out.println("If emptyStr belongs to String: " + (emptyStr instanceof String));
		// 全局的String不需要强制初始化，但是会默认初始化为null，这里null调用toString会抛NullPointerException
		// System.out.println("Output the static str: " + str.toString());
		System.out.println("Output the static str: " + String.valueOf(str));
		List<Object> list1 = Collections.emptyList();
		// 这里等号左边指明了类型但是右面没有，所以推荐用Collections.emptyList()
		List<Object> list2 = Collections.EMPTY_LIST;
	}

}
