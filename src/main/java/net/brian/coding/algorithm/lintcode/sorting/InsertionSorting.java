package net.brian.coding.algorithm.lintcode.sorting;

import java.util.Arrays;
/**
 * 
 * http://www.lintcode.com/zh-cn/problem/sort-integers/
 *
 */
public class InsertionSorting {

	public static int[] intArrayInsertionDemo(int[] target) {
		if (target == null || target.length == 0)
			target = new int[0];
		for (int j = 1; j < target.length; j++) {
			int exchange = target[j];
			int i = j - 1;
			while (i >= 0 && target[i] > exchange) {
				target[i + 1] = target[i];
				i = i - 1;
			}
			target[i + 1] = exchange;
		}
		return target;
	}

	public static void main(String[] args) {
		int[] target = { 5, 3, 4, 6, 8 };
		System.out.println(Arrays.toString(intArrayInsertionDemo(target)));
	}
}
