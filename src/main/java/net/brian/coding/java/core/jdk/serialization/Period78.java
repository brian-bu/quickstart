package net.brian.coding.java.core.jdk.serialization;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item78: Consider serialization proxies instead of serialized instances
 * 
 * 序列化代理三要素：
 * a.它应该有一个单独的构造器，形参就是外围类
 * b.这个构造器只从他的参数中复制数据，不需要任何一致性检查或保护性拷贝
 * c.序列化代理的默认序列化形式是外围类最好的序列化形式，外围类及其序列代理都必须声明实现Serializable接口
 *
 */
@SuppressWarnings("serial")
public final class Period78 implements Serializable {
	private final Date start;
	private final Date end;

	/**
	 * @param start
	 *            the beginning of the period
	 * @param end
	 *            the end of the period; must not precede start
	 * @throws IllegalArgumentException
	 *             if start is after end
	 * @throws NullPointerException
	 *             if start or end is null
	 */
	public Period78(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		if (this.start.compareTo(this.end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
	}

	public Date start() {
		return new Date(start.getTime());
	}

	public Date end() {
		return new Date(end.getTime());
	}

	public String toString() {
		return start + " - " + end;
	}

	// 嵌套类实现序列化代理，用序列化代理代替序列化实例
	// Serialization proxy for Period class - page 312
	private static class SerializationProxy implements Serializable {
		private final Date start;
		private final Date end;

		SerializationProxy(Period78 p) {
			this.start = p.start;
			this.end = p.end;
		}

		private static final long serialVersionUID = 234098243823485285L; // Any
																			// number
																			// will
																			// do
																			// (Item
																			// 75)
		/**
		 * 最后在代理序列化类中提供一个readResolve方法来返回一个逻辑上相当的外围类实例
		 * 这个方法的出现导致序列化系统在反序列化时将序列化代理转变回外围类的实例
		 * readResolve method for Period.SerializationProxy - Page 313
		 * 关于readResolve的详细讨论见：
		 * @see net.brian.coding.java.core.jdk.serialization.ElvisImpersonator
		 */
		private Object readResolve() {
			return new Period78(start, end); // Uses public constructor
		}
	}
	/** 
	 * 该方法在序列化之前将外围类的实例转变成了他的序列化代理，有了这个方法，序列化系统永远不会产生外围类的序列化实例
	 * 此时为预防攻击者伪造，可以在外围类中添加readObject方法:
	 * @see net.brian.coding.java.core.jdk.serialization.Period78.readObject(ObjectInputStream)
	 * writeReplace method for the serialization proxy pattern - page 312
	 * 
	 */
	private Object writeReplace() {
		return new SerializationProxy(this);
	}

	// readObject method for the serialization proxy pattern - Page 313
	private void readObject(ObjectInputStream stream)
			throws InvalidObjectException {
		throw new InvalidObjectException("Proxy required");
	}
}