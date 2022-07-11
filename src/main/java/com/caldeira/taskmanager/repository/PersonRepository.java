package com.caldeira.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caldeira.taskmanager.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public long countByDepartmentId(Long departmentId);
	
}
