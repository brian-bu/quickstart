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
public class ArrayBinTreeTest
{
	public static void main(String[] args) 
	{
		ArrayBinTree<String> binTree = 
			new ArrayBinTree<String>(4, "根");
		binTree.add(0 , "第二层右子节点" , false);
		binTree.add(2 , "第三层右子节点" , false);
		binTree.add(6 , "第四层右子节点" , false);
		System.out.println(binTree);
	}
}
