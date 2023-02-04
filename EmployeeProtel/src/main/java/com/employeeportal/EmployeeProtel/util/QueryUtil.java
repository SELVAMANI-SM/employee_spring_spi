package com.employeeportal.EmployeeProtel.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.employeeportal.EmployeeProtel.model.Query;



public class QueryUtil {
	public static List<Query> adminAllRequest() throws Exception
	{
		List<Query> query = new ArrayList<Query>();

		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		String value ="manager";
		
		
		String query1 = "select * from user_detail where user_type=?";
		statement = connection.prepareStatement(query1);
		statement.setString(1,value);
		ResultSet result = statement.executeQuery();
		Query querys=null;
		int number=0;
		while(result.next()) {
			
			number=result.getInt("id");
			
			String query2 = "select * from query_details where user_id =?";
			statement = connection.prepareStatement(query2);
			statement.setInt(1,number);
			ResultSet result1 = statement.executeQuery();
			while(result1.next())
			{
				querys = new Query();
				
				querys.setUserId(result1.getInt("user_id"));
				querys.setApply(result1.getString("apply"));
				querys.setReply(result1.getString("reply"));
				
				query.add(querys);
			}
			
		}
		return query;
		
	}
//	public static List<Query> adminAllNullRequest() throws Exception
//	{
//		List<Query> query = new ArrayList<Query>();
//
//		
//		Connection connection;
//		connection = ConnectionUtilDao.sqlConnection();
//		PreparedStatement statement;
//		String value ="admin";
//		
//		
//		String query1 = "select * from user_detail where user_type=?";
//		statement = connection.prepareStatement(query1);
//		statement.setString(1,value);
//		ResultSet result = statement.executeQuery();
//		Query querys=null;
//		int number=0;
//		String answer="";
//		while(result.next()) {
//			
//			number=result.getInt("id");
//			
//			String query2 = "select * from query_details where user_id =? and reply =?";
//			statement = connection.prepareStatement(query2);
//			statement.setInt(1,number);
//			statement.setString(2,answer);
//			ResultSet result1 = statement.executeQuery();
//			while(result1.next())
//			{
//				querys = new Query();
//				
//				querys.setUserId(result1.getInt("user_id"));
//				querys.setApply(result1.getString("apply"));
//				querys.setReply(result1.getString("reply"));
//				
//				query.add(querys);
//			}
//			
//		}
//		return query;
//		
//	}
	public static List<Query> managerAllRequest() throws Exception
	{
		List<Query> query = new ArrayList<Query>();
		String value ="employee";

		
		Connection connection;
		connection = ConnectionUtilDao.sqlConnection();
		PreparedStatement statement;
		
		String query1 = "select * from user_detail where user_type=?";
		statement = connection.prepareStatement(query1);
		statement.setString(1,value);
		ResultSet result = statement.executeQuery();
		int value1=0;
		Query querys=null;
		while(result.next()) {
			value1= result.getInt("id");

			String query2 = "select * from query_details where user_id =?";
			statement = connection.prepareStatement(query2);
			statement.setInt(1,value1);
			ResultSet result1 = statement.executeQuery();
			while(result1.next())
			{
                querys = new Query();
				
				querys.setUserId(result1.getInt("user_id"));
				querys.setApply(result1.getString("apply"));
				querys.setReply(result1.getString("reply"));
				
				query.add(querys);
			}
			
			
		}
		return query;
		
	}
//	public static List<Query> managerRequestDetails(int id) throws Exception
//	{
//		List<Query> query = new ArrayList<Query>();
//
//		
//		Connection connection;
//		connection = ConnectionUtilDao.sqlConnection();
//		PreparedStatement statement;
//		
//		String query1 = "select * from user_detail where reference=?";
//		statement = connection.prepareStatement(query1);
//		statement.setInt(1,id);
//		ResultSet result = statement.executeQuery();
//		int value1=0;
//		String answer="";
//		Query querys=null;
//		while(result.next()) {
//			value1= result.getInt("id");
//
//			String query2 = "select * from query_details where user_id =? ans reply =?";
//			statement = connection.prepareStatement(query2);
//			statement.setInt(1,value1);
//			statement.setString(2, answer);
//			ResultSet result1 = statement.executeQuery();
//			while(result1.next())
//			{
//                querys = new Query();
//				
//				querys.setUserId(result1.getInt("user_id"));
//				querys.setApply(result1.getString("apply"));
//				querys.setReply(result1.getString("reply"));
//				
//				query.add(querys);
//			}
//			
//			
//		}
//		return query;
//		
//	}
	public static String reply(String name, int id,String name1) throws Exception {

		Connection connection;
		PreparedStatement statement;
		connection=ConnectionUtilDao.sqlConnection();
		
		
		String query = "UPDATE query_details SET reply=? WHERE user_id=? AND apply =?";
		statement = connection.prepareStatement(query);
		statement.setString(1,name);
		statement.setInt(2,id);
		statement.setString(3,name1);
	     String  result=null;
		int row =statement.executeUpdate();
		if(row==1)
		{
			result="SUCCESS";
			
		}
		else
		{
			result="FAILED";
		}
		
		
		return result;
		
		
	}
}
