package com.learncode.springdemo.rest;

public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public StudentNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public StudentNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	

}
