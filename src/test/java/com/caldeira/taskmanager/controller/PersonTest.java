package com.caldeira.taskmanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.caldeira.taskmanager.dto.PersonDto;
import com.caldeira.taskmanager.entity.Person;

@SpringBootTest
public class PersonTest {

	@Autowired
	PersonController controller;
	
	@Test
	@Order(1)
	public void save() {
		PersonDto personDto = new PersonDto();
		personDto.setName("Anderson");
		personDto.setDepartment("TI");
		ResponseEntity<Person> response = controller.save(personDto);
		
		assertNotNull(response.getBody().getId());
	}
	
	@Test
	@Order(2)
	public void saveReturningBadRequest() {
		PersonDto personDto = new PersonDto();
		ResponseEntity<Person> response = controller.save(personDto);
		
		assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	@Order(3)
	public void update() {
		PersonDto personDto = new PersonDto();
		personDto.setName("Anderson Caldeira");
		personDto.setDepartment("TI");
		ResponseEntity<Person> response = controller.update(personDto, 2L);
		
		assertEquals("Anderson Caldeira", response.getBody().getName());
	}
	
	@Test
	@Order(4)
	public void updateReturningNotFound() {
		PersonDto personDto = new PersonDto();
		personDto.setName("Anderson Caldeira");
		personDto.setDepartment("TI");
		ResponseEntity<Person> response = controller.update(personDto, 0L);
		
		assertEquals(404, response.getStatusCodeValue());
	}
	
	@Test
	@Order(5)
	public void delete() {
		ResponseEntity<Person> response = controller.delete(2L);
		
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	@Order(6)
	public void deleteReturningNotFound() {
		ResponseEntity<Person> response = controller.delete(0L);
		
		assertEquals(404, response.getStatusCodeValue());
	}
	
}
