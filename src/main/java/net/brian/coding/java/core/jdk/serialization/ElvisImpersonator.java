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
 * Effective Java提供的针对序列化破坏单利模式这个问题的解决
 * a.readResolve方法的作用：对于一个正在被反序列化的对象，如果他的类定义了一个readResolve方法且具备正确的声明
 * 则反序列化之后，新建对象上的readResolve方法就会被调用。然后该方法返回的对象引用将被返回取代新建的对象
 * b.使用枚举类型单例代替覆盖readResolve方法，就可以完美的解决这个问题了
 * 
 * 实现readResolve方法的不足：
 * a.本例却示范了一种利用readResolve方法进行攻击的示例
 * b.readResolve的另一个缺点就是：依赖该方法进行实例控制，带有对象引用类型的所有实例域都必须声明为transient的
 * 否则在readResolve被调用之前就可以进行如下所示的攻击，readResolve方法将不起作用:
 * @sse net.brian.coding.java.core.jdk.serialization.MutablePeriod
 * 
 * 枚举类型也有短板：必须编写可序列化的实例受控的类，它的实例在编译时还不知道，你就无法将类表示成一个枚举类型
 * 
 * 总而言之，应该尽量使用枚举类型，否则就必须提供一个readResolve方法并确保该类的所有实例域都为基本类型或transient的
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
	 * 与下面这个反序列化方法相同
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
 * 恶意攻击者代码示例
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
 * 有问题的单例模式
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