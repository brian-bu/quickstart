package net.brian.coding.java.core.jdk.concurrency.inpractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * Javaģ�Ⲣ���������з�����ѹ������
 *
 */
public class ConcurrencySimulation {
	
	static int count = 0;
	//�ܷ�������client_num����������thread_num
	int thread_num = 10;
	int client_num = 1000;
	
	float avg_exec_time = 0;
	float sum_exec_time = 0;
	long first_exec_time = Long.MAX_VALUE;
	long last_done_time = Long.MIN_VALUE;
	float total_exec_time = 0;
	
	String url = "";
	String postData = "";
	
	public ConcurrencySimulation(int thread_num, int client_num, String url, String postData){
		
		this.thread_num = thread_num;
		this.client_num = client_num;
		this.url = url;
		this.postData = postData;
	}
	
	
	public void run() {
		
		final ConcurrencySimulation currentObj = this;
		
		final ConcurrentHashMap<Integer, ThreadRecord> records = new ConcurrentHashMap<Integer, ThreadRecord>();

		// ����ExecutorService�̳߳�
		ExecutorService exec = Executors.newFixedThreadPool(thread_num);
		// thread_num���߳̿���ͬʱ���� 
		// ģ��client_num���ͻ��˷���
		
		final CountDownLatch doneSignal = new CountDownLatch(client_num);
		
		for (int i = 0; i < client_num; i++) {
			
			Runnable run = new Runnable() {
				
				public void run() {
					
					int index = getIndex();
					long st = System.currentTimeMillis();
					
					try {
						
						//��������һЩ��Դ�������߿������޸�
						URL url = new URL(currentObj.url);
						HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
						urlConn.connect();
						urlConn.setReadTimeout(50000);
						BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
						
						
						String src = "";
						String line = null; 
						while ((line = reader.readLine()) != null) {
							src = src + line;
						}
						//System.out.println(src);
						urlConn.disconnect();
					} catch (Exception e) { 
						e.printStackTrace(); 
					} 
					records.put(index, new ThreadRecord(st, System.currentTimeMillis()));
					doneSignal.countDown();//ÿ����һ��countDown()��������������1
				} 
			}; 
			exec.execute(run); 
		}
		
		try {
			//����������0 ʱ��await()�����������������ִ��
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/**
		 * ��ȡÿ���̵߳Ŀ�ʼʱ��ͽ���ʱ��
		 */
		for(int i : records.keySet()){
			ThreadRecord r = records.get(i);
			sum_exec_time += ((double)(r.et - r.st))/1000;
			
			if(r.st < first_exec_time){
				first_exec_time = r.st;
			}
			if(r.et > last_done_time){
				this.last_done_time = r.et;
			}
		}
		
		this.avg_exec_time = this.sum_exec_time / records.size();
		this.total_exec_time = ((float)(this.last_done_time - this.first_exec_time)) / 1000;
		NumberFormat nf=NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(4);
		
		
		System.out.println("======================================================");
		System.out.println("Thread Num: " + thread_num + ", Client Count: "+ client_num +".");
		System.out.println("Avg Exec Time:   " + nf.format(this.avg_exec_time) + " s");
		System.out.println("Total Exec Time: " + nf.format(this.total_exec_time) + " s");
		System.out.println("Throughput:      " + nf.format(this.client_num /this.total_exec_time)+ " /s");
	}
	
	public static int getIndex(){
		return ++count;
	}
	
	public static void main(String[] args) {
		//�ܷ������Ͳ���������ѭ���������������
		for(int j=500; j<600; j+= 100){
			for(int i=10; i<100; i+=10){
				//Ҫ���Ե�URL
				String url="http://www.baidu.com/";
				new ConcurrencySimulation(i, j, url, "").run();
			}
		}
		System.out.println("finished!");
	}
}

class ThreadRecord {
	long st;
	long et;
	public ThreadRecord(long st, long et){
		this.st = st;
		this.et = et;
	}
}