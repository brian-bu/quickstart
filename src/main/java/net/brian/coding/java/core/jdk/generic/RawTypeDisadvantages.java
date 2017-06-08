package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item23: Don��t use raw types in new code
 * 
 * item24: Eliminate unchecked warnings
 * 
 * ������Ҳ���βκ�ʵ�Σ�List<E>��List<String>��E�����з��͵��β�
 * ����ָ�������ͣ�����String������E��ʵ��
 * 
 * ���ڡ���Ҫ���´�����ʹ��ԭ��̬���͡����������⣺
 * a.�������б���ʹ��ԭ��̬��List.class, String[].class��int.class���Ϸ�
 * ����List<String>.class��List<?>.class���Ϸ�
 * b.���Ϳ�������ʱ�����������������ͺ�instanceofһ��ʹ���ǷǷ���
 * ����������ͨ�������ȴ�ǺϷ��ģ�������÷�����ʹ��instanceof����ѡ��������
 * @see net.brian.coding.java.core.jdk.generic.RawTypeDisadvantages.testGenericWithInstanceof(Object)
 * 
 * ����������ͨ������ͣ�
 * @see net.brian.coding.java.core.jdk.generic.BoundedWildcardType
 *
 */
public class RawTypeDisadvantages {
	/** 
	 * �����������ͻ�(sub typing)�Ĺ���
	 * List<String>��ԭ��̬List��һ�������ͣ�������List<Object>��������
	 * ��ʵ������List<E>����ԭ��̬List��������(E�Ƿ��ͻ����βΣ�����Ϊ��������)
	 * ���ʹ����List������ԭ��̬���;ͻ�ʧ�����Ͱ�ȫ�ԣ�ʹ��List<Object>�򲻻�
	 */
	public void testSubTyping() {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new Integer(42));
		String s = strings.get(0);
		System.out.println(s);
		
	}

	/**
	 * �����list�������ܿ���List��List<Object>�Ĳ�ͬ��
	 * ����testSubTyping�����ĵ��ã�
	 * �������list����ΪList<Object>�����ʱ�ͼ�������
	 * ��ΪList<String>����List<Object>�������ͣ����ε�ʱ��ͻᱨ��
	 * �������list����ΪList����ΪList<String>��List�������������ʱ������ֱ������ʱ�ŷ��ִ���
	 * 
	 * �������item24��������Ҫע��������list.add(obj)�ķ��ܼ쾯�棬��unchecked���������ֻ�ܷ��ڷ�������
	 * ֻ�����޷����������ҿ���֤�����𾯸�Ĵ��������Ͱ�ȫ��ʱ��ſ�����unchecked����Ȼ�����Ǵ����ʾ��
	 * ������rawtypes��������Ӧ�þ�����С@SuppressWarnings�����÷�Χ������Ӧ��������list����ǰ�;�����Ҫ�����ڷ�������
	 * 
	 * @param list
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	private void unsafeAdd(@SuppressWarnings("rawtypes") List list, Object obj) {
		// ÿ��ʹ��uncheckedע�ⶼӦ�ü�һ��ע��˵��Ϊʲô�����ǰ�ȫ��
		// ����Ͳ���ע���ˣ���Ϊ����Ȼ��ǰ��������ǲ���ȫ��
		list.add(obj);
	}
	
	/**
	 * 
	 * �ڲ�ȷ�����߲��ں�ʵ�ʵ����Ͳ�����ʱ��Ҳ������ԭ�����ͣ���ʱ���Կ����������Ƶ�ͨ������ͣ����ʺ�?
	 * ����Set<E>��������ͨ������;���Set<?>����������Set���Գ����κμ���
	 * 
	 * ���⣬�����unused����Ϊ��������Ķ��岢����Ϊ���ã�����Ϊ���ṩһ�ִ���ʾ��
	 * �����ҷǳ�ȷ�����ķ��ܼ쾯���Ƕ���ģ����Ծͼ���unused��ע��
	 * @param list
	 * @param obj
	 * 
	 * ���޷�����?����������ע���е����ƣ����Կ����÷��ͷ����������Ƶ�ͨ�������
	 * ���ͷ�����
	 * 
	 * �����Ƶ�ͨ������ͣ�
	 * 
	 */
	@SuppressWarnings("unused")
	private void safeAdd(List<?> list, Object obj) {
		// ���ᱨ�������? is not applicable for the arguments (Object)
		// list.add(obj);
		// �㲻�ܽ�����null������κ�Ԫ�ؼӵ����list��ȥ
		list.add(null);
	}
	
	public void testGenericWithInstanceof(Object o) {
		if(o instanceof Set) {
			@SuppressWarnings("unused")
			Set<?> m = (Set<?>)o;
		}
	}
}
