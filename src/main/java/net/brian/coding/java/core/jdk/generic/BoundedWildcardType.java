package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item28: Use bounded wildcards to increase API flexibility
 * 
 * 请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。
 * a.生产者使用extends：如果你需要一个列表提供T类型的元素，即你想从列表中读取T类型的元素
 * 你需要把这个列表声明成<? extends T>，比如List<? extends Integer>因此你不能往该列表中添加任何元素
 * b.消费者使用super： 如果需要一个列表使用T类型的元素，即你想把T类型的元素加入到列表
 * 你需要把这个列表声明成<? super T>，比如List<? super Integer>因此你不能保证从中读取到的元素的类型
 * c.即是生产者，也是消费者：你不能使用泛型通配符声明列表，比如List<Integer>
 * @see java.util.Collections.copy(List<? super T>, List<? extends T>)
 * 一条通用的规则：所有的comparable和comparator都是消费者
 * 
 * 总之：为了使程序获得最大灵活性，要在表示生产者或者消费者的输入参数上使用通配符类型
 * 
 * 注：本例讲的是有限制的通配符类型
 * 对于无限制通配符类型：
 * @see net.brian.coding.java.core.jdk.generic.RawTypeDisadvantages
 *
 */
public class BoundedWildcardType {
	// Double和Number的关系Double extends Number

	/**
	 * <? extends T>表示包括T在内的任何T的子类 
	 * ? extends T的示例：只要是Number或者Number的子类就可以放到这个容器List里去
	 * 根据PECS原则，extends只能读取，不能添加，super不能保证从中读取到的元素的类型
	 */
	@Test
	public void testExtendT() {
		List<? extends Number> list1 = new ArrayList<Number>();
		List<? extends Number> list2 = new ArrayList<Double>();
		Double d = new Double("1.00");
		// Compiler err，根据PECS原则，extends只能读取，不能添加，super不能保证从中读取到的元素的类型
		// list1.add(d);
		// list2.add(d);
		System.out.println("Output list1.size():: " + list1.size());
		System.out.println("Output list2.size():: " + list2.size());
	}

	/**
	 * <? super T>表示包括T在内的任何T的父类
	 *  ? super T的示例：只要是Double或者Double的父类就可以放到这个容器List里去
	 *  根据PECS原则，extends只能读取，不能添加，super不能保证从中读取到的元素的类型
	 */
	@Test
	public void testSuperT() {
		List<? super Double> list1 = new ArrayList<Double>();
		List<? super Double> list2 = new ArrayList<Number>();
		double d = 1.00;
		// 自动装箱
		list1.add(d);
		list2.add(d);
		// 根据PECS原则，extends只能读取，不能添加，super不能保证从中读取到的元素的类型
		System.out.println(list1.get(0) instanceof Double);
		System.out.println("Output list1.size():: " + list1.size());
		System.out.println("Output list2.size():: " + list2.size());
	}
}
