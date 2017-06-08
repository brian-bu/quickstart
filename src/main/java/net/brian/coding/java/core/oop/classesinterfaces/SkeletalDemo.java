package net.brian.coding.java.core.oop.classesinterfaces;
/**
 * 
 * 抽象类允许部分内部实现部分屏蔽细节：private或protected变量、main方法、非final变量
 * 接口是绝对抽象屏蔽一切细节的：默认public、final，不允许方法的实现因此子类实现全部方法
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item17: Design and document for inheritance or else prohibit it
 * 
 * item18: Prefer interfaces to abstract classes
 * 
 * mixin(混合类型)：多个类型的实现，由于java单根继承所以抽象类不适合mixin的实现，需要用接口
 * 
 * 抽象类的问题(结合花旗AbstractAction经验)：
 * a.抽象类允许包含某些方法的实现，接口则不允许，因此抽象类可以形成模板模式
 * 定义一部分方法的实现，然后所有子类自行实现其中的抽象方法
 * b.为了实现由抽象类定义的类型，类必须成为抽象类的一个子类于是无法继承别的类
 * 补救办法是骨架实现：定义若干mixin接口，然后通过一系列的抽象类实现一部分方法，
 * 并用模板模式定义一部分方法由子抽象类实现，最终形成一个骨架
 * 一旦接口需要添加新方法或者添加新的接口的时候，不需要改动所有的实现类，仅需改动部分骨架实现类即可
 * 
 * 根据item17，这里的骨架实现类就是为了继承而设计的
 * 为了允许继承，构造器不能调用可被覆盖的方法，同理，因为clone方法和readObject方法非常类似构造器
 * 所以无论clone还是readObject，都不可以调用可覆盖的方法
 * 
 * 如果代码已经成型无法扩展成骨架实现类，那么就通过java的私有内部类模拟多重继承
 * 关于内部类的详细介绍见：
 * {@link https://app.yinxiang.com/shard/s62/nl/12840192/0def3bc6-dae7-435a-9e78-4d3eb9e1a4bc 印象笔记}
 *
 */
public class SkeletalDemo extends SkeletalInterface {
	public static void main(String[] args) {
		SkeletalInterface demo = new SkeletalDemo();
		demo.method();
		demo.additionalMethod();
	}

	@Override
	public void method() {
		System.out.println("SkeletalDemo - method()...");
	}

	@Override
	public void anotherMethod() {
		System.out.println("SkeletalDemo - anotherMethod()...");
	}
	
	@Override
	public void testDefaultForJDK8() {
		System.out.println("Implementation of this method declared in Skeletal interface is optional.");
	}
}
