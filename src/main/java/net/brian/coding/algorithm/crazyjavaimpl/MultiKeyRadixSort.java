package net.brian.coding.algorithm.crazyjavaimpl;

import java.util.Arrays;
/**
 * 
 * ���Ѿ�����һ�ֳ������򣬵������ǹؼ���������ʱ����
 *
 */
public class MultiKeyRadixSort
{	
	/**
	 * @param data �����������	
	 * @param radix  ָ���ؼ��ֲ�ֵĽ��ơ���radix=10��������10���Ʋ��
	 * @param d ָ�����ؼ��ֲ�ֳɼ����ӹؼ���
	 */
	public static void radixSort(int[] data, int radix, int d) 
	{
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		//��Ҫһ����ʱ����
		int[] tmp = new int[arrayLength];
		//buckets������Ͱʽ�������buckets����
		int[] buckets = new int[radix];
		//���δ����λ���ӹؼ��ֶԴ������ݽ�������
		//����ѭ����rate���ڱ��浱ǰ�����λ������ʮλʱrate=10��
		for(int i = 0 , rate = 1 ; i < d ; i++)
		{
			//����count���飬��ʼͳ�Ƶڶ����ؼ���
			Arrays.fill(buckets, 0);
			//��data�����Ԫ�ظ��Ƶ�temporary�����н��л���
			System.arraycopy(data, 0, tmp, 0, arrayLength);
			//����ÿ�����������ݵ��ӹؼ���
			for(int j = 0 ; j < arrayLength ; j++)
			{
				//��������ָ��λ�ϵ��ӹؼ���
				int subKey = (tmp[j] / rate) % radix;
				buckets[subKey]++;
			}
			for(int j = 1 ; j < radix ; j++)
			{
				buckets[j] = buckets[j] + buckets[j-1];
			}
			//���ӹؼ��ֶ�ָ�����ݽ�������
			for(int m = arrayLength - 1 ; m >= 0 ; m--)
			{
				int subKey = (tmp[m] / rate) % radix;
				data[--buckets[subKey]] = tmp[m];
			}
			System.out.println("��" + rate + "λ���ӹؼ�������" 
				+ java.util.Arrays.toString(data));
			rate *= radix;
		}
	}
	public static void main(String[] args)
	{
		int[] data = {1100 , 192 , 221 , 12 , 13};
		System.out.println("����֮ǰ��\n"
			+ java.util.Arrays.toString(data));
		radixSort(data , 10 , 4);
		System.out.println("����֮��\n" 
			+ java.util.Arrays.toString(data));
	}
}