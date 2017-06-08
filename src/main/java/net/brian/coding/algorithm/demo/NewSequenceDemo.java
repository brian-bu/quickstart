package net.brian.coding.algorithm.demo;

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
	// 哨兵
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
		// 第一轮递归开始：对所有map元素编号，编号值为map的value，第一个元素编号为1，第二个为2，第三个为3，第四个又为1，直到最后一个元素被标记完
		// 获取map的最后一位，以及它的编号，并将编号赋值给哨兵，从此开始哨兵的值只能是1,2,3中的一个
		// 删除所有等于三的map元素，得到一个新的map
		// 第二轮递归开始：获取哨兵元素，将哨兵元素加1，如果哨兵是3，则加1为1，作为第二次递归时的第一个map元素编号，然后依次编号
		// 编号结束之后，继续进行一次清除，删除所有的编号为3的元素
		// 如果元素个数不足3，依旧需要保证这种编号规则
		// 终止条件：当map仅剩下一个元素的时候程序终止并返回map元素的值
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
