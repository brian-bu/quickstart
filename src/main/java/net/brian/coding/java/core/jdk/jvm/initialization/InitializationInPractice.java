package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * 本例作为一个初始化顺序的实战演练，重要的是分析实例变量的初始化的顺序并用序号标注
 * 判断静态和非静态变量初始化顺序的核心思想：
 * 如果你的代码尝试不用实例来访问非static的变量，编译器会报错，因为这些变量还没有被创建出来，还没有跟任何实例关联上
 *
 */
public class InitializationInPractice {
	public static void main(String[] args)
	{
		// Point 1: 通过Price的INSTANCE访问currentPrice实例变量
		// 此次调用时initPrice还没赋值
		System.out.println(Price.INSTANCE.currentPrice);
		// 显式创建Price实例
		Price p = new Price(2.8);
		// Point 2: 通过先是创建的Price实例访问currentPrice实例变量
		// 此次调用时initPrice肯定已经赋值20了
		System.out.println(p.currentPrice);
	}
}
class Price
{
	//类成员是Price实例
	/*(1)*/final static Price INSTANCE = /*(2)*/new Price(2.8);
	//在定义一个类变量。
	/*(5)*/static double initPrice = 20;
	//定义该Price的currentPrice实例变量
	/*(3)*/double currentPrice;
	public Price(double discount)
	{
		//根据静态变量计算实例变量
		/*(4)*/currentPrice = initPrice - discount;
	}
}
