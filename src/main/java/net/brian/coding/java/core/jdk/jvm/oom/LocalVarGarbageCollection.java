package net.brian.coding.java.core.jdk.jvm.oom;
/**
 * ��������չʾ�ֲ��������������յ�Ӱ�죬�ֲ���������ջ֡����Ҫ��ɲ���֮һ�����ڱ��溯�������Լ��ֲ�����
 * �������ý��������˳�ʱ����ջ֡���ٶ����١��ֲ��������еı���Ҳ����Ҫ���������ո��ڵ�
 * ֻҪ���ֲ���������ֱ�ӻ������õĶ����ǲ��ᱻ���յġ���ˣ����ֲ�������������������Ҳ��һ������
 * 
 * ���ڴ���OOM��õķ������Ǵ��������ܴ�Ķ��󣬱�Ҫ�Ļ���������forѭ����������
 * ������ÿ�������ľֲ�����������һ��6MB��byte���飬��ʹ�þֲ���������
 * 
 * ����ʹ�������������-XX:+PrintGC -Xmx20m -Xms10m
 * ��ӡ����GC��־��
 * [GC (System.gc())  7062K->6800K(9728K), 0.0013850 secs]
 * [Full GC (System.gc())  6800K->610K(9728K), 0.0089410 secs]
 * 
 * �������־���з�����
 * a.���Կ��������ܹ��ָ�Java��9728K�ڴ棬��Լ10M�����ǲ���-Xms10m�����Ľ��
 * b.��һ���������ս�������1M�Ŀռ䣬����Ȼû�л��մ����
 * c.���Ŵ�����Full GC�����Կ�������֮ǰ6800k������֮��610k�����˴��6M���ң���λ������������˴����
 * d.��6M��ô��Ķ���һ����ֱ�ӷŵ�������ģ�����System.gc()��һ�δ��������������GC��ֱ��������Full GC��ʱ��Ż��������
 * ���-XX:+PrintGCDetails������ͬ�ĳ����ӡ����ϸ��GC��־���Կ�����
 * [GC (System.gc()) [PSYoungGen: 918K->488K(2560K)] 7062K->6800K(9728K), 0.0025846 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 * [Full GC (System.gc()) [PSYoungGen: 488K->0K(2560K)] [ParOldGen: 6312K->610K(7168K)] 6800K->610K(9728K), [Metaspace: 2569K->2569K(1056768K)], 0.0077014 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
 * Heap
 *  PSYoungGen      total 2560K, used 20K [0x00000000ff980000, 0x00000000ffc80000, 0x0000000100000000)
 *   eden space 2048K, 1% used [0x00000000ff980000,0x00000000ff985360,0x00000000ffb80000)
 *   from space 512K, 0% used [0x00000000ffb80000,0x00000000ffb80000,0x00000000ffc00000)
 *   to   space 512K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffc80000)
 *  ParOldGen       total 7168K, used 610K [0x00000000fec00000, 0x00000000ff300000, 0x00000000ff980000)
 *   object space 7168K, 8% used [0x00000000fec00000,0x00000000fec98930,0x00000000ff300000)
 *  Metaspace       used 2575K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 285K, capacity 386K, committed 512K, reserved 1048576K
 * �󲿷ֵĿռ䶼û�����ã�ֻ�������ParOldGen��һ�У�object space 7168K, 8% used������ռ�ռ��7M���ң������ԣ������������ǰ��������
 */
public class LocalVarGarbageCollection {
	private static final int BIG_SIZE = 6 * 1024 * 1024;
	// ����ռ���������գ���Ϊ������ǿ��������޷����գ������ڴ�й©�ĵ���
	public void localVarGc1() {
		@SuppressWarnings("unused")
		byte[] a = new byte[BIG_SIZE];
		System.gc();
	}
	// ����������Ϊʵ���������̲߳���ȫ�������ڷ����������ڴ�й©�������ô���أ�
	// �������һ��˼·����������������ֶ�֮һ�����ÿգ�ʧȥ��ǿ���ã������a�Ϳ��Ա�������
	public void localVarGc2() {
		@SuppressWarnings("unused")
		byte[] a = new byte[BIG_SIZE];
		a = null;
		System.gc();
	}
	// ����Ҳ�����ڴ�й©������ֲ������ʹ�þֲ�����aʧЧ�����Ǿֲ����������ɱ�����a
	public void localVarGc3() {
		{
			@SuppressWarnings("unused")
			byte[] a = new byte[BIG_SIZE];
		}
		System.gc();
	}
	@SuppressWarnings("unused")
	public void localVarGc4() {
		{
			byte[] a = new byte[BIG_SIZE];
		}
		int c = 10;
		System.gc();
	}
	// ��Ȼ��localVarGc1�е���������û��ʲôЧ�������������Ѿ���localVarGc5
	// ��ʱlocalVarGc1�����Ѿ��˳�ջ����Ӧ��ջ֡Ҳ�ͱ������ջ֡��ľֲ�������ͬ�������
	// ���ʱ��localVarGc1�����Ķ���a�Ѿ�ʧȥ���ڵ�����ȴ��������գ���ʱ��localVarGc5�ٵ���gc����Ŀ��Ի�����
	// ������Ҳ��������ֻҪ�����ڷ����ڲ��Ķ��󴴽���ʹ�ò������ڴ�й©�Ϳ��ԣ��������ý�����û��Ҫ���Ƿ����ڲ��ڴ�й©��
	public void localVarGc5() {
		localVarGc1();
		System.gc();
	}

	public static void main(String[] args) {
		LocalVarGarbageCollection gc = new LocalVarGarbageCollection();
		gc.localVarGc4();
	}
}
