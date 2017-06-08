package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * ����������������Ƕ��������е����ݲ��Ͻ��н�����������Ҫ��ð������Ϳ�������
 * 
 * ð������
 * ÿ�˽���������Ԫ�ؼ�������λ�ã�ͬʱ����Ԫ����֮ǰ��
 * ����ǰ���п���˳�����˳��ǰ��Ԫ�ص�˳�����һ��ĳ��û�н�����������ǰ��������
 */
//����һ�����ݰ�װ��
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
	//����dataʵ����������������BubbleSortDataWrap�Ĵ�С
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
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			boolean flag = false;
			for (int j = 0; j < arrayLength - 1 - i ; j++ )
			{
				//���j��������Ԫ�ش���j+1��������Ԫ��
				if (data[j].compareTo(data[j + 1]) > 0)
				{
					//��������
					BubbleSortDataWrap tmp = data[j + 1];
					data[j + 1] = data[j];
					data[j] = tmp;
					flag = true;
				}
			}
			System.out.println(java.util.Arrays.toString(data));
			//���ĳ��û�з���������������Ѵ�������״̬
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		bubbleSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
