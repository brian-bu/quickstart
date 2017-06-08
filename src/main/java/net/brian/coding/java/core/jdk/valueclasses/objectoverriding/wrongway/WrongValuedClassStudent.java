package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
/**
 * 
 * ��������������equals(Object)�����Ƚ������ ��ô��������������������һ�������hashCode�������������ͬ�����������
 * ��˸���equals��ͬʱ���븲��hashCode
 * 
 * equals��compareTo��ʵ�ֹ��������ͬ
 * 
 * Υ����hashCodeԼ��������ƻ�����������ɢ���������࣬Υ��compareTo��Լ��Ҳ���ƻ����������ڱȽϹ�ϵ����
 * ������ɢ�е������HashSet��HashMap
 * �����ڱȽϹ�ϵ����������򼯺���TreeMap��TreeSet
 * ��Ӧ�������ڱȽϹ�ϵ�Ļ��м��Ϲ�������Collections��Arrays�������ڲ����������������㷨
 * 
 * ���⣬����һ���¶���õľ��бȽϹ�ϵ���࣬Ӧ���ṩ��Comparable�ӿڵ�ʵ���Ա���ʵ��Ӧ���бȽ϶���
 * jdk�����е�ֵ�඼���бȽϹ�ϵ����ʵ����Comparable�ӿ�
 * ����ʵ��Comparable�ӿڣ���Ҫָ����Ӧ�ķ��ͣ�����������ͱ�����Ǹ�ֵ��
 * ������������ǿ���㸲��compareTo������ʱ��ͻ�����ʹ�ø�ֵ����ΪcompareTo�Ĳ�����
 * class WrongValuedClassStudent implements Comparable<WrongValuedClassStudent>
 * public int compareTo(WrongValuedClassStudent o)
 * class WrongValuedClassStudent implements Comparable<Object>
 * public int compareTo(Object o)
 * ����Ȼ����ʵ��Comparable����Ϊ�˷���Ƚ��¶����ֵ������Ķ���
 * Ҫ�ǱȽϵĲ���Student����Object�Ƕ������ֵ�໹��ʲô�����أ�
 * 
 */
public class WrongValuedClassStudent implements Comparable<WrongValuedClassStudent> {

	private String name;
	private double height;
	private boolean ifGraduated;
	private char sex;// 'F' or 'M'
	private float gpa;
	private long timeStamp;

	/**
	 * ����equals�Ĳ���ֻ����Object���������Object���������൱�����أ����Ǹ���
	 * �����ڸ���equals������ͬʱ����Ҫ�Ѳ���ת������ȷ������
	 */
	@Override
	public boolean equals(Object obj) {
		// ʹ��==��������顰�����Ƿ�Ϊ�����������á�
		if (this == obj)
			return true;
		// ʹ��instanceof��������顰�����Ƿ�Ϊ��ȷ�����͡�
		if (!(obj instanceof WrongValuedClassStudent))
			return false;
		// �Ѳ���ת������ȷ�����͡�
		WrongValuedClassStudent student = (WrongValuedClassStudent) obj;
		// ���ڸ����е�ÿ�����ؼ����򣬼������е����Ƿ���ö����ж�Ӧ������ƥ��(���´���ʾ��)��
		return this.name == student.name && this.sex == student.sex && this.gpa == student.gpa && this.timeStamp == student.timeStamp&& this.height == student.height && this.ifGraduated == student.ifGraduated; 
	}

	@Override
	/**
	 * ��Ҫ���������������еĶ���ӵ����ͬ��hashֵ�����hash��ײ������HashMap��Ÿ�ֵ���Ԫ��ʱ�˻�������
	 * @see net.brian.coding.datastructure.HashMapDemo
	 */
	public int hashCode() {
		return 0;
	}
	
	/**
	 * ����Effective Java����˵��compareTo��equals��ʵ�ֹ�����һ�µģ����ԾͲ���ʾ�������compareTo��ʵ�ַ�ʽ
	 * ������ȷ��ʵ�ַ�ʽд����
	 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent.compareTo(RightValuedClassStudent)
	 */
	@Override
	public int compareTo(WrongValuedClassStudent o) {
		return 0;
	}
	
}