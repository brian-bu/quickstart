package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * �Ľ�ֱ��ѡ���������ν���n-1�˱Ƚϣ���i�˱ȽϽ���i���ֵѡ������iλ����
 * ����ͬ�������һ��minIndex��Զ�������˱Ƚ�����Сֵ������
 * ��ʱ�����õ�iλ��jλ�Ľ��бȽϣ�����ֱ�������minIndex��jλ���ݱȽ�
 * ���minIndex����jλ�õ����ݽ�j��ֵ����minIndex��
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
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		//���ν���n��1�˱Ƚ�, ��i�˱ȽϽ���i���ֵѡ������iλ���ϡ�
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			//minIndex��Զ�������˱Ƚ�����Сֵ������
			int minIndex = i ;
			// �㷨�ĺ���ʵ�֣���ʼ
			//��i������ֻ�������������ݱȽ�
			for (int j = i + 1 ; j < arrayLength ; j++ )
			{
				//�����minIndexλ�õ����� > jλ�õ�����
				if (data[minIndex].compareTo(data[j]) > 0)
				{
					//��j��ֵ����minIndex
					minIndex = j;
				}
			}
			//ÿ�˱Ƚ���ཻ��һ��
			if (minIndex != i)
			{
				SelectSort2DataWrap tmp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = tmp;
			}
			// �㷨�ĺ���ʵ�֣�����
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		selectSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}