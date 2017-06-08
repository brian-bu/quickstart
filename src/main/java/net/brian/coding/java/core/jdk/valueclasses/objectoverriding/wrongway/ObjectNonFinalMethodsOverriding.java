package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
import org.junit.Test;
/**
 * ����Ϊ��ʾ��������������й��ڵȼ۹�ϵ�����ԣ�������Ҫһ����ȷ����equals������Student
 * ����û�����õ�ǰpackage���е�Student�࣬�������Student1�Ĺ��ڸ���equals�ķ������Ǵ��
 * ���Էŵ�wrongway��package��
 */
import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent;


public class ObjectNonFinalMethodsOverriding {
	public void testStudent1Point2() {
		// ǰ��������Ҫȷ��һ���Ա�super.equals����true
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
		// ����Ľ�ѵ�͸�������ˣ�������Ȼ��ͬ�����������ʵ���ȽϽ����ȻҲ��ͬ��Ҳ����true
		// ͨ�����Ǻ��toString��ӡ����������Է��ֶ���ʵ��student1��student2��idֵ����0��name����null��sex����''
		// ����Ҫ����Ϊ�ڶ���Student���ֵ���ʱ���������ع����������ÿ�ε��õĶ���Ĭ�Ϲ�����
		// �����Ѿ����ǵ�equals���߼�����ȵ������Ǿ��в�ͬ��name��age��������û�����ع������ĳ�ʼ������������������Ĭ�ϳ�ʼ��
		// ���������ô�Ƚ�student1��student2�����߼���ͬ��
		WrongValuedClassStudent stu1 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu2 = new WrongValuedClassStudent();
		System.out.println(stu1.equals(stu2));//true
	}
}
