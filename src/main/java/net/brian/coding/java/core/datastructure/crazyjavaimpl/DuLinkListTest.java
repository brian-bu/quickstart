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
public class DuLinkListTest
{
	public static void main(String[] args) 
	{
		DuLinkList<String> list = new DuLinkList<String>();
		list.insert("aaaa" , 0);
		list.add("bbbb");
		list.insert("cccc" , 0);
		//在索引为1处插入一个新元素
		list.insert("dddd" , 1);
		//输出顺序线性表的元素
		System.out.println(list);
		//删除索引为2处的元素
		list.delete(2);
		System.out.println(list);
		System.out.println(list.reverseToString());
		//获取cccc字符串在顺序线性表中的位置
		System.out.println("cccc在顺序线性表中的位置：" 
			+ list.locate("cccc"));
		System.out.println("链表中索引1处的元素：" 
			+ list.get(1));
		list.remove();
		System.out.println("调用remove方法后的链表:" + list);
		list.delete(0);
		System.out.println("调用delete(0)后的链表:" + list);
	}
}
