package net.brian.coding.java.core.jdk.concurrency.mechanism.daemon;

import java.util.concurrent.TimeUnit;

//: concurrency/SimpleThread.java

public class DaemonDemo extends Thread {
  private int countDown = 5;
  private static int threadCount = 0;
  public DaemonDemo() {
    // Store the thread name:
    super(Integer.toString(++threadCount));
    // 如果要把一个线程设置为后台线程，那么一定要在start方法之前调用setDaemon方法
    setDaemon(true);
    start();
  }
  public String toString() {
    return "#" + getName() + "(" + countDown + "), ";
  }
  public void run() {
    while(true) {
      System.out.print(this);
      if(--countDown == 0)
        return;
    }
  }
  public static void main(String[] args) throws InterruptedException {
    for(int i = 0; i < 5; i++) new DaemonDemo();
    // 由于已经通过setDaemon(true);将所有的线程设置为后台运行，因此一旦main退出则后台线程均被杀死，即使没有执行完
    // 这里让main先休眠1秒，目的就是为了等到所有的后台线程都执行完了再退出
    // 可以看到没有这句休眠而是直接退出的话，每次执行程序输出的结果都不一样
    // 这是因为已经输出 结果的是执行完的后台线程，而未来得及输出结果的后台线程直接退出
    TimeUnit.SECONDS.sleep(1);
  }
} 