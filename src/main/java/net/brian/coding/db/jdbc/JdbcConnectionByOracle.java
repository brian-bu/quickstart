package net.brian.coding.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * 代码未经连接oracle测试，仅作为示例
 *
 */
public class JdbcConnectionByOracle {
	static String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	static String DBURL = "jdbc:oracle:thin:@localhost:1521:Test";
	static String DBUSER = "scott";
	static String DBPASS = "tiger";

	public Connection testDB() {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Application failed!The class is not found!");
		}
		try {
			Connection conn = DriverManager
					.getConnection(DBURL, DBUSER, DBPASS);
			String str = "SELECT * FROM TAB";
			conn.prepareStatement(str);
			System.out.println(conn);
		} catch (SQLException e) {
			System.out.println("Fail to connect with the database!");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		JdbcConnectionByOracle tj = new JdbcConnectionByOracle();
		tj.testDB();
	}
}
