package com.sjsuext.cloudproject.model;

import java.util.Date;

public class StudentDetails {

	private int id;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private String gender;
	
	private String programs;
	
	private String about;
	
	// file name
	private String name;
	
	private Date fileUploadTime;
	
	private String fileDesc;
	
	private Date fileUpdateTime;

	public Date getFileUploadTime() {
		return fileUploadTime;
	}

	public void setFileUploadTime(Date fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public Date getFileUpdateTime() {
		return fileUpdateTime;
	}

	public void setFileUpdateTime(Date fileUpdateTime) {
		this.fileUpdateTime = fileUpdateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrograms() {
		return programs;
	}

	public void setPrograms(String programs) {
		this.programs = programs;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StudentDetails [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", programs=" + programs
				+ ", about=" + about + ", name=" + name + ", fileUploadTime=" + fileUploadTime + ", fileDesc="
				+ fileDesc + ", fileUpdateTime=" + fileUpdateTime + "]";
	}
}
