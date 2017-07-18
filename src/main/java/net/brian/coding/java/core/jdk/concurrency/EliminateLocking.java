package net.brian.coding.java.core.jdk.concurrency;
/**
 * 
 * ��������������������-serverģʽ�½���
 * ����ʹ�õ�JVM�������£�
 * �ر�����������
 * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 * ��������createStringBuffer: 1993 ms
 * ������������
 * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 * ��������createStringBuffer: 1673 ms
 * ����ƫ��������������Ļ�ȡ�����ܽϺã������ʹ��ƫ������������������������
 * �ر�����������������ƫ������
 * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks -Xcomp -XX:-BackgroundCompilation
 * ��������createStringBuffer: 3302 ms
 * ������������������ƫ������
 * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp -XX:-BackgroundCompilation
 * ��������createStringBuffer: 1726 ms
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
