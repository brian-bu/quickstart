package net.brian.coding.java.core.datastructure.crazyjavaimpl;


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
public class ThreeLinkBinTreeTestO
{
    public static void main(String[] args) 
    {
        ThreeLinkBinTree<String> binTree = new ThreeLinkBinTree("���ڵ�");
		//������ӽڵ�
		ThreeLinkBinTree.TreeNode tn1 = binTree.addNode(binTree.root()
			,  "����" , true);
		ThreeLinkBinTree.TreeNode tn2 = binTree.addNode(binTree.root()
			, "����" ,false );
		ThreeLinkBinTree.TreeNode tn3 = binTree.addNode(tn1 
			, "����" , true);
		ThreeLinkBinTree.TreeNode tn4 = binTree.addNode(tn1
			, "����" , false);
		ThreeLinkBinTree.TreeNode tn5 = binTree.addNode(tn3
			, "����" , false);
		ThreeLinkBinTree.TreeNode tn6 = binTree.addNode(tn5
			, "����" , true);
		ThreeLinkBinTree.TreeNode tn7 = binTree.addNode(tn5
			, "����" , false);
//		System.out.println(binTree.preIterator());
//		System.out.println(binTree.inIterator());
//		System.out.println(binTree.postIterator());
//		System.out.println(binTree.breadthFirst());

    }
}