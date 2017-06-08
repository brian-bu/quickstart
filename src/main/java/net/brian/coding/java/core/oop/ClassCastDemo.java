package net.brian.coding.java.core.oop;

import org.junit.Test;

/**
 * JVM�����ԭ�򣺶�������ù�ϵֻ�ж���Ĵ����߳��к�ʹ�ã�JVM�����Ը�Ԥ��������ù�ϵ
 * ��ΪJVM����֪����������ô��ʹ�õģ�����漰JVM����֪�����������ʱ���Ͷ�ֻ֪������ʱ����
 * 
 * ת�ͷ�Ϊǿ��ת�ͺ��Զ�ת��
 * ��������ǿ��ת�������������ת�ͣ�����Ҫ��ȷ��ָ��Ҫת������һ������
 * ����ת���ɸ���������ת��
 * 
 * ����ת�Ϳ�������ClassCastException��JVM��������ת����ʱ��ᰴ�����¹�����м�飺
 * a.������ͨ���󣬸ö��������Ŀ�����ʵ����Ŀ����������ʵ�������Ŀ�����ǽӿڣ����������ʵ���˸ýӿڵ�һ������
 * b.�����������ͣ�Ŀ����������������ͻ�java.lang.Object/java.lang.Cloneable/java.io.Serializable
 * ������������������ͻ�����ClassCastException�����������������ַ�ʽ��
 * a.���÷��͵Ļ���ȷ���ڱ����ڽ���ǿת�İ�ȫ�Լ��
 * b.ǿת֮ǰ�����instanceof�ж�һ�£��������������
 * ����instanceof�Ĵ���ʾ������дһ���ࣺ
 * @see net.brian.coding.java.core.jdk.keywords.InstanceofDemo
 * 
 * ǿת����������������֮���ǿת�����ǵײ�Ķ����ǲ�����
 *
 */
public class ClassCastDemo {
	/**
	 * ��ת�ͱ����ı���ʱ������Ŀ�����͵ĸ��ࣺ����ת��
	 * ��Ҫǿ��ת�������ת��ʧ�ܻ��׳�ClassCastingException
	 */
	@SuppressWarnings("unused")
	public void testDownCasting() {
		Object obj = "Hello";
		// obj�����ı�������ΪObject����String���͵ĸ��࣬����ǿ������ת��
		// ����obj����ʵ�������õ�Ҳ��String������������ʱҲ����
		String objStr = (String) obj;
		System.out.println(objStr);
		// ����һ��objPri��������������ΪObject��ʵ������ΪInteger
		Object objPri = new Integer(5);
		// objPri�����ı�������ΪObject����String���͵ĸ��࣬����ǿ������ת��
		// ��ǿת����������������֮���ǿת�����������ײ�Ķ����ǲ�����
		// ������ǿת֮�����ñ���ָ��һ��Integer��������������String����
		// obj����ʵ�������õ���Integer����Ҳ��ǿת����ɹ��������ɣ�
		// String str = new Integer(5);����Ȼ�ǲ����ܵ�
		// ����������������е�ʱ��ͻ�����ClassCastingException
		String str = (String) objPri;
	}
	
	/**
	 * ��ת�ͱ����ı���ʱ������Ŀ�����͵����ࣺ����ת��
	 * ����Ҫǿ��ת����ת����������ʱ�Զ���ɵ�
	 */
	@Test
	public void testUpCasting() {
		String s1 = "Hello";
		String str1 = "He";
		String str2 = "llo";
		String s2 = str1 + str2;
		// String���͵Ķ�����Object��������������ת��
		// ����ͨ������equalsҲ���Է�������ת�ͻ��Զ���ʧһЩ����������������equals�����ĸ���
		// ǿת֮����õĲ�����String���Ǹ���ķ��������Ǹ���Object�Լ��ķ���
		Object obj = s1;
		// ���equals��Object�Ķ�����String��
		// ���Ǵ���ȥ�Ĳ���ȴ��String������ת��Ҳ������ת�ͣ�Ҳ���Զ���
		System.out.println(obj.equals(s2));
	}
	
	/**
	 * ����Ȳ�������ת��Ҳ��������ת�ͣ��Ǿ��Ǳ�ת�ͱ����ı���ʱ���ͺ�Ŀ��������ͬ
	 * ����������Ͳ�ͬ�������Stringǿת��Math����ô�����������鶼����ȥ
	 */
	@SuppressWarnings("unused")
	public void testWrongTypeCasting() {
		String s1 = "Hello World";
		// ��ת�ͱ����ı���ʱ���ͺ�Ŀ��������ͬ�����������ͨ�����룬����û�б�Ҫ����
		String s2 = (String)s1;
		// ��Ϊs�ı���ʱ������String��String����Math����
		// StringҲ����Math�����࣬Ҳ����Math�ĸ��࣬����������뵼�±������
		// Math m = (Math)s1;
	}
	
}
