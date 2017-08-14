package net.brian.coding.algorithm.lintcode;

import org.junit.Test;

/**
 * 
 * http://www.lintcode.com/zh-cn/problem/remove-linked-list-elements/
 *
 * ��ԭ����������⣬�������󲻳�����������һ����������Ψһ֪����һ���������LinkedList��
 * �����Ҿ���LinkedList������ṹ�����ף����Ǹ��Ӳ������Ŀ����˼
 * ͨ����������������ı�д���ҲŽ������������������Ľṹ��ͬʱҲ��������Ŀ���߼�
 * �������յ�һ��������ǣ������벻�����Ŀ�������ʱ�򣬾�д��Ԫ����
 * ������Ҫ���Գ����Ƿ���ͨ����ʱ��ҲҪд��Ԫ����
 * ���Ե�ʱ�򣬷�����϶���Ҫд��Ԫ�����ˣ�������д��������Ŀ�����ʵ���벻�����ô���£�
 * ��ô������ĵ�Ԫ����д���ˣ�Ҳ���ֳ�����������Ͻ��ģ����������������Ŀ��
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
	// ��˽�������һ��������ӡ�ڵ㣬ÿ�δ�ӡ��һ���ڵ��ʱ�򶼻�ݹ�Ĵ�ӡ����һ���ڵ㣬ֱ�����Ϊ��
	public String toString() {
		return "ListNode value: " + val + "\n" + "Next node is: " + next + "\n";
	}
}