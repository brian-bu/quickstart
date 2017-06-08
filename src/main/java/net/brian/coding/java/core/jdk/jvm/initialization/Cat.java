package net.brian.coding.java.core.jdk.jvm.initialization;

import java.io.Serializable;

/**
 * 
 * 实例变量初始化的三种方式和先后顺序
 * 
 * 此外:
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * 为了用这个值类示范序列化，所以让Cat实现了Serializable接口
 * 实现序列化的第一种方式就是实现Serializable接口，还有一种方式就是实现接口Externalizable
 * 对于该接口的详细使用情况请见：http://www.cnblogs.com/xiohao/p/4234184.html
 *
 */
public class Cat implements Serializable
{
	// 序列化Point 1：serialVersionUID如果不统一，反序列化的时候会出错
	// 序列化之后将5952689219411916553L改成595268921941191655L再进行反序列化则报错：
	// java.io.InvalidClassException: net.brian.coding.java.core.jdk.jvm.initialization.Cat; local class incompatible: stream classdesc serialVersionUID = 5952689219411916553, local class serialVersionUID = 595268921941191655
	private static final long serialVersionUID = 5952689219411916553L;
	// 序列化Point 2: 序列化只是序列化对象，因而序列化并不保存和类相关的变量，也就是静态变量
	public static int eyeNum;
	// 定义name、age两个实例变量
	public String name;
	public int age;
	// 序列化Point 3: 即使是私有的域也会被序列化到流里去，丧失了密码的安全性
	private String pass1;
	// 序列化Point 4: 通过添加transient关键字使得密码对应的域不参加序列化
	private transient String pass2;
	// 使用构造器初始化name、age两个实例变量
	// 初始化Point 3：这里还有一次机会对weight变量初始化，无论构造器在什么位置出现都会最后被执行
	public Cat(String name , int age, int eyeNum)
	{
		// Overriding重写即覆盖：当子类需要改变从父类继承得到的方法的行为时就需要重写，发生在父类和子类之间
		// Overloading是重载，也即参数列表发生变化，发生在同一个类中
		System.out.println("Overloading constructor.");
		this.name = name;
		this.age = age;
		this.eyeNum = eyeNum;
	}
	public Cat() {
		System.out.println("Default constructor.");
	}
	{
		System.out.println("执行非静态初始化块");
		// 初始化Point 1：首先会先加载所有实例变量的声明语句，比如double weight；
		// 然后按照代码块和定义时的赋值语句在代码出现的先后顺序进行赋值
		// 这里的非静态初始化块先于Point2出现，因此先执行
		weight = 2.0;
	}
	// 初始化Point 2：虽然直到这里才定义weight，但是之前的非静态初始化块并不属于非法引用
	// 按照代码出现的顺序Point1先执行，所以已经赋值2.0，这里赋值2.3是覆盖了之前的赋值
	double weight = 2.3;
	// 序列化Point 5: 序列化前保留这个方法，序列化把该类实例保存到磁盘之后注释掉这个方法
	// 再进行反序列化取出这个对象的时候，看看还能否通过反序列化重新构建Cat的实例
//	@Override
//	public String toString()
//	{
//		return "Cat[name=" + name
//			+ ",age=" + age + ",weigth=" + weight + "]";
//	}
}