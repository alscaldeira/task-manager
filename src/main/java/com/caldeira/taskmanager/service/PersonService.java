package com.caldeira.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caldeira.taskmanager.dto.PersonDto;
import com.caldeira.taskmanager.dto.PersonWithTaskDto;
import com.caldeira.taskmanager.entity.Department;
import com.caldeira.taskmanager.entity.Person;
import com.caldeira.taskmanager.entity.Task;
import com.caldeira.taskmanager.entity.Task.Status;
import com.caldeira.taskmanager.repository.PersonRepository;
import com.caldeira.taskmanager.repository.TaskRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	public void save(Person person) {
		personRepo.save(person);
	}
	
	public Person update(PersonDto personDto, Long id, Department department) {
		
		Optional<Person> person = personRepo.findById(id);
		
		if(person.isEmpty())
			return null;
		
		person.get().setDepartment(department);
		person.get().setName(personDto.getName());
		
		personRepo.save(person.get());
		
		return person.get();
	}
	
	public void delete(Long id) {
		if(personRepo.existsById(id)) {
			personRepo.deleteById(id);
		}
	}
	
	public Person findById(Long id) {
		Optional<Person> person = personRepo.findById(id);
		
		if(person.isEmpty()) {
			return null;
		}
		return person.get();
	}
	
	public boolean existsById(Long id) {
		return personRepo.existsById(id);
	}
	
	public List<PersonWithTaskDto> findPeopleWithTask() {
		List<Person> people = personRepo.findAll();
		List<PersonWithTaskDto> personWithTaskDtoList = new ArrayList<>();
		
		for (Person person : people) {
			PersonWithTaskDto personWithTaskDto = new PersonWithTaskDto();
			personWithTaskDto.setName(person.getName());
			personWithTaskDto.setDepartment(person.getDepartment().getName());
			personWithTaskDto.setTotalOfHoursWastedOnTasks(calculateTotalHoursWastedOnTask(person.getId()));
			personWithTaskDtoList.add(personWithTaskDto);
		}
		return personWithTaskDtoList;
	}
	
	private Integer calculateTotalHoursWastedOnTask(Long personId) {
		Optional<List<Task>> tasks = taskRepo.findByPersonId(personId);
		
		Integer total = 0;		
		
		for(Task task : tasks.get()) {
			if(task.getStatus() == Status.FEITO) { 
				total += task.getDuration();
			}
		}
		
		return total;
	}
	
}
