package com.ganguli.employeemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ganguli.employeemanagement.dto.DepartmentDTO;
import com.ganguli.employeemanagement.entity.Department;
import com.ganguli.employeemanagement.enums.DepartmentField;
import com.ganguli.employeemanagement.exception.BadRequestException;
import com.ganguli.employeemanagement.repository.DepartmentRepository;
import com.ganguli.employeemanagement.service.impl.DepartmentServiceImpl;
import com.ganguli.employeemanagement.util.DepartmentUtil;

@SpringBootTest
class DepartmentTests {
	@Mock
	private DepartmentRepository departmentRepository;
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
	@Test
	void getDepartments() {
		List<Department> departmentList = new ArrayList<>();
		Sort.Direction sortDirection = Direction.ASC;
		DepartmentField sortField = DepartmentField.departmentId;
		departmentList.add(new Department(1L, "A"));
		departmentList.add(new Department(2L, "B"));
		Sort sort = Sort.by(sortDirection, sortField.toString());
		Pageable pageable = PageRequest.of(0, 10, sort);
		Page<Department> departmentPage = new PageImpl<>(departmentList, pageable, 2);
		Mockito.when(departmentRepository.findByDepartmentNameContaining("", pageable)).thenReturn(departmentPage);
		Page<DepartmentDTO> departmentDTOpage = departmentPage.map(DepartmentUtil::entityToDTO);
		assertEquals(departmentService.getDepartments(1, 10, sortField, sortDirection, ""), departmentDTOpage);
	}
	
	@Test
	void addDepartmentSuccess() throws BadRequestException {
		DepartmentDTO departmentDTO = new DepartmentDTO(null, "A");
		Department department = new Department(1L, "A");
		Optional<Department> departmentOptional = Optional.ofNullable(null);
		Mockito.when(departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName())).thenReturn(departmentOptional);
		Mockito.when(departmentRepository.save(DepartmentUtil.DTOToEntity(departmentDTO))).thenReturn(department);
		assertEquals(departmentService.addDepartment(departmentDTO), DepartmentUtil.entityToDTO(department));
	}
	
	@Test
	void addDepartmentDepartmentAlreadyExists() throws BadRequestException {
		DepartmentDTO departmentDTO = new DepartmentDTO(null, "A");
		Department department = new Department(1L, "A");
		Optional<Department> departmentOptional = Optional.of(department);
		Mockito.when(departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName())).thenReturn(departmentOptional);
		assertThrows(BadRequestException.class, () -> departmentService.addDepartment(departmentDTO));
	}
	
	@Test
	void editDepartmentSuccess() throws BadRequestException {
		DepartmentDTO departmentDTO = new DepartmentDTO(null, "B");
		Long departmentId = 1L;
		Department department = new Department(1L, "A");
		Department edited = new Department(1L, "B");
		Optional<Department> departmentOptional = Optional.of(department);
		Mockito.when(departmentRepository.findById(departmentId)).thenReturn(departmentOptional);
		Mockito.when(departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName())).thenReturn(Optional.ofNullable(null));
		Mockito.when(departmentRepository.save(edited)).thenReturn(edited);
		assertEquals(departmentService.editDepartment(departmentId, departmentDTO), DepartmentUtil.entityToDTO(edited));
	}
	
	@Test
	void editDepartmentIdNotFound() throws BadRequestException {
		DepartmentDTO departmentDTO = new DepartmentDTO(null, "B");
		Long departmentId = 1L;
		Mockito.when(departmentRepository.findById(departmentId)).thenReturn(Optional.ofNullable(null));
		assertThrows(BadRequestException.class, () -> departmentService.editDepartment(departmentId, departmentDTO));
	}
	
	@Test
	void editDepartmentNameAlreadyExists() throws BadRequestException {
		DepartmentDTO departmentDTO = new DepartmentDTO(null, "B");
		Long departmentId = 1L;
		Department department = new Department(1L, "A");
		Department duplicate = new Department(2L, "B");
		Optional<Department> departmentOptional = Optional.of(department);
		Optional<Department> duplicateOptional = Optional.of(duplicate);
		Mockito.when(departmentRepository.findById(departmentId)).thenReturn(departmentOptional);
		Mockito.when(departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName())).thenReturn(duplicateOptional);
		assertThrows(BadRequestException.class, () -> departmentService.editDepartment(departmentId, departmentDTO));
	}
}
