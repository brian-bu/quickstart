package net.brian.coding.java.core.datastructure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent;
/**
 * 如果想预知迭代顺序（默认的插入顺序），只需将HashMap转换成一个LinkedHashMap
 * 
 * Hash碰撞：如果两个实例经hashCode计算得出了相同的hash值，且二者不相等，即用equals比较返回false
 * 那么就称这种现象为发生了hash碰撞，因此我们不能用hash值作为key存储对象，否则一旦碰见hash碰撞的情况就尴尬了
 * HashMap底层是一个Entry数组，每一个Entry都是一个key-value键值对
 * 如果发生了Hash碰撞那么所有具有相同hash值的Entry将会放到同一个数组元素位置，并形成一个Entry链表
 * 在jdk8，如果这个链表长度大于8则自动转换成一个红黑树来提高性能
 * 
 * 因此要注意：如果覆盖hashCode的时候把return值设置为固定的值，那么就会出现对所有的实例都返回相同的值
 * 也就是所有的元素都会发生Hash碰撞，而HashMap也就退化成了一个链表
 * 
 * 关于hash碰撞的内部逻辑实现详见：
 * @see java.util.HashMap.putVal(int, K, V, boolean, boolean)
 * 
 * 其中最重要的逻辑代码是：
 * if (p.hash == hash &&  ((k = p.key) == key || (key != null && key.equals(k))))
        e = p;
    else if (p instanceof TreeNode)
        e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
    else {
 * 这里说的是：
 * 先判断是否新增map元素的key在map中已经存在且hash值也相等，如果是，则属于相同的元素重复插入
 * 然后判断是否是否是树节点，即考虑到HashMap如果发生hash碰撞且同一个Entry里面的链表长度大于8自动转为树
 * 最后，如果上述两种情况都不中，也就是说hash值相同但key不同发生碰撞，而碰撞的元素还不是树节点，则进入else块
 */
public class HashMapDemo {
	@Test
	public void testHashConflict() {
		WrongValuedClassStudent stu1 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu2 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu3 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu4 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu5 = new WrongValuedClassStudent();
		//FIXME: HashMap：hash碰撞debug
		Map<String, WrongValuedClassStudent> map = new HashMap<String, WrongValuedClassStudent>();
		map.put("name1", stu1);
		map.put("name2", stu2);
		map.put("name3", stu3);
		map.put("name4", stu4);
		map.put("name5", stu5);
		// 这里因为WrongValuedClassStudent在覆盖hashCode的时候返回值全都设置为0，所以下面都输出0
		System.out.println("map.get(name1).hashCode():: " + map.get("name1").hashCode());//0
		// 其实这里是否调用hashCode方法都是一样的，因为该值类没有覆盖toString方法
		// 默认从父类Object继承来的toString方法默认打印的是对象的hash值
		// Object.toString源码：return getClass().getName() + "@" + Integer.toHexString(hashCode());
		// 这段源码就非常熟悉了，是不小心打印了一个没有覆盖toString方法的值类的对象时总会出现的尴尬
		System.out.println("stu1.hashCode():: " + stu1.hashCode());//0
		// 这里由于没有覆盖toString方法，导致这个对象在控制台输出的时候输出的是hashCode
		// 输出：stu1:: net.brian.coding.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent@0
		System.out.println("stu1:: " + stu1);
	}
	
	// HashMap的put方法是有返回值的，它返回的是key之前的value
	// 如果之前的value是null或者这个key之前不存在，那么就返回null，官方解释如下：
	// the previous value associated with key, or null if there was no mapping for key
	public void testPutReturn() {
		Map<Integer, String> stringValueMap = new HashMap<Integer, String>();
		Map<Integer, Boolean> booleanValueMap = new HashMap<Integer, Boolean>();

		String oldA = stringValueMap.put(1, "A");
		String newA = stringValueMap.put(1, "B");
		Boolean oldB = booleanValueMap.put(2, true);
		Boolean newB = booleanValueMap.put(2, false);

		System.out.println("HashMapDemo -- testPutReturn() -- oldA:: " + oldA);
		System.out.println("HashMapDemo -- testPutReturn() -- newA:: " + newA);
		System.out.println("HashMapDemo -- testPutReturn() -- oldB:: " + oldB);
		System.out.println("HashMapDemo -- testPutReturn() -- newB:: " + newB);
	}

//	@Test
	//FIXME: HashMap：问题的关键在于面试者使用的是 ArrayList 的 remove() 还是 Iterator 的 remove()方法
	// Using HashMap. Throw java.util.ConcurrentModificationException
	public void testFailFast() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String str = "Hello world and hello kitty!";
		// String to char: toCharArray()
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// char to String: String.valueOf(char)
			map.put(i, String.valueOf(c[i]));
		}
		System.out.println("HashMapDemo -- testFailFast() -- map.size():: " + map.size());
		// Entry maintain the key and map maintain the entrySet.
		for (Entry<Integer, String> t : map.entrySet()) {
			map.remove(t.getKey());
			// Exception throw
		}
	}

//	@Test
	// Using ConcurrentHashMap
	public void testAgainstFailFast() {
		Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
		String str = "Hello world and hello kitty!";
		// String to char: toCharArray()
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// char to String: String.valueOf(char)
			map.put(i, String.valueOf(c[i]));
		}
		System.out.println("HashMapDemo -- testAgainstFailFast() -- map.size():: " + map.size());
		// Entry maintain the key and map maintain the entrySet.
		for (Entry<Integer, String> t : map.entrySet()) {
			map.remove(t.getKey());
			// No Exception throw
		}
		System.out.println("HashMapDemo -- testAgainstFailFast() -- map.size():: " + map.size());
	}
//	@Test
	public void testAnotherFailFast() {
		Map<Integer, String> map = new Hashtable<Integer, String>();
		String str = "Hello world and hello kitty!";
		// String to char: toCharArray()
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// char to String: String.valueOf(char)
			map.put(i, String.valueOf(c[i]));
		}
		System.out.println("HashMapDemo -- testAnotherFailFast() -- map.size():: " + map.size());
		// Entry maintain the key and map maintain the entrySet.
		for (Entry<Integer, String> t : map.entrySet()) {
			map.remove(t.getKey());
			// Exception throw
		}
	}
}
