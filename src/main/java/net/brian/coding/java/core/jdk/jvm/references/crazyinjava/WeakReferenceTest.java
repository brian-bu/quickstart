package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.WeakReference;
/**
 * 
 * ʹ��������ʱ��Ҫע�⴦���ָ���쳣
 *
 */
public class WeakReferenceTest
{
	public static void main(String[] args) throws Exception
	{
		// ����һ���ַ������󣬲�Ҫ��ֱ���������򿴲���Ч��
		// ��Ϊֱ�����ᱻ�ŵ������أ�����ǿ���û�����
		// String str = "hello world";
		String str = new String("hello world");
		// ����һ�������ã��ô����������õ�"���Java����"�ַ���
		WeakReference<String> wr = new WeakReference<String>(str);
		// �ж�str���ú�hello world�ַ���֮�������
		str = null; // ��
		// ȡ�������������õĶ���
		System.out.println(wr.get());
		// ǿ����������
		System.gc();
		System.runFinalization();
		// �ٴ�ȡ�������������õĶ���
		// ��ʱȡ�����ǿ�ֵnull�����ʹ��������ʱ��Ҫע�⴦���ָ���쳣
		System.out.println(wr.get());// null
	}
}
