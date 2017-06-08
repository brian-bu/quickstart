package net.brian.coding.java.core.jdk.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import net.brian.coding.java.core.jdk.jvm.initialization.Cat;

/**
 * ʵ��Serializable����Ķ���ſ������л���ʵ�����кŵĶ������������������ʽ����
 * ObjectInputStream��ObjectOutputStream��������������չ���ɶ�д����
 * ��ObjectInputStream����readObject()��������ֱ�Ӷ�ȡһ������
 * ObjectOutputStream����writeObject()��������ֱ�ӽ����󱣴浽�������
 *
 * ��Ҫע����ǣ� ������Щ��Ҫ���ܵ��ֶΣ���Ӧ���������ý����� �����߲�Ӧ�򵥵ز��Ӵ���ر�������
 * Ϊ�˱�֤��ȫ�ԣ�Ӧ������Щ�ֶ�ǰ����transient�ؼ��֡� ��������ǰ���ϸùؼ��֣�������ֹ�ñ��������л����ļ���
 * �ڱ������л���transient������ֵ����Ϊ��ʼֵ���� int �͵��� 0�������͵��� null��
 */
public class SerializationDemo {
//	@Test
	public void testSerialization() {
		// ʵ��������
		Cat cat = new Cat("Kitty", 3, 2);
		// ͨ�����������������״̬��������
		try {
			FileOutputStream fo = new FileOutputStream("data.ser");
			ObjectOutputStream so = new ObjectOutputStream(fo);
			so.writeObject(cat);
			so.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		// ���߿�����apache commons�ṩ�Ĺ���
		// SerializationUtils.serialize(cat, so);
	}
	@Test
	public void testDeserialization() {
		Cat cat = null;
		// ͨ������������������״̬�ָ�
		try {
			FileInputStream fi = new FileInputStream("data.ser");
			ObjectInputStream si = new ObjectInputStream(fi);
			cat = (Cat) si.readObject();
			si.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
		// ���߿�����apache commons�ṩ�Ĺ���
		// SerializationUtils.deserialize(cat, si);
		System.out.println("Cat Info:");
		System.out.println("Name: " + cat.name + "\nAge: " + "\nEyes: " + Cat.eyeNum);
	}
}
