package net.brian.coding.algorithm.demo.sorting;

@SuppressWarnings("rawtypes")
public class SortingTemplate {

	public static void selectionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// ��СԪ��������Ȼ�����ʣ��Ԫ�أ�˭��С�Ͱ������������˭
			// ���վ��γɴ�0��i��һ���ź��������
			// �ȼٶ���i��Ԫ����С��������С����
			int min = i;
			// �����ҵ�����ʣ��Ԫ���бȵ�i��Ԫ�ظ�С��Ԫ��
			for (int j = i + 1; j < N; j++)
				// �ο�Ԫ�س�����СԪ�������������һ��������Ԫ�رȲο�Ԫ��С
				// ����������Ȩת�����Ǹ�Ԫ��
				if (less(a[j], a[min]))
					min = j;
			// �ڲ�forѭ���������Ǳ����Ƚ�������ʣ��Ԫ�أ������ȷ������ʣ��Ԫ���е���СԪ��
			// ����ڲ�forѭ�������ҵ�����СԪ��Ҫ�͵�i��Ԫ�ؽ���λ�û�����Ҳ����˵ѡ����������ʲô�������N�ε�Ԫ�ؽ���
			exch(a, i, min);
		}
	}

	public static void insertionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// ƾ�����less(a[j], a[j - 1])�����������������ֻ�ƶ�δ�ź����Ԫ��
			// ���ǡ�������Ѿ��ź�˳����ôforѭ�������Ͳ�������κ�Ԫ�صĽ���
			for (int j = i; less(a[j], a[j - 1]); j++) {
				exch(a, j, j - 1);
			}
		}
	}

	public static void improvedInsertionSorting(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// ƾ�����less(a[j], a[j - 1])�����������������ֻ�ƶ�δ�ź����Ԫ��
			// ���ǡ�������Ѿ��ź�˳����ôforѭ�������Ͳ�������κ�Ԫ�صĽ���
			for (int j = i; less(a[j], a[j - 1]); j++) {
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i] + " ");
	}

	public static boolean isSorted(Comparable[] a) {
		// Test if the array is sorted.
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static void main(String[] args) {
		String[] a = { "ax", "s", "e", "gf", "k" };
		selectionSorting(a);
		assert isSorted(a);
		show(a);
	}
}
