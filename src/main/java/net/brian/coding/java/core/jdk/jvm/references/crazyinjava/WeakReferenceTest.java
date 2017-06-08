package net.brian.coding.java.core.jdk.jvm.references.crazyinjava;

import java.lang.ref.WeakReference;
/**
 * 
 * 使用弱引用时需要注意处理空指针异常
 *
 */
public class WeakReferenceTest
{
	public static void main(String[] args) throws Exception
	{
		// 创建一个字符串对象，不要用直接量，否则看不见效果
		// 因为直接量会被放到常量池，会用强引用缓存它
		// String str = "hello world";
		String str = new String("hello world");
		// 创建一个弱引用，让此弱引用引用到"疯狂Java讲义"字符串
		WeakReference<String> wr = new WeakReference<String>(str);
		// 切断str引用和hello world字符串之间的引用
		str = null; // ②
		// 取出弱引用所引用的对象
		System.out.println(wr.get());
		// 强制垃圾回收
		System.gc();
		System.runFinalization();
		// 再次取出弱引用所引用的对象
		// 这时取出的是空值null，因此使用弱引用时需要注意处理空指针异常
		System.out.println(wr.get());// null
	}
}
