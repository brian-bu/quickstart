package net.brian.coding.java.core.oop;

/**
 * �������һ����������������ʵ�ο������β��������͵����࣬�����Ƕ�����д����ͬ�Ĵ���
 * ��������ʱ��������ʵ�ʲ����ҵ���Ӧ�����࣬ÿ�������в�ͬ����Ϊ��ͽж�̬
 * 
 * չʾ�˶�̬����¸���������е����Ժͷ���֮��ļ̳й�ϵ������һ���������ͱ������ԣ�
 * ��ͨ���ñ��������������õĶ����ʵ������ʱ���ñ�����ֵȡ���������ñ���ʱ����
 * ��ͨ���ñ����������������õĶ���ķ���ʱ���÷�����Ϊȡ��������ʵ�����õĶ�������
 * 
 * �����򴴽�һ���������ʱ��ϵͳ������Ϊ�����ж����ʵ�����������ڴ�
 * Ҳ��Ϊ�丸���ж��������ʵ�����������ڴ棬��ʹ���ඨ�����븸��ͬ����ʵ������
 * 
 */
public class PolymorphismDemo {

	public void base2Base() {
		// ����������һ��Base����
		Base b = new Base();
		// ֱ�ӷ���countʵ��������ͨ��display����countʵ������
		System.out.println(b.count);
		b.display();
	}

	public void derived2Derived() {
		// ������������һ��Derived����
		Derived d = new Derived();
		// ֱ�ӷ���countʵ��������ͨ��display����countʵ������
		System.out.println(d.count);
		d.display();
	}

	public void base2Derived() {
		// ����һ��Base����������Derived���󸳸��ñ���������������ڶ��в�������Base�Ķ���
		// ����ķ���ջ�б�����һ��Base�����ã�������ֻ����һ��Derived�Ķ���
		// ����������󲻽�������Derived�����ȫ�����Ժͷ�������ͬʱ�����Ÿ��ඨ���ȫ��ʵ������
		// Ҳ����˵��̬�������Ǹ�������ָ��������󷽱��滻��������ʵ����ô�򵥣������Ѹ����ʵ���������˹�ȥ
		// �������������� ͬ����ʵ������������ĻḲ�������
		Base bd = new Derived();
		// ֱ�ӷ���countʵ��������ͨ��display����countʵ������
		System.out.println(bd.count);
		bd.display();
	}

	public void base2Derived2() {
		Derived d = new Derived();
		// ��d2b����ָ��ԭd������ָ���Dervied����
		Base d2b = d;
		// ����d2b��ָ�����countʵ������
		System.out.println(d2b.count);
	}
}
class Base {
	int count = 2;

	public void display() {
		System.out.println(this.count);
	}
}

class Derived extends Base {
	int count = 20;

	@Override
	public void display() {
		System.out.println(this.count);
	}
}