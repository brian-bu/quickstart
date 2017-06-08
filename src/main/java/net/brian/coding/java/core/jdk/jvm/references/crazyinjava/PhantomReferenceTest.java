package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
/**
 * 
 * ��������Ҫ���ڸ��ٶ����������յ�״̬�������ò��ܵ���ʹ�ã���������ö���ReferenceQueue���ʹ��
 *
 */
public class PhantomReferenceTest
{
	public static void main(String[] args) 
		throws Exception
	{
		//����һ���ַ�������
		String str = new String("hello world");
		//����һ�����ö���
		ReferenceQueue<String> rq = new ReferenceQueue<String>();
		//����һ�������ã��ô����������õ�hello world�ַ���
		PhantomReference<String> pr = 
			new PhantomReference<String>(str , rq);
		//�ж�str���ú�hello world�ַ���֮�������
		str = null;
		//��ͼȡ�������������õĶ���
		//���򲢲���ͨ�������÷��ʱ����õĶ������Դ˴����null
		System.out.println(pr.get());
		//ǿ����������
		System.gc();
		System.runFinalization();
		//ȡ�����ö��������Ƚ��������������pr���бȽ�
		System.out.println(rq.poll() == pr);
	}
}
