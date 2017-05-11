package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDeleteRecord {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		deleteDbTableRecordWithStatement();
		deleteDbTableRecordWithPreparedStatement();
	}
	
	/**
	 * 采用Statement的方式
	 * @throws SQLException
	 */
	public static void deleteDbTableRecordWithStatement() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String deleteTableSQL = "DELETE FROM USER_INFO WHERE USER_ID = 1";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			statement = dbConnection.createStatement();

			System.out.println(deleteTableSQL);

			// execute delete SQL stetement
			statement.execute(deleteTableSQL);

			System.out.println("Record is deleted from USER_INFO table!");

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
	public static void deleteDbTableRecordWithPreparedStatement() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM USER_INFO WHERE USER_ID = ?";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, 2);

			// execute delete SQL Prepared stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is deleted from USER_INFO table!");

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
