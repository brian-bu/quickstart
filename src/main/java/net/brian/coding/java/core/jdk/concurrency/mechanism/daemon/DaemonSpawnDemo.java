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
	 // 用一个线程A批量启动10个线程，即使只把A设置为后台线程，它创建的其余10个线程自动为后台线程 
    Thread d = new Thread(new Daemon());
    d.setDaemon(true);
    d.start();
    System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
    // 如果把休眠时间设置的足够小，那么就会出现每次执行同样的程序出现不同的结果：
    // 有的时候控制台完整的输出log，但有的时候输出的不全，需要调大休眠时间才能输出完整的log
    TimeUnit.MILLISECONDS.sleep(1);
  }
}