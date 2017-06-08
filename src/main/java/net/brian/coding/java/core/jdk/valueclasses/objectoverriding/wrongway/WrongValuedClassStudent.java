package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
/**
 * 
 * 如果两个对象根据equals(Object)方法比较是相等 那么调用这两个对象中任意一个对象的hashCode方法都必须产生同样的整数结果
 * 因此覆盖equals的同时必须覆盖hashCode
 * 
 * equals和compareTo的实现规则基本相同
 * 
 * 违反了hashCode约定的类会破坏其它依赖于散列做法的类，违反compareTo的约定也会破坏其他依赖于比较关系的类
 * 依赖于散列的类比如HashSet和HashMap
 * 依赖于比较关系的类比如有序集合类TreeMap和TreeSet
 * 相应的依赖于比较关系的还有集合工具类如Collections和Arrays，它们内部包含搜索和排序算法
 * 
 * 此外，对于一个新定义好的具有比较关系的类，应该提供对Comparable接口的实现以便在实际应用中比较对象
 * jdk中所有的值类都具有比较关系，都实现了Comparable接口
 * 对于实现Comparable接口，需要指定相应的泛型，而且这个泛型必须就是该值类
 * 这样编译器在强制你覆盖compareTo方法的时候就会让你使用该值类作为compareTo的参数：
 * class WrongValuedClassStudent implements Comparable<WrongValuedClassStudent>
 * public int compareTo(WrongValuedClassStudent o)
 * class WrongValuedClassStudent implements Comparable<Object>
 * public int compareTo(Object o)
 * 很显然我们实现Comparable就是为了方便比较新定义的值类产生的对象
 * 要是比较的不是Student而是Object那定义这个值类还有什么意义呢？
 * 
 */
public class WrongValuedClassStudent implements Comparable<WrongValuedClassStudent> {

	private String name;
	private double height;
	private boolean ifGraduated;
	private char sex;// 'F' or 'M'
	private float gpa;
	private long timeStamp;

	/**
	 * 这里equals的参数只能是Object，如果换成Object的子类型相当于重载，而非覆盖
	 * 但是在覆盖equals方法的同时，需要把参数转换成正确的类型
	 */
	@Override
	public boolean equals(Object obj) {
		// 使用==操作符检查“参数是否为这个对象的引用”
		if (this == obj)
			return true;
		// 使用instanceof操作符检查“参数是否为正确的类型”
		if (!(obj instanceof WrongValuedClassStudent))
			return false;
		// 把参数转换成正确的类型。
		WrongValuedClassStudent student = (WrongValuedClassStudent) obj;
		// 对于该类中的每个“关键”域，检查参数中的域是否与该对象中对应的域相匹配(以下错误示范)。
		return this.name == student.name && this.sex == student.sex && this.gpa == student.gpa && this.timeStamp == student.timeStamp&& this.height == student.height && this.ifGraduated == student.ifGraduated; 
	}

	@Override
	/**
	 * 不要这样做，这样所有的对象都拥有相同的hash值会产生hash碰撞，导致HashMap存放该值类的元素时退化成链表
	 * @see net.brian.coding.datastructure.HashMapDemo
	 */
	public int hashCode() {
		return 0;
	}
	
	/**
	 * 根据Effective Java上所说：compareTo和equals的实现规则是一致的，所以就不再示范错误的compareTo的实现方式
	 * 仅将正确的实现方式写出来
	 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent.compareTo(RightValuedClassStudent)
	 */
	@Override
	public int compareTo(WrongValuedClassStudent o) {
		return 0;
	}
	
}