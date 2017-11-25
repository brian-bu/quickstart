package net.brian.coding.algorithm.lintcode.strings.get;

import java.util.Arrays;

public class ArraysDemo {
	static Object[] obj = null;
	static Object[] obj1 = { "", "ss", "as", "ssa", "ass" };
	static Object[] obj2 = { null, "", "ss" };
	static char[] obj3 = {'a', 'c', 'b'};
	static char a;

	public static void main(String[] args) {
		Arrays.sort(obj1);
		// ����������Ϊnull������������nullԪ�أ�������NPE
		// Arrays.sort(obj);
		// Arrays.sort(obj2);
		// ���[, as, ass, ss, ssa]���ɼ�����˭��˭����ǰ�棬���ǰ��յ�һ����ĸ����
		System.out.println("Arrays.toString(obj1):: " + Arrays.toString(obj1));
		System.out.println("obj1.toString():: " + obj1.toString());
		System.out.println("String.valueOf(obj1):: " + String.valueOf(obj1));
		System.out.println("new String(obj3):: " + new String(obj3));
		System.out.println("value of a::_" + a + "_");
		int compareInt = 0;
		char compareChar = '0';
		System.out.println("a == 0? " + (a == compareInt));// true
		System.out.println("a == 0? " + (a == compareChar));// false
		System.out.println("a == 0? " + (a == ("0d".charAt(0))));// false
	}
}
