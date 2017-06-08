package net.brian.coding.java.core.jdk.jvm.initialization;

import java.util.HashMap;
import java.util.Map;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item05: Avoid creating unnecessary objects
 * 
 * a.Ҫ����ʹ�û������Ͷ�����װ�����ͣ�Ҫ��������ʶ���Զ�װ��
 * b.��������ǲ��ɱ�ģ���ô����ʼ�տ��Ա����ã�����û��Ҫÿ��ʹ�õ�ʱ�򴴽�һ���µ�ʵ��
 * c.����String��Integer�����final�࣬�����ǲ��ɱ�ģ�����Ҫ��������ʼ������ֱ������ֵ���
 * 
 * ����String��ֱ������Integer��ֱ�������ǲ�һ���ģ�
 * ���漰����String�����غ�Integer�����ص��й����⣬���д���ʾ����
 * @see net.brian.coding.java.core.pool.ConstantPoolForString
 * 
 * ����item05��Ҫ˵���ǣ�����Ӧ���������ж���ʱ���벻Ҫ�����µĶ���
 * ��Ӧ��item39˵���ǣ�����Ӧ�ô����¶���ʱ�벻Ҫ�������ж���
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DefensiveCopiesDemo
 * 
 * item49: Prefer primitive types to boxed primitives
 * 
 * ����һ������л��ʹ�û������ͺ�װ������ʱ��װ�����;ͻ��Զ����䣬���null�������ñ��Զ�����ͻ�õ�NPE
 * ������֮ǰ����Ŀ�������ʱ��Ҳ���ֹ���
 * @see net.brian.coding.java.core.jdk.operators.TernaryConditionalOperator
 * ��Ȼ��װ����Ҳ����һ���Ǵ����������ʺϰ�װ���͵����������Щ�����Ҫʹ�ð�װ���ͣ������Ӧʹ�û������ͣ�
 * a.��Ϊ���ϵ�Ԫ�ء�����ֵ
 * b.���������ͣ����ͣ��б���ʹ��װ��������Ϊ���Ͳ���
 * c.����ķ�������ʱ������װ������
 * 
 */
public class UnnecessaryObjects {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// String unnecessaryObject1 = "Hello world!"; This is enough.
		String unnecessaryObject1 = new String("Hello world!");//DO NOT DO THIS!
		// int is not big enough during calculation so we need long.
		// But we should use long instead of Long during declaration.
		// Autoboxing will low the performance.
		// item49
		Long unnecessaryObject2 = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			unnecessaryObject2 += i;
		}

		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("UnnecessaryObjects -- If keySet duplicated:: " + (map.keySet() == map.keySet()));
	}
}