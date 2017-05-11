package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCreateTable {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		createDbTableWithPreparedStatement();
	}

	/**
	 * 采用Statement的方式
	 * @throws SQLException
	 */
	public static void createDbTableWithStatement() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE TABLE USER_INFO("
				+ "USER_ID int(5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
				+ ")";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();

			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);

			statement.execute(createTableSQL);

			System.out.println("Table \"USER_INFO\" is created!");

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
	 * 采用Prepared Statement的方式
	 * @throws SQLException
	 */
	public static void createDbTableWithPreparedStatement() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String createTableSQL = "CREATE TABLE USER_INFO("
				+ "USER_ID int(5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
				+ ")";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();

			preparedStatement  = dbConnection.prepareStatement(createTableSQL);

			System.out.println(createTableSQL);

			preparedStatement .executeUpdate();

			System.out.println("Table \"USER_INFO\" is created!");

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
