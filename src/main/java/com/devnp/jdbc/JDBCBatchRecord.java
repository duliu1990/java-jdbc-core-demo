package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JDBCBatchRecord {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		batchDbRecordWithStatement();
		
		batchDbRecordWithPreparedStatement();
	}
	
	/**
	 * 采用 Statement 方式插入数据
	 * @throws SQLException
	 */
	public static void batchDbRecordWithStatement() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL1 = "INSERT INTO USER_INFO"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(3,'duliu','system',  NOW())";

		String insertTableSQL2 = "INSERT INTO USER_INFO"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(4,'duliu','system', NOW())";

		String updateTableSQL1 = "UPDATE USER_INFO SET USERNAME = 'devp.com' "
				+ " WHERE USER_ID = 4" ;

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			statement = dbConnection.createStatement();

			dbConnection.setAutoCommit(false);

			statement.addBatch(insertTableSQL1);
			statement.addBatch(insertTableSQL2);
			statement.addBatch(updateTableSQL1);

			statement.executeBatch();

			dbConnection.commit();

			System.out.println("Records are inserted and updated USER_INFO table!");

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
	
	/**
	 * 采用Prepared Statement 方式插入数据
	 * @throws SQLException
	 */
	public static void batchDbRecordWithPreparedStatement() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO USER_INFO"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
				+ "(?,?,?,?)";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			dbConnection.setAutoCommit(false);

			preparedStatement.setInt(1, 5);
			preparedStatement.setString(2, "duliu");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.addBatch();

			preparedStatement.setInt(1, 6);
			preparedStatement.setString(2, "devnp.com");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.addBatch();

			preparedStatement.setInt(1, 7);
			preparedStatement.setString(2, "devnp.com");
			preparedStatement.setString(3, "system");
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.addBatch();

			preparedStatement.executeBatch();

			dbConnection.commit();

			System.out.println("Record is inserted into USER_INFO table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			
			dbConnection.rollback();

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
