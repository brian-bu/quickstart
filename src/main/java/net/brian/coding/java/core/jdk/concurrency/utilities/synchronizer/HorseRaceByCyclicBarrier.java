package net.brian.coding.java.core.jdk.concurrency.utilities.synchronizer;

//: concurrency/HorseRace.java
import java.util.concurrent.*;
import java.util.*;

/**
 * 
 * CyclicBarrier�������������������ϣ������һ���������ǲ���ִ�й���
 * Ȼ���ڽ�����һ������֮ǰ�ȴ���ֱ������������ɣ��������е���join������join���ʺ���Ӧ�����������
 * ��ʹ�����в������񶼽���դ�����жӣ���˿���һ����ǰ�ƶ����������CountDownLatch
 * ����CountDownLatch������
 * CountDownLatch��ֻ����һ�ε��¼���CyclicBarrier���Զ������
 *
 */

public class HorseRaceByCyclicBarrier {
	  static final int FINISH_LINE = 75;
	  private List<Horse> horses = new ArrayList<Horse>();
	  private ExecutorService exec =
	    Executors.newCachedThreadPool();
	  private CyclicBarrier barrier;
	  public HorseRaceByCyclicBarrier(int nHorses, final int pause) {
		  // ������Ҫ�õ�CyclicBarrier�ĵط���������������������þ���
		  // ����һ������ΪnHorses�����񣬵ڶ���������һ���߳������࣬Ҳ��������������ģ��
	    barrier = new CyclicBarrier(nHorses, new Runnable() {
	    	 // Point 3���������񵽴�դ���������Խ��ж���Ĵ���
	    	public void run() {
	        StringBuilder s = new StringBuilder();
	        for(int i = 0; i < FINISH_LINE; i++)
	          s.append("="); // The fence on the racetrack
	        System.out.println(s);
	        for(Horse horse : horses)
	        	System.out.println(horse.tracks());
	        for(Horse horse : horses)
	        	// դ�����ص���ǿ��ظ����������е�����Ȼ��ͳһִ��
	        	// Point 1����һ��whileѭ�����������ظ�ִ�еģ�������Ҫһ����ֹ����
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
	  // Point 1����������һ���߳�
    try {
    	// FIXME: �̣߳������д�ʧ�źŵ�����ô��
      while(!Thread.interrupted()) {
        synchronized(this) {
        	// ������������ؼ��ĵط������
        	// ����ǲ�ͬ������ܵĲ�һ�����ԭ����Ϊÿһ������ܵ�Զ���������
        	// ��������ָ���Ƕ����������
        	// CyclicBarrier�Ĵ���ȷ�������е��������դ�����жӣ���˿���һ����ǰ�ƶ�
        	// ������ΪCyclicBarrier������CountDownLatch���Բ���ִ��һ�������ͣ����
        	// ���Ǿ����ƶ����پͿ����������ֵ�ˣ������ܻ���һ�����ܵ���족��
        	// Ҳ����һƥ����ȵ����յ�ģ�����ͣ����������������������е�
        	// horse.getStrides() >= FINISH_LINE
          strides += rand.nextInt(3); // Produces 0, 1 or 2
        }
        // Point 2������դ�������ȴ��������񵽴�
        barrier.await();
      }
    } catch(InterruptedException e) {
      // A legitimate way to exit
    } catch(BrokenBarrierException e) {
      // This one we want to know about
      throw new RuntimeException(e);
    }
    // Point 4���������񵽴�դ����������ִ�и��Ե�����
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

