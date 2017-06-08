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
 * ������ͨ�ľ�����(concrete class)���п�Խ���߽�ļ̳��Ƿǳ�Σ�յģ��������˷�װ��
 * ��������ͳ��ദ�ڲ�ͬ�����ҳ��಻��Ϊ�˼̳ж���Ƶģ������ּ̳лᵼ�³������
 * ��˿�����Ϻ�ת������̳У�����ͨ������Ϻͼ̳зֱ���ʵ��ͬһ��������������֤��һ�㡣
 * �����������£�
 * Ϊ��ͳ��ĳ��ʹ��HashSet���Ԫ�صĳ�����������˶���Ԫ�أ����һ����ʵ�ָ�ͳ�ƹ���
 * ���⵱�����ʵ��Ľӿڿ���ʵ�ְ�װ���ʱ�򣬰�װ�಻����������ӽ�׳�����ҹ���Ҳ��ǿ��
 * 
 * ��֮����Ϊ�˼̳ж���Ƶ����ڱ�ʹ��ʱ�Ϳ��Ǽ̳У�����͸���ͨ����is-a�Ĺ�ϵ
 * ����Ӧ��ѡ����ϵķ�ʽ����̳У����ͨ����������֮�����has-a�Ĺ�ϵ
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
	 * ����HashSet��addAll�е�����add���������︲��֮���addAllҲ����ø��Ǻ��add
	 * ���յ���ÿ�ε���addAll���һ��Ԫ�أ�addCount�ͷֱ���addAll��add������һ�Σ�ͳ�ƽ��Ϊ��
	 * ÿ���һ��Ԫ��addCount+2.����Ϊ�Ǽ̳У����Զ�add��addAll�ĸ��Ǽ�����˳�ƶ�Ϊ������Ȼ
	 * ��ͳ�ֱ�¶�˼̳е�ȱ�ݣ����ƻ���HashSet��װ�õ��߼���ʹ�������װ�õ��߼�������©��
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
 * ��������һ����װ�࣬���Ա�������װ�κ�Setʵ�֣����ҿ��Խ����ǰ�κεĹ�����һ����
 * �����Լ��Ͻ��������β���Ӽ���������
 *
 * @param <E>
 */
class InstrumentedSet2<E> extends ForwardingSet<E> {
	private int addCount = 0;

	// ���������ʹ��InstrumentedSet2��ÿһ��ʵ��������һ��Setʵ������������
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