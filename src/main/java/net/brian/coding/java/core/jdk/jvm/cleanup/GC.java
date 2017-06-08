package net.brian.coding.java.core.jdk.jvm.cleanup;

import java.util.concurrent.TimeUnit;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item07: Avoid finalizers
 * 
 * ����finalize������ʹ�ã�ʵ������ô��ֻ��Ϊ��ʾ��һ��jvm�Ļ��ջ���
 * ��Ҫ��System.gc��System.runFinalization�Ի�����ֻ��������finalize������ִ�еĻ���
 * �������ܱ�֤�ս᷽��һ������������ʱ����ִ�У�Ҳ����˵����ǲ��ܳ���Ա���Ƶ�
 * �������System.runFinalizersOnExit��Runtime.runFinalizersOnExit��Ȼ�ܱ�֤finalizeһ����ִ��
 * �������Ƕ�������ȱ�ݣ��ѱ�������
 * 
 * ��ʵ�ʿ����в����Ƽ����Ǽ�ʹ��finalize������finalize������Ҫ������ȱ�㣺
 * a.JVM�ӳ�ִ��finalize������
 * �����finalize�����ر��Ѵ򿪵��ļ��Ǹ����صĴ�����Ϊ�����ļ��ᱣ���ڴ�״̬
 * ��һ�������ٳ��Դ�����ļ����������ٻ�ȡ���ļ���Ȩ�ޣ����ܵ��³�������ʧ��
 * b.�ս᷽���߳����ȼ��ϵͣ�
 * ��˶���GUI�����ĳ���ͼ�ζ�����ս��ٶȴﲻ��������е��ٶ�
 * c.ʹ��finalize���������ص�������ʧ
 * 
 * ��˲�Ҫ����finalize�������г����������������Ҫ��д��ʽ����ֹ����
 * ���͵���ʽ��ֹ������ÿ��IO����������close����
 * ��ʽ����ֹ����ͨ����finallyһ��ʹ��ȷ����ʱ��ֹ
 * 
 * Effective Java�Ͻ����˼���ʹ��finalize����������������ԭ�飺
 * a.��ȫ��
 * b.��ֹ�ǹؼ��ı�����Դ
 * c.�ս᷽��������
 *
 */
public class GC {

	public static GC SAVE_HOOK = null;

	public void test() {
		System.out.println("Yes , I am still alive");
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new GC();
		
		SAVE_HOOK = null;
		// ������������ֻ�ǽ��������ռ����������������õĶ����ͷ��ڴ�ռ䣬����GC������ʱ��������һ����
		System.gc();
		// ���������������һ���ᴥ��GC������������������Ѿ�������
		// System.runFinalizersOnExit(false);
		TimeUnit.MICROSECONDS.sleep(500);
		
//		if (null != SAVE_HOOK) { // ��ʱ����Ӧ�ô���(reachable, finalized)״̬
			SAVE_HOOK.test();
//		} else {
//			System.out.println("No , I am dead");
//		}
//		
//		SAVE_HOOK = null;
//		System.gc();
//		TimeUnit.MICROSECONDS.sleep(500);
//		
//		if (null != SAVE_HOOK) {
//			SAVE_HOOK.test();
//		} else {
//			System.out.println("No , I am dead");
//		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("execute method finalize()");
		SAVE_HOOK = this;
	}
}