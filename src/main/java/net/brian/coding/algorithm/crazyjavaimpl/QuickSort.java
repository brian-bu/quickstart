package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * ����������������Ƕ��������е����ݲ��Ͻ��н�����������Ҫ��ð������Ϳ�������
 * 
 * ��������
 * a.�ؼ����ڵ�һ��Ҫ�������飬��ѡ��һ��������Ϊ�ֽ�ֵ��
 * b.�����бȷֽ�ֵС��Ԫ�ط������
 * c.�ȷֽ�ֵ��ķ����ұ�
 * d.����������������Ԫ�صݹ�ִ����������

 */
//����һ�����ݰ�װ��
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
	//����dataʵ����������������QuickSortDataWrap�Ĵ�С
	public int compareTo(QuickSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class QuickSort
{
	//��ָ�������i��j��������Ԫ�ؽ���
    private static void swap(QuickSortDataWrap[] data, int i, int j)
    {
        QuickSortDataWrap tmp;
        tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
	//��data�����д�start��end������Χ�������н��д���
	//ʹ֮��������С�ڷֽ�ֵ�ķ�����ߣ����д��ڷֽ�ֵ�ķ����ұ�
	private static void subSort(QuickSortDataWrap[] data
		, int start , int end)
	{
		//��Ҫ����
		if (start < end)
		{
			//�Ե�һ��Ԫ����Ϊ�ֽ�ֵ
			QuickSortDataWrap base = data[start];
			//i������������������ڷֽ�ֵ��Ԫ�ص�����
			int i = start;
			//j���ұ߿�ʼ����������С�ڷֽ�ֵ��Ԫ�ص�����
			int j = end + 1;
			while(true)
			{
				//�ҵ����ڷֽ�ֵ��Ԫ�ص���������i�Ѿ�����end��
				while(i < end && data[++i].compareTo(base) <= 0);
				//�ҵ�С�ڷֽ�ֵ��Ԫ�ص���������j�Ѿ�����start��
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
			//�ݹ���������
			subSort(data , start , j - 1);
			//�ݹ��ұ�������
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		quickSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
