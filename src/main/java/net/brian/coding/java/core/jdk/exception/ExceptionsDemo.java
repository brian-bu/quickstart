package net.brian.coding.java.core.jdk.exception;
/**
 * 
 * �쳣�ļ̳нṹ��ThrowableΪ���࣬Error��Exception�̳�Throwable
 * RuntimeException��IOException�ȼ̳�Exception
 * Error��RuntimeException���������Ϊδ����쳣��unchecked���������쳣��Ϊ�Ѽ���쳣��checked��
 *
 */
public class ExceptionsDemo {
	public void triggerAnException() {
		int i = 1;
		int j = 1;
		int k;
		try {
			k = i / j;
			System.out.println("ExceptionsDemo -- triggerAnException() -- k:: " + k);
		} catch (ArithmeticException a) {
			System.out.println("ExceptionsDemo -- triggerAnException() -- ArithmeticException:: " + a.getMessage());
		}
		// This will be executed anyway even if an exception was caught.
		System.out.println("ExceptionsDemo -- triggerAnException() -- Exit...");
	}

	public void throwAnException(boolean ifThrowAnException, String msg) throws Exception {
		if (ifThrowAnException) {
			// It won't work if we don't have throw keyword.
			throw new Exception("ExceptionsDemo -- throwAnException(boolean, String) -- throw an exception:: " + msg);
			// No exception of type Object can be thrown; an exception type must be a subclass of Throwable
			// throw new Object();
		} else {
			System.out.println("We don't need to throw an exception here!");
		}
	}

	public static void main(String[] args) throws Exception {
		ExceptionsDemo demo = new ExceptionsDemo();
		demo.throwAnException(true, "Hello Exception!!!");
	}
}
