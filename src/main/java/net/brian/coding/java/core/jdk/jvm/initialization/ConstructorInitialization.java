package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * �������¼̳й�ϵ��Object - ParentForInitialization - SubClassForInitialization
 * 
 * ������ʼ���������£�
 * 1.ִ��Object��Ǿ�̬��ʼ���飨����еĻ���
 * 2.��ʽ����ʽ�ĵ���Object���һ������������ִ�г�ʼ��
 * 3.ִ��ParentForInitialization��Ǿ�̬��ʼ���飨����еĻ���
 * 4.��ʽ����ʽ�ĵ���ParentForInitialization���һ������������ִ�г�ʼ��
 * 5.ִ��SubClassForInitialization��Ǿ�̬��ʼ���飨����еĻ���
 * 6.��ʽ����ʽ�ĵ���SubClassForInitialization���һ������������ִ�г�ʼ��
 *
 */
public class ConstructorInitialization {

	public static void main(String[] args) {
		// ע������������main����ֱ�ӵ��õķ�ʽ
		SubClassForInitialization.main(args);
	}
}

class ParentForInitialization {
	public static final int constantInt = 0;

	static {
		System.out.println("Entering ParentForInitialization static block...");
		// Cannot reference a field before it is defined.
		// System.out.println("Entering ParentForInitialization static block --
		// constantStr:: " + constantStr);
		// The final field ParentForInitialization.constantInt cannot be assigned.
		// constantInt = 1;
		System.out.println("Entering ParentForInitialization static block -- constantInt:: " + constantInt);
	}

	public ParentForInitialization(String str) {
		//FIXME: ��ʼ�������õ��������methodbody����
		// ��Ҫ�ڸ��๹�����е��������������Է��γɵݹ����
		methodbody();
		// But invoking final methods or private methods is okay.
		System.out.println("ParentForInitialization() -- parameter:: " + str);
	}

	public static String constantStr = "ParentForInitialization";

	public void methodbody() {
		System.out.println("ParentForInitialization -- Entering methodbody...");
	}

}

class SubClassForInitialization extends ParentForInitialization {
	public SubClassForInitialization(String str) {
		super(str);
		System.out.println("SubClassForInitialization() -- parameter:: " + str);
	}

	static {
		System.out.println("Entering SubClassForInitialization static block...");
		System.out.println("Entering SubClassForInitialization static block -- constantStr:: " + constantStr);
	}

	@Override
	public void methodbody() {
		System.out.println("SubClassForInitialization -- Entering overriden methodbody...");
	}

	public static void main(String[] args) {
//		 ParentForInitialization demo = new SubClassForInitialization("SubClassForInitialization");
//		 demo.methodbody();

		new SubClassForInitialization("Another SubClassForInitialization").methodbody();
	}
}
