package net.brian.coding.java.core.jdk.operators;

/**
 * 移位运算符
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent.hashCode()
 */
public class ShiftOperatorDemo {
	
	private static void testShiftOperatorCalculation() {
		// 128, wrong, this means 2 << 6 actually.
		// System.out.println("ShiftOperatorDemo -- testShiftOperatorCalculation() -- 2 * 17:: " + (2 << 4 + 2));
		// 2<<4 means 2*(2*2*2*2)
		System.out.println("ShiftOperatorDemo -- testShiftOperatorCalculation() -- 2 * 17:: " + ((2 << 4) + 2));
	}
	
	public static void main(String[] args) {
		testShiftOperatorCalculation();
		int number = -5;
		// 原始数二进制
		printInfo(number);
		number = 536870911;
		printInfo(number);
//		number = number << 10;
//		// 左移20位
//		printInfo(number);
//		number = number >> 10;
//		// 右移12位
//		printInfo(number);
//		number = number >>> 20;
//		// 无符号右移20位
//		printInfo(number);
		// HashMap like.
		printInfo(number >>> 20);
		printInfo(number >>> 12);
		int num = (number >>> 20) ^ (number >>> 12);
		// number not changed after >>>.
		printInfo(number);
		printInfo(num);
		printInfo(number ^= num);
		// number changed after ^=.
		printInfo(number);
		
	}

	/**
	 * 输出一个int的二进制数
	 * 
	 * @param num
	 */
	private static void printInfo(int num) {
		System.out.println(Integer.toBinaryString(num));
	}
}
