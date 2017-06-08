package net.brian.coding.java.core.jdk.jvm.classloader;
/**
 * 
 * ClassLoader的重要作用：
 * a.将Class加载到JVM的永久代
 * b.审查每个类由谁加载，是一种父优先的等级加载机制
 * c.将Class字节码重新解析成JVM统一要求的对象格式
 * 
 * ClassLoader加载类的方式：
 * a.显式加载：某个class被ClassLoader主动加载就是显式的，显式加载的方式通常有：
 * Class.forName("abc.class");ClassLoader.loaderClass("abc.class");ClassLoader.findSystemClass("abc.class");
 * 这些方法都有一个共同点就是传入的参数都是一个字符串，一旦字符串拼写错误或者根本就没有这个类就会抛出ClassNotFoundException
 * 
 * 因此显式加载常见的异常就是ClassNotFoundException
 * 
 * @see net.brian.coding.java.core.jdk.jvm.classloader.ClassUtil.printClassPath(ClassLoader)
 * 
 * b.隐式加载：该class被加载的时候发现引用了其它的类不在内存中JVM会自动将这些类加载，这就是隐式加载。显式和隐式通常是混合使用的
 * 
 * 因此隐式加载常见的异常就是NoClassDefFoundError，由于是JVM在运行期间显式加载某个类的时候隐式加载了该类中包含的其它的类的引用
 * 因此这个NoClassDefFoundError是要在代码运行起来的时候才发生的，编译期无法检查该错误，避免的方法就是保证所有的引用类都在classpath下
 * 
 */
public class DefaultClassLoaderDemo {
	/**
	 * 如果你不想重新定义加载类的规则，也没有复杂的处理逻辑
	 * 只想在运行的时候加载自己指定的一个类而已，就可以用下面这种方法
	 * @throws ClassNotFoundException
	 */
	public void loadClassByDefault() throws ClassNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		// 利用loadClass
		classLoader.loadClass("abc.class");
		// 利用ClassLoader提供的getResource或者getResourceAsStream
		classLoader.getResource("abc.class");
		classLoader.getResourceAsStream("abc.class");
	}
}
