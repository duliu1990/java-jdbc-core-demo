package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleJDBCUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		
		if(connection == null)
			System.out.println("Get Oracle JDBC Connection failed.");
		else
			System.out.println("Get Oracle JDBC Connection Success.");
	}
	
	public static Connection getConnection(){
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Load Oracle JDBC Driver has Error.");
			
			e.printStackTrace();
		}
		
		Connection connection = null;
		
		try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            
            e.printStackTrace();

        }
		
		return connection ;
	}
	
}
