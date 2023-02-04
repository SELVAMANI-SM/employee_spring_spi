package com.employeeportal.EmployeeProtel.dto;

import lombok.Data;

@Data
public class Message {

	private String message;

	public Message(String message) {
		super();
		this.message = message;
	}
}
