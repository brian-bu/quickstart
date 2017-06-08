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
 * ���л�������Ҫ�أ�
 * a.��Ӧ����һ�������Ĺ��������βξ�����Χ��
 * b.���������ֻ�����Ĳ����и������ݣ�����Ҫ�κ�һ���Լ��򱣻��Կ���
 * c.���л������Ĭ�����л���ʽ����Χ����õ����л���ʽ����Χ�༰�����д�����������ʵ��Serializable�ӿ�
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

	// Ƕ����ʵ�����л����������л�����������л�ʵ��
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
		 * ����ڴ������л������ṩһ��readResolve����������һ���߼����൱����Χ��ʵ��
		 * ��������ĳ��ֵ������л�ϵͳ�ڷ����л�ʱ�����л�����ת�����Χ���ʵ��
		 * readResolve method for Period.SerializationProxy - Page 313
		 * ����readResolve����ϸ���ۼ���
		 * @see net.brian.coding.java.core.jdk.serialization.ElvisImpersonator
		 */
		private Object readResolve() {
			return new Period78(start, end); // Uses public constructor
		}
	}
	/** 
	 * �÷��������л�֮ǰ����Χ���ʵ��ת������������л���������������������л�ϵͳ��Զ���������Χ������л�ʵ��
	 * ��ʱΪԤ��������α�죬��������Χ�������readObject����:
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