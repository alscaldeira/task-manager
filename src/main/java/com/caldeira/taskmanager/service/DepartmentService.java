package com.caldeira.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caldeira.taskmanager.entity.Department;
import com.caldeira.taskmanager.repository.DepartmentRepository;
import com.caldeira.taskmanager.repository.PersonRepository;
import com.caldeira.taskmanager.repository.TaskRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	public void save(String name) {
		Department department = new Department();
		department.setName(name);
		departmentRepo.save(department);
	}
	
	public Department findByName(String name) {
		Optional<Department> department = departmentRepo.findByName(name);
		
		if(department.isPresent())
			return department.get();
		
		Department newDepartment = new Department(name);
		
		return departmentRepo.save(newDepartment);
	}

	public List<Department> departmentResume() {
		List<Department> departments = departmentRepo.findAll();
		
		if(departments.isEmpty())
			return null;
		
		return departments;
	}
	
	public long quantityOfPeople(Long departmentId) {
		return personRepo.countByDepartmentId(departmentId);
	}
	
	public long quantityOfTask(Long departmentId) {
		return taskRepo.countByDepartmentId(departmentId);
	}
	
}
