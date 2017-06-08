package net.brian.coding.java.core.jdk.keywords;
/**
 * 
 * instanceof�����ǰ��������ı���ʱ���ͱ���������3�������
 * Ҫô����������ͬ��Ҫô�Ǻ������ĸ��࣬Ҫô�Ǻ����������
 * ���Math instanceof String���֣������鶼����ȥ��ֱ�ӱ��������
 *
 */
public class InstanceofDemo {
	public static void main(String[] args) {
		String s = "Hello";
		Object obj1 = null;
		Object obj2 = obj1;
		Object obj3 = new Object();
		// String����Object��ʵ��Ҳ��String��ʵ�������Կ϶�����true
		System.out.println("s instanceof Object || s instanceof String���� " + (s instanceof Object && s instanceof String));
		// obj3��Ȼֻ����Object��ʵ��������String��ʵ�����������ﷵ��false
		System.out.println("obj3 instanceof String:: " + (obj3 instanceof String));
		// ������ֱ������Ϊnull����ָ����һ��Ϊnull�Ķ������յ����Ͷ���null����null�ǻ�û�����͵Ķ���
		// ����null instanceof�κ����͵Ķ������Ƿ���false
		System.out.println("obj1 instanceof Object || obj2 instanceof Object:: " + (obj1 instanceof Object || obj2 instanceof Object));
		// �����ʱ��Ϳ��Լ���String��Math֮�䲻����instanceof��ʹ��������������ᱨ�������
	//	System.out.println("s instanceof Math:: " + (s instanceof Math));
	}
}
