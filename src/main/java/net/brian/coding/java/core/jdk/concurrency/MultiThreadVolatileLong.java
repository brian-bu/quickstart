package net.brian.coding.java.core.jdk.concurrency;
/**
 * 
 * 
 * 本例代码：这个程序运行了很长时间也没出什么问题，也就是说没有出现MultiThreadVolatileLong.t值和传入参数不等的情况
 * 但是最终程序还是有了输出，也就证明了并发的更改long或者double，并不能总保证修改结果正确
 * 这就是因为：long和double长度为64位，无法一次性操作，因此它们的操作都不是原子的，并发环境下可能会出现一些意想不到的错误
 * 可能出现一个线程写了long的32位，另一个线程写了long的另外32位。
 * 
 * 处理办法就是将long声明为volatile的，即：public static volatile long t = 0;
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
