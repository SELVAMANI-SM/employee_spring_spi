package com.employeeportal.EmployeeProtel.Validation;

import java.sql.Date;


import com.employeeportal.EmployeeProtel.exception.ValidationException;
import com.employeeportal.EmployeeProtel.model.Leave;



public class LeaveValidation {

	public static void addDetails(Leave leave) throws Exception,ValidationException
	{
		idCheck(leave.getEmployeeId());
		daysCheck(leave.getDays());
		startDateCheck(leave.getStartDate());
		endDateCheck(leave.getEndDate());
		reasonCheck(leave.getReason());
		
		
	}

	private static void reasonCheck(String reason) throws ValidationException {
		if(reason==null|| reason.isBlank())
		{
			throw new ValidationException("Enter Reason");
		}
		
		
	}

	private static void endDateCheck(Date date) throws ValidationException {
		
			String value= date.toString();
			if(value.isEmpty()||value.isBlank())
			{
				throw new ValidationException("Enter Valid EndDate");
			}
		
	}

	private static void startDateCheck(Date date) throws ValidationException {
		String value= date.toString();
		if(value.isEmpty()||value.isBlank())
		{
			throw new ValidationException("Enter Valid EndDate");
		}
		
	}

	private static void daysCheck(int days) throws ValidationException {
	if(days==0||days>6)
	{
		throw new ValidationException("Enter valid Days");
	}
		
	}

	private static void idCheck(int employeeId) throws ValidationException {
		if(employeeId==0)
		{
			throw new ValidationException("Enter valid Id");
		}
		
	}

}
