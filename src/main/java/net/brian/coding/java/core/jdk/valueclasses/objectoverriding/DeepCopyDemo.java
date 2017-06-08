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
 * �������Effective Java�ᵽ�ı����Ա�дreadObject����д��һ��ʵ�ֵ�demo
 * 
 * ����ò��ɱ���ʵ��Serializable�ӿڲ��������ָ��ɱ������򣬾ͱ����ṩ���readObject����
 * ���򹥻��߿��ܴӲ��ɱ���ഴ���ɱ��ʵ�������磺
 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
 * 
 */
public class DeepCopyDemo {

	/**
	 * (T) ois.readObject();
	 * ���������ľ�����Ϣ��Type safety: Unchecked cast from Object to T
	 * 
	 * Object��������ĸ���,ֻҪ��Object���󶼿��Ա�ǿ��ת���ɱ�Ķ���,�����Զ���Ķ���
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
