package com.caldeira.taskmanager.dto;

public class TaskDto {

	private String title;
	private String description;
	private Long person;
	private String department;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getPerson() {
		return person;
	}
	public void setPerson(Long person) {
		this.person = person;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
