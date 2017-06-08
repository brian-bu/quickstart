package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * ��������LinkedBlockingQueue֮������-���������ӣ�������ÿ��1������1����Ʒ
 * 6�������������Ѳ�Ʒ��ÿ��1�룬ֻ��һ���������ܹ���ȡ����Ʒ���ѣ������߳�ֻ�ܵȴ�
 *
 */

public class ProducerConsumerByLinkedBlockingQueue {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		for (int i = 0; i < 1; i++) {
			new Thread(new Producer(queue)).start();
		}
		for (int i = 0; i < 6; i++) {
			new Thread(new Consumer(queue)).start();
		}
	}
}

class Producer implements Runnable {
	private final BlockingQueue<String> fileQueue;

	public Producer(BlockingQueue<String> queue) {
		this.fileQueue = queue;
	}

	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(1000);
				String produce = this.produce();
				System.out.println(Thread.currentThread() + "������" + produce);
				fileQueue.put(produce);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public String produce() {
		SimpleDateFormat dfdate = new SimpleDateFormat("HH:mm:ss");
		return dfdate.format(new Date());
	}
}

class Consumer implements Runnable {
	private final BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(1000);
				System.out.println(Thread.currentThread() + "prepare ����");
				System.out.println(Thread.currentThread() + "starting��" + queue.take());
				System.out.println(Thread.currentThread() + "end ����");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}