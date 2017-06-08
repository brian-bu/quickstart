package net.brian.coding.designpatterns.strategy;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * item21：用函数对象表示策略
 * 函数指针的主要用途就是实现策略模式，具体实现方法： ①声明一个接口表示该策略，并为每一个具体策略声明一个实现了该接口的类；
 * ②当一个具体策略只能被使用一次的时候要用匿名类来声明和实例化这个具体策略类；
 * ③当一个具体策略是设计用来重复使用的时候，它的类通常就要被实现为私有静态成员类，并通过公有的静态final域被导出，其类型为该策略接口
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