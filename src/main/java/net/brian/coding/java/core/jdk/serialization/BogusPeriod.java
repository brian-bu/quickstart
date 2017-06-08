package net.brian.coding.java.core.jdk.serialization;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Date;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item76: Write readObject methods defensively
 * 
 * 保护性地编写readObject方法，本程序可以解决对于序列化的大部分疑惑，也是入门必备的一段程序：
 *
 */
public class BogusPeriod {
    // TODO: 序列化：不能像书上那样还原一个实例，下面的字节码有问题，先弄清楚如何获取正确的字节码
    private static final byte[] serializedForm = new byte[] { (byte) 0xac,
                 ( byte) 0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06, 0x50, 0x65, 0x72,
                 0x69, 0x6f, 0x64, 0x40, 0x7e, ( byte) 0xf8, 0x2b, 0x4f, 0x46,
                 ( byte) 0xc0, (byte ) 0xf4, 0x02, 0x00, 0x02, 0x4c, 0x00, 0x03, 0x65,
                 0x6e, 0x64, 0x74, 0x00, 0x10, 0x4c, 0x6a, 0x61, 0x76, 0x61, 0x2f,
                 0x75, 0x74, 0x69, 0x6c, 0x2f, 0x44, 0x61, 0x74, 0x65, 0x3b, 0x4c,
                 0x00, 0x05, 0x73, 0x74, 0x61, 0x72, 0x74, 0x71, 0x00, 0x7e, 0x00,
                 0x01, 0x78, 0x70, 0x73, 0x72, 0x00, 0x0e, 0x6a, 0x61, 0x76, 0x61,
                 0x2e, 0x75, 0x74, 0x69, 0x6c, 0x2e, 0x44, 0x61, 0x74, 0x65, 0x68,
                 0x6a, ( byte) 0x81, 0x01, 0x4b, 0x59, 0x74, 0x19, 0x03, 0x00, 0x00,
                 0x78, 0x70, 0x77, 0x08, 0x00, 0x00, 0x00, 0x66, ( byte) 0xdf, 0x6e,
                 0x1e, 0x00, 0x78, 0x73, 0x71, 0x00, 0x7e, 0x00, 0x03, 0x77, 0x08,
                 0x00, 0x00, 0x00, ( byte) 0xd5, 0x17, 0x69, 0x22, 0x00, 0x78 };

	public static void main(String[] args) {
		Period p = (Period) deserialize(serializedForm);
		System.out.println(p);
	}

	/** 
	 * Returns the object with the specified serialized form
	 * 与下面这个反序列化方法相同
	 * @see net.brian.coding.java.core.jdk.serialization.ElvisImpersonator.deserialize(byte[])
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

class Period {
	private final Date start;
	private final Date end;

	public Period(Date start, Date end) {
		if (start.compareTo(end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
		this.start = start;
		this.end = end;
	}

	public Date start() {
		return start;
	}

	public Date end() {
		return end;
	}

	public String toString() {
		return start + " - " + end;
	}
}