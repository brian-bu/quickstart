package net.brian.coding.java.core.datastructure.crazyjavaimpl;
     

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class LinkStack<T>
{
	//定义一个内部类Node，Node实例代表链栈的节点。
	private class Node
	{
		//保存节点的数据
		private T data;
		//指向下个节点的引用
		private Node next;
		//无参数的构造器
		public Node()
		{
		}
		//初始化全部属性的构造器
		public Node(T data , Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	//保存该链栈的栈顶元素
	private Node top;
	//保存该链栈中已包含的节点数
	private int size;
	//创建空链栈
	public LinkStack()
	{
		//空链栈，top的值为null
		top = null;
	}
	//以指定数据元素来创建链栈，该链栈只有一个元素
	public LinkStack(T element)
	{
		top = new Node(element , null);
		size++;
	}
	//返回链栈的长度	
	public int length()
	{
		return size;
	}
	//进栈
	public void push(T element)
	{
		//让top指向新创建的元素，新元素的next引用指向原来的栈顶元素
		top = new Node(element , top);
		size++;
	}
	//出栈
    public T pop()
	{
		Node oldTop = top;
		//让top引用指向原栈顶元素的下一个元素
		top = top.next;
		//释放原栈顶元素的next引用
		oldTop.next = null;
		size--;
		return oldTop.data;
	}
	//访问栈顶元素，但不删除栈顶元素
	public T peek()
	{
		return top.data;
	}
	//判断链栈是否为空栈
	public boolean empty()
	{
		return size == 0;
	}
	//清空链栈
	public void clear()
	{
		//将底层数组所有元素赋为null
		top = null;
		size = 0;
	}
	public String toString()
	{
		//链栈为空链栈时
		if (empty())
		{
			return "[]";
		}
		else
		{
			StringBuilder sb = new StringBuilder("[");
			for (Node current = top ; current != null
				; current = current.next )
			{
				sb.append(current.data.toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2 , len).append("]").toString();
		}
	}
}
