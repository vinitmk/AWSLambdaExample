package com.sjsuext.cloudproject.model;

public class StudentAPIRequest {

	private String httpMethod;
	
	private String username;
	
	private StudentDetails student;

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public StudentDetails getStudent() {
		return student;
	}

	public void setStudent(StudentDetails student) {
		this.student = student;
	}		
}

