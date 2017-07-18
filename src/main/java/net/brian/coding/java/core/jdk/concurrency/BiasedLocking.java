package net.brian.coding.java.core.jdk.concurrency;

import java.util.List;
import java.util.Vector;

/**
 * 
 * ƫ�������������û�о�������ȡ��֮ǰ�Ѿ�ȡ�������߳�ͬ��������Ҳ����˵����ĳһ�����̻߳�ȡ��ͽ���ƫ��ģʽ��
 * ���߳��ٴ����������ʱ���������ص�ͬ����������ʡ����ʱ�䡣-XX:+UseBiasedLocking����������ƫ������
 * 
 * Ϊ�˱�֤�̰߳�ȫ�Զ�������Ϊ���������ƫ������Ч��������û��ʹ��ArrayList����ʹ����Vector ArrayList���÷�����@see
 * net.brian.coding.java.core.jdk.concurrency.ThreadUnsafeArrayList
 * 
 * Ϊ����ʾƫ������������Ҫ����������������£�
 * ����ƫ��������JVM����ʱ��0���ӳ٣����ã�
 * �����������XX:BiasedLockingStartupDelay��Ĭ����jvm������4������ƫ������
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 * ������565
 * ������ƫ������
 * -XX:-UseBiasedLocking -client -Xmx512m -Xms512m
 * ������766
 * 
 * �ɴ����Կ�������ƫ������Ч�ʵ�����
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
