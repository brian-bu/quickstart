package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/InterruptingIdiom.java
import java.util.concurrent.*;

/**
 * 
 * ����ʹ�ò�ͬ���ӳ�ʱ������ڲ�ͬ�ĵص��˳�run����������Ӧinterrupt����
 * ��ͨ����ζ��������Ҫ����Ķ��󴴽������ĺ��涼�������try-finally �Ӷ�ʹ������run����˳������������ᷢ��
 * NeedsCleanupǿ�������쳣�뿪�����ʱ����ȷ������Դ�ı�Ҫ�ԣ�ʵ�ʳ�����Ҳ����û������࣬����try-finally��һ��Ҫ�е�
 *
 */
class NeedsCleanup {
	private final int id;

	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}

	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// student1�������￪ʼ�� point2֮ǰ
				NeedsCleanup n1 = new NeedsCleanup(1);
				// ������n1�������������һ��try-finallyȷ��n1������ȷ����
				try {
					System.out.println("Sleeping");
					// ����������е�����֮�������interrupt�������������run��������InterruptedException�ķ�ʽ�˳�
					// ���ʱ�����������n1���󣬻�û�д���n2����������ͨ���˵�һ��try-finallyȷ����n1������������
					TimeUnit.SECONDS.sleep(1);
					// point2:�����￪ʼ֮ǰ����ͨ��sleep�����õ��³�������һС��ʱ��
					// ����������е�����֮�������interrupt�����������Ƚ���forѭ����ִ�в��������б��ض���֮����while�����˳�
					// ִ��forѭ����ʱ��n2�����Ѿ����������������ʱ����Ҫ���ɵڶ���try-finally���г��������
					NeedsCleanup n2 = new NeedsCleanup(2);
					// ������n2�������������һ��try-finallyȷ��n2������ȷ����
					try {
						System.out.println("Calculating");
						// ������һ������������ѧ���㣬���ǱȽϺ�ʱ����;�п��ܱ����
						for (int i = 1; i < 2500000; i++)
							d = d + (Math.PI + Math.E) / d;
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
}

public class InterruptingIdiom {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java InterruptingIdiom delay-in-mS");
			System.exit(1);
		}
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}
}