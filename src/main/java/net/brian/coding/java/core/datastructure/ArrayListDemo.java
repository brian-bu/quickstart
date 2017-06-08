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
	
	// 每一个容器类都会提供把别的容器类转换为当前容器类的构造器，只要把数组转换成容器类传进这个构造器里就可以了
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
	
	// 我们用ArrayList的contains方法测试一下equals
		// 因为contains方法的内部是调用equals方法进行元素判断的
		// 但是这个equals默认是Object的equals，我们这里来验证一下：
		// 是不是声明了Student作为ArrayList的泛型，那么contains就自动调用Student覆盖的equals方法了？
		// 验证方法就是创建一个Student的ArrayList，在覆盖的equals方法中打印一行信息，然后调用contains
		// 这样如果控制台有输出就证明调用的是覆盖后的equals了。
	// 因此我们需要清楚，能够放到ArrayList容器中的值类，都默认调用自己覆盖后的equals方法，如果值类没有覆盖，那么就调用Object中的
	// 因此你就清楚为什么要在定义值类的时候覆盖equals方法了，也清楚为什么向ArrayList中添加元素的时候最好指明泛型了
	@Test
		public void testArrayListContains() {
//			WrongValuedClassStudent student1 = new WrongValuedClassStudent("Brian", 201314, 'M');
//			WrongValuedClassStudent student2 = new WrongValuedClassStudent("Sure", 131420, 'F');
		WrongValuedClassStudent student1 = new WrongValuedClassStudent();
		WrongValuedClassStudent student2 = new WrongValuedClassStudent();
			// 这里是全局的变量，使用前确保清空这个list
			// 顺便学习一下clear方法，这个方法就是遍历整个ArrayList并将所有元素置空
			studentList.clear();
			studentList.add(student1);
			if(studentList.contains(student2)) {
				System.out.println("Hello student1, welcome to the list!");
			}
		}
}
