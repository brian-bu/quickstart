package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * ����һ����ʾ����Ρ��滵���������ѭ����������󲢲��ͷ�����
 * ��һ����Ҳʾ�������ͨ�����ò�����ȡdump�ļ�
 * 
 * ����ʹ�������������-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/OneDrive/desktop/a.dump -Xmx20m -Xms5m
 * ����̨��ӡ����GC��־��
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
