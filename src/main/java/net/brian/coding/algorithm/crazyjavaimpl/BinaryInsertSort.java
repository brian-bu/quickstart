package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 折半插入排序：直接插入排序没有利用到“前面0~i-1”这一点，折半插入排序正是针对这一点做的改进。
 * Arrays.binarySearch()方法就是利用了这个排序方法实现的，用于从有序数组中查找指定元素
 * @see java.util.Arrays.binarySearch0(int[], int, int, int)
 */
//定义一个数据包装类
class BinaryInsertSortDataWrap implements Comparable<BinaryInsertSortDataWrap>
{
	int data;
	String flag;
	public BinaryInsertSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个BinaryInsertSortDataWrap的大小
	public int compareTo(BinaryInsertSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class BinaryInsertSort
{
	public static void binaryInsertSort(BinaryInsertSortDataWrap[] data)
	{
		System.out.println("开始排序：\n");
		int arrayLength = data.length;
		for(int i = 1 ; i < arrayLength ; i++)
		{
			//当整体后移时，保证data[i]的值不会丢失
			BinaryInsertSortDataWrap tmp = data[i];
			int low = 0;
			int high = i - 1;
			while(low <= high)
			{
				//找出low、high中间的索引
				int mid = (low + high) / 2;
				// 算法核心实现：开始
				//如果tmp值大于low、high中间元素的值
				if(tmp.compareTo(data[mid]) > 0)
				{
					//限制在索引大于mid的那一半中搜索
					low = mid + 1;
				} 
				else
				{
					//限制在索引小于mid的那一半中搜索
					high = mid - 1;
				}
				// 算法核心实现：结束
			}
			//将low到i处的所有元素向后整体移一位
			for(int j = i ; j > low ; j--)
			{
				data[j] = data[j - 1];
			}
			//最后将tmp的值插入合适位置
			data[low] = tmp;
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args)
	{
		BinaryInsertSortDataWrap[] data = {
			new BinaryInsertSortDataWrap(9 , ""),
			new BinaryInsertSortDataWrap(-16 , ""),
			new BinaryInsertSortDataWrap(21 , "*"),
			new BinaryInsertSortDataWrap(23 , ""),
			new BinaryInsertSortDataWrap(-30 , ""),
			new BinaryInsertSortDataWrap(-49 , ""),
			new BinaryInsertSortDataWrap(21 , ""),
			new BinaryInsertSortDataWrap(30 , "*"),
			new BinaryInsertSortDataWrap(30 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		binaryInsertSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
