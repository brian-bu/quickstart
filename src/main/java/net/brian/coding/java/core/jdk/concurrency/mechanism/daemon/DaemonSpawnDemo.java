package net.brian.coding.java.core.jdk.concurrency.mechanism.daemon;

//: concurrency/Daemons.java
import java.util.concurrent.*;

class Daemon implements Runnable {
  private Thread[] t = new Thread[10];
  public void run() {
    for(int i = 0; i < t.length; i++) {
      t[i] = new Thread(new DaemonSpawn());
      t[i].start();
      System.out.println("DaemonSpawn " + i + " started, ");
    }
    for(int i = 0; i < t.length; i++)
    	System.out.println("t[" + i + "].isDaemon() = " +
        t[i].isDaemon() + ", ");
    while(true)
      Thread.yield();
  }
}

class DaemonSpawn implements Runnable {
  public void run() {
    while(true)
      Thread.yield();
  }
}

public class DaemonSpawnDemo {
  public static void main(String[] args) throws Exception {
	 // ��һ���߳�A��������10���̣߳���ʹֻ��A����Ϊ��̨�̣߳�������������10���߳��Զ�Ϊ��̨�߳� 
    Thread d = new Thread(new Daemon());
    d.setDaemon(true);
    d.start();
    System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
    // ���������ʱ�����õ��㹻С����ô�ͻ����ÿ��ִ��ͬ���ĳ�����ֲ�ͬ�Ľ����
    // �е�ʱ�����̨���������log�����е�ʱ������Ĳ�ȫ����Ҫ��������ʱ��������������log
    TimeUnit.MILLISECONDS.sleep(1);
  }
}