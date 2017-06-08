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
public class TwoLinkBinTreeTest
{
    public static void main(String[] args) 
    {
        TwoLinkBinTree<String> binTree = new TwoLinkBinTree("根节点");
		//依次添加节点
		TwoLinkBinTree.TreeNode tn1 = binTree.addNode(binTree.root()
			,  "第二层左节点" , true);
		TwoLinkBinTree.TreeNode tn2 = binTree.addNode(binTree.root()
			, "第二层右节点" ,false );
		TwoLinkBinTree.TreeNode tn3 = binTree.addNode(tn2 
			, "第三层左节点" , true);
		TwoLinkBinTree.TreeNode tn4 = binTree.addNode(tn2
			, "第三层右节点" , false);
		TwoLinkBinTree.TreeNode tn5 = binTree.addNode(tn3
			, "第四层左节点" , true);
		System.out.println("tn2的左子节点：" + binTree.leftChild(tn2));
		System.out.println("tn2的右子节点：" + binTree.rightChild(tn2));
		System.out.println(binTree.deep());
    }
}
