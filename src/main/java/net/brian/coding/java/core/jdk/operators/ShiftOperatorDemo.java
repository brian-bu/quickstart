package net.brian.coding.java.core.jdk.operators;

/**
 * ��λ�����
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
		// ԭʼ��������
		printInfo(number);
		number = 536870911;
		printInfo(number);
//		number = number << 10;
//		// ����20λ
//		printInfo(number);
//		number = number >> 10;
//		// ����12λ
//		printInfo(number);
//		number = number >>> 20;
//		// �޷�������20λ
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
	 * ���һ��int�Ķ�������
	 * 
	 * @param num
	 */
	private static void printInfo(int num) {
		System.out.println(Integer.toBinaryString(num));
	}
}
