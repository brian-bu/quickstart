package net.brian.coding.java.core.jdk.keywords;
/**
 * 
 * switch...case不用像if...else那样遍历条件分支直到命中条件而只需访问对应索引号的表项从而到达定位分支的目的
 * 具体地说，switch...case会生成一份大小（表项数）为最大case常量＋1的跳表来指示实际的case分支的地址
 * 程序首先判断switch变量是否大于最大case 常量，若大于，则跳到default分支处理
 * 否则取得索引号为switch变量大小的跳表项的地址（即跳表的起始地址＋表项大小＊索引号）
 * 程序接着跳到此地址执行，到此完成了分支的跳转。switch有点以空间换时间的意思
 * switch有点类似数组的随机访问，它只能是在常量选择分支且分支较多的时候比ifelse效率高
 * 但是ifelse能应用于更多的场合，ifelse比较灵活。
 * 
 * 此外应注意：
 * switch在绝大多数情况下都不应该省略break
 * 对于if...else还涉及到else if和if...if的区别：前者的条件是if显式条件和else隐式条件的交集
 *
 */
public class DifferSwitchAndIfelse {
	
	// 演示else..if和if...if的区别
	public void elseif() {
		int age = 45;
		if (age > 20) {
			System.out.println("青年人");
		} else if (age > 40) {
			System.out.println("中年人");
		} else if (age > 60) {
			System.out.println("老年人");
		}
	}
	// 演示else..if和if...if的区别
	public void ifif() {
		int age = 45;
		if (age > 20) {
			System.out.println("青年人");
		}
		if (age > 40 && !(age > 20)) {
			System.out.println("中年人");
		} else if (age > 60 && !(age > 20) && (age > 40 && !(age > 20))) {
			System.out.println("老年人");
		}
	}
	
	// 从 Java7 开始，我们可以在switch case中使用字符串，但这仅仅是一个语法糖。内部实现在switch中使用字符串的hashcode。
	// 三句都会打印，用于处理一些需要针对每种情况产生应对方案的，可以用default设置不可预知发生的情况的应对办法
	private void testSwitch(String serverity) {
		switch(serverity) {
		case "critical":
			System.out.println("testSwitch -- critical...");
		case "high":
			System.out.println("testSwitch -- high...");
		case "medium":
			System.out.println("testSwitch -- medium...");
		}
	}
	// 功能上和ifelse�?样了，因为加了break，所以只会执行其中一个满足条件的，然后�?�过break挑出switch
	// 从效率上来讲，switch的底层原理是使用了Binary Tree的算�?
	// switch使用查找表的方式决定了case的条件必须是�?个连续的常量。�?�if-else则可以灵活的�?
	// 此外，当分支较少的时候，switch有跳转表的过程所以显得效率有点低，但是当分支非常多的时�?�跳转表造成的�?�能下降幅度可以忽略，这个时候就比ifelse效率高了
	private void testSwitchBreak(String serverity) {
		switch(serverity) {
		case "critical":
			System.out.println("testSwitchBreak -- critical...");
			break;
		case "high":
			System.out.println("testSwitchBreak -- high...");
			break;
		case "medium":
			System.out.println("testSwitchBreak -- medium...");
			break;
		}
	}
	// if…else if…中的第二个if是在不符合第�?个if的东西中继续找满足条件的东西
	private void testIfelse(String serverity) {
		if("critical".equals(serverity)) {
			System.out.println("testIfelse -- critical...");
		}
		else if("high".equals(serverity)) {
			System.out.println("testIfelse -- high...");
		}
		else if("medium".equals(serverity)) {
			System.out.println("testIfelse -- medium...");
		}
	}
	// 第一个if会筛选掉�?些不符合条件的东西，第二个if在第�?个的基础上继续筛�?
	private void testIfonly(String serverity) {
		if("critical".equals(serverity)) {
			System.out.println("testIfonly -- critical...");
		}
		if("high".equals(serverity)) {
			System.out.println("testIfonly -- high...");
		}
		if("medium".equals(serverity)) {
			System.out.println("testIfonly -- medium...");
		}
	}
//	@Test
	public void testCase() {
		String serverity = "critical";
		testSwitch(serverity);
		testSwitchBreak(serverity);
		testIfelse(serverity);
		testIfonly(serverity);
	}
	public static void main(String[] args) {
		DifferSwitchAndIfelse demo = new DifferSwitchAndIfelse();
		demo.testCase();
	}
}
