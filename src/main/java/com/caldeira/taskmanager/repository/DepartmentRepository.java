package com.caldeira.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caldeira.taskmanager.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
//	@Query(value = "SELECT * FROM department WHERE department.name = ?1 FETCH FIRST ROW ONLY;", nativeQuery = true)
//	public Optional<Department> findByName(String name);
	
	public Optional<Department> findByName(String name);
	
}
