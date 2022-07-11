package com.caldeira.taskmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.caldeira.taskmanager.dto.PersonDto;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Department department;
	
	public Person() { }
	
	public Person(PersonDto person, Department department) {
		this.setName(person.getName());
		this.setDepartment(department);
	}
	
	public boolean thereIsDataMissing() {
		if(this.getName() == null || this.getName() == ""
		|| this.getDepartment() == null) {
			return true;
		}
		return false;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
	
}
