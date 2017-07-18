package net.brian.coding.java.core.jdk.concurrency.utilities.concurrentcollections;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * ���ֻ��Ҫһ�������Ļ���ɢ�е�ӳ�䣬�Ϳ���ʹ��ConcurrentHashMap�����Ҫ����һ��Mapʵ���ṩ���߲����ķ��ʣ�����ʹ�ö�д����װ����map
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
	}//��remove��putAll��clear����ִ����ͬ����������ʵ����
	public V get(Object key) {
		r.lock();
		try {
			return map.get(key);
		} finally {
			r.unlock();
		}
	}//������ֻ����Map����ִ����ͬ����������ʵ����
}