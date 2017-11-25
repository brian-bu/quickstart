package net.brian.coding.algorithm.lintcode.datastructure;

import java.util.EmptyStackException;

public class StackByArray {
	public static void main(String[] args) {
		MyArray<String> myArr = new MyArray<String>();
		myArr.push("Hello");
		myArr.peek();
		myArr.pop();
	}
}

class MyArray<E> {
	private Object[] e;
	private int capacity;
	private int top = -1;
	public MyArray() {
		this.capacity = 10;
		e = new Object[capacity];
	}
	public MyArray(int capacity) {
		this.capacity = capacity;
		e = new Object[capacity];
	}
	@SuppressWarnings("unchecked")
	public E peek() {
		if(top == -1) throw new EmptyStackException();
		return (E) e[top];
	}

	public void push(E obj) {
		e[++top] = obj;
	}
	
	public int size() {
		return e.length;
	}

	public E pop() {
		if(top == -1) throw new EmptyStackException();
		E obj = peek();
		int len = size();
		int j = top - 1;
        if (j > 0) {
            System.arraycopy(e, len + 1, e, len, j);
        }
        System.out.println(top++);
        e[--top] = null;
		return obj;
	}
}
