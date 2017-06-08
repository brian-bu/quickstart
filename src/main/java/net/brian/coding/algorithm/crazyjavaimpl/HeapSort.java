package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * 堆排序
 * 
 * 小顶堆：完全二叉树中所有节点的值都小于其左右子节点的值，则此树根节点的值必然最小
 * 大顶堆：完全二叉树中所有节点的值都大于其左右子节点的值，则此树根节点的值必然最大 堆排序的关键在于如何建立一个上述这样的堆。建堆的过程如下：
 * a.首先将给定数据组转换成完全二叉树； 
 * b.从lastIndex节点的父节点开始，若它存在子节点，选择其中较大的比较，若比子节点小则交换二者的位置
 * c.通过不断的比较和交换位置最终构建成一个大顶堆。 堆排序每次都是建堆并选出该堆的最大最小值，因此它本质上依然是一种选择排序
 * 只不过堆排序可以通过树形结构保存部分比较结果，因此相比较直接选择排序而言可以减少比较次数
 * 
 */
//定义一个数据包装类
class HeapSortDataWrap implements Comparable<HeapSortDataWrap>
{
	int data;
	String flag;
	public HeapSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//根据data实例变量来决定两个HeapSortDataWrap的大小
	public int compareTo(HeapSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class HeapSort
{
	public static void heapSort(HeapSortDataWrap[] data) 
	{
		System.out.println("开始排序");
		int arrayLength = data.length;
		//循环建堆
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			//建堆
			builMaxdHeap(data , arrayLength - 1 - i);
			//交换堆顶和最后一个元素
			swap(data , 0 , arrayLength - 1 - i);
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	// 算法核心实现：开始
	//对data数组从0到lastIndex建大顶堆
	private static void builMaxdHeap(HeapSortDataWrap[] data , int lastIndex)
	{
		//从lastIndex处节点（最后一个节点）的父节点开始
		for (int i = (lastIndex - 1) / 2 ; i >= 0  ; i--)
		{
			//k保存当前正在判断的节点
			int k = i;
			//如果当前k节点的子节点存在
			while (k * 2 + 1 <= lastIndex)
			{
				//k节点的左子节点的索引
				int biggerIndex = 2 * k  + 1; 
				//如果biggerIndex小于lastIndex，即biggerIndex + 1
				//代表的k节点的右子节点存在
				if (biggerIndex < lastIndex)
				{
					 //如果右子节点的值较大
					if(data[biggerIndex].compareTo(data[biggerIndex + 1]) < 0)
					{
						//biggerIndex总是记录较大子节点的索引
						biggerIndex++; 
					}
				}
				//如果k节点的值小于其较大子节点的值
				if(data[k].compareTo(data[biggerIndex]) < 0)
				{
					//交换它们
					swap(data , k , biggerIndex);
					//将biggerIndex赋给k，开始while循环的下一次循环，
					//重新保证k节点的值大于其左、右子节点的值。
					k = biggerIndex;
				}
				else
				{
					break;
				}
			}
		}
	}
	
	//交换data数组中i、j两个索引处的元素
	// 几乎所有的排序算法都涉及到交换位置，所以这个方法也是必须掌握的
	private static void swap(HeapSortDataWrap[] data , int i , int j)
	{
		HeapSortDataWrap tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	// 算法核心实现：结束
	public static void main(String[] args)
	{
		HeapSortDataWrap[] data = {
			new HeapSortDataWrap(21 , ""),
			new HeapSortDataWrap(30 , ""),
			new HeapSortDataWrap(49 , ""),
			new HeapSortDataWrap(30 , "*"),
			new HeapSortDataWrap(21 , "*"),
			new HeapSortDataWrap(16 , ""),
			new HeapSortDataWrap(9 , "")
		};
		System.out.println("排序之前：\n"
			+ java.util.Arrays.toString(data));
		heapSort(data);
		System.out.println("排序之后：\n" 
			+ java.util.Arrays.toString(data));
	}
}
