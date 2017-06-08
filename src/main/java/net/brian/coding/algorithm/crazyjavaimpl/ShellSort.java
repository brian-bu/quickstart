package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 希尔排序：
 * 加大直接插入排序中元素之间的间隔，并在这些有间隔的元素中进行插入排序，从而使数据项大跨度移动
 * 选择增量h作为元素间隔的大小的表示方法，当h值较大时，数据项每一趟排序需要移动元素的个数增多
 * 但数据项移动的距离很长，这相对于普通的插入排序更有效率，每一趟排序之后都会减小h的值
 * 当h减小时，每一趟排序需要移动元素个数增多，此时数据项已经接近于他们排序后最终的位置
 * 直到最后完成增量为1的希尔排序
 * 
 * 增量为1的希尔排序就是直接插入排序
 */
//定义一个数据包装类
class ShellSortDataWrap implements Comparable<ShellSortDataWrap>
{
	int data;
	String flag;
	public ShellSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个ShellSortDataWrap的大小
	public int compareTo(ShellSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class ShellSort
{
	public static void shellSort(ShellSortDataWrap[] data) 
	{
		System.out.println("开始排序：");
		int arrayLength = data.length;
		//h变量保存可变增量
		int h = 1;
		//按h * 3 + 1得到增量序列的最大值
		while(h <= arrayLength / 3)
		{
			h = h * 3 + 1;
		}
		while(h > 0)
		{
			System.out.println("===h的值:" + h + "===");
			for (int i = h ; i < arrayLength ; i++ )
			{
				//当整体后移时，保证data[i]的值不会丢失
				ShellSortDataWrap tmp = data[i];
				//i索引处的值已经比前面所有值都大，表明已经有序，无需插入
				//（i-1索引之前的数据已经有序的，i-1索引处元素的值就是最大值）
				if (data[i].compareTo(data[i - h]) < 0)
				{
					int j = i - h;
					//整体后移h格
					for ( ; j >= 0 && data[j].compareTo(tmp) > 0 ; j-=h)
					{
						data[j + h] = data[j];
					}
					//最后将tmp的值插入合适位置
					data[j + h] = tmp;
				}
				System.out.println(java.util.Arrays.toString(data));
			}
			h = (h - 1) / 3;
		}
	}
	public static void main(String[] args)
	{
		ShellSortDataWrap[] data = {
			new ShellSortDataWrap(9 , ""),
			new ShellSortDataWrap(-16 , ""),
			new ShellSortDataWrap(21 , "*"),
			new ShellSortDataWrap(23 , ""),
			new ShellSortDataWrap(-30 , ""),
			new ShellSortDataWrap(-49 , ""),
			new ShellSortDataWrap(21 , ""),
			new ShellSortDataWrap(30 , "*"),
			new ShellSortDataWrap(30 , ""),
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		shellSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
