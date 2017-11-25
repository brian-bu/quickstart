package net.brian.coding.db.redis.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

	public Connection conn;

	{

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://192.168.255.128:3306/test", "root", "bqy@Beijing02");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}