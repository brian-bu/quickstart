package net.brian.coding.java.utils.entity;

import java.util.List;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * ʵ����������ǹ��еģ��������пɱ�����ಢ�����̰߳�ȫ�ġ���ʹ����final�������ò��ɱ����
 * ����������Ϊ���е�Ҳ�����ˡ��л���һ���µ��ڲ����ݱ�ʾ�����������
 * ���ȷ��javabean���̰߳�ȫ���أ�
 * ����volatile-javabeanģ�ͻ��߹���ͬ����getter��setter
 * 
 * item14: In public classes, use accessor methods, not public fields
 * 
 * ��������౩¶������������Ҫ���ڽ����ı����ڲ���ʾ���ǲ����ܵģ���Ϊ������Ŀͻ��˴����Ѿ��鲼������
 * �������Щ������������public����ô��Щ���ݵı�ʾ�������ǹ̶����ˣ��޷��ı������κ�Լ������
 * ���򱻷���ʱҲ�޷���ȡ�κθ����ж� 
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
