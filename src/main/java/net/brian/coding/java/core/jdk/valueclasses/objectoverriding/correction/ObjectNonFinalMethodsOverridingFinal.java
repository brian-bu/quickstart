package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction;

import java.util.ArrayList;
import java.util.List;

/**
 * ֮����Object�������һЩ��final�ķ���������Ϊ���ǿ��Ա����ǵ� �����Ҳ��final���η�����һ�����ã���ֹ���า��
 * ͨ������equals��hashCode����ʵ��Comparable�ӿڵĶ���һЩֵ�࣬ͬ����ֵ�࣬enum���͵�ȴ����Ҫ����equals
 * 
 * ���ڵ�IDE����IntelliJ IDEA�Ѿ������˸�����Щ������ѡ��Ѿ�����Ҫ����Ա�Լ�д������
 * ֮���Ի��о���Щ�����ĸ��ǹ�����Ҫ������Щ�����ı������壬�˽�java��һЩ�ڲ�����ԭ�� ��Щ�������κ�IDE���޷���������ɵ�
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
		// ��ͬʵ�ֵıȽ�
		// ������ΪStudent2������ڼ̳�Student���ʱ��û�����и���equals
		// ���Ե��õ�equals�����õ��Ǹ���Student��equals����
		// ��Student��equals������û�ж�������бȽϵ�
		// ���������Ϣ��Student2�ڼ̳�Student�Ļ����϶�����ӵ�
		// ���������Ȼstudent2ʵ����student3ʵ�������䲻ͬ������ʵ��
		// ���ǱȽϵĽ���Ծɷ���true����Ϊ���ݸ����equals������id��name��sex������ͬ��
		System.out.println(student2.equals(student3));
		// ��ͬʵ�ֵıȽ�
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
