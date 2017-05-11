package com.devnp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCCallProcedureIn {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		callOracleStoredProcINParameter();
	}

	public static void callOracleStoredProcINParameter() throws SQLException{
		Connection dbConnection = null;
		CallableStatement callableStatement = null;

		String insertStoreProc = "{call insertUserInfo(?,?,?)}";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			callableStatement = dbConnection.prepareCall(insertStoreProc);

			callableStatement.setInt(1, 9);
			callableStatement.setString(2, "devp.com");
			callableStatement.setString(3, "system");

			// execute insertUserInfo store procedure
			callableStatement.executeUpdate();

			System.out.println("Record is inserted into USER_INFO table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
}
