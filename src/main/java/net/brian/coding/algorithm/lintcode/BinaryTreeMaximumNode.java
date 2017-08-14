package net.brian.coding.algorithm.lintcode;

import org.junit.Test;

/**
 * 
 * http://www.lintcode.com/zh-cn/problem/binary-tree-maximum-node/
 *
 */
public class BinaryTreeMaximumNode {
	/**
	 * @param root
	 *            the root of binary tree
	 * @return the max ndoe
	 */
	public TreeNode maxNode2(TreeNode root) {
		// Write your code here
		int flag = root.val;
		TreeNode result = null;
		TreeNode leftNode = root.left;
		TreeNode rightNode = root.right;
		if (null == leftNode && null == rightNode) {
			result = root;
		} else {

		}
		return result;
	}

	int minNum = Integer.MIN_VALUE;
	TreeNode maxNode = null;

	public TreeNode maxNode(TreeNode root) {
		// Write your code here
		if (null == root)
			return null;
		if (root.val > minNum) {
			minNum = root.val;
			maxNode = root;
		}
		maxNode(root.left);
		maxNode(root.right);
		return maxNode;
	}

	@Test
	public void testMaxNode() {
		TreeNode node1 = new TreeNode(0, null, null);
		TreeNode node2 = new TreeNode(3, null, null);
		TreeNode node3 = new TreeNode(-4, null, null);
		TreeNode node4 = new TreeNode(-5, null, null);
		TreeNode node5 = new TreeNode(-5, node1, node2);
		TreeNode node6 = new TreeNode(2, node3, node4);
		TreeNode node7 = new TreeNode(1, node5, node6);
		System.out.println(maxNode(node7));
	}
}

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
		this.val = val;
	}
}