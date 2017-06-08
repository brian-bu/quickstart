package net.brian.coding.java.core.jdk.valueclasses.objectoverriding;

import java.util.Date;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item39: Make defensive copies when needed
 * 
 * 对可变变量或者可变对象的引用进行保护性拷贝，大概有三种类型需要进行保护性拷贝：
 * a.不可变类中的可变域，比如Date，当前类的示例代码就是针对这种情况的示例
 * b.编写方法或者构造器时允许客户提供的对象进入内部数据结构
 * 这时如果无法容忍该对象进入数据结构后的变化就需要提供对该对象的保护性拷贝并使用这个拷贝进入内部数据结构
 * 比如：你的程序用由客户提供的对象的引用作为内部Set实例的元素
 * 或者作为内部Map实例的键，则若这个对象在插入后再被修改，Set或Map的约束条件就会遭到破坏
 * c.内部组件被返回给客户端之前应该进行保护性拷贝，比如长度非零的final数组，具体见：
 * @see net.brian.coding.java.core.jdk.keywords.FinalDemo.FinalClass.myClassArr
 * 
 */
public final class DefensiveCopiesDemo {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Point 1: First attack - start
		Date start = new Date();
		Date end = new Date();
		DefensiveCopiesDemo period = new DefensiveCopiesDemo(start, end);
		// 虽然Period已经是final的了，而且它内部的域也都应该是final的，但是因为Date本身是可以调用API改变的
		// 所以这破坏了Period这个类的封装性，使得period实例仍旧是可以改变的
		end.setYear(1990);
		// First attack - end
		// Point 2: Second attack - start
		period.end().setYear(1990);
		// Second attack - end
	}
	
	private final Date start;
	private final Date end;

	public DefensiveCopiesDemo(Date start, Date end) {
		if (start.compareTo(end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
		this.start = start;
		this.end = end;
	}

	// Repaired constructor - makes defensive copies of parameters - Page 185
	// Point 1: Stops first attack
	// 用了这个构造器之后通过Date的API对Date进行修改不再有效
	// 这里用new Date对象作为备份对象成为Period实例的组件，而不是使用原始对象
	// 同时注意我们并没有用clone进行保护性拷贝，事实上也不建议这样做
	// Date是非final的，不能保证clone方法一定返回专门出于恶意的目的而设计的不可信任的子类实例
	// public Period(Date start, Date end) {
	// this.start = new Date(start.getTime());
	// this.end = new Date(end.getTime());
	//
	// if (this.start.compareTo(this.end) > 0)
	// throw new IllegalArgumentException(start +" after "+ end);
	// }

	public Date start() {
		return start;
	}

	public Date end() {
		return end;
	}

	// Repaired accessors - make defensive copies of internal fields - Page 186
	// Point 2: Stops second attack
	// 因为Period类提供了end()这类的访问方法，这些方法提供了对其可变内部成员的访问能力
	// 因此增强方法也是修改这两个方法使之返回date的保护性拷贝
	// public Date start() {
	// return new Date(start.getTime());
	// }
	//
	// public Date end() {
	// return new Date(end.getTime());
	// }

	public String toString() {
		return start + " - " + end;
	}

	// Remainder omitted
}