package net.brian.coding.java.core.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent;

public class ArrayListDemo {
	List<String> defaultSizeList = new ArrayList<String>();
	List<String> definedSizeList = new ArrayList<String>(20);
	List<WrongValuedClassStudent> studentList = new ArrayList<WrongValuedClassStudent>();
	
	// ÿһ�������඼���ṩ�ѱ��������ת��Ϊ��ǰ������Ĺ�������ֻҪ������ת���������ഫ�������������Ϳ�����
	public static <T> List<T> createArrayListFromArray(T[] arr) {
		return new ArrayList<T>(Arrays.asList(arr));
	}
	
	public static void main(String[] args) {
		String[] str = {};
		createArrayListFromArray(str);
	}
	
//	@Test
	public void test() {
		System.out.println("ArrayListDemo -- test() -- definedSizeList.size():: " + definedSizeList.size());
		Object[] arrayBeforeAdd = definedSizeList.toArray();
		 /**
		  * Src code in jdk for elementData:
	      * The array buffer into which the elements of the ArrayList are stored.
	      * The capacity of the ArrayList is the length of this array buffer. 
	      * Any empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
	      * will be EXPANDED to DEFAULT_CAPACITY when the FIRST element is added.
	      * 
	      * private static final int DEFAULT_CAPACITY = 10;
	      * 
	      * transient Object[] elementData;
	      */
		/**
		 * The src code of toArray in ArrayList:
		 * public Object[] toArray() {
		 *    return Arrays.copyOf(elementData, size);
		 * }
		 * When the ArrayList was built, it didn't contain any data, so the size is empty,
		 * However the size of elementData is 
		 */
		System.out.println("ArrayListDemo -- test() -- toArray before add:: " + Arrays.toString(arrayBeforeAdd));
		definedSizeList.add(0, "Brian");
		Object[] arrayAfterAdd = definedSizeList.toArray();
		System.out.println("ArrayListDemo -- test() -- toArray after add:: " + Arrays.toString(arrayAfterAdd));
	}
	
	// ������ArrayList��contains��������һ��equals
		// ��Ϊcontains�������ڲ��ǵ���equals��������Ԫ���жϵ�
		// �������equalsĬ����Object��equals��������������֤һ�£�
		// �ǲ���������Student��ΪArrayList�ķ��ͣ���ôcontains���Զ�����Student���ǵ�equals�����ˣ�
		// ��֤�������Ǵ���һ��Student��ArrayList���ڸ��ǵ�equals�����д�ӡһ����Ϣ��Ȼ�����contains
		// �����������̨�������֤�����õ��Ǹ��Ǻ��equals�ˡ�
	// ���������Ҫ������ܹ��ŵ�ArrayList�����е�ֵ�࣬��Ĭ�ϵ����Լ����Ǻ��equals���������ֵ��û�и��ǣ���ô�͵���Object�е�
	// ���������ΪʲôҪ�ڶ���ֵ���ʱ�򸲸�equals�����ˣ�Ҳ���Ϊʲô��ArrayList�����Ԫ�ص�ʱ�����ָ��������
	@Test
		public void testArrayListContains() {
//			WrongValuedClassStudent student1 = new WrongValuedClassStudent("Brian", 201314, 'M');
//			WrongValuedClassStudent student2 = new WrongValuedClassStudent("Sure", 131420, 'F');
		WrongValuedClassStudent student1 = new WrongValuedClassStudent();
		WrongValuedClassStudent student2 = new WrongValuedClassStudent();
			// ������ȫ�ֵı�����ʹ��ǰȷ��������list
			// ˳��ѧϰһ��clear����������������Ǳ�������ArrayList��������Ԫ���ÿ�
			studentList.clear();
			studentList.add(student1);
			if(studentList.contains(student2)) {
				System.out.println("Hello student1, welcome to the list!");
			}
		}
}
