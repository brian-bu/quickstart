package net.brian.coding.java.core.oop;

/**
 * 比如对于一个方法，传进来的实参可能是形参任意类型的子类，而它们都可以写成相同的代码
 * 根据运行时传进来的实际参数找到对应的子类，每个子类有不同的行为这就叫多态
 * 
 * 展示了多态情况下父类和子类中的属性和方法之间的继承关系：对于一个引用类型变量而言：
 * 当通过该变量访问它所引用的对象的实例变量时，该变量的值取决于声明该变量时类型
 * 当通过该变量来调用它所引用的对象的方法时，该方法行为取决于它所实际引用的对象类型
 * 
 * 当程序创建一个子类对象时，系统不仅会为该类中定义的实例变量分配内存
 * 也会为其父类中定义的所有实例变量分配内存，即使子类定义了与父类同名的实例变量
 * 
 */
public class PolymorphismDemo {

	public void base2Base() {
		// 声明、创建一个Base对象
		Base b = new Base();
		// 直接访问count实例变量和通过display访问count实例变量
		System.out.println(b.count);
		b.display();
	}

	public void derived2Derived() {
		// 声明、并创建一个Derived对象
		Derived d = new Derived();
		// 直接访问count实例变量和通过display访问count实例变量
		System.out.println(d.count);
		d.display();
	}

	public void base2Derived() {
		// 声明一个Base变量，并将Derived对象赋给该变量，这种情况下在堆中并不存在Base的对象
		// 程序的方法栈中保存着一个Base的引用，而堆中只存在一个Derived的对象
		// 但是这个对象不仅保存了Derived对象的全部属性和方法，还同时保存着父类定义的全部实例变量
		// 也就是说多态不仅仅是父类引用指向子类对象方便替换其它子类实现这么简单，它还把父类的实例变量传了过去
		// 如果父类和子类有 同名的实例变量，则父类的会覆盖子类的
		Base bd = new Derived();
		// 直接访问count实例变量和通过display访问count实例变量
		System.out.println(bd.count);
		bd.display();
	}

	public void base2Derived2() {
		Derived d = new Derived();
		// 让d2b变量指向原d变量所指向的Dervied对象
		Base d2b = d;
		// 访问d2b所指对象的count实例变量
		System.out.println(d2b.count);
	}
}
class Base {
	int count = 2;

	public void display() {
		System.out.println(this.count);
	}
}

class Derived extends Base {
	int count = 20;

	@Override
	public void display() {
		System.out.println(this.count);
	}
}