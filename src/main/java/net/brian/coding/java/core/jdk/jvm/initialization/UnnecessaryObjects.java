package net.brian.coding.java.core.jdk.jvm.initialization;

import java.util.HashMap;
import java.util.Map;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item05: Avoid creating unnecessary objects
 * 
 * a.要优先使用基本类型而不是装箱类型，要当心无意识的自动装箱
 * b.如果对象是不可变的，那么它就始终可以被重用，所以没必要每次使用的时候创建一个新的实例
 * c.比如String和Integer这类的final类，对象是不可变的，不需要构造器初始化，用直接量赋值最好
 * 
 * 对于String的直接量和Integer的直接量还是不一样的：
 * 这涉及到了String常量池和Integer常量池的有关问题，另有代码示范：
 * @see net.brian.coding.java.core.pool.ConstantPoolForString
 * 
 * 这里item05主要说的是：当你应该重用现有对象时，请不要创建新的对象
 * 对应的item39说的是：当你应该创建新对象时请不要重用现有对象
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DefensiveCopiesDemo
 * 
 * item49: Prefer primitive types to boxed primitives
 * 
 * 当在一项操作中混合使用基本类型和装箱类型时，装箱类型就会自动拆箱，如果null对象引用被自动拆箱就会得到NPE
 * 这个情况之前在三目运算符的时候也出现过：
 * @see net.brian.coding.java.core.jdk.operators.TernaryConditionalOperator
 * 当然包装类型也不是一无是处。以下是适合包装类型的情况，仅这些情况需要使用包装类型，其余均应使用基本类型：
 * a.作为集合的元素、键、值
 * b.参数化类型（泛型）中必须使用装箱类型作为类型参数
 * c.反射的方法调用时必须用装箱类型
 * 
 */
public class UnnecessaryObjects {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// String unnecessaryObject1 = "Hello world!"; This is enough.
		String unnecessaryObject1 = new String("Hello world!");//DO NOT DO THIS!
		// int is not big enough during calculation so we need long.
		// But we should use long instead of Long during declaration.
		// Autoboxing will low the performance.
		// item49
		Long unnecessaryObject2 = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			unnecessaryObject2 += i;
		}

		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("UnnecessaryObjects -- If keySet duplicated:: " + (map.keySet() == map.keySet()));
	}
}