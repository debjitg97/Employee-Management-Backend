package com.ganguli.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ganguli.employeemanagement.entity.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	public Page<Employee> findByEmployeeFirstNameContaining(String searchQuery, Pageable pageable);
	
	public Optional<Employee> findByEmployeeEmail(String employeeEmail);
}
