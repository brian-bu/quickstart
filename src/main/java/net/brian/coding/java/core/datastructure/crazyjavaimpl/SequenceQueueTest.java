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
public class SequenceQueueTest
{
	public static void main(String[] args)
	{
		SequenceQueue<String> queue = new SequenceQueue<String>();
		//依次将4个元素加入队列
		queue.add("aaaa");
		queue.add("bbbb");
		queue.add("cccc");
		queue.add("dddd");
		System.out.println(queue);
		System.out.println("访问队列的front端元素:" 
			+ queue.element());
		System.out.println("移除队列的front端元素:" 
			+ queue.remove());
		System.out.println("移除队列的front端元素:" 
			+ queue.remove());
		System.out.println("两次调用remove方法后的队列：" 
			+ queue);

	}
}
