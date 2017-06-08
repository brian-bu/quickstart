package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.util.WeakHashMap;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item06: Eliminate obsolete object references
 * 
 * 导致内存泄漏的几个方面：
 * a.Stack类自己管理内存，一般只要自己管理内存，程序员就应该警惕内存泄漏
 * 比如Stack：从栈中弹出的对象不会被垃圾回收，因为栈本身还保留着它的引用，因此每次一个元素被弹出栈就应该置空
 * b.缓存：
 * 用WeakHashMap代表缓存，当缓存中的项过期之后就会自动被删除
 * 前提是：只有当所要的缓存项的生命周期是由该键的外部引用而不是由值决定时
 * 有关缓存详见：
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/4a204468-80da-4273-99d9-d9ea81a7f97c 印象笔记}
 * c.监听器和其它回调：
 * 确保回调立即被当作垃圾回收的最佳方法是只保存它们的弱引用WeakReference，比如只将它们保存成WeakHashMap的键
 * 
 * 垃圾回收之前WeakHashMap和普通的HashMap没有区别
 * 一旦垃圾回收机制被执行，WeakHashMap中所有的key-value对都会被清空
 * 除非某些key还有强引用在引用着它们
 *
 */
class CrazyKey 
{
	String name;
	public CrazyKey(String name) 
	{
		this.name = name;
	}
	//重写hashCode()方法
	public int hashCode() 
	{ 
		return name.hashCode();
	}
	//重写equals方法
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
	//重写toString()方法
	public String toString()
	{
		return "CrazyKey[name=" + name + "]";
	}
}
//FIXME: jvm调优：用WeakHashMap解决内存泄漏问题
public class WeakHashMapTest
{
	public static void main(String[] args) throws Exception
	{
		WeakHashMap<CrazyKey , String> map
			= new WeakHashMap<CrazyKey , String>();
		//循环放入10个key-value对
		for (int i = 0 ; i < 10 ; i++)
		{
			map.put(new CrazyKey(i + 1 + "") , "value" + (i + 11));
		}
		//垃圾回收之前，WeakHashMap与普通HashMap并无区别
		System.out.println(map);
		System.out.println(map.get(new CrazyKey("2")));
		//通知垃圾回收
		System.gc();
		//暂停当前线程50ms，让垃圾回收后台线程获得执行
		Thread.sleep(50);
		//垃圾回收后，WeakHashMap里所有Entry全部清空
		System.out.println(map);
		System.out.println(map.get(new CrazyKey("2")));
	}
}
