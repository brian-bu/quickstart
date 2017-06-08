package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction;

import java.util.ArrayList;
import java.util.List;

/**
 * 之所以Object里面存在一些非final的方法，是因为它是可以被覆盖的 因此这也是final修饰方法的一个作用：禁止子类覆盖
 * 通常覆盖equals、hashCode并且实现Comparable接口的都是一些值类，同样是值类，enum类型的却不需要覆盖equals
 * 
 * 现在的IDE比如IntelliJ IDEA已经集成了覆盖这些方法的选项，已经不需要程序员自己写代码了
 * 之所以还研究这些方法的覆盖规则是要掌握这些方法的本质意义，了解java的一些内部机制原理 这些东西是任何IDE都无法帮助你完成的
 * 
 */
public class ObjectNonFinalMethodsOverridingFinal {
	RightValuedClassStudent student1 = new RightValuedClassStudent("Brian", 'M');
	RightValuedClassStudent1 student2 = new RightValuedClassStudent1("Sure", 'F', 23);
	RightValuedClassStudent1 student3 = new RightValuedClassStudent1("Sure", 'F', 24);
	RightValuedClassStudent student4 = new RightValuedClassStudent2("Sure", 'F', 23);
	RightValuedClassStudent student5 = new RightValuedClassStudent2("Sure", 'F', 24);
	List<RightValuedClassStudent> studentList = new ArrayList<RightValuedClassStudent>();
	public void testOverridingEquals() {
		// 相同实现的比较
		// 这里因为Student2这个类在继承Student类的时候没有自行覆盖equals
		// 所以调用的equals方法用的是父类Student的equals方法
		// 而Student的equals方法是没有对年龄进行比较的
		// 年龄这个信息是Student2在继承Student的基础上额外添加的
		// 因此这里虽然student2实例和student3实例是年龄不同的两个实例
		// 但是比较的结果仍旧返回true，因为根据父类的equals方法，id、name、sex都是相同的
		System.out.println(student2.equals(student3));
		// 不同实现的比较
		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student1.equals(student2));
		System.out.println(student2.equals(student1));
	}
	
	public void testOverridingHashCode() {
		studentList.add(student1);
		studentList.add(student4);
		studentList.add(student5);
	}
	
	public void testToString() {
		System.out.println(student1);
	}
	
	public void testStudent1Equals() {
		System.out.println("student1.equals(student2):: " + student3.equals(student2));//false
		System.out.println("student2.equals(student1):: " + student2.equals(student3));//false
		
		System.out.println("student1.equals(student2):: " + student1.equals(student2));//false
		System.out.println("student2.equals(student3):: " + student2.equals(student3));//false
		System.out.println("student1.equals(student3):: " + student1.equals(student3));//false
	}
	
	public static void main(String[] args) {
		ObjectNonFinalMethodsOverridingFinal demo = new ObjectNonFinalMethodsOverridingFinal();
		demo.testStudent1Equals();
//		demo.testOverridingEquals();
//		demo.testOverridingHashCode();
//		demo.toString();
	}
}
