package net.brian.coding.java.core.jdk.keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 
 * for��foreach��ʹ�÷���ʾ������
 * 
 * �������ʹ�� Java ����ǿ��ѭ������������ֻ��Ҫʵ��Iterable�ӿ�
 * ����Map��ôѭ����?����ѭ��Map��key�ļ���Entry[]��ʵ����Iterable��
 * 
 * ���û��ʹ��Iterator������һ������ķ�����ʹ������for����whileѭ��
 * �����ַ����ͻ��˳���Ա����������֪�����ϵ��ڲ��ṹ�����ʴ���ͼ��ϱ����ǽ����
 * �޷��������߼��Ӽ�����Ϳͻ��˴����з��������ÿһ�ּ��϶�Ӧһ�ֱ����������ͻ��˴����޷�����
 * �����ArrayList����ΪLinkedList����ԭ���Ŀͻ��˴������ȫ����д
 * Iterable����ʾ�������
 * @see net.brian.coding.java.core.datastructure.IterableDemo
 * 
 * forѭ���ṹ��⣺
 * �ֺ��������������ģ�����forѭ����������÷ֺŷָ����Կ�����������䲢��һ��д
 * ���forѭ������������ֺ��Ǳز����ٵģ�����Ķ����Բ�д��
 * a.��ʼ��������ͬʱ��ʼ���������������for (int i = 0, j = 0; i < 10; i++)
 * �������԰�i��ȡ��forѭ����������Ȼ���һ���ֺ�ǰʲôҲ��д��for (; i < 10; i++)
 * �������������֮ǰint i��forѭ���ڲ���ʱ�����ʼ��Ҳ��һ��forѭ�������԰ѳ�ʼ����ȡ�������������һ��ѭ��
 * b.�����ֺ�֮��Ĳ��ַ���һ��boolean�߼����ʽ������trueʱforѭ����ִ����һ��
 * c.���һ������ѭ���������֣�ÿ��ѭ���������ִ��ѭ����������
 * 
 * ���⣺
 * forѭ����whileѭ��������
 * ��������һ����ѭ������֮���ǲ����õģ����ں����ȼ۵�whileѭ����
 * ����������ѭ������֮����Ȼ���á�������𳣳���ʹ��while����forѭ������Ҫԭ��
 *
 */
public class ForLoopAndWhileLoop {
	
	public void declareOnce() {
		@SuppressWarnings("unused")
		Object obj = null;
		for (int i = 0; i < 10; i++) {
			obj = new Object();
		}
	}

	public void declareMultipleTimes() {
		for (int i = 0; i < 10; i++) {
			@SuppressWarnings("unused")
			Object obj = new Object();
		}
	}
	// Normal list using for loop enhancement.
	public void demo1() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		for (Object str : list) {
			System.out.println(str);
		}
	}

	// forѭ����while����
	public void demo2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		Set<String> keySet = map.keySet();
		for (Object obj : keySet) {
			String key = obj.toString();
			String value = (String) map.get(key);
			System.out.println(key + " = " + value);
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println(key + " = " + value);
		}
	}

	// ��ǿforѭ����Ҳ����foreach�������ڱ����������Ҫ��ȡĳ��Ԫ�أ�����Ҫi������
	public void demo3() {

		int arrs[] = { 1, 2, 3 };
		for (int arr : arrs) {
			arr = 10;
			System.out.println(arr);
		}
		System.out.println(arrs[0]);
		System.out.println(arrs[1]);
		System.out.println(arrs[2]);

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		// �������ѭ�����ڵ�obj������forѭ���Ͳ�������
		// ��ֻ��һ���м������ʱ���������ڱ���������򼯺�Ԫ��
		// ���ͨ�������ѭ���������и�ֵ����Ȼ������û��ʲô�﷨����
		for (Object obj : list) {
			obj = "hehe";
			System.out.println(obj);
		}
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}
}
