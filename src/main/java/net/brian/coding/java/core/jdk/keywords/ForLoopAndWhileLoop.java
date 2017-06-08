package net.brian.coding.java.core.jdk.keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 
 * for和foreach的使用方法示例代码
 * 
 * 如果你想使用 Java 中增强的循环来遍历，你只需要实现Iterable接口
 * 对于Map怎么循环呢?可以循环Map的key的集合Entry[]（实现了Iterable）
 * 
 * 如果没有使用Iterator，遍历一个数组的方法是使用索引for或者while循环
 * 这两种方法客户端程序员都必须事先知道集合的内部结构，访问代码和集合本身是紧耦合
 * 无法将访问逻辑从集合类和客户端代码中分离出来，每一种集合对应一种遍历方法，客户端代码无法复用
 * 假设把ArrayList更换为LinkedList，则原来的客户端代码必须全部重写
 * Iterable具体示范详见：
 * @see net.brian.coding.java.core.datastructure.IterableDemo
 * 
 * for循环结构详解：
 * 分号是用来隔开语句的，所以for循环三个语句用分号分隔可以看作是三个语句并列一行写
 * 因此for循环里仅有两个分号是必不可少的，其余的都可以不写：
 * a.初始化语句可以同时初始化多个变量，比如for (int i = 0, j = 0; i < 10; i++)
 * 甚至可以把i提取到for循环外面声明然后第一个分号前什么也不写：for (; i < 10; i++)
 * 这种情况下由于之前int i在for循环内部的时候算初始化也算一次for循环，所以把初始化提取到外面就是少了一次循环
 * b.两个分号之间的部分返回一个boolean逻辑表达式，返回true时for循环才执行下一次
 * c.最后一部分是循环迭代部分，每次循环结束后会执行循环迭代部分
 * 
 * 此外：
 * for循环和while循环的区别：
 * 递增变量一般在循环结束之后都是不可用的，但在和它等价的while循环中
 * 递增变量在循环结束之后仍然可用。这个区别常常是使用while而非for循环的主要原因。
 *
 */
public class ForLoopAndWhileLoop {
	
	public void declareOnce() {
		@SuppressWarnings("unused")
		Object obj = null;
		for (int i = 0; i < 10; i++) {
			obj = new Object();
		}
	}

	public void declareMultipleTimes() {
		for (int i = 0; i < 10; i++) {
			@SuppressWarnings("unused")
			Object obj = new Object();
		}
	}
	// Normal list using for loop enhancement.
	public void demo1() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		for (Object str : list) {
			System.out.println(str);
		}
	}

	// for循环和while迭代
	public void demo2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		Set<String> keySet = map.keySet();
		for (Object obj : keySet) {
			String key = obj.toString();
			String value = (String) map.get(key);
			System.out.println(key + " = " + value);
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println(key + " = " + value);
		}
	}

	// 增强for循环，也就是foreach，仅用于遍历，如果需要获取某个元素，还是要i做索引
	public void demo3() {

		int arrs[] = { 1, 2, 3 };
		for (int arr : arrs) {
			arr = 10;
			System.out.println(arr);
		}
		System.out.println(arrs[0]);
		System.out.println(arrs[1]);
		System.out.println(arrs[2]);

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		// 这里对于循环体内的obj，跳出for循环就不再用了
		// 它只是一个中间变量临时保存了正在遍历的数组或集合元素
		// 因此通常不会对循环变量进行赋值，虽然这样并没有什么语法错误
		for (Object obj : list) {
			obj = "hehe";
			System.out.println(obj);
		}
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}
}
