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
public class ThreeLinkBinTreeTest
{
    public static void main(String[] args) 
    {
        ThreeLinkBinTree<String> binTree = new ThreeLinkBinTree("根节点");
		//依次添加节点
		ThreeLinkBinTree.TreeNode tn1 = binTree.addNode(binTree.root()
			,  "第二层左节点" , true);
		ThreeLinkBinTree.TreeNode tn2 = binTree.addNode(binTree.root()
			, "第二层右节点" ,false );
		ThreeLinkBinTree.TreeNode tn3 = binTree.addNode(tn2 
			, "第三层左节点" , true);
		ThreeLinkBinTree.TreeNode tn4 = binTree.addNode(tn2
			, "第三层右节点" , false);
		ThreeLinkBinTree.TreeNode tn5 = binTree.addNode(tn3
			, "第四层左节点" , true);
		System.out.println("tn2的父节点：" + binTree.parent(tn2));
		System.out.println("tn2的左子节点：" + binTree.leftChild(tn2));
		System.out.println("tn2的右子节点：" + binTree.rightChild(tn2));
		System.out.println(binTree.deep());
    }
}
