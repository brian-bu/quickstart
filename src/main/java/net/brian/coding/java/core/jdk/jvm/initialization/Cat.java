package net.brian.coding.java.core.jdk.jvm.initialization;

import java.io.Serializable;

/**
 * 
 * ʵ��������ʼ�������ַ�ʽ���Ⱥ�˳��
 * 
 * ����:
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * Ϊ�������ֵ��ʾ�����л���������Catʵ����Serializable�ӿ�
 * ʵ�����л��ĵ�һ�ַ�ʽ����ʵ��Serializable�ӿڣ�����һ�ַ�ʽ����ʵ�ֽӿ�Externalizable
 * ���ڸýӿڵ���ϸʹ����������http://www.cnblogs.com/xiohao/p/4234184.html
 *
 */
public class Cat implements Serializable
{
	// ���л�Point 1��serialVersionUID�����ͳһ�������л���ʱ������
	// ���л�֮��5952689219411916553L�ĳ�595268921941191655L�ٽ��з����л��򱨴�
	// java.io.InvalidClassException: net.brian.coding.java.core.jdk.jvm.initialization.Cat; local class incompatible: stream classdesc serialVersionUID = 5952689219411916553, local class serialVersionUID = 595268921941191655
	private static final long serialVersionUID = 5952689219411916553L;
	// ���л�Point 2: ���л�ֻ�����л�����������л��������������صı�����Ҳ���Ǿ�̬����
	public static int eyeNum;
	// ����name��age����ʵ������
	public String name;
	public int age;
	// ���л�Point 3: ��ʹ��˽�е���Ҳ�ᱻ���л�������ȥ��ɥʧ������İ�ȫ��
	private String pass1;
	// ���л�Point 4: ͨ�����transient�ؼ���ʹ�������Ӧ���򲻲μ����л�
	private transient String pass2;
	// ʹ�ù�������ʼ��name��age����ʵ������
	// ��ʼ��Point 3�����ﻹ��һ�λ����weight������ʼ�������۹�������ʲôλ�ó��ֶ������ִ��
	public Cat(String name , int age, int eyeNum)
	{
		// Overriding��д�����ǣ���������Ҫ�ı�Ӹ���̳еõ��ķ�������Ϊʱ����Ҫ��д�������ڸ��������֮��
		// Overloading�����أ�Ҳ�������б����仯��������ͬһ������
		System.out.println("Overloading constructor.");
		this.name = name;
		this.age = age;
		this.eyeNum = eyeNum;
	}
	public Cat() {
		System.out.println("Default constructor.");
	}
	{
		System.out.println("ִ�зǾ�̬��ʼ����");
		// ��ʼ��Point 1�����Ȼ��ȼ�������ʵ��������������䣬����double weight��
		// Ȼ���մ����Ͷ���ʱ�ĸ�ֵ����ڴ�����ֵ��Ⱥ�˳����и�ֵ
		// ����ķǾ�̬��ʼ��������Point2���֣������ִ��
		weight = 2.0;
	}
	// ��ʼ��Point 2����Ȼֱ������Ŷ���weight������֮ǰ�ķǾ�̬��ʼ���鲢�����ڷǷ�����
	// ���մ�����ֵ�˳��Point1��ִ�У������Ѿ���ֵ2.0�����︳ֵ2.3�Ǹ�����֮ǰ�ĸ�ֵ
	double weight = 2.3;
	// ���л�Point 5: ���л�ǰ����������������л��Ѹ���ʵ�����浽����֮��ע�͵��������
	// �ٽ��з����л�ȡ����������ʱ�򣬿������ܷ�ͨ�������л����¹���Cat��ʵ��
//	@Override
//	public String toString()
//	{
//		return "Cat[name=" + name
//			+ ",age=" + age + ",weigth=" + weight + "]";
//	}
}