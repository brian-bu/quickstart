package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item28: Use bounded wildcards to increase API flexibility
 * 
 * ���סPECSԭ�������ߣ�Producer��ʹ��extends�������ߣ�Consumer��ʹ��super��
 * a.������ʹ��extends���������Ҫһ���б��ṩT���͵�Ԫ�أ���������б��ж�ȡT���͵�Ԫ��
 * ����Ҫ������б�������<? extends T>������List<? extends Integer>����㲻�������б�������κ�Ԫ��
 * b.������ʹ��super�� �����Ҫһ���б�ʹ��T���͵�Ԫ�أ��������T���͵�Ԫ�ؼ��뵽�б�
 * ����Ҫ������б�������<? super T>������List<? super Integer>����㲻�ܱ�֤���ж�ȡ����Ԫ�ص�����
 * c.���������ߣ�Ҳ�������ߣ��㲻��ʹ�÷���ͨ��������б�����List<Integer>
 * @see java.util.Collections.copy(List<? super T>, List<? extends T>)
 * һ��ͨ�õĹ������е�comparable��comparator����������
 * 
 * ��֮��Ϊ��ʹ�������������ԣ�Ҫ�ڱ�ʾ�����߻��������ߵ����������ʹ��ͨ�������
 * 
 * ע�����������������Ƶ�ͨ�������
 * ����������ͨ������ͣ�
 * @see net.brian.coding.java.core.jdk.generic.RawTypeDisadvantages
 *
 */
public class BoundedWildcardType {
	// Double��Number�Ĺ�ϵDouble extends Number

	/**
	 * <? extends T>��ʾ����T���ڵ��κ�T������ 
	 * ? extends T��ʾ����ֻҪ��Number����Number������Ϳ��Էŵ��������List��ȥ
	 * ����PECSԭ��extendsֻ�ܶ�ȡ��������ӣ�super���ܱ�֤���ж�ȡ����Ԫ�ص�����
	 */
	@Test
	public void testExtendT() {
		List<? extends Number> list1 = new ArrayList<Number>();
		List<? extends Number> list2 = new ArrayList<Double>();
		Double d = new Double("1.00");
		// Compiler err������PECSԭ��extendsֻ�ܶ�ȡ��������ӣ�super���ܱ�֤���ж�ȡ����Ԫ�ص�����
		// list1.add(d);
		// list2.add(d);
		System.out.println("Output list1.size():: " + list1.size());
		System.out.println("Output list2.size():: " + list2.size());
	}

	/**
	 * <? super T>��ʾ����T���ڵ��κ�T�ĸ���
	 *  ? super T��ʾ����ֻҪ��Double����Double�ĸ���Ϳ��Էŵ��������List��ȥ
	 *  ����PECSԭ��extendsֻ�ܶ�ȡ��������ӣ�super���ܱ�֤���ж�ȡ����Ԫ�ص�����
	 */
	@Test
	public void testSuperT() {
		List<? super Double> list1 = new ArrayList<Double>();
		List<? super Double> list2 = new ArrayList<Number>();
		double d = 1.00;
		// �Զ�װ��
		list1.add(d);
		list2.add(d);
		// ����PECSԭ��extendsֻ�ܶ�ȡ��������ӣ�super���ܱ�֤���ж�ȡ����Ԫ�ص�����
		System.out.println(list1.get(0) instanceof Double);
		System.out.println("Output list1.size():: " + list1.size());
		System.out.println("Output list2.size():: " + list2.size());
	}
}
