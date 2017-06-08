package net.brian.coding.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item01: Consider static factory methods instead of constructors
 * 
 * 根据Effective Java的item01，JDBC是一个典型的服务提供者框架
 * 服务提供者框架一般有三个重要组件：
 * 服务接口（比如JDBC的Connection）、提供者（比如com.mysql.jdbc. Driver）以及
 * 提供者注册API（比如JDBC的DriverManager.registerDriver），这是系统用来注册实现，让客户端访问
 * 服务访问API（比如DriverManager.getConnection），是客户端用来获取服务的实例，它也是“灵活的静态工厂”
 * 服务提供者框架模式有着无数种变体，如服务访问API可以利用适配器模式，返回比提供者需要的更丰富服务接口。
 * 
 * 这里就是简单的连接数据库并象征性的返回一条查询结果，经测试通过
 * 实际项目中需要针对项目需求进行一定程度的封装，这里不展示出来
 * 具体的封装示例可参考：黄勇写的书《架构探险》中给出的DatabaseHelper
 * @see net.brian.coding.java.utils.apache.DatabaseHelperByDbUtils
 *
 */
public class JdbcConnectionByMysql {
	private final String DBDRIVER = "com.mysql.jdbc.Driver";
	private final String DBURL = "jdbc:mysql://localhost:3306/finalsql";
	private final String DBUSER = "root";
	private final String DBPASSWORD = "bqy@Home2016";
	// Connection is a service interface right here.
	private Connection conn = null;

	public JdbcConnectionByMysql() {
		try {
			// This Driver class here is the service provider interface.
			Class.forName(DBDRIVER);
			// public static Connection getConnection(String url, String user, String password)
			// We have many other getConnection()s for service access api.
			// DriverManager, as a provider registration api, will implement
			// Connection interface in different ways.
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		JdbcConnectionByMysql demo = new JdbcConnectionByMysql();
		Connection conn = demo.getConnection();
		if(conn != null) {
			String sql = "select * from demo_ssm_student where id=?";
			List<StudentForJdbc> stu  = demo.fetchResultSetById(sql, "50");
			if(stu != null) {
				// Output: Student list size for id=50:: 1
				System.out.println("Student list size for id=50:: " + stu.size());
			}
		}
		demo.close();
	}

	public List<StudentForJdbc> fetchResultSetById(String sql, String param) {
		if(sql == null || "".equals(sql)) return null;
		StudentForJdbc demo = new StudentForJdbc();
		ResultSet rs = null;
		PreparedStatement stmt;
		List<StudentForJdbc> list = Collections.emptyList();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, param);
			rs = stmt.executeQuery();
			if(rs != null) {
				list = new ArrayList<StudentForJdbc>();
				while(rs.next()) {
					// 1表示数据库表中返回结果的第一列，而不是0
					demo.setId(rs.getInt(1));
					demo.setName(rs.getString(2));
					list.add(demo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
class StudentForJdbc {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}