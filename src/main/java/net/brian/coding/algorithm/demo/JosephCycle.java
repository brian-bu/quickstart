package net.brian.coding.algorithm.demo;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ʵ�ֵĹؼ�����Ϸ��Ϣ�Ĵ��档���������λ��Ϣ�������������Ϣ�Լ�������Ϣ������ͨ���Զ��嵥��ѭ������Joeph_list�洢�ṹ��ʵ����Ϸ���̵�ģ�⡣�����Խ�����ӡ����Node�洢����Ϣ����ÿ�������е����롢ÿ���˵�λ���Լ���һ������ڼ�����еĴ洢λ�ã���ָ����һ������ָ�롣ֵ��ע����ǣ���Ϣ��ÿ���˵�λ�á��Ǳز����ٵģ���Ϊ������ͬ�ڽ���������е�λ�á�����һ����ұ��Ƴ�֮��������Ԫ��λ�ûᡰǰ��������������Ҫ����ҵ�λ��ʼ���ǲ���ġ�
��ҵı���������ͨ��ѭ���м������ĵ���ʵ�֣���˳����������������һ����㣬��ѭ����û�н���ʱ�����Ǽ����ӵ�һ��Ԫ�ؿ�ʼ�����������൱�����һ�������û�б�����m���Ǿʹӵ�һ�������ͷ��ʼ������ֱ���������ۼӵ�m����������Ҫ�Ƴ��Ľ�㣬��¼������Ƴ�������Ϣ��������Ϸ��ֱ��������Ԫ�ر���գ����������
�㷨�Ĺؼ��ǽ�ʵ����Ϸ�������������е�Ԫ�صĲ��Һ��Ƴ��ϣ�Ҫ���������Щ���ݴ�����Щ��Ϣ������Ϥ���������и����жϵ����̡�
 *
 */
public class JosephCycle {
	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in);
		// System.out.print("��������������");
		// int totalNum = scanner.nextInt();
		// System.out.print("�����뱨���Ĵ�С��");
		// int cycleNum = scanner.nextInt();
		// System.out.print("�����뿪ʼ��ţ�");
		// int startNO= scanner.nextInt();
		// yuesefu(totalNum, cycleNum,startNO);
		joseph(13, 3, 1);
	}

	public static void joseph(int totalNum, int countNum, int startNO) {
		// ��ʼ������
		List<Integer> start = new ArrayList<Integer>();
		for (int i = 1; i <= totalNum; i++) {
			start.add(i);
		}
		// ���±�ΪK��ʼ����
		int k = startNO - 1;
		while (start.size() > 0) {
			System.out.println(start);
			// ��m�˵�����λ��
			k = (k + countNum) % (start.size()) - 1;
			// �ж��Ƿ񵽶�β ����βʱ��k=-1
			if (k < 0) {
				System.out.println(start.get(start.size() - 1));
				start.remove(start.size() - 1);
				k = 0;
			} else {
				System.out.println(start.get(k));
				start.remove(k);
			}
		}
	}
}
