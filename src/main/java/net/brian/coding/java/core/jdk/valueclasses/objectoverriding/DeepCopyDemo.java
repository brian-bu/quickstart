package net.brian.coding.java.core.jdk.valueclasses.objectoverriding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item76: Write readObject methods defensively
 * 
 * 本例针对Effective Java提到的保护性编写readObject方法写了一个实现的demo
 * 
 * 如果让不可变类实现Serializable接口并包含多个指向可变对象的域，就必须提供这个readObject方法
 * 否则攻击者可能从不可变的类创建可变的实例，比如：
 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
 * 
 */
public class DeepCopyDemo {

	/**
	 * (T) ois.readObject();
	 * 解除这句代码的警告信息：Type safety: Unchecked cast from Object to T
	 * 
	 * Object是所有类的父类,只要是Object对象都可以被强制转换成别的对象,包括自定义的对象
	 * @see net.brian.coding.java.core.oop.ClassCastDemo
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepCopy(T obj) {
		
		T clonedObj = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			clonedObj = (T) ois.readObject();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clonedObj;
	}

}
