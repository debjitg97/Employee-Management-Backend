package com.ganguli.employeemanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.ganguli.employeemanagement.dto.DepartmentDTO;
import com.ganguli.employeemanagement.enums.DepartmentField;
import com.ganguli.employeemanagement.exception.BadRequestException;

public interface DepartmentService {
	public Page<DepartmentDTO> getDepartments(int pageNo, int pageSize, DepartmentField sortField, Sort.Direction sortDirection, String searchQuery);
	
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) throws BadRequestException;
	
	public DepartmentDTO editDepartment(Long departmentId, DepartmentDTO departmentDTO) throws BadRequestException;
}
