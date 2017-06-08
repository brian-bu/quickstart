package net.brian.coding.java.core.jdk.valueclasses.objectoverriding;

import java.util.Date;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item39: Make defensive copies when needed
 * 
 * �Կɱ�������߿ɱ��������ý��б����Կ��������������������Ҫ���б����Կ�����
 * a.���ɱ����еĿɱ��򣬱���Date����ǰ���ʾ���������������������ʾ��
 * b.��д�������߹�����ʱ����ͻ��ṩ�Ķ�������ڲ����ݽṹ
 * ��ʱ����޷����̸ö���������ݽṹ��ı仯����Ҫ�ṩ�Ըö���ı����Կ�����ʹ��������������ڲ����ݽṹ
 * ���磺��ĳ������ɿͻ��ṩ�Ķ����������Ϊ�ڲ�Setʵ����Ԫ��
 * ������Ϊ�ڲ�Mapʵ���ļ���������������ڲ�����ٱ��޸ģ�Set��Map��Լ�������ͻ��⵽�ƻ�
 * c.�ڲ���������ظ��ͻ���֮ǰӦ�ý��б����Կ��������糤�ȷ����final���飬�������
 * @see net.brian.coding.java.core.jdk.keywords.FinalDemo.FinalClass.myClassArr
 * 
 */
public final class DefensiveCopiesDemo {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Point 1: First attack - start
		Date start = new Date();
		Date end = new Date();
		DefensiveCopiesDemo period = new DefensiveCopiesDemo(start, end);
		// ��ȻPeriod�Ѿ���final���ˣ��������ڲ�����Ҳ��Ӧ����final�ģ�������ΪDate�����ǿ��Ե���API�ı��
		// �������ƻ���Period�����ķ�װ�ԣ�ʹ��periodʵ���Ծ��ǿ��Ըı��
		end.setYear(1990);
		// First attack - end
		// Point 2: Second attack - start
		period.end().setYear(1990);
		// Second attack - end
	}
	
	private final Date start;
	private final Date end;

	public DefensiveCopiesDemo(Date start, Date end) {
		if (start.compareTo(end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
		this.start = start;
		this.end = end;
	}

	// Repaired constructor - makes defensive copies of parameters - Page 185
	// Point 1: Stops first attack
	// �������������֮��ͨ��Date��API��Date�����޸Ĳ�����Ч
	// ������new Date������Ϊ���ݶ����ΪPeriodʵ���������������ʹ��ԭʼ����
	// ͬʱע�����ǲ�û����clone���б����Կ�������ʵ��Ҳ������������
	// Date�Ƿ�final�ģ����ܱ�֤clone����һ������ר�ų��ڶ����Ŀ�Ķ���ƵĲ������ε�����ʵ��
	// public Period(Date start, Date end) {
	// this.start = new Date(start.getTime());
	// this.end = new Date(end.getTime());
	//
	// if (this.start.compareTo(this.end) > 0)
	// throw new IllegalArgumentException(start +" after "+ end);
	// }

	public Date start() {
		return start;
	}

	public Date end() {
		return end;
	}

	// Repaired accessors - make defensive copies of internal fields - Page 186
	// Point 2: Stops second attack
	// ��ΪPeriod���ṩ��end()����ķ��ʷ�������Щ�����ṩ�˶���ɱ��ڲ���Ա�ķ�������
	// �����ǿ����Ҳ���޸�����������ʹ֮����date�ı����Կ���
	// public Date start() {
	// return new Date(start.getTime());
	// }
	//
	// public Date end() {
	// return new Date(end.getTime());
	// }

	public String toString() {
		return start + " - " + end;
	}

	// Remainder omitted
}