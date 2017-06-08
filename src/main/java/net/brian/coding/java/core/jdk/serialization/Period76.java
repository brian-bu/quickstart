package net.brian.coding.java.core.jdk.serialization;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item76: Write readObject methods defensively
 * 
 * 演示了利用伪造字节流构造对象进行非法攻击的两个示例：
 *
 */
@SuppressWarnings("serial")
public final class Period76 implements Serializable {

	private Date start;
	private Date end;

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
	public Period76(Date start, Date end) {
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

	/**
	 * 示例一：对于默认序列化，也即没有覆盖readObject的类，通过伪造字节流进行的攻击 
	 * readObject method with validity checking - Page 304 
	 * This will defend against BogusPeriod attack but not MutablePeriod.
	 * 攻击的细节见：
	 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
	 * 
	 * 本例示范的readObject方法仅能避免示例一这种形式的攻击，它的问题在于：
	 * 仅顾及了约束条件没有被破坏，没有考虑对内部组件的改动的可能性
	 * 我们不能将安全性托付给Period类的不可变性，因为即使Period不可变，只要对外提供了对象可变的引用，比如Date
	 * 那么就应该对这类不可变对象内部的可变引用提供足够的保护性拷贝，readObject2就修复了这个问题
	 * 
	 * @param s
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	private void readObject1(ObjectInputStream s) throws IOException, ClassNotFoundException {
		// 首先调用defaulReadObject
		s.defaultReadObject();
		// 然后检查被反序列化之后的对象的有效性，失败直接抛InvalidObjectException异常
		// TODO: 序列化：除了本例之外，还有哪些方法检查被反序列化之后的对象的有效性？
		// Check that our invariants are satisfied
		if (start.compareTo(end) > 0)
			throw new InvalidObjectException(start + " after " + end);
	}

	/**
	 * 示例二：攻击者从字节流ObjecInputStream中读取一个有效的Period实例作为开头，附加两个额外的引用
	 * 指向Period实例中两个私有的Date域，这些对象引用能够访问到Period对象内部的私有Date域所引用的对象
	 * 通过改变这些Date实例，攻击者可以改变对象实例，攻击的细节见：
	 * @see net.brian.coding.java.core.jdk.serialization.MutablePeriod
	 * 
	 * 为避免这个问题，需要对这类不可变对象内部的可变引用提供足够的保护性拷贝
	 * 
	 * 本例示范的readObject的实现方法可以有效避免示例一和二的攻击
	 * 
	 * readObject method with defensive copying and validity checking - Page 306
	 * This will defend against BogusPeriod and MutablePeriod attacks.
	 * 
	 * @param s
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	private void readObject2(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();

		// Defensively copy our mutable components
		start = new Date(start.getTime());
		end = new Date(end.getTime());

		// Check that our invariants are satisfied
		if (start.compareTo(end) > 0)
			throw new InvalidObjectException(start + " after " + end);
	}
}