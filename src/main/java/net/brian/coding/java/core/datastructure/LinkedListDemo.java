package net.brian.coding.java.core.datastructure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * public class LinkedList<E> extends AbstractSequentialList<E> implements
 * List<E>, Deque<E>, Cloneable, java.io.Serializable
 * 根据LinkedList的继承体系可以看出：它几乎包揽了包括栈、队列、链表在内的所有功能
 * 对于线性表，基本就是ArrayList（顺序存储）和LinkedList（链式存储）的天下
 * 
 * 原文见：http://www.w2bc.com/Article/76901
 *
 */
public class LinkedListDemo {
	/**
	 * LinkedList用作列表，List作为接口的多态
	 */
	public void linkedListAsList() {
		List<String> myList = new LinkedList<String>();
		String s = "myString";
		// 增加元素
		myList.add(s);
		// 在指定位置插入元素
		myList.add(1, s);
		// 获取指定位置的元素，获取链表第11处元素，从头计算
		@SuppressWarnings("unused")
		String getString = myList.get(10);
		// 删除元素，删除链表第3个元素
		myList.remove(2);
		// clear清空链表
		myList.clear();
	}

	/**
	 * LinkedList模拟队列，Queue作为接口的多态
	 * LinkedList通过在链表尾插入元素，链表首取出元素,模拟了先进先出FIFO的队列，但是这里的队列是单向的
	 */
	@SuppressWarnings("unused")
	public void linkedListAsQueue() {

		Queue<String> myQueue = new LinkedList<String>();
		// 添加元素到到队尾
		String myString = "linkedListAsQueue";
		// 添加成功返回true，否则返回false
		myQueue.offer(myString);
		myQueue.add(myString);
		// 检索但不删除队首元素，若为空，返回null
		String head = myQueue.peek();
		// 若队列为空，抛出NoSuchElementException
		String head1 = myQueue.element();
		// 取出并且删除队首元素，若为空，返回null
		String head2 = myQueue.poll();
		// 若队列为空，抛出NoSuchElementException
		String head3 = myQueue.remove();

	}

	/**
	 * LinkedList模拟栈Stack操作，Deque作为接口的多态而不是Stack
	 * LinkedList通过在队首插入元素，队首取出元素，模拟stack的先进后出操作
	 */
	public void linkedListAsStack() {
		String myString = "linkedListAsStack";
		Deque<String> stack = new LinkedList<String>();
		// 进栈操作
		stack.push(myString);
		// 出栈操作，删除并且取出
		stack.pop();
		// 若是检索不删除则还用peek
		stack.peek();
	}

	/**
	 * LinkedList模拟双端队列Deque操作，Deque作为接口的多态 LinkedList通过操作链表队首队尾就实现了双端队列
	 */
	@SuppressWarnings("unused")
	public void linkedListAsDeque() {
		String myString = "linkedListAsStack";
		Deque<String> deque = new LinkedList<String>();
		// 队首添加元素
		deque.offerFirst(myString);
		deque.addFirst(myString);
		// 队尾添加元素
		deque.offerLast(myString);
		deque.addLast(myString);
		// 检索但不删除队首元素
		String first = deque.peekFirst();
		first = deque.getFirst();
		// 检索但不删除队尾元素
		String last = deque.peekLast();
		last = deque.getLast();
		// 取出并删除队首元素
		deque.pollFirst();
		deque.removeFirst();
		// 取出并删除队尾元素
		deque.pollLast();
		deque.removeLast();
	}
}
