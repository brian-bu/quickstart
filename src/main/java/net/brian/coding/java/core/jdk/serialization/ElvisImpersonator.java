package net.brian.coding.java.core.jdk.serialization;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item77: For instance control, prefer enum types to readResolve
 * 
 * Effective Java�ṩ��������л��ƻ�����ģʽ�������Ľ��
 * a.readResolve���������ã�����һ�����ڱ������л��Ķ�����������ඨ����һ��readResolve�����Ҿ߱���ȷ������
 * �����л�֮���½������ϵ�readResolve�����ͻᱻ���á�Ȼ��÷������صĶ������ý�������ȡ���½��Ķ���
 * b.ʹ��ö�����͵������渲��readResolve�������Ϳ��������Ľ�����������
 * 
 * ʵ��readResolve�����Ĳ��㣺
 * a.����ȴʾ����һ������readResolve�������й�����ʾ��
 * b.readResolve����һ��ȱ����ǣ������÷�������ʵ�����ƣ����ж����������͵�����ʵ���򶼱�������Ϊtransient��
 * ������readResolve������֮ǰ�Ϳ��Խ���������ʾ�Ĺ�����readResolve��������������:
 * @sse net.brian.coding.java.core.jdk.serialization.MutablePeriod
 * 
 * ö������Ҳ�ж̰壺�����д�����л���ʵ���ܿص��࣬����ʵ���ڱ���ʱ����֪��������޷������ʾ��һ��ö������
 * 
 * �ܶ���֮��Ӧ�þ���ʹ��ö�����ͣ�����ͱ����ṩһ��readResolve������ȷ�����������ʵ����Ϊ�������ͻ�transient��
 *
 */
public class ElvisImpersonator {

	// Byte stream could not have come from real Elvis instance!
	private static final byte[] serializedForm = new byte[] { (byte) 0xac, (byte) 0xed, 0x00, 0x05, 0x73, 0x72, 0x00,
			0x05, 0x45, 0x6c, 0x76, 0x69, 0x73, (byte) 0x84, (byte) 0xe6, (byte) 0x93, 0x33, (byte) 0xc3, (byte) 0xf4,
			(byte) 0x8b, 0x32, 0x02, 0x00, 0x01, 0x4c, 0x00, 0x0d, 0x66, 0x61, 0x76, 0x6f, 0x72, 0x69, 0x74, 0x65, 0x53,
			0x6f, 0x6e, 0x67, 0x73, 0x74, 0x00, 0x12, 0x4c, 0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f,
			0x4f, 0x62, 0x6a, 0x65, 0x63, 0x74, 0x3b, 0x78, 0x70, 0x73, 0x72, 0x00, 0x0c, 0x45, 0x6c, 0x76, 0x69, 0x73,
			0x53, 0x74, 0x65, 0x61, 0x6c, 0x65, 0x72, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x01,
			0x4c, 0x00, 0x07, 0x70, 0x61, 0x79, 0x6c, 0x6f, 0x61, 0x64, 0x74, 0x00, 0x07, 0x4c, 0x45, 0x6c, 0x76, 0x69,
			0x73, 0x3b, 0x78, 0x70, 0x71, 0x00, 0x7e, 0x00, 0x02 };

	public static void main(String[] args) {
		// Initializes ElvisStealer.impersonator and returns
		// the real Elvis (which is Elvis.INSTANCE)
		Elvis elvis = (Elvis) deserialize(serializedForm);
		Elvis impersonator = ElvisStealer.impersonator;

		elvis.printFavorites();
		impersonator.printFavorites();
	}

	/** 
	 * Returns the object with the specified serialized form
	 * ��������������л�������ͬ
	 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod.deserialize(byte[])
	 * 
	 * @param sf
	 * @return
	 */
	private static Object deserialize(byte[] sf) {
		try {
			InputStream is = new ByteArrayInputStream(sf);
			ObjectInputStream ois = new ObjectInputStream(is);
			return ois.readObject();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
/**
 * 
 * ���⹥���ߴ���ʾ��
 *
 */
class ElvisStealer implements Serializable {
	static Elvis impersonator;
	private Elvis payload;

	private Object readResolve() {
		// Save a reference to the "unresolved" Elvis instance
		impersonator = payload;

		// Return an object of correct type for favorites field
		return new String[] { "A Fool Such as I" };
	}

	private static final long serialVersionUID = 0;
}
/**
 * 
 * ������ĵ���ģʽ
 *
 */
@SuppressWarnings("serial")
class Elvis implements Serializable {
	public static final Elvis INSTANCE = new Elvis();

	private Elvis() {
	}

	private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };

	public void printFavorites() {
		System.out.println(Arrays.toString(favoriteSongs));
	}

	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}
}