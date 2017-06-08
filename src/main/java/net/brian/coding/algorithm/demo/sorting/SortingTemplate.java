package net.brian.coding.algorithm.demo.sorting;

@SuppressWarnings("rawtypes")
public class SortingTemplate {

	public static void selectionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// 最小元素索引，然后遍历剩余元素，谁最小就把这个索引传给谁
			// 最终就形成从0到i的一个排好序的数组
			// 先假定第i个元素最小，持有最小索引
			int min = i;
			// 遍历找到数组剩余元素中比第i个元素更小的元素
			for (int j = i + 1; j < N; j++)
				// 参考元素持有最小元素索引，如果下一个遍历的元素比参考元素小
				// 则索引持有权转交给那个元素
				if (less(a[j], a[min]))
					min = j;
			// 内层for循环结束才是遍历比较了数组剩余元素，才真的确定下来剩余元素中的最小元素
			// 因此内层for循环结束找到的最小元素要和第i个元素进行位置互换，也就是说选择排序无论什么情况都是N次的元素交换
			exch(a, i, min);
		}
	}

	public static void insertionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// 凭借这句less(a[j], a[j - 1])，插入排序可以做到只移动未排好序的元素
			// 如果恰好数组已经排好顺序，那么for循环结束就不会产生任何元素的交换
			for (int j = i; less(a[j], a[j - 1]); j++) {
				exch(a, j, j - 1);
			}
		}
	}

	public static void improvedInsertionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// 凭借这句less(a[j], a[j - 1])，插入排序可以做到只移动未排好序的元素
			// 如果恰好数组已经排好顺序，那么for循环结束就不会产生任何元素的交换
			for (int j = i; less(a[j], a[j - 1]); j++) {
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i] + " ");
	}

	public static boolean isSorted(Comparable[] a) {
		// Test if the array is sorted.
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static void main(String[] args) {
		String[] a = { "ax", "s", "e", "gf", "k" };
		selectionSorting(a);
		assert isSorted(a);
		show(a);
	}
}
