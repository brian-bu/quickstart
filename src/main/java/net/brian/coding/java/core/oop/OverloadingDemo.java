package net.brian.coding.java.core.oop;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item41: Use overloading judiciously
 * 
 * 对于重载方法(overloaded method)的选择是静态，对于被覆盖的方法(overriden method)的选择是动态的。
 * 选择被覆盖的方法的正确版本是在运行时进行的，选择的依据是被调用方法所在对象的运行时类型
 * 
 * 避免乱用重载的策略：
 * a.永远不要导出两个具有相同参数数目的重载方法，如果方法使用可变参数，保守的策略是根本不要重载它
 * b.始终给方法起不同的名称而不是重载，这样就可以避免“对于任何一组实际的参数，哪个重载方法是适用的”的疑惑中
 * 对于构造器的重载，可以参照item02，即可以导出静态工厂方法并给每个方法起不同的名字，而不是重载构造器
 * c.至少应该避免这样的情形：同一组参数只需经过类型转换就可以被传递给不同的重载方法
 * 如果不能保证这一点，那至少要保证当传递同样的参数时所有重载方法的行为必须保持一致
 *
 */
public class OverloadingDemo {
	// 方案二：定义了三个类代替三个方法的重载
	// 对象的运行时类型并不影响“哪个重载版本将被执行”
	// 选择工作是在编译时进行的，完全基于参数的编译时类型
	private static class Wine {
		String name() {
			return "wine";
		}
	}

	private static class SparklingWine extends Wine {
		@Override
		String name() {
			return "sparkling wine";
		}
	}

	private static class Champagne extends SparklingWine {
		@Override
		String name() {
			return "champagne";
		}
	}

	public static void main(String[] args) {
		Wine[] wines = { new Wine(), new SparklingWine(), new Champagne() };
		for (Wine wine : wines)
			System.out.println(wine.name());
	}
	// 方案三：这里通过单个方法替换CollectionClassifier中重载的三个classify方法
	// 这里用了连环三目运算符，可以借鉴一下用法，此外通过instanceof预先判断Collection的子类型
	public static String classify(Collection<?> c) {
		return c instanceof Set ? "Set" : c instanceof List ? "List" : "Unknown Collection";
	}
}

// 方案一：定义了三个不同参数列表的同名方法，但是参数之间存在继承关系，Won't work!
// 选择调用哪个方法是在运行时进行的，运行的时候会选择Collection参数对应的方法执行，因为声明的collections就是Collection的
class CollectionClassifier {
	public static String classify(Set<?> s) {
		return "Set";
	}

	public static String classify(List<?> lst) {
		return "List";
	}

	public static String classify(Collection<?> c) {
		return "Unknown Collection";
	}

	public static void main(String[] args) {
		Collection<?>[] collections = { new HashSet<String>(), new ArrayList<BigInteger>(),
				new HashMap<String, String>().values() };

		for (Collection<?> c : collections)
			System.out.println(classify(c));
	}
}