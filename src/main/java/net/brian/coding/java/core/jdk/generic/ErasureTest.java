package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item25: Prefer lists to arrays
 * 
 * 数组的协变性和泛型的不变性
 * 
 * 泛型擦除：
 * 当把一个具有泛型信息的对象赋值给另一个没有泛型信息的变量时，尖括号中的泛型信息就会被擦除扔掉
 * 泛型转换：
 * 当把一个没有泛型信息的对象赋值给另一个泛型信息的变量时，不会发生报错，会自动转换
 * 
 * 擦除就是使泛型可以与没有使用泛型的代码随意进行互用
 * 
 */
public class ErasureTest
{
	/**
	 * 泛型是不可变的，即List<String>不是List<Object>的子类型
	 * 数组却是协变的，即String[]是Object[]的子类型
	 * 这可以说是泛型安全保证的又一个体现，泛型总是能在编译的时候发现错误
	 * 同时也暴露出数组表示类型时的问题，没有泛型很多错误都得等到运行的时候才能发现
	 */
	public void testCovariantAndInvariant() {
		// 数组的协变性示例
		Object[] objArr = new Long[1];
		objArr[0] = "I don't fit in!";//Throws ArrayStoreException
		
		// 泛型的不可变性示例
		// List<Object> ol = new ArrayList<Long>();//Compiler err
		// ol.add("I don't fit in!");
	}
	
	/**
	 * 泛型和数组的另一个区别是：
	 * 数组是具体化的，在运行时才知道并检查它们的元素类型约束
	 * 泛型是通过擦除来实现的，因此泛型在编译时强化它们的类型，运行时擦除元素类型信息
	 * 
	 */
	@SuppressWarnings("unused")
	public void testErasure()
	{
		Apple<Integer> a = new Apple<Integer>(6);
		//a的getSize方法返回Integer对象
		Integer as = a.getSize();
		//把a对象赋给Apple变量，会丢失尖括号里的类型信息
		@SuppressWarnings("rawtypes")
		Apple b = a;
		//b只知道size的类型是Number
		Number size1 = b.getSize();
		//下面代码引起编译错误
		// Integer size2 = b.getSize();
	}
	
	/**
	 * 数组和泛型是不兼容的，不能一起使用
	 *  也即：不能定义泛型数组，因为泛型是通过擦除实现的，List<String>运行时类型是List
	 *  如果真的有泛型数组，则List<String>[]运行时类型应该是List[]
	 *  定义了一个List<Integer>在运行的时候变成了List也可以放进List[]中去，就会产生ClassCastException
	 *  上述情况举例如下：本例无法通过编译，因此通过注释形式演示，对于其运行时类型通过代码演示
	 */
	public void noGenericArrays() {
		// List<String>[] stringLists = new List<String>[1];
		// List<Integer> intList = Arrays.asList(42);
		// Object[] objects = stringLists;
		// objects[0] = intList;
		// String s = stringLists[0].get(0);
	}
}

class Apple<T extends Number>
{
	T size;
	public Apple()
	{
	}
	public Apple(T size)
	{
		this.size = size;
	}
	public void setSize (T size)
	{
		this.size = size;
	}
	public T getSize()
	{
		 return this.size;
	}
}