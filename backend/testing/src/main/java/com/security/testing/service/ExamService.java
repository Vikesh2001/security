package com.security.testing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.security.testing.dto.ExamDto;
import com.security.testing.dto.ExamResultDto;
import com.security.testing.entity.Exam;
import com.security.testing.entity.ExamResult;
import com.security.testing.entity.Student;
import com.security.testing.entity.Subject;
import com.security.testing.exception.ResourceNotFoundException;
import com.security.testing.repository.ExamRepository;
import com.security.testing.repository.ExamResultRepository;
import com.security.testing.repository.StudentRepository;
import com.security.testing.repository.SubjectRepository;

@Service
public class ExamService {
    
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    
    
    
    public ExamService(ExamRepository examRepository, ExamResultRepository examResultRepository,
			StudentRepository studentRepository, SubjectRepository subjectRepository, ModelMapper modelMapper) {
		this.examRepository = examRepository;
		this.examResultRepository = examResultRepository;
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.modelMapper = modelMapper;
	}

	public ExamDto createExam(ExamDto examDto) {
        Exam exam = modelMapper.map(examDto, Exam.class);
        Exam savedExam = examRepository.save(exam);
        return modelMapper.map(savedExam, ExamDto.class);
    }
    
    public ExamResultDto enterExamMarks(ExamResultDto examResultDto) {
        Exam exam = examRepository.findById(examResultDto.getExamId())
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", examResultDto.getExamId()));
        
        Student student = studentRepository.findById(examResultDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", examResultDto.getStudentId()));
        
        Subject subject = subjectRepository.findById(examResultDto.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", examResultDto.getSubjectId()));
        
        ExamResult examResult = modelMapper.map(examResultDto, ExamResult.class);
        examResult.setExam(exam);
        examResult.setStudent(student);
        examResult.setSubject(subject);
        
        ExamResult savedResult = examResultRepository.save(examResult);
        return modelMapper.map(savedResult, ExamResultDto.class);
    }
    
    public List<ExamResultDto> getStudentResults(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        
        List<ExamResult> results = examResultRepository.findByStudent(student);
        return results.stream()
                .map(result -> modelMapper.map(result, ExamResultDto.class))
                .collect(Collectors.toList());
    }
    public List<ExamDto> getAllExams() {
        return examRepository.findAll().stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }
}