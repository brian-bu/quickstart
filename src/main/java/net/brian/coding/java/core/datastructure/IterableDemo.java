package net.brian.coding.java.core.datastructure;

import java.util.Iterator;

import org.junit.Test;
/**
 * 
 * Listһ�����Setһ�壬����ʵ����Iterable�ӿڣ�������ֱ��ʵ��Iterator�ӿڣ�����ͨ���ڲ���ʵ��
 * ArrayList�ǻ������飬LinkedList�ǻ��������������ȫ��һ�������ݽṹ
 * ���о��������ʵ���Ǹ��Լ������ڲ�ʵ��Iterator�ӿ���ʵ�֡���ͨ��������ʵ��iterable�ӿ��������ṩ����ӿڡ�
 *
 */
public class IterableDemo {
	@Test
	// Classes those implement Iterable will be iterable by for-each.
	public void testForeach() {

	}
}

class NewType implements Iterable<Object> {
	@Override
	public Iterator<Object> iterator() {
		return null;
	}

}
