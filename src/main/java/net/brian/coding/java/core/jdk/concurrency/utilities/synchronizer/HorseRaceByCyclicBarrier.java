package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer;

//: concurrency/HorseRace.java
import java.util.concurrent.*;
import java.util.*;

/**
 * 
 * CyclicBarrier适用于这样的情况：你希望创建一组任务，他们并行执行工作
 * 然后在进行下一个步骤之前等待，直至所有任务都完成（看起来有点像join，但比join更适合于应对这种情况）
 * 它使得所有并行任务都将在栅栏处列队，因此可以一致向前移动，这里很像CountDownLatch
 * 它与CountDownLatch的区别：
 * CountDownLatch是只触发一次的事件，CyclicBarrier可以多次重用
 *
 */

public class HorseRaceByCyclicBarrier {
	  static final int FINISH_LINE = 75;
	  private List<Horse> horses = new ArrayList<Horse>();
	  private ExecutorService exec =
	    Executors.newCachedThreadPool();
	  private CyclicBarrier barrier;
	  public HorseRaceByCyclicBarrier(int nHorses, final int pause) {
		  // 真正需要用到CyclicBarrier的地方，这里这个构造器的作用就是
		  // 创建一组数量为nHorses的任务，第二个参数是一个线程匿名类，也就是这里的任务的模板
	    barrier = new CyclicBarrier(nHorses, new Runnable() {
	    	 // Point 3：所有任务到达栅栏处，可以进行额外的处理
	    	public void run() {
	        StringBuilder s = new StringBuilder();
	        for(int i = 0; i < FINISH_LINE; i++)
	          s.append("="); // The fence on the racetrack
	        System.out.println(s);
	        for(Horse horse : horses)
	        	System.out.println(horse.tracks());
	        for(Horse horse : horses)
	        	// 栅栏的特点就是可重复的阻塞所有的任务然后统一执行
	        	// Point 1处是一个while循环，任务是重复执行的，所以需要一个终止条件
	          if(horse.getStrides() >= FINISH_LINE) {
	        	  System.out.println(horse + "won!");
	            exec.shutdownNow();
	            return;
	          }
	        try {
	          TimeUnit.MILLISECONDS.sleep(pause);
	        } catch(InterruptedException e) {
	        	System.out.println("barrier-action sleep interrupted");
	        }
	      }
	    });
	    for(int i = 0; i < nHorses; i++) {
	      Horse horse = new Horse(barrier);
	      horses.add(horse);
	      exec.execute(horse);
	    }
	  }
	  public static void main(String[] args) {
	    int nHorses = 7;
	    int pause = 200;
	    if(args.length > 0) { // Optional argument
	      int n = new Integer(args[0]);
	      nHorses = n > 0 ? n : nHorses;
	    }
	    if(args.length > 1) { // Optional argument
	      int p = new Integer(args[1]);
	      pause = p > -1 ? p : pause;
	    }
	    new HorseRaceByCyclicBarrier(nHorses, pause);
	  }
	} /* (Execute to see output) *///:~

class Horse implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private int strides = 0;
  private static Random rand = new Random(47);
  private static CyclicBarrier barrier;
  public Horse(CyclicBarrier b) { barrier = b; }
  public synchronized int getStrides() { return strides; }
  public void run() {
	  // Point 1：正在运行一个线程
    try {
    	// FIXME: 线程：不会有错失信号的问题么？
      while(!Thread.interrupted()) {
        synchronized(this) {
        	// 理解这个程序最关键的地方在这里！
        	// 这就是不同的马儿跑的不一样快的原因：因为每一次马儿跑的远近是随机的
        	// 这里的马儿指的是多个并行任务
        	// CyclicBarrier的存在确保了所有的马儿都在栅栏处列队，因此可以一致向前移动
        	// 而又因为CyclicBarrier并不是CountDownLatch所以不会执行一次马儿就停下来
        	// 但是具体移动多少就看随出来多大的值了，但是总会有一个“跑得最快”的
        	// 也总有一匹马儿先到达终点的，所以停下来的条件就是上面代码中的
        	// horse.getStrides() >= FINISH_LINE
          strides += rand.nextInt(3); // Produces 0, 1 or 2
        }
        // Point 2：到达栅栏处，等待其它任务到达
        barrier.await();
      }
    } catch(InterruptedException e) {
      // A legitimate way to exit
    } catch(BrokenBarrierException e) {
      // This one we want to know about
      throw new RuntimeException(e);
    }
    // Point 4：所有任务到达栅栏处，继续执行各自的任务
  }
  public String toString() { return "Horse " + id + " "; }
  public String tracks() {
    StringBuilder s = new StringBuilder();
    for(int i = 0; i < getStrides(); i++)
      s.append("*");
    s.append(id);
    return s.toString();
  }
}

