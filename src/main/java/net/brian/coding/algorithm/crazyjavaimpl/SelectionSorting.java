package net.brian.coding.algorithm.crazyjavaimpl;

import java.util.Arrays;

// 冒泡排序的优化，因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数。选择排序的时间复杂度为O(n^2)
public class SelectionSorting {
	// 本方法和fetchTheSmallestNumberInArray方法的实现思路基本一致，只是按照那样的方式每次只返回最小的一个，那么需要调用n次这个方法，每次都获取数组中剩余元素的最小值，所以算上该方法内的两个for循环，至少需要三层for循环才能完成任务，这样的话复杂度就升高了，因此下面这种方式最大的可取之处就是设计了一个swap函数在数组内部进行元素对调
	public static int[] intArraySelectionDemo(int[] target) {
		if (target == null || target.length == 0)
			throw new NullPointerException("Target array is null!");
		int timeCounter = 0;
		int swapCounter = 0;
		for (int i = 0, len = target.length; i < len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++) {
				if (target[j] < target[min]) {
					min = j;
				}
			}
			swap(target, i, min);
		}
		System.out.println("比较次数：" + timeCounter);
		System.out.println("交换次数：" + swapCounter);
		return target;
	}

	// swap应该是一个选择排序中的原子操作，因此可以考虑单独封装成一个方法，不对外暴露交换细节
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// 最坏情况：数组中每一个元素都和剩下的每一个元素比较一次，则第一次比较运算量是n-1,第二次是n-2，直到n-n+1，运算总量就是n*n-(1+2+...+n)+1，约为n²
	public static int fetchTheSmallestNumberInArray(int[] target) {
		// 取出数组的第一个元素，将它和它之后的元素所组成的数组进行依次比较，如果该元素最小（最佳情况），直接将该元素赋值给result，程序结束，否则将第一个发现的比该元素小的元素赋值给result，下次循环开始，从该数组中第一个等于result的元素开始循环。总之就是只有一种情况会将result赋值给第一个元素，就是它遍历比较了剩下的元素之后仍旧是最小的，否则result的值一定是第一个比这个元素还小的元素。
		// 初始条件：取出数组中的第一个元素，和数组中剩下的元素依次比较，此时key为0，返回第一个比它还小的元素，并记住该元素的下标，下次迭代直接从这个元素开始迭代，否则赋值第一个元素的值给result，程序退出
		// 保持迭代：既然上次迭代没有确定下来最小结果，则数组下标的值肯定不是0，用数组下标取出数组中的元素作为起点，和数组中该元素之后的元素依次进行比较
		// 终止条件：某个元素和其后面的所有元素比较之后仍为最小值，则将该元素赋值给result，程序退出；或者发现被比较元素已经是数组中最后一个元素了，则直接将这个元素赋值给result，程序退出。
		// 列出了上面的循环不变式，我们就知道遍历的循环语句块的全部业务逻辑了。于是有了下面代码
		int result = 0;
		int key = 0;
		for (int i = 0; i < target.length; i++) {
			// 我们不需要重新定义一个数组，只要定义一个新的下标
			int j = i + 1;
			// 如果已经遍历到数组的最后一个元素了还没找到最小元素，那么这个最后的就是最小元素了，不用比较直接返回了
			// 如果某个元素既是target[key]又是列表中最后一个元素，即key = target.length，则程序应先确定是否
			// key = target.length成立，不成立再从target[key]开始比较
			if (j == target.length) {
				result = target[i];
				break;
			}
			// 当key是数组倒数第二个及以前的元素时，说明数组中还有没比较完的元素，我们就继续比较
			while (key < target.length - 1) {
				j = key + 1;
				// key元素和其之后的元素进行比较，如果大于后面的元素则对换两个比较元素，使得key元素总是已经比较的元素中最小的
				// 对于target[key] = target[j]的情况，我们选择保留被比较的元素target[key]
				// 每次ifelse都是一次元素比较，可以在这个语句块里进行计数
				if (target[key] < target[j]) {
					result = target[j];
					break;
				}
				key++;
			}
		}
		System.out.println("The smallest number in array:: " + result);
		System.out.println("Output the array after result:: " + Arrays.toString(target));
		return result;
	}
	private static int[] sortedArr = null;
	private static int[] cacheArr = null;
//	public static void simpleSorting(int[] target) {
//		int len = target.length;
//		if(target == null || len == 0)
//			return;
//		// 只能在第一次执行该方法时初始化一次，以后每次用的都是同一个数组，这样保证每次选出的最小值都可以放进同一个数组，直到放满。
//		if(sortedArr == null)
//			sortedArr = new int[len];
//		
//			// 找到一个数组中最小的那个元素
//			int small = fetchTheSmallestNumberInArray(target);
//			// 把找到的最小元素放到一个新的数组中
//			sortedArr[i] = small;
//			// 把剩下的未比较的元素放到一个新的数组中继续比较找最小值
//			if(len-1-i == 0)
//				return;
//			cacheArr = new int[len-1-i];
//			
//			// 递归对数组中剩下的元素执行相同的操作：获取最小值 - 放到sortedArr数组中 - 拷贝剩余数组元素（如果有的话）到一个新的数组进行下一次递归
//			simpleSorting(cacheArr);
//	}

	public static void main(String[] args) {
//		int[] target = { 10, 9, 8, 7, 6 };
		int[] target = { 6, 7, 8, 9, 10 };
		// 最简单的数组比较，如果最坏情况数组倒序排列则可以验证命题：对于长度为N的数组，选择排序需要进行N²/2次比较和N次交换。
//		simpleSorting(target);
//		System.out.println(Arrays.toString(intArraySelectionDemo(target)));
		fetchTheSmallestNumberInArray(target);
	}
}
