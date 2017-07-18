package net.brian.coding.java.core.jdk.concurrency;
/**
 * 
 * 
 * �������룺������������˺ܳ�ʱ��Ҳû��ʲô���⣬Ҳ����˵û�г���MultiThreadVolatileLong.tֵ�ʹ���������ȵ����
 * �������ճ��������������Ҳ��֤���˲����ĸ���long����double���������ܱ�֤�޸Ľ����ȷ
 * �������Ϊ��long��double����Ϊ64λ���޷�һ���Բ�����������ǵĲ���������ԭ�ӵģ����������¿��ܻ����һЩ���벻���Ĵ���
 * ���ܳ���һ���߳�д��long��32λ����һ���߳�д��long������32λ��
 * 
 * ����취���ǽ�long����Ϊvolatile�ģ�����public static volatile long t = 0;
 *
 */
public class MultiThreadVolatileLong {
	public static long t = 0;
	public static class ChangeT implements Runnable {
		private long to;
		public ChangeT(long to) {
			this.to = to;
		}
		@Override
		public void run() {
			while(true) {
				MultiThreadVolatileLong.t = to;
				Thread.yield();
			}
		}
	}
	public static class ReadT implements Runnable {

		@Override
		public void run() {
			while(true) {
				long tmp = MultiThreadVolatileLong.t;
				if(tmp!=111L && tmp!=-999L  && tmp!=333L && tmp!=-444L)
					System.out.println(tmp);
				Thread.yield();
			}
		}
		
	}
	public static void main(String[] args) {
		new Thread(new ChangeT(111L)).start();;
		new Thread(new ChangeT(-999L)).start();;
		new Thread(new ChangeT(333L)).start();;
		new Thread(new ChangeT(-444L)).start();;
		new Thread(new ReadT()).start();;
	}
}
