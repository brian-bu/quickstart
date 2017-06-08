package net.brian.coding.java.core.jdk.keywords;
/**
 * 
 * �Դ���Ϊ������super��this�ؼ���
 * 
 * super������ʽ���ø���Ĺ�������this������ʽ���ñ�����һ�����ع�����
 * super��������return super;this��������return this;
 * �����԰�super���ɱ���ʹ�ã����ǿ����������α����ͷ����Ա�ʾ��Щ�����ͷ������Ը���
 * ���⻹����ͨ�������������ʸ���������
 *
 */
public class SuperAndThisDemo {
	public static void main(String[] args) {
		new SubClassForFinal("Brian", 12345, 27);
	}
}

class SubClassForFinal extends ParentForFinal {
	int age;

	public SubClassForFinal(String name, int id, int age) {
		// ��ʽ�ĵ��ø��๹������ǰ���ǲ�����д����ע��������κδ����
		super(name, id);
		System.out.println("SubClassForFinal(String name, int id, int age)...");
		// �����������ֻ�ܵ��������
		// System.out.println("super.name:: " + ParentForFinal.name);
		// ���ﲻ���б������super�������Ե���������������Ե���ʵ��������
		System.out.println("super.name:: " + super.name);
		this.age = age;
	}
}
class ParentForFinal {
	String name;
	int id;
	public ParentForFinal(String name, int age) {
		// ��ʽ�ĵ��ñ�����һ�����ع�������ǰ���ǲ�����д����ע��������κδ����
		this();
		System.out.println("ParentForFinal(String name, int age)...");
		this.name = name;
		this.id = age;
	}
	public ParentForFinal() {
		System.out.println("ParentForFinal()...");
	}
}