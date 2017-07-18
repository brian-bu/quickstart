package net.brian.coding.java.core.jdk.concurrency;

import java.util.List;
import java.util.Vector;

/**
 * 
 * 偏向锁：如果程序没有竞争，则取消之前已经取得锁的线程同步操作。也就是说，若某一锁被线程获取后就进入偏向模式，
 * 当线程再次请求这个锁时无须进行相关的同步操作，节省操作时间。-XX:+UseBiasedLocking可设置启用偏向锁。
 * 
 * 为了保证线程安全性而不会因为出错干扰了偏向锁的效果，本例没有使用ArrayList而是使用了Vector ArrayList的用法见：@see
 * net.brian.coding.java.core.jdk.concurrency.ThreadUnsafeArrayList
 * 
 * 为了显示偏向锁的作用需要配置虚拟机参数如下：
 * 启用偏向锁并在JVM启动时（0秒延迟）启用：
 * （如果不配置XX:BiasedLockingStartupDelay将默认在jvm启动后4秒启用偏向锁）
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 * 输出结果565
 * 不启用偏向锁：
 * -XX:-UseBiasedLocking -client -Xmx512m -Xms512m
 * 输出结果766
 * 
 * 由此明显看出启用偏向锁对效率的提升
 */
public class BiasedLocking {
	public static List<Integer> numberList = new Vector<Integer>();

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		int count = 0;
		int startNum = 0;
		while (count < 10000000) {
			numberList.add(startNum);
			startNum += 2;
			count++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}
