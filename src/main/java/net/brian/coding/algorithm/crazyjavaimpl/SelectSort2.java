package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 改进直接选择排序：依次进行n-1趟比较，第i趟比较将第i大的值选出放在i位置上
 * 所不同的是添加一个minIndex永远保留本趟比较中最小值的索引
 * 这时不再用第i位和j位的进行比较，而是直接用这个minIndex和j位数据比较
 * 如果minIndex大于j位置的数据将j的值赋给minIndex。
 */
class SelectSort2DataWrap implements Comparable<SelectSort2DataWrap>
{
	int data;
	String flag;
	public SelectSort2DataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	public int compareTo(SelectSort2DataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class SelectSort2
{
	public static void selectSort(SelectSort2DataWrap[] data) 
	{
		System.out.println("开始排序");
		int arrayLength = data.length;
		//依次进行n－1趟比较, 第i趟比较将第i大的值选出放在i位置上。
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			//minIndex永远保留本趟比较中最小值的索引
			int minIndex = i ;
			// 算法的核心实现：开始
			//第i个数据只需和它后面的数据比较
			for (int j = i + 1 ; j < arrayLength ; j++ )
			{
				//如果第minIndex位置的数据 > j位置的数据
				if (data[minIndex].compareTo(data[j]) > 0)
				{
					//将j的值赋给minIndex
					minIndex = j;
				}
			}
			//每趟比较最多交换一次
			if (minIndex != i)
			{
				SelectSort2DataWrap tmp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = tmp;
			}
			// 算法的核心实现：结束
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args)
	{
		SelectSort2DataWrap[] data = {
			new SelectSort2DataWrap(21 , ""),
			new SelectSort2DataWrap(30 , ""),
			new SelectSort2DataWrap(49 , ""),
			new SelectSort2DataWrap(30 , "*"),
			new SelectSort2DataWrap(16 , ""),
			new SelectSort2DataWrap(9 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		selectSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}