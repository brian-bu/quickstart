package net.brian.coding.db.redis.string;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 
 * 限制用户操作的频率，比如1分钟内只能发送一次请求，同一个用户的重复请求将被拒绝，这个是不是比token机制靠谱多了？
 *
 */
public class AvoidingFrequentRequest {
	JedisPool pool;
	Jedis jedis;

	public void setUp() {
		pool = new JedisPool(new JedisPoolConfig(), "192.168.1.102", 6379);
		jedis = pool.getResource();
	}
	
	public void avoidingFrequentRequest(String phoneNum) {
		String key = "shortMsg:limit:" + phoneNum;
		// 利用redis原生的功能，设置一个1分钟过期的键，如果键存在那就是还没过期，就是还没有达到1分钟上限
		String ifExists = jedis.set(key, "1", "EX 60"); 
		if(null != ifExists || jedis.incr(key) <= 5) {
			// 通过
		} else {
			// 限制
		}
	}
}
