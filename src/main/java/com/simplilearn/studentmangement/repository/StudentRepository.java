package com.simplilearn.studentmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.studentmangement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	

}