package net.brian.coding.java.core.jdk.reflection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassDemo {

	private void demo1() {
		Object obj = new Date();
		// Not such method in class Object.
		// obj.getTime();
		Date date = (Date) obj;
		date.getTime();
	}

	// Three ways to get the reference of a class in your hand.
	private void demo2() {
		List<String> list = new ArrayList<String>();
		Class clazz1 = list.getClass();
		Class clazz2 = null;
		try {
			clazz2 = Class.forName("java.util.ArrayList");
		} catch (ClassNotFoundException e) {
			System.out.println(">>>>>>>>>>>>>Oops! ClassNotFoundException!");
		}
		Class clazz3 = ArrayList.class;

		System.out.println(clazz1.toString() + "\n" + clazz2 + "\n" + clazz3);

		System.out.println(clazz1 == clazz2);
		System.out.println(clazz1 == clazz3);
		System.out.println(clazz3 == clazz2);
		System.out.println(Integer.class == int.class);
		System.out.println(void.class);
		System.out.println(Boolean.TYPE == boolean.class);
	}

	private void demo3() {
		String[] sArr1 = {};
		String[] sArr2 = { "Brian", "Sure" };
		String[][] sArr3 = {};
		System.out.println(sArr1.getClass() == sArr2.getClass());
		System.out.println(String[].class == sArr2.getClass());
		// System.out.println(sArr3.getClass() == sArr1.getClass());
		System.out.println(sArr3.getClass());
		System.out.println(sArr2.getClass());
		System.out.println(sArr1.getClass());
	}

	public void execute() {
		// demo2();
		demo3();
	}

	public static void main(String[] args) {
		ClassDemo demo = new ClassDemo();
		demo.execute();
	}
}
