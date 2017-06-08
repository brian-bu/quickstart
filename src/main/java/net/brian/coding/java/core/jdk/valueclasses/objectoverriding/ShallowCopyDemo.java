package net.brian.coding.java.core.jdk.valueclasses.objectoverriding;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item11: Override clone judiciously
 * 
 * 本例主要是示范Object.clone方法实现的浅克隆
 * 本例也将示范用Object的clone方法的基础上对引用类型做特殊处理从而实现深克隆
 * 此外还示范了java.util.HashMap.putAll(Map<? extends K, ? extends V>)是浅克隆
 * 
 * 先来介绍一些基础概念：
 * 深复制浅复制也即深拷贝浅拷贝，也即shallow clone和deep clone
 * 
 * 浅复制：只复制对象的引用，两个引用仍然指向同一个对象，在内存中占用同一块内存
 * 被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象
 * 换言之，浅复制仅仅复制所考虑的对象，而不复制它所引用的对象
 * 
 * 深复制：被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量
 * 那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象
 * 换言之，深复制把要复制的对象所引用的对象都复制了一遍
 * 
 * java.lang.Object的clone()方法将创建一个拷贝返回给调用者
 * 因为Object类的clone()方法通过创建新对象来生成这个副本的，然后逐个域拷贝（field-by-filed）
 * 类似于赋值操作，这种操作对于原始类型（primitives）和不可变类型（immutable）来说是没问题的
 * 如果你的类包含一些可变的数据结构如：ArrayList或数组就不合适了
 * 这种情况原始对象和副本对象将指向相同的堆，为确保每一个可变的域被独立的克隆就需要深度克隆
 * 深拷贝本质就是对引用类型做特殊处理
 * 
 * 此外注意：
 * a.调用clone()方法的对象所属的类必须自行实现Cloneable接口以指示 Object.clone()方法可以合法地对该类实例进行按字段复制
 * 否则在调用Clone方法的时候会抛出CloneNotSupportedException
 * b.Cloneable接口实际上是个标识接口，没有任何接口方法，对于标记接口的说明见：
 * @see net.brian.coding.java.core.oop.classesinterfaces.annotation.MarkerAnnotationAndMarkerInterface
 * c.对于String这样的不可变对象不应该提供clone方法或者保护性构造器
 * 因为拷贝这些对象的结果始终等于原始对象，但String早期并没有意识到这一点仍旧包含clone，尽量不要使用
 * d.非final的类的实例，不能保证clone方法一定返回该类的对象，可能返回专门出于恶意的目的而设计的不可信任的子类实例
 * 因此对于参数类型可以被不可信任方子类化的参数，不要使用clone方法进行保护性拷贝
 * 
 * 对于深度拷贝还可以参考保护性编写readObject这种方法
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DeepCopyDemo
 * 
 */
public class ShallowCopyDemo implements Cloneable {
	
	private Date hireDay;

	/**
	 * HashMap的putAll仅对基本数据类型起到深拷贝的作用，而对引用类型只会拷贝其引用
	 */
	@Test
	public void deepCopyByHashMap() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(200);

		HashMap<String, Object> map = new HashMap<String, Object>();
		// 放基本类型数据
		map.put("basic", 100);
		// 放对象
		map.put("list", list);

		HashMap<String, Object> mapNew = new HashMap<String, Object>();
		mapNew.putAll(map);
		System.out.println("Show results after putAll:: " + mapNew);
		// 更改基本类型的数值没有影响到mapNew的对象，说明对基本类型发生了深拷贝
		map.put("basic", 200);
		System.out.println("Show results after changing primitive type for putAll:: " + mapNew);
		// 更改引用类型的数值影响到mapNew的对象，说明对引用类型发生了浅拷贝
		list.add(300);
		System.out.println("Show results after changing reference type for putAll:: " + mapNew);

		mapNew = DeepCopyDemo.deepCopy(map);
		list.add(400);
		// 这次才是同时作用于基本类型和引用类型的深拷贝
		System.out.println("Show results after changing reference type for deepCopy:: " + mapNew);
	}

	/**
	 * 深度克隆的本职就是在Object的clone方法的基础上对引用类型做特殊处理
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ShallowCopyDemo cloned = (ShallowCopyDemo)super.clone();  
	    cloned.hireDay = (Date)hireDay.clone();
	    return cloned;
	}
	
	//TODO: 编程验证：对于参数类型可以被不可信任方子类化的参数，不要使用clone方法进行保护性拷贝
}
