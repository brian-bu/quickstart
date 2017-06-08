package net.brian.coding.designpatterns.decorator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item16: Favor composition over inheritance
 * 
 * 对于普通的具体类(concrete class)进行跨越包边界的继承是非常危险的，它打破了封装性
 * 即：子类和超类处在不同包中且超类不是为了继承而设计的，则这种继承会导致程序脆弱
 * 因此考虑组合和转发代替继承，下面通过用组合和继承分别来实现同一个功能需求来验证这一点。
 * 功能需求如下：
 * 为了统计某个使用HashSet添加元素的程序曾经添加了多少元素，设计一个类实现该统计功能
 * 此外当存在适当的接口可以实现包装类的时候，包装类不仅比子类更加健壮，而且功能也更强大
 * 
 * 总之就是为了继承而设计的类在被使用时就考虑继承，子类和父类通常有is-a的关系
 * 否则应该选择组合的方式代替继承，组合通常是两个类之间存在has-a的关系
 *
 * @param <E>
 */
public class CompositionBetterThanInheritance {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Brian", "Sure", "Yanan");
		InstrumentedHashSet1<String> s = new InstrumentedHashSet1<String>();
		s.addAll(list);
		System.out.println("addCount by interitance:: " + s.getAddCount());//4
		InstrumentedSet2<String> s2 = new InstrumentedSet2<String>(new HashSet<String>());
		s2.addAll(list);
		System.out.println("addCount by composition:: " + s2.getAddCount());//3
	}
}

class InstrumentedHashSet1<E> extends HashSet<E> {

	private static final long serialVersionUID = 1L;
	private int addCount = 0;
	public InstrumentedHashSet1() {}
	public InstrumentedHashSet1(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}
	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}
	/**
	 * 由于HashSet的addAll中调用了add，所以这里覆盖之后的addAll也会调用覆盖后的add
	 * 最终导致每次调用addAll添加一个元素，addCount就分别在addAll和add中自增一次，统计结果为：
	 * 每添加一个元素addCount+2.而因为是继承，所以对add和addAll的覆盖几乎是顺势而为理所当然
	 * 这就充分暴露了继承的缺陷，它破坏了HashSet封装好的逻辑，使得这个封装好的逻辑出现了漏洞
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount++;
		return super.addAll(c);
	}
	public int getAddCount() {
		return addCount;
	}
}
/**
 * 
 * 这个类就是一个包装类，可以被用来包装任何Set实现，并且可以结合先前任何的构造器一起工作
 * 这个类对集合进行了修饰并添加计数的特性
 *
 * @param <E>
 */
class InstrumentedSet2<E> extends ForwardingSet<E> {
	private int addCount = 0;

	// 这个构造器使得InstrumentedSet2的每一个实例都把另一个Set实例包裹起来了
	public InstrumentedSet2(Set<E> s) {
		super(s);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}

class ForwardingSet<E> implements Set<E> {
	private final Set<E> s;

	public ForwardingSet(Set<E> s) {
		this.s = s;
	}

	public void clear() {
		s.clear();
	}

	public boolean contains(Object o) {
		return s.contains(o);
	}

	public boolean isEmpty() {
		return s.isEmpty();
	}

	public int size() {
		return s.size();
	}

	public Iterator<E> iterator() {
		return s.iterator();
	}

	public boolean add(E e) {
		return s.add(e);
	}

	public boolean remove(Object o) {
		return s.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return s.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return s.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return s.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return s.retainAll(c);
	}

	public Object[] toArray() {
		return s.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return s.toArray(a);
	}

	@Override
	public boolean equals(Object o) {
		return s.equals(o);
	}

	@Override
	public int hashCode() {
		return s.hashCode();
	}

	@Override
	public String toString() {
		return s.toString();
	}
}