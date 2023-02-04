package com.employeeportal.EmployeeProtel.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employeeportal.EmployeeProtel.Validation.UserValidation;
import com.employeeportal.EmployeeProtel.dao.UserRepository;
import com.employeeportal.EmployeeProtel.dto.Logins;
import com.employeeportal.EmployeeProtel.dto.Message;
import com.employeeportal.EmployeeProtel.exception.ValidationException;
import com.employeeportal.EmployeeProtel.model.User;
import com.employeeportal.EmployeeProtel.util.Login;
import com.employeeportal.EmployeeProtel.util.UserUtil;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@PostMapping("user/addDetails")
	public ResponseEntity<?> addDetails(@RequestBody User user)
	{
		try {
			UserValidation.addDetails(user);
			userRepository.save(user);
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
			Message message = new Message("SERVER ERROR 403");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user/profile/{id}")
	public ResponseEntity<?> profile(@PathVariable("id") Integer id) throws ValidationException{
		Optional<User> user=null;
		
		try {
			user=userRepository.findById(id);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@PostMapping("user/login")
	public ResponseEntity<?> findByEmailAndPassword(@RequestBody User user) throws Exception,ValidationException
	{
	
		String email=user.getEmail();
		String password=user.getPassword();
		
		Logins login=null;
		try
		{
		login=Login.loginValidator(email,password);
		
		
		return new ResponseEntity<>(login,HttpStatus.OK);
		}
		catch(ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	@GetMapping("user/employee")
	public ResponseEntity<?> findEmployee()
	{
		List<User> user= null;
		String value= "employee";
		try
		{
			user = userRepository.findByUserType(value);
			
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 Message message = new Message("Server Error");
			 return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user/manager")
	public ResponseEntity<?> findManager()
	{
		List<User> user= null;
		String value= "manager";
		try
		{
			user = userRepository.findByUserType(value);
			
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 Message message = new Message("Server Error");
			 return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("user/updateDetails/{id}")
	public ResponseEntity<?> updateDetails(@PathVariable("id") Integer id,@RequestBody User user)
	{
		
		
		try
		{
			userRepository.save(user);
			Message message = new Message("SUCCESSFULLY UPDATED");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user/delete/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id)
	{
		try
		{
			userRepository.deleteById(id);
			Message message = new Message("DELETED SUCCESSFULLY");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user/manager/deatils/{id}")
	public ResponseEntity<?> managerDetails(@PathVariable("id") Integer id)
	{
		int number=0;
		try
		{
			number=UserUtil.findManager(id);
			return new ResponseEntity<>(number,HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			 Message message = new Message("SERVER ERROR");
			 return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user/employeedetails")
	public ResponseEntity<?> employeeDetails()
	{
		List<User> user =null;
		String value ="employee";
		try 
		{
			user =userRepository.findByUserType(value);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 Message message = new Message("SERVER ERROR");
			 return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
