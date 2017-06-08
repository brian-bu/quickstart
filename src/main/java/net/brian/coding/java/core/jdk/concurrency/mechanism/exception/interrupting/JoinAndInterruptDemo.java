package net.brian.coding.java.core.jdk.concurrency.mechanism.exception.interrupting;

//: concurrency/Joining.java
import static java.lang.System.out;

class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	public void run() {
		try {
			// ����Ҫ������ʱ�����õ��㹻�󣬾���Ϊ�˸�������������߳�B�㹻��ʱ�䴴���߳�
			// �����߳�B��û�������߳�A�ͽ����ˣ��Ǿ��޷�����join�����������ˡ�
			sleep(duration);
		} catch (InterruptedException e) {
			out.println(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
			return;
		}
		out.println(getName() + " has awakened");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			out.println("Interrupted");
		}
		// ���ݿ���̨��������Կ����������߳�A�����ѻ��Ǳ���ϣ��߳�B����䶼��ִ�У����Һ����߳�A
		// Ҳ����˵join�����þ��ǣ��߳�B����ҲҪrun�����������ڵ������߳�A.join֮��
		// �൱�ڡ����롱���߳�A�������ȵȴ�Aִ�����˻򱻴���˳�֮����ִ��
		out.println(getName() + " join completed");
	}
}

public class JoinAndInterruptDemo {
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500), grumpy = new Sleeper("Grumpy", 1500);
		@SuppressWarnings("unused")
		Joiner dopey = new Joiner("Dopey", sleepy), doc = new Joiner("Doc", grumpy);
		// interrupt�������debug������Ҫ���߳������㹻����ʱ�䣬��������߳��Ѿ������Ѳ����������ʱ���ٵ���interrupt������û��Ч����
		// �������interrupt�ɹ������Ӧ���߳̾ͻ��ߵ�catch������ִ����Ӧ�Ĵ��룬����ͻ�������ʱ����������ʣ�µ�����
		grumpy.interrupt();
	}
}