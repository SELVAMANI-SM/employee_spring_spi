package com.employeeportal.EmployeeProtel.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;



import com.employeeportal.EmployeeProtel.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {

	@SuppressWarnings("unchecked")
	Leave save(Leave leave);

	List<Leave> findAll();

	List<Leave> findByEmployeeId(Integer id);
//	@Transactional
//	@Modifying
//	@Query("update Leave u set u.status =: value  where u.employeeId =:employeeId")
//	 void approveDeatils(String value,int employeeId);
//		
		
	

	List<Leave> findByStatus(String value);

	
}
