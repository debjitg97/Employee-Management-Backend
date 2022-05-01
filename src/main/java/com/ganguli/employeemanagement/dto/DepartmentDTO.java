package com.ganguli.employeemanagement.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
	private Long departmentId;
	
	@NotNull(message = "{department.name.missing}")
	@Size(min = 3, max = 10, message = "{department.name.invalidSize}")
	@Pattern(regexp = "^[a-zA-Z]*", message = "{department.name.invalidPattern}")
	private String departmentName;
}
