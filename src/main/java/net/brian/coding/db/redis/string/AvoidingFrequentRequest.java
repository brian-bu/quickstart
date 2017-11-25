package net.brian.coding.db.redis.string;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 
 * �����û�������Ƶ�ʣ�����1������ֻ�ܷ���һ������ͬһ���û����ظ����󽫱��ܾ�������ǲ��Ǳ�token���ƿ��׶��ˣ�
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
		// ����redisԭ���Ĺ��ܣ�����һ��1���ӹ��ڵļ�������������Ǿ��ǻ�û���ڣ����ǻ�û�дﵽ1��������
		String ifExists = jedis.set(key, "1", "EX 60"); 
		if(null != ifExists || jedis.incr(key) <= 5) {
			// ͨ��
		} else {
			// ����
		}
	}
}
