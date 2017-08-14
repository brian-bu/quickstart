package net.brian.coding.java.core.jdk.serialization.breakingsingleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FixedSerializationDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile"));
		oos.writeObject(FixedSingleton.getSingleton());
		File file = new File("src/main/java/net/brian/coding/java/core/jdk/serialization/breakingsingleton/TempFile");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		FixedSingleton newInstance = (FixedSingleton) ois.readObject();
		System.out.println(newInstance == FixedSingleton.getSingleton());//true
	}
}
