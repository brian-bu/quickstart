package net.brian.coding.java.core.jdk.jvm.initialization;

/**
 * Java�и�ֵ˳�� 
 * 1. ����ľ�̬������ֵ
 * 2. ����ľ�̬������ֵ 
 * 3. �����Ա������ֵ 
 * 4. ����鸳ֵ 
 * 5. ���๹�캯����ֵ 
 * 6. �����Ա������ֵ 
 * 7. ����鸳ֵ 
 * 8. �����캯����ֵ
 * 
 * ��ĳ�ʼ����������ൽ���ࡢ������˳�򣬶�������ĳ�ʼ����䡢��̬��ʼ���������ν��г�ʼ��
 * ������ʵ���ĳ�ʼ��Ҳ���ƣ���������ൽ���ࡢ������˳�򣬶����Ա�ĳ�ʼ����䡢ʵ����ʼ���顢���췽�����ν��г�ʼ��
 * ���������������е������չʾ��ÿһ�������ڳ�ʼ���е�˳��
 * (1).����T�౻���ء����Ӻ���г�ʼ�������ȶ��ֶ�k��t1��t2��i��n�Լ�static����г�ʼ����
 * (2).t1ʵ���ĳ�ʼ�����ʼ��ʵ����Աj��(ʵ�����Ƚ��и���ʵ�����ݵĳ�ʼ��)�ȵ��þ�̬����print����ִ��ʵ����ʼ����{}������� 1: j i=0
 * n= 0(i��n����û�г�ʼ��) 2:����� i=1 n=1 (3)������t1ʵ���Ĺ��캯��������� 3:t1 i=2 n=2
 * (4).������t2ʵ���ĳ�ʼ���� 4: j i=3 n= 3 5:����� i=4 n=4 6:t2 i=5 n=5 (5).i�ĳ�ʼ���� 7.i i=6
 * n=6 (6).n�ĳ�ʼ���;�̬��ĳ�ʼ���� 8.��̬�� i=7 n=99(n�Ѿ�����ʼ��) (7).tʵ���ĳ�ʼ���� 9.j i=8 n= 100
 * 10.����� i=9 n= 101 11.init i=10 n= 102
 *
 */
public class InitializationSequence implements Cloneable {
	public static int k = 0;
	public static InitializationSequence t1 = new InitializationSequence("t1");
	public static InitializationSequence t2 = new InitializationSequence("t2");
	public static int i = print("i");
	public static int n = 99;

	public int j = print("j");
	{
		print("�����");
	}

	static {
		print("��̬��");
	}

	public InitializationSequence(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		++i;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		return ++i;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		InitializationSequence t = new InitializationSequence("init");
	}
}