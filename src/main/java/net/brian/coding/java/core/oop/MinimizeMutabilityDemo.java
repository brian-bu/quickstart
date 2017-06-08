package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item15: Minimize mutability
 * 
 * 为了使类不可变，需要遵循的五条原则：
 * a.不要提供任何会修改对象状态的方法，比如setter
 * b.保证类不会被扩展：
 * @see net.brian.coding.java.core.oop.AvoidingInheritance
 * c.使所有的域都是final的
 * d.使所有的域都是私有的
 * e.确保对任何可变组件的互斥访问：如果类一定要具有指向可变对象的域，必须确保客户端无法获得并操作这些引用
 * 并在构造器访问方法和readObject中使用保护性拷贝
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DeepCopyDemo
 * 
 * 注意：不可变对象也是有缺点的，它的唯一缺点就是对于每一个不同的值都需要一个单独的对象
 * 执行一个多步操作且每个步骤都会产生一个新对象，比如+连接多个字符串，最后只保留一个对象其余的全抛弃
 * 这样就会产生很大的性能问题，为此可提供公有的可变配套类，比如StringBuilder之于String
 * 现在用+连接字符串编译器会自动调用StringBuilder的append方法进行优化
 * 
 * 此外对于一些开销昂贵的计算可以用final域将结果缓存起来进而节约重新计算的开销
 * 因为对象的不可变性，所以这些计算结果再次被执行可以产生相同的结果，因此不需要重复执行 
 * 比如String的hashCode的计算就是先将对象缓存起来，这就是延迟初始化
 * 
 * 此外还应该关于编译期常量的注意事项，具体见下方示范代码的注释。
 * 
 */
// Point b：保证类不会被扩展
public final class MinimizeMutabilityDemo {

	// Point c and d: 使所有的域都是final的，使所有的域都是私有的
	private final double re;
	private final double im;

	private MinimizeMutabilityDemo(double re, double im) {
		this.re = re;
		this.im = im;
	}

	// 缓存并共享现有实例，降低内存占用和垃圾回收成本
	public static MinimizeMutabilityDemo valueOf(double re, double im) {
		// Notes: Normal final-like class style: To return a new reference as
		// result instead of change the old one.
		return new MinimizeMutabilityDemo(re, im);
	}

	// 静态工厂的第二个好处：它有各自的名字
	public static MinimizeMutabilityDemo valueOfPolar(double r, double theta) {
		return new MinimizeMutabilityDemo(r * Math.cos(theta), r * Math.sin(theta));
	}

	// 不可变对象一定是线程安全的，因此鼓励最大程度运用这个特性保证线程安全，尽可能重用现有实例
	// 比如对于频繁用到的值提供public static final声明的常量，也就是编译期常量
	// 对于编译期常量的使用需要注意的问题：
	// 公共静态不可变（public static final ）变量也就是我们所说的编译期常量，这里的 public 可选的
	 // 实际上这些变量在编译时会被替换掉，因为编译器知道这些变量的值，并且知道这些变量在运行时不能改变
	// 这种方式存在的一个问题是你使用了一个内部的或第三方库中的公有编译时常量，但是这个值后面被其他人改变了
	// 但是你的客户端仍然在使用老的值，甚至你已经部署了一个新的jar
	// 为了避免这种情况，当你在更新依赖 JAR 文件时，确保重新编译你的程序
	public static final MinimizeMutabilityDemo ZERO = new MinimizeMutabilityDemo(0, 0);
	public static final MinimizeMutabilityDemo ONE = new MinimizeMutabilityDemo(1, 0);
	public static final MinimizeMutabilityDemo I = new MinimizeMutabilityDemo(0, 1);

	// Point a.不要提供任何会修改对象状态的方法，比如setter
	public double realPart() {
		return re;
	}

	public double imaginaryPart() {
		return im;
	}

	/**
	 * 对于以下四个表示加减乘除的方法是一种典型的函数式做法：
	 * 创建并返回新的MinimizeMutabilityDemo实例，而不是修改这个实例
	 * 大多数不可变的类都使用这种模式，比如String的replace、replaceAll等方法
	 * 这种方法带来了不可变性
	 * @see 
	 * 对应的是过程式或命令式的做法，也即void方法，不返回值，直接对局部变量或全局变量进行逻辑处理
	 * 这种做法会导致方法的操作对象的状态发生改变
	 * @param c
	 * @return
	 */
	public MinimizeMutabilityDemo add(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re + c.re, im + c.im);
	}

	public MinimizeMutabilityDemo subtract(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re - c.re, im - c.im);
	}

	public MinimizeMutabilityDemo multiply(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re * c.re - im * c.im, re * c.im + im * c.re);
	}

	public MinimizeMutabilityDemo divide(MinimizeMutabilityDemo c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new MinimizeMutabilityDemo((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof MinimizeMutabilityDemo))
			return false;
		MinimizeMutabilityDemo c = (MinimizeMutabilityDemo) o;

		// See page 43 to find out why we use compare instead of ==
		return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
	}

	@Override
	public int hashCode() {
		int result = 17 + hashDouble(re);
		result = 31 * result + hashDouble(im);
		return result;
	}

	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(re);
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}

}