package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.*;
/**
 * 
 * ��ϵͳ�ڴ�ռ����ʱ�������ú�ǿ����ûʲô����ϵͳ�ڴ�ռ䲻��ʱ�����������õĶ�����Ա���������������
 *
 */
class Person
{
	String name;
	int age;
	public Person(String name , int age)
	{
		this.name = name;
		this.age = age;
	}
	public String toString()
	{
		return "Person[name=" + name
			+ ", age=" + age + "]";
	}
}
public class SoftReferenceTest
{
	public static void main(String[] args) 
		throws Exception
	{
		@SuppressWarnings("unchecked")
		SoftReference<Person>[] people = 
			new SoftReference[100000];
		for (int i = 0 ; i < people.length ; i++)
		{
			people[i] = new SoftReference<Person>(new Person(
				"����" + i , (i + 1) * 4 % 100));
		}
		System.out.println(people[2].get());
		System.out.println(people[4].get());
		//֪ͨϵͳ������������
		System.gc();
		System.runFinalization();
		//�������ջ�������֮��SoftReference�������Ԫ�ر��ֲ���
		System.out.println(people[2].get());
		System.out.println(people[4].get());
	}
}
