package net.brian.coding.java.core.datastructure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * public class LinkedList<E> extends AbstractSequentialList<E> implements
 * List<E>, Deque<E>, Cloneable, java.io.Serializable
 * ����LinkedList�ļ̳���ϵ���Կ����������������˰���ջ�����С��������ڵ����й���
 * �������Ա���������ArrayList��˳��洢����LinkedList����ʽ�洢��������
 * 
 * ԭ�ļ���http://www.w2bc.com/Article/76901
 *
 */
public class LinkedListDemo {
	/**
	 * LinkedList�����б�List��Ϊ�ӿڵĶ�̬
	 */
	public void linkedListAsList() {
		List<String> myList = new LinkedList<String>();
		String s = "myString";
		// ����Ԫ��
		myList.add(s);
		// ��ָ��λ�ò���Ԫ��
		myList.add(1, s);
		// ��ȡָ��λ�õ�Ԫ�أ���ȡ�����11��Ԫ�أ���ͷ����
		@SuppressWarnings("unused")
		String getString = myList.get(10);
		// ɾ��Ԫ�أ�ɾ�������3��Ԫ��
		myList.remove(2);
		// clear�������
		myList.clear();
	}

	/**
	 * LinkedListģ����У�Queue��Ϊ�ӿڵĶ�̬
	 * LinkedListͨ��������β����Ԫ�أ�������ȡ��Ԫ��,ģ�����Ƚ��ȳ�FIFO�Ķ��У���������Ķ����ǵ����
	 */
	@SuppressWarnings("unused")
	public void linkedListAsQueue() {

		Queue<String> myQueue = new LinkedList<String>();
		// ���Ԫ�ص�����β
		String myString = "linkedListAsQueue";
		// ��ӳɹ�����true�����򷵻�false
		myQueue.offer(myString);
		myQueue.add(myString);
		// ��������ɾ������Ԫ�أ���Ϊ�գ�����null
		String head = myQueue.peek();
		// ������Ϊ�գ��׳�NoSuchElementException
		String head1 = myQueue.element();
		// ȡ������ɾ������Ԫ�أ���Ϊ�գ�����null
		String head2 = myQueue.poll();
		// ������Ϊ�գ��׳�NoSuchElementException
		String head3 = myQueue.remove();

	}

	/**
	 * LinkedListģ��ջStack������Deque��Ϊ�ӿڵĶ�̬������Stack
	 * LinkedListͨ���ڶ��ײ���Ԫ�أ�����ȡ��Ԫ�أ�ģ��stack���Ƚ��������
	 */
	public void linkedListAsStack() {
		String myString = "linkedListAsStack";
		Deque<String> stack = new LinkedList<String>();
		// ��ջ����
		stack.push(myString);
		// ��ջ������ɾ������ȡ��
		stack.pop();
		// ���Ǽ�����ɾ������peek
		stack.peek();
	}

	/**
	 * LinkedListģ��˫�˶���Deque������Deque��Ϊ�ӿڵĶ�̬ LinkedListͨ������������׶�β��ʵ����˫�˶���
	 */
	@SuppressWarnings("unused")
	public void linkedListAsDeque() {
		String myString = "linkedListAsStack";
		Deque<String> deque = new LinkedList<String>();
		// �������Ԫ��
		deque.offerFirst(myString);
		deque.addFirst(myString);
		// ��β���Ԫ��
		deque.offerLast(myString);
		deque.addLast(myString);
		// ��������ɾ������Ԫ��
		String first = deque.peekFirst();
		first = deque.getFirst();
		// ��������ɾ����βԪ��
		String last = deque.peekLast();
		last = deque.getLast();
		// ȡ����ɾ������Ԫ��
		deque.pollFirst();
		deque.removeFirst();
		// ȡ����ɾ����βԪ��
		deque.pollLast();
		deque.removeLast();
	}
}
