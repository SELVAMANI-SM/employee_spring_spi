package com.employeeportal.EmployeeProtel.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtilDao {
	public static Connection sqlConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://hariharan-db.cdbky3odmgvg.ap-south-1.rds.amazonaws.com/selvamani_db","selvamani","Selva@123");
		return connection;
				
	}
}
