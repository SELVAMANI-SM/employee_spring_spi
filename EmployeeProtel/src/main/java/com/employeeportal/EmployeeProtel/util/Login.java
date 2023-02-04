package com.employeeportal.EmployeeProtel.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.employeeportal.EmployeeProtel.dto.Logins;
import com.employeeportal.EmployeeProtel.exception.ValidationException;


public class Login {
	
	public static Logins loginValidator(String email,String password) throws Exception,ValidationException
	{
		
	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	ResultSet rs = null;
	String query = "SELECT email,PASSWORD,id,user_type FROM user_detail WHERE email=?";
	
	statement = connection.prepareStatement(query);
	statement.setString(1,email);


	
	rs = statement.executeQuery();
	String mail = null;
	String Password=null;
	String types =null;
	int id=0;
	
	while(rs.next())
	{
	mail=rs.getString("email");
	Password=rs.getString("password");
	id=rs.getInt("id");
	types=rs.getString("user_type");
	
	}
    if(mail==null)
	{
    	throw new ValidationException("REGISTER FIRST");
	}
    else if(Password.equals(password))
	{	
		Logins login = new Logins(id,types);
		return login;

	}
	else
	{
		throw new ValidationException("INVALID CREDENTIALS");
		
	}
	
}
	
}
