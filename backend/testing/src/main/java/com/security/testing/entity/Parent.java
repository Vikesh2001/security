package com.security.testing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "parents")
@PrimaryKeyJoinColumn(name = "user_id")
public class Parent extends User {
	
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    private String occupation;
    // Additional parent-specific fields

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
    
}
