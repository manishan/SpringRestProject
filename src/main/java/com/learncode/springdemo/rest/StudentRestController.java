package com.learncode.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learncode.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	private List<Student> theStudents;
	
	//define postConstruct for loading student list at once
	@PostConstruct
	public void loadData() {
		 	theStudents =  new ArrayList<Student>();
			theStudents.add(new Student("Manisha","Naik"));
			theStudents.add(new Student("Poornima","Patel"));
			theStudents.add(new Student("Srishti","Chapi"));
	}
	
	//define an end point for "/student"
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
			//add condition for validation of studentId
			if(studentId >= theStudents.size() || studentId <0) {
				throw new StudentNotFoundException("Student Id not found -"+studentId);
			}
			return theStudents.get(studentId);
	}
	
	//add exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		//create a studentErrorResponse
		
		StudentErrorResponse error = new StudentErrorResponse(); 
		//return responseEntity
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		//return responseEntity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	//add exception handler for generic exception
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
		
		//create a studentErrorResponse
		
				StudentErrorResponse error = new StudentErrorResponse(); 
				//return responseEntity
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage(ex.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
				
				//return responseEntity
				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
}
