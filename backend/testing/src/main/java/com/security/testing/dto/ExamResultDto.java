package com.security.testing.dto;

public class ExamResultDto {
    private Long id;
    private Long examId;
    private String examName;
    private Long studentId;
    private String studentName;
    private Long subjectId;
    private String subjectName;
    private Double marksObtained;
    private Double maxMarks;
    private String grade;
    private String remarks;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Double getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(Double marksObtained) {
		this.marksObtained = marksObtained;
	}
	public Double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(Double maxMarks) {
		this.maxMarks = maxMarks;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
}
