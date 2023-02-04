package com.employeeportal.EmployeeProtel.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.employeeportal.EmployeeProtel.model.Leave;
import com.employeeportal.EmployeeProtel.model.User;

public class UserUtil {
	public static String approve(String value, int id) throws Exception
	{
		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		String query1 = "update leave_details set status = ? where employee_id =?";
		statement = connection.prepareStatement(query1);
		statement.setString(1, value);
		statement.setInt(2, id);

		int rows = statement.executeUpdate();
		if (rows == 0) {
			return "NOT UPDATED";
		}
		return "SUCCESS";
	}
	public static int takenLeave(int id) throws Exception
	{
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		String value ="Approved";
		String query1 = "select * from leave_details where employee_id =? and status=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		statement.setString(2, value);
		ResultSet result = statement.executeQuery();
		int count=0;

		while (result.next()) {
			
			count +=result.getInt("days");
		}
		return count;
	}
	public static int findValue(int id) throws Exception
	{
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		String value ="pending";
		String query1 = "select * from leave_details where employee_id =? and status=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		statement.setString(2, value);
		ResultSet result = statement.executeQuery();
		int count=0;

		while (result.next()) {
			count++;
		}
		return count;
	}
	public static int findManager(int id) throws Exception
	{
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		
		String query1 = "select * from user_detail where id=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		int value=0;

		if(result.next()) {
			value= result.getInt("reference");
		}
		return value;
	}
	public static List<Leave> empolyeeRequest(int id) throws Exception
	{
		List<Leave> leave = new ArrayList<Leave>();
		String value ="pending";
		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		
		String query1 = "select * from user_detail where reference=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		int value1=0;
		Leave leaves=null;
		while(result.next()) {
			value1= result.getInt("id");

			String query = "select * from leave_details where employee_id =? and status =?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,value1);
			statement.setString(2, value);
			ResultSet result1 = statement.executeQuery();
			while(result1.next())
			{
				leaves = new Leave();
				leaves.setEmployeeId(result1.getInt("employee_id"));
				leaves.setDays(result1.getInt("days"));
				leaves.setStartDate(result1.getDate("start_date"));
				leaves.setEndDate(result1.getDate("end_date"));
				leaves.setType(result1.getString("type"));
				leaves.setReason(result1.getString("reason"));
				leaves.setStatus(result1.getString("status"));
				
				leave.add(leaves);
			}
			
			
		}
		return leave;
		
	}
	public static List<Leave> leaveAllRequest(int id) throws Exception
	{
		List<Leave> leave = new ArrayList<Leave>();

		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		
		String query1 = "select * from user_detail where reference=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		int value1=0;
		Leave leaves=null;
		while(result.next()) {
			value1= result.getInt("id");

			String query = "select * from leave_details where employee_id =?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,value1);
			ResultSet result1 = statement.executeQuery();
			while(result1.next())
			{
				leaves = new Leave();
				leaves.setEmployeeId(result1.getInt("employee_id"));
				leaves.setDays(result1.getInt("days"));
				leaves.setStartDate(result1.getDate("start_date"));
				leaves.setEndDate(result1.getDate("end_date"));
				leaves.setType(result1.getString("type"));
				leaves.setReason(result1.getString("reason"));
				leaves.setStatus(result1.getString("status"));
				
				leave.add(leaves);
			}
			
			
		}
		return leave;
		
	}
	public static List<User> employeeRequestDetails(int id) throws Exception
	{
		List<User>user = new ArrayList<User>();

		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		
		String query1 = "select * from user_detail where reference=?";
		statement = connection.prepareStatement(query1);
		statement.setInt(1,id);
		ResultSet result = statement.executeQuery();
		User users=null;
		while(result.next()) {
			
			users= new User();
			users.setId(result.getInt("id"));
			users.setName(result.getString("name"));
			users.setEmail(result.getString("email"));
			users.setMobile(result.getString("mobile"));
			users.setUserType(result.getString("user_type"));
			user.add(users);	
		}
		return user;
		
	}
	
	
}
