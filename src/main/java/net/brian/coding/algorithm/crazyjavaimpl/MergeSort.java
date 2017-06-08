package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * �鲢����
 * ���ڹ鲢����ؼ����㷨���ǡ��ϲ������ϲ��㷨���岽�����£�
 * a.�����ʼֵΪ0�ı���i����A������ÿ��Ԫ�ص�����
 * b.�����ʼֵΪ0�ı���j����B������ÿ��Ԫ�ص�����
 * c.��A[i]��B[j]���бȽϣ�����С�ĸ��Ƶ�һ����ʱ����
 * d.��A[i]<B[j]��i++����A[i]>B[j]��j++
 * �������Ͳ��ϵ�ִ���������裬ֱ���õ����������
 * ˵���ˣ��鲢������ǰ����ݴ�һ������ȡ��������ȡ�߱Ƚϴ�С���ȵ��ŵ���һ��������ʱԪ�����ź���
 * ��Ȼ�鲢�����ǰ����A��B����������������Ѿ�����
 * 
 * �ռ�Ч�ʺܲ��Ҫһ���ʹ���������ͬ����С�����鱸��
 */
class MergeSortDataWrap implements Comparable<MergeSortDataWrap>
{
	int data;
	String flag;
	public MergeSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//����dataʵ����������������MergeSortDataWrap�Ĵ�С
	public int compareTo(MergeSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class MergeSort 
{
	//���ù鲢�����㷨������data�������� 
	public static void mergeSort(MergeSortDataWrap[] data) 
	{
		//�鲢���� 
		sort(data , 0 , data.length - 1);
	}
	/** 
	 * ��������left��right��Χ������Ԫ�ؽ��й鲢���� 
	 * @param data �����������
	 * @param left �����������ĵ�һ��Ԫ������ 
	 * @param right ���������������һ��Ԫ�ص����� 
	 */ 
	private static void sort(MergeSortDataWrap[] data
		 , int left, int right) 
	{ 
		if (left < right) 
		{
			//�ҳ��м�����
			int center = (left + right) / 2;
			//�����������еݹ�
			sort(data, left, center); 
			//���ұ�������еݹ�
			sort(data, center + 1, right); 
			//�ϲ�
			merge(data, left, center, right); 
		} 
	} 
	/** 
	 * ������������й鲢���鲢ǰ���������Ѿ����򣬹鲢����Ȼ���� 
	 * @param data ������� 
	 * @param left ������ĵ�һ��Ԫ�ص����� 
	 * @param center ����������һ��Ԫ�ص�������center+1���������һ��Ԫ�ص�����
	 * @param right ����������һ��Ԫ�ص����� 
	 */ 
	private static void merge(MergeSortDataWrap[] data
		, int left, int center, int right) 
	{
		//����һ������������г�����ͬ����ʱ���� 
		MergeSortDataWrap[] tmpArr = new MergeSortDataWrap[data.length];
		int mid = center + 1;
		//third��¼�м����������
		int third = left; 
		int tmp = left; 
		while (left <= center && mid <= right) 
		{ 
			//������������ȡ��С�ķ����м����� 
			if (data[left].compareTo(data[mid]) <= 0) 
			{ 
				tmpArr[third++] = data[left++]; 
			} 
			else
			{
				tmpArr[third++] = data[mid++]; 
			}
		} 
		//ʣ�ಿ�����η����м����� 
		while (mid <= right) 
		{ 
			tmpArr[third++] = data[mid++]; 
		} 
		while (left <= center) 
		{ 
			tmpArr[third++] = data[left++]; 
		} 
		//���м������е����ݸ��ƿ���ԭ����
		//(ԭleft��right��Χ�����ݸ��ƻ�ԭ����) 
		while (tmp <= right)
		{
			data[tmp] = tmpArr[tmp++]; 
		}
	} 
	public static void main(String[] args)
	{
		MergeSortDataWrap[] data = {
			new MergeSortDataWrap(9 , ""),
			new MergeSortDataWrap(-16 , ""),
			new MergeSortDataWrap(21 , "*"),
			new MergeSortDataWrap(23 , ""),
			new MergeSortDataWrap(-30 , ""),
			new MergeSortDataWrap(-49 , ""),
			new MergeSortDataWrap(21 , ""),
			new MergeSortDataWrap(30 , "*"),
			new MergeSortDataWrap(30 , "")
		};
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		mergeSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
 