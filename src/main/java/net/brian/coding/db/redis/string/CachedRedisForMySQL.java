package net.brian.coding.db.redis.string;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import net.brian.coding.db.jdbc.JdbcConnectionByMysql;
import net.brian.coding.db.redis.ProtostuffSerializerUtil;
import net.brian.coding.java.utils.BeanUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 将Redis作为缓存层，MySQL作为数据存储，绝大部分请求的数据都是从Redis中获取。可以加速读写，降低后端压力
 * 
 * 本功能可以分别使用字符串和哈希两种方式实现
 *
 */
public class CachedRedisForMySQL {
	JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.102", 6379);
	Jedis jedis = pool.getResource();

	/**
	 * 哈希方式实现
	 * 
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public UserInfo getUserInfoByHash(long id)
			throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		UserInfo userInfo = null;
		String userRedisKey = "user:info:" + id;
		Map<String, String> userInfoMap = jedis.hgetAll(userRedisKey);
		if (null != userInfoMap) {
			userInfo = (UserInfo) BeanUtil.convertMap(UserInfo.class, userInfoMap);
		} else {
			// 如果redis没有获取到信息就从mysql数据库获取
			try {
				userInfo = getUserInfoByIdFromMySQL(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 将userInfo变为映射关系存入redis
			if (userInfo != null) {
				jedis.hmset(userRedisKey, BeanUtil.convertBean(userInfo));
				jedis.expire(userRedisKey, 3600);
			}
		}
		return userInfo;
	}

	/**
	 * 字符串方式实现
	 */
	public UserInfo getUserInfoByString(long id) {
		UserInfo userInfo = null;
		String userRedisKey = "user:info:" + id;
		String value = jedis.get(userRedisKey);
		if (value != null) {
			// 将值进行反序列化为UserInfo并返回结果
			userInfo = deserialize(value);
		} else {
			// 如果redis没有获取到信息就从mysql数据库获取
			try {
				userInfo = getUserInfoByIdFromMySQL(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 将userInfo序列化存入redis
			if (userInfo != null)
				jedis.setex(userRedisKey, 3600, serialize(userInfo));
		}
		return userInfo;
	}

	private UserInfo deserialize(String value) {
		final byte[] bytes = value.getBytes();
		UserInfo userInfo = ProtostuffSerializerUtil.deserialize(bytes, schema.newMessage(), schema);
		return userInfo;
	}

	private Schema<UserInfo> schema = RuntimeSchema.createFrom(UserInfo.class);

	private String serialize(UserInfo userInfo) {
		final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		String result = ProtostuffSerializerUtil.serialize(userInfo, schema, buffer).toString();
		buffer.clear();
		return result;
	}

	private UserInfo getUserInfoByIdFromMySQL(long id) throws SQLException {
		JdbcConnectionByMysql demo = new JdbcConnectionByMysql();
		Connection conn = demo.getConnection();
		Map<Long, UserInfo> userInfoMap = null;
		UserInfo userInfo = null;
		ResultSet rs = null;
		PreparedStatement stmt;
		if (conn != null) {
			String sql = "select * from demo_ssm_student where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(id));
			rs = stmt.executeQuery();
			if (rs != null) {
				userInfoMap = new HashMap<Long, UserInfo>();
				while (rs.next()) {
					// 1表示数据库表中返回结果的第一列，而不是0
					// demo.setId(rs.getInt(1));
					// demo.setName(rs.getString(2));
					// list.add(demo);
				}
			}
		}
		demo.close();
		return userInfo;
	}
}

class UserInfo {
}