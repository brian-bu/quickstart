package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * @see net.brian.coding.java.core.jdk.jvm.initialization.InitializationSequence
 * 
 * ����InitializationSequence�ļ������ۣ���ֻ��һ�����ʱ��ĳ�ʼ��˳��
 * ��static��Ȼ��ʵ������Ȼ������
 * ������Client�˵��û���static��ʽ��ʼ����ʱ����ã�ֻҪ�����˹������ͻ��Ӧ�ȷǾ�̬��ʼ�����ٹ������ĵ���
 * ������ȱ����ʼ��������ʽ��̬��ʼ���������ι������������
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 * ��������ʼ�ֵ�static��̬��ʼ���飬�����Static InitializationSequence2
 * ������main�����е��õĹ�������Ӧ�������
 * Instantiate InitializationSequence2
 * Construct InitializationSequence2
 *
 */
public class InitializationSequence2 {
	public static InitializationSequence2 t1 = new InitializationSequence2();
	public static InitializationSequence2 t2 = new InitializationSequence2();
	 {
		System.out.println("Instantiate InitializationSequence2");
	}
	static {
		System.out.println("Static InitializationSequence2");
	}

	public InitializationSequence2() {
		System.out.println("Construct InitializationSequence2");
	}

	public static void main(String[] args) {
		new InitializationSequence2();
	}
}
