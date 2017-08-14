package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BrokenSerializationDemo {
	// 为了便于理解，忽略关闭流操作及删除文件操作。真正编码时千万不要忘记
	// Exception直接抛出
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Write Obj to file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile"));
		oos.writeObject(BrokenSingleton.getSingleton());
		// Read Obj from file
		File file = new File("src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		// 通过对Singleton的序列化与反序列化得到的对象是一个新的对象，这就破坏了Singleton的单例性
		BrokenSingleton newInstance = (BrokenSingleton) ois.readObject();
		// 判断是否是同一个对象
		System.out.println(newInstance == BrokenSingleton.getSingleton());//false
	}
}
