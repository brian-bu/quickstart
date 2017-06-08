package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction;
/**
 * 
 * �Ⱪ¶��������������й��ڵȼ۹�ϵ��һ���������⣺
 * �����޷�����չ��ʵ���������ͬʱ���������µ�ֵ���ͬʱ�ֱ���equals��Լ��
 * 
 * ������ǸĽ�Student1��ͨ�����ϴ���̳У���Ҳ�����ǳ��������������ڼ̳С���ԭ��֮һ��
 * 
 * @see net.brian.coding.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent1
 *
 */
public class RightValuedClassStudent1 {
	private RightValuedClassStudent student;
	private int age;
	public RightValuedClassStudent1(String name, char sex, int age) {
		student = new RightValuedClassStudent(name, sex);
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RightValuedClassStudent1)) return false;
		RightValuedClassStudent1 student1 = (RightValuedClassStudent1)obj;
		return student1.student.equals(student) && student1.age == age;
	}
	
	public RightValuedClassStudent getStudent() {
		return student;
	}
	public void setStudent(RightValuedClassStudent student) {
		this.student = student;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
