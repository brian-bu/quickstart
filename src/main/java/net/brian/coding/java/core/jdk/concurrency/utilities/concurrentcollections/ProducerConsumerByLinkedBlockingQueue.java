package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 阻塞队列LinkedBlockingQueue之生产者-消费者例子，生产者每隔1秒生产1个产品
 * 6个消费者在消费产品，每隔1秒，只有一个消费者能够获取到产品消费，其它线程只能等待
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
				System.out.println(Thread.currentThread() + "生产：" + produce);
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
				System.out.println(Thread.currentThread() + "prepare 消费");
				System.out.println(Thread.currentThread() + "starting：" + queue.take());
				System.out.println(Thread.currentThread() + "end 消费");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}