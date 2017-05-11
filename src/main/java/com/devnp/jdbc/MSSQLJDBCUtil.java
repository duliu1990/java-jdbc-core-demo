package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLJDBCUtil {
	
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=mloes-dev"; //连接URL
	private static String USER_NAME = "sa"; 		// 数据库用户名
	private static String PASSWORD = "!qaz2wsx"; 	// 数据库密码

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = getConnection() ;
		
		if(connection == null)
			System.out.println("Get MSSQL JDBC Connection Failed.");
		else
			System.out.println("Get MSSQL JDBC Connection Succeess.");
	}
	
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void connectionClose(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
