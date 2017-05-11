package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsertRecord {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		insertDbTableWithStatement();
		insertDbTableWithPreparedStatement();
	}
	
	/**
	 * 采用 Statement 方式插入数据
	 * @throws SQLException
	 */
	public static void insertDbTableWithStatement() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String sql = "INSERT INTO USER_INFO (USER_ID, USERNAME, CREATED_BY,CREATED_DATE) "
				+ "VALUES (1,'devnp.com','system','2017-03-29')" ;
		
		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			statement = dbConnection.createStatement();

			System.out.println(sql);

			// execute insert SQL stetement
			statement.executeUpdate(sql);

			System.out.println("Record is inserted into USER_INFO table Success!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	/**
	 * 采用Prepared Statement 方式插入数据
	 * @throws SQLException
	 */
	public static void insertDbTableWithPreparedStatement() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO USER_INFO (USER_ID, USERNAME, CREATED_BY,CREATED_DATE) "
				+ "VALUES (2,'devnp.com','system','2017-03-29')" ;
		
		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			preparedStatement = dbConnection.prepareStatement(sql);

			System.out.println(sql);

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into USER_INFO table Success!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
}
