package net.brian.coding.java.core.jdk.jvm.deepjvm;

public class StackOverFlowSucceedDemo {
	public StackOverFlowSucceedDemo() {
		mockConstructorFailed();
	}
	public StackOverFlowSucceedDemo mockConstructorFailed() {
		// ����ʲôԭ������������͹����������ķ���֮��Ļ�����ö����׳�StackOverFlowError
		// û�����Թ������ĵ�����仰�Ͳ���Error�ˣ�����������൱�ڹ����������ص��ǵ�ǰ������ã�������Ҳ���ص�ǰ������á�
		// ����������������static�ģ�static������������ģ��޷����ص�ǰ�������
		// System.out.println("this == new StackOverFlowDemo():: " + (this == new StackOverFlowSucceedDemo()));
		return this;
	}

	public StackOverFlowSucceedDemo mockConstructorSucceed() {
		// ����ʲôԭ������������͹����������ķ���֮��Ļ�����ö����׳�StackOverFlowError
		return new StackOverFlowSucceedDemo();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StackOverFlowSucceedDemo sofd = new StackOverFlowSucceedDemo();
	}
}
