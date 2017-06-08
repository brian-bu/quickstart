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
public class SequenceStackTest
{
	public static void main(String[] args) 
	{
		SequenceStack<String> stack =
			new SequenceStack<String>();
		//不断地入栈
		stack.push("aaaa");
		stack.push("bbbb");
		stack.push("cccc");
		stack.push("dddd");
		System.out.println(stack);
		//访问栈顶元素
		System.out.println("访问栈顶元素：" + stack.peek());
		//弹出一个元素
		System.out.println("第一次弹出栈顶元素：" + stack.pop());
		//再次弹出一个元素
		System.out.println("第二次弹出栈顶元素：" + stack.pop());
		System.out.println("两次pop之后的栈：" + stack);
	}
}
