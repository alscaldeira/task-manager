package com.caldeira.taskmanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.caldeira.taskmanager.dto.TaskDto;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo")
	private String title;
	
	@Column(name = "descricao")
	private String description;
	
	@Column(name = "prazo")
	private Date deadline;
	
	@Column(name = "duracao")
	private Integer duration;

	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Department department;
	
	@Column(name="finalizado")
	private Status status;
	
	public enum Status {
		AFAZER,
		FEITO
	}

	public Task() { }
	
	public Task(TaskDto taskDto, Department department, Person person) {
		this.title = taskDto.getTitle();
		this.description = taskDto.getDescription();
		this.person = person;
		this.department = department;
		this.status = Status.AFAZER;
	}
	
	public boolean thereIsDataMissing() {
		if(this.title == null || this.title == ""
		|| this.description == null || this.description == ""
		|| this.department == null) {
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", person=" + person
				+ ", department=" + department + "]";
	}
	
}
