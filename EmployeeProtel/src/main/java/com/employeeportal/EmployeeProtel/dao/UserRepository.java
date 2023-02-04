package com.employeeportal.EmployeeProtel.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeportal.EmployeeProtel.model.User;

public interface UserRepository  extends JpaRepository<User,Integer>{

	@SuppressWarnings("unchecked")
	User save(User user);

	List<User> findByUserType(String value);
	
	

}
