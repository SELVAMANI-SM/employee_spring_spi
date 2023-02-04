package com.employeeportal.EmployeeProtel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeportal.EmployeeProtel.model.Query;

public interface QueryRepository extends JpaRepository<Query,Integer> {

	List<Query> findByUserId(Integer id);
	

}
