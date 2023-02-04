package com.employeeportal.EmployeeProtel.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import com.employeeportal.EmployeeProtel.Validation.LeaveValidation;
import com.employeeportal.EmployeeProtel.dao.LeaveRepository;
import com.employeeportal.EmployeeProtel.dto.Message;
import com.employeeportal.EmployeeProtel.model.Leave;
import com.employeeportal.EmployeeProtel.util.UserUtil;

import io.micrometer.core.instrument.config.validate.ValidationException;

@RestController
public class LeaveController {

	@Autowired
	LeaveRepository leaveRepository;
	@PostMapping("leave/addDetails")
	public ResponseEntity<?> AddDetails(@RequestBody Leave leave)
	{
		try {
			LeaveValidation.addDetails(leave);
			leaveRepository.save(leave);
			Message message = new Message("SUCCESS");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(ValidationException e)
		{
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("leave/display")
	public ResponseEntity<?> leaveDisplay() {
		List<Leave> leave = null;
		try {
			leave =leaveRepository.findAll();
			return new ResponseEntity<>(leave, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping("leave/profile/{id}")
	public ResponseEntity<?> profile(@PathVariable("id") Integer id) throws ValidationException{
		List<Leave> leave=null;
		
		try {
			leave=leaveRepository.findByEmployeeId(id);
			return new ResponseEntity<>(leave,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("leave/approve/{id}")
	public ResponseEntity<?> ApproveId(@PathVariable("id") Integer id) throws ValidationException{
		String value="Approved";
		String result="";
		try {
			
			result =UserUtil.approve(value, id);
			
			Message message = new Message(result);
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("leave/reject/{id}")
	public ResponseEntity<?> RejectId(@PathVariable("id") Integer id) throws ValidationException{
		String value="Rejected";
		String result ="";
		
		try {
			result =UserUtil.approve(value, id);
			Message message = new Message(result);
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("leave/request")
	public ResponseEntity<?> request()
	{
		List<Leave> leave =null;
		String value= "pending";
		try
		{
			leave = leaveRepository.findByStatus(value);
		return new ResponseEntity<>(leave,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("Server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("leave/days/{id}")
	public ResponseEntity<?> days(@PathVariable("id") Integer id)
	{
		int number=0;
		try
		{
			number= UserUtil.takenLeave(id);
			//Message  message = new Message();
			return new ResponseEntity<>(number,HttpStatus.OK);
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("Server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	@GetMapping("leave/find/{id}")
	public ResponseEntity<?> find(@PathVariable ("id") Integer id)
	{
		int number=0;
		Message message=null;
		try
		{
			number=UserUtil.findValue(id);
			System.out.println(number);
			if(number==0)
			{
				 message = new Message("CARE ON");
			}
			else
			{
				 message = new Message("PLEASE WAIT");
			}
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("leave/leavedetails")
	public ResponseEntity<?> findManager()
	{
		List<Leave> leave = null;
		String value="pending";
		try
		{
			leave = leaveRepository.findByStatus(value);
			return new ResponseEntity<>(leave,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	@GetMapping("leave/allleavedetails")
	public ResponseEntity<?> findAllDetails()
	{
		List<Leave> leave = null;
		try
		{
			leave = leaveRepository.findAll();
			return new ResponseEntity<>(leave,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}
