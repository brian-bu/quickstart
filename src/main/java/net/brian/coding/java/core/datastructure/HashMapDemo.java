package net.brian.coding.java.core.datastructure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent;
/**
 * �����Ԥ֪����˳��Ĭ�ϵĲ���˳�򣩣�ֻ�轫HashMapת����һ��LinkedHashMap
 * 
 * Hash��ײ���������ʵ����hashCode����ó�����ͬ��hashֵ���Ҷ��߲���ȣ�����equals�ȽϷ���false
 * ��ô�ͳ���������Ϊ������hash��ײ��������ǲ�����hashֵ��Ϊkey�洢���󣬷���һ������hash��ײ�������������
 * HashMap�ײ���һ��Entry���飬ÿһ��Entry����һ��key-value��ֵ��
 * ���������Hash��ײ��ô���о�����ͬhashֵ��Entry����ŵ�ͬһ������Ԫ��λ�ã����γ�һ��Entry����
 * ��jdk8�������������ȴ���8���Զ�ת����һ����������������
 * 
 * ���Ҫע�⣺�������hashCode��ʱ���returnֵ����Ϊ�̶���ֵ����ô�ͻ���ֶ����е�ʵ����������ͬ��ֵ
 * Ҳ�������е�Ԫ�ض��ᷢ��Hash��ײ����HashMapҲ���˻�����һ������
 * 
 * ����hash��ײ���ڲ��߼�ʵ�������
 * @see java.util.HashMap.putVal(int, K, V, boolean, boolean)
 * 
 * ��������Ҫ���߼������ǣ�
 * if (p.hash == hash &&  ((k = p.key) == key || (key != null && key.equals(k))))
        e = p;
    else if (p instanceof TreeNode)
        e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
    else {
 * ����˵���ǣ�
 * ���ж��Ƿ�����mapԪ�ص�key��map���Ѿ�������hashֵҲ��ȣ�����ǣ���������ͬ��Ԫ���ظ�����
 * Ȼ���ж��Ƿ��Ƿ������ڵ㣬�����ǵ�HashMap�������hash��ײ��ͬһ��Entry����������ȴ���8�Զ�תΪ��
 * ����������������������У�Ҳ����˵hashֵ��ͬ��key��ͬ������ײ������ײ��Ԫ�ػ��������ڵ㣬�����else��
 */
public class HashMapDemo {
	@Test
	public void testHashConflict() {
		WrongValuedClassStudent stu1 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu2 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu3 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu4 = new WrongValuedClassStudent();
		WrongValuedClassStudent stu5 = new WrongValuedClassStudent();
		//FIXME: HashMap��hash��ײdebug
		Map<String, WrongValuedClassStudent> map = new HashMap<String, WrongValuedClassStudent>();
		map.put("name1", stu1);
		map.put("name2", stu2);
		map.put("name3", stu3);
		map.put("name4", stu4);
		map.put("name5", stu5);
		// ������ΪWrongValuedClassStudent�ڸ���hashCode��ʱ�򷵻�ֵȫ������Ϊ0���������涼���0
		System.out.println("map.get(name1).hashCode():: " + map.get("name1").hashCode());//0
		// ��ʵ�����Ƿ����hashCode��������һ���ģ���Ϊ��ֵ��û�и���toString����
		// Ĭ�ϴӸ���Object�̳�����toString����Ĭ�ϴ�ӡ���Ƕ����hashֵ
		// Object.toStringԴ�룺return getClass().getName() + "@" + Integer.toHexString(hashCode());
		// ���Դ��ͷǳ���Ϥ�ˣ��ǲ�С�Ĵ�ӡ��һ��û�и���toString������ֵ��Ķ���ʱ�ܻ���ֵ�����
		System.out.println("stu1.hashCode():: " + stu1.hashCode());//0
		// ��������û�и���toString������������������ڿ���̨�����ʱ���������hashCode
		// �����stu1:: net.brian.coding.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent@0
		System.out.println("stu1:: " + stu1);
	}
	
	// HashMap��put�������з���ֵ�ģ������ص���key֮ǰ��value
	// ���֮ǰ��value��null�������key֮ǰ�����ڣ���ô�ͷ���null���ٷ��������£�
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
	//FIXME: HashMap������Ĺؼ�����������ʹ�õ��� ArrayList �� remove() ���� Iterator �� remove()����
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
