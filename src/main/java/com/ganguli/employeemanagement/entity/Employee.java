package com.ganguli.employeemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(name = "employee_first_name")
	private String employeeFirstName;
	
	@Column(name = "employee_last_name")
	private String employeeLastName;
	
	@Column(name = "employee_email")
	private String employeeEmail;
	
	@ManyToOne
	@JoinColumn(name = "employee_department_id")
	private Department employeeDepartment;
}
