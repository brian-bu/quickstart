package net.brian.coding.java.core.jdk.concurrency.mechanism.daemon;

import java.util.concurrent.TimeUnit;

//: concurrency/SimpleThread.java

public class DaemonDemo extends Thread {
  private int countDown = 5;
  private static int threadCount = 0;
  public DaemonDemo() {
    // Store the thread name:
    super(Integer.toString(++threadCount));
    // ���Ҫ��һ���߳�����Ϊ��̨�̣߳���ôһ��Ҫ��start����֮ǰ����setDaemon����
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
    // �����Ѿ�ͨ��setDaemon(true);�����е��߳�����Ϊ��̨���У����һ��main�˳����̨�߳̾���ɱ������ʹû��ִ����
    // ������main������1�룬Ŀ�ľ���Ϊ�˵ȵ����еĺ�̨�̶߳�ִ���������˳�
    // ���Կ���û��������߶���ֱ���˳��Ļ���ÿ��ִ�г�������Ľ������һ��
    // ������Ϊ�Ѿ���� �������ִ����ĺ�̨�̣߳���δ���ü��������ĺ�̨�߳�ֱ���˳�
    TimeUnit.SECONDS.sleep(1);
  }
} 