package net.brian.coding.java.core.jdk.jvm.oom;

import net.brian.coding.designpatterns.proxy.CglibBean;

/**
 * 
 * ����֪��spring�ȿ�ܴ���ʹ�ô���ʵ��aop��������̾Ͳ��ò������������࣬����һ���Ƿ������ô���
 * ����ʹ��ʱ��������jdk1.8����ģ�jdk��1.8��ʼ�Ѿ��Ƴ����ô�����Ϊֱ���ڶ����ڴ�ֱ������ռ��ˣ�����ռ��Ԫ������
 * 
 * ע�⣺�������Ǽ򵥵�ʾ��������һ����ϸ��ʾ����Ҳ��Ϊ����ͬʱ���������������������Ч��������
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/9499e452-75d9-4a2f-8d23-d7059e8816fe ӡ��ʼ�}
 *
 * ����ʹ�������������-XX:MaxMetaspaceSize=5M -XX:+PrintGC
 * ����̨��ӡ����GC��־��
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