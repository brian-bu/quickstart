package net.brian.coding.designpatterns.singleton;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item03: Enforce the singleton property with a private constructor or an enum type
 * 
 * ����ʾ���˸��ֵ���ģʽ�ı��壬���ж���ö��ʵ�ֵĵ����������������ǵ�Ԫ�ص�ö��
 *
 */
public enum Singleton {
	LAZYLOADING(1), HUANGREYLOADING(2), ENUMLOADING(3), STATICINNERCLASS(4), VOLATILELOCK(5);

	Singleton(int type) {
		switch (type) {
		case 1:
			Singleton1.getInstance();
		case 2:
			Singleton3.getInstance();
		case 3:
			Singleton5.whateverMethod();
		case 4:
			Singleton4.getInstance();
		case 5:
			Singleton6.getSingleton();
		}
	}
}

enum B {
	// ����������������ͺ͹������е��������ͱ��뱣��һ�¡�
	VALUE_B1("B1"), VALUE_B2("B2");

	private String id;

	private B(String id) {

		this.id = id;

	}

	public static B getById(String id) {
		// ���԰�ʵ������������ʹ���������¶���
		return VALUE_B1;
	}

}

// ����ģʽ���ӳټ��أ��̲߳���ȫ��
class Singleton1 {
	private static Singleton1 instance;

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}

// ����ģʽ���ӳټ��أ��̰߳�ȫ����JDK1.5֮����Ҫ˫��У�����ķ�������ʵ�ֵ�����
class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {
	}

	public static synchronized Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}

// ����ģʽ�������������ģʽ���̰߳�ȫ���⣬����ĳ�ʼ��ʱ��ʵ������
class Singleton3 {
	private static Singleton3 instance = new Singleton3();

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		return instance;
	}
}

// ��̬�ڲ��෽ʽ��ֻ����ʾͨ������getInstance����ʱ���Ż���ʾװ��SingletonHolder�࣬�Ӷ�ʵ����instance
class Singleton4 {
	private static class SingletonHolder {
		private static final Singleton4 INSTANCE = new Singleton4();
	}

	private Singleton4() {
	}

	public static final Singleton4 getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

// ö�ٷ�ʽ��Effective Java�Ƽ�ʹ�ã��������ܱ�����߳�ͬ�����⣬���һ��ܷ�ֹ�����л����´����µĶ���
enum Singleton5 {
	INSTANCE;
	public static Singleton5 whateverMethod() {
		return INSTANCE;
	}
}

// ˫��У������ʽ
class Singleton6 {
	private volatile static Singleton6 singleton;

	private Singleton6() {
	}

	public static Singleton6 getSingleton() {
		if (singleton == null) {
			synchronized (Singleton6.class) {
				if (singleton == null) {
					singleton = new Singleton6();
				}
			}
		}
		return singleton;
	}
}