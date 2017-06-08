package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 直接选择排序：关键是n-1趟比较，虽然比较费时，但是实现原理简单
 * 即：依次进行n-1趟比较，第i趟比较将第i大的值选出放在i位置上
 * 如果第i位的数据大于j位置的数据，交换它们。
 */
//定义一个数据包装类
class SelectSortDataWrap implements Comparable<SelectSortDataWrap>
{
	int data;
	String flag;
	public SelectSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个SelectSortDataWrap的大小
	public int compareTo(SelectSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class SelectSort
{
	public static void selectSort(SelectSortDataWrap[] data) 
	{
		System.out.println("开始排序");
		int arrayLength = data.length;
		//依次进行n－1趟比较, 第i趟比较将第i大的值选出放在i位置上。
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			@SuppressWarnings("unused")
			int minIndex = i ;
			// 算法的核心实现：开始
			//第i个数据只需和它后面的数据比较
			for (int j = i + 1 ; j < arrayLength ; j++ )
			{				
				//如果第i位置的数据 > j位置的数据, 交换它们
				if (data[i].compareTo(data[j]) > 0)
				{
					SelectSortDataWrap tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
			// 算法的核心实现：结束
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args)
	{
		SelectSortDataWrap[] data = {
			new SelectSortDataWrap(21 , ""),
			new SelectSortDataWrap(30 , ""),
			new SelectSortDataWrap(49 , ""),
			new SelectSortDataWrap(30 , "*"),
			new SelectSortDataWrap(16 , ""),
			new SelectSortDataWrap(9 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		selectSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
