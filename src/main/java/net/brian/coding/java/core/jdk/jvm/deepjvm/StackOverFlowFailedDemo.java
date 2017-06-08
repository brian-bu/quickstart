package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class StackOverFlowFailedDemo {
	private StackOverFlowFailedDemo() {
		mockConstructorFailed();
	}

	private static final StackOverFlowFailedDemo singleInstance = new StackOverFlowFailedDemo();

	public static StackOverFlowFailedDemo getSingleInstance() {
		return singleInstance;
	}

	public StackOverFlowFailedDemo mockConstructorFailed() {
		// ���ﲻ����StackOverFlow����Ϊ����ֻ����ôһ��ʵ���������ڴ�������һ�����󣬲������������໥����
		// FIXME: ��ʼ�������ﷵ�ص���false��Ϊʲô�أ�����˵���캯�����ص��ǵ�ǰ���������ô��this����Ҳ�ǵ�ǰ���������ô��Ϊʲô����ȣ�
		 System.out.println("this == new StackOverFlowDemo():: " + (this == singleInstance));
		return this;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StackOverFlowFailedDemo sofd = new StackOverFlowFailedDemo();
	}
}
