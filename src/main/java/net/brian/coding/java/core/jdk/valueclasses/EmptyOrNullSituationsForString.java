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
		// null�Ȳ���Object���ͣ�Ҳ����String���ͣ���Ϊnull���ǲ����ڵģ��������κ����͵Ķ���
		System.out.println("If null belongs to Object: " + (null instanceof Object));
		System.out.println("If nullStr belongs to Object: " + (nullStr instanceof Object));
		System.out.println("If nullStr belongs to String: " + (nullStr instanceof String));
		System.out.println("If emptyStr belongs to Object: " + (emptyStr instanceof Object));
		System.out.println("If emptyStr belongs to String: " + (emptyStr instanceof String));
		// ȫ�ֵ�String����Ҫǿ�Ƴ�ʼ�������ǻ�Ĭ�ϳ�ʼ��Ϊnull������null����toString����NullPointerException
		// System.out.println("Output the static str: " + str.toString());
		System.out.println("Output the static str: " + String.valueOf(str));
		List<Object> list1 = Collections.emptyList();
		// ����Ⱥ����ָ�������͵�������û�У������Ƽ���Collections.emptyList()
		List<Object> list2 = Collections.EMPTY_LIST;
	}

}
