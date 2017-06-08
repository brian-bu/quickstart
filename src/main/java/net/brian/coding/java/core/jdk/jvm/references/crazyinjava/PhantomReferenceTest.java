package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
/**
 * 
 * 虚引用主要用于跟踪对象被垃圾回收的状态，虚引用不能单独使用，必须和引用队列ReferenceQueue结合使用
 *
 */
public class PhantomReferenceTest
{
	public static void main(String[] args) 
		throws Exception
	{
		//创建一个字符串对象
		String str = new String("hello world");
		//创建一个引用队列
		ReferenceQueue<String> rq = new ReferenceQueue<String>();
		//创建一个虚引用，让此虚引用引用到hello world字符串
		PhantomReference<String> pr = 
			new PhantomReference<String>(str , rq);
		//切断str引用和hello world字符串之间的引用
		str = null;
		//试图取出虚引用所引用的对象，
		//程序并不能通过虚引用访问被引用的对象，所以此处输出null
		System.out.println(pr.get());
		//强制垃圾回收
		System.gc();
		System.runFinalization();
		//取出引用队列中最先进入队列中引用与pr进行比较
		System.out.println(rq.poll() == pr);
	}
}
