package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * 本例一方面示范如何“玩坏”虚拟机：循环创建大对象并不释放引用
 * 另一方面也示范了如何通过配置参数获取dump文件
 * 
 * 本例使用虚拟机参数：-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/OneDrive/desktop/a.dump -Xmx20m -Xms5m
 * 控制台打印出的GC日志：
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to D:/OneDrive/desktop/a.dump ...
 * Heap dump file created [14945745 bytes in 0.112 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at net.brian.coding.java.core.jdk.jvm.oom.BigObjectInCollections.main(BigObjectInCollections.java:10)
 *
 */
public class BigObjectInCollections {
	public static void main(String[] args) {
		List<byte[]> list = new ArrayList<byte[]>();
		for(int i = 0; i < 25; i++) {
			list.add(new byte[1024*1024]);
		}
	}
}
