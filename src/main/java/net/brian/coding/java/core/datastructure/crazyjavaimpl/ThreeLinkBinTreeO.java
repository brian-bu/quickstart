package net.brian.coding.java.core.datastructure.crazyjavaimpl;

import java.util.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ThreeLinkBinTreeO<E>
{
	public static class TreeNode
	{
		Object data;
		TreeNode left;
		TreeNode right;
		TreeNode parent;
		public TreeNode()
		{
		}
		public TreeNode(Object data)
		{
			this.data = data;
		}
		public TreeNode(Object data , TreeNode left
			, TreeNode right , TreeNode parent)
		{
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		public String toString()
		{
			return data.toString();
		}
	}
	private TreeNode root;
	//��Ĭ�ϵĹ�����������������
	public ThreeLinkBinTreeO()
	{
		this.root = new TreeNode();
	}
	//��ָ����Ԫ��������������
	public ThreeLinkBinTreeO(E data)
	{
		this.root = new TreeNode(data);
	}
	/**
	 * Ϊָ���ڵ�����ӽڵ㡣
	 * @param index ��Ҫ����ӽڵ�ĸ��ڵ������
	 * @param data ���ӽڵ������
	 * @param isLeft �Ƿ�Ϊ��ڵ�
	 * @return �����Ľڵ�
	 */
	public TreeNode addNode(TreeNode parent , E data
		, boolean isLeft)
	{
		if (parent == null)
		{
			throw new RuntimeException(parent +
				"�ڵ�Ϊnull���޷�����ӽڵ�");
		}
		if (isLeft && parent.left != null)
		{
			throw new RuntimeException(parent +
				"�ڵ��������ӽڵ㣬�޷�������ӽڵ�");
		}
		if (!isLeft && parent.right != null)
		{
			throw new RuntimeException(parent +
				"�ڵ��������ӽڵ㣬�޷�������ӽڵ�");
		}
		TreeNode newNode = new TreeNode(data);
		if (isLeft)
		{
			//�ø��ڵ��left����ָ���½ڵ�
			parent.left = newNode;
		}
		else
		{
			//�ø��ڵ��left����ָ���½ڵ�
			parent.right = newNode;
		}
		//���½ڵ��parent���õ�parent�ڵ�
		newNode.parent = parent;
		return newNode;
	}
	//�ж϶������Ƿ�Ϊ�ա�
	public boolean empty()
	{
		//���ݸ�Ԫ�����ж϶������Ƿ�Ϊ��
		return root.data == null;
	}
	//���ظ��ڵ㡣
	public TreeNode root()
	{
		if (empty())
		{
			throw new RuntimeException("��Ϊ�գ��޷����ʸ��ڵ�");
		}
		return root;
	}
	//����ָ���ڵ㣨�Ǹ��ڵ㣩�ĸ��ڵ㡣
	public E parent(TreeNode node)
	{
		if (node == null)
		{
			throw new RuntimeException(node +
				"�ڵ�Ϊnull���޷������丸�ڵ�");
		}
		return (E)node.parent.data;
	}
	//����ָ���ڵ㣨��Ҷ�ӣ������ӽڵ㡣�����ӽڵ㲻����ʱ����null
	public E leftChild(TreeNode parent)
	{
		if (parent == null)
		{
			throw new RuntimeException(parent +
				"�ڵ�Ϊnull���޷�����ӽڵ�");
		}
		return parent.left == null ? null : (E)parent.left.data;
	}
	//����ָ���ڵ㣨��Ҷ�ӣ������ӽڵ㡣�����ӽڵ㲻����ʱ����null
	public E rightChild(TreeNode parent)
	{
		if (parent == null)
		{
			throw new RuntimeException(parent +
				"�ڵ�Ϊnull���޷�����ӽڵ�");
		}
		return parent.right == null ? null : (E)parent.right.data;
	}
	//���ظö���������ȡ�
	public int deep()
	{
		//��ȡ���������
		return deep(root); 
	}
	//����һ���ݹ鷽����ÿ�����������Ϊ������������������ + 1
	private int deep(TreeNode node)
	{
		if (node == null)
		{
			return 0;
		}
		//û������
		if (node.left == null
			&& node.right == null)
		{
			return 1;
		}
		else
		{
			int leftDeep = deep(node.left);
			int rightDeep = deep(node.right);
			//��¼���������������нϴ�����
			int max = leftDeep > rightDeep ? 
				leftDeep : rightDeep;
			//���������������нϴ����� + 1
			return max + 1;
		}
	}
	//ʵ���������
	public List<TreeNode> preIterator()
	{
		return preIterator(root);
	}
	private List<TreeNode> preIterator(TreeNode node)
	{
		List<TreeNode> list = new ArrayList<TreeNode>();
		//������ڵ�
		list.add(node);
		//�ݹ鴦��������
		if (node.left != null)
		{
			list.addAll(preIterator(node.left));
		}
		//�ݹ鴦��������
		if (node.right != null)
		{
			list.addAll(preIterator(node.right));
		}
		return list;
	}
	//ʵ���������
	public List<TreeNode> inIterator()
	{
		return inIterator(root);
	}
	private List<TreeNode> inIterator(TreeNode node)
	{
		List<TreeNode> list = new ArrayList<TreeNode>();
		//�ݹ鴦��������
		if (node.left != null)
		{
			list.addAll(inIterator(node.left));
		}
		//������ڵ�
		list.add(node);
		//�ݹ鴦��������
		if (node.right != null)
		{
			list.addAll(inIterator(node.right));
		}
		return list;
	}
	public List<TreeNode> postIterator()
	{
		return postIterator(root);
	}
	//ʵ�ֺ������
	private List<TreeNode> postIterator(TreeNode node)
	{
		List<TreeNode> list = new ArrayList<TreeNode>();
		//�ݹ鴦��������
		if (node.left != null)
		{
			list.addAll(postIterator(node.left));
		}
		//�ݹ鴦��������
		if (node.right != null)
		{
			list.addAll(postIterator(node.right));
		}
		//������ڵ�
		list.add(node);
		return list;
	}
	//������ȱ���
	public List<TreeNode> breadthFirst()
	{
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		if( root != null)
		{
			//����Ԫ�ؼ��롰���С�
			queue.offer(root);
		}
		while(!queue.isEmpty())
		{
			//���ö��еġ���β����Ԫ����ӵ�List��
			list.add(queue.peek());
			TreeNode p = queue.poll();
			//������ӽڵ㲻Ϊnull���������롰���С�
			if(p.left != null)
			{
				queue.offer(p.left);
			}
			//������ӽڵ㲻Ϊnull���������롰���С�
			if(p.right != null)
			{
				queue.offer(p.right);
			}
		}
		return list;
	}
}
