package net.brian.coding.designpatterns.strategy;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * item21���ú��������ʾ����
 * ����ָ�����Ҫ��;����ʵ�ֲ���ģʽ������ʵ�ַ����� ������һ���ӿڱ�ʾ�ò��ԣ���Ϊÿһ�������������һ��ʵ���˸ýӿڵ��ࣻ
 * �ڵ�һ���������ֻ�ܱ�ʹ��һ�ε�ʱ��Ҫ����������������ʵ���������������ࣻ
 * �۵�һ�������������������ظ�ʹ�õ�ʱ��������ͨ����Ҫ��ʵ��Ϊ˽�о�̬��Ա�࣬��ͨ�����еľ�̬final�򱻵�����������Ϊ�ò��Խӿ�
 *
 */
public class StrategyPattern {
	public static void main(String[] args) {
		int pattern1 = StringLengthComparator_StrategyPattern_1.INSTANCE.compare("hello", "world");
		System.out.println("FunctionPointer_StrategyPattern_1 -- pattern1:: " + pattern1);

		String[] strArr = { "12345", "123" };
		Arrays.sort(strArr, new java.util.Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str1.length() - str2.length();
			}
		});
		System.out.println("FunctionPointer_StrategyPattern_2 -- pattern2:: " + Arrays.toString(strArr));

		int pattern3 = FunctionPointer_StrategyPattern_3.STRING_LENGTH_COMPARATOR.compare("hello", "world");
		System.out.println("FunctionPointer_StrategyPattern_3 -- pattern3:: " + pattern3);
	}
}

class StringLengthComparator_StrategyPattern_1 implements Comparator<String> {
	private StringLengthComparator_StrategyPattern_1() {
	}

	// This INSTANCE here is the Function Pointer of the compare function.
	public static final StringLengthComparator_StrategyPattern_1 INSTANCE = new StringLengthComparator_StrategyPattern_1();

	public int compare(String str1, String str2) {
		return str1.length() - str2.length();
	}
}

class FunctionPointer_StrategyPattern_3 {
	private static class StringLengthComparator_StrategyPattern_3 implements Comparator<String>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(String str1, String str2) {
			return str1.length() - str2.length();
		}
	}

	// This STRING_LENGTH_COMPARATOR here is the Function Pointer of the compare
	// function.
	public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StringLengthComparator_StrategyPattern_3();
}

// Strategy inferface.
interface Comparator<T> {
	int compare(String str1, String str2);
}