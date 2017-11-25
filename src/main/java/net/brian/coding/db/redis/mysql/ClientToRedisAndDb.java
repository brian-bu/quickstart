package net.brian.coding.db.redis.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class ClientToRedisAndDb {
	MysqlConnection mysql = new MysqlConnection();
	RedisConnection redis = new RedisConnection();
	ResultSet rs = null;

	// ģ���½����
	@Test
	public void redisLogin() throws SQLException {
		// ����ҵ���ID��ͨ��UI��request.getParamenter()��ȡ
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
				// 30����δ�����͹���
				redis.expire("user_" + id, 1800);
			}
		}
	}
}