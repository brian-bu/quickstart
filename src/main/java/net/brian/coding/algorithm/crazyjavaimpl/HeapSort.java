package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * ������
 * 
 * С���ѣ���ȫ�����������нڵ��ֵ��С���������ӽڵ��ֵ����������ڵ��ֵ��Ȼ��С
 * �󶥶ѣ���ȫ�����������нڵ��ֵ�������������ӽڵ��ֵ����������ڵ��ֵ��Ȼ��� ������Ĺؼ�������ν���һ�����������Ķѡ����ѵĹ������£�
 * a.���Ƚ�����������ת������ȫ�������� 
 * b.��lastIndex�ڵ�ĸ��ڵ㿪ʼ�����������ӽڵ㣬ѡ�����нϴ�ıȽϣ������ӽڵ�С�򽻻����ߵ�λ��
 * c.ͨ�����ϵıȽϺͽ���λ�����չ�����һ���󶥶ѡ� ������ÿ�ζ��ǽ��Ѳ�ѡ���öѵ������Сֵ���������������Ȼ��һ��ѡ������
 * ֻ�������������ͨ�����νṹ���沿�ֱȽϽ���������Ƚ�ֱ��ѡ��������Կ��Լ��ٱȽϴ���
 * 
 */
//����һ�����ݰ�װ��
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
	//����dataʵ����������������HeapSortDataWrap�Ĵ�С
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
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		//ѭ������
		for (int i = 0; i < arrayLength - 1 ; i++ )
		{
			//����
			builMaxdHeap(data , arrayLength - 1 - i);
			//�����Ѷ������һ��Ԫ��
			swap(data , 0 , arrayLength - 1 - i);
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	// �㷨����ʵ�֣���ʼ
	//��data�����0��lastIndex���󶥶�
	private static void builMaxdHeap(HeapSortDataWrap[] data , int lastIndex)
	{
		//��lastIndex���ڵ㣨���һ���ڵ㣩�ĸ��ڵ㿪ʼ
		for (int i = (lastIndex - 1) / 2 ; i >= 0  ; i--)
		{
			//k���浱ǰ�����жϵĽڵ�
			int k = i;
			//�����ǰk�ڵ���ӽڵ����
			while (k * 2 + 1 <= lastIndex)
			{
				//k�ڵ�����ӽڵ������
				int biggerIndex = 2 * k  + 1; 
				//���biggerIndexС��lastIndex����biggerIndex + 1
				//�����k�ڵ�����ӽڵ����
				if (biggerIndex < lastIndex)
				{
					 //������ӽڵ��ֵ�ϴ�
					if(data[biggerIndex].compareTo(data[biggerIndex + 1]) < 0)
					{
						//biggerIndex���Ǽ�¼�ϴ��ӽڵ������
						biggerIndex++; 
					}
				}
				//���k�ڵ��ֵС����ϴ��ӽڵ��ֵ
				if(data[k].compareTo(data[biggerIndex]) < 0)
				{
					//��������
					swap(data , k , biggerIndex);
					//��biggerIndex����k����ʼwhileѭ������һ��ѭ����
					//���±�֤k�ڵ��ֵ�����������ӽڵ��ֵ��
					k = biggerIndex;
				}
				else
				{
					break;
				}
			}
		}
	}
	
	//����data������i��j������������Ԫ��
	// �������е������㷨���漰������λ�ã������������Ҳ�Ǳ������յ�
	private static void swap(HeapSortDataWrap[] data , int i , int j)
	{
		HeapSortDataWrap tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	// �㷨����ʵ�֣�����
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
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		heapSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
