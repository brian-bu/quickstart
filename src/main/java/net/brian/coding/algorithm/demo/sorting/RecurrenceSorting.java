package net.brian.coding.algorithm.demo.sorting;

import java.util.Arrays;

public class RecurrenceSorting {
	// 这个方法为了排序A[1, n]而先对A[1, n-1]排序，最终，就是先对A1和A2进行排序，然后接下来每次插入排序都是对已经排好序的进行插入排序
	// 这里面即使是针对已经排好序的数组进行插入排序，我们拿到一个随机的数字，也不知道它究竟应该被排到什么位置，所以对数组进行一次遍历是不可避免的
	// 这次数组遍历就是为了根据大小给将要插入的元素找到一个合适的位置，如果找到了这个位置，那么这个位置之后的元素就都要后移一位
	// 其中遍历并找到合适位置这两个操作每次都是一样的，可以将这两个操作单独提取成一个方法
	public static int[] recurrenceInsteadOfInsertion(int[] target) {
		if (target == null || target.length == 0)
			throw new NullPointerException("Target array is null!");
		int[] swap = new int[1];
		for (int i = 0; i < target.length; i++) {
			swap[i] = target[i];
			swap = findSuitablePlaceForNewElement(swap, swap[i]);
			System.out.println(Arrays.toString(swap));
		}
		return swap;
	}

	// 要么你要确保传进来的数组最后一个元素是专门用于占位的，即为空，但这很难保证，因为还有很多其它的元素也可能为空呢
	// 要么就只能选择新建一个长度加1的数组，因为传进来的数组长度是固定的
	private static int[] findSuitablePlaceForNewElement(int[] swap, int newEle) {
		int[] result = new int[swap.length + 1];
		for (int i = 0; i < swap.length; i++) {
			if (swap[i] < newEle) {
				continue;
			} else {
				System.arraycopy(swap, 0, result, 0, i);
				result[i] = newEle;
				System.arraycopy(swap, i, result, i + 1, swap.length - i);
				break;
			}
		}
		return result;
	}

	// 欧几里得算法求解最大公约数：gcd(a,b) = gcd(b,a mod b);
	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		int r = p % q;
		return gcd(q, r);
	}
	// 求解最小公倍数
	public static int gbs(int m, int n) {
		return m * n / gcd(m, n);
	}

	public static void main(String[] args) {
		System.out.println("最大公约数:: " + gcd(40, 16));
	}
}
