package net.brian.coding.java.core.jdk.operators;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ��Ŀ�����ʹ�÷�����ע�����
 * 
 * ��Ŀ�����Ҫ��ڶ���λ������ͳһ��Ҫô���ǻ�������Ҫô���ǰ�װ����
 * ����ڶ�������λ�������ֱ�Ϊ�������ͺͶ���ʱ�����еĶ���ͻ����Ϊ�������ͽ��в���
 * 
 * ���ϣ�����ʹ����Ŀ�����Ҫ������֤ʹ�ð�װ���Ͷ����ǻ�������
 * 
 * ���´��뻹�ж���Ŀ�����������ʹ��ʾ����
 * @see net.brian.coding.java.core.oop.OverloadingDemo.classify(Collection<?>)
 *
 */
public class TernaryConditionalOperator {
	
	public static void main(String[] args) {
		Map<String,Boolean> map =  new HashMap<String, Boolean>();
		// NPEԭ������ʹ������Ŀ����������ҵڶ�������λ�������ֱ��ǻ������ͺͶ���
		// ���ԶԶ�����в�����������ڸö���Ϊnull
		// �����ڲ�������е���null.booleanValue()��ʱ��ͱ���NPE
		// hashmap.get(��test��)->null;
		// (Boolean)null->null;
		// null.booleanValue()->����
		Boolean b1 = (map!=null ? map.get("test") : false);
		// ������ȷ���ڶ���λ���Ƕ������;Ͳ���
		Boolean b2 = (map!=null ? map.get("test") : Boolean.FALSE);
	}
}
