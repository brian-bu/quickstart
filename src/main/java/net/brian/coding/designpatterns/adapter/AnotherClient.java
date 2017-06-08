package net.brian.coding.designpatterns.adapter;

// �������ֱ࣬�ӹ����������࣬ͬʱʵ�ֱ�׼�ӿ�  
class AnotherAdapter implements Target {
	// ֱ�ӹ�����������
	private Adaptee adaptee;

	// ����ͨ�����캯�����������Ҫ����ı����������
	public AnotherAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	public void request() {
		// ������ʹ��ί�еķ�ʽ������⹦��
		this.adaptee.specificRequest();
	}
}

// ������
public class AnotherClient {
	public static void main(String[] args) {
		// ʹ����ͨ������
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();

		// ʹ�����⹦���࣬�������࣬
		// ��Ҫ�ȴ���һ����������Ķ�����Ϊ����
		Target adapter = new AnotherAdapter(new Adaptee());
		adapter.request();
	}
}