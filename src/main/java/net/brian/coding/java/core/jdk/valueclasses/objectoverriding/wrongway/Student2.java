package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;

public class Student2 extends WrongValuedClassStudent{
	private String name;
	private int id;
	private char sex;
	private int age;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public Student2(String name, int id, char sex, int age) {
		this.name = name;
		this.id = id;
		this.sex = sex;
		this.age = age;
	}
}