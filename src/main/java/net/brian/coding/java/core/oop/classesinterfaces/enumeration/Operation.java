// Enum type with constant-specific class bodies and data - Page 153
package net.brian.coding.java.core.oop.classesinterfaces.enumeration;

import java.util.HashMap;
import java.util.Map;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item30: Use enums instead of int constants
 * 
 * �����ʲ�ͬ����Ϊ��ÿ����������������������switch�������ö��
 * switch��ö�پ�����������һ�ԡ�
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

	// ö�������еĳ��󷽷����뱻�����г����еľ��巽�������ǣ���һ��ͳ�������һ�µ�
	// �����е�ö�����Ͷ���������x��y�Ĳ��������Ӽ��˳��ĸ�ö����
	// ��˶���һ�����󷽷��涨ÿһ��ö�����Ͷ�Ҫ���x��y������Բ�ͬ�Ĳ���
	// ���ַ����������ض��ڳ����ķ���ʵ��
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
