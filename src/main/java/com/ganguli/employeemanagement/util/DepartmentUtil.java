package com.ganguli.employeemanagement.util;

import com.ganguli.employeemanagement.dto.DepartmentDTO;
import com.ganguli.employeemanagement.entity.Department;

public final class DepartmentUtil {
	private DepartmentUtil() {
	}
	
	public static DepartmentDTO entityToDTO(Department department) {
		return new DepartmentDTO(department.getDepartmentId(), department.getDepartmentName());
	}
	
	public static Department DTOToEntity(DepartmentDTO departmentDTO) {
		return new Department(null, departmentDTO.getDepartmentName());
	}
}
