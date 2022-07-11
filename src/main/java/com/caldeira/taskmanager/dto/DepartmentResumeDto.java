package com.caldeira.taskmanager.dto;

public class DepartmentResumeDto {

	private String name;
	private Long quantityOfPeople;
	private Long quantityOfTasks;
	
	public DepartmentResumeDto() { }
	
	public DepartmentResumeDto(String name, Long quantityOfPeople, Long quantityOfTasks) {
		this.name = name;
		this.quantityOfPeople = quantityOfPeople;
		this.quantityOfTasks = quantityOfTasks;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQuantityOfPeople() {
		return quantityOfPeople;
	}
	public void setQuantityOfPeople(Long quantityOfPeople) {
		this.quantityOfPeople = quantityOfPeople;
	}
	public Long getQuantityOfTasks() {
		return quantityOfTasks;
	}
	public void setQuantityOfTasks(Long quantityOfTasks) {
		this.quantityOfTasks = quantityOfTasks;
	}
	
}
