package net.brian.coding.java.core.datastructure.crazyjavaimpl;

import java.util.Arrays;
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
public class LoopQueueTest
{
	public static void main(String[] args)
	{
		LoopQueue<String> queue 
			= new LoopQueue<String>("aaaa" , 3);
		//添加两个元素
		queue.add("bbbb");
		queue.add("cccc");
		//此时队列已满
		System.out.println(queue);
		//删除一个元素后，队列可以再多加一个元素
		queue.remove();
		System.out.println("删除一个元素后的队列：" + queue);
		//再次添加一个元素，此时队列又满
		queue.add("dddd");
		System.out.println(queue);
		System.out.println("队列满时的长度：" + queue.length());
		//删除一个元素后，队列可以再多加一个元素
		queue.remove();
		//再次加入一个元素，此时队列又满
		queue.add("eeee");
		System.out.println(queue);
	}
}
