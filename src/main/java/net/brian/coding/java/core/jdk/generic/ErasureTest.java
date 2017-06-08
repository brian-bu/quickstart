package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item25: Prefer lists to arrays
 * 
 * �����Э���Ժͷ��͵Ĳ�����
 * 
 * ���Ͳ�����
 * ����һ�����з�����Ϣ�Ķ���ֵ����һ��û�з�����Ϣ�ı���ʱ���������еķ�����Ϣ�ͻᱻ�����ӵ�
 * ����ת����
 * ����һ��û�з�����Ϣ�Ķ���ֵ����һ��������Ϣ�ı���ʱ�����ᷢ���������Զ�ת��
 * 
 * ��������ʹ���Ϳ�����û��ʹ�÷��͵Ĵ���������л���
 * 
 */
public class ErasureTest
{
	/**
	 * �����ǲ��ɱ�ģ���List<String>����List<Object>��������
	 * ����ȴ��Э��ģ���String[]��Object[]��������
	 * �����˵�Ƿ��Ͱ�ȫ��֤����һ�����֣������������ڱ����ʱ���ִ���
	 * ͬʱҲ��¶�������ʾ����ʱ�����⣬û�з��ͺܶ���󶼵õȵ����е�ʱ����ܷ���
	 */
	public void testCovariantAndInvariant() {
		// �����Э����ʾ��
		Object[] objArr = new Long[1];
		objArr[0] = "I don't fit in!";//Throws ArrayStoreException
		
		// ���͵Ĳ��ɱ���ʾ��
		// List<Object> ol = new ArrayList<Long>();//Compiler err
		// ol.add("I don't fit in!");
	}
	
	/**
	 * ���ͺ��������һ�������ǣ�
	 * �����Ǿ��廯�ģ�������ʱ��֪����������ǵ�Ԫ������Լ��
	 * ������ͨ��������ʵ�ֵģ���˷����ڱ���ʱǿ�����ǵ����ͣ�����ʱ����Ԫ��������Ϣ
	 * 
	 */
	@SuppressWarnings("unused")
	public void testErasure()
	{
		Apple<Integer> a = new Apple<Integer>(6);
		//a��getSize��������Integer����
		Integer as = a.getSize();
		//��a���󸳸�Apple�������ᶪʧ���������������Ϣ
		@SuppressWarnings("rawtypes")
		Apple b = a;
		//bֻ֪��size��������Number
		Number size1 = b.getSize();
		//�����������������
		// Integer size2 = b.getSize();
	}
	
	/**
	 * ����ͷ����ǲ����ݵģ�����һ��ʹ��
	 *  Ҳ�������ܶ��巺�����飬��Ϊ������ͨ������ʵ�ֵģ�List<String>����ʱ������List
	 *  �������з������飬��List<String>[]����ʱ����Ӧ����List[]
	 *  ������һ��List<Integer>�����е�ʱ������ListҲ���ԷŽ�List[]��ȥ���ͻ����ClassCastException
	 *  ��������������£������޷�ͨ�����룬���ͨ��ע����ʽ��ʾ������������ʱ����ͨ��������ʾ
	 */
	public void noGenericArrays() {
		// List<String>[] stringLists = new List<String>[1];
		// List<Integer> intList = Arrays.asList(42);
		// Object[] objects = stringLists;
		// objects[0] = intList;
		// String s = stringLists[0].get(0);
	}
}

class Apple<T extends Number>
{
	T size;
	public Apple()
	{
	}
	public Apple(T size)
	{
		this.size = size;
	}
	public void setSize (T size)
	{
		this.size = size;
	}
	public T getSize()
	{
		 return this.size;
	}
}