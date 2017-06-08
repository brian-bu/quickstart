package net.brian.coding.algorithm.demo.sorting;

import java.util.Arrays;

public class RecurrenceSorting {
	// �������Ϊ������A[1, n]���ȶ�A[1, n-1]�������գ������ȶ�A1��A2��������Ȼ�������ÿ�β��������Ƕ��Ѿ��ź���Ľ��в�������
	// �����漴ʹ������Ѿ��ź����������в������������õ�һ����������֣�Ҳ��֪��������Ӧ�ñ��ŵ�ʲôλ�ã����Զ��������һ�α����ǲ��ɱ����
	// ��������������Ϊ�˸��ݴ�С����Ҫ�����Ԫ���ҵ�һ�����ʵ�λ�ã�����ҵ������λ�ã���ô���λ��֮���Ԫ�ؾͶ�Ҫ����һλ
	// ���б������ҵ�����λ������������ÿ�ζ���һ���ģ����Խ�����������������ȡ��һ������
	public static int[] recurrenceInsteadOfInsertion(int[] target) {
		if (target == null || target.length == 0)
			throw new NullPointerException("Target array is null!");
		int[] swap = new int[1];
		for (int i = 0; i < target.length; i++) {
			swap[i] = target[i];
			swap = findSuitablePlaceForNewElement(swap, swap[i]);
			System.out.println(Arrays.toString(swap));
		}
		return swap;
	}

	// Ҫô��Ҫȷ�����������������һ��Ԫ����ר������ռλ�ģ���Ϊ�գ�������ѱ�֤����Ϊ���кܶ�������Ԫ��Ҳ����Ϊ����
	// Ҫô��ֻ��ѡ���½�һ�����ȼ�1�����飬��Ϊ�����������鳤���ǹ̶���
	private static int[] findSuitablePlaceForNewElement(int[] swap, int newEle) {
		int[] result = new int[swap.length + 1];
		for (int i = 0; i < swap.length; i++) {
			if (swap[i] < newEle) {
				continue;
			} else {
				System.arraycopy(swap, 0, result, 0, i);
				result[i] = newEle;
				System.arraycopy(swap, i, result, i + 1, swap.length - i);
				break;
			}
		}
		return result;
	}

	// ŷ������㷨������Լ����gcd(a,b) = gcd(b,a mod b);
	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		int r = p % q;
		return gcd(q, r);
	}
	// �����С������
	public static int gbs(int m, int n) {
		return m * n / gcd(m, n);
	}

	public static void main(String[] args) {
		System.out.println("���Լ��:: " + gcd(40, 16));
	}
}
