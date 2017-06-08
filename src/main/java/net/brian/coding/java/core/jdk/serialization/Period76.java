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
 * ��ʾ������α���ֽ������������зǷ�����������ʾ����
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
	 * ʾ��һ������Ĭ�����л���Ҳ��û�и���readObject���࣬ͨ��α���ֽ������еĹ��� 
	 * readObject method with validity checking - Page 304 
	 * This will defend against BogusPeriod attack but not MutablePeriod.
	 * ������ϸ�ڼ���
	 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
	 * 
	 * ����ʾ����readObject�������ܱ���ʾ��һ������ʽ�Ĺ����������������ڣ�
	 * ���˼���Լ������û�б��ƻ���û�п��Ƕ��ڲ�����ĸĶ��Ŀ�����
	 * ���ǲ��ܽ���ȫ���и���Period��Ĳ��ɱ��ԣ���Ϊ��ʹPeriod���ɱ䣬ֻҪ�����ṩ�˶���ɱ�����ã�����Date
	 * ��ô��Ӧ�ö����಻�ɱ�����ڲ��Ŀɱ������ṩ�㹻�ı����Կ�����readObject2���޸����������
	 * 
	 * @param s
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	private void readObject1(ObjectInputStream s) throws IOException, ClassNotFoundException {
		// ���ȵ���defaulReadObject
		s.defaultReadObject();
		// Ȼ���鱻�����л�֮��Ķ������Ч�ԣ�ʧ��ֱ����InvalidObjectException�쳣
		// TODO: ���л������˱���֮�⣬������Щ������鱻�����л�֮��Ķ������Ч�ԣ�
		// Check that our invariants are satisfied
		if (start.compareTo(end) > 0)
			throw new InvalidObjectException(start + " after " + end);
	}

	/**
	 * ʾ�����������ߴ��ֽ���ObjecInputStream�ж�ȡһ����Ч��Periodʵ����Ϊ��ͷ�������������������
	 * ָ��Periodʵ��������˽�е�Date����Щ���������ܹ����ʵ�Period�����ڲ���˽��Date�������õĶ���
	 * ͨ���ı���ЩDateʵ���������߿��Ըı����ʵ����������ϸ�ڼ���
	 * @see net.brian.coding.java.core.jdk.serialization.MutablePeriod
	 * 
	 * Ϊ����������⣬��Ҫ�����಻�ɱ�����ڲ��Ŀɱ������ṩ�㹻�ı����Կ���
	 * 
	 * ����ʾ����readObject��ʵ�ַ���������Ч����ʾ��һ�Ͷ��Ĺ���
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