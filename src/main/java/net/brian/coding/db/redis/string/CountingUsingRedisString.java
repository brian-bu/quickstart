package net.brian.coding.db.redis.string;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * ���Ӧ�ö���ʹ��Redis��Ϊ�����Ļ������ߣ�������ʵ�ֿ��ټ�������ѯ����Ĺ��ܣ�ͬʱ���ݿ����첽��ص���������Դ
 *
 */
public class CountingUsingRedisString {
	JedisPool pool;
	Jedis jedis;

	public void setUp() {
		pool = new JedisPool(new JedisPoolConfig(), "192.168.1.102", 6379);
		jedis = pool.getResource();
	}

	/*
	 * ����ʵ��
	 */
	public long incrVideoCounter(long id) {
		String key = "video:playCount:" + id;
		return jedis.incr(key);
	}
}
