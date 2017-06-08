package net.brian.coding.algorithm.crazyjavaimpl;

import java.util.Arrays;
/**
 * 
 * 这已经不是一种常规排序，倒更像是关键字排序，暂时不看
 *
 */
public class MultiKeyRadixSort
{	
	/**
	 * @param data 待排序的数组	
	 * @param radix  指定关键字拆分的进制。如radix=10，表明按10进制拆分
	 * @param d 指定将关键字拆分成几个子关键字
	 */
	public static void radixSort(int[] data, int radix, int d) 
	{
		System.out.println("开始排序：");
		int arrayLength = data.length;
		//需要一个临时数组
		int[] tmp = new int[arrayLength];
		//buckets数组是桶式排序必须buckets数组
		int[] buckets = new int[radix];
		//依次从最高位的子关键字对待排数据进行排序
		//下面循环中rate用于保存当前计算的位（比如十位时rate=10）
		for(int i = 0 , rate = 1 ; i < d ; i++)
		{
			//重置count数组，开始统计第二个关键字
			Arrays.fill(buckets, 0);
			//将data数组的元素复制到temporary数组中进行缓存
			System.arraycopy(data, 0, tmp, 0, arrayLength);
			//计算每个待排序数据的子关键字
			for(int j = 0 ; j < arrayLength ; j++)
			{
				//计算数据指定位上的子关键字
				int subKey = (tmp[j] / rate) % radix;
				buckets[subKey]++;
			}
			for(int j = 1 ; j < radix ; j++)
			{
				buckets[j] = buckets[j] + buckets[j-1];
			}
			//按子关键字对指定数据进行排序
			for(int m = arrayLength - 1 ; m >= 0 ; m--)
			{
				int subKey = (tmp[m] / rate) % radix;
				data[--buckets[subKey]] = tmp[m];
			}
			System.out.println("对" + rate + "位上子关键字排序：" 
				+ java.util.Arrays.toString(data));
			rate *= radix;
		}
	}
	public static void main(String[] args)
	{
		int[] data = {1100 , 192 , 221 , 12 , 13};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		radixSort(data , 10 , 4);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}