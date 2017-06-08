package net.brian.coding.java.core.datastructure;

import java.util.Iterator;

import org.junit.Test;
/**
 * 
 * List一族或者Set一族，都是实现了Iterable接口，但并不直接实现Iterator接口，而是通过内部类实现
 * ArrayList是基于数组，LinkedList是基于链表的两种完全不一样的数据结构
 * 所有具体迭代器实例是各自集合类内部实现Iterator接口来实现。并通过集合类实现iterable接口来想外提供这个接口。
 *
 */
public class IterableDemo {
	@Test
	// Classes those implement Iterable will be iterable by for-each.
	public void testForeach() {

	}
}

class NewType implements Iterable<Object> {
	@Override
	public Iterator<Object> iterator() {
		return null;
	}

}
