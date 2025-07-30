package com.security.testing.dto;


//import lombok.Data;


public class StudentProfileDto {
    private Long id;
    private String admissionNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String className;
    private String sectionName;
    private String classTeacherName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getClassTeacherName() {
		return classTeacherName;
	}
	public void setClassTeacherName(String classTeacherName) {
		this.classTeacherName = classTeacherName;
	}
    
    // You can add more fields as needed
    
    
}
