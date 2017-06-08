package net.brian.coding.algorithm.demo.sorting;

import java.util.Arrays;

public class InsertionSorting {

	public static int[] intArrayInsertionDemo(int[] target) {
		if (target == null || target.length == 0)
			throw new NullPointerException("Target array is null!");
		// 循环不变式：i = 0初始化：起始条件；target[i]保持：循环某次迭代之前为真，下次迭代之前仍为真；
		// i = target.length终止条件。
		for (int j = 1; j < target.length; j++) {
			int exchange = target[j];
			// Insert target[j] into the sorted sequence target[1 .. j-1].
			int i = j - 1;
			// while循环将A[j-1],A[j-2],A[j-3]向右移动一个位置，直到找到A[j]的适当位置,因为不可能在两个相邻的存储单元之间再插入一个单元，因此要将插入点之后的数据依次往后移动一个单元
			// 一旦确定了正确位置j，目标值target（即原始的arr[i]）就会被复制到这个位置。与选择排序不同的是，插入排序将数据向右滑动，并且不会执行交换。
			while (i >= 0 && target[i] > exchange) {
				target[i + 1] = target[i];
				i = i - 1;
			}
			// 将A[j]的值插入该位置
			target[i + 1] = exchange;
		}
		return target;
	}

	public static void main(String[] args) {
		int[] target = { 5, 3, 4, 6, 8 };
		System.out.println(Arrays.toString(intArrayInsertionDemo(target)));
	}
}
