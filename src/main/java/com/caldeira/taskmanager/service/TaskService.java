package com.caldeira.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caldeira.taskmanager.dto.NameAndPeriodDto;
import com.caldeira.taskmanager.entity.Person;
import com.caldeira.taskmanager.entity.Task;
import com.caldeira.taskmanager.entity.Task.Status;
import com.caldeira.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private PersonService personService;
	
	public void save(Task task) {
		taskRepo.save(task);
	}
	
	public void allocate(Long personId) {
		Person person = personService.findById(personId);
		if(person != null) {
			Optional<List<Task>> tasks = taskRepo.findByDepartmentName(person.getDepartment().getName());
			if(tasks.isPresent()) {
				tasks.get().forEach(task -> {
					task.setPerson(person);
					taskRepo.save(task);
				});
			}
		}
	}
	
	public boolean existsById(Long id) {
		return taskRepo.existsById(id);
	}

	public Task finalizeTask(Long id) {
		Task task = taskRepo.findById(id).get();
		task.setStatus(Status.FEITO);
		taskRepo.save(task);
		return task;
	}

	public List<Task> getTasksByDepartment(String departmentName) {
		Optional<List<Task>> tasks = taskRepo.findByDepartmentName(departmentName);
		if(tasks.isEmpty()) {
			return null;
		}
		return tasks.get();
	}
	
	public Integer timeWastedPerTask(NameAndPeriodDto nameAndPeriod) {
		Optional<List<Task>> tasks = taskRepo.findByDeadlineBetween(nameAndPeriod.getInitialDate(),
				nameAndPeriod.getEndDate(),
				nameAndPeriod.getPersonId());
		
		if(tasks.isEmpty()) return 0;
		
		int average = 0;
		int sum = 0;
		
		for(Task task : tasks.get()) {
			
			if(task.getStatus() == Status.FEITO)
				sum+=task.getDuration();
			
		}
		
		average = sum / tasks.get().size();
		
		return average;
	}

	public List<Task> findThreeOldestTasks() {
		Optional<List<Task>> tasks = taskRepo.findTop3ByOrderByDeadlineAsc();
		
		if(tasks.isEmpty())
			return null;
		
		return tasks.get();
	}
	
}
