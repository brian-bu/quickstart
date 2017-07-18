package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * 如果只需要一个并发的基于散列的映射，就可以使用ConcurrentHashMap，如果要对另一种Map实现提供更高并发的访问，可以使用读写锁包装既有map
 *
 */
public class ReadWriteLockMapInsteadOfConcurrentHashMap {
	public static void main(String[] args) {
		
	}
}
class ReadWriteMap<K, V> {
	private final Map<K, V> map;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();
	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
	}
	public V put(K key, V value) {
		w.lock();
		try {
			return map.put(key, value);
		} finally {
			w.unlock();
		}
	}//对remove、putAll、clear方法执行相同操作，具体实现略
	public V get(Object key) {
		r.lock();
		try {
			return map.get(key);
		} finally {
			r.unlock();
		}
	}//对其它只读的Map方法执行相同操作，具体实现略
}