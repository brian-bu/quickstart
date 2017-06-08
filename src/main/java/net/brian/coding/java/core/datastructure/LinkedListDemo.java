package net.brian.coding.java.core.datastructure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
	private static void synchronizeLinkedListInsteadOfStack() {
		List<String> insteadOfStack = new LinkedList<String>();
		Collections.synchronizedCollection(insteadOfStack);
	}

	public static void main(String[] args) {
		synchronizeLinkedListInsteadOfStack();
	}
}
