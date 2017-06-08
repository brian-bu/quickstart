package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.*;
/**
 * 
 * 当系统内存空间充足时，软引用和强引用没什么区别，系统内存空间不足时软引用所引用的对象可以被垃圾回收器回收
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
				"名字" + i , (i + 1) * 4 % 100));
		}
		System.out.println(people[2].get());
		System.out.println(people[4].get());
		//通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		//垃圾回收机制运行之后，SoftReference数组里的元素保持不变
		System.out.println(people[2].get());
		System.out.println(people[4].get());
	}
}
