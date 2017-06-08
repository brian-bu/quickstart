package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * ֱ��ѡ�����򣺹ؼ���n-1�˱Ƚϣ���Ȼ�ȽϷ�ʱ������ʵ��ԭ���
 * �������ν���n-1�˱Ƚϣ���i�˱ȽϽ���i���ֵѡ������iλ����
 * �����iλ�����ݴ���jλ�õ����ݣ��������ǡ�
 */
//����һ�����ݰ�װ��
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
	//����dataʵ����������������SelectSortDataWrap�Ĵ�С
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
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		//���ν���n��1�˱Ƚ�, ��i�˱ȽϽ���i���ֵѡ������iλ���ϡ�
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			@SuppressWarnings("unused")
			int minIndex = i ;
			// �㷨�ĺ���ʵ�֣���ʼ
			//��i������ֻ�������������ݱȽ�
			for (int j = i + 1 ; j < arrayLength ; j++ )
			{				
				//�����iλ�õ����� > jλ�õ�����, ��������
				if (data[i].compareTo(data[j]) > 0)
				{
					SelectSortDataWrap tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
			// �㷨�ĺ���ʵ�֣�����
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		selectSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
