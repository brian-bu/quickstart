package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * ֱ�Ӳ����������ν������������Ԫ�ذ���ؼ���ֵ�Ĵ�С����ǰ����������С������軺��һ�����ݵ�Ԫ���ռ�Ч�ʺܺ�
 * 
 * ����ֱ�Ӳ����������һ����С�����ݵ�Ԫλ�ںܿ����Ҷ˵�λ�ã�Ϊ�˰�������ݵ�Ԫ�ƶ��������ȷ��λ��
 * �м����е����ݵ�Ԫ����Ҫ�����ƶ�һ��ƽ������ÿ�������ִ���˽�N�εĸ��ƣ�ÿ��������ƽ���ƶ�N/2��
 * ����ܹ�����N*N/2�Σ����Բ��������ִ��Ч����O(N^2)
 * ϣ�������������һ����ĸĽ���
 * @see net.brian.coding.algorithm.crazyjavaimpl.ShellSort
 * 
 */
//����һ�����ݰ�װ��
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
	//����dataʵ����������������InsertSortDataWrap�Ĵ�С
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
		System.out.println("��ʼ����\n");
		int arrayLength = data.length;
		for (int i = 1 ; i < arrayLength ; i++ )
		{
			//���������ʱ����֤data[i]��ֵ���ᶪʧ
			InsertSortDataWrap tmp = data[i];
			//i��������ֵ�Ѿ���ǰ������ֵ���󣬱����Ѿ������������
			//��i-1����֮ǰ�������Ѿ�����ģ�i-1������Ԫ�ص�ֵ�������ֵ��
			if (data[i].compareTo(data[i - 1]) < 0)
			{
				int j = i - 1;
				//�������һ��
				for ( ; j >= 0 && data[j].compareTo(tmp) > 0 ; j--)
				{
					data[j + 1] = data[j];
				}
				//���tmp��ֵ�������λ��
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		insertSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
