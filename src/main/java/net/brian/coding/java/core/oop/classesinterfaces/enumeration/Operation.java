// Enum type with constant-specific class bodies and data - Page 153
package net.brian.coding.java.core.oop.classesinterfaces.enumeration;

import java.util.HashMap;
import java.util.Map;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item30: Use enums instead of int constants
 * 
 * 将本质不同的行为与每个常量关联起来，并且在switch语句中用枚举
 * switch和枚举就是天造地设的一对。
 * 
 */
public enum Operation {
	PLUS("+") {
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		double apply(double x, double y) {
			return x / y;
		}
	};
	private final String symbol;

	Operation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	// 枚举类型中的抽象方法必须被它所有常量中的具体方法所覆盖，这一点和抽象类是一致的
	// 本例中的枚举类型定义的是针对x和y的操作，即加减乘除四个枚举项
	// 因此定义一个抽象方法规定每一个枚举类型都要针对x和y定义各自不同的操作
	// 这种方法被称作特定于常量的方法实现
	abstract double apply(double x, double y);

	// Implementing a fromString method on an enum type - Page 154
	private static final Map<String, Operation> stringToEnum = new HashMap<String, Operation>();
	static { // Initialize map from constant name to enum constant
		for (Operation op : values())
			stringToEnum.put(op.toString(), op);
	}

	// Returns Operation for string, or null if string is invalid
	public static Operation fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	// Test program to perform all operations on given operands
	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		for (Operation op : Operation.values())
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}
}
