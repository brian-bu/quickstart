package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BrokenSerializationDemo {
	// Ϊ�˱�����⣬���Թر���������ɾ���ļ���������������ʱǧ��Ҫ����
	// Exceptionֱ���׳�
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Write Obj to file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile"));
		oos.writeObject(BrokenSingleton.getSingleton());
		// Read Obj from file
		File file = new File("src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		// ͨ����Singleton�����л��뷴���л��õ��Ķ�����һ���µĶ�������ƻ���Singleton�ĵ�����
		BrokenSingleton newInstance = (BrokenSingleton) ois.readObject();
		// �ж��Ƿ���ͬһ������
		System.out.println(newInstance == BrokenSingleton.getSingleton());//false
	}
}
