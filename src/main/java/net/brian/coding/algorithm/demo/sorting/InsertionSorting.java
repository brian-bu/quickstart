package net.brian.coding.algorithm.demo.sorting;

import java.util.Arrays;

public class InsertionSorting {

	public static int[] intArrayInsertionDemo(int[] target) {
		if (target == null || target.length == 0)
			throw new NullPointerException("Target array is null!");
		// ѭ������ʽ��i = 0��ʼ������ʼ������target[i]���֣�ѭ��ĳ�ε���֮ǰΪ�棬�´ε���֮ǰ��Ϊ�棻
		// i = target.length��ֹ������
		for (int j = 1; j < target.length; j++) {
			int exchange = target[j];
			// Insert target[j] into the sorted sequence target[1 .. j-1].
			int i = j - 1;
			// whileѭ����A[j-1],A[j-2],A[j-3]�����ƶ�һ��λ�ã�ֱ���ҵ�A[j]���ʵ�λ��,��Ϊ���������������ڵĴ洢��Ԫ֮���ٲ���һ����Ԫ�����Ҫ�������֮����������������ƶ�һ����Ԫ
			// һ��ȷ������ȷλ��j��Ŀ��ֵtarget����ԭʼ��arr[i]���ͻᱻ���Ƶ����λ�á���ѡ������ͬ���ǣ����������������һ��������Ҳ���ִ�н�����
			while (i >= 0 && target[i] > exchange) {
				target[i + 1] = target[i];
				i = i - 1;
			}
			// ��A[j]��ֵ�����λ��
			target[i + 1] = exchange;
		}
		return target;
	}

	public static void main(String[] args) {
		int[] target = { 5, 3, 4, 6, 8 };
		System.out.println(Arrays.toString(intArrayInsertionDemo(target)));
	}
}
