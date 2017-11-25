package net.brian.coding.db.redis.string;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 许多应用都会使用Redis作为计数的基础工具，它可以实现快速计数、查询缓存的功能，同时数据可以异步落地到其他数据源
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
	 * 核心实现
	 */
	public long incrVideoCounter(long id) {
		String key = "video:playCount:" + id;
		return jedis.incr(key);
	}
}
