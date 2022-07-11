package com.caldeira.taskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.taskmanager.dto.DepartmentResumeDto;
import com.caldeira.taskmanager.entity.Department;
import com.caldeira.taskmanager.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/get/departamentos")
	public ResponseEntity<List<DepartmentResumeDto>> departmentResume() {
		
		List<Department> departments = departmentService.departmentResume();
		
		List<DepartmentResumeDto> departmentsResume = new ArrayList<>();
		
		for(Department department : departments) {
			
			DepartmentResumeDto departmentResume = new DepartmentResumeDto(
					department.getName(),
					departmentService.quantityOfPeople(department.getId()),
					departmentService.quantityOfTask(department.getId()));
			departmentsResume.add(departmentResume);
			
		}
		
		if(departmentsResume.isEmpty())
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(departmentsResume);
		
	}
	
}
