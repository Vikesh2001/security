package com.security.testing.dto;

import java.util.List;

public class TeacherDto extends UserDto {
    private String employeeId;
    private List<Long> subjectIds;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public List<Long> getSubjectIds() {
		return subjectIds;
	}
	public void setSubjectIds(List<Long> subjectIds) {
		this.subjectIds = subjectIds;
	}
    
    
}
