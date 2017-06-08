package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item04: Enforce noninstantiability with a private constructor
 * 
 * ��ֹ�̳л���ʵ�����������ķ�ʽ����ʾ������:
 * a.˽�л�����������������������ȫ�������ˣ��Ͳ���ʵ�����ˣ�����Ҳ��ֹ�˼̳�
 * b.����Ϊfinal�࣬�������������ɿ����ã����Ծɿ���ʵ���������ҳ��׽�ֹ�˼̳�
 * c.����Ϊ�����࣬�����������ɿ��Ե��ø���Ĺ���������û��������ֹʵ���������һ����Լ̳�
 *
 */
public class AvoidingInheritance {
	public static class TestCollections /*extends java.util.Collections*/ {
		// Collections��ͨ��˽�л���������ֹ���̳У���ΪCollections����һ�������࣬Ҳ����Ҫ�õ���������ʼ��ʲô
	}
	public static class TestString /*extends String*/ {
		// ����String�ͺ�Collections��ͬ�ˣ�String����Ҫ���������һ����ʼ��������
		// ���String�Ĳ��ɼ̳к�Collections�໹��һ����String��ֱ�ӽ�������Ϊfinal�Ľ�����ֹ�̳�
	}
	
	public static class AbstractClass {public AbstractClass() {System.out.println("AbstractClass()");}}
	public static class DerivedClass extends AbstractClass {
		//��Ҫ��ͼ��һ�������óɳ���������ֹ���౻ʵ��������Ϊ������Ա����໯��Ȼ��������ǿ�ʵ���������ˣ�����ͬ����¶�˸���Ĺ�����
		public DerivedClass() {
			super();
		}
	}
	public class PrivateConstructor {
		private PrivateConstructor() {
			// Ϊ�˱������Ʒ����ԭ����������������������Կ�����˽�л���������ͬʱ�׳�һ��Error
			throw new AssertionError();
		}
	}
}
