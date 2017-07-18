package net.brian.coding.java.core.jdk.concurrency;
/**
 * 
 * 锁消除：锁消除必须在-server模式下进行
 * 本例使用的JVM参数如下：
 * 关闭了锁消除：
 * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 * 输出结果：createStringBuffer: 1993 ms
 * 开启锁消除：
 * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 * 输出结果：createStringBuffer: 1673 ms
 * 这里偏向锁本身简化了锁的获取，性能较好，如果不使用偏向锁，这里的输出结果差距会更大：
 * 关闭了锁消除并不启用偏向锁：
 * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks -Xcomp -XX:-BackgroundCompilation
 * 输出结果：createStringBuffer: 3302 ms
 * 开启锁消除并不启用偏向锁：
 * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp -XX:-BackgroundCompilation
 * 输出结果：createStringBuffer: 1726 ms
 * 
 */
public class EliminateLocking {
	private static final int CIRCLE = 20000000;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < CIRCLE; i++) {
			createStringBuffer("JVM", "Diagnosis");
		}
		long end = System.currentTimeMillis();
		System.out.println("createStringBuffer: " + (end - start) + " ms");
	}
	public static String createStringBuffer(String s1, String s2) {
		StringBuffer sb = new StringBuffer();
		sb.append(s1);
		sb.append(s2);
		return sb.toString();
	}
}
