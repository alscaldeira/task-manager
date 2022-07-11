package com.caldeira.taskmanager.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caldeira.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	public Optional<List<Task>> findByDepartmentName(String name);
	
	public Optional<List<Task>> findByPersonId(Long id);
	
	@Query("SELECT t FROM Task t WHERE t.deadline between :initialDate AND :endDate AND t.person = :personId")
	public Optional<List<Task>> findByDeadlineBetween(
			@Param("initialDate") Date initialDate,
			@Param("endDate") Date endDate,
			@Param("personId") Long personId);
	
	@Query("SELECT t FROM Task t WHERE t.person = null order by t.deadline asc")
	public Optional<List<Task>> findTop3ByOrderByDeadlineAsc();
	
	public long countByDepartmentId(Long departmentId);
	
}
