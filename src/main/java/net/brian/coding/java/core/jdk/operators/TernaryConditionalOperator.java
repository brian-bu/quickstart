package net.brian.coding.java.core.jdk.operators;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 三目运算符使用方法和注意事项：
 * 
 * 三目运算符要求第二三位的类型统一：要么都是基本类型要么都是包装类型
 * 如果第二，第三位操作数分别为基本类型和对象时，其中的对象就会拆箱为基本类型进行操作
 * 
 * 综上：所以使用三目运算符要尽量保证使用包装类型而不是基本类型
 * 
 * 以下代码还有对三目运算符的连环使用示例：
 * @see net.brian.coding.java.core.oop.OverloadingDemo.classify(Collection<?>)
 *
 */
public class TernaryConditionalOperator {
	
	public static void main(String[] args) {
		Map<String,Boolean> map =  new HashMap<String, Boolean>();
		// NPE原因：由于使用了三目运算符，并且第二、第三位操作数分别是基本类型和对象
		// 所以对对象进行拆箱操作，由于该对象为null
		// 所以在拆箱过程中调用null.booleanValue()的时候就报了NPE
		// hashmap.get(“test”)->null;
		// (Boolean)null->null;
		// null.booleanValue()->报错
		Boolean b1 = (map!=null ? map.get("test") : false);
		// 更正：确保第二三位都是对象类型就不会
		Boolean b2 = (map!=null ? map.get("test") : Boolean.FALSE);
	}
}
