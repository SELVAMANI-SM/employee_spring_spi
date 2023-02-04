package com.employeeportal.EmployeeProtel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeportal.EmployeeProtel.dao.QueryRepository;
import com.employeeportal.EmployeeProtel.dto.Message;
import com.employeeportal.EmployeeProtel.model.Query;
import com.employeeportal.EmployeeProtel.util.QueryUtil;

@RestController
public class QueryController {
	@Autowired
	QueryRepository queryRepository;
	@PostMapping("query/addDetails")
	public ResponseEntity<?> addDetails(@RequestBody Query query)
	{
		try
		{
			queryRepository.save(query);
			Message message = new Message("SUCCESS");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("query/profile/{id}")
	public ResponseEntity<?> profile(@PathVariable("id") Integer id)
	{
		List<Query> query =null;
	
		try {
			query =queryRepository.findByUserId(id);
			return new ResponseEntity<>(query,HttpStatus.OK);	
		}
		catch(Exception e)
		{
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("query/admin/update")
	public ResponseEntity<?> admin(@RequestParam("userId") Integer userId, @RequestParam("reply") String reply,@RequestParam("apply") String apply)
	{
		
		try
		{
			QueryUtil.reply(reply, userId,apply);
			Message message = new Message("SUCCESS");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("query/admin")
	public ResponseEntity<?> adminDetails()
	{
		List<Query> query = null;
		try {
			query = QueryUtil.adminAllRequest();
			return new ResponseEntity<>(query,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("query/adminrequest")
	public ResponseEntity<?> adminDetailsAllRequest()
	{
		List<Query> query = null;
		try {
			query = QueryUtil.adminAllRequest();
			return new ResponseEntity<>(query,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("query/manager")
	public ResponseEntity<?> managerDetails()
	{
		List<Query> query = null;
		try
		{
			query =QueryUtil.managerAllRequest();
			return new ResponseEntity<>(query,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("SERVER ERROR");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}


