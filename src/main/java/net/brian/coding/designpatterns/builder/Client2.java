package net.brian.coding.designpatterns.builder;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item02: Consider a builder when faced with many constructor parameters
 * 
 * Point 1：重叠构造器模式：构造器彼此独立，每个构造器参数列表都不一样，可以根据参数的需要订制
 * 缺点：此方法不适合于参数太多的情况，而且参数一多就显得客户端代码可读性极差；
 * Point 2：用javabean的方式处理，可以将参数列表设置成bean中的属性，然后通过setter向里传值
 * 缺点：这种方式就阻止了把类做成不可变的可能，这就需要程序员付出额外的努力确保它的线程安全，而且这种方式还让单元测试变得困难
 * 
 * Builder模式就可以既保证了重叠构造器模式安全性，也保证像javabean模式那样的可读性
 * 
 * item40: Design method signatures carefully
 * 
 * 避免过长的参数列表，比如对于数据库调用的API，如果把查询条件等项都作为参数传进方法中，那么参数列表就很长了
 * 遇到相同类型的参数，比如同是boolean，当你传入两个false到参数列表之后就混乱了，也不知道哪个false代表什么了
 * 
 * 避免过长参数列表的方法：
 * a.把一个方法分解成多个方法，每个方法分摊其中的几个参数
 * b.创建辅助类用来保存参数的分组，这些辅助类一般是静态成员类
 * 如果频繁出现的参数序列可以被看作是代表了某个独特的实体，则建议使用这种方法
 * c.利用Builder模式，@see net.brian.coding.designpatterns.builder.Client2
 *
 */
public class Client2 {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		// Point 1：保证了重叠构造器模式安全性。builder就像一个构造器一样，可以对其参数强加约束条件
		// build方法可以检验这些约束条件，将参数从builder拷贝到对象中之后，在对象域而不是在builder域对其检验
		// 违反了任何约束条件都应该抛出IllegalArgumentException
		// Point 2：每一次传参都是可读的，甚至可以直接根据方法名calories看出传进去的参数100是指的卡路里
		NutritionFactsConcreteBuilder2 builder = new NutritionFactsConcreteBuilder2.Builder2(240, 8).calories(100)
				.sodium(35).carbohydrate(27).build();
	}
}
