package com.ganguli.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ganguli.employeemanagement.entity.Department;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	public Page<Department> findByDepartmentNameContaining(String searchQuery, Pageable pageable);
	
	public Optional<Department> findByDepartmentName(String departmentName);
}
