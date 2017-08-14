package net.brian.coding.algorithm.lintcode;

import org.junit.Test;

/**
 * 
 * http://www.lintcode.com/zh-cn/problem/remove-linked-list-elements/
 *
 * 我原本不理解题意，根本想象不出这是怎样的一个链表，而我唯一知道的一个链表就是LinkedList了
 * 所以我就用LinkedList的链表结构往上套，但是更加不理解题目的意思
 * 通过对这个测试用例的编写，我才渐渐的认清了这个链表的结构，同时也理清了题目的逻辑
 * 这里掌握的一条经验就是：当你想不清楚题目的用意的时候，就写单元测试
 * 当你想要测试程序是否能通过的时候，也要写单元测试
 * 面试的时候，反正你肯定是要写单元测试了，可以先写，至于题目，如果实在想不清楚怎么回事，
 * 那么至少你的单元测试写对了，也表现出你编码风格是严谨的，而且你是理解了题目的
 * 
 */
public class RemoveLinkedListElements {
	/**
	 * @param head
	 *            a ListNode
	 * @param val
	 *            an integer
	 * @return a ListNode
	 */
	public ListNode removeElements(ListNode head, int val) {
		// Write your code here
		if (head == null)
			return head;
		ListNode p = head, q = head.next;
		while (q != null) {
			if (q.val == val) {
				p.next = q.next;
				q = q.next;
			} else {
				p = p.next;
				q = q.next;
			}
		}
		if (head.val == val)
			head = head.next;
		return head;
	}
	
	@Test
	public void testRemoveElements() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(4);
		ListNode node6 = new ListNode(5);
		ListNode node7 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		System.out.println(node1);
		System.out.println(removeElements(node1, 3));
	}
	
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	// 我私自添加了一个方法打印节点，每次打印下一个节点的时候都会递归的打印再下一个节点，直至最后为空
	public String toString() {
		return "ListNode value: " + val + "\n" + "Next node is: " + next + "\n";
	}
}