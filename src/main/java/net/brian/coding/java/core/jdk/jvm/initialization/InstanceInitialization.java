package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * java分为基本类型变量和引用类型变量，其中引用类型变量包括数组和普通的java对象
 * 
 * 对于java程序中所有的引用变量都不需要经过所谓的初始化，需要进行初始化操作的是该引用变量所引用的对象
 * 数组变量不需要进行初始化操作，数组对象本身需要初始化操作，对象的引用变量不需要初始化，对象本身才需要初始化
 * 
 * 变量分为：
 * a.类变量：
 * 方法体外，类以内，用static修饰的变量，同一个JVM内每个类只有一个Class对象，因此类变量随着类的加载只能初始化一次
 * b.实例变量：
 * 方法体外，类以内，不用static修饰的变量，可在三种情况完成初始化操作
 * 其中两种和类变量相同：定义的时候初始化和代码块初始化
 * 此外，由于每次调用构造器创建类的实例的时候都会创建新的实例，所以实例变量可以比类变量在构造器中获得额外的一次初始化的机会
 * @see net.brian.coding.jdk.jvm.initialization.Cat
 * 实例变量属于类的实例而不属于类本身，因此每次创建一个类的实例就会为实例变量分配一次内存空间并对实例变量初始化一次
 * 一句话：类变量在类初始化阶段完成初始化，实例变量在对象初始化阶段完成初始化
 * c.局部变量：
 * 局部变量分为三类：形参，方法内的局部变量，代码块内的局部变量
 * 类变量和实例变量都有好几次机会可以初始化，但是对于局部变量，由于定义和使用都在方法内部
 * 所以除了形参可以被方法调用方传参，其余必须在声明的时候初始化
 * 所有局部变量都是存放在栈内存，不管是基本类型变量还是引用类型
 * 都存在各自的方法栈，但引用类型变量所引用的对象总存储在堆内存中
 * 
 * 没有经过初始化的引用类型变量，只要不访问它的属性和方法，程序完全可以使用它，只不过默认为null
 * 只有当引用类型变量初始化完毕才可以访问它的属性和方法，否则会抛出NPE
 *
 */
public class InstanceInitialization {
	// 添加这个注解消除针对p.eyeNum和p2.eyeNum的编译警告：
	// The static field Cat.eyeNum should be accessed in a static way
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		//类变量属于该类本身，只要该类初始化完成，程序即可使用类变量。
		Cat.eyeNum = 2;
		//通过Person类访问eyeNum类变量
		System.out.println(Cat.eyeNum);
		//创建第一个Person对象
		Cat p = new Cat();
		p.name = "NameA";
		p.age = 300;
		//通过p访问Person类的eyeNum类变量
		System.out.println(p.eyeNum);
		p.toString();
		//创建第二个Person对象
		Cat p2 = new Cat();
		p2.name = "NameB";
		p2.age = 500;
		p2.toString();
		//通过p2修改Person类的eyeNum类变量
		p2.eyeNum = 3;
		//分别通过p、p2和Person访问Person类的eyeNum类变量
		//实例也是可以访问类变量的，但是编译器会给出警告，建议用类访问类变量
		System.out.println(p.eyeNum);
		System.out.println(p2.eyeNum);
		System.out.println(Cat.eyeNum);
	}
}
