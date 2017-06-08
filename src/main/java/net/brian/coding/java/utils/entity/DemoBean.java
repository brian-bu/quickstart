package net.brian.coding.java.utils.entity;

import java.util.List;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * 实例域决不能是公有的，包含公有可变域的类并不是线程安全的。即使域是final的且引用不可变对象
 * 当把这个域变为公有的也放弃了“切换到一种新的内部数据表示法”的灵活性
 * 如何确保javabean的线程安全性呢？
 * 采用volatile-javabean模型或者构造同步的getter和setter
 * 
 * item14: In public classes, use accessor methods, not public fields
 * 
 * 如果公有类暴露了它的数据域，要想在将来改变其内部表示法是不可能的，因为公有类的客户端代码已经遍布各处了
 * 如果将这些数据域声明成public，那么这些数据的表示方法就是固定的了，无法改变或添加任何约束条件
 * 当域被访问时也无法采取任何辅助行动 
 *
 */
public class DemoBean {

	public DemoBean() {
		System.out.println("Demo()");
	}

	@SuppressWarnings("unused")
	private DemoBean(List<String> list) {
		System.out.println("Demo(List)");
	}

	public DemoBean(String str) {
		System.out.println("Demo(String)");
	}

	public static void main(String[] args) {
		System.out.println("Hello Demo!");
	}

	private String name;
	private String pass;
	private String gender;
	private String birthday;
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
