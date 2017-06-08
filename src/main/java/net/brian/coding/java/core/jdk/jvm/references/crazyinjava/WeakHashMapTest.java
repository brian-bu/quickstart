package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.util.WeakHashMap;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item06: Eliminate obsolete object references
 * 
 * �����ڴ�й©�ļ������棺
 * a.Stack���Լ������ڴ棬һ��ֻҪ�Լ������ڴ棬����Ա��Ӧ�þ����ڴ�й©
 * ����Stack����ջ�е����Ķ��󲻻ᱻ�������գ���Ϊջ�����������������ã����ÿ��һ��Ԫ�ر�����ջ��Ӧ���ÿ�
 * b.���棺
 * ��WeakHashMap�����棬�������е������֮��ͻ��Զ���ɾ��
 * ǰ���ǣ�ֻ�е���Ҫ�Ļ�����������������ɸü����ⲿ���ö�������ֵ����ʱ
 * �йػ��������
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/4a204468-80da-4273-99d9-d9ea81a7f97c ӡ��ʼ�}
 * c.�������������ص���
 * ȷ���ص������������������յ���ѷ�����ֻ�������ǵ�������WeakReference������ֻ�����Ǳ����WeakHashMap�ļ�
 * 
 * ��������֮ǰWeakHashMap����ͨ��HashMapû������
 * һ���������ջ��Ʊ�ִ�У�WeakHashMap�����е�key-value�Զ��ᱻ���
 * ����ĳЩkey����ǿ����������������
 *
 */
class CrazyKey 
{
	String name;
	public CrazyKey(String name) 
	{
		this.name = name;
	}
	//��дhashCode()����
	public int hashCode() 
	{ 
		return name.hashCode();
	}
	//��дequals����
	public boolean equals(Object obj) 
	{
		if (obj == this)
		{
			return true;
		}
		if (obj != null && obj.getClass() == CrazyKey.class)
		{
			return name.equals(((CrazyKey)obj).name);
		}
		return false;
	}
	//��дtoString()����
	public String toString()
	{
		return "CrazyKey[name=" + name + "]";
	}
}
//FIXME: jvm���ţ���WeakHashMap����ڴ�й©����
public class WeakHashMapTest
{
	public static void main(String[] args) throws Exception
	{
		WeakHashMap<CrazyKey , String> map
			= new WeakHashMap<CrazyKey , String>();
		//ѭ������10��key-value��
		for (int i = 0 ; i < 10 ; i++)
		{
			map.put(new CrazyKey(i + 1 + "") , "value" + (i + 11));
		}
		//��������֮ǰ��WeakHashMap����ͨHashMap��������
		System.out.println(map);
		System.out.println(map.get(new CrazyKey("2")));
		//֪ͨ��������
		System.gc();
		//��ͣ��ǰ�߳�50ms�����������պ�̨�̻߳��ִ��
		Thread.sleep(50);
		//�������պ�WeakHashMap������Entryȫ�����
		System.out.println(map);
		System.out.println(map.get(new CrazyKey("2")));
	}
}
