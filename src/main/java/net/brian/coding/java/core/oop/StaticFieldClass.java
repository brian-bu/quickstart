package net.brian.coding.java.core.oop;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 演示“持有当前类的实例”：
 * 只要不在当前类StaticFieldClass构造器的初始化代码里形成递归调用，这个类就是安全的
 * 此外，持有当前类的其它实例也需要小心，因为也容易造成递归调用
 *
 */
public class StaticFieldClass {
	
	public static void main(String[] args) {
		StaticFieldClass staticFieldClass1 = new StaticFieldClass(1);
		System.out.println(staticFieldClass1.size());
		
		StaticFieldClass staticFieldClass2 = new StaticFieldClass(2);
		StaticFieldClass staticFieldClass3 = new StaticFieldClass(3);
		System.out.println(staticFieldClass2.size());
		System.out.println(staticFieldClass3.size());
	}
	
	private final int id;
	private static List<StaticFieldClass> list = new ArrayList<StaticFieldClass>();

	public StaticFieldClass(int id) {
		this.id = id;
		list.add(this);
	}

	public int size() {
		if (null == list)
			return 0;
		return list.size();
	}

	public String toString() {
		return "Entrance " + id;
	}
}
