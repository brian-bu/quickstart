package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
import org.junit.Test;
/**
 * 这里为了示范面向对象语言中关于等价关系的特性，所以需要一个正确覆盖equals方法的Student
 * 所以没有引用当前package就有的Student类，但是这个Student1的关于覆盖equals的方法仍是错的
 * 所以放到wrongway的package下
 */
import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent;


public class ObjectNonFinalMethodsOverriding {
	public void testStudent1Point2() {
		// 前两个参数要确保一致以便super.equals返回true
		RightValuedClassStudent student1 = new RightValuedClassStudent("Brian", 'M');
		WrongValuedClassStudent1 student2 = new WrongValuedClassStudent1("Brian", 'M', 23);
		System.out.println("student1.equals(student2):: " + student1.equals(student2));//true
		System.out.println("student2.equals(student1):: " + student2.equals(student1));//false
	}
	@Test
	public void testStudent1Point3() {
		WrongValuedClassStudent1 student1 = new WrongValuedClassStudent1("Brian", 'M', 27);
		RightValuedClassStudent student2 = new RightValuedClassStudent("Brian", 'M');
		WrongValuedClassStudent1 student3 = new WrongValuedClassStudent1("Brian", 'M', 23);
		System.out.println("student1.equals(student2):: " + student1.equals(student2));//true
		System.out.println("student2.equals(student3):: " + student2.equals(student3));//true
		System.out.println("student1.equals(student3):: " + student1.equals(student3));//false
	}
	public void testEqualsWithoutOverloadedConstructor() {
		// 这里的教训就更加深刻了：两个截然不同的子类产生的实例比较结果居然也相同，也返回true
		// 通过覆盖后的toString打印两个对象可以发现对于实例student1和student2：id值都是0，name都是null，sex都是''
		// 这主要是因为在定义Student这个值类的时候忘了重载构造器，因此每次调用的都是默认构造器
		// 根据已经覆盖的equals的逻辑，相等的条件是具有不同的name和age，而由于没有重载构造器的初始化，这两个变量都是默认初始化
		// 因此无论怎么比较student1和student2都是逻辑相同的
		WrongValuedClassStudent stu1 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu2 = new WrongValuedClassStudent();
		System.out.println(stu1.equals(stu2));//true
	}
}
