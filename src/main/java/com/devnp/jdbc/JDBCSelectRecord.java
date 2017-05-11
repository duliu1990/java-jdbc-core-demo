package com.devnp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JDBCSelectRecord {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		selectDbTableWithStatement();
		selectDbTableWithPreparedStatement();
	}
	
	/**
	 * 采用Statement 方式插入数据
	 * @throws SQLException
	 */
	public static void selectDbTableWithStatement() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT USER_ID, USERNAME, CREATED_BY, CREATED_DATE from USER_INFO";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				String userid = rs.getString("USER_ID");
				String username = rs.getString("USERNAME");
				String createBy = rs.getString("CREATED_BY");
				Date createDate = rs.getDate("CREATED_DATE");

				System.out.println("userid : " + userid);
				System.out.println("username : " + username);
				System.out.println("Create By : " + createBy);
				System.out.println("Create Date : " + createDate);

			}

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
	public static void selectDbTableWithPreparedStatement() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT USER_ID, USERNAME, CREATED_BY, CREATED_DATE from USER_INFO WHERE USER_ID = ?";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, 1);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String userid = rs.getString("USER_ID");
				String username = rs.getString("USERNAME");
				String createBy = rs.getString("CREATED_BY");
				Date createDate = rs.getDate("CREATED_DATE");

				System.out.println("userid : " + userid);
				System.out.println("username : " + username);
				System.out.println("Create By : " + createBy);
				System.out.println("Create Date : " + createDate);

			}

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
