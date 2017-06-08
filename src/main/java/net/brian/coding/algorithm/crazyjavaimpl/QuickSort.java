package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 交换排序：主体操作是对数据组中的数据不断进行交换操作，主要有冒泡排序和快速排序
 * 
 * 快速排序：
 * a.关键在于第一趟要做的事情，先选出一个数据作为分界值，
 * b.将所有比分界值小的元素放在左边
 * c.比分界值大的放在右边
 * d.接下来对左右两边元素递归执行上述步骤

 */
//定义一个数据包装类
class QuickSortDataWrap implements Comparable<QuickSortDataWrap>
{
	int data;
	String flag;
	public QuickSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个QuickSortDataWrap的大小
	public int compareTo(QuickSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class QuickSort
{
	//将指定数组的i和j索引处的元素交换
    private static void swap(QuickSortDataWrap[] data, int i, int j)
    {
        QuickSortDataWrap tmp;
        tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
	//对data数组中从start～end索引范围的子序列进行处理
	//使之满足所有小于分界值的放在左边，所有大于分界值的放在右边
	private static void subSort(QuickSortDataWrap[] data
		, int start , int end)
	{
		//需要排序
		if (start < end)
		{
			//以第一个元素作为分界值
			QuickSortDataWrap base = data[start];
			//i从左边搜索，搜索大于分界值的元素的索引
			int i = start;
			//j从右边开始搜索，搜索小于分界值的元素的索引
			int j = end + 1;
			while(true)
			{
				//找到大于分界值的元素的索引，或i已经到了end处
				while(i < end && data[++i].compareTo(base) <= 0);
				//找到小于分界值的元素的索引，或j已经到了start处
				while(j > start && data[--j].compareTo(base) >= 0);
				if (i < j)
				{
					swap(data , i , j);
				}
				else
				{
					break;
				}
			}
			swap(data , start , j);
			//递归左子序列
			subSort(data , start , j - 1);
			//递归右边子序列
			subSort(data , j + 1, end);
		}
	}
	public static void quickSort(QuickSortDataWrap[] data) 
	{
		subSort(data , 0 , data.length - 1);
	}
	public static void main(String[] args)
	{
		QuickSortDataWrap[] data = {
			new QuickSortDataWrap(9 , ""),
			new QuickSortDataWrap(-16 , ""),
			new QuickSortDataWrap(21 , "*"),
			new QuickSortDataWrap(23 , ""),
			new QuickSortDataWrap(-30 , ""),
			new QuickSortDataWrap(-49 , ""),
			new QuickSortDataWrap(21 , ""),
			new QuickSortDataWrap(30 , "*"),
			new QuickSortDataWrap(13 , "*")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		quickSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
