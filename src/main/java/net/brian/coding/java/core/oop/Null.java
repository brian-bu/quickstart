package net.brian.coding.java.core.oop;

public class Null {

	public static void haha() {
		System.out.println("haha");
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// null���Ա�����Ϊ�κ����ͣ�(String)nullҲ�ǺϷ��ģ������κ�null���͵Ķ���ֻ�ܷ��ʾ�̬����������ʵ�������ǲ��ܷ��ʵ�
		((Null) null).haha();
	}

}