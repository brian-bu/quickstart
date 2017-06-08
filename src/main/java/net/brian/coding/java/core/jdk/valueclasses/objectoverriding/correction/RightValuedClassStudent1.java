package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction;
/**
 * 
 * 这暴露了面向对象语言中关于等价关系的一个基本问题：
 * 我们无法在扩展可实例化的类的同时，既增加新的值组件同时又保留equals的约定
 * 
 * 因此我们改进Student1，通过复合代替继承（这也是我们倡导“复合优先于继承”的原因之一）
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
