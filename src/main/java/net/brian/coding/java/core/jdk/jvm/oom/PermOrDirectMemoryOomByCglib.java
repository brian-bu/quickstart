package net.brian.coding.java.core.jdk.jvm.oom;

import net.brian.coding.designpatterns.proxy.CglibBean;

/**
 * 
 * 我们知道spring等框架大量使用代理实现aop，这个过程就不得不创建大量的类，而类一般是放在永久代的
 * 本例使用时是运行在jdk1.8上面的，jdk从1.8开始已经移除永久代，改为直接在堆外内存直接申请空间了，这个空间叫元数据区
 * 
 * 注意：本例仅是简单的示例，还有一份详细的示例，也作为本人同时体验多个虚拟机参数的运行效果，见：
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/9499e452-75d9-4a2f-8d23-d7059e8816fe 印象笔记}
 *
 * 本例使用虚拟机参数：-XX:MaxMetaspaceSize=5M -XX:+PrintGC
 * 控制台打印出的GC日志：
 * [GC (Metadata GC Threshold)  7385K->1458K(60928K), 0.0046892 secs]
 * [Full GC (Metadata GC Threshold)  1458K->1333K(47616K), 0.0124795 secs]
 * [GC (Last ditch collection)  1333K->1333K(47616K), 0.0004111 secs]
 * [Full GC (Last ditch collection)  1333K->1282K(74752K), 0.0147939 secs]
 * [GC (Metadata GC Threshold)  1917K->1386K(74752K), 0.0006774 secs]
 * [Full GC (Metadata GC Threshold)  1386K->831K(106496K), 0.0137465 secs]
 * [GC (Last ditch collection)  831K->831K(107520K), 0.0004157 secs]
 * [Full GC (Last ditch collection)  831K->831K(158208K), 0.0072202 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 */
public class PermOrDirectMemoryOomByCglib {
	public static void main(String[] args) {
		int i = 0;
		try {
			for (i = 0; i < 1000000; i++) {
				@SuppressWarnings({ "rawtypes" })
				CglibBean bean = CglibBean.createCglibBean("_" + i);
			}
		} catch (Exception e) {
			System.out.println("total create count:: " + i);
		}
	}
}