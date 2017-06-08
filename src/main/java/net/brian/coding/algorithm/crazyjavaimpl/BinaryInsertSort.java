package net.brian.coding.algorithm.crazyjavaimpl;

/**
 * �۰��������ֱ�Ӳ�������û�����õ���ǰ��0~i-1����һ�㣬�۰�����������������һ�����ĸĽ���
 * Arrays.binarySearch()��������������������򷽷�ʵ�ֵģ����ڴ����������в���ָ��Ԫ��
 * @see java.util.Arrays.binarySearch0(int[], int, int, int)
 */
//����һ�����ݰ�װ��
class BinaryInsertSortDataWrap implements Comparable<BinaryInsertSortDataWrap>
{
	int data;
	String flag;
	public BinaryInsertSortDataWrap(int data, String flag)
	{
		this.data = data;
		this.flag = flag;
	}
	public String toString()
	{
		return data + flag;
	}
	//����dataʵ����������������BinaryInsertSortDataWrap�Ĵ�С
	public int compareTo(BinaryInsertSortDataWrap dw)
	{
		return this.data > dw.data ? 1 
			: (this.data == dw.data ? 0 : -1);
	}
}
public class BinaryInsertSort
{
	public static void binaryInsertSort(BinaryInsertSortDataWrap[] data)
	{
		System.out.println("��ʼ����\n");
		int arrayLength = data.length;
		for(int i = 1 ; i < arrayLength ; i++)
		{
			//���������ʱ����֤data[i]��ֵ���ᶪʧ
			BinaryInsertSortDataWrap tmp = data[i];
			int low = 0;
			int high = i - 1;
			while(low <= high)
			{
				//�ҳ�low��high�м������
				int mid = (low + high) / 2;
				// �㷨����ʵ�֣���ʼ
				//���tmpֵ����low��high�м�Ԫ�ص�ֵ
				if(tmp.compareTo(data[mid]) > 0)
				{
					//��������������mid����һ��������
					low = mid + 1;
				} 
				else
				{
					//����������С��mid����һ��������
					high = mid - 1;
				}
				// �㷨����ʵ�֣�����
			}
			//��low��i��������Ԫ�����������һλ
			for(int j = i ; j > low ; j--)
			{
				data[j] = data[j - 1];
			}
			//���tmp��ֵ�������λ��
			data[low] = tmp;
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args)
	{
		BinaryInsertSortDataWrap[] data = {
			new BinaryInsertSortDataWrap(9 , ""),
			new BinaryInsertSortDataWrap(-16 , ""),
			new BinaryInsertSortDataWrap(21 , "*"),
			new BinaryInsertSortDataWrap(23 , ""),
			new BinaryInsertSortDataWrap(-30 , ""),
			new BinaryInsertSortDataWrap(-49 , ""),
			new BinaryInsertSortDataWrap(21 , ""),
			new BinaryInsertSortDataWrap(30 , "*"),
			new BinaryInsertSortDataWrap(30 , "")
		};
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		binaryInsertSort(data);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}
