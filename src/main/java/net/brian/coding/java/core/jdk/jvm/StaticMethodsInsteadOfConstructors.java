package net.brian.coding.java.core.jdk.jvm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item01: Consider static factory methods instead of constructors
 * 
 * 静态工厂相比构造器的优势：
 * a.有名称，如Boolean.valueOf；构造器名称需要和类保持一致，这就无法一目了然地看出每个构造器的具体作用
 * b.不必每次都创建一个新对象，所有的工厂方法可以返回同一个实例，如果创建这个对象需要很大代价的情况，这种方式会显著提升性能
 * 既然是返回同一个实例，下次比较两个对象时可以用==代替equals方法来比较对象，这样也可以提高性能
 * c.返回原返回对象的子类型，这样对于返回类型我们就有了更多的灵活性
 * 一旦某些子类型被验证不好用我们可以随时替换掉它们而不会对客户端产生任何影响
 * 因为客户端调用的是工厂方法，工厂的内部实现对客户端而言是不可见的
 * 
 * 具体实现举例：
 * a.集合类每个版本的jdk都会进行改良，为了方便改动代码，java.util.Collections里面大量应用了这种方法
 * @see java.util.Collections
 * b.JDBC就是一个服务提供者框架，即：由多个服务提供者实现一个服务，系统为服务提供者的客户端提供多个实现
 * 并把他们从多个实现中解耦出来。比如JDBC API就是一个典型的服务提供者框架。
 * 对于JDBC的详细代码示例在JdbcConnectionByMysql给出
 * 
 * @see net.brian.coding.db.jdbc.JdbcConnectionByMysql
 * {@linkplain http://www.tuicool.com/articles/vmMni2}
 *
 */
public class StaticMethodsInsteadOfConstructors extends SuperClient {
	// 私有化构造器，不通过构造器产生对象，利用工厂方法提供类的客户端，在工厂方法内部调用私有构造器产生对象
	private StaticMethodsInsteadOfConstructors() {
	}
	// 组合代替继承的方式实现静态工厂，实际上java也鼓励组合代替继承
	private static StaticMethodsInsteadOfConstructors client = new StaticMethodsInsteadOfConstructors();

	public static StaticMethodsInsteadOfConstructors giveMethodMeaningfulName() {
		return client;
	}

	public static SuperClient returnMultiType() {
		return new StaticMethodsInsteadOfConstructors();
	}

	public static <K, V> HashMap<K, V> newHashMapInstance() {
		return new HashMap<K, V>();
	}

	public static void main(String[] args) {
		// a.构造器只能和类同名，方法可以有自己的名字
		StaticMethodsInsteadOfConstructors client1 = giveMethodMeaningfulName();
		StaticMethodsInsteadOfConstructors client2 = giveMethodMeaningfulName();
		// b.通过==比较对象，比equals拥有更好的性能
		System.out.println("If static method factory always return the same reference:: " + (client1 == client2));
		// c.代替冗长的赋值：Map<String, List<String>> m = new HashMap<String, List<String>>();
		@SuppressWarnings("unused")
		Map<String, List<String>> m = StaticMethodsInsteadOfConstructors.newHashMapInstance();
	}
}

class SuperClient {
}