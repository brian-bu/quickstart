package net.brian.coding.java.core.datastructure.crazyjavaimpl;

import java.util.*;
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
public class TreeParentTest
{
	public static void main(String[] args) 
	{
		TreeParent<String> tp = new TreeParent<String>("root");
		TreeParent.Node root = tp.root();
		System.out.println(root);
		tp.addNode("节点1" , root);
		System.out.println("此树的深度:" + tp.deep());
		tp.addNode("节点2" , root);
		//获取根节点的所有子节点
		List<TreeParent.Node<String>> nodes = tp.children(root);
		System.out.println("根节点的第一个子节点：" + nodes.get(0));
		//为根节点的第一个子节点新增一个子节点
		tp.addNode("节点3" , nodes.get(0));
		System.out.println("此树的深度:" + tp.deep());
	}
}
