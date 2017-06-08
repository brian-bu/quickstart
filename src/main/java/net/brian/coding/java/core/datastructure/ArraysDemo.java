package net.brian.coding.java.core.datastructure;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
/**
 * 
 * 数组的初始化分为静态初始化和动态初始化
 * 数组变量是引用类型，通常存放在栈内存中，它并不是数组对象本身，
 * 只要让数组变量指向有效的数组对象，程序中即可使用该数组对象
 *
 */
public class ArraysDemo {
	static int[] sampleIntArr = { 3, 1, 5 };
	static int[] emptyArr = new int[2];
	static String[][] sampleStrArr1 = { { "Brian", "male", "26" }, { "sure", "female", "23" }, { "Yanan", "female" } };
	static String[] sampleStrArr2 = { "Brian", "sure", "Yanan" };

	@Ignore
	// 以deep开头的方法，常用于多维数组
	public void testDeep() {
		String deepIntToStr = Arrays.toString(sampleIntArr);
		String deepStr = Arrays.deepToString(sampleStrArr1);
		System.out.println("ArraysDemo -- testDeep -- Print array directly:: " + sampleStrArr1.toString());
		System.out.println("ArraysDemo -- testDeep -- deepIntToStr:: " + deepIntToStr);
		System.out.println("ArraysDemo -- testDeep -- deepStr:: " + deepStr);
		boolean isArrsEqual = Arrays.deepEquals(sampleStrArr1, sampleStrArr2);
		System.out.println("ArraysDemo -- testDeep -- If array1 equals to array2:: " + isArrsEqual);
	}

	@Ignore
	public void testOthers() {
		
		boolean isArrsEquals = Arrays.equals(sampleStrArr1, sampleStrArr2);
		System.out.println("ArraysDemo -- testOthers -- isArrsEquals:: " + isArrsEquals);
		
		System.out.println("ArraysDemo -- testOthers -- sorting sampleIntArr:: " + Arrays.toString(sampleIntArr));
		// Can't sort multidimensional array.
		// java.lang.ClassCastException: [Ljava.lang.String; cannot be cast to java.lang.Comparable
		Arrays.sort(sampleIntArr);
		System.out.println("ArraysDemo -- testOthers -- sorted sampleIntArr:: " + Arrays.toString(sampleIntArr));
		Arrays.sort(sampleStrArr2);
		System.out.println("ArraysDemo -- testOthers -- sorted sampleStrArr2:: " + Arrays.toString(sampleStrArr2));
		Arrays.sort(sampleStrArr2, String.CASE_INSENSITIVE_ORDER);
		System.out.println("ArraysDemo -- testOthers -- case insensitive sorted sampleStrArr2:: " + Arrays.toString(sampleStrArr2));
	}

	/**
	 * Otherwise, if any of the following is true, an IndexOutOfBoundsException is thrown and the destination is not modified: 
	 * •The srcPos argument is negative. 
	 * •The destPos argument is negative. 
	 * •The length argument is negative. 
	 * •srcPos+length is greater than src.length, the length of the source array. 
	 * •destPos+length is greater than dest.length, the length of the destination array.
	 */
	/**
	 * Parameters:
	 * src the source array.
	 * srcPos starting position in the source array.
	 * dest the destination array.
	 * destPos starting position in the destination data.
	 * length the number of array elements to be copied.
	 */
	@Ignore
	public void testSystemArrayCopy() {
		int srcPos = 1, length = emptyArr.length, destPos = 1;
		if ((length-1 > destPos) && (srcPos >= destPos)) {
			// This is a native method, you need to handle all the exceptions yourself.
			System.arraycopy(sampleIntArr, srcPos, emptyArr, destPos, emptyArr.length);
			System.out.println(
					"ArraysDemo -- testSystemArrayCopy -- Copied Array -- emptyArr:: " + Arrays.toString(emptyArr));
		} else {
			System.err.println("ArraysDemo -- testSystemArrayCopy -- java.lang.ArrayIndexOutOfBoundsException");
		}
		testAddEleUsingSysArrayInArrayList(1);
	}
	
	private void testAddEleUsingSysArrayInArrayList(int index) {
		System.out.println("ArraysDemo -- testAddEleUsingSysArrayInArrayList() -- Before copy:: " + Arrays.toString(sampleIntArr));
		System.arraycopy(sampleIntArr, index, sampleIntArr, index + 1, sampleIntArr.length - index);
		System.out.println("ArraysDemo -- testAddEleUsingSysArrayInArrayList() -- After copy:: " + Arrays.toString(sampleIntArr));
	}

//	public static void paramCheckForSystemArrayCopy(Object src, int srcPos, Object dest, int destPos, int length) {
//		if((null == srcPos) || (null == destPos) || (null == length))
//	}
	public static void main(String[] args) {
		ArraysDemo demo = new ArraysDemo();
		demo.testSystemArrayCopy();
	}
}
