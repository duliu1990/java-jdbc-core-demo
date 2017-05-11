package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class JDBCTransaction {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		updateWithOutTransaction();
		updateWithTransaction();
	}
	
	/**
	 * 不采用事物的方式, 发生异常操作不会回滚
	 * @throws SQLException
	 */
	public static void updateWithOutTransaction() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;

		String insertTableSQL = "INSERT INTO USER_INFO"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
				+ "(?,?,?,?)";

		String updateTableSQL = "UPDATE USER_INFO SET USERNAME =? "
				+ "WHERE USER_ID = ?";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();

			preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
			preparedStatementInsert.setInt(1, 8);
			preparedStatementInsert.setString(2, "devnp.com");
			preparedStatementInsert.setString(3, "system");
			preparedStatementInsert.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			preparedStatementInsert.executeUpdate();
			
			System.out.println("Insert Success!");

			//update the user name length is more than max
			preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
			preparedStatementUpdate.setString(1, "update the user name length is more than max");
			preparedStatementUpdate.setInt(2, 7);
			preparedStatementUpdate.executeUpdate();

			System.out.println("Updated will be failed!");

		} catch (SQLException e) {

			//e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {

			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}

			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	/**
	 * 采用事物的方式，发生异常操作会回滚
	 * @throws SQLException
	 */
	public static void updateWithTransaction() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;

		String insertTableSQL = "INSERT INTO USER_INFO"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
				+ "(?,?,?,?)";

		String updateTableSQL = "UPDATE USER_INFO SET USERNAME =? "
				+ "WHERE USER_ID = ?";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			
			dbConnection.setAutoCommit(false);

			preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
			preparedStatementInsert.setInt(1, 9);
			preparedStatementInsert.setString(2, "devnp.com");
			preparedStatementInsert.setString(3, "system");
			preparedStatementInsert.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			preparedStatementInsert.executeUpdate();
			
			//update the user name length is more than max
			preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
			preparedStatementUpdate.setString(1, "update the user name length is more than max");
			preparedStatementUpdate.setInt(2, 8);
			preparedStatementUpdate.executeUpdate();

			dbConnection.commit();
			
			System.out.println("Insert and Updated all will be failed!");

		} catch (SQLException e) {

			dbConnection.rollback();
			
			//e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {

			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}

			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}

}
