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
 * ����Effective Java��item01��JDBC��һ�����͵ķ����ṩ�߿��
 * �����ṩ�߿��һ����������Ҫ�����
 * ����ӿڣ�����JDBC��Connection�����ṩ�ߣ�����com.mysql.jdbc. Driver���Լ�
 * �ṩ��ע��API������JDBC��DriverManager.registerDriver��������ϵͳ����ע��ʵ�֣��ÿͻ��˷���
 * �������API������DriverManager.getConnection�����ǿͻ���������ȡ�����ʵ������Ҳ�ǡ����ľ�̬������
 * �����ṩ�߿��ģʽ���������ֱ��壬��������API��������������ģʽ�����ر��ṩ����Ҫ�ĸ��ḻ����ӿڡ�
 * 
 * ������Ǽ򵥵��������ݿⲢ�����Եķ���һ����ѯ�����������ͨ��
 * ʵ����Ŀ����Ҫ�����Ŀ�������һ���̶ȵķ�װ�����ﲻչʾ����
 * ����ķ�װʾ���ɲο�������д���顶�ܹ�̽�ա��и�����DatabaseHelper
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
					// 1��ʾ���ݿ���з��ؽ���ĵ�һ�У�������0
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