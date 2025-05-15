package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
	
	public void getDbconnection(String url, String username, String password) throws SQLException {
		try {
		
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		 conn=DriverManager.getConnection(url, username, password);
		}
		catch(Exception e) {
			
		}
	}
	
	public void getDbconnection() {
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			conn= DriverManager.getConnection("");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
		public void closeConnectionQuery() throws Throwable {
			try {
				conn.close();
			} catch (Exception e) {
				
			}
			
			
		}
		public ResultSet executeSelectQuery(String query) throws Throwable {
			ResultSet result=null;
			try {
			Statement stat=conn.createStatement();
			 result=stat.executeQuery(query);
			
			}catch(Exception e){
				
			}
			return result;
		}
		public int executeNonSelectQuery(String query) {
			int result=0;
			try {
				Statement stat=conn.createStatement();
				result=stat.executeUpdate(query);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;			
		}
}


