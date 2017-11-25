package net.brian.coding.db.redis.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class ClientToRedisAndDb {
	MysqlConnection mysql = new MysqlConnection();
	RedisConnection redis = new RedisConnection();
	ResultSet rs = null;

	// 模拟登陆缓存
	@Test
	public void redisLogin() throws SQLException {
		// 正常业务的ID是通过UI的request.getParamenter()获取
		String id = "9028935b527d22cc01527d235aea0142";
		String sql = "select * from user where id_='" + id + "'";
		String username;
		if (redis.hexists("user_" + id, "username_")) {
			username = redis.hget("user_" + id, "username_");
			System.out.println("Welcome Redis! User " + username + " login success");
		} else {
			rs = mysql.conn.createStatement().executeQuery(sql);
			if (rs.next() == false) {
				System.out.println("Mysql no register, Please register first");
			} else {
				username = rs.getString("username_");
				System.out.println("Welcome Mysql ! User " + username + " login success");
				redis.hset("user_" + id, "username_", username);
				// 30分钟未操作就过期
				redis.expire("user_" + id, 1800);
			}
		}
	}
}