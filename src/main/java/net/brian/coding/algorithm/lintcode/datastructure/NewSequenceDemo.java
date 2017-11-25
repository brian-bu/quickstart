package net.brian.coding.algorithm.lintcode.datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

public class NewSequenceDemo {
	List<Integer> list = new LinkedList<Integer>();
	Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
	// �ڱ�
	int guard = 0;

	@Before
	public void prepareForFinals() {
		for (int i = 0; i < 9; i++) {
			map.put(i + 1, 0);
		}
	}

	@Test
	public void getFinals() {
		int finalInt = -1;
		while (map.size() > 1) {
			System.out.println("map.size():: " + map.size());
			map = remarkToHashMap();
		}
		if (map.size() == 1) {
			for (Entry<Integer, Integer> ele : map.entrySet()) {
				finalInt = ele.getKey();
			}
		}
		if (finalInt == -1) {
			System.err.println("Failed to get the Element!");
		} else {
			System.out.println("Final element:: " + finalInt);
		}
	}

	private Map<Integer, Integer> remarkToHashMap() {
		// ��һ�ֵݹ鿪ʼ��������mapԪ�ر�ţ����ֵΪmap��value����һ��Ԫ�ر��Ϊ1���ڶ���Ϊ2��������Ϊ3�����ĸ���Ϊ1��ֱ�����һ��Ԫ�ر������
		// ��ȡmap�����һλ���Լ����ı�ţ�������Ÿ�ֵ���ڱ����Ӵ˿�ʼ�ڱ���ֵֻ����1,2,3�е�һ��
		// ɾ�����е�������mapԪ�أ��õ�һ���µ�map
		// �ڶ��ֵݹ鿪ʼ����ȡ�ڱ�Ԫ�أ����ڱ�Ԫ�ؼ�1������ڱ���3�����1Ϊ1����Ϊ�ڶ��εݹ�ʱ�ĵ�һ��mapԪ�ر�ţ�Ȼ�����α��
		// ��Ž���֮�󣬼�������һ�������ɾ�����еı��Ϊ3��Ԫ��
		// ���Ԫ�ظ�������3��������Ҫ��֤���ֱ�Ź���
		// ��ֹ��������map��ʣ��һ��Ԫ�ص�ʱ�������ֹ������mapԪ�ص�ֵ
		for (int i = 0; i < map.size(); i++) {
			
//			if (i % 3 == 0) {
//				map.put(ele.getKey(), 1);
//			} else if (i % 3 == 1) {
//				map.put(ele.getKey(), 2);
//			} else if (i % 3 == 2) {
//				map.put(ele.getKey(), 3);
//			}
//			if (ele.getKey() == map.size())
//				guard = i;
		}

		for (Entry<Integer, Integer> ele : map.entrySet()) {
			if (ele.getValue() == 3) {
				System.out.println("Removing:: " + ele.getKey() + " -- " + ele.getValue());
				map.remove(ele.getKey());
			}
		}
		return map;
	}
}
