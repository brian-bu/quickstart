package net.brian.coding.java.core.jdk.keywords;

/**
 * ���������漰���Զ�����ת����ת���ȼ�ͼ���£�
 * 
 * 			char  --> 
 * 					| --> int --> long --> float --> double
 * byte --> short -->
 * 
 * ע��һ��
 * E1 op= E2 �͵ĸ��ϸ�ֵ���ʽ�ȼ��� E1 = (T)((E1) op (E2))�����磺
 * +=��ʽ�Ľ��Ӳ����Ľ������ǿ��ת��Ϊ���н��������
 * ��������������ӣ���byte��short����int�����ȻὫ����������int���ͣ�Ȼ����ִ�мӷ�����
 * ����ӷ������Ľ����a�����ֵҪ���� a+b����ֱ�����󣬵� a += bû����
 * short x = 3;x += 4.6;x���Ϊ7����Ϊ���ȼ���short x = 3;x = (short)(x + 4.6);
 * ���Ƕ���int i = 5;long j = 8;��i+=j���ԣ�i=i+j;���޷�ͨ������:Type mismatch: cannot convert from long to int
 * ��Ϊi += j; ʵ������i = (type of i) (i + j)�ļ�д�����������������ת��ͼ��int����ǿתlong��long������ǿתint
 * 
 * ע�����
 * ���Խ�intǿ��ת����byte������ Java��int�� 32λ�ģ���byte��8λ�ģ�����ǿ��ת��ʱint���͵ĸ� 24λ���ᱻ����
 *
 */
public class AutoPromote
{
	public static void main(String[] args) 
	{
		//����һ��short���ͱ���
		@SuppressWarnings("unused")
		short sValue = 5;
		//���ʽ�е�sValue���Զ�������int���ͣ����ұߵı��ʽ����Ϊint
		//��һ��int���͸���short���͵ı�������������
		// sValue = sValue - 2;
		byte b = 40;
		char c = 'a';
		int i = 23;
		double d = .314;
		//�ұ߱��ʽ������ߵȼ�������Ϊd��double�ͣ�
		//���ұ߱��ʽ������Ϊdouble��,�ʸ���һ��double�ͱ���
		double result = b + c + i * d;
		//�����144.222
		System.out.println(result);
		int val = 3;
		//�ұ߱��ʽ��2������������int�����ұ߱��ʽ������Ϊint
		//��ˣ���Ȼ23/3���ܳ�������Ȼ�õ�һ��int����
		int intResult = 23 / val;
		//�����7
		System.out.println(intResult);
		//�����Զ���7��'a'�Ȼ�������ת��Ϊ�ַ���������ַ���Hello!a7
		System.out.println("Hello!" + 'a' + 7);
		//���򽫰�'a'����int�������'a' + 7�õ�104������ַ���104Hello!
		System.out.println('a' + 7 + "Hello!");
	}
}
