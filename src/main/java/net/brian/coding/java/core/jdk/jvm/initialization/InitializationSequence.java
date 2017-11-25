package net.brian.coding.java.core.jdk.jvm.initialization;

/**
 * Java�и�ֵ˳�� 
 * 1. ����ľ�̬������ֵ
 * 2. ����ľ�̬������ֵ 
 * =====�ָ���=====
 * 3. �����Ա������ֵ 
 * 4. ����鸳ֵ 
 * 5. ���๹�캯����ֵ 
 * =====�ָ���=====
 * 6. �����Ա������ֵ 
 * 7. ����鸳ֵ 
 * 8. �����캯����ֵ
 * 
 *
 */
public class InitializationSequence extends A {
	/**
	 * ���û������new InitializationSequence();�Ĵ�������Java�и�ֵ˳��Ӧ�����������
	 * A static
	 * InitializationSequence static
	 * �ȶ���������о�̬��ʼ�������������Ҫjvm���������������أ����Ȼ��ȰѾ�̬��Աload��ȥ
	 * A instantiated
	 * A constructor.
	 * Ȼ����ظ����������Ϣ������ʵ�����飬Ȼ���ǹ������������������һ������һ�����������Ͷ���Ϳ�����
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * ���������ļ���
	 * 
	 * ע����main������������InitializationSequence t = new InitializationSequence();����ֱ��new InitializationSequence();
	 * ��������һ���ģ�Ҳ����˵������û������t��ֻ�ǵ����ĵ��ù��������Ǿ�̬��ʼ���������ᱻִ��
	 */
	/**
	 * ���������new InitializationSequence();�Ĵ�������Java�и�ֵ˳��Ӧ�����������
	 * A static
	 * �ȶԾ�̬���ʼ������ʼ����A��ʱ���ʼ��public static InitializationSequence t1 = new InitializationSequence();
	 * ������Ҫnew InitializationSequence()����������Ĺ���������Ҫ����˳��ֱ�ִ�и����ʵ�������������������ʵ��������������
	 * ������������н�������
	 * A instantiated
	 * A constructor
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * ��̬��ʼ��public static InitializationSequence t1 = new InitializationSequence();��������������̬��ʼ����̬�����
	 * InitializationSequence static
	 * ��̬��ʼ����̬�����
	 * A instantiated
	 * A constructor
	 * InitializationSequence instantiated
	 * InitializationSequence constructor
	 * �ٴΰ���˳��ֱ�ִ�и����ʵ�������������������ʵ��������������
	 */
	 public static InitializationSequence t1 = new InitializationSequence();
	
	/**
	 * ���д��뵼�µݹ���ã���Ҫ����д
	 */
	// public InitializationSequence t2 = new InitializationSequence();
	static {
		System.out.println("InitializationSequence static");
	}
	{
		System.out.println("InitializationSequence instantiated");
	}

	public InitializationSequence() {
		System.out.println("InitializationSequence constructor");
	}

	public static void main(String[] args) {
		new InitializationSequence();
	}
}

class A {
	public A() {
		System.out.println("A constructor");
	}

	{
		System.out.println("A instantiated");
	}
	static {
		System.out.println("A static");
	}

}