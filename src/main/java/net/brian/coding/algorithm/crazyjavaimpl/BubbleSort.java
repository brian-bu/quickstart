package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 交换排序：主体操作是对数据组中的数据不断进行交换操作，主要有冒泡排序和快速排序
 * 
 * 冒泡排序：
 * 每趟交换将最大的元素挤到最后的位置，同时其它元素随之前移
 * 这种前移有可能顺便就理顺了前面元素的顺序，因此一旦某趟没有交换发生则提前结束排序
 */
//定义一个数据包装类
class BubbleSortDataWrap implements Comparable<BubbleSortDataWrap>
{
	int data;
	String flag;
	public BubbleSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个BubbleSortDataWrap的大小
	public int compareTo(BubbleSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class BubbleSort
{
	public static void bubbleSort(BubbleSortDataWrap[] data) 
	{
		System.out.println("开始排序");
		int arrayLength = data.length;
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			boolean flag = false;
			for (int j = 0; j < arrayLength - 1 - i ; j++ )
			{
				//如果j索引处的元素大于j+1索引处的元素
				if (data[j].compareTo(data[j + 1]) > 0)
				{
					//交换它们
					BubbleSortDataWrap tmp = data[j + 1];
					data[j + 1] = data[j];
					data[j] = tmp;
					flag = true;
				}
			}
			System.out.println(java.util.Arrays.toString(data));
			//如果某趟没有发生交换，则表明已处于有序状态
			if (!flag)
			{
				break;
			}
		}
	}
	public static void main(String[] args)
	{
		BubbleSortDataWrap[] data = {
			new BubbleSortDataWrap(9 , ""),
			new BubbleSortDataWrap(16 , ""),
			new BubbleSortDataWrap(21 , "*"),
			new BubbleSortDataWrap(23 , ""),
			new BubbleSortDataWrap(30 , ""),
			new BubbleSortDataWrap(49 , ""),
			new BubbleSortDataWrap(21 , ""),
			new BubbleSortDataWrap(30 , "*")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		bubbleSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
