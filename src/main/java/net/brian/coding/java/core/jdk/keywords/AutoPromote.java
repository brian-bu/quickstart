package net.brian.coding.java.core.jdk.keywords;

/**
 * 基本类型涉及到自动类型转换，转换等级图如下：
 * 
 * 			char  --> 
 * 					| --> int --> long --> float --> double
 * byte --> short -->
 * 
 * 注意一：
 * E1 op= E2 型的复合赋值表达式等价于 E1 = (T)((E1) op (E2))。比如：
 * +=隐式的将加操作的结果类型强制转换为持有结果的类型
 * 如果两这个整型相加，如byte、short或者int，首先会将它们提升到int类型，然后在执行加法操作
 * 如果加法操作的结果比a的最大值要大，则 a+b会出现编译错误，但 a += b没问题
 * short x = 3;x += 4.6;x结果为7，因为它等价于short x = 3;x = (short)(x + 4.6);
 * 但是对于int i = 5;long j = 8;则i+=j可以，i=i+j;则无法通过编译:Type mismatch: cannot convert from long to int
 * 因为i += j; 实际上是i = (type of i) (i + j)的简写，而根据上面的类型转换图，int可以强转long，long不可以强转int
 * 
 * 注意二：
 * 可以将int强制转换成byte，但是 Java中int是 32位的，而byte是8位的，所以强制转化时int类型的高 24位将会被丢弃
 *
 */
public class AutoPromote
{
	public static void main(String[] args) 
	{
		//定义一个short类型变量
		@SuppressWarnings("unused")
		short sValue = 5;
		//表达式中的sValue将自动提升到int类型，则右边的表达式类型为int
		//将一个int类型赋给short类型的变量将发生错误。
		// sValue = sValue - 2;
		byte b = 40;
		char c = 'a';
		int i = 23;
		double d = .314;
		//右边表达式中在最高等级操作数为d（double型）
		//则右边表达式的类型为double型,故赋给一个double型变量
		double result = b + c + i * d;
		//将输出144.222
		System.out.println(result);
		int val = 3;
		//右边表达式中2个操作数都是int，故右边表达式的类型为int
		//因此，虽然23/3不能除尽，依然得到一个int整数
		int intResult = 23 / val;
		//将输出7
		System.out.println(intResult);
		//程序自动将7、'a'等基本类型转换为字符串，输出字符串Hello!a7
		System.out.println("Hello!" + 'a' + 7);
		//程序将把'a'当成int处理，因此'a' + 7得到104，输出字符串104Hello!
		System.out.println('a' + 7 + "Hello!");
	}
}
