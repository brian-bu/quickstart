package net.brian.coding.java.core.jdk.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import net.brian.coding.java.core.jdk.jvm.initialization.Cat;

/**
 * 实现Serializable的类的对象才可以序列化，实现序列号的对象可以以数据流的形式传输
 * ObjectInputStream和ObjectOutputStream将数据流功能扩展至可读写对象
 * 在ObjectInputStream中用readObject()方法可以直接读取一个对象
 * ObjectOutputStream中用writeObject()方法可以直接将对象保存到输出流中
 *
 * 需要注意的是： 对于这些需要保密的字段，不应保存在永久介质中 ，或者不应简单地不加处理地保存下来
 * 为了保证安全性，应该在这些字段前加上transient关键字。 变量声明前加上该关键字，可以阻止该变量被序列化到文件中
 * 在被反序列化后，transient变量的值被设为初始值，如 int 型的是 0，对象型的是 null。
 */
public class SerializationDemo {
//	@Test
	public void testSerialization() {
		// 实例化对象
		Cat cat = new Cat("Kitty", 3, 2);
		// 通过对象输出流将对象状态保存下来
		try {
			FileOutputStream fo = new FileOutputStream("data.ser");
			ObjectOutputStream so = new ObjectOutputStream(fo);
			so.writeObject(cat);
			so.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		// 或者可以用apache commons提供的工具
		// SerializationUtils.serialize(cat, so);
	}
	@Test
	public void testDeserialization() {
		Cat cat = null;
		// 通过对象输入流将对象状态恢复
		try {
			FileInputStream fi = new FileInputStream("data.ser");
			ObjectInputStream si = new ObjectInputStream(fi);
			cat = (Cat) si.readObject();
			si.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
		// 或者可以用apache commons提供的工具
		// SerializationUtils.deserialize(cat, si);
		System.out.println("Cat Info:");
		System.out.println("Name: " + cat.name + "\nAge: " + "\nEyes: " + Cat.eyeNum);
	}
}
