package net.brian.coding.java.core.oop;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ��ʾ�����е�ǰ���ʵ������
 * ֻҪ���ڵ�ǰ��StaticFieldClass�������ĳ�ʼ���������γɵݹ���ã��������ǰ�ȫ��
 * ���⣬���е�ǰ�������ʵ��Ҳ��ҪС�ģ���ΪҲ������ɵݹ����
 *
 */
public class StaticFieldClass {
	
	public static void main(String[] args) {
		StaticFieldClass staticFieldClass1 = new StaticFieldClass(1);
		System.out.println(staticFieldClass1.size());
		
		StaticFieldClass staticFieldClass2 = new StaticFieldClass(2);
		StaticFieldClass staticFieldClass3 = new StaticFieldClass(3);
		System.out.println(staticFieldClass2.size());
		System.out.println(staticFieldClass3.size());
	}
	
	private final int id;
	private static List<StaticFieldClass> list = new ArrayList<StaticFieldClass>();

	public StaticFieldClass(int id) {
		this.id = id;
		list.add(this);
	}

	public int size() {
		if (null == list)
			return 0;
		return list.size();
	}

	public String toString() {
		return "Entrance " + id;
	}
}
