package com.caldeira.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.taskmanager.dto.NameAndPeriodDto;
import com.caldeira.taskmanager.dto.PersonDto;
import com.caldeira.taskmanager.dto.PersonWithTaskDto;
import com.caldeira.taskmanager.entity.Department;
import com.caldeira.taskmanager.entity.Person;
import com.caldeira.taskmanager.service.DepartmentService;
import com.caldeira.taskmanager.service.PersonService;
import com.caldeira.taskmanager.service.TaskService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/post/pessoas")
	public ResponseEntity<Person> save(@RequestBody PersonDto personDto) {
		Department department = departmentService.findByName(personDto.getDepartment());
		if(department != null) {
			Person person = new Person(personDto, department);
			if(person.thereIsDataMissing()) {
				return ResponseEntity.badRequest().build();
			}
			personService.save(person);
			return ResponseEntity.ok(person);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/put/pessoas/{id}")
	public ResponseEntity<Person> update(@RequestBody PersonDto personDto, @PathVariable("id") Long id) {
		
		Department department = departmentService.findByName(personDto.getDepartment());
		
		Person person = personService.update(personDto, id, department);
		
		if(person == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(person);
	}
	
	@DeleteMapping("/delete/pessoas/{id}")
	public ResponseEntity<Person> delete(@PathVariable("id") Long id) {
		
		if(id == null || id < 1)
			return ResponseEntity.notFound().build();

		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/get/pessoas")
	public ResponseEntity<List<PersonWithTaskDto>> getPeopleByTask() {
		List<PersonWithTaskDto> people = personService.findPeopleWithTask();
		if(people.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(people);
	}
	
	@GetMapping("/get/pessoas/gastos")
	public ResponseEntity<Integer> averageOfTimeWastedPerTask(@RequestBody NameAndPeriodDto nameAndPeriod) {
		Integer average = taskService.timeWastedPerTask(nameAndPeriod);
		return ResponseEntity.ok(average);
	}
	
}
