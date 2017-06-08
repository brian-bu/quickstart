package net.brian.coding.algorithm.demo;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 实现的关键是游戏信息的储存。包括玩家座位信息，玩家所报数信息以及密码信息。我们通过自定义单向循环链表Joeph_list存储结构来实现游戏过程的模拟。链表以结点连接。结点Node存储的信息包括每个人手中的密码、每个人的位置以及下一个结点在计算机中的存储位置，及指向下一个结点的指针。值得注意的是，信息“每个人的位置”是必不可少的，因为他不等同于结点在链表中的位置——但一个玩家被移除之后，链表后的元素位置会“前进”，而我们需要的玩家的位置始终是不变的。
玩家的报数，我们通过循环中计数器的递增实现，当顺序递增到链表中最后一个结点，而循环仍没有结束时，我们继续从第一个元素开始递增——及相当于最后一个玩家仍没有报数到m我们就从第一个玩家重头开始报数。直到计数器累加到m，则发现我们要移除的结点，记录并输出移出结点的信息，继续游戏。直到链表中元素被清空，程序结束。
算法的关键是将实际游戏场景抽象到链表中的元素的查找和移除上，要掌握清楚哪些数据代表哪些信息，并熟悉程序运行中各种判断的流程。
 *
 */
public class JosephCycle {
	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in);
		// System.out.print("请输入总人数：");
		// int totalNum = scanner.nextInt();
		// System.out.print("请输入报数的大小：");
		// int cycleNum = scanner.nextInt();
		// System.out.print("请输入开始编号：");
		// int startNO= scanner.nextInt();
		// yuesefu(totalNum, cycleNum,startNO);
		joseph(13, 3, 1);
	}

	public static void joseph(int totalNum, int countNum, int startNO) {
		// 初始化人数
		List<Integer> start = new ArrayList<Integer>();
		for (int i = 1; i <= totalNum; i++) {
			start.add(i);
		}
		// 从下标为K开始计数
		int k = startNO - 1;
		while (start.size() > 0) {
			System.out.println(start);
			// 第m人的索引位置
			k = (k + countNum) % (start.size()) - 1;
			// 判断是否到队尾 到队尾时候k=-1
			if (k < 0) {
				System.out.println(start.get(start.size() - 1));
				start.remove(start.size() - 1);
				k = 0;
			} else {
				System.out.println(start.get(k));
				start.remove(k);
			}
		}
	}
}
