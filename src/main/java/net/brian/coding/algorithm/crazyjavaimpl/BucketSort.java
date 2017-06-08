package net.brian.coding.algorithm.crazyjavaimpl;

import java.util.Arrays;
/**
 * 桶式排序：
 * 它不再是一种基于比较的排序方法，它的巧妙之处在于：
 * a.待排序列的所有值处于一个可枚举范围内
 * 比如[9, 5, -1, 8, 5, 7, 3, -3, 1, 3]可枚举范围就是：
 * [-3, -1, 1, 3, 5, 7, 8, 9]一共8个元素
 * b.这个可枚举的范围不可太大，否则开销太大
 * 
 * 桶式排序只需要两轮遍历：
 * 第一轮遍历待排数据，统计每个待排数据落入各桶中的个数
 * 第二轮遍历用于重新计算每个buckets数组元素的值
 * 两轮遍历后就可以得到每个待排数据在有序序列中的位置，然后将各个数据项一次性放入指定位置即可
 * 
 * 桶式排序空间开销比较大，因为它需要两个数组
 * 
 */
class BucketSortDataWrap implements Comparable<BucketSortDataWrap>
{
	int data;
	String flag;
	public BucketSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个BucketSortDataWrap的大小
	public int compareTo(BucketSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class BucketSort
{
	public static void bucketSort(BucketSortDataWrap[] data 
		, int min , int max)
	{
		System.out.println("开始排序：");
		//arrayLength记录待排序数组的长度
		int arrayLength = data.length;
		BucketSortDataWrap[] tmp = new BucketSortDataWrap[arrayLength];
		//buckets数组相当于定义了max - min个桶，
		//buckets数组用于记录待排序元素的信息
		int[] buckets = new int[max - min];	
		//计算每个元素在序列出现的次数
		for(int i = 0 ; i < arrayLength ; i++)
		{
			//buckets数组记录了BucketSortDataWrap出现的次数
			buckets[data[i].data - min]++;
		}
		System.out.println( Arrays.toString(buckets));
		//计算“落入”各桶内的元素在有序序列中的位置
		for(int i = 1 ; i < max - min; i++)
		{
			//前一个bucket的值 + 当前bucket的值 -> 当前bucket新的值
			buckets[i] = buckets[i] + buckets[i - 1];
		}
		//循环结束后，buckets数组元素记录了“落入”前面所有桶和 
		//“落入”当前bucket中元素的总数，
		//也就说：buckets数组元素的值代表了“落入”当前桶的元素在有序序列中的位置
		System.out.println( Arrays.toString(buckets));
		//将data数组中数据完全复制到tmp数组中缓存起来。
		System.arraycopy(data, 0, tmp, 0, arrayLength);
		//根据buckets数组中的信息将待排序列的各元素放入相应的位置。
		for(int k = arrayLength - 1 ; k >=  0 ; k--)
		{
			data[--buckets[tmp[k].data - min]] = tmp[k];
		}
	}
	public static void main(String[] args)
	{
		BucketSortDataWrap[] data = {
			new BucketSortDataWrap(9 , ""),
			new BucketSortDataWrap(5, ""),
			new BucketSortDataWrap(-1, ""),
			new BucketSortDataWrap(8 , ""),
			new BucketSortDataWrap(5 , "*"),
			new BucketSortDataWrap(7 , ""),
			new BucketSortDataWrap(3 , ""),
			new BucketSortDataWrap(-3, ""),
			new BucketSortDataWrap(1 , ""),
			new BucketSortDataWrap(3 , "*")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		bucketSort(data , -3 , 10);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}