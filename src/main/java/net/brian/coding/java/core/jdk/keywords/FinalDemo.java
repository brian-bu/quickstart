package net.brian.coding.java.core.jdk.keywords;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * 不可变对象只有一种状态，即被创建时的状态。如果确保所有构造器都建立了这个类的约束关系
 * 就可以确保这个约束关系在整个生命周期不再变化，使用这个类的时候无需额外的工作维护这些约束关系
 * 因此不可变的对象都是线程安全的，利用这个特点：
 * a.尽可能重用现有实例
 * @see net.brian.coding.java.core.oop.MinimizeMutabilityDemo.ZERO
 * b.缓存并共享现有实例，降低内存占用和垃圾回收成本
 * @see net.brian.coding.java.core.oop.MinimizeMutabilityDemo.valueOf(double, double)
 * 
 * 可以创建一个包含可变对象的不可变对象的，你只需要谨慎一点，不要共享可变对象的引用就可以了
 * 如果需要变化时，就返回原对象的保护性拷贝。最常见的例子就是对象中包含一个日期对象的引用，见：
 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
 * 
 * final修饰变量：基本类型被赋值后不可重新赋值，引用类型初始化后不可改变引用
 * final修饰方法：方法不能被覆盖或重写
 * final修饰类：该类不可被继承
 * 
 * 不管是类变量、实例变量还是局部变量，只要定义该变量时使用final修饰
 * 并在定义该final变量的时候指定了初始值且该初始值在编译的时候就能确定下来
 * 那么这个final变量本质上就已经不再是变量，而是直接量
 * 
 * 用final定义域时，这些域要么包含基本类型的值，要么指向不可变的对象比如字符串，否则：
 * 如果final域包含可变对象的引用，那么虽然引用本身不能修改，但是它所引用的对象可以被修改
 * 注意：长度非零的数组总是可变的，所以类具有公有的静态final数组域或返回这些域的访问方法，这几乎总是错的
 * 
 */
public class FinalDemo {
	public static class FinalClass {
		// final修饰的实例变量必须显式指定初始值，且只能在定义、代码块、构造器三种情况下选择一种而不能多次赋值
		// 否则会有编译错误：The blank final field name may not have been initialized
		final String name;
		// 12345是一个在编译的时候就能确定下值的直接量，用final修饰之后就变成了宏变量
		// 此外还有编译的时候就可以通过直接量计算出结果的比如final int a = 5 + 2;也是宏变量
		// 这种类型的变量通常都抽取到一个统一的常量类中并用大写字母命名
		final int id = 12345;
		final SubFinalClass myClass = new SubFinalClass();
		// 长度非零的数组总是可变的，如果类具有共有的静态final数组域或返回这种数组域的访问方法，几乎总是错误的
		final String[] myClassArr = {"Brian", "Sure"};

		public void changeFinalArray() {
			// 这样重新引用肯定是不行的
			// myClassArr = new String[12];
			// 但是如下我们把数组第二个元素由Sure改成了Yanan
			// 这样却是可以的，也就是说客户端可以随意更改数组内的元素对应的引用
			myClassArr[1] = "Yanan";
		}
		// 以下有两种改进方法，但是无论哪种，都要先把数组变为私有的
		private static final String[] PRIVATE_VALUES = {"Brian", "Sure"};
		// 针对final数组域myClassArr的改进，以防止对final数组域的更改（从final数组域的声明入手）
		// 使公有数组变成私有的，并增加一个公有的不可变列表：
		// 这里主要是unmodifiableList在起作用，看它的源码可知它的作用就是返回一个给定List的不可变视图（unmodifiable view）
		// 这个不可变视图是read-only的，详见方法的源码注释
		public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
		
		// 针对方法changeFinalArray的改进，以防止对final数组域的更改（从final数组域的操作方法入手）
		// 使数组变为私有，并增加一个公有方法来返回私有数组的一个备份
		public static final String[] values() {
			return PRIVATE_VALUES.clone();
		}

		public void outputReferenceClass() {
			//输出1进而证明引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的
			System.out.println(++myClass.i);
		}
		
		public FinalClass() {
			this.name = "Brian";
		}
		
		public final void testFinalMethod() {
			// 这个方法不能被子类覆盖，但是在当前类中可以被重写
		}
		
		public void testFinalMethod(int age) {
			// 这个方法没有final关键字修饰，可以被子类覆盖
		}
		
		public void testFinalParam(final int age) {
			// 其意义在于让传进来的参数不可变，对于方法参数这种局部变量，传参的时候就赋值了
			// 因此用final修饰的目的就是确保在调用该方法传参之后这个值就不再被改变
			// age = 10;
			System.out.println("Output final int age in param list:: " + age);
		}
	}
	public static class SubFinalClass extends FinalClass {
		public int i = 0;
		@Override
		public void testFinalMethod(int age) {
			// 覆盖testFinalMethod()会报编译错误，只能覆盖testFinalMethod(int age)
		}
	}
	// 对比两个方法可以作为变量和直接量的区分
	public static void main(String[] args) {
		// 直接量
		String str1 = "Brian&Sure";
		String str12 = "Brian" + "&Sure";
		System.out.println("str1 == str12:: " + (str1 == str12));//true
		// 非直接量
		String str21 = "Brian";
		String str22 = "&Sure";
		String str2 = str21 + str22;
		System.out.println("str1 == str2:: " + (str1 == str2));//false
		// 经过final修饰，原来的非直接量现在也变成了直接量
		final String str31 = "Brian";
		final String str32 = "&Sure";
		String str3 = str31 + str32;
		System.out.println("str1 == str3:: " + (str1 == str3));//true
	}
}
