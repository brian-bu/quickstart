package net.brian.coding.java.core.datastructure;

public class LinkedArrayListDemo<T> {
	private class Node {
		private T data;
		private Node next;

		public Node() {

		}

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private final int DEFAULT_SIZE = 16;
	private int capacity;
	private Object[] elementData;
	
	private Node header;
	private Node tail;
	private int size = 0;

	public LinkedArrayListDemo() {
		header = null;
		tail = null;
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}

	public LinkedArrayListDemo(T element) {
		this();
		header = new Node(element, null);
		tail = header;
		elementData[0] = element;
		size++;
	}
}
