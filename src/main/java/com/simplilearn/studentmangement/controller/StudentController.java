package com.simplilearn.studentmangement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.studentmangement.entity.Student;
import com.simplilearn.studentmangement.exception.ResourceNotFoundException;
import com.simplilearn.studentmangement.repository.StudentRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	@GetMapping("/students")
	public List<Student>getAllStudents(){
		return studentRepository.findAll();
	}
	//create add student rest api
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student)
	{
	return studentRepository.save(student);	
	}
	//get student by id
	@GetMapping("/students/{id}")
	 public ResponseEntity<Student>  getStudentById(@PathVariable Long id)
	 {
		 Student student =studentRepository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("Student with this ID is not exist :" + id));
		 return ResponseEntity.ok(student);
	 }
	
	//update student Api
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student studentDetails)
	{
		 Student student =studentRepository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("Student with this ID is not exist :" + id));
		 student.setFirstName(studentDetails.getFirstName());
		 student.setLastName(studentDetails.getLastName());
		 student.setEmailId(studentDetails.getEmailId());
		 student.setCourse(studentDetails.getCourse());
		 
		 Student updateStudent= studentRepository.save(student);
		 return ResponseEntity.ok(updateStudent);
		 
	}	
	
	//delete student
	@DeleteMapping("/students/{id}")
	public ResponseEntity <Map<String, Boolean> >deleteStudent(@PathVariable Long id)
	{
		Student student =studentRepository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("Student with this ID is not exist :" + id));
		
		studentRepository.delete(student);
		Map<String, Boolean> response= new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}