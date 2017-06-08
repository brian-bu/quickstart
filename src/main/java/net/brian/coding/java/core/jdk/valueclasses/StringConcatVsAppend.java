package net.brian.coding.java.core.jdk.valueclasses;
/**
 * 
 * 对于如下程序的输出结果，每次都是concat值最大，其次buffer，最小的是builder
 * 
 * 对于builder和buffer区别在于synchronized关键字带来的性能损耗，所以我们只讨论concat和buffer的append方法
 * 
 * 对于concat，影响性能的核心源码如下：
 *  int len = value.length;
        char buf[] = Arrays.copyOf(value, len + otherLen);
        str.getChars(buf, len);
        return new String(buf, true);
 * 消耗性能的方面如下：
 * 肯定会调用一次Arrays.copyOf
 * 这里有一次getChars的调用，这个方法底层是对System.arraycopy的调用，这次调用是十分耗性能的
 * return的时候还要创建一个新的String对象
 * 
 * 对于buffer，影响性能的核心源码如下：
 *  int len = str.length();
		ensureCapacityInternal(count + len);
 		str.getChars(0, len, value, count);
 * 这段代码出自buffer和builder共用的父类方法java.lang.AbstractStringBuilder.append(String)
 * 这里len是append入参字符串的长度，因此ensureCapacityInternal的入参是原来字符串的长度加上新添加的字符串的长度
 * 消耗性能的主要方面如下：
 * ensureCapacityInternal底层会有一次Arrays.copyOf的调用
 * 同concat，这里也会有一次getChars的调用
 * 
 * 由此可见concat和buffer底层对性能影响的区别主要在于：
 * buffer不需要new一个String对象，而concat每次都new一个，这不仅浪费空间，而且也会一定程度影响性能
 * 但是如果是两个字符串的简单连接就可以用concat，毕竟没必要为此手动创建一个buffer对象，这样节省了很多代码
 */
public class StringConcatVsAppend {
	public static void main(String[] args) {
		long[] timeStringconcat = new long[10];
		long[] timeStringBuffer = new long[10];
		;
		long[] timeStringBuilder = new long[10];
		;
		for (int x = 0; x < 10; x++) {
			System.out.println("-----------第" + (x + 1) + "次------------------");
			long startTime1 = System.currentTimeMillis();
			String str1 = "字符串拼接 ";
			for (int i = 0; i < 100000; i++) {
				str1.concat("Hello world!");
				str1.concat("Hello world!");
				str1.concat("Hello world!");
			}
			long endTime1 = System.currentTimeMillis();
			timeStringconcat[x] = (endTime1 - startTime1);
			System.out.println("使用String.concat()经过10W次拼接耗时：" + timeStringconcat[x] + "毫秒");

			long startTime2 = System.currentTimeMillis();
			StringBuffer sbr1 = new StringBuffer("字符串拼接");
			/**
			 * @see: net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
			 */
			sbr1.ensureCapacity(100000);
			for (int i = 0; i < 100000; i++) {
				sbr1.append("Hello world!");
				sbr1.append("Hello world!");
				sbr1.append("Hello world!");
			}
			long endTime2 = System.currentTimeMillis();
			timeStringBuffer[x] = (endTime2 - startTime2);
			System.out.println("使用StringBuffer.append()经过10W次拼接耗时：" + timeStringBuffer[x] + "毫秒");

			long startTime3 = System.currentTimeMillis();
			// 在for循环外部定义StringBuilder，在for循环内部进行append
			StringBuilder sbd1 = new StringBuilder("字符串拼接");
			/**
			 * 
			 * 对于当前的程序示例： 每次添加的都是长度相等且很小的字符串
			 * 所以无论append自动扩容还是手动扩容影响都不大
			 * 因此是否调用sbr1.ensureCapacity(100000);并不影响结果
			 * 具体是否调用ensureCapacity体现的性能差异另写一个类说明
			 * 
			 * @see: net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
			 */
			sbd1.ensureCapacity(100000);
			for (int i = 0; i < 100000; i++) {
				sbd1.append("Hello world!");
				sbd1.append("Hello world!");
				sbd1.append("Hello world!");
			}
			long endTime3 = System.currentTimeMillis();
			timeStringBuilder[x] = (endTime3 - startTime3);
			System.out.println("使用StringBuilder.append()经过10W次拼接耗时：" + timeStringBuilder[x] + "毫秒");
			System.out.println("----------------------------------");
		}
		System.out.println("平均值：");
		long result = 0l;
		for (long avg : timeStringconcat) {
			result += avg;
		}
		System.out.println(result / 10.0);
		result = 0l;
		for (long avg : timeStringBuffer) {
			result += avg;
		}
		System.out.println(result / 10.0);
		result = 0l;
		for (long avg : timeStringBuilder) {
			result += avg;
		}
		System.out.println(result / 10.0);
	}
}