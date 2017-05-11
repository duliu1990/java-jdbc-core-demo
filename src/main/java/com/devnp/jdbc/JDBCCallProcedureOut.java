package com.devnp.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCCallProcedureOut {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		callOracleStoredProcOutParameter();
	}
	
	public static void callOracleStoredProcOutParameter() throws SQLException{
		Connection dbConnection = null;
		CallableStatement callableStatement = null;

		String insertStoreProc = "{call findUserInfoById(?)}";

		try {
			dbConnection = MySqlJDBSUtil.getConnection();
			callableStatement = dbConnection.prepareCall(insertStoreProc);

			callableStatement.setInt(1, 9);
			
			// execute findUserInfoById store procedure
			ResultSet rs = callableStatement.executeQuery();
			
			System.out.println("select USER_INFO record!");
			
			// get return value
			while (rs.next()) {
				int userId = rs.getInt(1);
				String userName = rs.getString(2);
				String createdBy = rs.getString(3);
				Date createdDate = rs.getDate(4);

				System.out.println("UserName : " + userId);
				System.out.println("UserName : " + userName);
				System.out.println("CreatedBy : " + createdBy);
				System.out.println("CreatedDate : " + createdDate);
			}
			

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
