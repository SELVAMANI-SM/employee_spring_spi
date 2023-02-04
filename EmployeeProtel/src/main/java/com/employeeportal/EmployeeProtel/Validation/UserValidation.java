package com.employeeportal.EmployeeProtel.Validation;

import com.employeeportal.EmployeeProtel.exception.ValidationException;
import com.employeeportal.EmployeeProtel.model.User;

public class UserValidation {
	public static void addDetails(User user) throws Exception,ValidationException
	{
		nameCheck(user.getName());
		emailCheck(user.getEmail());
		passwordCheck(user.getPassword());
		userTypeCheck(user.getUserType());
		mobileCheck(user.getMobile());
		
		
	}

	private static void mobileCheck(String mobile) throws ValidationException {
		
		char[] number=mobile.toCharArray();
		if(mobile.length()>10||mobile.length()<10)
		{
			
		throw new ValidationException("Enter valid Number ");
		
		}
		for(char i:number)
		{
			if(Character.isAlphabetic(i))
			{
				throw new ValidationException("Enter Only Numeric Value");	
			}
		}
	}

	private static void userTypeCheck(String userType) throws ValidationException {
		
		if(userType==null|| userType.isBlank())
		{
			throw new ValidationException("Enter vaild UserType");
		}
	}

	private static void passwordCheck(String password) throws ValidationException {
	
		if(password.length() < 8 && password.length() > 16)
		{
			throw new ValidationException("Enter valid password it must contain 8 to 16 character");
		}
	}

	private static void emailCheck(String email) throws ValidationException {
		if(!email.contains("@")|| !email.contains("."))
		{
			throw new ValidationException("Enter valid Email");
		}
		
	}

	private static void nameCheck(String name) throws ValidationException {
		if(name==null|| name.isBlank())
		{
			throw new ValidationException("Enter vaild Name");
		}
		
	}

}
