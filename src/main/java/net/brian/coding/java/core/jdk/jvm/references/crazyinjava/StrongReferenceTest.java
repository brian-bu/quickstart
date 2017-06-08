package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;
/**
 * 
 * 强引用示例：当一个对象被一个或多个强引用所引用时，始终处于可达状态，在jvm生命周期内永远不会被内存回收
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
			// 用new创建的都是强引用，因此不存在空指针异常，因此强引用是造成内存泄漏的主要原因
			people[i] = new Person(
				"名字" + i , (i + 1) * 4 % 100);
		}
		System.out.println(people[2]);
		System.out.println(people[4]);
		//通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		//StrongReference数组里不受任何影响
		System.out.println(people[2]);
		System.out.println(people[4]);
	}
}
