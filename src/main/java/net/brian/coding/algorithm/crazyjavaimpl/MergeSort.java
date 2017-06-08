package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 归并排序：
 * 对于归并排序关键的算法就是“合并”，合并算法具体步骤如下：
 * a.定义初始值为0的变量i代表A序列中每个元素的索引
 * b.定义初始值为0的变量j代表B序列中每个元素的索引
 * c.用A[i]和B[j]进行比较，将较小的复制到一个临时数组
 * d.若A[i]<B[j]则i++；若A[i]>B[j]则j++
 * 接下来就不断的执行上述步骤，直至得到有序的序列
 * 说白了，归并排序就是把数据从一个序列取出来，边取边比较大小，等到放到另一个序列中时元素已排好序
 * 当然归并排序的前提是A、B两个待排序的序列已经有序
 * 
 * 空间效率很差，需要一个和待排序数组同样大小的数组备用
 */
class MergeSortDataWrap implements Comparable<MergeSortDataWrap>
{
	int data;
	String flag;
	public MergeSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个MergeSortDataWrap的大小
	public int compareTo(MergeSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class MergeSort 
{
	//利用归并排序算法对数组data进行排序 
	public static void mergeSort(MergeSortDataWrap[] data) 
	{
		//归并排序 
		sort(data , 0 , data.length - 1);
	}
	/** 
	 * 将索引从left到right范围的数组元素进行归并排序 
	 * @param data 待排序的数组
	 * @param left 待排序的数组的第一个元素索引 
	 * @param right 待排序的数组的最后一个元素的索引 
	 */ 
	private static void sort(MergeSortDataWrap[] data
		 , int left, int right) 
	{ 
		if (left < right) 
		{
			//找出中间索引
			int center = (left + right) / 2;
			//对左边数组进行递归
			sort(data, left, center); 
			//对右边数组进行递归
			sort(data, center + 1, right); 
			//合并
			merge(data, left, center, right); 
		} 
	} 
	/** 
	 * 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序。 
	 * @param data 数组对象 
	 * @param left 左数组的第一个元素的索引 
	 * @param center 左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
	 * @param right 右数组的最后一个元素的索引 
	 */ 
	private static void merge(MergeSortDataWrap[] data
		, int left, int center, int right) 
	{
		//定义一个与待排序序列长度相同的临时数组 
		MergeSortDataWrap[] tmpArr = new MergeSortDataWrap[data.length];
		int mid = center + 1;
		//third记录中间数组的索引
		int third = left; 
		int tmp = left; 
		while (left <= center && mid <= right) 
		{ 
			//从两个数组中取出小的放入中间数组 
			if (data[left].compareTo(data[mid]) <= 0) 
			{ 
				tmpArr[third++] = data[left++]; 
			} 
			else
			{
				tmpArr[third++] = data[mid++]; 
			}
		} 
		//剩余部分依次放入中间数组 
		while (mid <= right) 
		{ 
			tmpArr[third++] = data[mid++]; 
		} 
		while (left <= center) 
		{ 
			tmpArr[third++] = data[left++]; 
		} 
		//将中间数组中的内容复制拷回原数组
		//(原left～right范围的内容复制回原数组) 
		while (tmp <= right)
		{
			data[tmp] = tmpArr[tmp++]; 
		}
	} 
	public static void main(String[] args)
	{
		MergeSortDataWrap[] data = {
			new MergeSortDataWrap(9 , ""),
			new MergeSortDataWrap(-16 , ""),
			new MergeSortDataWrap(21 , "*"),
			new MergeSortDataWrap(23 , ""),
			new MergeSortDataWrap(-30 , ""),
			new MergeSortDataWrap(-49 , ""),
			new MergeSortDataWrap(21 , ""),
			new MergeSortDataWrap(30 , "*"),
			new MergeSortDataWrap(30 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		mergeSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
 