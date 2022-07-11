package com.caldeira.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.taskmanager.dto.TaskDto;
import com.caldeira.taskmanager.entity.Department;
import com.caldeira.taskmanager.entity.Person;
import com.caldeira.taskmanager.entity.Task;
import com.caldeira.taskmanager.service.DepartmentService;
import com.caldeira.taskmanager.service.PersonService;
import com.caldeira.taskmanager.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/post/tarefas")
	public ResponseEntity<Task> save(@RequestBody TaskDto taskDto) {
		
		if(taskDto.getDepartment() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Department department = departmentService.findByName(taskDto.getDepartment());

		Person person = null;
		if(taskDto.getPerson() != null)
			person = personService.findById(taskDto.getPerson());
		
		Task task = new Task(taskDto, department, person);
		if(task.thereIsDataMissing()) {
			return ResponseEntity.badRequest().build();
		}
		taskService.save(task);
		return ResponseEntity.ok(task);
	}
	
	@PutMapping("/put/tarefas/alocar/{id}")
	public ResponseEntity<Task> allocate(@PathVariable("id") Long personId) {
		if(personService.existsById(personId)) {	
			taskService.allocate(personId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/put/tarefas/finalizar/{id}")
	public ResponseEntity<Task> finalizeTask(@PathVariable("id") Long id) {
		if(taskService.existsById(id)) {
			Task task = taskService.finalizeTask(id);
			return ResponseEntity.ok(task);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/get/tarefas/pendentes")
	public ResponseEntity<List<Task>> threeOldestTasks() {
		List<Task> tasks = taskService.findThreeOldestTasks();
		
		if(tasks.isEmpty())
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(tasks);
	}
	
}
