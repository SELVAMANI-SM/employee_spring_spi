package com.employeeportal.EmployeeProtel.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="leave_details")
@Data
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column(name="employee_id")
	private Integer employeeId;
	private Integer days;
	private Date startDate;
	private Date endDate;
	private String type;
	private String reason;
	private String status;
}
