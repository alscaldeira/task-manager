package com.caldeira.taskmanager.dto;

import com.caldeira.taskmanager.entity.Person;

public class PersonWithTaskDto {

	private String name;
	private String department;
	private Integer totalOfHoursWastedOnTasks;
	
	public PersonWithTaskDto() { }
	
	public PersonWithTaskDto(Person person, Integer totalOfHoursWastedOnTasks) {
		this.name = person.getName();
		this.department = person.getDepartment().getName();
		this.totalOfHoursWastedOnTasks = totalOfHoursWastedOnTasks;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getTotalOfHoursWastedOnTasks() {
		return totalOfHoursWastedOnTasks;
	}

	public void setTotalOfHoursWastedOnTasks(Integer totalOfHoursWastedOnTasks) {
		this.totalOfHoursWastedOnTasks = totalOfHoursWastedOnTasks;
	}
	
}
