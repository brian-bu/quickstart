package net.brian.coding.java.core.jdk.exception;

/**
 * 
 * 1. �����Ƿ����쳣��finally���е���䶼��ִ�У�����try��catchģ���Ƿ���return��
 * 2.��try��catchģ����return��䣬��finallyģ������returnʱ
 * try��catchģ���return����ֵ��finallyģִ��ǰȷ���ģ���return�������finallyģ��ִ��
 * ��finallyģ����return������try��catchģ�����Ƿ���return����������մ�finally��return����;
 * ��һ�㲻�Ƽ���finally�����return
 * 3.��finallyģ����return��return�������finallyģ��ִ��
 * try��catchģ���return����ֵ�����е�return�Ļ�����finallyģִ��ǰ���Ѿ�ȷ�����ҵ����������Ǵ�return��䷵�ء�
 * 4.����try-catch-finally���֮���return��䣬finallyģ������returnǰִ�еģ���return����ֵ��finallyģִ�к�ȷ����
 *
 * 
 */
public class FinallyReturnDemo {
	private Object sharedObj = new Object();

	private Object method1() {
		System.out.println("method1()");
		throw new RuntimeException();
		// ����������Ϊ���׳��쳣��д�ķ�������ô����returnֻ��ִ��һ������һ���ͱ����unreachable code��
		// return sharedObj;
	}

	private Object method2() {
		System.out.println("method2()");
		return sharedObj;
	}

	private Object method3() {
		System.out.println("method3()");
		return sharedObj;
	}

	@SuppressWarnings("finally")
	public Object testFinallyReturn() {
		try {
			return method1();
		} catch (RuntimeException rte) {
//			return method2();
		} finally {
//			return method3();
		}
		return null;
	}

	public static void main(String[] args) {
		FinallyReturnDemo demo = new FinallyReturnDemo();
		demo.testFinallyReturn();
	}
}
