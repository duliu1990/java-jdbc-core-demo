package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUpdateRecord {

	public static void main(String[] args) throws SQLException {
		updateDbTableWithStatement();
		updateDbTableWithPreparedStatement();
	}

	public static void updateDbTableWithStatement() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String updateTableSQL = "UPDATE USER_INFO SET USERNAME = 'duliu' "
				+ " WHERE USER_ID = 1";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			statement = dbConnection.createStatement();

			System.out.println(updateTableSQL);

			// execute update SQL stetement
			statement.execute(updateTableSQL);

			System.out.println("Updated USER_INFO table record is Success!");

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
	
	public static void updateDbTableWithPreparedStatement() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE USER_INFO SET USERNAME = ? "
				                  + " WHERE USER_ID = ?";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, "duliu");
			preparedStatement.setInt(2, 2);

			// execute update SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Updated USER_INFO table record is Success!");

		} catch (SQLException e) {

			e.printStackTrace();

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
