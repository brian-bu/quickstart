package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 直接插入排序：依次将待排序的数据元素按其关键字值的大小插入前面的有序序列。它仅需缓存一个数据单元，空间效率很好
 * 
 * 对于直接插入排序，如果一个很小的数据单元位于很靠近右端的位置，为了把这个数据单元移动到左边正确的位置
 * 中间所有的数据单元都需要向右移动一格，平均下来每个数据项都执行了近N次的复制，每个数据项平均移动N/2格
 * 因此总共复制N*N/2次，所以插入排序的执行效率是O(N^2)
 * 希尔排序是针对这一问题的改进：
 * @see net.brian.coding.algorithm.crazyjavaimpl.ShellSort
 * 
 */
//定义一个数据包装类
class InsertSortDataWrap implements Comparable<InsertSortDataWrap>
{
	int data;
	String flag;
	public InsertSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个InsertSortDataWrap的大小
	public int compareTo(InsertSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class InsertSort
{
	public static void insertSort(InsertSortDataWrap[] data) 
	{
		System.out.println("开始排序：\n");
		int arrayLength = data.length;
		for (int i = 1 ; i < arrayLength ; i++ )
		{
			//当整体后移时，保证data[i]的值不会丢失
			InsertSortDataWrap tmp = data[i];
			//i索引处的值已经比前面所有值都大，表明已经有序，无需插入
			//（i-1索引之前的数据已经有序的，i-1索引处元素的值就是最大值）
			if (data[i].compareTo(data[i - 1]) < 0)
			{
				int j = i - 1;
				//整体后移一格
				for ( ; j >= 0 && data[j].compareTo(tmp) > 0 ; j--)
				{
					data[j + 1] = data[j];
				}
				//最后将tmp的值插入合适位置
				data[j + 1] = tmp;
			}
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args)
	{
		InsertSortDataWrap[] data = {
			new InsertSortDataWrap(9 , ""),
			new InsertSortDataWrap(-16 , ""),
			new InsertSortDataWrap(21 , "*"),
			new InsertSortDataWrap(23 , ""),
			new InsertSortDataWrap(-30 , ""),
			new InsertSortDataWrap(-49 , ""),
			new InsertSortDataWrap(21 , ""),
			new InsertSortDataWrap(30 , "*"),
			new InsertSortDataWrap(30 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		insertSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
