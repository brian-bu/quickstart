package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;
/**
 * 
 * ǿ����ʾ������һ������һ������ǿ����������ʱ��ʼ�մ��ڿɴ�״̬����jvm������������Զ���ᱻ�ڴ����
 *
 */
public class StrongReferenceTest
{
	public static void main(String[] args) 
		throws Exception
	{
		Person[] people = 
			new Person[100000];
		for (int i = 0 ; i < people.length ; i++)
		{
			// ��new�����Ķ���ǿ���ã���˲����ڿ�ָ���쳣�����ǿ����������ڴ�й©����Ҫԭ��
			people[i] = new Person(
				"����" + i , (i + 1) * 4 % 100);
		}
		System.out.println(people[2]);
		System.out.println(people[4]);
		//֪ͨϵͳ������������
		System.gc();
		System.runFinalization();
		//StrongReference�����ﲻ���κ�Ӱ��
		System.out.println(people[2]);
		System.out.println(people[4]);
	}
}
